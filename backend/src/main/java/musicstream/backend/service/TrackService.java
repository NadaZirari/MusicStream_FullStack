package musicstream.backend.service;

import musicstream.backend.dto.TrackMapper;
import musicstream.backend.dto.TrackRequestDTO;
import musicstream.backend.dto.TrackResponseDTO;
import musicstream.backend.model.Track;
import musicstream.backend.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private TrackMapper trackMapper;

    public TrackResponseDTO saveTrack(MultipartFile file, String title, String artist,
                                     String category, String description, String duration) throws IOException {

        Track track = Track.builder()
                .title(title)
                .artist(artist)
                .category(category)
                .description(description)
                .duration(duration)
                .addedDate(LocalDateTime.now())
                .audioData(file.getBytes())
                .fileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();

        return trackMapper.toResponseDTO(trackRepository.save(track));
    }

    public List<TrackResponseDTO> getAllTracks() {
        return trackRepository.findAll().stream()
                .map(trackMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteTrack(String id) {
        if (id == null) return;
        trackRepository.deleteById(id);
    }

    public Track getTrackEntityById(String id) {
        return trackRepository.findById(id).orElseThrow(() -> new RuntimeException("Track not found"));
    }

    public TrackResponseDTO getTrackById(String id) {
        return trackMapper.toResponseDTO(getTrackEntityById(id));
    }

    public TrackResponseDTO updateTrack(String id, TrackRequestDTO dto) {
        Track track = getTrackEntityById(id);
        track.setTitle(dto.getTitle());
        track.setArtist(dto.getArtist());
        track.setCategory(dto.getCategory());
        track.setDescription(dto.getDescription());
        track.setDuration(dto.getDuration());
        return trackMapper.toResponseDTO(trackRepository.save(track));
    }
}
