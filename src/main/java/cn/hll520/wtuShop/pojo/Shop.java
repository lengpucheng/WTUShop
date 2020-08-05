package cn.hll520.wtuShop.pojo;

public class Shop {
    private Integer shoppingId;

    private Integer userId;

    private Integer goodsId;

    private Integer goodsSum;

    private Goods goods;

    public Integer getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Integer shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsSum() {
        return goodsSum;
    }

    public void setGoodsSum(Integer goodsSum) {
        this.goodsSum = goodsSum;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shoppingId=" + shoppingId +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", goodsSum=" + goodsSum +
                ", goods=" + goods +
                '}';
    }
}