package br.com.uol.crud.model.client;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MetaWeather {

    @Getter
    @Setter
    @SerializedName("latt_long")
    private String latt_long;

    @Getter
    @Setter
    @SerializedName("woeid")
    private String woeid;

    @Getter
    @Setter
    @SerializedName("distance")
    private String distance;

    @Getter
    @Setter
    @SerializedName("consolidated_weather")
    private List<ConsolidatedWeather> consolidatedWeather;
}
