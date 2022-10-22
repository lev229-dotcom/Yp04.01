package ru.spring.P50519.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=1,max=30,message="Поле должно содержать не менее 1 и не более 30 символов")
    private String name;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=1,max=30,message="Поле должно содержать не менее 1 и не более 30 символов")
    private String surname;

    @NotBlank(message="Пробелы недопустимы")
    private String patronymic;
    @Max(value=100,message="Сотруднику должно быть не более 100 лет")
    @Min(value=18,message="Сотруднику должно быть не менее 18 лет")
    @NotNull(message="Поле не должно быть пустым")
    private int age;
    @PositiveOrZero(message = "Значение не должно быть негативным")
    @NotNull(message="Поле не должно быть пустым")
    private int amount_of_kids;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=1,max=50,message="Поле должно содержать не менне 1 и не более 30 символов")
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
