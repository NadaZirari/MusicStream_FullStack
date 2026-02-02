package musicstream.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackResponseDTO {
    private String id;
    private String title;
    private String artist;
    private String category;
    private String description;
    private String duration;
    private LocalDateTime addedDate;
    private String audioUrl;
}
