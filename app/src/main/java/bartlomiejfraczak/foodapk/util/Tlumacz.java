package bartlomiejfraczak.foodapk.util;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tlumacz extends AsyncTask<String, Void, String> {
    List<TextView> listaTv;

    public Tlumacz(List<TextView> listaTv) {
        this.listaTv = listaTv;
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
        System.out.println(s);
        List<String> listaStr = new ArrayList<>(Arrays.asList(s.split(". 080856f98d1eb14b814733d0c19b1af3. ")));
        if (listaStr.size() != listaTv.size()) {
            System.out.println("błąd w bartlomiejfraczak.foodapk.util.Tlumacz.onPostExecute() (ilosc sie nie zgadza " + listaStr.size() + "!=" + listaTv.size() + ")");
            return;
        }
        for (int i = 0; i < listaStr.size(); i++) {
            listaTv.get(i).setText(listaStr.get(i));
        }
    }
}
