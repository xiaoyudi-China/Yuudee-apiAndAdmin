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
public class SystemAppControllerTest {
    private final Logger logger = LoggerFactory.getLogger(SystemAppControllerTest.class);

    private MockMvc mockMvc;
    @Autowired
    private SystemAppController systemAppController;
    private String reqUrl = "/app/system";
    @Autowired
    private RedisService redisService;

    private String loginReqUrl = "/app/user";

    private  String token;
    private  String mobile="18844272334";
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String tokenCacheKey = "userToken"+mobile;
        Object tokenObj = redisService.get(tokenCacheKey);
        if(tokenObj==null) {
            ResultActions resultActionsToken = this.mockMvc.
                    perform(MockMvcRequestBuilders.post(loginReqUrl + "/generalLogin")
                            .param("phone", mobile)
                            .param("password", "123456")
                            .param("qcellcoreId", "1")
                    );
            MvcResult mvcResultToken = resultActionsToken.andReturn();
            logger.info("=====generalLogin mvcResult:" + mvcResultToken.getResponse().getStatus());
            String resultToken = mvcResultToken.getResponse().getContentAsString();
            logger.info("=====generalLogin result :" + resultToken);
            JSONObject jsonObject = JSONObject.fromObject(resultToken);
            String tokenJson = jsonObject.optString("data");
            String parents = JSONObject.fromObject(tokenJson).optString("parents");
            token = JSONObject.fromObject(parents).optString("token");
            redisService.set(tokenCacheKey,token,24*60*60L);
            logger.info("=====generalLogin result token:" + token);
        }else {
            System.err.println("已登录"+tokenCacheKey+":"+tokenObj);
            token = String.valueOf(tokenObj);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fileUpload() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/oss/fileUpload")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====fileUpload mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====fileUpload result:" + result);
    }

    @Test
    public void deleteFileUpload() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/oss/deleteFileUpload")
                        .param("image","1")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====deleteFileUpload mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====deleteFileUpload result:" + result);
    }

    @Test
    public void getQcellcoreList() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/qcellcoreList")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getQcellcoreList mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getQcellcoreList result:" + result);
    }

    @Test
    public void productInfo() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/productInfo")
                        .param("type","1")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====productInfo mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====productInfo result:" + result);

        ResultActions resultActionsOther = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/productInfo")
                        .param("type","89999")

                );
        MvcResult mvcResultOther = resultActionsOther.andReturn();
        System.out.println("=====productInfo mvcResult:" + mvcResultOther.getResponse().getStatus());
        String resultOther = mvcResultOther.getResponse().getContentAsString();
        System.out.println("=====productInfo result:" + resultOther);
    }

    @Test
    public void getAboutUs() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/aboutUs")
                        .param("versionsNumber","1")
                        .param("type","1")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getAboutUs mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getAboutUs result:" + result);


        ResultActions resultActionsOther = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/aboutUs")
                        .param("versionsNumber","1")
                        .param("type","1")
                        .param("token",token)

                );
        MvcResult mvcResultOther = resultActionsOther.andReturn();
        System.out.println("=====getAboutUs mvcResult:" + mvcResultOther.getResponse().getStatus());
        String resultOther = mvcResultOther.getResponse().getContentAsString();
        System.out.println("=====getAboutUs result:" + resultOther);


        ResultActions resultActionsOtherTwo = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/aboutUs")
                        .param("versionsNumber","1.0")
                        .param("type","2")
                        .param("token",token)

                );
        MvcResult mvcResultOtherTwo = resultActionsOtherTwo.andReturn();
        System.out.println("=====getAboutUs mvcResult:" + mvcResultOtherTwo.getResponse().getStatus());
        String resultOtherTwo = mvcResultOtherTwo.getResponse().getContentAsString();
        System.out.println("=====getAboutUs result:" + resultOtherTwo);
    }

    @Test
    public void getAppStartImage() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/appStartImage")
                        .param("type","1")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getAppStartImage mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getAppStartImage result:" + result);


        ResultActions resultActionsOther = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/appStartImage")
                        .param("type","10")

                );
        MvcResult mvcResultOther = resultActionsOther.andReturn();
        System.out.println("=====getAppStartImage mvcResult:" + mvcResultOther.getResponse().getStatus());
        String resultOther = mvcResultOther.getResponse().getContentAsString();
        System.out.println("=====getAppStartImage result:" + resultOther);

    }

   @Test
    public void getCityList() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getCityList")
                        .param("level","1")
                        .param("parentId","1")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getCityList mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getCityList result:" + result);
    }


    @Test
    public void getThisCityList() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getThisCityList")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getThisCityList mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getThisCityList result:" + result);
    }


    @Test
    public void verifyPhone() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/verify/phoneNumber")
                        .param("phone","17795591253")
                        .param("qcellcoreId","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====verifyPhone mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====verifyPhone result:" + result);


        ResultActions resultActionsOther = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/verify/phoneNumber")
                        .param("phone","")
                        .param("qcellcoreId","1")
                );
        MvcResult mvcResultOther = resultActionsOther.andReturn();
        System.out.println("=====verifyPhone mvcResult:" + mvcResultOther.getResponse().getStatus());
        String resultOther = mvcResultOther.getResponse().getContentAsString();
        System.out.println("=====verifyPhone result:" + resultOther);

        ResultActions resultActionsOtherTwo = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/verify/phoneNumber")
                        .param("phone","17795591253")
                        .param("qcellcoreId","")
                );
        MvcResult mvcResultOtherTwo = resultActionsOtherTwo.andReturn();
        System.out.println("=====verifyPhone mvcResult:" + mvcResultOtherTwo.getResponse().getStatus());
        String resultOtherTwo = mvcResultOtherTwo.getResponse().getContentAsString();
        System.out.println("=====verifyPhone result:" + resultOtherTwo);

        ResultActions resultActionsOtherThree = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/verify/phoneNumber")
                        .param("phone","17795591253")
                        .param("qcellcoreId","999")
                );
        MvcResult mvcResultOtherThree = resultActionsOtherThree.andReturn();
        System.out.println("=====verifyPhone mvcResult:" + mvcResultOtherThree.getResponse().getStatus());
        String resultOtherThree = mvcResultOtherThree.getResponse().getContentAsString();
        System.out.println("=====verifyPhone result:" + resultOtherThree);
    }
}