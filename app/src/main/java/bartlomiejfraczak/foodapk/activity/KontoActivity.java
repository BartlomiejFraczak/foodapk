package bartlomiejfraczak.foodapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.modele.PrzepisModel;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KontoActivity extends CustomAppCompatActivity {
    private Button bUlubioneKonto;
    private Button bWylogujKonto;
    private PrzepisApi przepisApi;


    private static KontoActivity instancja;

    public static KontoActivity getInstancja() {
        return instancja;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konto);
        instancja = this;
        init();
    }

    private void init() {
        dodajBackButton();
        bUlubioneKonto = findViewById(R.id.bUlubioneKonto);
        bWylogujKonto = findViewById(R.id.bWylogujKonto);

//        bSzukaj.setOnClickListener(view -> {
//            przepisApi.getPrzepisy(etNazwa.getText().toString(),
//                            etKuchnia.getText().toString(),
//                            etDieta.getText().toString(),
//                            etNietolerancje.getText().toString(),
//                            etSkladniki.getText().toString()
//                    )
//                    .enqueue(new Callback<List<Przepis>>() {
//                        @Override
//                        public void onResponse(Call<List<Przepis>> call, Response<List<Przepis>> response) {
//                            List<Przepis> przepisy = response.body();
//                            Intent intent = new Intent(SzukajActivity.this, PrzepisyActivity.class);
//                            PrzepisModel.getInstancja().setPrzepisy(przepisy);
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Przepis>> call, Throwable t) {
//                            Toast.makeText(SzukajActivity.this, "błąd", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//        });
        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);

        bUlubioneKonto.setOnClickListener(view -> {
            Uzytkownik u = GlobalneInfo.getInstancja().getZalogowanyUzytkownik();
            if (u == null) return;
            przepisApi.getUlubioneUzytkownika(u.getId())
                    .enqueue(new Callback<List<PrzepisSzczegolowy>>() {
                        @Override
                        public void onResponse(Call<List<PrzepisSzczegolowy>> call, Response<List<PrzepisSzczegolowy>> response) {
                            List<PrzepisSzczegolowy> przepisy = response.body();
                            if (przepisy == null || przepisy.isEmpty()) {
                                Toast.makeText(KontoActivity.this, R.string.brakulubionych, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent intent = new Intent(KontoActivity.this, PrzepisyActivity.class);

                            PrzepisModel.getInstancja().setPrzepisySzczegolowe(przepisy);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<List<PrzepisSzczegolowy>> call, Throwable t) {
                            Toast.makeText(KontoActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                        }
                    });
        });
        bWylogujKonto.setOnClickListener(view -> {
            GlobalneInfo.getInstancja().setCzyUzytkownikZalogowany(false);
            MainActivity.getInstancja().zmianaUzytkownika();
            Toast.makeText(KontoActivity.this, R.string.wylogowano, Toast.LENGTH_SHORT).show();
            finish();
        });
    }


    public void updateJezyka() {
        bUlubioneKonto.setText(R.string.ulubione);
        bWylogujKonto.setText(R.string.wyloguj);
        super.updateJezyka();
    }
}