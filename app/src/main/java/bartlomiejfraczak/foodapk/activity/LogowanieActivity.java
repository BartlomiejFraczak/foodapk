package bartlomiejfraczak.foodapk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import bartlomiejfraczak.foodapk.komunikacja.LoginApi;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.modele.PrzepisSzczegolowyModel;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import bartlomiejfraczak.foodapk.util.Jezyk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogowanieActivity extends CustomAppCompatActivity {
    TextView tvLoginLabel;
    TextView tvHasloLabel;
    EditText etLogin;
    EditText etHaslo;
    Button bZaloguj;
    Button bZarejestruj;

    LoginApi loginApi;

    private static LogowanieActivity instancja;


    public static LogowanieActivity getInstancja() {
        return instancja;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
        instancja = this;
        init();
    }

    private void init() {
        dodajBackButton();
        setTitle(R.string.title_activity_logowanie);
        tvLoginLabel = findViewById(R.id.tvLoginLabel);
        tvHasloLabel = findViewById(R.id.tvHasloLabel);
        etLogin = findViewById(R.id.etLogin);
        etHaslo = findViewById(R.id.etHaslo);
        bZaloguj = findViewById(R.id.bZaloguj);
        bZarejestruj = findViewById(R.id.bZarejestruj);

        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        loginApi = retrofitService.getRetrofit().create(LoginApi.class);

        bZaloguj.setOnClickListener(view -> {
            zaloguj();
        });
        bZarejestruj.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, RejestracjaActivity.class);
            startActivity(myIntent);
        });
    }

    private void zaloguj() {
        Uzytkownik uzytkownik = new Uzytkownik(
                etLogin.getText().toString(),
                etHaslo.getText().toString());

        loginApi.zaloguj(uzytkownik)
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Integer id = response.body();
                        if (id != null && id != 0) {
                            uzytkownik.setId(id);
                            GlobalneInfo.getInstancja().setZalogowanyUzytkownik(uzytkownik);
                            GlobalneInfo.getInstancja().setCzyUzytkownikZalogowany(true);
                            Toast.makeText(LogowanieActivity.this, R.string.zalogowano, Toast.LENGTH_SHORT).show();
                            MainActivity.getInstancja().zmianaUzytkownika();
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(LogowanieActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void updateJezyka() {
        this.setTitle(R.string.title_activity_logowanie);
        tvLoginLabel.setText(R.string.login);
        tvHasloLabel.setText(R.string.haslo);
        bZaloguj.setText(R.string.zaloguj);
        bZarejestruj.setText(R.string.zarejestruj);
        super.updateJezyka();
    }
}