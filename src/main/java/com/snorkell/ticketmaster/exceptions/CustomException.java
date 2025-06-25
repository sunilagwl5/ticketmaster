package com.snorkell.ticketmaster.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}
