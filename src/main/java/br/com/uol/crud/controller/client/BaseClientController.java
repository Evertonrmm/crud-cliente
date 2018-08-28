package br.com.uol.crud.controller.client;

import br.com.uol.crud.expections.BusinessErrorException;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class BaseClientController<T> {

    private Retrofit retrofit;

    public BaseClientController(String url){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public T getResponse(Call<T>  call){
        Response<T> response = null;

        try {
            response = call.execute();

            if(response.isSuccessful()){
                return response.body();
            }

            throw new BusinessErrorException("Retorno do serviço sem sucesso.");

        } catch (IOException e) {
            throw new BusinessErrorException("Não foi possível executar serviço.");
        }
    }

    public List<T> getResponseList(Call<List<T>>  call){
        Response<List<T>> response = null;

        try {
            response = call.execute();

            if(response.isSuccessful()){
                return response.body();
            }

            throw new BusinessErrorException("Retorno do serviço sem sucesso.");

        } catch (IOException e) {
            throw new BusinessErrorException("Não foi possível executar serviço.");
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
