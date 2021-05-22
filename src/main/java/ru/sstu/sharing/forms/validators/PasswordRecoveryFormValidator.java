package ru.sstu.sharing.forms.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sstu.sharing.forms.PasswordRecoveryForm;
import ru.sstu.sharing.services.UserService;

@Component
public class PasswordRecoveryFormValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordRecoveryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PasswordRecoveryForm form = (PasswordRecoveryForm)target;
		if(!this.userService.isUserWithEmailExistAndEnabled(form.getEmail())) {
			errors.rejectValue("email", "passwordRecovery.email.exists", "User with email no exists");
		}

	}

}
