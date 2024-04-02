package org.sound_cloud.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;



@Getter
public class StatusException extends Exception{

    private final HttpStatus status;
    public StatusException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
