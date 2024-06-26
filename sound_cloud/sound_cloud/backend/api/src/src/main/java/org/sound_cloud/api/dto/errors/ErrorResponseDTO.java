package org.sound_cloud.api.dto.errors;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private final String type;
    private final String message;

    public ErrorResponseDTO(Exception ex) {
        this.type = ex.getClass().toString();
        this.message = ex.getMessage();
    }

}
