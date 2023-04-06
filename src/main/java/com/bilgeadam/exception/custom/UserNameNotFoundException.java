package com.bilgeadam.exception.custom;

import com.bilgeadam.exception.EErrorType;
import lombok.Getter;

@Getter
public class UserNameNotFoundException extends RuntimeException{
    final EErrorType EErrorType;

    public UserNameNotFoundException(EErrorType EErrorType) {
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

}
