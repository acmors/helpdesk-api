package com.helpdesk_api.web.dto.comment;

import com.helpdesk_api.domain.Comment;

public final class CommentToDTOMapper {

    private CommentToDTOMapper(){}

    public static Comment toEntity(CommentRequest dto){
        Comment comment = new Comment();
        comment.setContent(dto.content());
        return comment;
    }

    public static CommentResponse toResponse(Comment comment){
        String userComment = comment.getUserAccount() != null ? comment.getUserAccount().getName() : null;

        return new CommentResponse(
                comment.getContent(),
                comment.getCreatedAt(),
                userComment
        );
    }
}
