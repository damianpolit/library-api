package com.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
class ExceptionResponse {
    
    private Long errorCode;
    private String errorMessage;
    private String uri;
    
}
