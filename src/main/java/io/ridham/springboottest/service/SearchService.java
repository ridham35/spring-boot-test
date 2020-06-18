package io.ridham.springboottest.service;


import io.ridham.springboottest.model.Character;

import java.util.List;

public interface SearchService {
    Character searchCharacter(String character_name);
    void callAPIs(String character_name);
    int spaceLeft();
    void removeExpiredValue();
    void removeLeastRecentlyUsedValue();
    void removeLowestPoweredHeros();
}
