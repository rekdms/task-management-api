package com.example.project1.domain.task;

import com.example.project1.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @ManyToOne
    @JoinColumn(name = "MEMBERSNO")
    private Member member;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "REGDT")
    private LocalDateTime regDt;

    @Column(name = "MODDT")
    private LocalDateTime modDt;

    @PrePersist
    public void prePersist(){
        this.regDt = LocalDateTime.now();
        this.status = TaskStatus.N;
    }
}
