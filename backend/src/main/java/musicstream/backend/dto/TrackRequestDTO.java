package musicstream.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackRequestDTO {
    private String title;
    private String artist;
    private String category;
    private String description;
    private String duration;
}
