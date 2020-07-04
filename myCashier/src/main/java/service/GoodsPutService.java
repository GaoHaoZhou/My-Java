package service;

import dao.GoodsPut;

public class GoodsPutService {
    public int put(String name,String stock,String introduce,String unit,String price,String discount){
        GoodsPut put = new GoodsPut();
        int ret = put.put(name,stock,introduce,unit,price,discount);
        return ret;
    }
}
