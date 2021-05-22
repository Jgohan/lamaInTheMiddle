package ru.sstu.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sstu.sharing.domain.entities.Role;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
