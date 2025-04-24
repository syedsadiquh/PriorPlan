package com.syedsadiquh.priorplan.dao;

import com.syedsadiquh.priorplan.models.User;
import com.syedsadiquh.priorplan.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDAO {
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUserInDB(String username, String password) {
        User user = userRepository.findUsersByUsernameAndPassword(username, password);
        return user != null;
    }
}
