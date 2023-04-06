package com.bilgeadam.entity;

import com.bilgeadam.utility.enums.EStatus;
import com.bilgeadam.utility.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String surname;
    @Column(length = 50)
    @Email
    private String email;
    @Column(length = 15)
    private String phone;

    @Column(length = 32)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
    message = "Şifre en az bir büyük, bir küçük, harff,rakam ve özel karakterlerden oluşmalı")
    private String password;

    @Column(length = 32)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Şifre en az bir büyük, bir küçük, harff,rakam ve özel karakterlerden oluşmalı")
    private String repassword;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.PENDING;

    @Enumerated(EnumType.STRING) //string tipte olacagını soyledik enumun
    @Builder.Default  // default değeri springe soyler.
    private EUserType userType=EUserType.USER;

    @ElementCollection
    private List<Integer> genreId;

    @ElementCollection
    private List<Integer> movieId;

    @ElementCollection
    private List<Integer> commentsId;
}
