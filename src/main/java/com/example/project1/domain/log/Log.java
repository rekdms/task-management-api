package com.example.project1.domain.log;

import com.example.project1.domain.task.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Log {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @ManyToOne
    @JoinColumn(name = "TASKSNO")
    private Task task;

    @Enumerated(EnumType.STRING)
    private LogType type;

    private String ip;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "REGDT")
    private LocalDateTime regDt;

    @PrePersist
    public void prePersist() {
        this.regDt = LocalDateTime.now();
    }
}
