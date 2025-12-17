package mx.badak.movies.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import mx.badak.movies.utils.Constants;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = Constants.TITLE_MAX_LENGTH)
    private String title;
    @Column(nullable = false, length = Constants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "image_url", nullable = false, length = Constants.URL_MAX_LENGTH)
    private String imageUrl;
    @Column(name = "release_date", nullable = false)
    private Integer releaseYear;
    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

}

