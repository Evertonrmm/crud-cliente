package br.com.uol.crud.model;

import br.com.uol.crud.common.MessageConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CLIENTE")
public class Cliente extends BaseModel {

    @Id
    @Getter
    @Setter
    @GeneratedValue
    @Column(name = "ID_CLIENTE")
    @JsonProperty("identificador")
    private Long id;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "NOME")
    @JsonProperty("name")
    private String nome;

    @Getter
    @Setter
    @NotNull
    @Column(name = "IDADE")
    @Min(value = 1, message = MessageConstants.IDADE_MINIMA)
    @JsonProperty("age")
    private Integer idade;

    @Getter
    @Setter
    @Column(name = "TEMP_MIN")
    @JsonProperty("tempMin")
    private String temperaturaMinima;

    @Getter
    @Setter
    @Column(name = "TEMP_MAX")
    @JsonProperty("tempMax")
    private String temperaturaMaxima;
}
