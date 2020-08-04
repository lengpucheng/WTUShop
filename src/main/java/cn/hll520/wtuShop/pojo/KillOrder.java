package cn.hll520.wtuShop.pojo;

public class KillOrder {
    private Integer killOrderId;

    private Integer killUserId;

    private Integer killGoodsId;

    private Integer orderId;

    public Integer getKillOrderId() {
        return killOrderId;
    }

    public void setKillOrderId(Integer killOrderId) {
        this.killOrderId = killOrderId;
    }

    public Integer getKillUserId() {
        return killUserId;
    }

    public void setKillUserId(Integer killUserId) {
        this.killUserId = killUserId;
    }

    public Integer getKillGoodsId() {
        return killGoodsId;
    }

    public void setKillGoodsId(Integer killGoodsId) {
        this.killGoodsId = killGoodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "KillOrder{" +
                "killOrderId=" + killOrderId +
                ", killUserId=" + killUserId +
                ", killGoodsId=" + killGoodsId +
                ", orderId=" + orderId +
                '}';
    }
}