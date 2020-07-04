package service;

import dao.GoodsOut;

public class GoodsOutService {
    public int goodsOut(String idstr){
        GoodsOut out = new GoodsOut();
        int ret = out.goodsOut(idstr);
        return ret;
    }
}
