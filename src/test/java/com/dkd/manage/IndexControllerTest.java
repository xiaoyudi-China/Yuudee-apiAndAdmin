package com.dkd.manage;import com.dkd.XiaoyudiApplication;import com.dkd.common.config.Global;import com.dkd.common.utils.MyMD5Util;import com.dkd.model.XydManageAccount;import com.dkd.service.XydManageAccountService;import com.dkd.service.impl.XydManageAccountServiceImpl;import com.dkd.service.impl.XydManageRoleServiceImpl;import org.junit.After;import org.junit.Before;import org.junit.Test;import org.junit.runner.RunWith;import org.mockito.InjectMocks;import org.mockito.Mock;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.http.HttpMethod;import org.springframework.mock.web.MockHttpSession;import org.springframework.test.annotation.Rollback;import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;import org.springframework.test.web.servlet.MockMvc;import org.springframework.test.web.servlet.MvcResult;import org.springframework.test.web.servlet.ResultActions;import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;import org.springframework.test.web.servlet.setup.MockMvcBuilders;import org.springframework.transaction.annotation.Transactional;import java.util.ArrayList;import java.util.List;import java.util.UUID;import static org.powermock.api.mockito.PowerMockito.whenNew;@RunWith(SpringJUnit4ClassRunner.class)@SpringBootTest(classes = XiaoyudiApplication.class, properties = "/application.properties")@AutoConfigureMockMvc@Transactionalpublic class IndexControllerTest {    private final Logger logger = LoggerFactory.getLogger(IndexControllerTest.class);    private MockMvc mockMvc;    @Autowired    private IndexController indexController;    private String reqUrl = "/manage/account";    @Before    public void setUp() throws Exception {        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();    }    @After    public void tearDown() throws Exception {    }    @Test    public void tologinPage() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/toLogin"));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====tologinPage mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====tologinPage result:" + result);    }    @Test    public void toIndex() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/"));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====index mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====index result:" + result);    }    @Test    public void toindexPage() throws Exception {        MockHttpSession session = new MockHttpSession();        XydManageAccount xydManageAccount = new XydManageAccount();        xydManageAccount.setId(19);        session.setAttribute("user",xydManageAccount);        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/toindexPage")                        .session(session));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====toindexPage mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====toindexPage result:" + result);    }    @Test    public void login() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")                        .param("username","ceshi")                        .param("password","123456"));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====login mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====login result:" + result);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")                        .param("username","ceshi")                        .param("password","123"));        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====login mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====login result:" + result2);        ResultActions resultActions3 = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")                        .param("username","admin")                                .param("password","123")                        );        MvcResult mvcResult3 = resultActions3.andReturn();        System.out.println("=====login mvcResult:" + mvcResult3.getResponse().getStatus());        String result3 = mvcResult3.getResponse().getContentAsString();        System.out.println("=====login result:" + result3);        ResultActions resultActions4 = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")                        .param("username","dangdang")                        .param("password","123456")                );        MvcResult mvcResult4 = resultActions4.andReturn();        System.out.println("=====login mvcResult:" + mvcResult4.getResponse().getStatus());        String result4 = mvcResult4.getResponse().getContentAsString();        System.out.println("=====login result:" + result4);    }    @Test    public void outlogin() throws Exception {        MockHttpSession session = new MockHttpSession();        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.GET,reqUrl+"/loginOut").session(session));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====loginOut mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====loginOut result:" + result);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.GET,reqUrl+"/loginOut"));        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====loginOut mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====loginOut result:" + result2);    }    @Test    public void updatePassword() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/updatePassword")                        .param("oldPassword","123")                        .param("newPassword","123456")                        .param("accountId","33"));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====updatePassword result:" + result);        ResultActions resultActions4 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/updatePassword")                        .param("oldPassword","123456")                        .param("newPassword","123456")                        .param("accountId","33"));        MvcResult mvcResult4 = resultActions4.andReturn();        System.out.println("=====updatePassword mvcResult:" + mvcResult4.getResponse().getStatus());        String result4 = mvcResult4.getResponse().getContentAsString();        System.out.println("=====updatePassword result:" + result4);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/updatePassword")                        .param("oldPassword","123456")                        .param("newPassword","123456")                        );        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====updatePassword mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====updatePassword result:" + result2);        ResultActions resultActions3 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/updatePassword")                        .param("oldPassword","123456")                        .param("newPassword","123456")                        .param("accountId","0")                        );        MvcResult mvcResult3 = resultActions3.andReturn();        System.out.println("=====updatePassword mvcResult:" + mvcResult3.getResponse().getStatus());        String result3 = mvcResult3.getResponse().getContentAsString();        System.out.println("=====updatePassword result:" + result3);    }    @Test    public void getUserAccessList() throws Exception {        MockHttpSession session = new MockHttpSession();         XydManageAccount xydManageAccount = new XydManageAccount();        xydManageAccount.setId(19);        List<XydManageAccount> xydManageAccounts = new ArrayList<>();        xydManageAccounts.add(xydManageAccount);        ResultActions resultActions0 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")                        .param("id","19")                );        MvcResult mvcResult0 = resultActions0.andReturn();        System.out.println("=====getUserAccessList mvcResult:" + mvcResult0.getResponse().getStatus());        String result0 = mvcResult0.getResponse().getContentAsString();        System.out.println("=====getUserAccessList result:" + result0);        session.setAttribute("AccessList"+xydManageAccount.getId(),xydManageAccounts);        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList").session(session)                        .param("id","19")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====getUserAccessList mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====getUserAccessList result:" + result);        //when(xydManageAccountService.selectByPrimaryKey(19)).thenReturn(null);        ResultActions resultActions1 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")                        .param("id","26")                );        MvcResult mvcResult1 = resultActions1.andReturn();        System.out.println("=====getUserAccessList mvcResult:" + mvcResult1.getResponse().getStatus());        String result1 = mvcResult1.getResponse().getContentAsString();        System.out.println("=====getUserAccessList result:" + result1);        ResultActions resultActions3 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")                        .param("id","30")                );        MvcResult mvcResult3 = resultActions3.andReturn();        System.out.println("=====getUserAccessList mvcResult:" + mvcResult3.getResponse().getStatus());        String result3 = mvcResult3.getResponse().getContentAsString();        System.out.println("=====getUserAccessList result:" + result3);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")                        .session(session)                );        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====getUserAccessList mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====getUserAccessList result:" + result2);    }    @Test    public void getAccountList() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getAccountList"));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====getAccountList mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====getAccountList result:" + result);    }    @Test    public void addAccountPage() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/addAccountPage"));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====addAccountPage mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====addAccountPage result:" + result);    }    @Test    public void addAccount() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/addAccount")                        .param("name","1231")                        .param("phone","17795591253")                        .param("account","admin")                        .param("roleId","1")                        .param("password","123456")                        .param("roleIds","1,2,3")                        .param("id","1")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====addAccount mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====addAccount result:" + result);        ResultActions resultActions1 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/addAccount")                        .param("name","ceshi")                        .param("phone","17795591253")                        .param("account","admin")                        .param("roleId","")                        .param("password","123456")                        .param("roleIds","")                        .param("id","1")                );        MvcResult mvcResult1 = resultActions1.andReturn();        System.out.println("=====addAccount mvcResult:" + mvcResult1.getResponse().getStatus());        String result1 = mvcResult1.getResponse().getContentAsString();        System.out.println("=====addAccount result:" + result1);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/addAccount")                        .param("name","1231")                        .param("phone","17795591253")                        .param("account","admin")                        .param("roleId","1")                        .param("password","123456")                        .param("roleIds","1,21,3")                        .param("id","1")                );        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====addAccount mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====addAccount result:" + result2);        ResultActions resultActions3 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/addAccount")                        .param("name","1231")                        .param("phone","17795591253")                        .param("account","admin")                        .param("roleId","1")                        .param("password","123456")                        .param("roleIds","1,21,3")                );        MvcResult mvcResult3 = resultActions3.andReturn();        System.out.println("=====addAccount mvcResult:" + mvcResult3.getResponse().getStatus());        String result3 = mvcResult3.getResponse().getContentAsString();        System.out.println("=====addAccount result:" + result3);        String account = "dx_"+ UUID.randomUUID();        ResultActions resultActions4 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/addAccount")                        .param("name","1231")                        .param("phone","17795591253")                        .param("account",account)                        .param("roleId","1")                        .param("password","123456")                        .param("roleIds","1,21,3")                );        MvcResult mvcResult4 = resultActions4.andReturn();        System.out.println("=====addAccount mvcResult:" + mvcResult4.getResponse().getStatus());        String result4 = mvcResult4.getResponse().getContentAsString();        System.out.println("=====addAccount result:" + result4);        ResultActions resultActions5 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/addAccount")                        .param("name","1231")                        .param("phone","17795591253")                        .param("account","admin")                        .param("roleId","1")                        .param("password","123456")                        .param("roleIds","1,2,3")                        .param("id","2")                );        MvcResult mvcResult5 = resultActions5.andReturn();        System.out.println("=====addAccount mvcResult:" + mvcResult5.getResponse().getStatus());        String result5 = mvcResult5.getResponse().getContentAsString();        System.out.println("=====addAccount result:" + result5);    }    @Test    public void getAccountListAjax() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getAccountList.ajax")                        .param("start","1")                        .param("length","10"));        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====getAccountList mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====getAccountList result:" + result);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getAccountList.ajax")                        .param("start","1")                        .param("length","10")                        .param("accountName","dddd")                );        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====getAccountList mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====getAccountList result:" + result2);        ResultActions resultActions3 = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/getAccountList.ajax")                        .param("start","1")                        .param("length","10")                        .param("phone","17310000001")                );        MvcResult mvcResult3 = resultActions3.andReturn();        System.out.println("=====getAccountList mvcResult:" + mvcResult3.getResponse().getStatus());        String result3 = mvcResult3.getResponse().getContentAsString();        System.out.println("=====getAccountList result:" + result3);    }    @Test    @Rollback    public void deleteAccount() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/deleteAccount")                        .param("accountId","34")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====deleteAccount mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====deleteAccount result:" + result);        ResultActions resultActions1 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/deleteAccount")                        .param("accountId","0")                );        MvcResult mvcResult1 = resultActions1.andReturn();        System.out.println("=====deleteAccount mvcResult:" + mvcResult1.getResponse().getStatus());        String result1 = mvcResult1.getResponse().getContentAsString();        System.out.println("=====deleteAccount result:" + result1);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/deleteAccount")                );        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====deleteAccount mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====deleteAccount result:" + result2);    }    @Test    public void resetPassword() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/resetPassword")                        .param("accountId","32")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====resetPassword mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====resetPassword result:" + result);        ResultActions resultActions1 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/resetPassword")                        .param("accountId","0")                );        MvcResult mvcResult1 = resultActions1.andReturn();        System.out.println("=====resetPassword mvcResult:" + mvcResult1.getResponse().getStatus());        String result1 = mvcResult1.getResponse().getContentAsString();        System.out.println("=====resetPassword result:" + result1);        ResultActions resultActions2 = this.mockMvc.                perform(MockMvcRequestBuilders                        .request(HttpMethod.POST,reqUrl+"/resetPassword")                        .param("accountId","")                );        MvcResult mvcResult2 = resultActions2.andReturn();        System.out.println("=====resetPassword mvcResult:" + mvcResult2.getResponse().getStatus());        String result2 = mvcResult2.getResponse().getContentAsString();        System.out.println("=====resetPassword result:" + result2);    }    @Test    public void infoAccount() throws Exception {        ResultActions resultActions = this.mockMvc.                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/infoAccount")                        .param("accountId","1")                );        MvcResult mvcResult = resultActions.andReturn();        System.out.println("=====infoAccount mvcResult:" + mvcResult.getResponse().getStatus());        String result = mvcResult.getResponse().getContentAsString();        System.out.println("=====infoAccount result:" + result);    }}