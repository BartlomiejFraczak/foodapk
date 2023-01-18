package bartlomiejfraczak.foodapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import bartlomiejfraczak.foodapk.komunikacja.LoginApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RejestracjaActivity extends CustomAppCompatActivity {
    private TextView tvLoginLabel;
    private EditText etLogin;
    private TextView tvHasloLabel;
    private EditText etHaslo;
    private TextView tvHasloLabel2;
    private EditText etHaslo2;
    private Button bZarejestruj;

    LoginApi loginApi;

    private static RejestracjaActivity instancja;


    public static RejestracjaActivity getInstancja() {
        return instancja;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        instancja = this;
        init();
    }

    private void init() {
        dodajBackButton();
        setTitle(R.string.title_activity_rejestracja);
        tvLoginLabel = findViewById(R.id.tvLoginLabelRejestracja);
        etLogin = findViewById(R.id.etLoginRejestracja);
        tvHasloLabel = findViewById(R.id.tvHasloLabelRejestracja);
        etHaslo = findViewById(R.id.etHasloRejestracja);
        tvHasloLabel2 = findViewById(R.id.tvHasloLabelRejestracja2);
        etHaslo2 = findViewById(R.id.etHasloRejestracja2);
        bZarejestruj = findViewById(R.id.bZarejestrujRejestracja);

        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        loginApi = retrofitService.getRetrofit().create(LoginApi.class);

        bZarejestruj.setOnClickListener(view -> {
            zarejestruj();
        });

    }

    private void zarejestruj() {
        if (!walidacjaDanych()) {
            return;
        }
        Uzytkownik uzytkownik = new Uzytkownik(
                etLogin.getText().toString(),
                etHaslo.getText().toString());
        loginApi.zarejestruj(uzytkownik)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        boolean good = Boolean.TRUE.equals(response.body());
                        if (good) {
                            Toast.makeText(RejestracjaActivity.this, R.string.zarejestrowano, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RejestracjaActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(RejestracjaActivity.this, R.string.blad, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean walidacjaDanych() {
        String login = etLogin.getText().toString().trim();
        String haslo1 = etHaslo.getText().toString().trim();
        String haslo2 = etHaslo2.getText().toString().trim();
        if (login.isEmpty()) {
            etLogin.setError(getText(R.string.wymaganylogin));
            etLogin.requestFocus();
            return false;
        }
        if (haslo1.isEmpty()) {
            etHaslo.setError(getText(R.string.wymaganehaslo));
            etHaslo.requestFocus();
            return false;
        }
        if (!haslo1.equals(haslo2)) {
            etHaslo2.setError(getText(R.string.haslasieniezgaczaja));
            etHaslo2.requestFocus();
            return false;
        }
        return true;
    }

    public void updateJezyka() {
        setTitle(R.string.title_activity_rejestracja);

        tvLoginLabel.setText(R.string.login);
        tvHasloLabel.setText(R.string.haslo);
        tvHasloLabel2.setText(R.string.haslo);
        bZarejestruj.setText(R.string.zarejestruj);

        super.updateJezyka();
    }
}