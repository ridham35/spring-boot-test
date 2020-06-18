package io.ridham.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Autowired
    private Environment env;

    public String getUri1() {
        return env.getProperty("uri1");
    }

    public String getUri2() {
        return env.getProperty("uri2");
    }

    public String getUri3() {
        return env.getProperty("uri3");
    }

}
