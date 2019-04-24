package com.xfkj.common.query;






import com.xfkj.common.emun.BaseEnum;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;


public class GenericQueryParam extends LinkedHashMap<String, Object> implements ListQueryParam {
    /**
     * 最大单页记录数
     */
    public final static int MAX_PAGE_SIZE = 100;

    /**
     * 当前页面key
     */
    private final static String PAGE_KEY = "__page";

    /**
     * 单页记录数key
     */
    private final static String PAGESIZE_KEY = "__pagesize";

    /**
     * 排序参数List key
     */
    private final static String SORTCOND_KEY = "__sortcond";

    public GenericQueryParam() {
        this(1, 10);
    }

    public GenericQueryParam(Integer page, Integer pageSize) {
        setPage(page);
        setPageSize(pageSize);
    }

    @Override
    public Integer getPage() {
        return (Integer) get(PAGE_KEY);
    }

    @Override
    public Integer getPageSize() {
        return (Integer) get(PAGESIZE_KEY);
    }

    @Override
    public void setPage(Integer page) {
        put(PAGE_KEY, page);
    }

    @Override
    public void setPageSize(Integer pageSize) {
        put(PAGESIZE_KEY, pageSize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SortCond> getSortCond() {
        List<SortCond> sortCondList = (List<SortCond>) get(SORTCOND_KEY);
        if (sortCondList == null) {
            sortCondList = new LinkedList<SortCond>();
            put(SORTCOND_KEY, sortCondList);
        }
        return sortCondList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addSortCond(SortCond sortCond) {
        List<SortCond> sortCondList = (List<SortCond>) get(SORTCOND_KEY);

        if (sortCondList == null) {
            sortCondList = new LinkedList<SortCond>();
            put(SORTCOND_KEY, sortCondList);
        }

        sortCondList.add(sortCond);
    }

    @Override
    public void addSortCond(List<SortCond> sortCondList) {
        for (SortCond sortCond : sortCondList) addSortCond(sortCond);
    }

    /**
     *
     * @param key   参数名
     * @param value 参数值
     *              日期型要用 QueryDateType
     * @return
     */
    @Override
    public QueryParam fill(BaseEnum key, Object value) {

        put(key.toLowerCase(), value);

        return this;
    }

    @Override
    public QueryParam fill(String key, Object value) {
        put(key.toLowerCase(), value);
        return this;
    }

}
