package pl.coderslab.charity.models.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "userLogin";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") @Validated LoginForm loginForm,
                               BindingResult bindingResult,
                               HttpSession session) {
        // Validate the login form
        // (You can use annotations like @Email, @NotBlank, etc., on the LoginForm class)

        if (bindingResult.hasErrors()) {
            return "userLogin";
        }


        User user = userRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

        if (user != null) {
            session.setAttribute("user", user);
            if (user.getEnable() == 1){
                return "redirect:user/adminDashboard";
            }
            return "redirect:user/userDashboard";
        } else {
            // Failed login
            bindingResult.rejectValue("email", "error.loginForm", "Invalid email or password");
            return "userNotFound";
        }
    }
}