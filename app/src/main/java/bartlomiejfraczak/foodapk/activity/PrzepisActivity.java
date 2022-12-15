package bartlomiejfraczak.foodapk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.encje.Skladnik;
import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import bartlomiejfraczak.foodapk.komunikacja.PobierzObraz;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.modele.PrzepisModel;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import bartlomiejfraczak.foodapk.util.Jezyk;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrzepisActivity extends CustomAppCompatActivity {
    private PrzepisSzczegolowy przepisSzczegolowy;
    private boolean lubi;
    private TextView tvTitle;
    private ImageView iv;
    private ImageView ivUlubiony;
    private ImageView ivInfo;
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
        lubi = false;
        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);

        setTitle(R.string.title_activity_przepis);

        przepisSzczegolowy = PrzepisSzczegolowyModel.getInstancja().getPrzepisSzczegolowy();
        tvTitle = findViewById(R.id.tvTitle);
        iv = findViewById(R.id.iv);
        ivUlubiony = findViewById(R.id.ivUlubiony);
        ivInfo = findViewById(R.id.ivInfo);
        tvSkladnikiLabel = findViewById(R.id.tvSkladnikiLabel);
        tvExtendedIngredients = findViewById(R.id.tvExtendedIngredients);
        tvInstrukcjaLabel = findViewById(R.id.tvInstrukcjaLabel);
        tvInstrukcja = findViewById(R.id.tvInstrukcja);

        tvTitle.setText(przepisSzczegolowy.getTitle());

        new PobierzObraz(iv).execute(przepisSzczegolowy.getImage());

        if (GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
            Uzytkownik zalogowanyUzytkownik = GlobalneInfo.getInstancja().getZalogowanyUzytkownik();
            przepisApi.czyUzytkownikLubi(
                    String.valueOf(zalogowanyUzytkownik.getId()),
                    String.valueOf(przepisSzczegolowy.getId())
            ).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    lubi = Boolean.TRUE.equals(response.body());
                    if (lubi) {
                        ivUlubiony.setImageResource(R.mipmap.serce_pelne_foreground);
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Toast.makeText(PrzepisActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                }
            });

        }

        ivUlubiony.setClickable(true);
        ivUlubiony.setOnClickListener(view -> {
            Uzytkownik zalogowanyUzytkownik = GlobalneInfo.getInstancja().getZalogowanyUzytkownik();
            if (!GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
                Toast.makeText(PrzepisActivity.this, R.string.niejesteszalogowany, Toast.LENGTH_SHORT).show();
            } else {
                if (lubi) {
                    przepisApi.usunZUlubionych(
                            String.valueOf(zalogowanyUzytkownik.getId()),
                            String.valueOf(przepisSzczegolowy.getId())
                    ).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            ivUlubiony.setImageResource(R.mipmap.serce_puste_foreground);
                            lubi = false;
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(PrzepisActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    przepisApi.dodajDoUlubionych(
                            String.valueOf(zalogowanyUzytkownik.getId()),
                            String.valueOf(przepisSzczegolowy.getId())
                    ).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            ivUlubiony.setImageResource(R.mipmap.serce_pelne_foreground);
                            lubi = true;
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(PrzepisActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }
        });

        ivInfo.setClickable(true);
        ivInfo.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, InfoActivity.class);
            startActivity(myIntent);
        });

        tvSkladnikiLabel.setText(R.string.skladnikiDwukropek);
        StringBuilder sb = new StringBuilder();
        sb.append("<ol>");
        for (
                Skladnik s : przepisSzczegolowy.getExtendedIngredients()) {
            sb.append("<li>");
            sb.append(s.getOriginal());
            sb.append("</li>");
        }
        sb.append("</ol>");
        tvExtendedIngredients.setText(Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_COMPACT));

        tvInstrukcjaLabel.setText(R.string.instrukcjaDwukropek);
        tvInstrukcja.setText(Html.fromHtml(przepisSzczegolowy.getInstructions(), Html.FROM_HTML_MODE_COMPACT));
    }


    public void updateJezyka() {
        setTitle(R.string.title_activity_przepis);
        tvSkladnikiLabel.setText(R.string.skladnikiDwukropek);
        tvInstrukcjaLabel.setText(R.string.instrukcjaDwukropek);

        super.updateJezyka();
    }
}