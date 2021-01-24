package in.tatsam.priority.service;

import in.tatsam.priority.model.Priority;
import java.util.List;

public interface PriorityService {
    public Priority findById(Long id);
    public Priority findByGuid(String guid);
    public List<Priority> findAll();

    public Priority addPriority(Priority priority, Long userId);

    public Priority updatePriority(Priority priority, Long userId);
}
