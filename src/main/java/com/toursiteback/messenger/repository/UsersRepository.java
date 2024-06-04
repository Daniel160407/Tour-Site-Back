package com.toursiteback.messenger.repository;

import com.toursiteback.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name != 'Admin'")
    List<User> findAllByExceptAdmin();

    User getUserByName(String name);

    User save(User user);

    User getUserByEmail(String email);

    User getUserByEmailAndPassword(String email, String password);

    void deleteUserByEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.position = u.position + 1 WHERE u.email <> :email")
    void incrementPositionForAllExceptSpecificEmail(@Param("email") String email);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.position = u.position - 1")
    void reducePositionForAllExceptSpecificEmail();
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.position = 1 WHERE u.email = :email")
    void updatePositionForSpecificEmail(@Param("email") String email);
}
