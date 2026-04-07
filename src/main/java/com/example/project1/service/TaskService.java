package com.example.project1.service;

import com.example.project1.domain.log.Log;
import com.example.project1.domain.log.LogType;
import com.example.project1.domain.member.Member;
import com.example.project1.domain.task.Task;
import com.example.project1.domain.task.TaskStatus;
import com.example.project1.repository.LogRepository;
import com.example.project1.repository.MemberRepository;
import com.example.project1.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;
    private final LogRepository logRepository;

    // 업무 등록
    @Transactional
    public Task creatTask(String title, Long memberSno, String content, String ip) {

        Member member = memberRepository.findById(memberSno)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));


        if (title == null || title.isEmpty()) throw new IllegalArgumentException("제목은 필수입니다.");

        if (content == null || content.isEmpty()) throw new IllegalArgumentException("내용은 필수입니다.");
        Task task = new Task();
        task.setTitle(title);
        task.setStatus(TaskStatus.N);
        task.setMember(member);
        task.setContent(content);

        Task savedTask = taskRepository.save(task);
        //로그 저장
        Log log = new Log();
        log.setTask(savedTask);
        log.setType(LogType.TASK);
        log.setIp(ip);
        log.setContent("업무 생성: " + title);

        logRepository.save(log);

        return savedTask;
    }

    @Transactional
    public Task modifyTask(Long sno, String title, String content) {
        Task task = taskRepository.findById(sno)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 업무입니다."));

        if (title != null) task.setTitle(title);
        if (content != null) task.setContent(content);

        task.setModDt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Transactional
    public Task updateStatus(Long sno, String status, String ip) {

        Task task = taskRepository.findById(sno)
                .orElseThrow(() -> new RuntimeException("업무 없음"));

        String beforeStatus = task.getStatus().name();

        task.setStatus(TaskStatus.valueOf(status));

        Task savedTask = taskRepository.save(task);

        // 🔥 로그 저장 (핵심)
        Log log = new Log();
        log.setTask(savedTask);
        log.setType(LogType.TASK);
        log.setIp(ip);
        log.setContent("업무 상태 변경: " + beforeStatus + " → " + status);

        logRepository.save(log);

        return savedTask;
    }

    // 전체 업무 리스트 가져오기
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    // 단건 조회
    public Task getTaskOne(Long sno) {
        return taskRepository.findById(sno)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 업무입니다."));
    }



}
