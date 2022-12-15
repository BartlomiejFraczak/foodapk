package bartlomiejfraczak.foodapk.util;

import android.icu.text.IDNA;

import java.util.ArrayList;
import java.util.List;

import bartlomiejfraczak.foodapk.activity.CustomAppCompatActivity;
import bartlomiejfraczak.foodapk.activity.InfoActivity;
import bartlomiejfraczak.foodapk.activity.KontoActivity;
import bartlomiejfraczak.foodapk.activity.LogowanieActivity;
import bartlomiejfraczak.foodapk.activity.MainActivity;
import bartlomiejfraczak.foodapk.activity.PrzepisActivity;
import bartlomiejfraczak.foodapk.activity.PrzepisyActivity;
import bartlomiejfraczak.foodapk.activity.RejestracjaActivity;
import bartlomiejfraczak.foodapk.activity.SzukajActivity;
import bartlomiejfraczak.foodapk.encje.Przepis;

public class Jezyk {
    private static Jezyk instancja;

    public static Jezyk getInstancja() {
        if (instancja == null) {
            instancja = new Jezyk();
        }
        return instancja;
    }

    public Jezyk() {

    }

    public void updateJezyka() {
        if (LogowanieActivity.getInstancja() != null)
            LogowanieActivity.getInstancja().updateJezyka();
        if (MainActivity.getInstancja() != null)
            MainActivity.getInstancja().updateJezyka();
        if (PrzepisyActivity.getInstancja() != null)
            PrzepisyActivity.getInstancja().updateJezyka();
        if (PrzepisActivity.getInstancja() != null)
            PrzepisActivity.getInstancja().updateJezyka();
        if (SzukajActivity.getInstancja() != null)
            SzukajActivity.getInstancja().updateJezyka();
        if (KontoActivity.getInstancja() != null)
            KontoActivity.getInstancja().updateJezyka();
        if (InfoActivity.getInstancja() != null)
            InfoActivity.getInstancja().updateJezyka();
        if (RejestracjaActivity.getInstancja() != null)
            RejestracjaActivity.getInstancja().updateJezyka();
    }
}
