package com.hqh.quizserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hqh.quizserver.entity.base.BaseEntity;
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
@Table(name = "topic")
public class Topic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name", length = 45)
    private String topicName;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TestQuizz> testQuizz;

    public Topic(String topicName) {
        this.topicName = topicName;
    }
}