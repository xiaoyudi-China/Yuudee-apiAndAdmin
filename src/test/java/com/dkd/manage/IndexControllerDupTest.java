package com.dkd.manage;

import com.dkd.common.config.Global;
import com.dkd.common.utils.IsObjectNullUtils;
import com.dkd.common.utils.MyMD5Util;
import com.dkd.model.XydManageAccount;
import com.dkd.model.XydManageRole;
import com.dkd.model.XydUserRole;
import com.dkd.service.XydManageRoleService;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * @author dongxiaohong
 * @date 2019/4/28 09:51
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(value = {MyMD5Util.class,XydManageAccount.class, IsObjectNullUtils.class, XydUserRole.class,XydManageRole.class,IndexController.class})
public class IndexControllerDupTest {
    private final Logger logger = LoggerFactory.getLogger(IndexControllerDupTest.class);
    private MockMvc mockMvc;
    @InjectMocks
    private IndexController indexController;
    private String reqUrl = "/manage/account";

    @Mock
    XydManageAccountServiceImpl xydManageAccountService;
    @Mock
    XydManageRoleService manageRoleService;
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

        PowerMockito.mockStatic(IsObjectNullUtils.class);
        when(IsObjectNullUtils.is("ceshi")).thenReturn(true);
        when(IsObjectNullUtils.is("123456")).thenReturn(false);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")
                        .param("username","ceshi")
                        .param("password","123456")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====login mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====login result:" + result);

