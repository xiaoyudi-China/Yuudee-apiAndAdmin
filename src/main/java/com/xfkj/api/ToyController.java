package com.xfkj.api;

import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.TokenProccessor;
import com.xfkj.model.XydFortifier;
import com.xfkj.model.XydParents;
import com.xfkj.model.XydToy;
import com.xfkj.service.XydFortifierService;
import com.xfkj.service.XydParentsService;
import com.xfkj.service.XydToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 强化物
 * Created by King on 2018/11/14.
 */
@RestController
@RequestMapping(value = "/app/toy")
public class ToyController {

    @Autowired
    private XydToyService toyService;

    @Autowired
    private XydParentsService xydParentsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private XydFortifierService fortifierService;

    /*
    * 查询儿童剩余积木数
    * @return list
    * */
    @RequestMapping(value = "toyList")
    public Map getToyList(HttpServletRequest request, @RequestParam(value = "token", required = false) String token,
                          @RequestParam(value = "module", required = false) String module) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(token)) {
            result.put("data", 205);
            result.put("msg", "token获取失败");
        }
        if (IsObjectNullUtils.is(module)) {
            result.put("data", 205);
            result.put("msg", "module 获取失败");
        }
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
        if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
            XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
            if (!IsObjectNullUtils.is(xydParents)) {
                Integer childId = xydParents.getChildId();
                if (IsObjectNullUtils.is(childId)) {
                    result.put("code", 205);
                    result.put("msg", "儿童信息未完善！");
                    return result;
                }
                XydToy xydToy = new XydToy();
                xydToy.setUserId(childId);
                xydToy.setModule(module);
                xydToy.setStatus("0");
                //查询儿童剩余积木数
                List<XydToy> list = toyService.selectByEntity(xydToy);
                //如果剩余数量为0 可能是使用完 可能是没添加过数据
                if (list.size() < 1) {
                    //查询使用完的积木数
                    xydToy.setStatus("1");
                    List<XydToy> list1 = toyService.selectByEntity(xydToy);
                    //如果是使用完 修改
                    if (list1.size() > 0) {
                        XydToy xydToy1 = new XydToy();
                        xydToy1.setUserId(childId);
                        xydToy1.setModule(module);
                        xydToy1.setStatus("0");
                        int flag = toyService.updateByUserId(xydToy1);
                        if (flag != 0) {
                            xydToy.setStatus("0");
                            xydToy.setModule(module);
                            List<XydToy> list2 = toyService.selectByEntity(xydToy);
                            result.put("code", 200);
                            result.put("msg", "查询成功");
                            result.put("data", list2);
                            return result;
                        } else {
                            result.put("code", 206);
                            result.put("msg", "修改失败！");
                            return result;
                        }
                    } else {
                        List<XydToy> xydToy1 = new ArrayList<>();
                        for(Integer i = 0;i<9;i++){
                            XydToy xydToy2 = new XydToy();
                            xydToy2.setNumber(i+1);
                            xydToy1.add(xydToy2);
                        }
                        int flag = toyService.insertList(xydToy1,module,childId,"0");
                        if(flag == 0){
                            result.put("code", 206);
                            result.put("msg", "查询失败！");
                            return result;
                        }else{
                            XydToy xydToy4 = new XydToy();
                            xydToy4.setUserId(childId);
                            xydToy4.setModule(module);
                            xydToy4.setStatus("0");
                            //查询儿童剩余积木数
                            List<XydToy> list4 = toyService.selectByEntity(xydToy4);
                            result.put("code", 200);
                            result.put("msg", "查询成功");
                            result.put("data", list4);
                            return result;
                        }
                    }
                } else {
                    result.put("code", 200);
                    result.put("msg", "查询成功");
                    result.put("data", list);
                    return result;
                }
            } else {
                result.put("code", 205);
                result.put("msg", "用户信息获取失败！");
                return result;
            }
        } else {
            result.put("code", 205);
            result.put("msg", "用户信息获取失败！");
            return result;
        }
    }

    /*
     * 修改儿童积木状态
     * @return list
     * */
    @RequestMapping(value = "useToy")
    public Map useToy(HttpServletRequest request, @RequestParam(value = "token", required = false) String token,
                      @RequestParam(value = "number", required = false) Integer number,
                      @RequestParam(value = "module", required = false) String module) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(token) || IsObjectNullUtils.is(number) || IsObjectNullUtils.is(module)) {
            result.put("data", 205);
            result.put("msg", "参数获取失败");
        }
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
        if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
            XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
            if (!IsObjectNullUtils.is(xydParents)) {
                Integer childId = xydParents.getChildId();
                if (IsObjectNullUtils.is(childId)) {
                    result.put("code", 205);
                    result.put("msg", "儿童信息未完善！");
                    return result;
                }
                XydToy xydToy = new XydToy();
                xydToy.setUserId(childId);
                xydToy.setNumber(number);
                xydToy.setModule(module);
                xydToy.setStatus("1");
                int flag = toyService.updateUseToy(xydToy);
                if (flag != 0) {
                    result.put("code", 200);
                    result.put("msg", "修改成功！");
                    return result;
                } else {
                    result.put("code", 205);
                    result.put("msg", "修改失败！");
                    return result;
                }
            } else {
                result.put("code", 205);
                result.put("msg", "用户信息获取失败！");
                return result;
            }
        } else {
            result.put("code", 205);
            result.put("msg", "用户信息获取失败！");
            return result;
        }
    }

    /**
     * 通关清空强化物
     * @param token
     * @param module
     * @return
     */
    @RequestMapping(value = "/empty/useToy")
    public Map deleteUseToy (@RequestParam(value = "token") String token,
                             @RequestParam(value = "module") String module){
        Map<String, Object> result = new HashMap<String, Object>();
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
        if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
            XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
            if (!IsObjectNullUtils.is(xydParents)) {
                Integer childId = xydParents.getChildId();
                if (IsObjectNullUtils.is(childId)) {
                    result.put("code", 205);
                    result.put("msg", "儿童信息未完善！");
                    return result;
                }

                //金币清
                XydFortifier xydFortifier = fortifierService.selectOne(childId, module);
                if (!IsObjectNullUtils.is(xydFortifier)){
                    XydFortifier xydFortifier1 = new XydFortifier();
                    xydFortifier1.setId(xydFortifier.getId());
                    xydFortifier1.setGold(0);
                    xydFortifier1.setUpdateTime(new Date());
                    fortifierService.update(xydFortifier1);
                }
                //强化物清
                XydToy xydToy = new XydToy();
                xydToy.setUserId(xydParents.getChildId());
                xydToy.setModule(module);
                xydToy.setStatus("0");
                if (toyService.emptyUpdateUseToy(xydToy) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "操作成功！");
                    return result;
                }else {
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "操作失败！");
                    return result;
                }
            } else {
                result.put("code", 205);
                result.put("msg", "用户信息获取失败！");
                return result;
            }
        } else {
            result.put("code", 205);
            result.put("msg", "用户信息获取失败！");
            return result;
        }

    }


}
