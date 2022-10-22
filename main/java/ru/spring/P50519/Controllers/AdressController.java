package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Account;
import ru.spring.P50519.Models.Adress;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Repository.AccountRepository;
import ru.spring.P50519.Repository.AdressRepository;
import ru.spring.P50519.Repository.EmployeeRepository;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Adress")
@PreAuthorize("hasAnyAuthority('HR')")
public class AdressController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AdressRepository adressRepository;
    @GetMapping("/Index")
    public String Adress(Model model)
    {

        Iterable<Adress> listAdress= adressRepository.findAll();

        model.addAttribute("listAdress",listAdress);
        return "/Adress/Index";
    }
    @GetMapping("/IndexAddAdress")
    public String EmpAddView(Model model,Adress adress )
    {

        return "/Adress/IndexAddAdress";
    }
    @PostMapping("/IndexAddAdress")
    public String AdressAdd(
            @Valid Adress adress,
            BindingResult bindingResult,


            Model model)
    {
        adressRepository.save(adress);


        return "redirect:/Adress/Index";
    }
    @GetMapping("/AdressDetail/{id}")
    public String AdressDetails(@PathVariable Long id,

                             Model model)
    {

        ArrayList<Adress> res = new ArrayList<Adress>();
        adressRepository.findById(id).ifPresent(res::add);
        model.addAttribute("adress",res);

        return "/Adress/AdressDetail";
    }
    @GetMapping("/AdressEmployee/")
    public String AdressEmpView(Model model, Adress adress)
    {

        Iterable<Adress> listAdress= adressRepository.findAll();
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("listAdress",listAdress);
        model.addAttribute("listEmp",listEmp);

        return "/Adress/AdressEmployee";
    }
    @PostMapping("/AdressEmployee/")
    public String AdressEmp(Model model,
                            @RequestParam String listEmp, @RequestParam String listAdress)
    {
        Employee employee = employeeRepository.findFirstByName(listEmp);
        Adress adress = adressRepository.findByLocation(listAdress);

        adress.getEmployees().add(employee);
        adressRepository.save(adress);
        return "/Adress/Index";
    }
//    @PostMapping("/AdressDetail/AdressEmployee/{id}")
//    public String AdressEmployee(@PathVariable Long id,
//                                 Model model)
//    {
//        Optional<Adress> adress = adressRepository.findById(id);
//
//        ArrayList<Adress> res = new ArrayList<Adress>();
//        adress.ifPresent(res::add);
//
//        model.addAttribute("adress",res);
//
//        return "/Adress/AdressDetail";
//    }
}


