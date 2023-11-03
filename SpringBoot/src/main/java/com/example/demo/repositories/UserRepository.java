package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE User u set u.status = 2 where u.id = :id")
    void updateStatus(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.id_role = :id_role where u.id = :id AND u.status = 1")
    void updateIdRole(@Param("id") Integer id, @Param("id_role") Integer id_role);
}
