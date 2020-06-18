package io.ridham.springboottest.service;

import io.ridham.springboottest.AppProperties;
import io.ridham.springboottest.model.Character;
import io.ridham.springboottest.model.ResponseCharacter;
import io.ridham.springboottest.model.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AppProperties appProperties;

    private Character[] list = new Character[15];
//    boolean isListFull = false;
    public Character[] getList() {
        return list;
    }

    @Override
    public Character searchCharacter(String character_name) {

        // find character user searched for
        for(Character temp : list) {
            if(temp!=null && character_name.equalsIgnoreCase(temp.getName())) {
                if (spaceLeft() != 0) {
                    return temp;
                }
                removeLeastRecentlyUsedValue();
                return temp;
            }
        }

        callAPIs(character_name);

        for(Character temp : list) {
            if(character_name.equalsIgnoreCase(temp.getName())) {
                return temp;
            }
        }
        throw new RuntimeException("USER_NOT_FOUND!");
    }

    @Override
    public void callAPIs(String character_name) {
        // create a list to save all returned character in arraylist
        List<Character> characterList = new ArrayList<Character>();

        // call all 3 apis in parallel
        ResponseObj responseObj1 = restTemplate.getForObject(appProperties.getUri1(), ResponseObj.class);
        ResponseObj responseObj2 = restTemplate.getForObject(appProperties.getUri1(), ResponseObj.class);
        ResponseObj responseObj3 = restTemplate.getForObject(appProperties.getUri1(), ResponseObj.class);

        // extract character objects from result and save it to arraylist
        for (ResponseCharacter tempCharacter: responseObj1.getCharacter()){
            characterList.add(new Character(tempCharacter.getName(),
                    tempCharacter.getMax_power(), Calendar.getInstance().getTimeInMillis()));
        }
        for (ResponseCharacter tempCharacter: responseObj2.getCharacter()){
            characterList.add(new Character(tempCharacter.getName(),
                    tempCharacter.getMax_power(), Calendar.getInstance().getTimeInMillis()));
        }
        for (ResponseCharacter tempCharacter: responseObj3.getCharacter()){
            characterList.add(new Character(tempCharacter.getName(),
                    tempCharacter.getMax_power(), Calendar.getInstance().getTimeInMillis()));
        }

        if (spaceLeft() == 0) {
            removeLowestPoweredHeros();
        }

        // sort arraylist by power level
        characterList.sort(Comparator.comparing(Character::getMax_power));

        int i = 1;
        for (Character temp: characterList){
            if (character_name.equalsIgnoreCase(temp.getName())){
                list[0] = temp;
            }
            else{
                if(spaceLeft() <= i){
                    list[i] = new Character(temp.getName(), temp.getMax_power(), temp.getLastSearched());
                    i++;
                }
            }
        }
//        searchCharacter(character_name);
    }

    // return false if data-structure is not full
    @Override
    public int spaceLeft() {
        int space = 0;
        for(Character temp : list) {
            if(temp==null) {
                space++;
            }
        }
        return space;
    }

    // remove all characters which have been fetched more than 10 seconds ago
    @Override
    public void removeExpiredValue() {
        int i = 0;
        for(Character temp : list) {
            if(temp!=null && (Calendar.getInstance().getTimeInMillis() -
                    temp.getLastSearched()) >= 10000) {
                list[i] = null;
            }
            i++;
        }
    }

    // remove least recently used heroes
    @Override
    public void removeLeastRecentlyUsedValue() {
        Arrays.sort(list, Comparator.comparing(Character::getLastSearched));
        list[14] = null;
//        isListFull = false;
    }

    // remove lowest powered heroes
    @Override
    public void removeLowestPoweredHeros() {
//        Arrays.sort(list, Comparator.comparing(Character::getMax_power));
        list[14] = null;

//        isListFull = false;
    }

}
