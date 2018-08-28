package br.com.uol.crud.model.client;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

public class DadosIpVigilante {

    @Getter
    @Setter
    @SerializedName("latitude")
    private String latitude;

    @Getter
    @Setter
    @SerializedName("longitude")
    private String longitude;

}
