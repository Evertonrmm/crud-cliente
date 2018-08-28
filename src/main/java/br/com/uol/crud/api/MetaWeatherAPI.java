package br.com.uol.crud.api;

import br.com.uol.crud.model.client.MetaWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface MetaWeatherAPI {

    @GET("api/location/search/")
    Call<List<MetaWeather>> getLocalidade(@Query(value = "lattlong") String lattlong);


    @GET("api/location/{idLocal}/")
    Call<MetaWeather> getClima(@Path("idLocal") String idLocal);

}
