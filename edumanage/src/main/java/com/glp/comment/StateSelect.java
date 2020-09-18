package com.glp.comment;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum StateSelect {
    UNSUBMIT(0,"未提交"),UNCHECK(1,"未审核"),CHECK(2,"审核"),PASS(3,"通过"),UNPASS(4,"未通过");

    private int flg;
    private String str;

    StateSelect(int flg, String str) {
        this.flg = flg;
        this.str = str;
    }
    public static StateSelect valueOf(int flg){
        for(StateSelect state: values()){
            if(state.flg == flg){
                return state;
            }
        }
        throw new RuntimeException("orderStatus flg"+flg+"not found!");
    }

    public static StateSelect parseInt(String str){
        for(StateSelect state: values()){
            if(state.str.equals(str)){
                return state;
            }
        }
        throw new RuntimeException("State flg"+str+"not found!");
    }
}
