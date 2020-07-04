package service;

import dao.GoodsBrowse;
import entity.Goods;

import java.util.List;

public class GoodsBrowseService {
    public List<Goods> browse(){
        GoodsBrowse browse = new GoodsBrowse();
        List<Goods> goodsList = browse.browse();
        return goodsList;
    }
}
