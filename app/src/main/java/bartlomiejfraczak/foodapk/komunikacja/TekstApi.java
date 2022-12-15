package bartlomiejfraczak.foodapk.komunikacja;

import java.util.List;

import bartlomiejfraczak.foodapk.encje.Tekst;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TekstApi {
    @GET("/tekst/getall")
    Call<List<Tekst>> getAllTekst();

//    @POST("/poststring")
//    Call<Tekst> post(@Body Tekst tekst);
}
