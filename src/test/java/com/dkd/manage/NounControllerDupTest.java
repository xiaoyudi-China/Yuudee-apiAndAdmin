package com.dkd.manage;

import com.dkd.common.utils.IsObjectNullUtils;
import com.dkd.model.XydTrainingHelptime;
import com.dkd.service.XydNounTrainingService;
import com.dkd.service.XydTrainingHelpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * @author dongxiaohong
 * @date 2019/4/28 18:53
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(value = {NounController.class, IsObjectNullUtils.class})
public class NounControllerDupTest {

    private final Logger logger = LoggerFactory.getLogger(NounControllerTest.class);

    private MockMvc mockMvc;
    @InjectMocks
    private NounController nounController;
    @Mock
    private XydTrainingHelpService trainingHelpService;
    @Mock
    private XydNounTrainingService nounTrainingService;
    private String reqUrl = "/manage/noun";

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(nounController).build();
    }

    @Test
    public void setHelpTimes() throws Exception{
        XydTrainingHelptime xydTrainingHelptime1 = new XydTrainingHelptime();
        XydTrainingHelptime xydTrainingHelptime = PowerMockito.mock(XydTrainingHelptime.class);
        PowerMockito.mockStatic(IsObjectNullUtils.class);
        whenNew(XydTrainingHelptime.class).withNoArguments().thenReturn(xydTrainingHelptime);
        when(trainingHelpService.selectByEntity(xydTrainingHelptime)).thenReturn(xydTrainingHelptime1);
        when(trainingHelpService.insert(xydTrainingHelptime1)).thenReturn(0);
        when(IsObjectNullUtils.is(xydTrainingHelptime1)).thenReturn(true);
        ResultActions resultActions0 = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/setHelpTimes")
                        .param("states1","1")
                        .param("states2","2")
                        .param("states3","3")
                        .param("states4","4")
                        .param("topicType","1")
                );
        MvcResult mvcResult0 = resultActions0.andReturn();
        System.out.println("=====setHelpTimes mvcResult:" + mvcResult0.getResponse().getStatus());
        String result0 = mvcResult0.getResponse().getContentAsString();
        System.out.println("=====setHelpTimes result:" + result0);

        when(IsObjectNullUtils.is(xydTrainingHelptime1)).thenReturn(false);
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/setHelpTimes")
                        .param("states1","1")
                        .param("states2","2")
                        .param("states3","3")
                        .param("states4","4")
                        .param("topicType","1")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====setHelpTimes mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====setHelpTimes result:" + result);

        List<XydTrainingHelptime> xydTrainingHelptimes = new ArrayList<>();
        xydTrainingHelptimes.add(xydTrainingHelptime1);
        when(IsObjectNullUtils.is(xydTrainingHelptime1)).thenReturn(false);
        when(trainingHelpService.selectByEntityList(xydTrainingHelptime)).thenReturn(new ArrayList<>());
        when(trainingHelpService.insert(xydTrainingHelptime)).thenReturn(1);
        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/setHelpTimes")
                        .param("states1","1")
                        .param("states2","2")
                        .param("states3","3")
                        .param("states4","4")
                        .param("topicType","3")
                );
        MvcResult mvcResult1 = resultActions1.andReturn();
        System.out.println("=====setHelpTimes mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====setHelpTimes result:" + result1);

        when(trainingHelpService.insert(xydTrainingHelptime)).thenReturn(0);
        ResultActions resultActions2 = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/setHelpTimes")
                        .param("states1","1")
                        .param("states2","2")
                        .param("states3","3")
                        .param("states4","4")
                        .param("topicType","3")
                );
        MvcResult mvcResult2 = resultActions2.andReturn();
        System.out.println("=====setHelpTimes mvcResult:" + mvcResult2.getResponse().getStatus());
        String result2 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====setHelpTimes result:" + result2);
    }
}
