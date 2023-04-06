package com.bilgeadam.exception.custom;

import com.bilgeadam.exception.EErrorType;
import lombok.Getter;

@Getter
public class UserEmailExistsException extends RuntimeException {

    final EErrorType EErrorType;

    public UserEmailExistsException(EErrorType EErrorType) {
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }
}
