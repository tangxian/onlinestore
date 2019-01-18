package cn.stylefeng.guns.modular.account.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.DateHelper;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import cn.stylefeng.guns.modular.system.model.Cardetail;
import cn.stylefeng.guns.modular.system.model.OperationLog;
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
    private GunsProperties gunsProperties;
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
    	Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
    	List<Map<String, Object>> cardetail = cardetailService.selectCardetail(page, beginTime, endTime, itemid);
        //return cardetailService.selectList(null);
    	//return new CardetailWarpper(cardetail).wrap();
    	page.setRecords(new CardetailWarpper(cardetail).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增养车开支
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Cardetail cardetail) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	cardetail.setTime(new Date());
    	cardetail.setUserid(shiroUser.getId());
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
    
    /**
     * 上传文件
     */
    @RequestMapping(method = RequestMethod.POST, path = "/uploadfile")
    @ResponseBody
    public String uploadfile(@RequestPart("file") MultipartFile uploadfile) {

        String pictureName = DateHelper.getNumberForPK() + "." + ToolUtil.getFileSuffix(uploadfile.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath()+DateHelper.format(new Date(),"yyyyMM")+"\\";
            File file = new File(fileSavePath);
            if(!file.exists()){
            	file.mkdirs();
            }
            uploadfile.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return DateHelper.format(new Date(),"yyyyMM")+"/"+pictureName;
    }
}
