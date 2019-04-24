package com.xfkj.service.impl;

import com.xfkj.common.model_custom.NounTestCustom;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.mapper.XydNounTestMapper;
import com.xfkj.model.XydNounTest;
import com.xfkj.model.XydNounTraining;
import com.xfkj.service.XydNounTestService;
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
public class XydNounTestServiceImpl implements XydNounTestService {

    @Autowired
    private XydNounTestMapper nounTestMapper;

    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(2) + 1;
        System.out.println(i);
    }

    @Override
    public List<XydNounTest> selectAllRand() {
        List<XydNounTest> xydNounTests = nounTestMapper.selectAllRand();
        if(xydNounTests.size()>0){
            for(XydNounTest nounTest : xydNounTests){
                if(!IsObjectNullUtils.is(nounTest)){
                        //打乱顺序
                        Random random = new Random();
                        int i = random.nextInt(2) + 1;
                        if(i == 1){
                            ArrayList<Object> objects = new ArrayList<>();
                            NounTestCustom nounTestCustom = new NounTestCustom();
                            nounTestCustom.setCardColorImage(nounTest.getCardColorImage());
                            nounTestCustom.setCardColorChar(nounTest.getCardColorChar());
                            nounTestCustom.setCardColorRecord(nounTest.getCardColorRecord());
                            nounTestCustom.setAssistTime(nounTest.getFristAssistTime());
                            objects.add(nounTestCustom);
                            NounTestCustom nounTestCustom2 = new NounTestCustom();
                            nounTestCustom2.setCardColorImage(nounTest.getCardWireImage());
                            nounTestCustom2.setCardColorChar(nounTest.getCardWireChar());
                            nounTestCustom2.setCardColorRecord(nounTest.getCardWireRecord());
                            nounTestCustom2.setAssistTime(nounTest.getSecondAssistTime());
                            objects.add(nounTestCustom2);
                            nounTest.setList(objects);
                        }else{
                            ArrayList<Object> objects = new ArrayList<>();
                            NounTestCustom nounTestCustom2 = new NounTestCustom();
                            nounTestCustom2.setCardColorImage(nounTest.getCardWireImage());
                            nounTestCustom2.setCardColorChar(nounTest.getCardWireChar());
                            nounTestCustom2.setCardColorRecord(nounTest.getCardWireRecord());
                            nounTestCustom2.setAssistTime(nounTest.getSecondAssistTime());
                            objects.add(nounTestCustom2);
                            NounTestCustom nounTestCustom = new NounTestCustom();
                            nounTestCustom.setCardColorImage(nounTest.getCardColorImage());
                            nounTestCustom.setCardColorChar(nounTest.getCardColorChar());
                            nounTestCustom.setCardColorRecord(nounTest.getCardColorRecord());
                            nounTestCustom.setAssistTime(nounTest.getFristAssistTime());
                            objects.add(nounTestCustom);
                            nounTest.setList(objects);
                        }
                }
            }
        }else{
            return xydNounTests;
        }
        return xydNounTests;
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return nounTestMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydNounTest> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return nounTestMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydNounTest nounTest) {
        return nounTestMapper.insertSelective(nounTest);
    }

    @Override
    public int delete(Integer id) {
        return nounTestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydNounTest nounTest) {
        return nounTestMapper.updateByPrimaryKeySelective(nounTest);
    }

    @Override
    public XydNounTest selectById(Integer id) {
        return nounTestMapper.selectByPrimaryKey(id);
    }
}
