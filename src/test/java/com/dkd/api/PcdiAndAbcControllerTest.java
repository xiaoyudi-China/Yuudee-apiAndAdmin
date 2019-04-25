package com.dkd.api;

import com.dkd.XiaoyudiApplication;
import com.google.gson.Gson;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XiaoyudiApplication.class, properties = "/application.properties")
@AutoConfigureMockMvc
@Transactional
public class PcdiAndAbcControllerTest {
    private final Logger logger = LoggerFactory.getLogger(PcdiAndAbcControllerTest.class);

    private MockMvc mockMvc;
    @Autowired
    private PcdiAndAbcController pcdiAndAbcController;
    private String reqUrl = "/app/question";

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(pcdiAndAbcController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getGradeRule() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getPcdi/gradeRule")
                        .param("type","1")
                        .param("isOptional","1")
                        .param("topicType","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getGradeRule mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getGradeRule result:" + result);
    }

    @Test
    public void toPcdiMustPage() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getPcdi/must")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====toPcdiMustPage mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====toPcdiMustPage result:" + result);
    }

    @Test
    public void mustAndResultPage() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getPcdi/mustAndResult")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("answerId","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====mustAndResultPage mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====mustAndResultPage result:" + result);
    }

    @Test
    public void toPcdiOptionalPage() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getPcdi/optional")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====toPcdiOptionalPage mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====toPcdiOptionalPage result:" + result);
    }

    @Test
    public void optionalAndResult() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getPcdi/optionalAndResult")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("answerId","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====optionalAndResult mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====optionalAndResult result:" + result);
    }

    @Test
    public void getABC() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getABC")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getABC mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getABC result:" + result);
    }

    @Test
    public void getABCoutResult() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getABC/outResult")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("answerId","1")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getABCoutResult mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getABCoutResult result:" + result);
    }

    @Test
    public void addPcdiResult() throws Exception{
        List<Map<String ,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("1",455);
        list.add(map);
        Gson gson =new Gson();
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addPcdiResult")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("score","60")
                        .param("nounCount","3")
                        .param("verbCount","5")
                        .param("adjCount","7")
                        .param("pcdiCache","true")
                        .param("resultList", gson.toJson(list))

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addPcdiResult mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addPcdiResult result:" + result);
    }

    @Test
    public void addPcdiOutResult() throws Exception{
        List<Map<String ,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("1",455);
        list.add(map);
        Gson gson =new Gson();
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addPcdiOutResult")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("resultList",gson.toJson(list))
                        .param("mustId","1")
                        .param("count","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addPcdiOutResult mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addPcdiOutResult result:" + result);
    }

    @Test
    public void addABCResult() throws Exception{
        List<Map<String ,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("1",455);
        list.add(map);
        Gson gson =new Gson();
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addABCResult")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("score","60")
                        .param("resultList",gson.toJson(list))
                        .param("pcdiCache","true")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addABCResult mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addABCResult result:" + result);
    }

    @Test
    public void getRePortInfo() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getReportInfo")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("type","1")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getRePortInfo mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getRePortInfo result:" + result);
    }

    @Test
    public void getList() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.GET,reqUrl+"/getCache")
                        .param("token","sK5bS68IyDHn3LcmtEoUoA==")
                        .param("type","1")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getList mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getList result:" + result);
    }
}