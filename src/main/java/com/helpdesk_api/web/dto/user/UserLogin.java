package com.helpdesk_api.web.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLogin {

    private String email;

    private String password;


}
