package org.seppna.model;

import lombok.*;

import javax.persistence.*;

@Entity //класс является сущностью
@Table(name="learners")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
//Owning side (управляющая сторона)
public class Learner {
    @Id                 //поле - первичный ключ
    @Column(name="id")  //поле класса связано с полем таблицы "id"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="fullName")
    private String fullName;
    @Column(name="dateBirth")
    private String dateBirth;
    @Column(name="numberTelf")
    private String numberTelf;

    @ManyToOne      //Указываем, что "учеников много, но у каждого школа одна"
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    public Learner(String fullName, String dateBirth, String numberTelf, School school) {
        this.fullName = fullName;
        this.dateBirth = dateBirth;
        this.numberTelf = numberTelf;
        this.school = school;
    }
}
