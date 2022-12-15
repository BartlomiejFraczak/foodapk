package bartlomiejfraczak.foodapk.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.modele.PrzepisModel;
import bartlomiejfraczak.foodapk.util.Jezyk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SzukajActivity extends CustomAppCompatActivity {

    private EditText etNazwa;
    private EditText etSkladniki;

    private TextView tvNazwa;
    private TextView tvSkladniki;
    private TextView tvKuchnia;
    private TextView tvDieta;
    private TextView tvNietolerancje;

    private CheckBox cbWhole30;
    private CheckBox cbGlutenfree;
    private CheckBox cbKetogenic;
    private CheckBox cbVegetarian;
    private CheckBox cbLactovegetarian;
    private CheckBox cbOvovegetarian;
    private CheckBox cbVegan;
    private CheckBox cbPescetarian;
    private CheckBox cbPaleo;
    private CheckBox cbPrimal;
    private CheckBox cbLowfodmap;

    private Button bSzukaj;
    PrzepisApi przepisApi;

    private static SzukajActivity instancja;


    public static SzukajActivity getInstancja() {
        return instancja;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szukaj);
        setTitle(R.string.title_activity_szukaj);
        instancja = this;
        init();
    }


    private void init() {
//        ukryjZmianeJezyka();

        etNazwa = findViewById(R.id.etNazwa);
        etSkladniki = findViewById(R.id.etSkladniki);

        tvNazwa = findViewById(R.id.tvNazwa);
        tvSkladniki = findViewById(R.id.tvSkladniki);
        tvKuchnia = findViewById(R.id.tvKuchnia);
        tvDieta = findViewById(R.id.tvDieta);
        tvNietolerancje = findViewById(R.id.tvNietolerancje);

        bSzukaj = findViewById(R.id.bSzukaj);

        cbGlutenfree = findViewById(R.id.cbGlutenfree);
        cbKetogenic = findViewById(R.id.cbKetogenic);
        cbVegetarian = findViewById(R.id.cbVegetarian);
        cbLactovegetarian = findViewById(R.id.cbLactovegetarian);
        cbOvovegetarian = findViewById(R.id.cbOvovegetarian);
        cbVegan = findViewById(R.id.cbVegan);
        cbPescetarian = findViewById(R.id.cbPescetarian);
        cbPaleo = findViewById(R.id.cbPaleo);
        cbPrimal = findViewById(R.id.cbPrimal);
        cbLowfodmap = findViewById(R.id.cbLowfodmap);
        cbWhole30 = findViewById(R.id.cbWhole30);

        RetrofitService retrofitService = RetrofitService.getInstancja(this);
        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);

        bSzukaj.setOnClickListener(view -> {
            String diety = getDiety();
            przepisApi.getPrzepisy(etNazwa.getText().toString(),
                            getKuchnie(),
                            getDiety(),
                            getNietolerancje(),
                            etSkladniki.getText().toString()
                    )
                    .enqueue(new Callback<List<Przepis>>() {
                        @Override
                        public void onResponse(Call<List<Przepis>> call, Response<List<Przepis>> response) {
                            List<Przepis> przepisy = response.body();
                            Intent intent = new Intent(SzukajActivity.this, PrzepisyActivity.class);
                            PrzepisModel.getInstancja().setPrzepisy(przepisy);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<List<Przepis>> call, Throwable t) {
                            Toast.makeText(SzukajActivity.this, "błąd", Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }

    private String getDiety() {
        List<String> list = new ArrayList<>();
        if (cbGlutenfree.isChecked()) list.add("gluten+free");
        if (cbKetogenic.isChecked()) list.add("ketogenic");
        if (cbVegetarian.isChecked()) list.add("vegetarian");
        if (cbLactovegetarian.isChecked()) list.add("lacto-vegetarian");
        if (cbOvovegetarian.isChecked()) list.add("ovo-vegetarian");
        if (cbVegan.isChecked()) list.add("vegan");
        if (cbPescetarian.isChecked()) list.add("pescetarian");
        if (cbPaleo.isChecked()) list.add("paleo");
        if (cbPrimal.isChecked()) list.add("primal");
        if (cbLowfodmap.isChecked()) list.add("low+fodmap");
        if (cbWhole30.isChecked()) list.add("whole30");

        return String.join(",", list);
    }

    private String getKuchnie() {
        return "";
    }

    private String getNietolerancje() {
        return "";
    }

    public void updateJezyka() {
        this.setTitle(R.string.title_activity_szukaj);
        tvNazwa.setText(R.string.nazwa);
        tvSkladniki.setText(R.string.skladniki);
        tvKuchnia.setText(R.string.kuchnia);
        tvDieta.setText(R.string.dieta);
        tvNietolerancje.setText(R.string.nietolerancje);

        cbGlutenfree.setText(R.string.glutenfree);
        cbKetogenic.setText(R.string.ketogenic);
        cbVegetarian.setText(R.string.vegetarian);
        cbLactovegetarian.setText(R.string.lactovegetarian);
        cbOvovegetarian.setText(R.string.ovovegetarian);
        cbVegan.setText(R.string.vegan);
        cbPescetarian.setText(R.string.pescetarian);
        cbPaleo.setText(R.string.paleo);
        cbPrimal.setText(R.string.primal);
        cbLowfodmap.setText(R.string.lowfodmap);
        cbWhole30.setText(R.string.whole30);

        bSzukaj.setText(R.string.szukaj);



        super.updateJezyka();
    }
}