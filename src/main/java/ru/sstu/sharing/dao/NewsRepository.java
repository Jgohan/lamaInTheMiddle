package ru.sstu.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sstu.sharing.domain.entities.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
}
