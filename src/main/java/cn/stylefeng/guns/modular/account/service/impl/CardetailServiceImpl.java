package cn.stylefeng.guns.modular.account.service.impl;

import cn.stylefeng.guns.modular.system.model.Cardetail;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.guns.modular.system.dao.CardetailMapper;
import cn.stylefeng.guns.modular.account.service.ICardetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 养车开支记录表 服务实现类
 * </p>
 *
 * @author TANGXIAN
 * @since 2019-01-08
 */
@Service
public class CardetailServiceImpl extends ServiceImpl<CardetailMapper, Cardetail> implements ICardetailService {

	@Override
	public List<Map<String, Object>> selectCardetail(String beginTime, String endTime,
			Integer itemid) {
		 return this.baseMapper.selectCardetail(beginTime, endTime, itemid);
	}

}
