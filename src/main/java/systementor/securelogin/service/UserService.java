package systementor.securelogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import systementor.securelogin.model.UserModel;
import systementor.securelogin.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean saveUser(UserModel user) {

        Optional<String> existingUser = findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
        return true;
    }

    public Optional<String> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserModel::getPassword);
    }


}