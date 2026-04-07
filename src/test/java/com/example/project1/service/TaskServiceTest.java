package com.example.project1.service;

import com.example.project1.domain.log.Log;
import com.example.project1.domain.task.Task;
import com.example.project1.domain.task.TaskStatus;
import com.example.project1.repository.LogRepository;
import com.example.project1.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.project1.domain.task.TaskStatus.N;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Transactional
class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LogRepository logRepository;

    @Test
    void 로그_생성() {
        // given
        Task task = new Task();
        task.setTitle("테스트 업무");
        task.setStatus(N);

        Task savedTask = taskRepository.save(task);

        // when
        taskService.updateStatus(savedTask.getSno(), "N", "127.0.0.1");

        // then
        List<Log> logs = logRepository.findAll();

        assertThat(logs).hasSize(1);
    }

    @Test
    void 존재하지_않는_task_예외() {
        // when & then
        assertThatThrownBy(() ->
                taskService.updateStatus(999L, "Done", "127.0.0.1")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}