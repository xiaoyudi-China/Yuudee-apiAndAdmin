package com.xfkj.service.impl;

import com.xfkj.common.model_custom.SentenceGroupTrainingCustom;
import com.xfkj.common.model_custom.SentenceResolveTrainingCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydSentenceResolveTrainingMapper;
import com.xfkj.model.XydSentenceResolveTest;
import com.xfkj.model.XydSentenceResolveTraining;
import com.xfkj.service.XydSentenceResolveTrainingService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by King on 2018/10/10.
 */
@Service
public class XydSentenceResolveTrainingServiceImpl implements XydSentenceResolveTrainingService {

    @Autowired
    private XydSentenceResolveTrainingMapper sentenceResolveTrainingMapper;

    @Override
    public List<XydSentenceResolveTraining> selectAllByRand() {
        List<XydSentenceResolveTraining> xydSentenceResolveTrainings = sentenceResolveTrainingMapper.selectAllByRand();
        if(xydSentenceResolveTrainings.size()>0){
            for(XydSentenceResolveTraining sentenceResolveTraining : xydSentenceResolveTrainings){
                ArrayList<SentenceResolveTrainingCustom> objects = new ArrayList<>();
                Random random = new Random();
                int i = random.nextInt(4) + 1;

                SentenceResolveTrainingCustom sentenceGroupTrainingCustom = new SentenceResolveTrainingCustom();
                sentenceGroupTrainingCustom.setCardImage(sentenceResolveTraining.getCardOneImage());
                sentenceGroupTrainingCustom.setCardRecord(sentenceResolveTraining.getCardOneRecord());
                sentenceGroupTrainingCustom.setCardChar(sentenceResolveTraining.getCardOneChar());

                SentenceResolveTrainingCustom sentenceGroupTrainingCustom1 = new SentenceResolveTrainingCustom();
                sentenceGroupTrainingCustom1.setCardImage(sentenceResolveTraining.getCardTwoImage());
                sentenceGroupTrainingCustom1.setCardRecord(sentenceResolveTraining.getCardTwoRecord());
                sentenceGroupTrainingCustom1.setCardChar(sentenceResolveTraining.getCardTwoChar());

                SentenceResolveTrainingCustom sentenceGroupTrainingCustom2 = new SentenceResolveTrainingCustom();
                sentenceGroupTrainingCustom2.setCardImage(sentenceResolveTraining.getCardThreeImage());
                sentenceGroupTrainingCustom2.setCardRecord(sentenceResolveTraining.getCardThreeRecord());
                sentenceGroupTrainingCustom2.setCardChar(sentenceResolveTraining.getCardThreeChar());

                SentenceResolveTrainingCustom sentenceGroupTrainingCustom3 = new SentenceResolveTrainingCustom();
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
        return xydSentenceResolveTrainings;
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return sentenceResolveTrainingMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydSentenceResolveTraining> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return sentenceResolveTrainingMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydSentenceResolveTraining sentenceResolveTraining) {
        return sentenceResolveTrainingMapper.insertSelective(sentenceResolveTraining);
    }

    @Override
    public int delete(Integer id) {
        return sentenceResolveTrainingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydSentenceResolveTraining sentenceResolveTraining) {
        return sentenceResolveTrainingMapper.updateByPrimaryKeySelective(sentenceResolveTraining);
    }

    @Override
    public XydSentenceResolveTraining selectById(Integer id) {
        return sentenceResolveTrainingMapper.selectByPrimaryKey(id);
    }

    public static void main(String[] args) {
        String[] arr = new String[] {"0","1", "2","3"};
        List<String> list = Arrays.asList(arr);
        ArrayList<SentenceResolveTrainingCustom> objects = new ArrayList<>();
        for (String o : list) {
            SentenceResolveTrainingCustom s1 = new SentenceResolveTrainingCustom();
            s1.setCardImage(o);
            objects.add(s1);
        }


        Collections.shuffle(objects);
        for (SentenceResolveTrainingCustom s : objects) {
            System.out.println(s.getCardImage());
        }
    }
}
