package cn.stylefeng.guns.modular.account.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;

import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Cardetail;
import cn.stylefeng.guns.modular.account.service.ICardetailService;
import cn.stylefeng.guns.modular.account.warpper.CardetailWarpper;

/**
 * 养车开支控制器
 *
 * @author fengshuonan
 * @Date 2019-01-08 14:21:06
 */
@Controller
@RequestMapping("/cardetail")
public class CardetailController extends BaseController {

    private String PREFIX = "/account/cardetail/";

    @Autowired
    private ICardetailService cardetailService;

    /**
     * 跳转到养车开支首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cardetail.html";
    }

    /**
     * 跳转到添加养车开支
     */
    @RequestMapping("/cardetail_add")
    public String cardetailAdd() {
        return PREFIX + "cardetail_add.html";
    }

    /**
     * 跳转到修改养车开支
     */
    @RequestMapping("/cardetail_update/{cardetailId}")
    public String cardetailUpdate(@PathVariable Integer cardetailId, Model model) {
        Cardetail cardetail = cardetailService.selectById(cardetailId);
        model.addAttribute("item",cardetail);
        LogObjectHolder.me().set(cardetail);
        return PREFIX + "cardetail_edit.html";
    }

    /**
     * 获取养车开支列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer itemid) {
    	List<Map<String, Object>> cardetail = cardetailService.selectCardetail(beginTime, endTime, itemid);
        //return cardetailService.selectList(null);
    	return new CardetailWarpper(cardetail).wrap();
    }

    /**
     * 新增养车开支
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Cardetail cardetail) {
        cardetailService.insert(cardetail);
        return SUCCESS_TIP;
    }

    /**
     * 删除养车开支
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cardetailId) {
        cardetailService.deleteById(cardetailId);
        return SUCCESS_TIP;
    }

    /**
     * 修改养车开支
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Cardetail cardetail) {
        cardetailService.updateById(cardetail);
        return SUCCESS_TIP;
    }

    /**
     * 养车开支详情
     */
    @RequestMapping(value = "/detail/{cardetailId}")
    @ResponseBody
    public Object detail(@PathVariable("cardetailId") Integer cardetailId) {
        return cardetailService.selectById(cardetailId);
    }
}
