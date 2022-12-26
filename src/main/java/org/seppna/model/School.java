package org.seppna.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity //класс является сущностью
@Table(name="schools")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class School {
    @Id                 //поле - первичный ключ
    @Column(name="id")  //поле класса связано с полем таблицы "id"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nameschool")
    private String nameSchool;

    @Column(name="city")
    private String city;

    //school - объект, который описан в классе Learner.
    //Там уже описаны связи с сущностью School
    @OneToMany(mappedBy = "school")
    //в каждой школе есть список учеников
    private List<Learner> learners;

    public School(String nameSchool, String city) {
        this.nameSchool = nameSchool;
        this.city = city;
    }

}