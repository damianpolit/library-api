package com.library.account.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String login;
    
    @NotBlank
    private String password;
    
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
