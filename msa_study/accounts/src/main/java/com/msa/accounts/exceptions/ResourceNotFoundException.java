package com.msa.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
     *
     * @param resourceName
     * @param filedName
     * @param filedValue
     */
    public ResourceNotFoundException(String resourceName, String filedName, String filedValue ) {
        super(String.format("%s 적합한 인풋 데이터를 찾을수 없습니다. %s : '%s'", resourceName, filedName, filedValue));
    }
}
