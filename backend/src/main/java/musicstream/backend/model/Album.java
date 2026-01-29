package musicstream.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String releaseDate;
    private String coverImageUrl;

    @ManyToOne
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Track> tracks;
}
