package com.xfkj.manage;

import com.sun.org.apache.regexp.internal.RE;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.XydAccessRole;
import com.xfkj.model.XydManageAccess;
import com.xfkj.model.XydManageRole;
import com.xfkj.service.XydAccessRoleService;
import com.xfkj.service.XydManageAccessService;
import com.xfkj.service.XydManageRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/4.
 * 角色权限管理
 */
@Controller
@RequestMapping(value = "/manage/roleAccess")
public class RoleAccessController extends BaseController {
    @Autowired
    private XydAccessRoleService xydAccessRoleService;
    @Autowired
    private XydManageAccessService xydManageAccessService;
    @Autowired
    private XydManageRoleService xydManageRoleService;


    /**
     *跳转角色列表页
     */
    @RequestMapping(value = "/toRoleListPage")
    public String toRoleListPage(){
        return "/demo/user/roleAccess/roleManageList";
    }

    @RequestMapping(value = "/eecManageRoleList")
    @ResponseBody
    public Map eecManageRoleList(@RequestParam(required = false, value = "id")Integer id){
        Map<String, Object> resultMap = new HashMap<>();
        if (id != null){
            //获取数据
            XydManageRole xydManageRole = xydManageRoleService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydManageRole)){
                //获取该角色的权限
                XydAccessRole xydAccessRole = new XydAccessRole();
                xydAccessRole.setRoleId(xydManageRole.getId());
                xydAccessRole.setStatus("0");
                xydAccessRole.setIsDeleted(0);
                List<XydManageAccess> accessRoleList = xydManageAccessService.selectByRoleAccessList(xydAccessRole);
                xydManageRole.setAccesses(accessRoleList);
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", xydManageRole);
            }
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
            resultMap.put("msg", "");
        }
        return resultMap;
    }

    /**
     * 跳转到角色添加页
     */
    @RequestMapping(value = "/toRoleaddPage")
    public String toRoleaddPage(Model model){
        //获取权限列表
        XydManageAccess xydManageAccess = new XydManageAccess();
        xydManageAccess.setIsDeleted(0);
        xydManageAccess.setStatus("0");
        //0 为一级菜单
        xydManageAccess.setParentId(0);
        List<XydManageAccess> accessList = xydManageAccessService.selectByEntityList(xydManageAccess);
        model.addAttribute("accessList", accessList);
        return "/demo/user/roleAccess/roleManageAdd";
    }

    /**
     * 角色列表
     */
    @RequestMapping(value = "/getRoleList.ajax", method = RequestMethod.POST)
    @ResponseBody
    public DataTableReturnData getRoleList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydManageRole> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("roleName"))) {
            queryParam.fill("roleName", "%" + params.get("roleName").trim() + "%");
        }
        queryParam.fill("isDeleted", 0);
        int count = xydManageRoleService.selectByParamCount(queryParam);
        List<XydManageRole> eecManageRoleList = xydManageRoleService.selectByParamList(queryParam);
        dataTableReturnData.setData(eecManageRoleList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 查看角色详情
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/infoRole")
    public String infoRole(@RequestParam(required = false, value = "")Integer roleId, Model model){
        Map<String, Object> resultMap = new HashMap<>();
        if (roleId != null){
            //获取数据
            XydManageRole xydManageRole = xydManageRoleService.selectByPrimaryKey(roleId);
            if (!IsObjectNullUtils.is(xydManageRole)){
                //获取该角色的权限
                XydAccessRole xydAccessRole = new XydAccessRole();
                xydAccessRole.setRoleId(xydManageRole.getId());
                xydAccessRole.setStatus("0");
                xydAccessRole.setIsDeleted(0);
                List<XydManageAccess> accessRoleList = xydManageAccessService.selectByRoleAccessList(xydAccessRole);
                xydManageRole.setAccesses(accessRoleList);
                model.addAttribute("eecManageRole", xydManageRole);
            }
        }
        //获取权限列表
        XydManageAccess xydManageAccess = new XydManageAccess();
        xydManageAccess.setIsDeleted(0);
        xydManageAccess.setStatus("0");
        //0 为一级菜单
        xydManageAccess.setParentId(0);
        List<XydManageAccess> accessList = xydManageAccessService.selectByEntityList(xydManageAccess);
        model.addAttribute("accessList", accessList);
        return "/demo/user/roleAccess/roleManageAdd";
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public Map deleteRole(@RequestParam(required = false, value = "")Integer roleId){
        Map<String, Object> resultMap = new HashMap<>();
        if (roleId != null){
            //获取数据
            XydManageRole xydManageRole = xydManageRoleService.selectByPrimaryKey(roleId);
            if (!IsObjectNullUtils.is(xydManageRole)){
                xydManageRole.setIsDeleted(1);
                if (xydManageRoleService.updateByPrimaryKeySelective(xydManageRole) > 0){
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
     * 添加、编辑修改角色
     */
    @RequestMapping(value = "/addorUpdateRole", method = RequestMethod.POST)
    @ResponseBody
    public Map addorUpdateRole(@RequestParam Map<String, String> params){
        HashMap<String, Object> resultMap = new HashMap<>();
        XydManageRole xydManageRole = new XydManageRole();
        String roleName = params.get("roleName");
        String remark = params.get("remark");
        String role = params.get("role");
        String status = params.get("status");
        String id = params.get("id");
        String access = params.get("access");
        if (IsObjectNullUtils.is(access)){
            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
            resultMap.put("msg", "请选择权限！");
            return resultMap;
        }
        String[] saccessArr = access.split(",");
        if (!IsObjectNullUtils.is(id)){
            //根据roleName查询角色,判断修改后的角色是否已存在
            XydManageRole manageRole = new XydManageRole();
            manageRole.setRemark(roleName);
            manageRole.setRole(role);
            List<XydManageRole> list = xydManageRoleService.selectByRoleList(manageRole);
            if (!IsObjectNullUtils.is(list) && list.size() > 0 && !id.equals(list.get(0).getId().toString()) ){
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "此角色已存在请重新输入！");
                return resultMap;
            }
            //跟新操作
            if(!IsObjectNullUtils.is(roleName) && !IsObjectNullUtils.is(remark) && !IsObjectNullUtils.is(role)  && !IsObjectNullUtils.is(status)){
                xydManageRole.setRemark(remark);
                xydManageRole.setRoleName(roleName);
                xydManageRole.setRole(role);
                xydManageRole.setStatus(status);
                xydManageRole.setId(Integer.parseInt(id));
                xydManageRole.setUpdateTime(new Date());
                if (xydManageRoleService.updateByPrimaryKeySelective(xydManageRole) > 0){
                    //删除记录
                    XydAccessRole xydAccessRole = new XydAccessRole();
                    xydAccessRole.setStatus("0");
                    xydAccessRole.setIsDeleted(0);
                    xydAccessRole.setRoleId(xydManageRole.getId());
                    xydAccessRoleService.deleteByEntityList(xydAccessRole);
                    //更新角色权限
                    for (String s : saccessArr) {
                        xydAccessRole.setStatus("0");
                        xydAccessRole.setIsDeleted(0);
                        xydAccessRole.setAccessId(Integer.parseInt(s));
                        xydAccessRole.setRoleId(xydManageRole.getId());
                        xydAccessRole.setCreateTime(new Date());
                        xydAccessRole.setUpdateTime(new Date());
                        xydAccessRoleService.insertSelective(xydAccessRole);
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "更新成功！");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "操作失败！");
                }
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "信息不能为空！");
                return resultMap;
            }

        }else {//添加操作
            if(!IsObjectNullUtils.is(roleName) && !IsObjectNullUtils.is(remark) && !IsObjectNullUtils.is(role)  && !IsObjectNullUtils.is(status) ){
                xydManageRole.setRoleName(roleName);
                xydManageRole.setRole(role);
                //根据roleName查询角色否已存在
                if (xydManageRoleService.selectByRoleList(xydManageRole).size() > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "此角色已存在请重新输入！");
                    return resultMap;
                }
                xydManageRole.setRemark(remark);
                xydManageRole.setStatus(status);
                xydManageRole.setCreateTime(new Date());
                xydManageRole.setUpdateTime(new Date());
                xydManageRole.setIsDeleted(0);
                if (xydManageRoleService.insertSelective(xydManageRole) > 0){
                    //添加角色权限
                    XydAccessRole xydAccessRole = new XydAccessRole();
                    for (String s : saccessArr) {
                        xydAccessRole.setStatus("0");
                        xydAccessRole.setIsDeleted(0);
                        xydAccessRole.setAccessId(Integer.parseInt(s));
                        xydAccessRole.setRoleId(xydManageRole.getId());
                        xydAccessRole.setCreateTime(new Date());
                        xydAccessRole.setUpdateTime(new Date());
                        xydAccessRoleService.insertSelective(xydAccessRole);
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


}
