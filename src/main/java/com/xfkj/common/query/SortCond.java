package com.xfkj.common.query;


/**
 * Author ： LILEI ENGLISH SUNNY
 * CreateTime: 2017-04-28
 * Title  :排序参数的抽象
 */
public class SortCond implements java.io.Serializable{
    /**
     * 排序类型枚举
     */
    public enum Order {
        ASC, DESC
    }

    /**
     * 排序类型
     */
    private String column;

    /**
     * 排序类型
     */
    private Order order;

    public SortCond(String column) {
        this(column, Order.DESC);
    }

    public SortCond(String column, Order order) {
        this.column = column;
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public Order getOrder() {
        return order;
    }
}
