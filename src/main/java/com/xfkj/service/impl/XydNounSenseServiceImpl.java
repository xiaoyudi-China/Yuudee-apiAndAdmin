package com.xfkj.service.impl;

import com.xfkj.common.model_custom.NounSenseCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.mapper.XydNounSenseMapper;
import com.xfkj.model.XydNounSense;
import com.xfkj.service.XydNounSenseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by King on 2018/9/29.
 */
@Service
public class XydNounSenseServiceImpl implements XydNounSenseService {

    @Autowired
    private XydNounSenseMapper nounSenseMapper;

    @Override
    public List<XydNounSense> selectAllRand() {
        List<XydNounSense> list = nounSenseMapper.selectAllRand();
        if(list.size()>0){
            for(XydNounSense nounSense : list){
                //查询相同类型意义测试题
                XydNounSense nounSense1 = nounSenseMapper.selectByType(nounSense.getDisturbType(),nounSense.getId(), nounSense.getCardAdjectiveChar(), nounSense.getCardNounChar());
                //如果找不到 返回
                if(IsObjectNullUtils.is(nounSense1)){
                    continue;
                }
                Random random = new Random();
                int i = random.nextInt(4)+1;
                ArrayList<NounSenseCustom> nounSense2 = getNounSense(i, nounSense, nounSense1);
                nounSense.setList(nounSense2);
            }
            return list;
        }else{
            return list;
        }
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return nounSenseMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydNounSense> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return nounSenseMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydNounSense nounTest) {
        return nounSenseMapper.insertSelective(nounTest);
    }

    @Override
    public int delete(Integer id) {
        return nounSenseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydNounSense nounTest) {
        return nounSenseMapper.updateByPrimaryKeySelective(nounTest);
    }

    @Override
    public XydNounSense selectById(Integer id) {
        return nounSenseMapper.selectByPrimaryKey(id);
    }

    public static ArrayList<NounSenseCustom> getNounSense(int i,XydNounSense nounSense,XydNounSense nounSense1){
        ArrayList<NounSenseCustom> nounSenseCustoms = new ArrayList<>();
        NounSenseCustom nounSenseCustom = new NounSenseCustom();
        nounSenseCustom.setIsAdj("1");
        nounSenseCustom.setIsSuccess("1");
        nounSenseCustom.setAssistTime(nounSense.getFristAssistTime());
        nounSenseCustom.setCardAdjectiveImage(nounSense.getCardAdjectiveImage());
        nounSenseCustom.setCardAdjectiveChar(nounSense.getCardAdjectiveChar());
        nounSenseCustom.setCardAdjectiveRecord(nounSense.getCardAdjectiveRecord());

        NounSenseCustom nounSenseCustom2 = new NounSenseCustom();
        nounSenseCustom2.setIsAdj("0");
        nounSenseCustom2.setIsSuccess("1");
        nounSenseCustom2.setAssistTime(nounSense.getSecondAssistTime());
        nounSenseCustom2.setCardAdjectiveImage(nounSense.getCardNounImage());
        nounSenseCustom2.setCardAdjectiveChar(nounSense.getCardNounChar());
        nounSenseCustom2.setCardAdjectiveRecord(nounSense.getCardNounRecord());

        NounSenseCustom nounSenseCustom3 = new NounSenseCustom();
        nounSenseCustom3.setIsAdj("1");
        nounSenseCustom3.setIsSuccess("0");
        nounSenseCustom3.setAssistTime(nounSense1.getFristAssistTime());
        nounSenseCustom3.setCardAdjectiveImage(nounSense1.getCardAdjectiveImage());
        nounSenseCustom3.setCardAdjectiveChar(nounSense1.getCardAdjectiveChar());
        nounSenseCustom3.setCardAdjectiveRecord(nounSense1.getCardAdjectiveRecord());

        NounSenseCustom nounSenseCustom4 = new NounSenseCustom();
        nounSenseCustom4.setIsAdj("0");
        nounSenseCustom4.setIsSuccess("0");
        nounSenseCustom4.setAssistTime(nounSense1.getSecondAssistTime());
        nounSenseCustom4.setCardAdjectiveImage(nounSense1.getCardNounImage());
        nounSenseCustom4.setCardAdjectiveChar(nounSense1.getCardNounChar());
        nounSenseCustom4.setCardAdjectiveRecord(nounSense1.getCardNounRecord());
        switch (i){
            case 1:
                nounSenseCustoms.add(nounSenseCustom);
                nounSenseCustoms.add(nounSenseCustom2);
                nounSenseCustoms.add(nounSenseCustom3);
                nounSenseCustoms.add(nounSenseCustom4);
                break;
            case 2:
                nounSenseCustoms.add(nounSenseCustom2);
                nounSenseCustoms.add(nounSenseCustom);
                nounSenseCustoms.add(nounSenseCustom3);
                nounSenseCustoms.add(nounSenseCustom4);
                break;
            case 3:
                nounSenseCustoms.add(nounSenseCustom);
                nounSenseCustoms.add(nounSenseCustom4);
                nounSenseCustoms.add(nounSenseCustom2);
                nounSenseCustoms.add(nounSenseCustom3);
                break;
            case 4:
                nounSenseCustoms.add(nounSenseCustom4);
                nounSenseCustoms.add(nounSenseCustom2);
                nounSenseCustoms.add(nounSenseCustom);
                nounSenseCustoms.add(nounSenseCustom3);
                break;
        }
        return nounSenseCustoms;
    }

}
