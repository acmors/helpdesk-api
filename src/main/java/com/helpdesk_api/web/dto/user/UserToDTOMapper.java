package com.helpdesk_api.web.dto.user;

import com.helpdesk_api.domain.UserAccount;

public final class UserToDTOMapper {

    private UserToDTOMapper(){}

    public static UserAccount toEntity(UserCreate dto){
        UserAccount user = new UserAccount();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }

    public static UserResponse toResponse(UserAccount user){
        return new UserResponse(
                user.getName(),
                user.getEmail()
        );
    }
}
