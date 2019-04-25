package com.dkd.api;

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
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

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

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(systemAppController).build();
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
    }
}