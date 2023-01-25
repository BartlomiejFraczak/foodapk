package bartlomiejfraczak.foodapk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.komunikacja.PobierzObraz;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.komunikacja.Tlumacz;
import bartlomiejfraczak.foodapk.modele.PrzepisModel;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
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
    }

    private void dodajPrzepisyDoTabeli() {
        if (przepisy.isEmpty()) {
            //todo wyświetl napis "brak przepisów"
            System.out.println("przepisy jest puste");
            return;
        }
        tvPrzepisy = new ArrayList<>();
        for (Przepis przepis : przepisy) {
//            ViewGroup.LayoutParams layoutParams;
            TableRow tableRow = new TableRow(this);
            ImageView imageView = new ImageView(this);
            TextView textView = new TextView(this);


            tableRow.setClickable(true);
            tableRow.setOnClickListener(view -> {
                int uzytkownikId = 0;
                if (GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
                    uzytkownikId = GlobalneInfo.getInstancja().getZalogowanyUzytkownik().getId();
                }
                przepisApi.getPrzepisSzczegolowy(przepis.getId(), uzytkownikId)
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

            //todo zawijanie tekstu w tabeli

            // layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvPrzepisy.add(textView);

            new PobierzObraz(imageView).execute(przepis.getImage());
            textView.setText(przepis.getTitle());
            tableRow.setGravity(Gravity.CENTER_VERTICAL);

            tableRow.addView(imageView);
            tableRow.addView(textView);
            tPrzepisy.addView(tableRow);
        }
    }

    @Override
    public void updateJezyka() {
        this.setTitle(R.string.title_activity_przepisy);

        switch (GlobalneInfo.getInstancja().getRegion()) {
            case "pl":
                List<String> przepisyTytuly = przepisy.stream().map(Przepis::getTitle).collect(Collectors.toList());
                new Tlumacz(tvPrzepisy, "en", "pl", przepisyTytuly);
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