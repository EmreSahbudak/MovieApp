package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class UserExceptionHandler extends RuntimeException {

    final EErrorType EErrorType;

    public UserExceptionHandler(EErrorType EErrorType) {
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }
}
