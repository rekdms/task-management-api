package com.example.project1.repository;

import com.example.project1.domain.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long>  {
}
