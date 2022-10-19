package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Repository.DoljRepository;

import java.util.List;
@Controller
public class DoljController {
    @Autowired
    DoljRepository doljRepository;
    @GetMapping("/Dolj/Index")
    public String Dolj(Model model)
    {

        Iterable<Dolj> listDolj= doljRepository.findAll();
        model.addAttribute("doljs",listDolj);
        return "/Dolj/Index";
    }
    @GetMapping("/Dolj/IndexAddDolj")
    public String DoljAddView(Model model)
    {

        return "/Dolj/IndexAddDolj";
    }
    @PostMapping("/Dolj/IndexAddDolj")
    public String DoljAdd(
            @RequestParam String name,
            @RequestParam Integer tax,
            @RequestParam String description,
            @RequestParam Integer salary,
            @RequestParam Integer max_bonus,
            Model model)
    {
        Dolj dolj=new Dolj(name,tax,description,salary,max_bonus);
        doljRepository.save(dolj);
        return "redirect:/Dolj/Index";
    }
    @GetMapping("/Dolj/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Dolj> doljs=doljRepository.findByName(name);
        model.addAttribute("doljs_filtered",doljs);
        return "/Dolj/Filter";
    }
    @GetMapping("/Dolj/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Dolj> doljs=doljRepository.findByNameContaining(name);
        model.addAttribute("doljs_filtered",doljs);
        return "/Dolj/Filter";
    }
}
