package ru.sstu.sharing.services;

import org.springframework.data.domain.Page;
import ru.sstu.sharing.domain.entities.News;
import ru.sstu.sharing.forms.NewsAddForm;

import java.io.IOException;

public interface NewsService {
    Page<News> getPageProduct(int page);

    void createNewsFromAddNewsForm(NewsAddForm newsAddForm) throws IOException;

    Page<News> getTenNewsForNews();
}
