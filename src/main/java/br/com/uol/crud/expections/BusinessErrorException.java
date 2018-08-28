package br.com.uol.crud.expections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessErrorException extends RuntimeException {

    public BusinessErrorException(String message){
        super(message);
    }
}
