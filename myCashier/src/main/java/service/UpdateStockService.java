package service;

import dao.UpdateStock;
import entity.Goods;

public class UpdateStockService {
    public boolean updateStock(Goods goods, int buyGoodsNum ) {
        UpdateStock update = new UpdateStock();
        boolean flg = update.updateStock(goods,buyGoodsNum);
        return flg;
    }
}
