package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Hobbi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Пробелы недопустимы")
    private String Name;

    public Hobbi(Long id, String Name, String date_of_begin, List<Employee> employees) {
        this.id = id;
        this.Name = Name;
        this.date_of_begin = date_of_begin;
        this.employees = employees;
    }

    public String getdate_of_begin() {
        return date_of_begin;
    }

    public void setdate_of_begin(String date_of_begin) {
        this.date_of_begin = date_of_begin;
    }

    @NotBlank(message = "Пробелы недопустимы")
    private String date_of_begin;
    @ManyToMany
    @JoinTable(name="employee_hobbi",
            joinColumns=@JoinColumn(name = "hobbi_id"),
            inverseJoinColumns=@JoinColumn(name = "employee_id"))
    private List<Employee> employees;


    public Hobbi() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
