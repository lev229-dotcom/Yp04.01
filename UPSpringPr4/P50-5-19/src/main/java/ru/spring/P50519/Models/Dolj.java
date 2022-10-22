package ru.spring.P50519.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Dolj {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=10,max=50,message="Поле должно содержать не менее 10 и не более 50 символов")
    private String name;
    @NotNull
    @PositiveOrZero(message = "Значение не должно быть негативным")
    private Integer tax ;
    @NotBlank(message="Пробелы недопостимы")
    private String description;
    @Positive(message = "Значение не должно быть негативным")
    @NotNull
    private int salary;

    @PositiveOrZero(message = "Значение не должно быть негативным")
    private int max_bonus;

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
