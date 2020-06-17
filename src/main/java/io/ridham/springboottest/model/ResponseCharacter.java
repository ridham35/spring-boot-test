package io.ridham.springboottest.model;

public class ResponseCharacter {

    private String name;
    private Integer max_power;

    public ResponseCharacter() {
    }

    public ResponseCharacter(String name, Integer max_power) {
        this.name = name;
        this.max_power = max_power;
    }

    public String getName() {
        return name;
    }

    public Integer getMax_power() {
        return max_power;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", max_power=" + max_power +
                '}';
    }
}
