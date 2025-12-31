package com.helpdesk_api.web.dto.mapper;

import com.helpdesk_api.domain.UserAccount;
import com.helpdesk_api.web.dto.user.UserCreate;
import com.helpdesk_api.web.dto.user.UserResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserAccount toUser(UserCreate dto){
        return new ModelMapper().map(dto, UserAccount.class);
    }

    public static UserResponse toDTO(UserAccount user){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserResponse.class);
    }

    public static List<UserResponse> toListDTO(List<UserAccount> users){
        return users.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }
}
