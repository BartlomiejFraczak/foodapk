package bartlomiejfraczak.foodapk.komunikacja;

import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tlumacz extends AsyncTask<String, Void, String> {
    List<TextView> listaTv;
    static String separator = "080856f98d1eb14b814733d0c19b1af3";
    static String apiKey = "AKfycbxxDTVKhYqlgraKE4Uf2jLMsmYpJN1x9RCVfP6ztYvNt4kCexOSzuhuLJgeEENoR4jX-Q";


    public Tlumacz(List<TextView> listaTv, String jezykZ, String jezykNa, List<String> teksty) {
        this.listaTv = listaTv;
        String urlStr = null;
        String tekstyStr = String.join(". " + separator + ". ", teksty);
        try {
            urlStr = "https://script.google.com/macros/s/" + apiKey + "/exec" +
                    "?q=" + URLEncoder.encode(tekstyStr, "UTF-8") +
                    "&source=" + jezykZ +
                    "&target=" + jezykNa;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.execute(urlStr);
    }

    @Override
    protected String doInBackground(String... urlStr) {
        try {
            URL url = new URL(urlStr[0]);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        List<String> listaStr = new ArrayList<>(Arrays.asList(s.split(". " + separator + ".")));
        if (listaStr.size() != listaTv.size()) {
            System.out.println("błąd w komunikacja.Tlumacz.onPostExecute() (ilosc sie nie zgadza " + listaStr.size() + "!=" + listaTv.size() + ")");
            return;
        }
        for (int i = 0; i < listaStr.size(); i++) {
            listaTv.get(i).setText(Html.fromHtml(listaStr.get(i), Html.FROM_HTML_MODE_COMPACT));
        }
    }
}
