package com.toursiteback.messenger.repository;

import com.toursiteback.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name != 'Admin'")
    List<User> findAllByExceptAdmin();

    User getUserByName(String name);

    User save(User user);

    User getUserByEmail(String email);
}
