package com.helpdesk_api.web.dto.group;

import com.helpdesk_api.domain.Group;

public class GroupToDTOMapper {

    private static Group toEntity(GroupRequest dto){
        Group group = new Group();
        group.setName(dto.name());

        return group;
    }

    public static GroupResponse toResponse(Group group){
        return new GroupResponse(
                group.getId(),
                group.getName()
        );
    }
}
