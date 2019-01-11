package cn.stylefeng.guns.modular.account.service;

import cn.stylefeng.guns.modular.system.model.Cardetail;
import cn.stylefeng.roses.core.datascope.DataScope;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 养车开支记录表 服务类
 * </p>
 *
 * @author TANGXIAN
 * @since 2019-01-08
 */
public interface ICardetailService extends IService<Cardetail> {
	/**
     * 根据条件查询养车开支列表
     */
    List<Map<String, Object>> selectCardetail(String beginTime, String endTime, Integer itemid);
}
