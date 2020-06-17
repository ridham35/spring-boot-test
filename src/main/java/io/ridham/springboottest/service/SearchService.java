package io.ridham.springboottest.service;


import io.ridham.springboottest.model.Character;

public interface SearchService {
    Character searchCharacter(String character_name);

    void removeExpiredValue();
}
