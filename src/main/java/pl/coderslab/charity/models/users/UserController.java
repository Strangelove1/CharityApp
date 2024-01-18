package pl.coderslab.charity.models.users;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.models.donation.Donation;
import pl.coderslab.charity.models.donation.DonationRepository;
import pl.coderslab.charity.models.institution.InstitutionRepository;

import javax.servlet.http.HttpSession;

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
    @GetMapping("/updateUser/{id}")
    public String showUpdateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "userUpdate";
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
        return "userDeleted";
    }

}
