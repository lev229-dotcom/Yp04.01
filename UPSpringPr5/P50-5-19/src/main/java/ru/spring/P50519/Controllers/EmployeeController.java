package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Account;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Models.Zoo;
import ru.spring.P50519.Repository.AccountRepository;
import ru.spring.P50519.Repository.DoljRepository;
import ru.spring.P50519.Repository.EmployeeRepository;
import ru.spring.P50519.Repository.ZooRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    DoljRepository doljRepository;
    @GetMapping("/Index")
    public String Employee(Model model)
    {
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("emps",listEmp);
        return "/Employee/Index";
    }

    @GetMapping("/IndexAddEmp")
    public String EmpAddView(Model model,Employee employee, Account account, Dolj dolj)
    {
        Iterable<Dolj> listDolj= doljRepository.findAll();
        model.addAttribute("listDolj",listDolj);

        return "/Employee/IndexAddEmp";
    }
    @PostMapping("/IndexAddEmp")
    public String EmpAdd(
            @Valid Employee employee,
            @Valid Account account,
            BindingResult bindingResult,
            @RequestParam Long listDolj,

            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Employee/IndexAddEmp";
        }

        account = accountRepository.save(account);

        employee.setAccount(account);
        employee.setDolj(doljRepository.findById(listDolj).orElseThrow());
        employeeRepository.save(employee);


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
        Optional<Employee> employee = employeeRepository.findById(id);
        Optional<Account> account = accountRepository.findById(id);
        ArrayList<Employee> res = new ArrayList<Employee>();
        ArrayList<Account> res_acc = new ArrayList<Account>();
        employee.ifPresent(res::add);
        account.ifPresent(res_acc::add);
        model.addAttribute("employee",res);
        model.addAttribute("account",res_acc);
        return "/Employee/EmployeeDetail";
    }
    @GetMapping ("/EmployeeDelete/{id}")
    public String EmpDelete(@PathVariable Long id)
    {
        employeeRepository.deleteById(id);
        return "redirect:/Employee/Index";
    }
    @GetMapping ("/EmployeeEdit/{id}")
    public String EmpEditView(@PathVariable Long id,Employee employee,Account account,
                              Model model)
    {
        employee= employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee",employee);
        return "/Employee/EmployeeEdit";
    }
    @PostMapping ("/EmployeeEdit/{id}")
    public String EmpEdit(   @Valid Employee employee,
                             @Valid Account account,
                             BindingResult bindingResult,
                           Model model)
    {
        model.addAttribute("employee",employee);

        model.addAttribute("account",account);
        if (bindingResult.hasErrors())
        {
            return "/Employee/EmployeeEdit";
        }
        employeeRepository.save(employee);


        accountRepository.save(account);

        return "redirect:/Employee/Index";
    }
}
