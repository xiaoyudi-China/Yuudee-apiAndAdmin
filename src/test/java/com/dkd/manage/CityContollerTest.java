package com.dkd.manage;

import com.dkd.XiaoyudiApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XiaoyudiApplication.class, properties = "/application.properties")
@AutoConfigureMockMvc
@Transactional
public class CityContollerTest {
    private final Logger logger = LoggerFactory.getLogger(CityContollerTest.class);

    private MockMvc mockMvc;
    @Autowired
    private CityContoller cityContoller;
    private String reqUrl = "/manage/city";

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(cityContoller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void toStrartPageInfo() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/toCityList"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====toStrartPageInfo mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====toStrartPageInfo result:" + result);
    }

    @Test
    public void toStrartPageInfo1() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/toCityInfo").param("areaid", "127"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====toStrartPageInfo1 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====toStrartPageInfo1 result:" + result);
    }

    @Test
    public void getStartPageList() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("start", Arrays.asList("0"));
        params.put("length", Arrays.asList("1"));
        params.put("areaName", Arrays.asList("小小"));
        params.put("draw", Arrays.asList("1"));

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/getCityList.ajax").params(params));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====getStartPageList mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====getStartPageList result:" + result);
    }

    @Test
    public void cityDelete() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/deleteCity").param("areaid", "1"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityDelete mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityDelete result:" + result);
    }

    @Test
    public void cityDelete1() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/deleteCity").param("areaid", "4"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityDelete1 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityDelete1 result:" + result);
    }

    @Test
    public void cityDelete2() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/deleteCity").param("areaid", "127"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityDelete2 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityDelete2 result:" + result);
    }

    @Test
    public void cityDelete3() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/deleteCity"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityDelete3 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityDelete3 result:" + result);
    }

    @Test
    public void cityDelete4() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/deleteCity").param("areaid", ""));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityDelete4 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityDelete4 result:" + result);
    }

    @Test
    public void cityUpdateOrAdd() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/addCity").param("areaid", "0").param("level", "1").param("areaname", "小强").param("parentid", "0").param("areaCode", "00"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityUpdateOrAdd mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityUpdateOrAdd result:" + result);
    }

    @Test
    public void cityUpdateOrAdd1() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/addCity").param("level", "1").param("areaname", "小强").param("parentid", "0").param("areaCode", "00"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityUpdateOrAdd1 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityUpdateOrAdd1 result:" + result);
    }

    @Test
    public void cityUpdateOrAdd2() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/addCity").param("areaid", "1").param("level", "1").param("areaname", "小强").param("parentid", "0").param("areaCode", "00"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityUpdateOrAdd2 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityUpdateOrAdd2 result:" + result);
    }

    @Test
    public void cityUpdateOrAdd3() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/addCity").param("areaid", "2").param("level", "1").param("areaname", "小强33").param("parentid", "0").param("areaCode", "00"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityUpdateOrAdd3 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityUpdateOrAdd3 result:" + result);
    }

    @Test
    public void cityUpdateOrAdd4() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/addCity").param("areaid", "").param("level", "1").param("areaname", "小强").param("parentid", "0").param("areaCode", "00"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityUpdateOrAdd4 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityUpdateOrAdd4 result:" + result);
    }

    @Test
    public void cityUpdateOrAdd5() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/addCity").param("level", "1").param("areaname", "小强1").param("parentid", "0").param("areaCode", "00"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityUpdateOrAdd5 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityUpdateOrAdd5 result:" + result);
    }

    @Test
    public void cityUpdateOrAdd6() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(reqUrl + "/addCity").param("areaid", "525").param("level", "3").param("areaname", "中卫市　").param("parentid", "522").param("areaCode", "小强小强小强小强小强小强小强小强小强小强小强小强小强小强小强小强小强小强小强小强小强小"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====cityUpdateOrAdd6 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====cityUpdateOrAdd6 result:" + result);
    }
}