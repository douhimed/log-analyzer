package org.sqli.pfe.enset.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sqli.pfe.enset.models.entities.LogEntity;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
     Page<LogEntity> findByLoginContainingAndThreadContaining(String login, String thread, Pageable pageable);
     Page<LogEntity> findByLoginContaining(String login, Pageable pageable);
     Page<LogEntity> findByThreadContaining(String thread, Pageable pageable);

}
