package bartlomiejfraczak.foodapk.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bartlomiejfraczak.foodapk.R;
import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapk.encje.Tekst;
import bartlomiejfraczak.foodapk.komunikacja.LoginApi;
import bartlomiejfraczak.foodapk.komunikacja.PrzepisApi;
import bartlomiejfraczak.foodapk.komunikacja.RetrofitService;
import bartlomiejfraczak.foodapk.komunikacja.TekstApi;
import bartlomiejfraczak.foodapk.util.GlobalneInfo;
import bartlomiejfraczak.foodapk.util.Jezyk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends CustomAppCompatActivity {

    private static MainActivity instancja;

    private TextView textView;
    private Button bSzukajMenu;
    private Button bZalogujMenu;
    private Button bKontoMenu;
    private PrzepisApi przepisApi;
    private LoginApi loginApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancja = this;

//        initStary();
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
//    private void initStary() {
//        bSzukajMenu = findViewById(R.id.bSzukajMenu);
//
//        RetrofitService retrofitService = RetrofitService.getInstancja(this);
//        TekstApi tekstApi = retrofitService.getRetrofit().create(TekstApi.class);
//        przepisApi = retrofitService.getRetrofit().create(PrzepisApi.class);
//        loginApi = retrofitService.getRetrofit().create(LoginApi.class);
////        System.out.println("przepis api: " + przepisApi);
//
//
//        bSzukajMenu.setOnClickListener(view -> {
//            przepisApi.getUlubioneUzytkownika(2)
//                    .enqueue(new Callback<List<PrzepisSzczegolowy>>() {
//                        @Override
//                        public void onResponse(Call<List<PrzepisSzczegolowy>> call, Response<List<PrzepisSzczegolowy>> response) {
//                            List<PrzepisSzczegolowy> przepisy = response.body();
//                            System.out.println("ilosc przepisów: " + przepisy.size());
//                            System.out.println("pierwszy przepis: " + przepisy.get(0).toString());
//                            System.out.println("drugi przepis: " + przepisy.get(1).toString());
//                            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<PrzepisSzczegolowy>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "nie ok", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        });
//        bSzukajMenu.setOnClickListener(view -> {
//            przepisApi.getPrzepisy("pancake", null, null, null, null)
//                    .enqueue(new Callback<List<Przepis>>() {
//                        @Override
//                        public void onResponse(Call<List<Przepis>> call, Response<List<Przepis>> response) {
//                            List<Przepis> przepisy = response.body();
//                            System.out.println("ilosc przepisów: " + przepisy.size());
//                            System.out.println("pierwszy przepis: " + przepisy.get(0).toString());
//                            wypelnijPrzepisami(przepisy);
//                            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Przepis>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "nie ok", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//            Uzytkownik u = new Uzytkownik("xd", "123");
//            System.out.println("uzytkownik: " + u.toString());
//            loginApi.zaloguj(u)
//                    .enqueue(new Callback<Boolean>() {
//
//                        @Override
//                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                            Boolean udalosie = response.body();
//                            System.out.println("udalo sie: " + udalosie);
//                        }
//
//                        @Override
//                        public void onFailure(Call<Boolean> call, Throwable t) {
//                            System.out.println("coś poszło nie tak :(");
//                        }
//                    });


//        button.setOnClickListener(view -> {
//            przepisApi.siema()
//                    .enqueue(new Callback<String>() {
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            String siema = response.body();
//                            System.out.println("siema");
//                            textView.setText(siema);
//                            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//                            textView.setText("nie ok");
//                            Toast.makeText(MainActivity.this, "nie ok", Toast.LENGTH_SHORT).show();
//                        }
//                    });


//            tekstApi.getAllTekst()
//                    .enqueue(new Callback<List<Tekst>>() {
//                        @Override
//                        public void onResponse(Call<List<Tekst>> call, Response<List<Tekst>> response) {
//                            wypelnijTekstem(response.body());
//                            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Tekst>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "nie ok", Toast.LENGTH_SHORT).show();
//                        }
//                    });


//            testApi.post("XD")
//                    .enqueue(new Callback<String>() {
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            Toast.makeText(MainActivity.this, "Wysyłanie udane!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Błąd w wysyłaniu!", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error", t);
//                        }
//                    });
//        });
//    }

//    private void wypelnijPrzepisami(List<Przepis> przepisy) {
//        StringBuilder sb = new StringBuilder();
//        for (Przepis p : przepisy) {
//            sb.append(p.getId());
//            sb.append("\n");
//            sb.append(p.getTitle());
//            sb.append("\n");
//            sb.append(p.getImage());
//            sb.append("\n");
//            sb.append(p.getImageType());
//            sb.append("\n\n");
//        }
//        textView.setText(sb);
//    }
//
//    private void wypelnijTekstem(List<Tekst> lista) {
//        StringBuilder sb = new StringBuilder();
//        for (Tekst t : lista) {
//            sb.append(t.getNazwa());
//            sb.append("\n");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        textView.setText(sb);
//
//    }

}