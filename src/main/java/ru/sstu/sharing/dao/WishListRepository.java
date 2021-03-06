package ru.sstu.sharing.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.sstu.sharing.domain.entities.Product;
import ru.sstu.sharing.domain.entities.User;
import ru.sstu.sharing.domain.entities.WishList;


public interface WishListRepository extends JpaRepository<WishList, Long> {
    Page<WishList> findAllByUser(User user, Pageable pageable);

    int countByProductAndUser(Product product, User user);

    @Transactional
    void deleteByProductAndUser(Product product, User user);

    @Transactional
    void deleteAllByProduct(Product product);

}
