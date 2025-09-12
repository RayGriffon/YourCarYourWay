package fr.openclassrooms.YourCarYourWay.service;

import fr.openclassrooms.YourCarYourWay.model.User;
import fr.openclassrooms.YourCarYourWay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) { return userRepository.findById(id); }

    public Optional<User> findByEmail(String email) { return userRepository.findByEmail(email); }

    public User save(User u) { return userRepository.save(u); }
}