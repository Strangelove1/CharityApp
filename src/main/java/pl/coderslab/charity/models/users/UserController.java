package pl.coderslab.charity.models.users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.models.donation.DonationRepository;
import pl.coderslab.charity.models.institution.InstitutionRepository;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    public UserController(UserRepository userRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/listUsers")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }


    @GetMapping("/registerUser")
    public String showAddUserForm(Model model){
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String addUserForm(@ModelAttribute @Validated User user){
        if (Objects.equals(user.getPassword(), "admin")) {
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

    @RequestMapping("/userDashboard")
    public String showUserDashboard(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null){
            return "noLoginDashboardEntry";
        }
        Long userId = user.getId();
        model.addAttribute("donations", donationRepository.findDonationByUser_Id(userId));
        return "userDashboard";
    }

    @RequestMapping("/adminDashboard")
    public String showAdminDashboard(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "noLoginDashboardEntry";
        }
        if (user.getEnable() == 0){
            return "adminLoginWarning";
        }
        model.addAttribute("admins", userRepository.findUsersByEnable(1));
        model.addAttribute("institututions", institutionRepository.findAll());
        model.addAttribute("users", userRepository.findUsersByEnable(0));
        return "adminDashboard";
    }

//to be edited
    @GetMapping("/updateUser")
    public String showUpdateUserForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null){
            return "noLoginDashboardEntry";
        }
        model.addAttribute("user", user);
        return "userUpdate";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        if (user != null) {
            userRepository.save(user);
            return "redirect:/userDashboard";
        }
        return "noLoginDashboardEntry";
        }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "userDeleted";
    }

}
