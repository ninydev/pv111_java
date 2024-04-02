package org.sound_cloud.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sound_cloud.api.models.auth.UserModel;

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
