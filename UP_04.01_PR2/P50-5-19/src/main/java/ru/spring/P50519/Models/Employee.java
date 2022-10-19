package ru.spring.P50519.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private int age;

    private int amount_of_kids;

    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Employee(String name, String surname, String patronymic, int age, int amount_of_kids,String nationality) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.amount_of_kids = amount_of_kids;
        this.nationality=nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAmount_of_kids() {
        return amount_of_kids;
    }

    public void setAmount_of_kids(int amount_of_kids) {
        this.amount_of_kids = amount_of_kids;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
