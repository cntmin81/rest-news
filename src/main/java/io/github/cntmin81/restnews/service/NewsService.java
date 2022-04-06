package io.github.cntmin81.restnews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.cntmin81.restnews.exception.NewsNotFoundException;
import io.github.cntmin81.restnews.model.News;
import io.github.cntmin81.restnews.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repo;

    // Get all
    protected List<News> getAll() {
        return repo.findAll();
    }

    // Get one by id
    protected News getOneById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
    }

    // Create
    protected News create(News news) {
        return repo.save(news);
    }

    // Update
    protected News update(Long id, News newNews) {
        return repo
                .findById(id)
                .map(
                        (news) -> {
                            if (newNews.getTitle() != null) {
                                news.setTitle(newNews.getTitle());
                            }
                            if (newNews.getContent() != null) {
                                news.setContent(newNews.getContent());
                            }
                            return repo.save(news);
                        })
                .orElseGet(
                        () -> {
                            newNews.setId(id);
                            return repo.save(newNews);
                        });
    }

    // Delete
    protected void delete(Long id) {
        repo.deleteById(id);
    }
}
