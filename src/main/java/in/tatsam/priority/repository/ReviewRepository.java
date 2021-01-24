package in.tatsam.priority.repository;

import in.tatsam.priority.model.Priority;
import in.tatsam.priority.model.Review;
import in.tatsam.priority.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAll();
    public Optional<Review> findById(Long id);
    public List<Review> findByUser(User user);
    public List<Review> findByPriority(Priority priority);
}
