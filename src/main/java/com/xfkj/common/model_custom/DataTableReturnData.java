package com.xfkj.common.model_custom;

import java.util.List;

/**
 * 管理后台  返回给dataTables数据结构
 */
public class DataTableReturnData<T> {
    int draw;//datatables会自动传过来
    int recordsTotal;//总条数（datatables分页用）
    int recordsFiltered;//总条数（datatables分页用）
    public List<T> data;
    public String msg;
    public int status;

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getDraw() {
        return draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
