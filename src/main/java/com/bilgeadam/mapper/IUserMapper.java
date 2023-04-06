package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.UserRegisterRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.dto.response.UserLoginResponseDto;
import com.bilgeadam.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

    User toUserRegisterDto(final UserRegisterRequestDto dto);

    UserLoginResponseDto toUserLoginDto(final User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateRequestDto dto, @MappingTarget User user);
}


