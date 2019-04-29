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
public class TrainTestControllerTest {
    private final Logger logger = LoggerFactory.getLogger(TrainTestControllerTest.class);

    private MockMvc mockMvc;
    @Autowired
    private TrainTestController trainTestController;
    private String reqUrl = "/app/trainTest";
    private String loginReqUrl = "/app/user";
    @Autowired
    private WebApplicationContext webApplicationContext;
    private  String token;
    private  String token1;
    private  String token2;
    private  String token3;
    private  String mobile="18564656666";
    private  String mobile1="18254664666";
    private  String mobile2="13405947470";
    private  String mobile3="13237852010";

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


        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                        .param("phone",mobile1)
                        .param("password","123456")
                        .param("qcellcoreId","1")
                );
        MvcResult mvcResult1 = resultActions1.andReturn();
        logger.info("=====generalLogin mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult1.getResponse().getContentAsString();
        logger.info("=====generalLogin result :" + result1);
        JSONObject jsonObject1 = JSONObject.fromObject(result1);
        String  tokenJson1 =  jsonObject1.optString("data");
        String parents1 =  JSONObject.fromObject(tokenJson1).optString("parents");
        token1 =  JSONObject.fromObject(parents1).optString("token");
        logger.info("=====generalLogin result token:" + token1);

        ResultActions resultActions2 = this.mockMvc.
                perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                        .param("phone",mobile2)
                        .param("password","123456")
                        .param("qcellcoreId","1")
                );
        MvcResult mvcResult2 = resultActions2.andReturn();
        logger.info("=====generalLogin mvcResult:" + mvcResult2.getResponse().getStatus());
        String result2 = mvcResult2.getResponse().getContentAsString();
        logger.info("=====generalLogin result :" + result2);
        JSONObject jsonObject2 = JSONObject.fromObject(result2);
        String  tokenJson2 =  jsonObject2.optString("data");
        String parents2 =  JSONObject.fromObject(tokenJson2).optString("parents");
        token2 =  JSONObject.fromObject(parents2).optString("token");
        logger.info("=====generalLogin result token:" + token2);

        ResultActions resultActions3 = this.mockMvc.
                perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                        .param("phone",mobile3)
                        .param("password","123456")
                        .param("qcellcoreId","1")
                );
        MvcResult mvcResult3 = resultActions3.andReturn();
        logger.info("=====generalLogin mvcResult:" + mvcResult3.getResponse().getStatus());
        String result3 = mvcResult3.getResponse().getContentAsString();
        logger.info("=====generalLogin result :" + result3);
        JSONObject jsonObject3 = JSONObject.fromObject(result3);
        String  tokenJson3 =  jsonObject3.optString("data");
        String parents3 =  JSONObject.fromObject(tokenJson3).optString("parents");
        token3 =  JSONObject.fromObject(parents3).optString("token");
        logger.info("=====generalLogin result token:" + token3);
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
    public void getFortifier1() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getFortifier")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getFortifier1 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getFortifier1 result:" + result);
    }

    @Test
    public void getFortifier2() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getFortifier")
                        .param("token",token1)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getFortifier2 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getFortifier2 result:" + result);
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
    public void addFortifier1() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("module","2")
                        .param("state","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier1 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier1 result:" + result);
    }

    @Test
    public void addFortifier2() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("token",token)
                        .param("module","5")
                        .param("state","0")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier2 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier2 result:" + result);
    }

    @Test
    public void addFortifier3() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("token",token)
                        .param("module","2")
                        .param("state","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier3 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier3 result:" + result);
    }

    @Test
    public void addFortifier4() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("token",token)
                        .param("module","5")
                        .param("state","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier4 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier4 result:" + result);
    }

    @Test
    public void addFortifier5() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("token","8SEiqmYxvXqiOIo/qkpsmw11=")
                        .param("module","5")
                        .param("state","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier5 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier5 result:" + result);
    }

    @Test
    public void addFortifier6() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("token",token1)
                        .param("module","2")
                        .param("state","0")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier6 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier6 result:" + result);
    }

    @Test
    public void addFortifier7() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addFortifier")
                        .param("token",token)
                        .param("module","3")
                        .param("state","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addFortifier7 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addFortifier7 result:" + result);
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
    public void getSystemStatistics1() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token","8SEiqmYxvXqiOIo/qkpsmw11=")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics1 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics1 result:" + result);
    }


    @Test
    public void getSystemStatistics2() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token1)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics2 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics2 result:" + result);
    }

    @Test
    public void getSystemStatistics3() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token2)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics3 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics3 result:" + result);
    }

    @Test
    public void getSystemStatistics4() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics4 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics4 result:" + result);
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

    @Test
    public void getNounTraining1() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getNoun")
                        .param("token","8SEiqmYxvXqiOI111kpsmw11=")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getNounTraining1 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getNounTraining1 result:" + result);
    }

    @Test
    public void getNounTraining2() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getNoun")
                        .param("token",token)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getNounTraining2 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getNounTraining2 result:" + result);
    }

    @Test
    public void getNounTraining3() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getNoun")
                        .param("token",token)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getNounTraining3 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getNounTraining3 result:" + result);
    }

    @Test
    public void getNounTraining4() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getNoun")
                        .param("token",token1)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getNounTraining4 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getNounTraining4 result:" + result);
    }

    @Test
    public void getNounTraining5() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getNoun")
                        .param("token",token2)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getNounTraining5 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getNounTraining5 result:" + result);
    }

    @Test
    public void getNounTraining6() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getNoun")
                        .param("token",token3)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getNounTraining6 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getNounTraining6 result:" + result);
    }

    @Test
    public void getVerbTraining() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getVerb")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getVerbTraining mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getVerbTraining result:" + result);
    }

    @Test
    public void getSentencegroupTraining() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSentencegroup")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSentencegroupTraining mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSentencegroupTraining result:" + result);
    }

    @Test
    public void getSentenceResolveTraining() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSentenceResolve")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSentenceResolveTraining mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSentenceResolveTraining result:" + result);
    }
}