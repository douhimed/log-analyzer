package org.sqli.pfe.enset.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sqli.pfe.enset.models.entities.LogEntity;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
    public Page<LogEntity> findByLoginContainingOrThreadContaining(String login, String thread, Pageable pageable);
    public Page<LogEntity> findByLoginContaining(String login, Pageable pageable);
    public Page<LogEntity> findByThreadContaining(String thread, Pageable pageable);

}
