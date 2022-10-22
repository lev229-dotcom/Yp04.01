package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Role;
import ru.spring.P50519.Models.User;
import ru.spring.P50519.Repository.UserRepository;

@Controller
@RequestMapping("/User")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

@GetMapping("/userView")
    public String userView(Model model)
    {
        model.addAttribute("listUser",userRepository.findAll());
        return "User/userView";
    }
    @GetMapping("/userEdit/{id}")
    public String userEditView(@PathVariable(name="id") Long id, Model model)
    {
        model.addAttribute("listRole", Role.values());
        model.addAttribute("userOne",userRepository.findById(id).orElseThrow());
        return "User/userEdit";
    }
    @PostMapping("/userEdit/{id}")
    public String userEditView(@PathVariable(name="id") Long id,
                               @RequestParam String username,
                               @RequestParam(name="role[]") String[] userRoles,
                               Model model)
    {
        User user =userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.getRole().clear();
        for (String roleOne:
        userRoles)
        {
            user.getRole().add(Role.valueOf(roleOne));
        }
        userRepository.save(user);
      return "redirect:/User/userView";
    }
}
