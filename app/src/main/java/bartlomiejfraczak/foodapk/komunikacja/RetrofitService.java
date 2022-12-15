package bartlomiejfraczak.foodapk.komunikacja;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static RetrofitService instancja;
    private Retrofit retrofit;
    private Context context;

    private RetrofitService(Context context) {
        this.context = context;
        init();
    }

    private RetrofitService() {
        init();
    }

    public static RetrofitService getInstancja(Context context) {
        if (instancja == null) {
            instancja = new RetrofitService(context);
        }
        return instancja;
    }

    public static RetrofitService getInstancja() {
        if (instancja == null) {
            try {
                throw new Exception("Inicjalizacja RetrofitService nie zawiera contextu.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instancja;
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(getApiUrl())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private String getApiUrl() {
        String apiUrl = "";
        try {
            InputStream is = null;
            is = context.getAssets().open("config.properties");
            Properties props = new Properties();
            props.load(is);

            apiUrl = props.getProperty("api_url", "");

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiUrl;
    }
}
