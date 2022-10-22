package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Hobbi;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Repository.HobbiRepository;
import ru.spring.P50519.Repository.EmployeeRepository;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/Hobbi")
public class HobbiController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    HobbiRepository hobbiRepository;
    @GetMapping("/Index")
    public String Hobbi(Model model)
    {
//        Iterable<Employee> listEmp= employeeRepository.findAll();
//        model.addAttribute("listEmp",listEmp);
        Iterable<Hobbi> listHobbi= hobbiRepository.findAll();

        model.addAttribute("listHobbi",listHobbi);
        return "/Hobbi/Index";
    }
    @GetMapping("/IndexAddHobbi")
    public String EmpAddView(Model model,Hobbi hobbi )
    {

        return "/Hobbi/IndexAddHobbi";
    }
    @PostMapping("/IndexAddHobbi")
    public String HobbiAdd(
            @Valid Hobbi hobbi,
            BindingResult bindingResult,


            Model model)
    {
        hobbiRepository.save(hobbi);


        return "redirect:/Hobbi/Index";
    }
    @GetMapping("/HobbiDetail/{id}")
    public String HobbiDetails(@PathVariable Long id,

                             Model model)
    {

        ArrayList<Hobbi> res = new ArrayList<Hobbi>();
        hobbiRepository.findById(id).ifPresent(res::add);
        model.addAttribute("hobbi",res);

        return "/Hobbi/HobbiDetail";
    }
    @GetMapping("/HobbiEmployee/")
    public String HobbiEmpView(Model model, Hobbi hobbi)
    {

        Iterable<Hobbi> listHobbi= hobbiRepository.findAll();
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("listHobbi",listHobbi);
        model.addAttribute("listEmp",listEmp);

        return "/Hobbi/HobbiEmployee";
    }
    @PostMapping("/HobbiEmployee/")
    public String HobbiEmp(Model model,
                            @RequestParam String listEmp, @RequestParam String listHobbi)
    {
        Employee employee = employeeRepository.findFirstByName(listEmp);
        Hobbi hobbi = hobbiRepository.findByLocation(listHobbi);

        hobbi.getEmployees().add(employee);
        hobbiRepository.save(hobbi);
        return "/Hobbi/Index";
    }
}


