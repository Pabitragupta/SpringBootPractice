package com.example.journal.repository;


import com.example.journal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    void deleteByUserName(String userName);

    boolean existsByUserName(String userName);

}
