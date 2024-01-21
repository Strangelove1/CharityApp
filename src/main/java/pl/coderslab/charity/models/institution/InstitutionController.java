package pl.coderslab.charity.models.institution;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/institution")
public class InstitutionController {
    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/form")
    public String form(Model model) {
        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        return "institutionForm";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute Institution institution) {
        institutionRepository.save(institution);
        return "redirect:/institution/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "institutionList";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institution not found")); // You can replace this exception with a more specific one

        model.addAttribute("institution", institution);
        return "updateInstitutionForm";
    }

    @PostMapping("/update")
    public String updateInstitution(@ModelAttribute Institution institution) {
        institutionRepository.save(institution);
        return "redirect:/institution/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable Long id) {
        institutionRepository.deleteById(id);
        return "redirect:/institution/list";
    }

}
