package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Пробелы недопустимы")
    private String login;
    @NotBlank(message = "Пробелы недопустимы")
    @Size(min=5,max=50,message = "Минимум 5 и максимум 50 символов")
    private String Password;
    @OneToOne(optional = true,mappedBy = "account")
    private Employee employee;


    public Account() {
    }

    public Account(Long id, String login, String password, Employee employee) {
        this.id = id;
        this.login = login;
        this.Password = password;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
