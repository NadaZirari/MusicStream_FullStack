package musicstream.backend.controller;



import musicstream.backend.model.Track;
import musicstream.backend.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@CrossOrigin(origins = "http://localhost:4200") // Étape 13 déjà incluse
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long id) {
        return trackService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Track createTrack(@RequestBody Track track) {
        return trackService.create(track);
    }

    @PutMapping("/{id}")
    public Track updateTrack(@PathVariable Long id, @RequestBody Track track) {
        return trackService.update(id, track);
    }

    @DeleteMapping("/{id}")
    public void deleteTrack(@PathVariable Long id) {
        trackService.delete(id);
    }
}
