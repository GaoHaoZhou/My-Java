package com.glp.comment;

import lombok.*;

@Getter
@ToString
public enum TitleSelect {
    PROFESSOR(1,"教授"),ASSOCIATE(0,"副教授");
    private int flg;
    private String str;

    TitleSelect(int flg, String str) {
        this.flg = flg;
        this.str = str;
    }
    public static TitleSelect valueOf(int flg){
        for(TitleSelect title: values()){
            if(title.flg == flg){
                return title;
            }
        }
        throw new RuntimeException("orderStatus flg"+flg+"not found!");
    }

    public static TitleSelect parseInt(String str){
        for(TitleSelect title: values()){
            if(title.str.equals(str)){
                return title;
            }
        }
        throw new RuntimeException("orderStatus flg"+str+"not found!");
    }
}
