package pl.coderslab.charity.models.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.models.category.CategoryRepository;
import pl.coderslab.charity.models.institution.InstitutionRepository;
import pl.coderslab.charity.models.users.User;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/donation")
public class DonationController {
private final InstitutionRepository institutionRepository;
private final CategoryRepository categoryRepository;
private final DonationRepository donationRepository;


    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository) {

        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }


    @GetMapping("/listDonations")
    public String listDonations(Model model) {
        model.addAttribute("donations", donationRepository.findAll());
        return "donationList";
    }
    @GetMapping("/form1")
    public String form1a(Model model){
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
    return "donationform1";
    }

    @PostMapping("/form1")
    public String form1b(@ModelAttribute Donation donation, HttpSession session){
        User user = (User) session.getAttribute("user");
        donation.setUser(user);
        donationRepository.save(donation);
        return  "redirect:/donation/donationconfirmation";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found")); // You can replace this exception with a more specific one

        model.addAttribute("donation", donation);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "updateDonationForm";
    }

    @PostMapping("/update")
    public String updateDonation(@ModelAttribute Donation donation) {
        donationRepository.save(donation);
        return "redirect:/donation/donationconfirmation";
    }

    @GetMapping("/delete/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationRepository.deleteById(id);
        return "redirect:/donation/donationconfirmation";
    }

    @GetMapping("/donationconfirmation")
    public String confirmation() {
        return "donationConfirmation";
    }


}
