package in.tatsam.priority.service;

import in.tatsam.priority.model.Priority;
import in.tatsam.priority.repository.PriorityRepository;
import in.tatsam.priority.utils.PriorityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService{

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PriorityUtils priorityUtils;

    @Override
    public Priority findById(Long id) {
        return priorityRepository.findById(id).get();
    }

    @Override
    public Priority findByGuid(String guid) {
        return priorityRepository.findByGuid(guid);
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public Priority addPriority(Priority priority, Long userId) {
        boolean isAdmin = userService.isAdminUser(userId);
        // As we have not used any Authentication and Authorization System using roles to authorize Admin Permission.

        if (!isAdmin){
            new RuntimeException("Only Admin Can add Priority");
        }

        return save(priority);
    }

    @Override
    public Priority updatePriority(Priority priority, Long userId) {
        boolean isAdmin = userService.isAdminUser(userId);
        if (!isAdmin){
            throw new RuntimeException("Only Admin Can add Priority");
        }

        return save(priority);
    }

    public Priority save(Priority priority) {

        priority.setActive(priority.getActive()==null ? true:priority.getActive());
        String guid = priority.getGuid() == null ? priorityUtils.guidGenerator(priority.getName()) : priority.getGuid();
        priority.setGuid(guid);
        return priorityRepository.save(priority);
    }


}
