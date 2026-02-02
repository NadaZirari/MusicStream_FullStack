package musicstream.backend.service;

import musicstream.backend.dto.TrackMapper;
import musicstream.backend.dto.TrackResponseDTO;
import musicstream.backend.dto.TrackRequestDTO;
import musicstream.backend.model.Track;
import musicstream.backend.repository.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrackServiceTest {

    @Mock
    private TrackRepository trackRepository;

    @Mock
    private TrackMapper trackMapper;

    @InjectMocks
    private TrackService trackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTracks_ShouldReturnList() {
        // Arrange
        Track track1 = new Track();
        Track track2 = new Track();
        when(trackRepository.findAll()).thenReturn(Arrays.asList(track1, track2));
        when(trackMapper.toResponseDTO(any(Track.class))).thenReturn(new TrackResponseDTO());

        // Act
        List<TrackResponseDTO> result = trackService.getAllTracks();

        // Assert
        assertEquals(2, result.size());
        verify(trackRepository, times(1)).findAll();
        verify(trackMapper, times(2)).toResponseDTO(any(Track.class));
    }

    @Test
    void getTrackById_WhenExists_ShouldReturnTrackDTO() {
        // Arrange
        String id = "test-id";
        Track track = new Track();
        track.setId(id);
        TrackResponseDTO dto = new TrackResponseDTO();
        dto.setId(id);

        when(trackRepository.findById(id)).thenReturn(Optional.of(track));
        when(trackMapper.toResponseDTO(track)).thenReturn(dto);

        // Act
        TrackResponseDTO result = trackService.getTrackById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void getTrackById_WhenNotExists_ShouldThrowException() {
        // Arrange
        String id = "invalid-id";
        when(trackRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> trackService.getTrackById(id));
    }

    @Test
    void deleteTrack_ShouldCallRepository() {
        // Arrange
        String id = "test-id";

        // Act
        trackService.deleteTrack(id);

        // Assert
        verify(trackRepository, times(1)).deleteById(id);
    }

    @Test
    void saveTrack_ShouldReturnSavedTrackDTO() throws IOException {
        // Arrange
        MultipartFile file = mock(MultipartFile.class);
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});
        when(file.getOriginalFilename()).thenReturn("test.mp3");
        when(file.getContentType()).thenReturn("audio/mpeg");

        Track savedTrack = new Track();
        savedTrack.setTitle("Test Title");
        TrackResponseDTO dto = new TrackResponseDTO();
        dto.setTitle("Test Title");

        when(trackRepository.save(any(Track.class))).thenReturn(savedTrack);
        when(trackMapper.toResponseDTO(savedTrack)).thenReturn(dto);

        // Act
        TrackResponseDTO result = trackService.saveTrack(file, "Test Title", "Artist", "pop", "Desc", "180");

        // Assert
        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        verify(trackRepository, times(1)).save(any(Track.class));
        verify(trackMapper, times(1)).toResponseDTO(savedTrack);
    }

    @Test
    void updateTrack_ShouldReturnUpdatedTrackDTO() {
        // Arrange
        String id = "test-id";
        Track existingTrack = new Track();
        existingTrack.setId(id);
        
        TrackRequestDTO requestDTO = new TrackRequestDTO("New Title", "New Artist", "pop", "New Desc", "200");
        
        Track savedTrack = new Track();
        savedTrack.setTitle("New Title");
        
        TrackResponseDTO responseDTO = new TrackResponseDTO();
        responseDTO.setTitle("New Title");

        when(trackRepository.findById(id)).thenReturn(Optional.of(existingTrack));
        when(trackRepository.save(any(Track.class))).thenReturn(savedTrack);
        when(trackMapper.toResponseDTO(savedTrack)).thenReturn(responseDTO);

        // Act
        TrackResponseDTO result = trackService.updateTrack(id, requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        verify(trackRepository).save(any(Track.class));
    }
}

