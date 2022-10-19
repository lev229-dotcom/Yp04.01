package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.P50519.Models.Zoo;
import ru.spring.P50519.Repository.ZooRepository;

import java.util.List;

@Controller
public class ZooController {
    @Autowired
    ZooRepository zooRepository;
    @GetMapping("/Zoo/Index")
    public String Zoo(Model model)
    {
      Iterable<Zoo> listZoo= zooRepository.findAll();

      model.addAttribute("listZoo",listZoo);
        return "/Zoo/Index";
    }
    @GetMapping("/Zoo/IndexAddZoo")
    public String ZooAddView(Model model)
    {

        return "/Zoo/IndexAddZoo";
    }
    @PostMapping("/Zoo/IndexAddZoo")
    public String ZooAdd(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Integer age,
            @RequestParam Integer mass,
            Model model)
    {
        Zoo animal=new Zoo(name,age,description,mass);
        zooRepository.save(animal);
        return "redirect:/Zoo/Index";
    }
    @GetMapping("/Zoo/Filter")
    public String ZooFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Zoo> animal=zooRepository.findByNameContaining(name);
        model.addAttribute("anim",animal);
        return "/Zoo/Filter";
    }
}

