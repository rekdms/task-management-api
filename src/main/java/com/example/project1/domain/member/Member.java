package com.example.project1.domain.member;

import com.example.project1.domain.task.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    private String id;
    private String name;
    private String pwd;
    private String phone;
    private String email;

    @Column(name = "REGDT")
    private LocalDateTime regDt;

    @PrePersist
    public void prePersist(){
        this.regDt = LocalDateTime.now();
    }
}
