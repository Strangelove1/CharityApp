package pl.coderslab.charity.models.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registerUser")
    public String showAddUserForm(Model model){
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String addUserForm(@ModelAttribute @Validated User user){
        if (user.getPassword() == "admin") {
            user.setEnable(1);
            userRepository.save(user);
            return "redirect:/user/adminAdded";
        }
        user.setEnable(0);
        userRepository.save(user);
        return "redirect:/user/userAdded/" + user.getId();
    }

    @RequestMapping("/userAdded/{id}")
    public String showCreatedUser(@PathVariable long id, Model model){
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "userAdded";
        } else {
            return "userNotAdded";
        }
    }

    @RequestMapping("/adminAdded")
    public String showCreatedAdmin(@ModelAttribute @Validated User user, Model model){
        return "adminAdded";
    }
//    Tutaj nadal do zmiany
    @GetMapping("/updateUser/{id}")
    public String showUpdateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/updateUser";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        userRepository.save(user);
        return "redirect:/userUpdated/" + user.getId();
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/user/userDeleted";
    }

}
