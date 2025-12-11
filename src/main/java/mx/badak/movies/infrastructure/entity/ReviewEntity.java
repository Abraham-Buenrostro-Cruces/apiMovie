package mx.badak.movies.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import mx.badak.movies.utils.Constants;

import java.time.LocalDateTime;

@Entity
@Table(name = "review", uniqueConstraints = {
    @UniqueConstraint(name = "uq_review", columnNames = {"user_id", "movie_id"})
})
@Getter
@Setter
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false, length = Constants.COMMENT_MAX_LENGTH)
    private String comment;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private MovieEntity movie;
}
