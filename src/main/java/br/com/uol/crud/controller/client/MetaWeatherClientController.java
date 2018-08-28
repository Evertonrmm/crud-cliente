package br.com.uol.crud.controller.client;

import br.com.uol.crud.api.MetaWeatherAPI;
import br.com.uol.crud.expections.BusinessErrorException;
import br.com.uol.crud.model.client.MetaWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MetaWeatherClientController extends BaseClientController<MetaWeather> {

    private static final Logger LOGGER  = LoggerFactory.getLogger(MetaWeatherClientController.class);

    private static final String URL_API = "https://www.metaweather.com";

    private MetaWeatherAPI metaWeatherAPI;

    public MetaWeatherClientController(){
        super(URL_API);
        metaWeatherAPI = getRetrofit().create(MetaWeatherAPI.class);
    }

    public List<MetaWeather> obterLocalizacao(String latitude, String longitude){
        LOGGER.info("Buscando localização por latitude e longitude.");
        return getResponseList(metaWeatherAPI.getLocalidade(latitude.concat(",").concat(longitude)));
    }

    public MetaWeather obterClima(String idLocal){
        LOGGER.info("Buscando clima por localização.");
        return getResponse(metaWeatherAPI.getClima(idLocal));
    }
}
