package cn.hll520.wtuShop.pojo;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-27-8:57
 */
public class CategoryComplex extends Category{
    private List<Goods> goodsItems;

    public List<Goods> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<Goods> goodsItems) {
        this.goodsItems = goodsItems;
    }

    @Override
    public String toString() {
        return "CategoryComplex{" +
                "goodsItems=" + goodsItems +
                "} " + super.toString();
    }
}
