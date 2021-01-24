package in.tatsam.priority.repository;

import in.tatsam.priority.model.Priority;
import in.tatsam.priority.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    public List<Priority> findAll();

    public Optional<Priority> findById(Long userId);

    public Priority findByGuid(String guid);
}
