package com.dkd.manage;

import com.dkd.common.config.Global;
import com.dkd.common.utils.MyMD5Util;
import com.dkd.model.XydManageAccount;
import com.dkd.service.impl.XydManageAccountServiceImpl;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author dongxiaohong
 * @date 2019/4/28 09:51
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(value = {MyMD5Util.class,XydManageAccount.class})
public class IndexControllerDupTest {
    private final Logger logger = LoggerFactory.getLogger(IndexControllerDupTest.class);
    private MockMvc mockMvc;
    @InjectMocks
    private IndexController indexController;
    private String reqUrl = "/manage/account";

    @Mock
    XydManageAccountServiceImpl xydManageAccountService;
    @Mock
    Global global;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test

    public void login() throws Exception {
        List<XydManageAccount> xydManageAccounts = new ArrayList<>();
        XydManageAccount xydManageAccount =new XydManageAccount();
        xydManageAccount.setAccount("ceshi");
        xydManageAccount.setPassword("12345678");
        xydManageAccounts.add(xydManageAccount);
        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.validPassword("123456","12345678")).thenThrow(new UnsupportedEncodingException());
        when(xydManageAccountService.selectByAccount("ceshi")).thenReturn(xydManageAccounts);
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")
                        .param("username","ceshi")
                        .param("password","123456"));
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====login mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====login result:" + result);

        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")
                        .param("password","123456"));
        MvcResult mvcResult1 = resultActions1.andReturn();
        System.out.println("=====login mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====login result:" + result1);
    }
    @Test
    public void outlogin() throws Exception{
        ResultActions resultActions2 = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.GET,reqUrl+"/loginOut")
                );
        MvcResult mvcResult2 = resultActions2.andReturn();
        System.out.println("=====loginOut mvcResult:" + mvcResult2.getResponse().getStatus());
        String result2 = mvcResult2.getResponse().getContentAsString();
        System.out.println("=====loginOut result:" + result2);

    }
    @Test
    public void updatePassword() throws Exception {
        XydManageAccount xydManageAccount =new XydManageAccount();
        xydManageAccount.setAccount("ceshi");
        xydManageAccount.setPassword("12345678");
        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.validPassword("123456","12345678")).thenThrow(new UnsupportedEncodingException());
        when(xydManageAccountService.selectByPrimaryKey(33)).thenReturn(xydManageAccount);
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/updatePassword")
                        .param("oldPassword","123456")
                        .param("newPassword","123456")
                        .param("accountId","33"));
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);
    }
    @Test
    public void addAccount() throws Exception{
        List<XydManageAccount> xydManageAccounts = new ArrayList<>();
        XydManageAccount xydManageAccount =new XydManageAccount();
        xydManageAccount.setAccount("ceshi");
        xydManageAccount.setPassword("12345678");
        xydManageAccounts.add(xydManageAccount);

        when(xydManageAccountService.selectByAccount("ceshi")).thenReturn(new ArrayList<XydManageAccount>());
        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.getEncryptedPwd("123456")).thenThrow(new UnsupportedEncodingException());
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                );


        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);


        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.getEncryptedPwd("123456")).thenReturn("123456");
        XydManageAccount mkXydManageAccount= PowerMockito.mock(XydManageAccount.class);
        when(xydManageAccountService.selectByAccount("ceshi")).thenReturn(new ArrayList<XydManageAccount>());
        PowerMockito.whenNew(XydManageAccount.class).withNoArguments().thenReturn(mkXydManageAccount);
        when(xydManageAccountService.insertSelective(mkXydManageAccount)).thenReturn(0);
        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                );
        MvcResult mvcResult1 = resultActions1.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result1);

        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.getEncryptedPwd("123456")).thenReturn("123456");
        XydManageAccount mkXydManageAccount2= PowerMockito.mock(XydManageAccount.class);
        when(xydManageAccountService.selectByAccount("ceshi")).thenReturn(new ArrayList<XydManageAccount>());
        PowerMockito.whenNew(XydManageAccount.class).withNoArguments().thenReturn(mkXydManageAccount2);
        when(xydManageAccountService.insertSelective(mkXydManageAccount)).thenReturn(0);
        ResultActions resultActions2 = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("roleIds","1,2,3")
                );


        MvcResult mvcResult2 = resultActions2.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult2.getResponse().getStatus());
        String result2 = mvcResult2.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result2);
    }

    @Test
    public void resetPassword() throws Exception {
        XydManageAccount xydManageAccount =new XydManageAccount();
        xydManageAccount.setAccount("ceshi");
        xydManageAccount.setPassword("12345678");
        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.getEncryptedPwd(global.getXydPassword())).thenThrow(new UnsupportedEncodingException());
        when(xydManageAccountService.selectByPrimaryKey(32)).thenReturn(xydManageAccount);
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/resetPassword")
                        .param("accountId","32")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====resetPassword mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====resetPassword result:" + result);

        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.getEncryptedPwd(global.getXydPassword())).thenReturn("123456");
        when(xydManageAccountService.selectByPrimaryKey(32)).thenReturn(xydManageAccount);
        when(xydManageAccountService.updateByPrimaryKeySelective(xydManageAccount)).thenReturn(0);
        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/resetPassword")
                        .param("accountId","32")
                );
        MvcResult mvcResult1 = resultActions1.andReturn();
        System.out.println("=====resetPassword mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult.getResponse().getContentAsString();
        System.out.println("=====resetPassword result:" + result1);
    }

    @Test
    public void deleteAccount() throws Exception{
        XydManageAccount xydManageAccount =new XydManageAccount();
        xydManageAccount.setAccount("ceshi");
        xydManageAccount.setPassword("12345678");
        when(xydManageAccountService.selectByPrimaryKey(32)).thenReturn(xydManageAccount);
        when(xydManageAccountService.updateByPrimaryKeySelective(xydManageAccount)).thenReturn(0);
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/deleteAccount")
                        .param("accountId","32")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====deleteAccount mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====deleteAccount result:" + result);

        ResultActions resultActions1 = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/deleteAccount")
                        .param("accountId","")
                );
        MvcResult mvcResult1 = resultActions1.andReturn();
        System.out.println("=====deleteAccount mvcResult:" + mvcResult1.getResponse().getStatus());
        String result1 = mvcResult1.getResponse().getContentAsString();
        System.out.println("=====deleteAccount result:" + result1);
    }
}
