package bartlomiejfraczak.foodapk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.encje.Skladnik;
import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import bartlomiejfraczak.foodapk.komunikacja.PobierzObraz;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.komunikacja.Tlumacz;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrzepisActivity extends CustomAppCompatActivity {
    private PrzepisSzczegolowy przepisSzczegolowy;
    private String listaSkladnikowHtml;
    private TextView tvTitle;
    private ImageView iv;
    private ImageView ivUlubiony;
    private ImageView ivInfo;
    private ImageView ivNotatki;
    private TextView tvSkladnikiLabel;
    private TextView tvExtendedIngredients;
    private TextView tvInstrukcjaLabel;
    private TextView tvInstrukcja;

    private PrzepisApi przepisApi;

    private static PrzepisActivity instancja;


    public static PrzepisActivity getInstancja() {
        return instancja;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przepis);
        instancja = this;
        init();
    }

    private void init() {
        dodajBackButton();
        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);

        setTitle(R.string.title_activity_przepis);

        przepisSzczegolowy = PrzepisSzczegolowyModel.getInstancja().getPrzepisSzczegolowy();
        tvTitle = findViewById(R.id.tvTitle);
        iv = findViewById(R.id.iv);
        ivUlubiony = findViewById(R.id.ivUlubiony);
        ivInfo = findViewById(R.id.ivInfo);
        ivNotatki = findViewById(R.id.ivNotatki);
        tvSkladnikiLabel = findViewById(R.id.tvSkladnikiLabel);
        tvExtendedIngredients = findViewById(R.id.tvExtendedIngredients);
        tvInstrukcjaLabel = findViewById(R.id.tvInstrukcjaLabel);
        tvInstrukcja = findViewById(R.id.tvInstrukcja);

        tvTitle.setText(przepisSzczegolowy.getTitle());

        new PobierzObraz(iv).execute(przepisSzczegolowy.getImage());

        if (GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
            if (przepisSzczegolowy.getUlubiony()) {
                ivUlubiony.setImageResource(R.mipmap.serce_pelne_foreground);
            }
        }

        ivUlubiony.setClickable(true);
        ivUlubiony.setOnClickListener(view -> {
            Uzytkownik zalogowanyUzytkownik = GlobalneInfo.getInstancja().getZalogowanyUzytkownik();
            if (!GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
                Toast.makeText(PrzepisActivity.this, R.string.niejesteszalogowany, Toast.LENGTH_SHORT).show();
            } else {
                przepisApi.edytujPrzepisInfo(
                        zalogowanyUzytkownik.getId(),
                        przepisSzczegolowy.getId(),
                        !przepisSzczegolowy.getUlubiony(),
                        przepisSzczegolowy.getNotatka()
                ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        przepisSzczegolowy.setUlubiony(!przepisSzczegolowy.getUlubiony());
                        if (przepisSzczegolowy.getUlubiony()) {
                            ivUlubiony.setImageResource(R.mipmap.serce_pelne_foreground);
                        } else {
                            ivUlubiony.setImageResource(R.mipmap.serce_puste_foreground);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(PrzepisActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ivInfo.setClickable(true);
        ivInfo.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, InfoActivity.class);
            startActivity(myIntent);
        });

        ivNotatki.setOnClickListener(view -> {
            if (!GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
                Toast.makeText(PrzepisActivity.this, R.string.niejesteszalogowany, Toast.LENGTH_SHORT).show();
            } else {
                Intent myIntent = new Intent(this, NotatkiActivity.class);
                startActivity(myIntent);
            }
        });

        tvSkladnikiLabel.setText(R.string.skladnikiDwukropek);
        StringBuilder sb = new StringBuilder();
        sb.append("<ol>");
        for (Skladnik s : przepisSzczegolowy.getExtendedIngredients()) {
            sb.append("<li>");
            sb.append(s.getOriginal());
            sb.append("</li>");
        }
        sb.append("</ol>");
        listaSkladnikowHtml = sb.toString();
        tvExtendedIngredients.setText(Html.fromHtml(listaSkladnikowHtml, Html.FROM_HTML_MODE_COMPACT));

        tvInstrukcjaLabel.setText(R.string.instrukcjaDwukropek);
        tvInstrukcja.setText(Html.fromHtml(przepisSzczegolowy.getInstructions(), Html.FROM_HTML_MODE_COMPACT));

        updateJezyka();
    }

    public void updateJezyka() {
        setTitle(R.string.title_activity_przepis);
        tvSkladnikiLabel.setText(R.string.skladnikiDwukropek);
        tvInstrukcjaLabel.setText(R.string.instrukcjaDwukropek);


        switch (GlobalneInfo.getInstancja().getRegion()) {
            case "pl":
                System.out.println("elo");
                List<TextView> tvs = new ArrayList<>();
                List<String> teksty = new ArrayList<>();

                tvs.add(tvExtendedIngredients);
                tvs.add(tvInstrukcja);
                tvs.add(tvTitle);

                teksty.add(listaSkladnikowHtml);
                teksty.add(przepisSzczegolowy.getInstructions());
                teksty.add(przepisSzczegolowy.getTitle());

                new Tlumacz(tvs, "en", "pl", teksty);
                break;
            case "en":
                tvExtendedIngredients.setText(Html.fromHtml(listaSkladnikowHtml, Html.FROM_HTML_MODE_COMPACT));
                tvInstrukcja.setText(Html.fromHtml(przepisSzczegolowy.getInstructions(), Html.FROM_HTML_MODE_COMPACT));
                tvTitle.setText(Html.fromHtml(przepisSzczegolowy.getTitle(), Html.FROM_HTML_MODE_COMPACT));
                break;
            default:
                System.out.println("błąd w PrzepisyActivity.updateJezyka() switch");
                break;
        }
        super.updateJezyka();
    }
}