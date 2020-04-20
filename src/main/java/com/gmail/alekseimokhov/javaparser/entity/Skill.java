package com.gmail.alekseimokhov.javaparser.entity;

import java.util.Arrays;
import java.util.List;

public class Skill {
    private String skillName;
    private static final List<String>values = Arrays.asList(new String[] {"hibernate", "spring", "postgres", "cassandra", "junit", "java 8"
            , "oracle", "docker", "javascript", "java core", "collections", "concurrency"} );

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Skill() {
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public static List<String> getValues() {
        return values;
    }
}

