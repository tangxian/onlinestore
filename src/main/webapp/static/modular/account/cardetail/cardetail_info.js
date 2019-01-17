/**
 * 初始化养车开支详情对话框
 */
var CardetailInfoDlg = {
    cardetailInfoData : {}
};

/**
 * 清除数据
 */
CardetailInfoDlg.clearData = function() {
    this.cardetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CardetailInfoDlg.set = function(key, val) {
    this.cardetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CardetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CardetailInfoDlg.close = function() {
    parent.layer.close(window.parent.Cardetail.layerIndex);
}

/**
 * 收集数据
 */
CardetailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('item')
    .set('amount')
    .set('km')
    .set('time')
    .set('remark')
    .set('userid');
}

/**
 * 提交添加
 */
CardetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cardetail/add", function(data){
        Feng.success("添加成功!");
        window.parent.Cardetail.table.refresh();
        CardetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cardetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CardetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cardetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.Cardetail.table.refresh();
        CardetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cardetailInfoData);
    ajax.start();
}

$(function() {
	// 初始化头像上传
    var fileUp = new $WebUpload("testfile");
    fileUp.setUploadBarId("progressBar");
    fileUp.init();
	/**
	 * 查询养车开销项目下拉框
	 */
	var ajax = new $ax(Feng.ctxPath + "/dict/selectbyparentcodelist", function (data) {
		var strHtml ="";
        $.each(data,function(key, val){
        	strHtml+='<option value="'+val.code+'">'+val.name+'</option>';
        })
        $("#item").html(strHtml);
    }, function (data) {
        Feng.error("页面初始化失败!");
    });
    ajax.set("code", "car_item");
    ajax.start();
});
