package com.xfkj.common.query;


import com.xfkj.common.utils.DateUtil;

import java.util.Date;

/**
 * Author ： LILEI ENGLISH SUNNY
 * CreateTime: 2017-04-28
 * Title  :列表查询参数接口实现
 */
public class QueryDateType implements java.io.Serializable {

    private Date beginDate;
    private Date endDate;

    public QueryDateType() {
    }

    public QueryDateType(Date createDate) {
        if (createDate.after(new Date())) {
            this.beginDate = DateUtil.begin(new Date());
        } else {
            this.beginDate = DateUtil.begin(createDate);
        }
        this.endDate = DateUtil.end(new Date());
    }

    public QueryDateType(Date beginDate, Date endDate) {
        if (beginDate.after(endDate)) {
            this.beginDate = endDate;
            this.endDate = beginDate;
        } else {
            this.beginDate = beginDate;
            this.endDate = endDate;
        }
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "QueryDateType{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
