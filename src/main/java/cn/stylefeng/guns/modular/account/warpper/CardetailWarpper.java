package cn.stylefeng.guns.modular.account.warpper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;

public class CardetailWarpper extends BaseControllerWrapper {

	public CardetailWarpper(Map<String, Object> single) {
        super(single);
    }

    public CardetailWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CardetailWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CardetailWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

	@Override
	protected void wrapTheMap(Map<String, Object> map) {
		map.put("itemName", ConstantFactory.me().getDictsByName("养车开销项目",(Integer) map.get("item")));
		map.put("userName", ConstantFactory.me().getUserAccountById((Integer) map.get("userid")));
	}

}
