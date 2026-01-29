package musicstream.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String audioUrl;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Category category;
}
