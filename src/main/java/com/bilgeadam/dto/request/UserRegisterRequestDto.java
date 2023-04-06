package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserRegisterRequestDto {
    @NotBlank(message = "Kullanıcı adı boş geçilemez")
    @Size(min =3, max =45, message = "kullanıcı adı min 3 max 45 karakter olmalı")
    String name;
    @NotBlank
    String surname;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String email;
    @NotBlank(message = "şifre boş bırakılamaz")
    String password;
    @NotBlank(message = "şifre boş bırakılamaz")
    String repassword;
}
