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
                return temp;
            }
        }
        List<Character> newList = callAPIs();
        return null;
    }

    @Override
    public void removeExpiredValue() {
        for(Character temp : list) {
            if(Calendar.getInstance().getTimeInMillis() -
                    temp.getLastSearched() == 1000) {
                temp = null;
            }
        }
    }


    public List<Character> callAPIs() {

        List<Character> characterList = new ArrayList<Character>();

//        RestTemplate restTemplate = new RestTemplate();

        ResponseObj responseObj1 = restTemplate.getForObject("http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b", ResponseObj.class);
//        ResponseObj responseObj2 = restTemplate.getForObject("", ResponseObj.class);
//        ResponseObj responseObj3 = restTemplate.getForObject("", ResponseObj.class);

        for (ResponseCharacter tempCharacter: responseObj1.getCharacter()){

            characterList.add(new Character(tempCharacter.getName(),
                    tempCharacter.getMax_power(), Calendar.getInstance().getTimeInMillis()));
        }
//        System.out.println(Arrays.toString(characterList.toArray()));
        int i = 0;
        for (Character temp: characterList){
            list[i] = new Character(temp.getName(), temp.getMax_power(), temp.getLastSearched());
            i++;
        }
        return null;
    }
}
