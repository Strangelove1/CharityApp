package pl.coderslab.charity.models.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.category.CategoryRepository;
import pl.coderslab.charity.models.institution.InstitutionRepository;

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



    @GetMapping("/form1")
    public String form1a(Model model){
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
    return "donationform1";
    }

    @PostMapping("/form1")
    public String form1b(@ModelAttribute Donation donation){
        donationRepository.save(donation);
        return  "redirect:/donation/donationconfirmation";
    }

    @GetMapping("/donationconfirmation")
    public String confirmation(){
        return "donationConfirmation";
    }


}
