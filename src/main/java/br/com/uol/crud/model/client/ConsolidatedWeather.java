package br.com.uol.crud.model.client;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

public class ConsolidatedWeather {

    @Getter
    @Setter
    @SerializedName("min_temp")
    private String minTemp;

    @Getter
    @Setter
    @SerializedName("max_temp")
    private String maxTemp;

}
