package com.xfkj.service.impl;

import com.xfkj.common.model_custom.SentenceGroupTrainingCustom;
import com.xfkj.common.model_custom.VerbTestCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydSentenceGroupTrainingMapper;
import com.xfkj.model.XydSentenceGroupTraining;
import com.xfkj.service.XydSentenceGroupTrainingService;
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
public class XydSentenceGroupTrainingServiceImpl implements XydSentenceGroupTrainingService {

    @Autowired
    private XydSentenceGroupTrainingMapper sentenceGroupTrainingMapper;

    @Override
    public List<XydSentenceGroupTraining> selectAllByRand() {
        List<XydSentenceGroupTraining> xydSentenceGroupTrainings = sentenceGroupTrainingMapper.selectAllByRand();
        if(xydSentenceGroupTrainings.size()>0){
            for(XydSentenceGroupTraining sentenceGroupTraining : xydSentenceGroupTrainings){
                ArrayList<SentenceGroupTrainingCustom> objects = new ArrayList<>();
                Random random = new Random();
                int i = random.nextInt(2) + 1;

                SentenceGroupTrainingCustom sentenceGroupTrainingCustom = new SentenceGroupTrainingCustom();
                sentenceGroupTrainingCustom.setCardImage(sentenceGroupTraining.getCardOneImage());
                sentenceGroupTrainingCustom.setCardRecord(sentenceGroupTraining.getCardOneRecord());
                sentenceGroupTrainingCustom.setCardChar(sentenceGroupTraining.getCardOneChar());

                SentenceGroupTrainingCustom sentenceGroupTrainingCustom1 = new SentenceGroupTrainingCustom();
                sentenceGroupTrainingCustom1.setCardImage(sentenceGroupTraining.getCardTwoImage());
                sentenceGroupTrainingCustom1.setCardRecord(sentenceGroupTraining.getCardTwoRecord());
                sentenceGroupTrainingCustom1.setCardChar(sentenceGroupTraining.getCardTwoChar());
                if(i==1){
                    objects.add(sentenceGroupTrainingCustom);
                    objects.add(sentenceGroupTrainingCustom1);
                }else{
                    objects.add(sentenceGroupTrainingCustom1);
                    objects.add(sentenceGroupTrainingCustom);
                }
                sentenceGroupTraining.setList(objects);
            }
        }
        return xydSentenceGroupTrainings;
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return sentenceGroupTrainingMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydSentenceGroupTraining> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return sentenceGroupTrainingMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydSentenceGroupTraining verbTest) {
        return sentenceGroupTrainingMapper.insertSelective(verbTest);
    }

    @Override
    public int delete(Integer id) {
        return sentenceGroupTrainingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydSentenceGroupTraining verbTest) {
        return sentenceGroupTrainingMapper.updateByPrimaryKeySelective(verbTest);
    }

    @Override
    public XydSentenceGroupTraining selectById(Integer id) {
        return sentenceGroupTrainingMapper.selectByPrimaryKey(id);
    }
}
