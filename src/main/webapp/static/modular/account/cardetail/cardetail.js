/**
 * 养车开支管理初始化
 */
var Cardetail = {
    id: "CardetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Cardetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '养车开销项目', field: 'itemName', visible: true, align: 'center', valign: 'middle'},
            {title: '金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '累计公里数', field: 'km', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'time', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '录入人', field: 'userName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Cardetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Cardetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加养车开支
 */
Cardetail.openAddCardetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加养车开支',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cardetail/cardetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看养车开支详情
 */
Cardetail.openCardetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '养车开支详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cardetail/cardetail_update/' + Cardetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除养车开支
 */
Cardetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cardetail/delete", function (data) {
            Feng.success("删除成功!");
            Cardetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cardetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询养车开支列表
 */
Cardetail.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Cardetail.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Cardetail.initColumn();
    var table = new BSTable(Cardetail.id, "/cardetail/list", defaultColunms);
    table.setPaginationType("client");
    Cardetail.table = table.init();
});
