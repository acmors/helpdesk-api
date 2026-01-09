package com.helpdesk_api.web.dto.comment;

import java.time.Instant;

public record CommentResponse(String content, Instant createdAt, String userAccount) {
}
