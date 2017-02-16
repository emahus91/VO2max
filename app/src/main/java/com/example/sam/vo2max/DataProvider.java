package com.example.sam.vo2max;
/**
 * Created by Aran & Samuel on 2017-02-11.
 */

public class DataProvider {

    private String name;
    private String age;
    private String weight;
    private String power5;
    private String vo2max5;

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

    public String getPower5() {
        return power5;
    }
    public void setPower5(String power5) {
        this.name = power5;
    }

    public String getVo2max5() {
        return vo2max5;
    }
    public void setVo2max5(String vo2max5) {
        this.name = vo2max5;
    }


    public DataProvider(String name, String age, String weight, String power5, String vo2max5)
    {

        this.name = name;
        this.age = age;
        this.weight = weight;
        this.power5=power5;
        this.vo2max5=vo2max5;


    }
}
