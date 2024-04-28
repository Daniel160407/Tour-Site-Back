package com.toursiteback.messenger.repository;

import com.toursiteback.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findAllBy();

    User getUserByName(String name);

    User getUserById(int id);

    User save(User user);
}
