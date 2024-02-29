package com.itstep.first_spring.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "performers")
@Schema(description = " Performer Model ")
public class PerformerModel extends BaseEntity
{
    @Column(unique = true) // Уникальность почты
    @Schema(description = "Performer name", example = "Bon Jovi")
    private String name;

    @OneToOne(mappedBy = "performer")
    @Schema(description = "If performer is user")
    private UserModel user;

    @ManyToMany
    @JoinTable(
            name = "performer_songs",
            joinColumns = { @JoinColumn(name = "performer_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") }
    )
    @Schema(description = "Performer songs")
    private Set<SongModel> songs;
}
