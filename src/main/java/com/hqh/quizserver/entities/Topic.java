package com.hqh.quizserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name", length = 45)
    private String topicName;

    @OneToMany(mappedBy = "topic")
    @JsonIgnore
    private List<TestQuizz> testQuizz;

    public Topic(String topicName) {
        this.topicName = topicName;
    }
}