        when(IsObjectNullUtils.is("ceshi")).thenReturn(false);
        when(IsObjectNullUtils.is("123456")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")
                        .param("username","ceshi")
                        .param("password","123456")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====login mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====login result:" + result);

        when(IsObjectNullUtils.is("123456")).thenReturn(false);
        List<XydManageAccount> mockXydManageAccounts= PowerMockito.mock(List.class);
        XydManageAccount account = new XydManageAccount();
        mockXydManageAccounts.add(account);
        when(xydManageAccountService.selectByAccount("ceshi")).thenReturn(mockXydManageAccounts);
        when(IsObjectNullUtils.is("ceshi")).thenReturn(false);
        when(IsObjectNullUtils.is(mockXydManageAccounts)).thenReturn(false);
        when(mockXydManageAccounts.size()).thenReturn(2);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")
                        .param("username","ceshi")
                        .param("password","123456")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====login mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====login result:" + result);

        when(IsObjectNullUtils.is(mockXydManageAccounts)).thenReturn(true);
        when(mockXydManageAccounts.size()).thenReturn(1);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")
                        .param("username","ceshi")
                        .param("password","123456")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====login mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====login result:" + result);

        when(IsObjectNullUtils.is(mockXydManageAccounts)).thenReturn(true);
        when(mockXydManageAccounts.size()).thenReturn(2);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/login")
                        .param("username","ceshi")
                        .param("password","123456")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====login mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====login result:" + result);
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
    public void getUserAccessList() throws Exception{
        XydManageRole xydManageRole = new XydManageRole();
        xydManageRole.setId(1);
        XydManageAccount xydManageAccount = new XydManageAccount();
        xydManageAccount.setId(19);
        XydUserRole xydUserRole = PowerMockito.mock(XydUserRole.class);
        whenNew(XydUserRole.class).withAnyArguments().thenReturn(xydUserRole);
        PowerMockito.mockStatic(IsObjectNullUtils.class);
        List<XydManageRole> xydManageRoles = PowerMockito.mock(List.class);
        when(xydManageRoles.get(anyInt())).thenReturn(xydManageRole);
        when(xydManageAccountService.selectByPrimaryKey(19)).thenReturn(xydManageAccount);
        when(IsObjectNullUtils.is(xydManageAccount)).thenReturn(false);
        when(manageRoleService.selectByUserRoleList(xydUserRole)).thenReturn(xydManageRoles);

        when(IsObjectNullUtils.is(xydManageRoles)).thenReturn(false);
        when(xydManageRoles.size()).thenReturn(0);
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")
                        .param("id","19")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====getUserAccessList mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getUserAccessList result:" + result);

        when(IsObjectNullUtils.is(xydManageRoles)).thenReturn(true);
        when(xydManageRoles.size()).thenReturn(0);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")
                        .param("id","19")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====getUserAccessList mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getUserAccessList result:" + result);

        when(IsObjectNullUtils.is(xydManageRoles)).thenReturn(true);
        when(xydManageRoles.size()).thenReturn(2);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")
                        .param("id","19")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====getUserAccessList mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getUserAccessList result:" + result);

        when(IsObjectNullUtils.is(xydManageRoles)).thenReturn(false);
        when(xydManageRoles.size()).thenReturn(2);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/getUserAccessList")
                        .param("id","19")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====getUserAccessList mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====getUserAccessList result:" + result);
    }
    @Test
    public void addAccountPrev() throws Exception{
        XydManageAccount xydManageAccount = PowerMockito.mock(XydManageAccount.class);
        whenNew(XydManageAccount.class).withAnyArguments().thenReturn(xydManageAccount);
        xydManageAccount.setId(10);
        List<XydManageAccount> xydManageAccounts = PowerMockito.mock(List.class);
        PowerMockito.mockStatic(IsObjectNullUtils.class);
        when(IsObjectNullUtils.is(10)).thenReturn(false);
        when(xydManageAccountService.selectByAccount("admin")).thenReturn(xydManageAccounts);
        when(IsObjectNullUtils.is(xydManageAccounts)).thenReturn(true);
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is(xydManageAccounts)).thenReturn(false);
        when(xydManageAccounts.size()).thenReturn(0);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is(xydManageAccounts)).thenReturn(false);
        when(xydManageAccounts.size()).thenReturn(1);
        when(xydManageAccounts.get(0)).thenReturn(xydManageAccount);
        when(xydManageAccount.getId()).thenReturn(11);
        //when("10".equals(xydManageAccount.getId().toString())).thenReturn(false);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is(xydManageAccounts)).thenReturn(false);
        when(xydManageAccounts.size()).thenReturn(1);
        when(xydManageAccounts.get(0)).thenReturn(xydManageAccount);
        when(xydManageAccount.getId()).thenReturn(10);
        //when("10".equals(xydManageAccount.getId().toString())).thenReturn(true);
        when(IsObjectNullUtils.is("1231")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(false);
        when(IsObjectNullUtils.is("admin")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(false);
        when(IsObjectNullUtils.is("admin")).thenReturn(false);
        when(IsObjectNullUtils.is("1")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(false);
        when(IsObjectNullUtils.is("admin")).thenReturn(false);
        when(IsObjectNullUtils.is("1")).thenReturn(false);
        when(xydManageAccountService.updateByPrimaryKeySelective(xydManageAccount)).thenReturn(0);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("10")).thenReturn(true);

        when(IsObjectNullUtils.is("1231")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(false);
        when(IsObjectNullUtils.is("admin")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(false);
        when(IsObjectNullUtils.is("admin")).thenReturn(false);
        when(IsObjectNullUtils.is("123456")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(false);
        when(IsObjectNullUtils.is("admin")).thenReturn(false);
        when(IsObjectNullUtils.is("123456")).thenReturn(false);
        when(IsObjectNullUtils.is("1")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        when(IsObjectNullUtils.is("1231")).thenReturn(false);
        when(IsObjectNullUtils.is("17795591253")).thenReturn(false);
        when(IsObjectNullUtils.is("admin")).thenReturn(false);
        when(IsObjectNullUtils.is("123456")).thenReturn(false);
        when(IsObjectNullUtils.is("1")).thenReturn(false);
        when(IsObjectNullUtils.is("1,2,3")).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
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
        whenNew(XydManageAccount.class).withNoArguments().thenReturn(mkXydManageAccount);
        when(xydManageAccountService.insertSelective(mkXydManageAccount)).thenReturn(0);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("password","123456")
                        .param("roleIds","1,2,3")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);

        PowerMockito.mockStatic(MyMD5Util.class);
        PowerMockito.when(MyMD5Util.getEncryptedPwd("123456")).thenReturn("123456");
        XydManageAccount mkXydManageAccount2= PowerMockito.mock(XydManageAccount.class);
        when(xydManageAccountService.selectByAccount("ceshi")).thenReturn(new ArrayList<XydManageAccount>());
        whenNew(XydManageAccount.class).withNoArguments().thenReturn(mkXydManageAccount2);
        when(xydManageAccountService.insertSelective(mkXydManageAccount)).thenReturn(0);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders
                        .request(HttpMethod.POST,reqUrl+"/addAccount")
                        .param("name","1231")
                        .param("phone","17795591253")
                        .param("account","admin")
                        .param("roleId","1")
                        .param("roleIds","1,2,3")
                );


        mvcResult = resultActions.andReturn();
        System.out.println("=====updatePassword mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====updatePassword result:" + result);
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

    @Test
    public void infoAccount() throws Exception{
        ResultActions resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/infoAccount")
                );
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("=====infoAccount mvcResult:" + mvcResult.getResponse().getStatus());
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====infoAccount result:" + result);

        PowerMockito.mockStatic(IsObjectNullUtils.class);
        XydManageAccount xydManageAccount = PowerMockito.mock(XydManageAccount.class);
        whenNew(XydManageAccount.class).withAnyArguments().thenReturn(xydManageAccount);
        when(xydManageAccountService.selectByPrimaryKey(10)).thenReturn(xydManageAccount);
        when(IsObjectNullUtils.is(xydManageAccount)).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/infoAccount")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====infoAccount mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====infoAccount result:" + result);


        XydManageRole xydManageRole = PowerMockito.mock(XydManageRole.class);
        whenNew(XydManageRole.class).withAnyArguments().thenReturn(xydManageRole);
        List<XydManageRole> xydManageRoles = new ArrayList<>();
        when(manageRoleService.selectByRoleList(xydManageRole)).thenReturn(xydManageRoles);
        when(IsObjectNullUtils.is(xydManageAccount)).thenReturn(false);
        when(IsObjectNullUtils.is(xydManageAccount.getRoleList())).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/infoAccount")
                        .param("accountId","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====infoAccount mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====infoAccount result:" + result);

        when(IsObjectNullUtils.is(xydManageAccount.getRoleList())).thenReturn(false);
        when(IsObjectNullUtils.is(xydManageRoles)).thenReturn(true);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/infoAccount")
                        .param("accountId","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====infoAccount mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====infoAccount result:" + result);

        when(IsObjectNullUtils.is(xydManageRoles)).thenReturn(false);
        resultActions = this.mockMvc.
                perform(MockMvcRequestBuilders.request(HttpMethod.POST,reqUrl+"/infoAccount")
                        .param("id","10")
                );
        mvcResult = resultActions.andReturn();
        System.out.println("=====infoAccount mvcResult:" + mvcResult.getResponse().getStatus());
        result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====infoAccount result:" + result);
    }
}
