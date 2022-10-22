package ru.spring.P50519.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dolj {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private Integer tax ;

    private String description;

    private int salary;

    private  int max_bonus;

    public Dolj(String name, Integer tax, String description, int salary, int max_bonus) {
        this.name = name;
        this.tax = tax;
        this.description = description;
        this.salary = salary;
        this.max_bonus = max_bonus;
    }

    public Dolj() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getMax_bonus() {
        return max_bonus;
    }

    public void setMax_bonus(int max_bonus) {
        this.max_bonus = max_bonus;
    }
}
