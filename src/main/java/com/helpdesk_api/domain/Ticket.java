package com.helpdesk_api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helpdesk_api.domain.enums.Priority;
import com.helpdesk_api.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "tb_ticket")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    private Set<Comment> comments;




}
