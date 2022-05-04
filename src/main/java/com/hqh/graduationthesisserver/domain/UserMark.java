package com.hqh.graduationthesisserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_mark")
public class UserMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "mark")
    private float mark;

    @Column(name = "completed_date")
    private Instant completedDate;

    @OneToOne
    @JoinColumn(name = "quizz_id")
    private TestQuizz testQuizz;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}