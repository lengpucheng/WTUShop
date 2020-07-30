package cn.hll520.wtuShop.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lpc
 * @create 2020-07-27-8:53
 */

@Component
public class GoodsComplex extends Goods{
    @Autowired
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "GoodsComplex{" +
                "category=" + category +
                "} " + super.toString();
    }
}
