package org.sqli.pfe.enset.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sqli.pfe.enset.models.entities.LogEntity;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity,Long> {

}
