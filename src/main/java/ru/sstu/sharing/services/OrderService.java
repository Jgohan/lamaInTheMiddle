package ru.sstu.sharing.services;

import org.springframework.security.core.Authentication;
import ru.sstu.sharing.domain.entities.Order;
import ru.sstu.sharing.domain.entities.Product;
import ru.sstu.sharing.exceptions.ProductDoesNotExist;
import ru.sstu.sharing.exceptions.UserDoesNotExist;

public interface OrderService {
    void addInBasket(Long id, Authentication authentication) throws UserDoesNotExist, ProductDoesNotExist;

    Order getBasket(Authentication authentication) throws UserDoesNotExist;

    Order createOrder(Authentication authentication) throws UserDoesNotExist;

    void changeQuantity(Integer quantity, Long productId, Authentication authentication) throws UserDoesNotExist, ProductDoesNotExist;

    void deleteProduct(Long productId, Authentication authentication) throws UserDoesNotExist, ProductDoesNotExist;

    void deleteProductFromBasket(Product product);

    boolean hasOrderWithProduct(Product product);
}
