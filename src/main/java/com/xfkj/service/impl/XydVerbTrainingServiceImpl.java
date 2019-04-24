package com.xfkj.service.impl;

import com.xfkj.common.model_custom.VerbTrainingCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.mapper.XydVerbTrainingMapper;
import com.xfkj.model.XydVerbTest;
import com.xfkj.model.XydVerbTraining;
import com.xfkj.service.XydVerbTrainingService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by King on 2018/10/8.
 */
@Service
public class XydVerbTrainingServiceImpl implements XydVerbTrainingService {

    @Autowired
    private XydVerbTrainingMapper verbTrainingMapper;

    @Override
    public List<XydVerbTraining> selectAllRand() {
//        List<XydVerbTraining> list = verbTrainingMapper.selectAllRand();
        ArrayList<XydVerbTraining> list = new ArrayList<>();
        //查询五条动词 1吃2喝3穿4玩5乐
        List<XydVerbTraining> xydVerbTraining = verbTrainingMapper.selectByFiveType("1");
        List<XydVerbTraining> xydVerbTraining1 =verbTrainingMapper.selectByFiveType1();
        list.addAll(xydVerbTraining);
        list.addAll(xydVerbTraining1);
        if(list.size()>0){
            for(XydVerbTraining verbTraining : list){
                ArrayList<VerbTrainingCustom> verbTrainingCustoms = new ArrayList<>();
                if(!IsObjectNullUtils.is(verbTraining) && !IsObjectNullUtils.is(verbTraining.getVerbType())){
                    //查詢相同類型的動詞 去重
                    XydVerbTraining verbTraining1 = verbTrainingMapper.selectByType(verbTraining.getVerbType(),verbTraining.getCardChar());
                    if(IsObjectNullUtils.is(verbTraining1)){
                        continue;
                    }
                    Random random = new Random();
                    int i = random.nextInt(2) + 1;
                    VerbTrainingCustom verbTrainingCustom = new VerbTrainingCustom();
                    verbTrainingCustom.setIsSuccess("1");
                    verbTrainingCustom.setCardImage(verbTraining.getCardImage());
                    verbTrainingCustom.setCardChar(verbTraining.getCardChar());
                    verbTrainingCustom.setCardRecord(verbTraining.getCardRecord());

                    VerbTrainingCustom verbTrainingCustom2 = new VerbTrainingCustom();
                    verbTrainingCustom2.setIsSuccess("0");
                    verbTrainingCustom2.setCardImage(verbTraining1.getCardImage());
                    verbTrainingCustom2.setCardChar(verbTraining1.getCardChar());
                    verbTrainingCustom2.setCardRecord(verbTraining1.getCardRecord());
                    //打乱顺序
                    if(i==1){
                        verbTrainingCustoms.add(verbTrainingCustom);
                        verbTrainingCustoms.add(verbTrainingCustom2);
                    }else{
                        verbTrainingCustoms.add(verbTrainingCustom2);
                        verbTrainingCustoms.add(verbTrainingCustom);
                    }
                    verbTraining.setList(verbTrainingCustoms);
                }
            }
            return list;
        }else{
            return list;
        }
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return verbTrainingMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydVerbTraining> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return verbTrainingMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydVerbTraining verbTraining) {
        return verbTrainingMapper.insertSelective(verbTraining);
    }

    @Override
    public int delete(Integer id) {
        return verbTrainingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydVerbTraining verbTraining) {
        return verbTrainingMapper.updateByPrimaryKeySelective(verbTraining);
    }

    @Override
    public XydVerbTraining selectById(Integer id) {
        return verbTrainingMapper.selectByPrimaryKey(id);
    }
}