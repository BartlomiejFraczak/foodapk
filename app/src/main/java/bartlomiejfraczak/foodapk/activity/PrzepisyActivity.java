package bartlomiejfraczak.foodapk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.komunikacja.PobierzObraz;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.modele.PrzepisModel;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;
import bartlomiejfraczak.foodapk.util.Jezyk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrzepisyActivity extends CustomAppCompatActivity {

    private List<Przepis> przepisy;
    private TableLayout tPrzepisy;
    PrzepisApi przepisApi;

    private static PrzepisyActivity instancja;


    public static PrzepisyActivity getInstancja() {
        return instancja;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przepisy);
        instancja = this;
        init();
    }

    private void init() {
        tPrzepisy = findViewById(R.id.tPrzepisy);
        setTitle(R.string.title_activity_przepisy);
        przepisy = PrzepisModel.getInstancja().getPrzepisy();

        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);

        dodajPrzepisyDoTabeli();

//        StringBuilder sb = new StringBuilder();
//
//        for (Przepis p : przepisy) {
//            sb.append(p.getId());
//            sb.append("\n");
//            sb.append(p.getTitle());
//            sb.append("\n");
//            sb.append(p.getImage());
//            sb.append("\n");
//            sb.append(p.getImageType());
//            sb.append("\n\n");
//        }
//        if (sb.length() == 0) {
//            sb.append("*nic*");
//        }
//
//        tvPrzepisy.setText(sb.toString());

    }

    private void dodajPrzepisyDoTabeli() {
        if (przepisy.isEmpty()) {
            //todo
            System.out.println("przepisy jest puste");
            return;
        }

        for (Przepis p : przepisy) {
            ViewGroup.LayoutParams layoutParams;
            TableRow tr = new TableRow(this);
            ImageView iv = new ImageView(this);
            TextView tv = new TextView(this);


            tr.setClickable(true);
            tr.setOnClickListener(view -> {
                przepisApi.getPrzepisSzczegolowy(String.valueOf(p.getId()))
                        .enqueue(new Callback<PrzepisSzczegolowy>() {
                            @Override
                            public void onResponse(Call<PrzepisSzczegolowy> call, Response<PrzepisSzczegolowy> response) {
                                PrzepisSzczegolowy przepis = response.body();
                                Intent intent = new Intent(PrzepisyActivity.this, PrzepisActivity.class);
                                PrzepisSzczegolowyModel.getInstancja().setPrzepisSzczegolowy(przepis);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<PrzepisSzczegolowy> call, Throwable t) {
                                Toast.makeText(PrzepisyActivity.this, "błąd", Toast.LENGTH_SHORT).show();
                            }
                        });
            });


            new PobierzObraz(iv).execute(p.getImage());
            //todo zawijanie tekstu w tabeli

            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(10, 10, 10, 10);
//            layoutParams.weight = 1;
//            tv.setLayoutParams(layoutParams);
            tv.setText(p.getTitle());


//            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(10, 10, 10, 10);
//            layoutParams.weight = 0;
//            iv.setLayoutParams(layoutParams);

            tr.setGravity(Gravity.CENTER_VERTICAL);

            tr.addView(iv);
            tr.addView(tv);
            tPrzepisy.addView(tr);
        }

//        tPrzepisy.addView();

    }


    public void updateJezyka(){
        this.setTitle(R.string.title_activity_przepisy);

        super.updateJezyka();

    }
}