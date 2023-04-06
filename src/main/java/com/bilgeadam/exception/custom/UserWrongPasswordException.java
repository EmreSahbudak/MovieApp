package com.bilgeadam.exception.custom;

import com.bilgeadam.exception.EErrorType;
import lombok.Getter;

@Getter
public class UserWrongPasswordException extends RuntimeException{

    final EErrorType EErrorType;

    public UserWrongPasswordException(EErrorType EErrorType) {
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }
}
