package bartlomiejfraczak.foodapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotatkiActivity extends CustomAppCompatActivity {
    private static NotatkiActivity instancja;

    private Button bZapisz;
    private EditText etNotatka;

    private PrzepisApi przepisApi;
    PrzepisSzczegolowy przepisSzczegolowy;

    public static NotatkiActivity getInstancja() {
        return instancja;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notatki);
        instancja = this;
        init();
    }

    private void init() {
        setTitle(R.string.title_activity_notatki);

        bZapisz = findViewById(R.id.bZapiszNotatki);
        etNotatka = findViewById(R.id.etNotatka);

        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);

        przepisSzczegolowy = PrzepisSzczegolowyModel.getInstancja().getPrzepisSzczegolowy();

        bZapisz.setOnClickListener(view -> {
            if (GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
                Uzytkownik zalogowanyUzytkownik = GlobalneInfo.getInstancja().getZalogowanyUzytkownik();
                przepisApi.edytujPrzepisInfo(
                        zalogowanyUzytkownik.getId(),
                        przepisSzczegolowy.getId(),
                        przepisSzczegolowy.getUlubiony(),
                        String.valueOf(etNotatka.getText())
                ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        przepisSzczegolowy.setNotatka(String.valueOf(etNotatka.getText()));
                        Toast.makeText(NotatkiActivity.this, R.string.zapisano, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(NotatkiActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        etNotatka.setText(przepisSzczegolowy.getNotatka());
        etNotatka.setHint(R.string.pisztutaj);


    }


    public void updateJezyka() {
        setTitle(R.string.title_activity_notatki);
        bZapisz.setText(R.string.zapisz);
        etNotatka.setHint(R.string.pisztutaj);

        super.updateJezyka();

    }
}