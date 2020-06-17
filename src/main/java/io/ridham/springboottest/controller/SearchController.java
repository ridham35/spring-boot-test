package io.ridham.springboottest.controller;

import io.ridham.springboottest.model.Character;
import io.ridham.springboottest.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    SearchServiceImpl searchService;

    @GetMapping(value = "/{searchString}")
    public Character searchCharacter(@PathVariable("searchString") String searchString) {
        searchService.removeExpiredValue();
        return searchService.searchCharacter(searchString);
    }

    @GetMapping(value = "/list")
    public Character[] seeList() {
        return searchService.getList();
    }
}
