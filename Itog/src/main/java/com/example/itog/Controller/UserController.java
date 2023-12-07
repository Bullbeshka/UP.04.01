package com.example.itog.Controller;


import com.example.itog.model.ModelUser;
import com.example.itog.model.Role;
import com.example.itog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/edit")
    public String editAccess(Model model) {
        List<ModelUser> allUsers = (List<ModelUser>) userRepository.findAll();
        model.addAttribute("users", allUsers);
        return "edit";
    }

    @PostMapping("/edit/{userId}/edit")
    public String updateAccess(@PathVariable long userId, @RequestParam Set<Role> roles) {
        Optional<ModelUser> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            ModelUser user = optionalUser.get();
            user.setRoles(roles);
            userRepository.save(user);
        }

        return "redirect:/edit";
    }
}
