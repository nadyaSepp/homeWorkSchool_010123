//Домашнее задание 01.01.2023
//        Вам предстоит реализовать связь «Один ко многим» с помощью Hibernate.
//        Школьники учатся в школе. В одной школе много школьников, но каждый школьник учится ровно в одной школе.
//        Для школьника нужно хранить следующую информацию:
//        •	ФИО
//        •	Дата рождения
//        •	Номер телефона
//        Для школы нужно хранить следующую информацию:
//        •	Полное название школы
//        •	Город, в котором находится школа (в виде строки)
//
//        Должен быть сделан скрипт sql, который создаёт бд. Также, должны быть описаны данные сущности с помощью Java классов и Hibernate.
//

        package org.seppna;

import org.seppna.model.Learner;
import org.seppna.model.School;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //Будем здесь делать транзакции
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Learner.class)
                .addAnnotatedClass(School.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

//        //Получить список учеников для конкретной школы
//        School school1 = session.get(School.class, 2);  //школа с id 2
//        List<Learner> learners = school1.getLearners();
//        System.out.printf("Список учеников в школе %s :\n", school1.getNameSchool());
//        learners.forEach(b -> System.out.println(b.getFullName()));
//
//        //Добавить конкретного ученика в школу
//        School school2 = session.get(School.class, 2);  //школа с id 2
//        Learner learner = new Learner("Куликов Петр Ильич", "01.01.1995", "89161427968" , school2);
//        school2.getLearners().add(learner);   //Обязательно делаем связь с двух сторон!!!
//        session.save(learner);

//        //Создать новую школу и добавить в нее ученика
//        School school3 = new School("Средняя школа №8 имени А.Матросова","Ульяновск");
//        Learner learner3 =new Learner("Куликова Анна Ильинична", "01.01.1995", "89161427968" , school3);
//        //Связь с двух сторон!
//        school3.setLearners(new ArrayList<>(Collections.singletonList(learner3)));
//        session.save(school3);
//        session.save(learner3);

//        //Удалить всех учеников у школы
//        School targetSchool = session.get(School.class, 2);
//        targetSchool.getLearners()
//                .forEach(session::remove);      //Удаляем в базе
//        targetSchool.getLearners().clear();     //Удаляем на уровне объекта

        //Удалить школу
//        School schoolToBeRemoved = session.get(School.class, 2);
//        session.remove(schoolToBeRemoved);  //Удаляем из бд школу
//        //У каждого ученика данной школы устанавливаем школу как null
//        schoolToBeRemoved.getLearners().forEach(b -> b.setSchool(null));

        //Поменять школу у ученика
        School newSchool = session.get(School.class, 3);
        Learner learnerToBeChanged = session.get(Learner.class, 2);
        //У настоящей школы из списка её учеников нужно удалить этого ученика
        if (learnerToBeChanged.getSchool() != null) {
            learnerToBeChanged.getSchool().getLearners().remove(learnerToBeChanged);
        }
        //Устанавливаем у этого ученика новую школу
        learnerToBeChanged.setSchool(newSchool);
        //Добавляем в список учеников новой школы данного ученика
        newSchool.getLearners().add(learnerToBeChanged);

        session.getTransaction().commit();      //Сохраняем изменения
        session.close();
    }
}
