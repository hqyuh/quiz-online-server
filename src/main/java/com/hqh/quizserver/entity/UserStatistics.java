package com.hqh.quizserver.entity;

import com.hqh.quizserver.entity.base.BaseEntity;
import com.hqh.quizserver.utils.ConvertTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_statistics")
public class UserStatistics extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_of_users_in_use")
    private int numberOfUsersInUse;

}
