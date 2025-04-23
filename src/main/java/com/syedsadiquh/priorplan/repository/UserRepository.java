package com.syedsadiquh.priorplan.repository;

import com.syedsadiquh.priorplan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public User findUsersByUsernameAndPassword(String username, String password);

}
