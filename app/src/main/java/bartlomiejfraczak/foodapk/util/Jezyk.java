package bartlomiejfraczak.foodapk.util;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import bartlomiejfraczak.foodapk.activity.InfoActivity;
import bartlomiejfraczak.foodapk.activity.KontoActivity;
import bartlomiejfraczak.foodapk.activity.LogowanieActivity;
import bartlomiejfraczak.foodapk.activity.MainActivity;
import bartlomiejfraczak.foodapk.activity.NotatkiActivity;
import bartlomiejfraczak.foodapk.activity.PrzepisActivity;
import bartlomiejfraczak.foodapk.activity.PrzepisyActivity;
import bartlomiejfraczak.foodapk.activity.RejestracjaActivity;
import bartlomiejfraczak.foodapk.activity.SzukajActivity;
import bartlomiejfraczak.foodapk.komunikacja.PobierzObraz;

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
        if (MainActivity.getInstancja() != null)
            MainActivity.getInstancja().updateJezyka();
        if (LogowanieActivity.getInstancja() != null)
            LogowanieActivity.getInstancja().updateJezyka();
        if (SzukajActivity.getInstancja() != null)
            SzukajActivity.getInstancja().updateJezyka();
        if (PrzepisyActivity.getInstancja() != null)
            PrzepisyActivity.getInstancja().updateJezyka();
        if (PrzepisActivity.getInstancja() != null)
            PrzepisActivity.getInstancja().updateJezyka();
        if (KontoActivity.getInstancja() != null)
            KontoActivity.getInstancja().updateJezyka();
        if (InfoActivity.getInstancja() != null)
            InfoActivity.getInstancja().updateJezyka();
        if (RejestracjaActivity.getInstancja() != null)
            RejestracjaActivity.getInstancja().updateJezyka();
        if (NotatkiActivity.getInstancja() != null)
            NotatkiActivity.getInstancja().updateJezyka();
    }

    public static void przetlumacz(List<TextView> listaTv, String jezykZ, String jezykNa, List<String> teksty) {
        String urlStr = null;

        String tekstyStr = String.join(". 080856f98d1eb14b814733d0c19b1af3. ", teksty);
        System.out.println(tekstyStr);
        try {
            urlStr = "https://script.google.com/macros/s/AKfycbxxDTVKhYqlgraKE4Uf2jLMsmYpJN1x9RCVfP6ztYvNt4kCexOSzuhuLJgeEENoR4jX-Q/exec" +
                    "?q=" + URLEncoder.encode(tekstyStr, "UTF-8") +
                    "&target=" + jezykNa +
                    "&source=" + jezykZ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new Tlumacz(listaTv).execute(urlStr);
//        (new Thread() {
//            public void run() {
//                try {
//                    String urlStr = null;
////            String urlStr = "https://your.google.script.url" +
//                    urlStr = "https://script.google.com/macros/s/AKfycbxxDTVKhYqlgraKE4Uf2jLMsmYpJN1x9RCVfP6ztYvNt4kCexOSzuhuLJgeEENoR4jX-Q/exec" +
//                            "?q=" + URLEncoder.encode(tekst, "UTF-8") +
//                            "&target=" + jezykNa +
//                            "&source=" + jezykZ;
//                    URL url = new URL(urlStr);
//                    StringBuilder response = new StringBuilder();
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null) {
//                        response.append(inputLine);
//                    }
//                    in.close();
//                    return response.toString();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

}
