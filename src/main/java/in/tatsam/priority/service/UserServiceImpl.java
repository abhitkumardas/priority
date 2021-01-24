package in.tatsam.priority.service;

import in.tatsam.priority.config.Role;
import in.tatsam.priority.model.User;
import in.tatsam.priority.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long userId){
        User user = userRepository.findById(userId).get();
        if (user==null){
            throw new RuntimeException("User Not Exists with Id "+userId);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        User user1 = findByUsername(user.getUsername());
        if (user1!=null){
            throw new RuntimeException("User Already Exists with Username "+ user.getUsername());
        }
        user1=findByMobile(user.getMobile());
        if (user1!=null){
            throw new RuntimeException("User Already Exists with Mobile "+ user.getMobile());
        }
        user1=findByEmail(user.getEmail());
        if (user1!=null){
            throw new RuntimeException("User Already Exists with Email "+ user.getEmail());
        }

        user.setActive(user.getActive()==null ? true:user.getActive());

        return userRepository.save(user);
    }

    public User update(User user) {
        //check if user is there or not
        User user1 = findById(user.getId());
        return userRepository.save(user);
    }

    //We are not deleting the User we are just disabling them
    public User disableUser(User user) {
        //check wheather user exists
        User user1 = findById(user.getId());
        user.setActive(false);
        return userRepository.save(user);
    }

    @Override
    public boolean isAdminUser(User user) {
        Role role = user.getRole();
        if (role.equals(Role.ADMIN)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isAdminUser(Long userId) {
        User user = findById(userId);
        return isAdminUser(user);
    }
}
