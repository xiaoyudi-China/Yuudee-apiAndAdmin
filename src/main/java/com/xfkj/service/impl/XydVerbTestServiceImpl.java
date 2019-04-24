package com.xfkj.service.impl;

import com.xfkj.common.model_custom.VerbTestCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydVerbTestMapper;
import com.xfkj.model.XydVerbTest;
import com.xfkj.service.XydVerbTestService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by King on 2018/10/8.
 */
@Service
public class XydVerbTestServiceImpl implements XydVerbTestService {

    @Autowired
    private XydVerbTestMapper verbTestMapper;

    @Override
    public List<XydVerbTest> selectAllByRand() {
        List<XydVerbTest> xydVerbTests = verbTestMapper.selectAllByRand();
        if (xydVerbTests.size() > 0) {
            for (XydVerbTest verbTest : xydVerbTests) {
                Random random = new Random();
                int i = random.nextInt(2) + 1;
                ArrayList<VerbTestCustom> objects = new ArrayList<>();

                VerbTestCustom verbTestCustom = new VerbTestCustom();
                verbTestCustom.setCardChar(verbTest.getVerbChar());
                verbTestCustom.setCardImage(verbTest.getVerbImage());
                verbTestCustom.setCardRecord(verbTest.getVerbRecord());

                VerbTestCustom verbTestCustom1 = new VerbTestCustom();
                verbTestCustom1.setCardChar(verbTest.getCardChar());
                verbTestCustom1.setCardImage(verbTest.getCardImage());
                verbTestCustom1.setCardRecord(verbTest.getCardRecord());
                if (i == 1) {
                    objects.add(verbTestCustom1);
                    objects.add(verbTestCustom);
                } else {
                    objects.add(verbTestCustom);
                    objects.add(verbTestCustom1);
                }
                verbTest.setList(objects);
            }
        }
        return xydVerbTests;
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return verbTestMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydVerbTest> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return verbTestMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public int insert(XydVerbTest verbTest) {
        return verbTestMapper.insertSelective(verbTest);
    }

    @Override
    public int delete(Integer id) {
        return verbTestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydVerbTest verbTest) {
        return verbTestMapper.updateByPrimaryKeySelective(verbTest);
    }

    @Override
    public XydVerbTest selectById(Integer id) {
        return verbTestMapper.selectByPrimaryKey(id);
    }
}
