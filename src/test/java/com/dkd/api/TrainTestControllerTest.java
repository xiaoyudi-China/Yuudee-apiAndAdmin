package com.dkd.api;

import com.dkd.XiaoyudiApplication;
import com.dkd.common.utils.RedisService;
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
    private RedisService redisService;
    private String reqUrl = "/app/trainTest";
    private String loginReqUrl = "/app/user";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private  String token;
    private  String token1;
    private  String token2;
    private  String token3;
    private  String token4;
    private  String token5;
    private  String token6;
    private  String mobile="18564656666";
    private  String mobile1="18254664666";
    private  String mobile2="13405947470";
    private  String mobile3="13237852010";
    private  String mobile4="15865425094";
    private  String mobile5="13943631014";
    private  String mobile6="18364974128";

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String tokenCache = "userToken"+mobile;
        Object tokenObj = redisService.get(tokenCache);
        if(tokenObj==null) {
            ResultActions resultActions = this.mockMvc.
                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                            .param("phone", mobile)
                            .param("password", "123456")
                            .param("qcellcoreId", "1")
                    );
            MvcResult mvcResult = resultActions.andReturn();
            logger.info("=====generalLogin mvcResult:" + mvcResult.getResponse().getStatus());
            String result = mvcResult.getResponse().getContentAsString();
            logger.info("=====generalLogin result :" + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String tokenJson = jsonObject.optString("data");
            String parents = JSONObject.fromObject(tokenJson).optString("parents");
            token = JSONObject.fromObject(parents).optString("token");
            logger.info("=====generalLogin result token:" + token);
            redisService.set(tokenCache,token,24*60*60L);
        }else {
            token = String.valueOf(tokenObj);
            System.err.println("已登录tokenCache:"+tokenCache+" :"+tokenObj);
        }
        String tokenCache1 = "userToken"+mobile1;
        Object tokenObj1 = redisService.get(tokenCache1);
        if(tokenObj1==null) {
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
            redisService.set(tokenCache1,token1,24*60*60L);
        }else {
            token1 = String.valueOf(tokenObj1);
            System.err.println("已登录tokenCache:"+tokenCache1+" :"+tokenObj1);
        }
        String tokenCache2 = "userToken"+mobile2;
        Object tokenObj2 = redisService.get(tokenCache2);
        if(tokenObj2==null) {
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
            redisService.set(tokenCache2,token2,24*60*60L);
        }else {
            token2 = String.valueOf(tokenObj2);
            System.err.println("已登录tokenCache:"+tokenCache2+" :"+tokenObj2);
        }
        String tokenCache3 = "userToken"+mobile3;
        Object tokenObj3 = redisService.get(tokenCache3);
        if(tokenObj3==null) {
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
            redisService.set(tokenCache3,token3,24*60*60L);
        } else {
            token3 = String.valueOf(tokenObj3);
            System.err.println("已登录tokenCache:"+tokenCache3+" :"+tokenObj3);
        }

        String tokenCache4 = "userToken"+mobile4;
        Object tokenObj4 = redisService.get(tokenCache4);
        if(tokenObj4==null) {
            ResultActions resultActions4 = this.mockMvc.
                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                            .param("phone",mobile4)
                            .param("password","123456")
                            .param("qcellcoreId","1")
                    );
            MvcResult mvcResult4 = resultActions4.andReturn();
            logger.info("=====generalLogin mvcResult:" + mvcResult4.getResponse().getStatus());
            String result4 = mvcResult4.getResponse().getContentAsString();
            logger.info("=====generalLogin result :" + result4);
            JSONObject jsonObject4 = JSONObject.fromObject(result4);
            String  tokenJson4 =  jsonObject4.optString("data");
            String parents4 =  JSONObject.fromObject(tokenJson4).optString("parents");
            token4 =  JSONObject.fromObject(parents4).optString("token");
            logger.info("=====generalLogin result token:" + token4);
            redisService.set(tokenCache4,token4,24*60*60L);
        }else {
            token4 = String.valueOf(tokenObj4);
            System.err.println("已登录tokenCache:"+tokenCache4+" :"+tokenObj4);
        }
        String tokenCache5 = "userToken"+mobile5;
        Object tokenObj5 = redisService.get(tokenCache5);
        if(tokenObj5==null) {
            ResultActions resultActions5 = this.mockMvc.
                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                            .param("phone",mobile5)
                            .param("password","123456")
                            .param("qcellcoreId","1")
                    );
            MvcResult mvcResult5 = resultActions5.andReturn();
            logger.info("=====generalLogin mvcResult:" + mvcResult5.getResponse().getStatus());
            String result5 = mvcResult5.getResponse().getContentAsString();
            logger.info("=====generalLogin result :" + result5);
            JSONObject jsonObject5 = JSONObject.fromObject(result5);
            String  tokenJson5 =  jsonObject5.optString("data");
            String parents5 =  JSONObject.fromObject(tokenJson5).optString("parents");
            token5 =  JSONObject.fromObject(parents5).optString("token");
            logger.info("=====generalLogin result token:" + token5);
            redisService.set(tokenCache5,token5,24*60*60L);
        }else {
            token5 = String.valueOf(tokenObj5);
            System.err.println("已登录tokenCache:"+tokenCache5+" :"+tokenObj5);
        }
        String tokenCache6 = "userToken"+mobile6;
        Object tokenObj6 = redisService.get(tokenCache6);
        if(tokenObj5==null) {
            ResultActions resultActions6 = this.mockMvc.
                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                            .param("phone",mobile6)
                            .param("password","123456")
                            .param("qcellcoreId","1")
                    );
            MvcResult mvcResult6 = resultActions6.andReturn();
            logger.info("=====generalLogin mvcResult:" + mvcResult6.getResponse().getStatus());
            String result6 = mvcResult6.getResponse().getContentAsString();
            logger.info("=====generalLogin result :" + result6);
            JSONObject jsonObject6 = JSONObject.fromObject(result6);
            String  tokenJson6 =  jsonObject6.optString("data");
            String parents6 =  JSONObject.fromObject(tokenJson6).optString("parents");
            token6 =  JSONObject.fromObject(parents6).optString("token");
            logger.info("=====generalLogin result token:" + token6);
            redisService.set(tokenCache6,token6,24*60*60L);
        }else {
            token6 = String.valueOf(tokenObj6);
            System.err.println("已登录tokenCache:"+tokenCache6+" :"+tokenObj6);
        }
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
    public void getSystemStatistics5() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token4)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics5 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics5 result:" + result);
    }

    @Test
    public void getSystemStatistics6() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token4)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics6 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics6 result:" + result);
    }

    @Test
    public void getSystemStatistics7() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token5)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics7 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics7 result:" + result);
    }

    @Test
    public void getSystemStatistics8() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getSystemStatistics")
                        .param("token",token6)
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getSystemStatistics8 mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getSystemStatistics8 result:" + result);
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