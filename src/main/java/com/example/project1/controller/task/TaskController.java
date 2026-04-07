package com.example.project1.controller.task;


import com.example.project1.domain.task.Task;
import com.example.project1.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task create(@RequestBody Map<String, Object> request, HttpServletRequest httpRequest) {

        String title = (String) request.get("title");
        String content = (String) request.get("content");
        Long memberSno = Long.valueOf(request.get("memberSno").toString());
        String ip = httpRequest.getRemoteAddr();
        return taskService.creatTask(title, memberSno, content, ip);
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTaskList();
    }

    @GetMapping("/sno={sno}")
    public Task getOne(@PathVariable Long sno) {
        return taskService.getTaskOne(sno);
    }

    @PostMapping("/sno={sno}")
    public Task modify(@PathVariable Long sno, @RequestBody Map<String, Object> request) {
        String title = (String) request.get("title");
        String content = (String) request.get("content");
        return taskService.modifyTask(sno, title, content);
    }

    @PostMapping("/tasks/{sno}/status")
    public Task updateStatus(@PathVariable Long sno, @RequestBody Map<String, String> request, HttpServletRequest httpRequest) {

        String status = request.get("status");
        String ip = httpRequest.getRemoteAddr(); // ✅ 이걸로 충분

        return taskService.updateStatus(sno, status, ip);
    }
}
