package ru.sstu.sharing.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.sstu.sharing.domain.entities.Product;
import ru.sstu.sharing.domain.entities.User;
import ru.sstu.sharing.exceptions.ProductDoesNotExist;
import ru.sstu.sharing.services.ProductService;
import ru.sstu.sharing.services.UserService;

import java.io.Serializable;
import java.util.Optional;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        boolean permission = false;
        try {
            long id = Long.parseLong(serializable.toString());
            Optional<User> optionalUser = this.userService.getUserFromAuthentication(authentication);
            if (optionalUser.isPresent()) {
                switch (o.toString()) {
                    case "WRITE":
                        permission = this.hasPermissionWrite(optionalUser.get(), id, s);
                        break;
                    default:
                }
            }
        } catch (NumberFormatException e) {
        }
        return permission;
    }

    private boolean hasPermissionWrite(User user, long id, String type) {
        boolean permission = false;
        switch (type) {
            case "Product":
                permission = this.hasPermissionWriteProduct(user, id);
                break;
            default:
        }
        return permission;
    }

    private boolean hasPermissionWriteProduct(User user, long id) {
        boolean permission = false;
        try {
            Product product = this.productService.getProductByIdAndSeller(id, user);
            permission = true;
        } catch (ProductDoesNotExist e) {
        }
        return permission;
    }
}
