package bartlomiejfraczak.foodapk.komunikacja;

import java.util.List;

import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PrzepisApi {
    @GET("/przepis/szukaj")
    Call<List<Przepis>> getPrzepisy(
            @Query("query") String query,
            @Query("cuisine") String cuisine,
            @Query("diet") String diet,
            @Query("intolerances") String intolerances,
            @Query("includeIngredients") String includeIngredients
    );

    @GET("/przepis/szczegolowy")
    Call<PrzepisSzczegolowy> getPrzepisSzczegolowy(
            @Query("przepisId") Integer przepisId,
            @Query("uzytkownikId") Integer uzytkownikId
    );

    @GET("/przepis/ulubione")
    Call<List<PrzepisSzczegolowy>> getUlubioneUzytkownika(
            @Query("uzytkownikId") Integer uzytkownikId
    );

    @POST("/przepis/edytujinfo")
    Call<ResponseBody> edytujPrzepisInfo(
            @Query("uzytkownikId") Integer uzytkownikId,
            @Query("przepisId") Integer przepisId,
            @Query("ulubiony") boolean ulubiony,
            @Query("notatka") String notatka
    );


//    @POST("/przepis/ulubione/dodaj")
//    Call<ResponseBody> dodajDoUlubionych(
//            @Query("uzytkownikId") String uzytkownikId,
//            @Query("przepisId") String przepisId
//    );

//    @POST("/przepis/ulubione/czy")
//    Call<Boolean> czyUzytkownikLubi(
//            @Query("uzytkownikId") String uzytkownikId,
//            @Query("przepisId") String przepisId
//    );

//    @POST("/przepis/ulubione/usun")
//    Call<ResponseBody> usunZUlubionych(
//            @Query("uzytkownikId") String uzytkownikId,
//            @Query("przepisId") String przepisId
//    );
}
