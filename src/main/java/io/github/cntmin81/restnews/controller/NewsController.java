package io.github.cntmin81.restnews.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.ws.Response;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cntmin81.restnews.dto.NewsDto;
import io.github.cntmin81.restnews.model.News;
import io.github.cntmin81.restnews.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<NewsDto> getAllNews() {
        return newsService.getAll().stream().map(
            news -> modelMapper.map(news, NewsDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable(name = "id") Long id) {
        News news = newsService.getOneById(id);

        NewsDto newsResponse = modelMapper.map(news, NewsDto.class);
        return ResponseEntity.ok().body(newsResponse);
    }

    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsDto newsDto) {
        // convert DTO to entity
        News newsRequest = modelMapper.map(newsDto, News.class);
        News news = newsService.create(newsRequest);

        // converto entity to DTO
        NewsDto newsResponse = modelMapper.map(news, NewsDto.class);
        return new ResponseEntity<NewsDto>(newsResponse, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews(@PathVariable(name = "id") Long id, @RequestBody NewsDto newsDto) {
        // converto DTO to entity
        News newsRequest = modelMapper.map(newsDto, News.class);

        News news = newsService.update(id, newsRequest);

        // converto entity to DTO
        NewsDto newsResponse = modelMapper.map(news, NewsDto.class);

        return ResponseEntity.ok().body(newsResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable(name = "id") Long id) {
        newsService.delete(id);
        return ResponseEntity.ok("ID [" + id + "] is deleted");
    } 
}
