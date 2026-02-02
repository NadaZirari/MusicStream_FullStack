package musicstream.backend.dto;

import musicstream.backend.model.Track;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper {

    public TrackResponseDTO toResponseDTO(Track track) {
        if (track == null) return null;
        
        return TrackResponseDTO.builder()
                .id(track.getId())
                .title(track.getTitle())
                .artist(track.getArtist())
                .category(track.getCategory())
                .description(track.getDescription())
                .duration(track.getDuration())
                .addedDate(track.getAddedDate())
                .audioUrl("/api/tracks/" + track.getId() + "/stream")
                .build();
    }

    public Track toEntity(TrackRequestDTO dto) {
        if (dto == null) return null;

        return Track.builder()
                .title(dto.getTitle())
                .artist(dto.getArtist())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .build();
    }
}
