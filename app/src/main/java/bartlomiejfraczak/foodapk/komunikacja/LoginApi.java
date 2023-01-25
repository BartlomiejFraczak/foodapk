package bartlomiejfraczak.foodapk.komunikacja;

import bartlomiejfraczak.foodapk.encje.Uzytkownik;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
