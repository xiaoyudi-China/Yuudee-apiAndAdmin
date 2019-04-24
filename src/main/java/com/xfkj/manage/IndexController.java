package com.xfkj.manage;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xfkj.common.config.Global;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.MyMD5Util;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.XydManageAccount;
import com.xfkj.model.XydManageRole;
import com.xfkj.model.XydUserRole;
import com.xfkj.service.XydManageAccessService;
import com.xfkj.service.XydManageAccountService;
import com.xfkj.service.XydManageRoleService;
import com.xfkj.service.XydUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by King on 2018/9/28.
 */
@Controller
@RequestMapping("/manage/account")
public class IndexController extends BaseController{
    @Autowired
    private XydManageAccountService xydManageAccountService;
    @Autowired
    private XydManageRoleService manageRoleService;
    @Autowired
    private XydUserRoleService xydUserRoleService;
    @Autowired
    private XydManageAccessService xydManageAccessService;
    @Autowired
    private Global global;

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping(value = "/toLogin")
    public String tologinPage(RedirectAttributesModelMap modelMap) {
        Map msg = modelMap.asMap();
        return "/demo/system/login";
    }

    @RequestMapping(value = "/")
    public String toIndex(){
        return "/demo/system/login";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping(value = "/toindexPage")
    public String toindexPage(HttpServletRequest request, Model model){
        HttpSession session =request.getSession();
        XydManageAccount xydManageAccount= (XydManageAccount) session.getAttribute("user");
        model.addAttribute("id", xydManageAccount.getId());
        model.addAttribute("account", xydManageAccount);
        return "/demo/system/indexPage";
    }

    /**
     * 登录请求
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(Model model, @RequestParam Map<String, String> params, HttpServletRequest request) {
        HttpSession session =request.getSession();
        Map<String, Object> resutMap = new HashMap();
        String account = params.get("username");
        String password = params.get("password");
        if (IsObjectNullUtils.is(account)|| IsObjectNullUtils.is(password)){
            resutMap.put("msg", "用户名或密码不能为空!");
            resutMap.put("code", ResultStant.RESULT_CODE_ERROR);
            return resutMap;
        }
        List<XydManageAccount> list = xydManageAccountService.selectByAccount(account);
        if (!IsObjectNullUtils.is(list) && list.size() == 1){
            XydManageAccount xydManageAccount = list.get(0);
            try {
                if (MyMD5Util.validPassword(password, xydManageAccount.getPassword())){
                    session.setAttribute("user", xydManageAccount);
                    //信息正确、登录
                    resutMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resutMap.put("msg", "登录成功");
                    xydManageAccount.setPassword(null);
                    resutMap.put("data", xydManageAccount);
                }else{
                    resutMap.put("msg", "用户名错误或密码错误，请重新登录!");
                    resutMap.put("code", ResultStant.RESULT_CODE_ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resutMap.put("msg", "系统开小差了，");
                resutMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            }
        }else {
            resutMap.put("msg", "账号不存在！!");
            resutMap.put("code", ResultStant.RESULT_CODE_ERROR);
        }
        return resutMap;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String outlogin( HttpServletRequest request, Model m) {
        HttpSession session =request.getSession();
        if (session != null){
            deleteSession(request);
            return "redirect:/manage/account/toLogin";
        }else {
            m.addAttribute("msg", "退出异常!");
            m.addAttribute("code", ResultStant.RESULT_CODE_ERROR);
            return "redirect:/manage/user/residentList";
        }

    }

    /**
     * 修改密码
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Map updatePassword(@RequestParam(required = false, value = "")String oldPassword,
                              @RequestParam(required = false, value = "")String newPassword,
                              @RequestParam(required = false, value = "")Integer accountId,
                              HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        if (accountId != null){
            //获取数据
            XydManageAccount xydManageAccount = xydManageAccountService.selectByPrimaryKey(accountId);
            if (!IsObjectNullUtils.is(xydManageAccount)){
                try {
                    if (!MyMD5Util.validPassword(oldPassword, xydManageAccount.getPassword())){
                        resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                        resultMap.put("msg", "旧密码不正确，请重新输入！");
                        return resultMap;
                    }
                    xydManageAccount.setPassword(MyMD5Util.getEncryptedPwd(newPassword));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (xydManageAccountService.updateByPrimaryKeySelective(xydManageAccount) > 0){
                    //密码修改成功后更新session中的user对象
                    HttpSession session = request.getSession();
                    session.setAttribute("user", xydManageAccount);
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "密码修改成功！");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                    resultMap.put("msg", "操作异常！");
                }
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "无法获取相关信息,请稍后重试！");
            }
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息,请稍后重试！");
        }
        return resultMap;
    }

    /**
     *获取权限
     * @return
     */
    @RequestMapping(value = "/getUserAccessList", method = RequestMethod.POST)
    @ResponseBody
    public Map getUserAccessList(HttpServletRequest request, @RequestParam(required = false, value = "id")Integer id) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpSession session =request.getSession();
        List<XydManageAccount> accessList = (List<XydManageAccount>) session.getAttribute("AccessList"+id);
        if (!IsObjectNullUtils.is(accessList)){
            resultMap.put("data", accessList);
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            return resultMap;
        }
        //获取该用户的角色列表
        if (!IsObjectNullUtils.is(id)){
            XydManageAccount xydManageAccount = xydManageAccountService.selectByPrimaryKey(id);
            if (IsObjectNullUtils.is(xydManageAccount)){
                resultMap.put("msg", "用户获取失败重新登录!");
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                return resultMap;
            }
            XydUserRole xydUserRole = new XydUserRole();
            xydUserRole.setUserId(xydManageAccount.getId());
            xydUserRole.setStatus("0");
            xydUserRole.setIsDeleted(0);
            List<XydManageRole> roleList = manageRoleService.selectByUserRoleList(xydUserRole);
            if (IsObjectNullUtils.is(roleList) || roleList.size() < 1){
                resultMap.put("data", new ArrayList<XydManageAccount>());
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                return resultMap;
            }
           int [] roleids = new int[roleList.size()] ;
            if (!IsObjectNullUtils.is(roleList)){
                for (int i = 0; i < roleList.size(); i++) {
                    roleids[i] = roleList.get(i).getId();
                }
            }
            accessList = xydManageAccessService.selectByUserAccessList(roleids);
            session.setAttribute("AccessList"+id, accessList);
            resultMap.put("data", accessList);
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            return resultMap;
        }else {
            resultMap.put("msg", "用户获取失败重新登录!");
            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
            return resultMap;
        }
    }

