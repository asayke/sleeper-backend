package ru.sleeper.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sleeps")
@Data
public class Sleep extends BaseEntity {
    @Column(name = "noise")
    private Integer noise;

    @Column(name = "quality")
    private Integer quality;

    @Column(name = "went_sleep")
    private Date went_sleep;

    @Column(name = "waked_up_at")
    private Date waked_up_at;

    @Column(name = "fell_asleep_during")
    private Integer fell_asleep_during;
}