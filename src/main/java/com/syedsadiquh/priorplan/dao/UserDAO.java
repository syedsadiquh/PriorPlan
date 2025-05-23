package com.syedsadiquh.priorplan.dao;

import com.syedsadiquh.priorplan.globals.Global;
import com.syedsadiquh.priorplan.models.User;
import com.syedsadiquh.priorplan.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserDAO {
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean checkUserInDB(String username, String password) {
        User user = userRepository.findUsersByUsernameAndPassword(username, password);
        if (user == null) return false;
        Global.user = user;
        Global.username = user.getUsername();
        Global.name = user.getName();
        return true;
    }
}
