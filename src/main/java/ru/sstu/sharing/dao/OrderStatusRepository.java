package ru.sstu.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sstu.sharing.domain.entities.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    OrderStatus findByStatus(String status);

}
