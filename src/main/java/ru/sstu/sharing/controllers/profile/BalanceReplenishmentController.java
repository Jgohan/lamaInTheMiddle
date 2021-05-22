package ru.sstu.sharing.controllers.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sstu.sharing.exceptions.UserDoesNotExist;
import ru.sstu.sharing.forms.BalanceReplenishmentForm;
import ru.sstu.sharing.forms.validators.BalanceReplenishmentFormValidator;
import ru.sstu.sharing.forms.validators.ProductAddFormValidator;
import ru.sstu.sharing.services.UserService;

import javax.validation.Valid;

@Controller
public class BalanceReplenishmentController {

    @Autowired
    UserService userService;

    @Autowired
    private BalanceReplenishmentFormValidator balanceValidator;

    @InitBinder("balanceReplenishmentForm")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(this.balanceValidator);
    }

    @GetMapping("/profile/replenishment")
    public String balanceReplenishment() {return "profile/replenishment";}

    @ModelAttribute
    public void addCurrentPage(Model model, BalanceReplenishmentForm balanceReplenishmentForm) {
        model.addAttribute("balanceReplenishmentForm", balanceReplenishmentForm);
        model.addAttribute("currentSection", "profile");
    }

    @PostMapping("/profile/replenishment")
    public String replenishmentOfBalance(Model model, @Valid @ModelAttribute("balanceReplenishmentForm") BalanceReplenishmentForm balanceReplenishmentForm,
                                         BindingResult binding, Authentication authentication) {
        if (binding.hasErrors()) {
            return "profile/replenishment";
        }
        try {
            this.userService.balanceReplenishment(balanceReplenishmentForm, authentication);
            return "profile/replenishment";
        } catch (UserDoesNotExist e) {
            return "redirect:/error";
        }

    }
}
