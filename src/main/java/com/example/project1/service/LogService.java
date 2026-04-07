package com.example.project1.service;

import com.example.project1.domain.log.Log;
import com.example.project1.domain.log.LogType;
import com.example.project1.domain.task.TaskStatus;
import com.example.project1.repository.LogRepository;
import com.example.project1.repository.MemberRepository;
import com.example.project1.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

}
