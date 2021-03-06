package ru.sstu.sharing.forms.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sstu.sharing.forms.BalanceReplenishmentForm;
import ru.sstu.sharing.forms.CategoryAddForm;
import ru.sstu.sharing.services.CategoryService;

@Component
public class BalanceReplenishmentFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return BalanceReplenishmentForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        BalanceReplenishmentForm form = (BalanceReplenishmentForm) target;

        try {
            if (Integer.parseInt(form.getReplenishment()) < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("replenishment", "input.number", "Invalid type");
        }


    }

}
