package cn.hll520.wtuShop.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KillGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KillGoodsExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andKillIdIsNull() {
            addCriterion("kill_id is null");
            return (Criteria) this;
        }

        public Criteria andKillIdIsNotNull() {
            addCriterion("kill_id is not null");
            return (Criteria) this;
        }

        public Criteria andKillIdEqualTo(Integer value) {
            addCriterion("kill_id =", value, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdNotEqualTo(Integer value) {
            addCriterion("kill_id <>", value, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdGreaterThan(Integer value) {
            addCriterion("kill_id >", value, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("kill_id >=", value, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdLessThan(Integer value) {
            addCriterion("kill_id <", value, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdLessThanOrEqualTo(Integer value) {
            addCriterion("kill_id <=", value, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdIn(List<Integer> values) {
            addCriterion("kill_id in", values, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdNotIn(List<Integer> values) {
            addCriterion("kill_id not in", values, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdBetween(Integer value1, Integer value2) {
            addCriterion("kill_id between", value1, value2, "killId");
            return (Criteria) this;
        }

        public Criteria andKillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("kill_id not between", value1, value2, "killId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andStockCountIsNull() {
            addCriterion("stock_count is null");
            return (Criteria) this;
        }

        public Criteria andStockCountIsNotNull() {
            addCriterion("stock_count is not null");
            return (Criteria) this;
        }

        public Criteria andStockCountEqualTo(Integer value) {
            addCriterion("stock_count =", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotEqualTo(Integer value) {
            addCriterion("stock_count <>", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThan(Integer value) {
            addCriterion("stock_count >", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_count >=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThan(Integer value) {
            addCriterion("stock_count <", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThanOrEqualTo(Integer value) {
            addCriterion("stock_count <=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountIn(List<Integer> values) {
            addCriterion("stock_count in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotIn(List<Integer> values) {
            addCriterion("stock_count not in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountBetween(Integer value1, Integer value2) {
            addCriterion("stock_count between", value1, value2, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_count not between", value1, value2, "stockCount");
            return (Criteria) this;
        }

        public Criteria andDateStartIsNull() {
            addCriterion("date_start is null");
            return (Criteria) this;
        }

        public Criteria andDateStartIsNotNull() {
            addCriterion("date_start is not null");
            return (Criteria) this;
        }

        public Criteria andDateStartEqualTo(Date value) {
            addCriterion("date_start =", value, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartNotEqualTo(Date value) {
            addCriterion("date_start <>", value, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartGreaterThan(Date value) {
            addCriterion("date_start >", value, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartGreaterThanOrEqualTo(Date value) {
            addCriterion("date_start >=", value, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartLessThan(Date value) {
            addCriterion("date_start <", value, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartLessThanOrEqualTo(Date value) {
            addCriterion("date_start <=", value, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartIn(List<Date> values) {
            addCriterion("date_start in", values, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartNotIn(List<Date> values) {
            addCriterion("date_start not in", values, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartBetween(Date value1, Date value2) {
            addCriterion("date_start between", value1, value2, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateStartNotBetween(Date value1, Date value2) {
            addCriterion("date_start not between", value1, value2, "dateStart");
            return (Criteria) this;
        }

        public Criteria andDateEndIsNull() {
            addCriterion("date_end is null");
            return (Criteria) this;
        }

        public Criteria andDateEndIsNotNull() {
            addCriterion("date_end is not null");
            return (Criteria) this;
        }

        public Criteria andDateEndEqualTo(Date value) {
            addCriterion("date_end =", value, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndNotEqualTo(Date value) {
            addCriterion("date_end <>", value, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndGreaterThan(Date value) {
            addCriterion("date_end >", value, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndGreaterThanOrEqualTo(Date value) {
            addCriterion("date_end >=", value, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndLessThan(Date value) {
            addCriterion("date_end <", value, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndLessThanOrEqualTo(Date value) {
            addCriterion("date_end <=", value, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndIn(List<Date> values) {
            addCriterion("date_end in", values, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndNotIn(List<Date> values) {
            addCriterion("date_end not in", values, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndBetween(Date value1, Date value2) {
            addCriterion("date_end between", value1, value2, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andDateEndNotBetween(Date value1, Date value2) {
            addCriterion("date_end not between", value1, value2, "dateEnd");
            return (Criteria) this;
        }

        public Criteria andKillPriceIsNull() {
            addCriterion("kill_price is null");
            return (Criteria) this;
        }

        public Criteria andKillPriceIsNotNull() {
            addCriterion("kill_price is not null");
            return (Criteria) this;
        }

        public Criteria andKillPriceEqualTo(Integer value) {
            addCriterion("kill_price =", value, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceNotEqualTo(Integer value) {
            addCriterion("kill_price <>", value, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceGreaterThan(Integer value) {
            addCriterion("kill_price >", value, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("kill_price >=", value, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceLessThan(Integer value) {
            addCriterion("kill_price <", value, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceLessThanOrEqualTo(Integer value) {
            addCriterion("kill_price <=", value, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceIn(List<Integer> values) {
            addCriterion("kill_price in", values, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceNotIn(List<Integer> values) {
            addCriterion("kill_price not in", values, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceBetween(Integer value1, Integer value2) {
            addCriterion("kill_price between", value1, value2, "killPrice");
            return (Criteria) this;
        }

        public Criteria andKillPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("kill_price not between", value1, value2, "killPrice");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}