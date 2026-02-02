package musicstream.backend.controller;

import musicstream.backend.dto.TrackRequestDTO;
import musicstream.backend.dto.TrackResponseDTO;
import musicstream.backend.model.Track;
import musicstream.backend.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@CrossOrigin(origins = "http://localhost:4200")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public List<TrackResponseDTO> getAll() {
        return trackService.getAllTracks();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TrackResponseDTO> uploadTrack(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("duration") String duration) throws IOException {

        TrackResponseDTO savedTrack = trackService.saveTrack(file, title, artist, category, description, duration);
        return ResponseEntity.ok(savedTrack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable String id) {
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackResponseDTO> updateTrack(
            @PathVariable String id,
            @RequestBody TrackRequestDTO trackRequestDTO) {

        TrackResponseDTO updated = trackService.updateTrack(id, trackRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/stream")
    public ResponseEntity<byte[]> getAudio(@PathVariable String id) {
        if (id == null) return ResponseEntity.badRequest().build();
        Track track = trackService.getTrackEntityById(id);
        String contentType = track.getContentType();
        MediaType mediaType = contentType != null ? MediaType.parseMediaType(contentType) : MediaType.APPLICATION_OCTET_STREAM;
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(track.getAudioData());
    }
}
