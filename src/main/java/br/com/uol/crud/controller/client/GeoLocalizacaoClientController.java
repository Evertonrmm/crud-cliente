package br.com.uol.crud.controller.client;

import br.com.uol.crud.api.GeoLocalizacaoAPI;
import br.com.uol.crud.model.client.IpVigilante;

public class GeoLocalizacaoClientController extends BaseClientController<IpVigilante> {

    private static final String URL_API = "https://ipvigilante.com";

    private GeoLocalizacaoAPI geoLocalizacaoAPI;

    public GeoLocalizacaoClientController(){
        super(URL_API);
       geoLocalizacaoAPI = getRetrofit().create(GeoLocalizacaoAPI.class);
    }

    public IpVigilante obterLocalizacao(String ip){
        return getResponse(geoLocalizacaoAPI.getLocalizacao(ip));
    }
}
