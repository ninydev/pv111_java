package com.itstep.first_spring.models;

import com.itstep.first_spring.models.auth.UserModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "songs")
@Schema(description = " Songs Model ")
public class SongModel extends BaseEntity
{
    @Schema(description = "Song name", example = "Show mast go on")
    private String name;

    @ManyToOne
    @JoinColumn(name="uploader_id", nullable=false)
    @Schema(description = "Uploader")
    private UserModel uploader;

    @ManyToMany(mappedBy = "songs")
    @Schema(description = "Performers")
    private Set<PerformerModel> performers;
}
