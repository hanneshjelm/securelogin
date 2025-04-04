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

    /*TODO
        *Save User (OBS! Make sure the password is encrypted by Bcrypt)
        *check if user already exists (VG)
    * */

    public Optional<String> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserModel::getPassword);
    }


}