    /**
     * 跳转到账号管理界面
     * @return
     */
    @RequestMapping(value = "/getAccountList")
    public String residentList() {
        return "/demo/user/accountList";
    }

    /**
     * 跳转账号添加界面
     * @return
     */
    @RequestMapping(value = "/addAccountPage")
    public String addAccountPage(Model model) {
        //获取角色列表
        XydManageRole eecManageRole = new XydManageRole();
        eecManageRole.setStatus("0");
        eecManageRole.setIsDeleted(0);
        List<XydManageRole> eecManageRoles = manageRoleService.selectByRoleList(eecManageRole);
        model.addAttribute("eecManageRoles", eecManageRoles);
        return "/demo/user/accountInfo";
    }


    /**
     * 添加、编辑修改
     */
    @RequestMapping(value = "/addAccount")
    @ResponseBody
    public Map addAccount(@RequestParam Map<String, String> params){
        HashMap<String, Object> resultMap = new HashMap<>();
        XydManageAccount xydManageAccount = new XydManageAccount();
        String name = params.get("name");
        String phone = params.get("phone");
        String account = params.get("account");
        String roleId = params.get("roleId");
        String password = params.get("password");
        String roleIds = params.get("roleIds");
        String id = params.get("id");
        if (!IsObjectNullUtils.is(id)){
            //根据account查询登录账号,判断修改后的账号是否已存在
            List<XydManageAccount> list = xydManageAccountService.selectByAccount(account);
            if (!IsObjectNullUtils.is(list) && list.size() > 0 && !id.equals(list.get(0).getId().toString()) ){
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "此账号已存在请重新输入！");
                return resultMap;
            }
            //跟新操作
            if(!IsObjectNullUtils.is(name) && !IsObjectNullUtils.is(phone) && !IsObjectNullUtils.is(account)  && !IsObjectNullUtils.is(roleId)){
                xydManageAccount.setAccount(account);
                xydManageAccount.setPhone(phone);
                xydManageAccount.setAccountName(name);
                xydManageAccount.setId(Integer.parseInt(id));
                xydManageAccount.setUpdateTime(new Date());
                if (xydManageAccountService.updateByPrimaryKeySelective(xydManageAccount) > 0){
                    XydUserRole xydUserRole = new XydUserRole();
                    //清除用户角色映射关系
                    xydUserRole.setUserId(xydManageAccount.getId());
                    xydUserRole.setStatus("0");
                    xydUserRole.setIsDeleted(0);
                    xydUserRoleService.deleteByUserRoleList(xydUserRole);
                    //更新用户角色关系
                    String [] roleArrs = roleIds.split(",");

                    for (String roleArr : roleArrs) {
                        XydManageRole xydManageRole = manageRoleService.selectByPrimaryKey(Integer.parseInt(roleArr));
                        if (IsObjectNullUtils.is(xydManageRole))
                            continue;
                        xydUserRole.setUserId(xydManageAccount.getId());
                        xydUserRole.setRoleId(xydManageRole.getId());
                        //新增操作
                        xydUserRole.setUpdateTime(new Date());
                        xydUserRole.setStatus("0");
                        xydUserRole.setIsDeleted(0);
                        xydUserRole.setCreateTime(new Date());
                        xydUserRoleService.insertSelective(xydUserRole);
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "更新成功！");
                }
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "信息不能为空！");
                return resultMap;
            }

        }else {//添加操作
            if(!IsObjectNullUtils.is(name) && !IsObjectNullUtils.is(phone) && !IsObjectNullUtils.is(account)  &&
                    !IsObjectNullUtils.is(password) && !IsObjectNullUtils.is(roleId) && !IsObjectNullUtils.is(roleIds)){
                //根据account查询登录账号是否已存在
                if (xydManageAccountService.selectByAccount(account).size() > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "此账号已存在请重新输入！");
                    return resultMap;
                }
                xydManageAccount.setAccount(account);
                xydManageAccount.setPhone(phone);
                try {
                    xydManageAccount.setPassword(MyMD5Util.getEncryptedPwd(password));
                } catch (Exception e) {
                    resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
                    resultMap.put("msg", "系统异常！");
                    e.printStackTrace();
                    return resultMap;
                }
                xydManageAccount.setAccountName(name);
                xydManageAccount.setCreateTime(new Date());
                xydManageAccount.setStatus("0");
                if (xydManageAccountService.insertSelective(xydManageAccount) > 0){
                    //添加用户角色关系
                    String [] roleArrs = roleIds.split(",");
                    XydUserRole xydUserRole = new XydUserRole();
                    for (String roleArr : roleArrs) {
                        XydManageRole xydManageRole = manageRoleService.selectByPrimaryKey(Integer.parseInt(roleArr));
                        if (IsObjectNullUtils.is(xydManageRole))
                            continue;
                        xydUserRole.setUserId(xydManageAccount.getId());
                        xydUserRole.setStatus("0");
                        xydUserRole.setIsDeleted(0);
                        xydUserRole.setRoleId(xydManageRole.getId());
                        xydUserRole.setCreateTime(new Date());
                        xydUserRole.setUpdateTime(new Date());
                        xydUserRoleService.insertSelective(xydUserRole);
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "添加成功！");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "添加失败！");
                }
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "信息不能为空！");
                return resultMap;
            }
        }
        return resultMap;
    }

    /**
     * 查询账号列表
     */
    @RequestMapping(value = "/getAccountList.ajax")
    @ResponseBody
    public DataTableReturnData getAccountList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydManageAccount> dataTableReturnData = new DataTableReturnData<XydManageAccount>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("accountName"))) {
            queryParam.fill("name", "%" + params.get("accountName") + "%");
        }
        if (!IsObjectNullUtils.is(params.get("phone"))) {
            queryParam.fill("phone",  params.get("phone"));
        }
        queryParam.fill("status", "0");
        int count = xydManageAccountService.selectByParamCount(queryParam);
        List<XydManageAccount> xydManageAccounts = xydManageAccountService.selectByParamList(queryParam);
        if (!IsObjectNullUtils.is(xydManageAccounts)){
            XydUserRole eecUserRole = new XydUserRole();
            for (XydManageAccount manageAccount : xydManageAccounts) {
                eecUserRole.setUserId(manageAccount.getId());
                eecUserRole.setStatus("0");
                eecUserRole.setIsDeleted(0);
                List<XydManageRole> xydManageRoleList = manageRoleService.selectByUserRoleList(eecUserRole);
                manageAccount.setRoleList(xydManageRoleList);
            }
        }
        dataTableReturnData.setData(xydManageAccounts);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 删除账号
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/deleteAccount")
    @ResponseBody
    public Map deleteAccount(@RequestParam(value = "")Integer accountId){
        Map<String, Object> resultMap = new HashMap<>();
        if (accountId != null){
            //获取数据
            XydManageAccount manageAccount = xydManageAccountService.selectByPrimaryKey(accountId);
            if (!IsObjectNullUtils.is(manageAccount)){
                manageAccount.setStatus("2");
                if (xydManageAccountService.updateByPrimaryKeySelective(manageAccount) > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "删除成功！");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "删除异常！");
                }
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "无法获取相关信息,请稍后重试！");
            }
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息,请稍后重试！");
        }
        return resultMap;
    }

    /**
     * 重置密码
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/resetPassword")
    @ResponseBody
    public Map resetPassword(@RequestParam(value = "accountId")Integer accountId){
        Map<String, Object> resultMap = new HashMap<>();
        if (accountId != null){
            //获取数据
            XydManageAccount manageAccount = xydManageAccountService.selectByPrimaryKey(accountId);
            if (!IsObjectNullUtils.is(manageAccount)){
                try {
                    manageAccount.setPassword(MyMD5Util.getEncryptedPwd(global.getXydPassword()));
                } catch (Exception e) {
                    resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
                    resultMap.put("msg", "系统异常，请稍后重新！");
                    e.printStackTrace();
                }
                if (xydManageAccountService.updateByPrimaryKeySelective(manageAccount) > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "重置成功！");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "重置失败！");
                }
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "无法获取相关信息,请稍后重试！");
            }
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息,请稍后重试！");
        }
        return resultMap;
    }

    /**
     * 查看账号详情
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/infoAccount")
    public String infoAccount(@RequestParam(required = false, value = "accountId")Integer accountId, Model model){
        Map<String, Object> resultMap = new HashMap<>();
        XydManageAccount manageAccount = new XydManageAccount();
        if (accountId != null){
            //获取数据
            manageAccount = xydManageAccountService.selectByPrimaryKey(accountId);
            if (!IsObjectNullUtils.is(manageAccount)){
                XydUserRole xydUserRole = new XydUserRole();
                xydUserRole.setUserId(manageAccount.getId());
                xydUserRole.setStatus("0");
                xydUserRole.setIsDeleted(0);
                //获取该用户的角色列表
                List<XydManageRole> userlist = manageRoleService.selectByUserRoleList(xydUserRole);
                manageAccount.setRoleList(userlist);
                model.addAttribute("accountUser", manageAccount);
            }
        }
        //获取角色列表
        XydManageRole xydManageRole = new XydManageRole();
        xydManageRole.setStatus("0");
        xydManageRole.setIsDeleted(0);
        List<XydManageRole> manageRoleList = manageRoleService.selectByRoleList(xydManageRole);
        if (!IsObjectNullUtils.is(manageAccount.getRoleList()) && !IsObjectNullUtils.is(manageRoleList)){
            for (XydManageRole manageRole : manageRoleList) {
                for (XydManageRole role : manageAccount.getRoleList()) {
                    if (manageRole.getId() == role.getId()){
                        manageRole.setPitchOn(true);
                        break;
                    }
                }
            }
        }
        model.addAttribute("eecManageRoles", manageRoleList);
        return "/demo/user/accountInfo";
    }
}
