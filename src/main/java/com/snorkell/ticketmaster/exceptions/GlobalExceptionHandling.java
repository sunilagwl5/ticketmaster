package com.snorkell.ticketmaster.exceptions;

import com.snorkell.ticketmaster.dto.SErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling{

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<SErrorResponse> handleException(CustomException customException) {
        SErrorResponse sErrorResponse = SErrorResponse
                .builder()
                .code(customException.getStatus().toString())
                .message(customException.getMessage())
                .build();
        return new ResponseEntity<SErrorResponse>(sErrorResponse, customException.getStatus());
    }


}
