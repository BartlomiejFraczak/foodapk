package bartlomiejfraczak.foodapk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.komunikacja.PobierzObraz;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.modele.PrzepisModel;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import bartlomiejfraczak.foodapk.util.Jezyk;
import bartlomiejfraczak.foodapk.util.Tlumacz;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrzepisyActivity extends CustomAppCompatActivity {

    private List<Przepis> przepisy;
    private List<TextView> tvPrzepisy;
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
        dodajBackButton();
        tPrzepisy = findViewById(R.id.tPrzepisy);
        setTitle(R.string.title_activity_przepisy);
        przepisy = PrzepisModel.getInstancja().getPrzepisy();

        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);

        dodajPrzepisyDoTabeli();
        updateJezyka();

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
        tvPrzepisy = new ArrayList<>();
        for (Przepis p : przepisy) {
            ViewGroup.LayoutParams layoutParams;
            TableRow tr = new TableRow(this);
            ImageView iv = new ImageView(this);
            TextView tv = new TextView(this);


            tr.setClickable(true);
            tr.setOnClickListener(view -> {
                int uzytkownikId = 0;
                if (GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
                    uzytkownikId = GlobalneInfo.getInstancja().getZalogowanyUzytkownik().getId();
                }
                przepisApi.getPrzepisSzczegolowy(p.getId(), uzytkownikId)
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
            tvPrzepisy.add(tv);


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

    @Override
    public void updateJezyka() {
        this.setTitle(R.string.title_activity_przepisy);

        switch (GlobalneInfo.getInstancja().getRegion()) {
            case "pl":
                List<String> przepisyTytuly = przepisy.stream().map(Przepis::getTitle).collect(Collectors.toList());
                Jezyk.przetlumacz(tvPrzepisy, "en", "pl", przepisyTytuly);
                break;
            case "en":
                for (int i = 0; i < tvPrzepisy.size(); i++) {
                    tvPrzepisy.get(i).setText(przepisy.get(i).getTitle());
                }
                break;
            default:
                System.out.println("błąd w PrzepisActivity.updateJezyka() switch");
                break;

        }
        super.updateJezyka();
    }

}