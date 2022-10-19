package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Models.Zoo;
import ru.spring.P50519.Repository.DoljRepository;
import ru.spring.P50519.Repository.EmployeeRepository;
import ru.spring.P50519.Repository.ZooRepository;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/Employee/Index")
    public String Employee(Model model)
    {
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("emps",listEmp);
        return "/Employee/Index";
    }

    @GetMapping("/Employee/IndexAddEmp")
    public String EmpAddView(Model model)
    {

        return "/Employee/IndexAddEmp";
    }
    @PostMapping("/Employee/IndexAddEmp")
    public String EmpAdd(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String patronymic,
            @RequestParam Integer age,
            @RequestParam Integer amount_of_kids,
            @RequestParam String nationality,
            Model model)
    {
        Employee emp=new Employee(name,surname,patronymic,age,amount_of_kids,nationality);
        employeeRepository.save(emp);
        return "redirect:/Employee/Index";
    }
    @GetMapping("/Employee/Filter")
    public String EmpFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Employee> emp=employeeRepository.findByName(name);
        model.addAttribute("empfiltered",emp);
        return "/Employee/Filter";
    }
    @GetMapping("/Employee/FilterCategorized")
    public String EmpFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Employee> emp=employeeRepository.findByNameContaining(name);
        model.addAttribute("empfiltered",emp);
        return "/Employee/Filter";
    }
}
