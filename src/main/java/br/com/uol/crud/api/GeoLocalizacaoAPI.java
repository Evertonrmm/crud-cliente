package br.com.uol.crud.api;

import br.com.uol.crud.model.client.IpVigilante;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GeoLocalizacaoAPI {

    @GET("/json/{ip}/full")
    public Call<IpVigilante> getLocalizacao(@Path("ip") String ip);
}
