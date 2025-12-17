package mx.badak.movies.domain.core;

import lombok.RequiredArgsConstructor;
import mx.badak.movies.domain.mapper.ReviewMapper;
import mx.badak.movies.domain.model.ReviewDto;
import mx.badak.movies.domain.port.ReviewRepositoryDB;
import mx.badak.movies.domain.service.ReviewService;
import mx.badak.movies.infrastructure.entity.ReviewEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepositoryDB repository;

    @Override
    public List<ReviewDto> getAllReviews() {
        return repository.findAll()
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }

    @Override
    public ReviewDto createReview(final ReviewDto dto) {
        ReviewEntity entity = ReviewMapper.toEntity(dto);
        ReviewEntity saved = repository.save(entity);
        return ReviewMapper.toDto(saved);
    }

    @Override
    public ReviewDto getReviewById(final Integer id) {
        return repository.findById(id)
                .map(ReviewMapper::toDto)
                .orElse(null);
    }

    @Override
    public void deleteReview(final Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ReviewDto> getReviewsByMovieId(final Integer movieId) {
        return repository.findByMovieId(movieId)
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }

    @Override
    public List<ReviewDto> getReviewsByUserId(final Integer userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }
}
