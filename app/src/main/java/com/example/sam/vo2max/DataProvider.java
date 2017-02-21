package com.example.sam.vo2max;
/**
 * Created by Aran & Samuel on 2017-02-11.
 */

public class DataProvider {

    private String name;
    private String power3;
    private String power4;
    private String power5;
    private String vo2max3;
    private String vo2max4;
    private String vo2max5;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getPower3() {
        return power3;
    }
    public void setPower3(String power3) {
        this.power3 = power3;
    }

    public String getPower4() {
        return power4;
    }
    public void setPower4(String power4) {
        this.power4 = power4;
    }

    public String getPower5() {
        return power5;
    }
    public void setPower5(String power5) {
        this.name = power5;
    }

    public String getVo2max3() {
        return vo2max3;
    }
    public void setVo2max3(String vo2max3) {
        this.vo2max3 = vo2max3;
    }

    public String getVo2max4() {
        return vo2max4;
    }
    public void setVo2max4(String vo2max4) {
        this.vo2max4 = vo2max4;
    }

    public String getVo2max5() {
        return vo2max5;
    }
    public void setVo2max5(String vo2max5) {
        this. vo2max5 = vo2max5;
    }


    public DataProvider(String name, String power3,String power4,String power5, String vo2max3, String vo2max4, String vo2max5)
    {

        this.name = name;
        this.power3=power3;
        this.power4=power4;
        this.power5=power5;
        this.vo2max3=vo2max3;
        this.vo2max4=vo2max4;
        this.vo2max5=vo2max5;


    }
}
