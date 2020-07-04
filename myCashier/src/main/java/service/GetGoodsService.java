package service;

import dao.GetGoods;
import entity.Goods;

public class GetGoodsService {
    public Goods getGoods(int goodsID) {
        GetGoods get = new GetGoods();
        Goods good = get.getGoods(goodsID);
        return good;
    }
}
