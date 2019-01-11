package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Cardetail;
import cn.stylefeng.roses.core.datascope.DataScope;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 养车开支记录表 Mapper 接口
 * </p>
 *
 * @author TANGXIAN
 * @since 2019-01-08
 */
public interface CardetailMapper extends BaseMapper<Cardetail> {
	/**
     * 根据条件查询用户列表
     */
    List<Map<String, Object>> selectCardetail(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("itemid") Integer itemid);
}
