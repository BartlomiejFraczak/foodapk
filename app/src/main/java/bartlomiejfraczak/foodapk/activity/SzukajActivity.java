package bartlomiejfraczak.foodapk.activity;

import androidx.annotation.NonNull;

import android.app.ActionBar;
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

    private CheckBox cbDairy;
    private CheckBox cbEgg;
    private CheckBox cbGluten;
    private CheckBox cbGrain;
    private CheckBox cbPeanut;
    private CheckBox cbSeafood;
    private CheckBox cbSesame;
    private CheckBox cbShellfish;
    private CheckBox cbSoy;
    private CheckBox cbSulfite;
    private CheckBox cbTreenut;
    private CheckBox cbWheat;

    private CheckBox cbAfrican;
    private CheckBox cbAmerican;
    private CheckBox cbBritish;
    private CheckBox cbCajun;
    private CheckBox cbCaribbean;
    private CheckBox cbChinese;
    private CheckBox cbEasterneuropean;
    private CheckBox cbEuropean;
    private CheckBox cbFrench;
    private CheckBox cbGerman;
    private CheckBox cbGreek;
    private CheckBox cbIndian;
    private CheckBox cbIrish;
    private CheckBox cbItalian;
    private CheckBox cbJapanese;
    private CheckBox cbJewish;
    private CheckBox cbKorean;
    private CheckBox cbLatinamerican;
    private CheckBox cbMediterranean;
    private CheckBox cbMexican;
    private CheckBox cbMiddleeastern;
    private CheckBox cbNordic;
    private CheckBox cbSouthern;
    private CheckBox cbSpanish;
    private CheckBox cbThai;
    private CheckBox cbVietnamese;

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
        dodajBackButton();

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

        cbDairy = findViewById(R.id.cbDairy);
        cbEgg = findViewById(R.id.cbEgg);
        cbGluten = findViewById(R.id.cbGluten);
        cbGrain = findViewById(R.id.cbGrain);
        cbPeanut = findViewById(R.id.cbPeanut);
        cbSeafood = findViewById(R.id.cbSeafood);
        cbSesame = findViewById(R.id.cbSesame);
        cbShellfish = findViewById(R.id.cbShellfish);
        cbSoy = findViewById(R.id.cbSoy);
        cbSulfite = findViewById(R.id.cbSulfite);
        cbTreenut = findViewById(R.id.cbTreenut);
        cbWheat = findViewById(R.id.cbWheat);

        cbAfrican = findViewById(R.id.cbAfrican);
        cbAmerican = findViewById(R.id.cbAmerican);
        cbBritish = findViewById(R.id.cbBritish);
        cbCajun = findViewById(R.id.cbCajun);
        cbCaribbean = findViewById(R.id.cbCaribbean);
        cbChinese = findViewById(R.id.cbChinese);
        cbEasterneuropean = findViewById(R.id.cbEasterneuropean);
        cbEuropean = findViewById(R.id.cbEuropean);
        cbFrench = findViewById(R.id.cbFrench);
        cbGerman = findViewById(R.id.cbGerman);
        cbGreek = findViewById(R.id.cbGreek);
        cbIndian = findViewById(R.id.cbIndian);
        cbIrish = findViewById(R.id.cbIrish);
        cbItalian = findViewById(R.id.cbItalian);
        cbJapanese = findViewById(R.id.cbJapanese);
        cbJewish = findViewById(R.id.cbJewish);
        cbKorean = findViewById(R.id.cbKorean);
        cbLatinamerican = findViewById(R.id.cbLatinamerican);
        cbMediterranean = findViewById(R.id.cbMediterranean);
        cbMexican = findViewById(R.id.cbMexican);
        cbMiddleeastern = findViewById(R.id.cbMiddleeastern);
        cbNordic = findViewById(R.id.cbNordic);
        cbSouthern = findViewById(R.id.cbSouthern);
        cbSpanish = findViewById(R.id.cbSpanish);
        cbThai = findViewById(R.id.cbThai);
        cbVietnamese = findViewById(R.id.cbVietnamese);

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

    private String getNietolerancje() {
        List<String> list = new ArrayList<>();

        if (cbDairy.isChecked()) list.add("dairy");
        if (cbEgg.isChecked()) list.add("egg");
        if (cbGluten.isChecked()) list.add("gluten");
        if (cbGrain.isChecked()) list.add("grain");
        if (cbPeanut.isChecked()) list.add("peanut");
        if (cbSeafood.isChecked()) list.add("seafood");
        if (cbSesame.isChecked()) list.add("sesame");
        if (cbShellfish.isChecked()) list.add("shellfish");
        if (cbSoy.isChecked()) list.add("soy");
        if (cbSulfite.isChecked()) list.add("sulfite");
        if (cbTreenut.isChecked()) list.add("tree+nut");
        if (cbWheat.isChecked()) list.add("wheat");

        return String.join(",", list);
    }

    private String getKuchnie() {
        List<String> list = new ArrayList<>();

        if(cbAfrican.isChecked()) list.add("african");
        if(cbAmerican.isChecked()) list.add("american");
        if(cbBritish.isChecked()) list.add("british");
        if(cbCajun.isChecked()) list.add("cajun");
        if(cbCaribbean.isChecked()) list.add("caribbean");
        if(cbChinese.isChecked()) list.add("chinese");
        if(cbEasterneuropean.isChecked()) list.add("eastern+european");
        if(cbEuropean.isChecked()) list.add("european");
        if(cbFrench.isChecked()) list.add("french");
        if(cbGerman.isChecked()) list.add("german");
        if(cbGreek.isChecked()) list.add("greek");
        if(cbIndian.isChecked()) list.add("indian");
        if(cbIrish.isChecked()) list.add("irish");
        if(cbItalian.isChecked()) list.add("italian");
        if(cbJapanese.isChecked()) list.add("japanese");
        if(cbJewish.isChecked()) list.add("jewish");
        if(cbKorean.isChecked()) list.add("korean");
        if(cbLatinamerican.isChecked()) list.add("latin+american");
        if(cbMediterranean.isChecked()) list.add("mediterranean");
        if(cbMexican.isChecked()) list.add("mexican");
        if(cbMiddleeastern.isChecked()) list.add("middle+eastern");
        if(cbNordic.isChecked()) list.add("nordic");
        if(cbSouthern.isChecked()) list.add("southern");
        if(cbSpanish.isChecked()) list.add("spanish");
        if(cbThai.isChecked()) list.add("thai");
        if(cbVietnamese.isChecked()) list.add("vietnamese");

        return String.join(",", list);
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

        cbDairy.setText(R.string.dairy);
        cbEgg.setText(R.string.egg);
        cbGluten.setText(R.string.gluten);
        cbGrain.setText(R.string.grain);
        cbPeanut.setText(R.string.peanut);
        cbSeafood.setText(R.string.seafood);
        cbSesame.setText(R.string.sesame);
        cbShellfish.setText(R.string.shellfish);
        cbSoy.setText(R.string.soy);
        cbSulfite.setText(R.string.sulfite);
        cbTreenut.setText(R.string.treenut);
        cbWheat.setText(R.string.wheat);

        cbAfrican.setText(R.string.african);
        cbAmerican.setText(R.string.american);
        cbBritish.setText(R.string.british);
        cbCajun.setText(R.string.cajun);
        cbCaribbean.setText(R.string.caribbean);
        cbChinese.setText(R.string.chinese);
        cbEasterneuropean.setText(R.string.easterneuropean);
        cbEuropean.setText(R.string.european);
        cbFrench.setText(R.string.french);
        cbGerman.setText(R.string.german);
        cbGreek.setText(R.string.greek);
        cbIndian.setText(R.string.indian);
        cbIrish.setText(R.string.irish);
        cbItalian.setText(R.string.italian);
        cbJapanese.setText(R.string.japanese);
        cbJewish.setText(R.string.jewish);
        cbKorean.setText(R.string.korean);
        cbLatinamerican.setText(R.string.latinamerican);
        cbMediterranean.setText(R.string.mediterranean);
        cbMexican.setText(R.string.mexican);
        cbMiddleeastern.setText(R.string.middleeastern);
        cbNordic.setText(R.string.nordic);
        cbSouthern.setText(R.string.southern);
        cbSpanish.setText(R.string.spanish);
        cbThai.setText(R.string.thai);
        cbVietnamese.setText(R.string.vietnamese);

        bSzukaj.setText(R.string.szukaj);


        super.updateJezyka();
    }
}