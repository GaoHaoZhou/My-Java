package com.glp.comment;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum GenderSelect {
    MAIL(0,"男"),FEMAIL(1,"女");
    private int flg;
    private String str;

    GenderSelect(int flg, String str) {
        this.flg = flg;
        this.str = str;
    }
    public static GenderSelect valueOf(Integer flg){
        for(GenderSelect gender: values()){
            if(gender.flg == flg){
                return gender;
            }
        }
        throw new RuntimeException("orderStatus flg"+flg+"not found!");
    }

    public static GenderSelect parseInt(String str){
        for(GenderSelect gender: values()){
            if(gender.str.equals(str)){
                return gender;
            }
        }
        throw new RuntimeException("orderStatus flg"+str+"not found!");
    }
}
