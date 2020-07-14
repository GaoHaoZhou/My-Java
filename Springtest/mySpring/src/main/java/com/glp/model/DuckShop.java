package com.glp.model;

import java.util.List;

public class DuckShop {

    private List<Duck> ducks;

    @Override
    public String toString() {
        return "DuckShop{" +
                "ducks=" + ducks +
                '}';
    }

    public List<Duck> getDucks() {
        return ducks;
    }

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }
}
