package com.dkd.api;

import com.dkd.XiaoyudiApplication;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XiaoyudiApplication.class, properties = "/application.properties")
@AutoConfigureMockMvc
@Transactional
public class TrainTestControllerTest1 {
    private final Logger logger = LoggerFactory.getLogger(TrainTestControllerTest1.class);

    private MockMvc mockMvc;
    @Autowired
    private TrainTestController trainTestController;
    private String reqUrl = "/app/trainTest";
    private String loginReqUrl = "/app/user";
    @Autowired
    private WebApplicationContext webApplicationContext;
    private  String token;
    private  String mobile="18254664666";

    @Before
    public void setUp() throws Exception {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(trainTestController).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                        .param("phone",mobile)
                        .param("password","123456")
                        .param("qcellcoreId","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        logger.info("=====generalLogin mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        logger.info("=====generalLogin result :" + result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String  tokenJson =  jsonObject.optString("data");
        String parents =  JSONObject.fromObject(tokenJson).optString("parents");
        token =  JSONObject.fromObject(parents).optString("token");
        logger.info("=====generalLogin result token:" + token);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getFortifier() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getFortifier")
                        .param("token",token)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getFortifier mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getFortifier result:" + result);
    }

    @Test
    public void addFortifier() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("token",token)
                        .param("module","2")
                        .param("state","0")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier result:" + result);
    }

    @Test
    public void getSystemStatistics() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics result:" + result);
    }

    @Test
    public void getNounTraining() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getNoun")
                        .param("token",token)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getNounTraining mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getNounTraining result:" + result);
    }
}