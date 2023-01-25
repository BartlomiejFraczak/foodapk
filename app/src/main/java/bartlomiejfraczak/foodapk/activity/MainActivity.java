package bartlomiejfraczak.foodapk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.komunikacja.LoginApi;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;

public class MainActivity extends CustomAppCompatActivity {

    private static MainActivity instancja;

//    private TextView textView;
    private Button bSzukajMenu;
    private Button bZalogujMenu;
    private Button bKontoMenu;
//    private PrzepisApi przepisApi;
//    private LoginApi loginApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancja = this;

        init();
    }

    public static MainActivity getInstancja() {
        return instancja;
    }

    private void init() {
        bSzukajMenu = findViewById(R.id.bSzukajMenu);
        bZalogujMenu = findViewById(R.id.bZalogujMenu);
        bKontoMenu = findViewById(R.id.bKontoMenu);

        bSzukajMenu.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, SzukajActivity.class);
            startActivity(myIntent);
        });
        bZalogujMenu.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, LogowanieActivity.class);
            startActivity(myIntent);
        });
        bKontoMenu.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, KontoActivity.class);
            startActivity(myIntent);
        });
        zmianaUzytkownika();
    }

    public void updateJezyka() {
        bSzukajMenu.setText(R.string.szukaj);
        bZalogujMenu.setText(R.string.zaloguj);
        bKontoMenu.setText(R.string.konto);
        super.updateJezyka();

    }

    public void zmianaUzytkownika() {
        if (GlobalneInfo.getInstancja().getCzyUzytkownikZalogowany()) {
            bZalogujMenu.setVisibility(View.GONE);
            bKontoMenu.setVisibility(View.VISIBLE);
        } else {
            bZalogujMenu.setVisibility(View.VISIBLE);
            bKontoMenu.setVisibility(View.GONE);
        }
    }
}