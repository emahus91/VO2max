package com.example.sam.vo2max;
/**
 * Created by Aran & Samuel on 2017-02-11.
 */

public class DataProvider {

    private String name;
    private String power3;
    private String power4;
    private String power5;
    private String vo2max_liter_3;
    private String vo2max_liter_4;
    private String vo2max_liter_5;
    private String vo2max_mliter_3;
    private String vo2max_mliter_4;
    private String vo2max_mliter_5;
    private String antal_vandor_3;
    private String antal_vandor_4;
    private String antal_vandor_5;

    public DataProvider(String name, String power3, String power4,
                        String power5, String vo2max_liter_3, String vo2max_liter_4,
                        String vo2max_liter_5,String vo2max_mliter_3, String vo2max_mliter_4,
                        String vo2max_mliter_5, String antal_vandor_3,String antal_vandor_4,String antal_vandor_5)
    {
        this.name = name;
        this.power3=power3;
        this.power4=power4;
        this.power5=power5;
        this.vo2max_liter_3 = vo2max_liter_3;
        this.vo2max_liter_4 = vo2max_liter_4;
        this.vo2max_liter_5 = vo2max_liter_5;
        this.vo2max_mliter_3 = vo2max_mliter_3;
        this.vo2max_mliter_4 = vo2max_mliter_4;
        this.vo2max_mliter_5 = vo2max_mliter_5;
        this.antal_vandor_3 = antal_vandor_3;
        this.antal_vandor_4 = antal_vandor_4;
        this.antal_vandor_5 = antal_vandor_5;
    }


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

    public String getVo2max_liter_3() {
        return vo2max_liter_3;
    }
    public void setVo2max_liter_3(String vo2max_liter_3) {
        this.vo2max_liter_3 = vo2max_liter_3;
    }

    public String getVo2max_liter_4() {
        return vo2max_liter_4;
    }
    public void setVo2max_liter_4(String vo2max_liter_4) {
        this.vo2max_liter_4 = vo2max_liter_4;
    }

    public String getVo2max_liter_5() {
        return vo2max_liter_5;
    }
    public void setVo2max_liter_5(String vo2max_liter_5) {
        this.vo2max_liter_5 = vo2max_liter_5;
    }


    public String getVo2max_mliter_3() {
        return vo2max_mliter_3;
    }
    public void setVo2max_mliter_3(String vo2max_mliter_3) {
        this.vo2max_mliter_3 = vo2max_mliter_3;
    }

    public String getVo2max_mliter_4() {
        return vo2max_mliter_4;
    }
    public void setVo2max_mliter_4(String vo2max_mliter_4) {
        this.vo2max_mliter_4 = vo2max_mliter_4;
    }

    public String getVo2max_mliter_5() {
        return vo2max_mliter_5;
    }
    public void setVo2max_mliter_5(String vo2max_mliter_5) {
        this.vo2max_mliter_5 = vo2max_mliter_5;
    }

    public String getAntal_vandor_3() {
        return antal_vandor_3;
    }
    public void setAntal_vandor_3(String antal_vandor_3) {
        this.antal_vandor_3 = antal_vandor_3;
    }

    public String getAntal_vandor_4() {
        return antal_vandor_4;
    }
    public void setAntal_vandor_4(String antal_vandor_4) {
        this.antal_vandor_4 = antal_vandor_4;
    }

    public String getAntal_vandor_5() {
        return antal_vandor_5;
    }
    public void setAntal_vandor_5(String antal_vandor_5) {
        this.antal_vandor_5 = antal_vandor_5;
    }

}
