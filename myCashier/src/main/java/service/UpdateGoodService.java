package service;

import dao.UpdateGood;
import entity.Goods;

public class UpdateGoodService {
    public Goods getGoods(int goodsID) {
        UpdateGood ob = new UpdateGood();
        return ob.getGoods(goodsID);
    }

    public boolean modify(Goods goods) {
        UpdateGood ob = new UpdateGood();
        return ob.modify(goods);
    }
}
