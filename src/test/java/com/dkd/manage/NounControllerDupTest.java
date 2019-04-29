package com.dkd.manage;

import com.aliyun.oss.OSSClient;
import com.dkd.common.utils.FileUtils;
import com.dkd.common.utils.IsObjectNullUtils;
import com.dkd.model.XydNounSense;
import com.dkd.model.XydNounTest;
import com.dkd.model.XydNounTraining;
import com.dkd.model.XydTrainingHelptime;
import com.dkd.service.XydNounSenseService;
import com.dkd.service.XydNounTestService;
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
import org.springframework.mock.web.MockMultipartFile;
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
@PrepareForTest(value = {NounController.class, IsObjectNullUtils.class,FileUtils.class})
public class NounControllerDupTest {

    private final Logger logger = LoggerFactory.getLogger(NounControllerTest.class);

    private MockMvc mockMvc;
    @InjectMocks
    private NounController nounController;
    @Mock
    private XydTrainingHelpService trainingHelpService;
    @Mock
    private XydNounTrainingService nounTrainingService;
    @Mock
    private XydNounTestService nounTestService;
    @Mock
    private XydNounSenseService nounSenseService;
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

        ResultActions resultActions3 = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/setHelpTimes")
                        .param("states1","1")
                        .param("states2","2")
                        .param("states4","4")
                        .param("topicType","4")
                );
        MvcResult mvcResult3 = resultActions3.andReturn();
        System.out.println("=====setHelpTimes mvcResult:" + mvcResult3.getResponse().getStatus());
        String result3 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====setHelpTimes result:" + result3);

        when(trainingHelpService.insert(xydTrainingHelptime)).thenReturn(0);
        ResultActions resultActions4 = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/setHelpTimes")
                        .param("states1","1")
                        .param("states2","2")
                        .param("states3","3")
                        .param("states4","4")
                        .param("topicType","4")
                );
        MvcResult mvcResult4 = resultActions4.andReturn();
        System.out.println("=====setHelpTimes mvcResult:" + mvcResult4.getResponse().getStatus());
        String result4 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====setHelpTimes result:" + result4);

        ResultActions resultActions5 = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/setHelpTimes")
                        .param("states1","1")
                        .param("states2","2")
                        .param("states3","3")
                        .param("states4","4")
                        .param("topicType","5")
                );
        MvcResult mvcResult5 = resultActions5.andReturn();
        System.out.println("=====setHelpTimes mvcResult:" + mvcResult5.getResponse().getStatus());
        String result5 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====setHelpTimes result:" + result5);
    }
    @Test
    public void addNounTraining() throws Exception {
        XydNounTraining xydNounTraining = PowerMockito.mock(XydNounTraining.class);
        whenNew(XydNounTraining.class).withNoArguments().thenReturn(xydNounTraining);
        OSSClient ossClient = PowerMockito.mock(OSSClient.class);
        whenNew(OSSClient.class).withAnyArguments().thenReturn(ossClient);
        PowerMockito.mockStatic(IsObjectNullUtils.class);
        when(IsObjectNullUtils.is(xydNounTraining)).thenReturn(true);
        MockMultipartFile wireImage = new MockMultipartFile("wireImage", "1.jpeg", "image/jpeg", "some xml".getBytes());
        MockMultipartFile wireRecord = new MockMultipartFile("wireRecord", "2.jpeg", "image/jpeg", "some other type".getBytes());
        MockMultipartFile groupImage = new MockMultipartFile("groupImage", "3.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile colorPenRecord = new MockMultipartFile("colorPenRecord", "4.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile groupRecord = new MockMultipartFile("groupRecord", "5.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());

        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.fileUpload(reqUrl+"/addNounTraining")
                        .file(wireImage)
                        .file(wireRecord)
                        .file(groupImage)
                        .file(colorPenRecord)
                        .file(groupRecord)
                        .param("wireChar","11")
                        .param("colorPenChar","11")
                        .param("groupWord","111")

                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addNounTraining mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addNounTraining result:" + result);

        when(IsObjectNullUtils.is(xydNounTraining)).thenReturn(false);
        when(nounTrainingService.insert(xydNounTraining)).thenReturn(0);
        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders.fileUpload(reqUrl+"/addNounTraining")
                        .file(wireImage)
                        .file(wireRecord)
                        .file(groupImage)
                        .file(colorPenRecord)
                        .file(groupRecord)
                        .param("wireChar","11")
                        .param("colorPenChar","11")
                        .param("groupWord","111")

                );
        MvcResult mvcResult1 = resultActions1.andReturn();
        System.out.println("=====addNounTraining mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====addNounTraining result:" + result1);
    }

    @Test
    public void addNounTest() throws Exception{
        OSSClient ossClient = PowerMockito.mock(OSSClient.class);
        whenNew(OSSClient.class).withAnyArguments().thenReturn(ossClient);
        XydNounTest xydNounTest = PowerMockito.mock(XydNounTest.class);
        whenNew(XydNounTest.class).withNoArguments().thenReturn(xydNounTest);
        when(nounTestService.insert(xydNounTest)).thenReturn(0);
        MockMultipartFile groupImage = new MockMultipartFile("groupImage", "1.jpeg", "image/jpeg", "some xml".getBytes());
        MockMultipartFile cardOneImage = new MockMultipartFile("cardOneImage", "2.jpeg", "image/jpeg", "some other type".getBytes());
        MockMultipartFile cardOneRecord = new MockMultipartFile("cardOneRecord", "3.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile colorPenRecord = new MockMultipartFile("cardTwoImage", "4.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile cardTwoRecord = new MockMultipartFile("cardTwoRecord", "5.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile groupRecord = new MockMultipartFile("groupRecord", "5.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.fileUpload(reqUrl+"/addNounTest")
                        .file(groupImage)
                        .file(cardOneImage)
                        .file(cardOneRecord)
                        .file(cardTwoRecord)
                        .file(colorPenRecord)
                        .file(groupRecord)
                        .param("cardColorChar","1")
                        .param("fristAssistTime","10")
                        .param("cardWireChar","10")
                        .param("secondAssistTime","10")
                        .param("groupChar","10")
                );


        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====updateNounTraining mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updateNounTraining result:" + result);
    }

    @Test
    public void updateNounTest() throws Exception{
        PowerMockito.mockStatic(IsObjectNullUtils.class);
        OSSClient ossClient = PowerMockito.mock(OSSClient.class);
        whenNew(OSSClient.class).withAnyArguments().thenReturn(ossClient);
        XydNounTest xydNounTest = PowerMockito.mock(XydNounTest.class);
        whenNew(XydNounTest.class).withNoArguments().thenReturn(xydNounTest);
        when(IsObjectNullUtils.is(xydNounTest)).thenReturn(true);
        MockMultipartFile groupImage = new MockMultipartFile("groupImage", "1.jpeg", "image/jpeg", "some xml".getBytes());
        MockMultipartFile cardOneImage = new MockMultipartFile("cardOneImage", "2.jpeg", "image/jpeg", "some other type".getBytes());
        MockMultipartFile cardOneRecord = new MockMultipartFile("cardOneRecord", "3.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile colorPenRecord = new MockMultipartFile("cardTwoImage", "4.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile cardTwoRecord = new MockMultipartFile("cardTwoRecord", "5.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile groupRecord = new MockMultipartFile("groupRecord", "5.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.fileUpload(reqUrl+"/updateNounTest")
                        .file(groupImage)
                        .file(cardOneImage)
                        .file(cardOneRecord)
                        .file(cardTwoRecord)
                        .file(colorPenRecord)
                        .file(groupRecord)
                        .param("cardColorChar","1")
                        .param("fristAssistTime","10")
                        .param("cardWireChar","10")
                        .param("secondAssistTime","10")
                        .param("groupChar","10")
                        .param("id","58")
                );


        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====updateNounTraining mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updateNounTraining result:" + result);

        when(IsObjectNullUtils.is(xydNounTest)).thenReturn(false);
        when(nounTestService.insert(xydNounTest)).thenReturn(0);
        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders.fileUpload(reqUrl+"/updateNounTest")
                        .file(groupImage)
                        .file(cardOneImage)
                        .file(cardOneRecord)
                        .file(cardTwoRecord)
                        .file(colorPenRecord)
                        .file(groupRecord)
                        .param("cardColorChar","1")
                        .param("fristAssistTime","10")
                        .param("cardWireChar","10")
                        .param("secondAssistTime","10")
                        .param("groupChar","10")
                        .param("id","58")
                );


        MvcResult mvcResult1 = resultActions1.andReturn();
        System.out.println("=====updateNounTraining mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====updateNounTraining result:" + result1);
    }

    @Test
    public void addNounSense() throws Exception{
        XydNounSense xydNounSense = PowerMockito.mock(XydNounSense.class);
        whenNew(XydNounSense.class).withNoArguments().thenReturn(xydNounSense);
        OSSClient ossClient = PowerMockito.mock(OSSClient.class);
        whenNew(OSSClient.class).withAnyArguments().thenReturn(ossClient);
        when(nounSenseService.insert(xydNounSense)).thenReturn(0);
        MockMultipartFile groupImage = new MockMultipartFile("groupImage", "1.jpeg", "image/jpeg", "some xml".getBytes());
        MockMultipartFile cardOneImage = new MockMultipartFile("cardOneImage", "2.jpeg", "image/jpeg", "some other type".getBytes());
        MockMultipartFile cardOneRecord = new MockMultipartFile("cardOneRecord", "3.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile colorPenRecord = new MockMultipartFile("cardTwoImage", "4.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile cardTwoRecord = new MockMultipartFile("cardTwoRecord", "5.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        MockMultipartFile groupRecord = new MockMultipartFile("groupRecord", "5.jpeg", "image/jpeg", "{\"json\": \"someValue\"}".getBytes());
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.fileUpload(reqUrl+"/addNounSense")
                        .file(groupImage)
                        .file(cardOneImage)
                        .file(cardOneRecord)
                        .file(cardTwoRecord)
                        .file(colorPenRecord)
                        .file(groupRecord)
                        .param("cardAdjectiveChar","1")
                        .param("fristAssistTime","10")
                        .param("cardNounChar","10")
                        .param("secondAssistTime","10")
                        .param("groupChar","10")
                        .param("disturbType","10")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====addNounSense mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====addNounSense result:" + result);
    }
}
