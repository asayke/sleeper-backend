package ru.sleeper.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tokens")
@NoArgsConstructor
public class ConfirmationToken extends BaseEntity {
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private Date expiresAt;

    @Column(name = "confirmed_at")
    private Date confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User user;

    public ConfirmationToken(String token,
                             Date expiresAt,
                             User user) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}