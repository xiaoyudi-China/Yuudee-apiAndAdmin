package com.dkd.api;

import com.dkd.XiaoyudiApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by mai xiaogang on 2019/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XiaoyudiApplication.class,properties = "/application.properties")
@AutoConfigureMockMvc
@Transactional
public class TestAppTest {
    private MockMvc mockMvc;
    @Autowired
    private TestApp testApp;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(testApp).build();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCode() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/app/code")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        /*ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/app/code"));*/
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====客户端获得反馈状态:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + result);

    }

}