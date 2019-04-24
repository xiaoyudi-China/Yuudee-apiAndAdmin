package com.xfkj.service.impl;

import com.xfkj.common.model_custom.SentenceGroupTestCustom;
import com.xfkj.common.model_custom.SentenceGroupTrainingCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydSentenceGroupTestMapper;
import com.xfkj.model.XydSentenceGroupTest;
import com.xfkj.service.XydSentenceGroupTestService;
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
public class XydSentenceGroupTestServiceImpl implements XydSentenceGroupTestService {

    @Autowired
    private XydSentenceGroupTestMapper sentenceGroupTestMapper;

    @Override
    public List<XydSentenceGroupTest> selectAllByRand() {
        List<XydSentenceGroupTest> xydSentenceGroupTests = sentenceGroupTestMapper.selectAllByRand();
        if(xydSentenceGroupTests.size()>0){
            for(XydSentenceGroupTest sentenceGroupTest : xydSentenceGroupTests){
                ArrayList<SentenceGroupTestCustom> objects = new ArrayList<>();
                Random random = new Random();
                int i = random.nextInt(2) + 1;

                SentenceGroupTestCustom sentenceGroupTestCustom = new SentenceGroupTestCustom();
                sentenceGroupTestCustom.setCardImage(sentenceGroupTest.getCardOneImage());
                sentenceGroupTestCustom.setCardRecord(sentenceGroupTest.getCardOneRecord());
                sentenceGroupTestCustom.setCardChar(sentenceGroupTest.getCardOneChar());

                SentenceGroupTestCustom sentenceGroupTestCustom1 = new SentenceGroupTestCustom();
                sentenceGroupTestCustom1.setCardImage(sentenceGroupTest.getCardTwoImage());
                sentenceGroupTestCustom1.setCardRecord(sentenceGroupTest.getCardTwoRecord());
                sentenceGroupTestCustom1.setCardChar(sentenceGroupTest.getCardTwoChar());
                if(i==1){
                    objects.add(sentenceGroupTestCustom);
                    objects.add(sentenceGroupTestCustom1);
                }else{
                    objects.add(sentenceGroupTestCustom1);
                    objects.add(sentenceGroupTestCustom);
                }
                sentenceGroupTest.setList(objects);
            }
        }
        return xydSentenceGroupTests;
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return sentenceGroupTestMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydSentenceGroupTest> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return sentenceGroupTestMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydSentenceGroupTest verbTest) {
        return sentenceGroupTestMapper.insertSelective(verbTest);
    }

    @Override
    public int delete(Integer id) {
        return sentenceGroupTestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydSentenceGroupTest verbTest) {
        return sentenceGroupTestMapper.updateByPrimaryKeySelective(verbTest);
    }

    @Override
    public XydSentenceGroupTest selectById(Integer id) {
        return sentenceGroupTestMapper.selectByPrimaryKey(id);
    }
}
