package bartlomiejfraczak.foodapk.komunikacja;

import java.util.List;

import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {
    @POST("/zarejestruj")
    Call<Boolean> zarejestruj(
            @Body Uzytkownik uzytkownik
    );

    @POST("/zaloguj")
    Call<Integer> zaloguj(
            @Body Uzytkownik uzytkownik
    );
}
