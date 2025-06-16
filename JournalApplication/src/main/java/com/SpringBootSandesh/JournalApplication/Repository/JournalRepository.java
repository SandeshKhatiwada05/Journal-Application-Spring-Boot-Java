package com.SpringBootSandesh.JournalApplication.Repository;

import com.SpringBootSandesh.JournalApplication.Entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
}
