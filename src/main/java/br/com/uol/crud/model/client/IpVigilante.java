package br.com.uol.crud.model.client;

import lombok.Getter;
import lombok.Setter;

public class IpVigilante {

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private DadosIpVigilante data;

}
