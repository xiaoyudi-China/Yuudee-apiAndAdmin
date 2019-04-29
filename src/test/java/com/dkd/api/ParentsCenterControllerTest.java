package com.dkd.api;import com.dkd.XiaoyudiApplication;import com.dkd.common.utils.RedisService;import com.dkd.common.utils.StringUtil;import net.sf.json.JSONObject;import org.junit.After;import org.junit.Before;import org.junit.Test;import org.junit.runner.RunWith;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.http.HttpMethod;import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;import org.springframework.test.web.servlet.MockMvc;import org.springframework.test.web.servlet.MvcResult;import org.springframework.test.web.servlet.ResultActions;import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;import org.springframework.test.web.servlet.setup.MockMvcBuilders;import org.springframework.transaction.annotation.Transactional;import org.springframework.web.context.WebApplicationContext;@RunWith(SpringJUnit4ClassRunner.class)@SpringBootTest(classes = XiaoyudiApplication.class, properties = "/application.properties")@AutoConfigureMockMvc@Transactionalpublic class ParentsCenterControllerTest {    private final Logger logger = LoggerFactory.getLogger(ParentsCenterControllerTest.class);    private MockMvc mockMvc;    private String reqUrl = "/app/parents";    private String loginReqUrl = "/app/user";    @Autowired    private WebApplicationContext webApplicationContext;    private  String mobile="18564656666";    @Autowired    private RedisService redisService;    private String  token;    @Before    public void setUp() throws Exception {        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();        String tokenCache = "userToken"+mobile;        Object tokenObj = redisService.get(tokenCache);        if(tokenObj!=null) {            ResultActions resultActionsToken = this.mockMvc.                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")                            .param("phone", mobile)                            .param("password", "123456")                            .param("qcellcoreId", "1")                    );            MvcResult mvcResultToken = resultActionsToken.andReturn();            logger.info("=====generalLogin mvcResult:" + mvcResultToken.getResponse().getStatus());            String resultToken = mvcResultToken.getResponse().getContentAsString();            logger.info("=====generalLogin result :" + resultToken);            JSONObject jsonObject = JSONObject.fromObject(resultToken);            String tokenJson = jsonObject.optString("data");            String parents = JSONObject.fromObject(tokenJson).optString("parents");            token = JSONObject.fromObject(parents).optString("token");            logger.info("=====generalLogin result token:" + token);            redisService.set(tokenCache,token,24*60*60L);        }else {            token = String.valueOf(tokenObj);            System.err.println("已登录tokenCache:"+tokenCache+" :"+tokenObj);        }    }    @After    public void tearDown() throws Exception {    }    @Test    public void mxgTest() throws Exception{        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/test")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====mxgTest mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====mxgTest result:" + result);    }    @Test    public void parentsToAssess() throws Exception{        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/toAssess")                        .param("token","")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====parentsToAssess mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====parentsToAssess result:" + result);        ResultActions resultActionsUserIsNull = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/toAssess")                                .param("token", "abcdefg")                );        MvcResult mvcResultUserIsNull = resultActionsUserIsNull.andReturn();        System.out.println("=====parentsToAssess mvcResultUserIsNull:" + mvcResultUserIsNull.getResponse().getStatus());        String resultUserIsNull = mvcResultUserIsNull.getResponse().getContentAsString();        System.out.println("=====parentsToAssess resultUserIsNull:" + resultUserIsNull);    }    @Test    public void parentsToAssess1() throws Exception{        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/toAssess")                        .param("token", token)                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====parentsToAssess mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====parentsToAssess result:" + result);    }    @Test    public void trainingRecords() throws Exception{        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/training/records")                        .param("token","")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====trainingRecords mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====trainingRecords result:" + result);        ResultActions resultActionsUserIsNull = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/training/records")                                .param("token", "abcefg")                );        MvcResult mvcResultUserIsNull = resultActionsUserIsNull.andReturn();        System.out.println("=====trainingRecords mvcResultUserIsNull:" + mvcResultUserIsNull.getResponse().getStatus());        String resultUserIsNull = mvcResultUserIsNull.getResponse().getContentAsString();        System.out.println("=====trainingRecords resultUserIsNull:" + resultUserIsNull);    }    @Test    public void trainingRecords2() throws Exception{        //没有完善儿童信息1提示        //登录        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")                                .param("phone", "18230113589")                                .param("password", "123456")                                .param("qcellcoreId", "1")                );        MvcResult mvcResult = resultActions.andReturn();        String result = mvcResult.getResponse().getContentAsString();        JSONObject jsonObject = JSONObject.fromObject(result);        String  tokenJson =  jsonObject.optString("data");        String parents =  JSONObject.fromObject(tokenJson).optString("parents");        String token =  JSONObject.fromObject(parents).optString("token");        ResultActions resultActionsTip = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/training/records")                                .param("token", token)                                .param("scene", "1")                                .param("module", "2")                );        MvcResult mvcResultTip = resultActionsTip.andReturn();        System.out.println("=====trainingRecords2 mvcResultTip:" + mvcResultTip.getResponse().getStatus());        String resultTip = mvcResultTip.getResponse().getContentAsString();        System.out.println("=====trainingRecords2 resultTip:" + resultTip);    }    @Test    public void trainingRecords3() throws Exception{        //没有完善儿童信息2提示        //登录        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")                                .param("phone", "13581949447")                                .param("password", "123456")                                .param("qcellcoreId", "1")                );        MvcResult mvcResult = resultActions.andReturn();        String result = mvcResult.getResponse().getContentAsString();        JSONObject jsonObject = JSONObject.fromObject(result);        String  tokenJson =  jsonObject.optString("data");        String parents =  JSONObject.fromObject(tokenJson).optString("parents");        String token =  JSONObject.fromObject(parents).optString("token");        ResultActions resultActionsTip = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/training/records")                                .param("token", token)                );        MvcResult mvcResultTip = resultActionsTip.andReturn();        System.out.println("=====trainingRecords3 mvcResultTip:" + mvcResultTip.getResponse().getStatus());        String resultTip = mvcResultTip.getResponse().getContentAsString();        System.out.println("=====trainingRecords3 resultTip:" + resultTip);    }    @Test    public void trainingRecords1() throws Exception{        if(StringUtil.strIsNull(token)) {            ResultActions resultActionsToken = this.mockMvc.                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")                            .param("phone", mobile)                            .param("password", "123456")                            .param("qcellcoreId", "1")                    );            MvcResult mvcResultToken = resultActionsToken.andReturn();            logger.info("=====generalLogin mvcResult:" + mvcResultToken.getResponse().getStatus());            String resultToken = mvcResultToken.getResponse().getContentAsString();            logger.info("=====generalLogin result :" + resultToken);            JSONObject jsonObject = JSONObject.fromObject(resultToken);            String tokenJson = jsonObject.optString("data");            String parents = JSONObject.fromObject(tokenJson).optString("parents");            String token = JSONObject.fromObject(parents).optString("token");            logger.info("=====generalLogin result token:" + token);        }        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/training/records")                        .param("token",token)                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====trainingRecords mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====trainingRecords result:" + result);    }    @Test    public void addRecord() throws Exception{        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addRecord")                        .param("token","")                        .param("type","1")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====addRecord mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====addRecord result:" + result);    }    @Test    public void addRecord1() throws Exception{        if(StringUtil.strIsNull(token)) {            ResultActions resultActionsToken = this.mockMvc.                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")                            .param("phone", mobile)                            .param("password", "123456")                            .param("qcellcoreId", "1")                    );            MvcResult mvcResultToken = resultActionsToken.andReturn();            logger.info("=====generalLogin mvcResult:" + mvcResultToken.getResponse().getStatus());            String resultToken = mvcResultToken.getResponse().getContentAsString();            logger.info("=====generalLogin result :" + resultToken);            JSONObject jsonObject = JSONObject.fromObject(resultToken);            String tokenJson = jsonObject.optString("data");            String parents = JSONObject.fromObject(tokenJson).optString("parents");            String token = JSONObject.fromObject(parents).optString("token");            logger.info("=====generalLogin result token:" + token);        }        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addRecord")                        .param("token",token)                        .param("type","1")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====addRecord mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====addRecord result:" + result);        ResultActions resultActionsTokenXydExperienceRecord = this.mockMvc.                perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")                                .param("phone", "15848979999")                                .param("password", "123456")                                .param("qcellcoreId", "1")                );        MvcResult mvcResultTokenXydExperienceRecord = resultActionsTokenXydExperienceRecord.andReturn();        String resultTokenXydExperienceRecord = mvcResultTokenXydExperienceRecord.getResponse().getContentAsString();        JSONObject jsonObjectXydExperienceRecord = JSONObject.fromObject(resultTokenXydExperienceRecord);        String tokenJsonXydExperienceRecord = jsonObjectXydExperienceRecord.optString("data");        String parentsXydExperienceRecord = JSONObject.fromObject(tokenJsonXydExperienceRecord).optString("parents");        String tokenXydExperienceRecord = JSONObject.fromObject(parentsXydExperienceRecord).optString("token");        ResultActions resultActionsXydExperienceRecord = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST, reqUrl + "/addRecord")                                .param("token", tokenXydExperienceRecord)                                .param("type", "1")                );        MvcResult mvcResultXydExperienceRecord = resultActionsXydExperienceRecord.andReturn();        System.out.println("=====addRecord mvcResultXydExperienceRecord:" + mvcResultXydExperienceRecord.getResponse().getStatus());        String resultXydExperienceRecord = mvcResultXydExperienceRecord.getResponse().getContentAsString();        System.out.println("=====addRecord resultXydExperienceRecord:" + resultXydExperienceRecord);        ResultActions resultActionsUserIsNull = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST, reqUrl + "/addRecord")                                .param("token", "abcdefg")                                .param("type", "1")                );        MvcResult mvcResultUserIsNull = resultActionsUserIsNull.andReturn();        System.out.println("=====addRecord mvcResultUserIsNull:" + mvcResultUserIsNull.getResponse().getStatus());        String resultUserIsNull = mvcResultUserIsNull.getResponse().getContentAsString();        System.out.println("=====addRecord resultUserIsNull:" + resultUserIsNull);    }}