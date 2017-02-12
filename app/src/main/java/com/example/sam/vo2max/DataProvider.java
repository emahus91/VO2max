package com.example.sam.vo2max;

/**
 * Created by aranrashid on 2017-02-11.
 */

public class DataProvider {

    private String name;
    private String age;
    private String weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public DataProvider(String name, String age, String weight)
    {

        this.name = name;
        this.age = age;
        this.weight = weight;


    }
}
