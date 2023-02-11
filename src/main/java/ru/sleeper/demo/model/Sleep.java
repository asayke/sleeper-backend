package ru.sleeper.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sleeps")
@Data
public class Sleep extends BaseEntity {
    @Column(name = "noise", nullable = false)
    private Integer noise;

    @Column(name = "quality", nullable = false)
    private Integer quality;

    @Column(name = "went_sleep", nullable = false)
    private Date went_sleep;

    @Column(name = "waked_up_at")
    private Date waked_up_at;

    @Column(name = "fell_asleep_during")
    private Integer fell_asleep_during;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User user;
}