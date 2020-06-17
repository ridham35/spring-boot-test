package io.ridham.springboottest.model;

public class Character {
    private String name;
    private Integer max_power;
    private Long lastSearched;

    public Character() {
    }

    public Character(String name, Integer max_power, Long lastSearched) {
        this.name = name;
        this.max_power = max_power;
        this.lastSearched = lastSearched;
    }

    public String getName() {
        return name;
    }

    public Integer getMax_power() {
        return max_power;
    }

    public Long getLastSearched() {
        return lastSearched;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMax_power(Integer max_power) {
        this.max_power = max_power;
    }

    public void setLastSearched(Long lastSearched) {
        this.lastSearched = lastSearched;
    }

    @Override
    public String toString() {
        return "StoredCharacter{" +
                "name='" + name + '\'' +
                ", max_power=" + max_power +
                ", lastSearched=" + lastSearched +
                '}';
    }
}
