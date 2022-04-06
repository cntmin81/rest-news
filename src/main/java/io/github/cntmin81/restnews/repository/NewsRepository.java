package io.github.cntmin81.restnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.cntmin81.restnews.model.News;

public interface NewsRepository extends JpaRepository<News, Long>{
    
}
