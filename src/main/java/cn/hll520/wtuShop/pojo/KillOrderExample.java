package cn.hll520.wtuShop.pojo;

import java.util.ArrayList;
import java.util.List;

public class KillOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KillOrderExample() {
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

        public Criteria andKillOrderIdIsNull() {
            addCriterion("kill_order_id is null");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdIsNotNull() {
            addCriterion("kill_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdEqualTo(Integer value) {
            addCriterion("kill_order_id =", value, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdNotEqualTo(Integer value) {
            addCriterion("kill_order_id <>", value, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdGreaterThan(Integer value) {
            addCriterion("kill_order_id >", value, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("kill_order_id >=", value, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdLessThan(Integer value) {
            addCriterion("kill_order_id <", value, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("kill_order_id <=", value, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdIn(List<Integer> values) {
            addCriterion("kill_order_id in", values, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdNotIn(List<Integer> values) {
            addCriterion("kill_order_id not in", values, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("kill_order_id between", value1, value2, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("kill_order_id not between", value1, value2, "killOrderId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdIsNull() {
            addCriterion("kill_user_id is null");
            return (Criteria) this;
        }

        public Criteria andKillUserIdIsNotNull() {
            addCriterion("kill_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andKillUserIdEqualTo(Integer value) {
            addCriterion("kill_user_id =", value, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdNotEqualTo(Integer value) {
            addCriterion("kill_user_id <>", value, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdGreaterThan(Integer value) {
            addCriterion("kill_user_id >", value, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("kill_user_id >=", value, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdLessThan(Integer value) {
            addCriterion("kill_user_id <", value, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("kill_user_id <=", value, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdIn(List<Integer> values) {
            addCriterion("kill_user_id in", values, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdNotIn(List<Integer> values) {
            addCriterion("kill_user_id not in", values, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdBetween(Integer value1, Integer value2) {
            addCriterion("kill_user_id between", value1, value2, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("kill_user_id not between", value1, value2, "killUserId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdIsNull() {
            addCriterion("kill_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdIsNotNull() {
            addCriterion("kill_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdEqualTo(Integer value) {
            addCriterion("kill_goods_id =", value, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdNotEqualTo(Integer value) {
            addCriterion("kill_goods_id <>", value, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdGreaterThan(Integer value) {
            addCriterion("kill_goods_id >", value, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("kill_goods_id >=", value, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdLessThan(Integer value) {
            addCriterion("kill_goods_id <", value, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("kill_goods_id <=", value, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdIn(List<Integer> values) {
            addCriterion("kill_goods_id in", values, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdNotIn(List<Integer> values) {
            addCriterion("kill_goods_id not in", values, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("kill_goods_id between", value1, value2, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andKillGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("kill_goods_id not between", value1, value2, "killGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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