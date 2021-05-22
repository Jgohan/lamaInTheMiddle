package ru.sstu.sharing.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import ru.sstu.sharing.domain.entities.User;
import ru.sstu.sharing.exceptions.InvalidCurrentPassword;
import ru.sstu.sharing.exceptions.InvalidLogin;
import ru.sstu.sharing.exceptions.UserDoesNotExist;
import ru.sstu.sharing.forms.*;

import java.util.Locale;
import java.util.Optional;

public interface UserService {

    boolean isUserWithLoginExist(String login);

    boolean isUserWithEmailExist(String email);

    boolean isUserWithEmailExistAndEnabled(String email);

    void createUserFromRegistrationForm(UserRegistrationForm userForm, Locale locale);

    void confirmUserEmail(String token) throws UserDoesNotExist;

    void recoverUserPassword(PasswordRecoveryForm passwordRecoveryForm, Locale locale) throws UserDoesNotExist;

    Optional<User> getUserById(long id);

    void changeUserFullName(Authentication authentication, FullNameChangeForm fullNameChangeForm) throws UserDoesNotExist;

    Optional<User> getUserFromAuthentication(Authentication authentication);

    void changeUserPassword(Authentication authentication, PasswordChangeForm passwordChangeForm) throws UserDoesNotExist, InvalidCurrentPassword;

    Page<User> getPageUser(Pageable pageable);

    void changeUser(UserChangeForm userChangeForm, long id) throws InvalidLogin, UserDoesNotExist;

    void deleteUser(Long id);

    void balanceReplenishment(BalanceReplenishmentForm form, Authentication authentication) throws UserDoesNotExist;
}
