package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Models.Zoo;
import ru.spring.P50519.Repository.DoljRepository;
import ru.spring.P50519.Repository.EmployeeRepository;
import ru.spring.P50519.Repository.ZooRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/Index")
    public String Employee(Model model)
    {
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("emps",listEmp);
        return "/Employee/Index";
    }

    @GetMapping("/IndexAddEmp")
    public String EmpAddView(Model model)
    {

        return "/Employee/IndexAddEmp";
    }
    @PostMapping("/IndexAddEmp")
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
    @GetMapping("/Filter")
    public String EmpFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Employee> emp=employeeRepository.findByName(name);
        model.addAttribute("empfiltered",emp);
        return "/Employee/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String EmpFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Employee> emp=employeeRepository.findByNameContaining(name);
        model.addAttribute("empfiltered",emp);
        return "/Employee/Filter";
    }
    @GetMapping("/EmployeeDetail/{id}")
    public String EmpDetails(@PathVariable Long id,
                             Model model)
    {
        Optional<Employee> emp = employeeRepository.findById(id);
        ArrayList<Employee> res = new ArrayList<Employee>();
        emp.ifPresent(res::add);
        model.addAttribute("emp",res);
        return "/Employee/EmployeeDetail";
    }
    @GetMapping ("/EmployeeDelete/{id}")
    public String EmpDelete(@PathVariable Long id)
    {
        employeeRepository.deleteById(id);
        return "redirect:/Employee/Index";
    }
    @GetMapping ("/EmployeeEdit/{id}")
    public String EmpEditView(@PathVariable Long id,
                              Model model)
    {
        Employee emp= employeeRepository.findById(id).orElseThrow();
        model.addAttribute("emp",emp);
        return "/Employee/EmployeeEdit";
    }
    @PostMapping ("/EmployeeEdit/{id}")
    public String EmpEdit( @PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String patronymic,
                           @RequestParam Integer age,
                           @RequestParam Integer amount_of_kids,
                           @RequestParam String nationality,
                           Model model)
    {
        Employee emp= employeeRepository.findById(id).orElseThrow();
        emp.setName(name);
        emp.setSurname(surname);
        emp.setPatronymic(patronymic);
        emp.setAge(age);
        emp.setAmount_of_kids(amount_of_kids);
        emp.setNationality(nationality);
        employeeRepository.save(emp);
        model.addAttribute("emp",emp);
        return "redirect:/Employee/Index";
    }
}
