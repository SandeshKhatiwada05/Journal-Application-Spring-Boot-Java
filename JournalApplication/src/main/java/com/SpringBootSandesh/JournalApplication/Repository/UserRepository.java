package com.SpringBootSandesh.JournalApplication.Repository;

import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);  // Corrected method name
    void deleteByUsername(String username);  // inorder to delete the user
}
