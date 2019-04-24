package com.xfkj.service.impl;

import com.xfkj.common.model_custom.SentenceResolveTestCustom;
import com.xfkj.common.model_custom.SentenceResolveTrainingCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydSentenceResolveTestMapper;
import com.xfkj.model.XydSentenceResolveTest;
import com.xfkj.model.XydSentenceResolveTraining;
import com.xfkj.service.XydSentenceResolveTestService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by King on 2018/10/10.
 */
@Service
public class XydSentenceResolveTestServiceImpl implements XydSentenceResolveTestService {

    @Autowired
    private XydSentenceResolveTestMapper sentenceResolveTestMapper;

    @Override
    public List<XydSentenceResolveTest> selectAllByRand() {
        List<XydSentenceResolveTest> xydSentenceResolveTests = sentenceResolveTestMapper.selectAllByRand();
        if(xydSentenceResolveTests.size()>0){
            for(XydSentenceResolveTest sentenceResolveTraining : xydSentenceResolveTests){
                ArrayList<SentenceResolveTestCustom> objects = new ArrayList<>();
                Random random = new Random();
                int i = random.nextInt(4) + 1;

                SentenceResolveTestCustom sentenceGroupTrainingCustom = new SentenceResolveTestCustom();
                sentenceGroupTrainingCustom.setCardImage(sentenceResolveTraining.getCardOneImage());
                sentenceGroupTrainingCustom.setCardRecord(sentenceResolveTraining.getCardOneRecord());
                sentenceGroupTrainingCustom.setCardChar(sentenceResolveTraining.getCardOneChar());

                SentenceResolveTestCustom sentenceGroupTrainingCustom1 = new SentenceResolveTestCustom();
                sentenceGroupTrainingCustom1.setCardImage(sentenceResolveTraining.getCardTwoImage());
                sentenceGroupTrainingCustom1.setCardRecord(sentenceResolveTraining.getCardTwoRecord());
                sentenceGroupTrainingCustom1.setCardChar(sentenceResolveTraining.getCardTwoChar());

                SentenceResolveTestCustom sentenceGroupTrainingCustom2 = new SentenceResolveTestCustom();
                sentenceGroupTrainingCustom2.setCardImage(sentenceResolveTraining.getCardThreeImage());
                sentenceGroupTrainingCustom2.setCardRecord(sentenceResolveTraining.getCardThreeRecord());
                sentenceGroupTrainingCustom2.setCardChar(sentenceResolveTraining.getCardThreeChar());

                SentenceResolveTestCustom sentenceGroupTrainingCustom3 = new SentenceResolveTestCustom();
                sentenceGroupTrainingCustom3.setCardImage(sentenceResolveTraining.getCardFourImage());
                sentenceGroupTrainingCustom3.setCardRecord(sentenceResolveTraining.getCardFourRecord());
                sentenceGroupTrainingCustom3.setCardChar(sentenceResolveTraining.getCardFourChar());
                if(i==1){
                    objects.add(sentenceGroupTrainingCustom);
                    objects.add(sentenceGroupTrainingCustom1);
                    objects.add(sentenceGroupTrainingCustom2);
                    objects.add(sentenceGroupTrainingCustom3);
                }else if(i==2){
                    objects.add(sentenceGroupTrainingCustom);
                    objects.add(sentenceGroupTrainingCustom2);
                    objects.add(sentenceGroupTrainingCustom1);
                    objects.add(sentenceGroupTrainingCustom3);
                }else if(i==3){
                    objects.add(sentenceGroupTrainingCustom1);
                    objects.add(sentenceGroupTrainingCustom);
                    objects.add(sentenceGroupTrainingCustom3);
                    objects.add(sentenceGroupTrainingCustom2);
                }else{
                    objects.add(sentenceGroupTrainingCustom2);
                    objects.add(sentenceGroupTrainingCustom);
                    objects.add(sentenceGroupTrainingCustom1);
                    objects.add(sentenceGroupTrainingCustom3);
                }
                Collections.shuffle(objects);
                sentenceResolveTraining.setList(objects);
            }
        }
        return xydSentenceResolveTests;
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return sentenceResolveTestMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydSentenceResolveTest> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return sentenceResolveTestMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydSentenceResolveTest sentenceResolveTest) {
        return sentenceResolveTestMapper.insertSelective(sentenceResolveTest);
    }

    @Override
    public int delete(Integer id) {
        return sentenceResolveTestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydSentenceResolveTest sentenceResolveTest) {
        return sentenceResolveTestMapper.updateByPrimaryKeySelective(sentenceResolveTest);
    }

    @Override
    public XydSentenceResolveTest selectById(Integer id) {
        return sentenceResolveTestMapper.selectByPrimaryKey(id);
    }
}
