package cn.hll520.wtuShop.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class KillGoods extends Goods {
    private Integer killId;

    private Integer goodsId;

    private Integer stockCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateEnd;

    private Integer killPrice;

    public Integer getKillId() {
        return killId;
    }

    public void setKillId(Integer killId) {
        this.killId = killId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getKillPrice() {
        return killPrice;
    }

    public void setKillPrice(Integer killPrice) {
        this.killPrice = killPrice;
    }

    @Override
    public String toString() {
        return "KillGoods{" +
                "killId=" + killId +
                ", goodsId=" + goodsId +
                ", stockCount=" + stockCount +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", killPrice=" + killPrice +
                "} " + super.toString();
    }
}