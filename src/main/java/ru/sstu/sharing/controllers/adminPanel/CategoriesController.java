package ru.sstu.sharing.controllers.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.sstu.sharing.domain.entities.User;
import ru.sstu.sharing.exceptions.InvalidCurrentPassword;
import ru.sstu.sharing.exceptions.UserDoesNotExist;
import ru.sstu.sharing.forms.CategoryAddForm;
import ru.sstu.sharing.forms.FullNameChangeForm;
import ru.sstu.sharing.forms.PasswordChangeForm;
import ru.sstu.sharing.forms.validators.CategoryAddFormValidator;
import ru.sstu.sharing.forms.validators.UserRegistrationFormValidator;
import ru.sstu.sharing.services.CategoryService;
import ru.sstu.sharing.services.UserService;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/adminpanel/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryAddFormValidator categoryValidator;

    @InitBinder("categoryAddForm")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(this.categoryValidator);
    }

    @ModelAttribute
    public void addCurrentSection(Model model) {
        model.addAttribute("currentSection", "categories");
    }

    @GetMapping
    public String getProfile(Model model, CategoryAddForm categoryAddForm) {
        model.addAttribute("categoryAddForm", categoryAddForm);
        model.addAttribute("catalog", this.categoryService.getCatalog().orElse(null));
        return "adminPanel/categories";
    }


    @PostMapping
    public String addCategory(Model model, @Valid @ModelAttribute("categoryAddForm") CategoryAddForm categoryAddForm,
                              BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("catalog", this.categoryService.getCatalog().orElse(null));
            return "adminPanel/categories";
        }
        this.categoryService.addCategory(categoryAddForm);
        return "redirect:/adminpanel/categories";
    }


}
