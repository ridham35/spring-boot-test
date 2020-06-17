package io.ridham.springboottest.service;

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

    private Character[] list = new Character[15];
    public Character[] getList() {
        return list;
    }

    @Override
    public Character searchCharacter(String character_name) {
        for(Character temp : list) {
            if(temp!=null && character_name.equalsIgnoreCase(temp.getName())) {
                if (!isArrayFull()) {
                    return temp;
                }
                removeLeastRecentlyUsedValue();
                return temp;
            }
        }

        callAPIs(character_name);
    }

    public boolean isArrayFull() {
        for(Character temp : list) {
            if(temp==null) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void removeExpiredValue() {
        for(Character temp : list) {
            if((Calendar.getInstance().getTimeInMillis() -
                    temp.getLastSearched()) >= 10000) {
                temp = null;
            }
        }
    }

    @Override
    public void removeLeastRecentlyUsedValue() {
        Arrays.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return c1.getLastSearched().compareTo(c2.getLastSearched());
            }
        });
        list[14] = null;
    }

    @Override
    public void callAPIs(String character_name) {
        List<Character> characterList = new ArrayList<Character>();

        ResponseObj responseObj1 = restTemplate.getForObject("http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b", ResponseObj.class);
//        ResponseObj responseObj2 = restTemplate.getForObject("", ResponseObj.class);
//        ResponseObj responseObj3 = restTemplate.getForObject("", ResponseObj.class);
        for (ResponseCharacter tempCharacter: responseObj1.getCharacter()){
            characterList.add(new Character(tempCharacter.getName(),
                    tempCharacter.getMax_power(), Calendar.getInstance().getTimeInMillis()));
        }

        if (isArrayFull()) {

        }
        int i = 0;
        for (Character temp: characterList){
            list[i] = new Character(temp.getName(), temp.getMax_power(), temp.getLastSearched());
            i++;
        }
        searchCharacter(character_name);
    }
}
