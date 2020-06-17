package io.ridham.springboottest.model;

import java.util.Arrays;

public class ResponseObj {

    private String name;
    private ResponseCharacter[] character = new ResponseCharacter[6];

    public ResponseObj() {
    }

    public ResponseObj(String name, ResponseCharacter[] character) {
        this.name = name;
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public ResponseCharacter[] getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "ResponseObj{" +
                "name='" + name + '\'' +
                ", character=" + Arrays.toString(character) +
                '}';
    }
}
