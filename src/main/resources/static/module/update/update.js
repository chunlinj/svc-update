$(function () {
    currentVersion();
    getAllVersion();
})

/**
 * 查询所有版本
 */
function getAllVersion() {
    var requestUrl = SVC_UPDATER_IP + "/service/list"
    $.ajax({
        url: requestUrl,
        async: false,
        type: 'get',
        success: function (data) {
            if (data.code == 20000) {
                var list=data.data;
                for (var i=0;i<list.length;i++){
                    $("#dealer_id").append('<option>'+list[i]+'</option>');
                }
            }else{
                layer.msg(data.message);
            }
        }
    });

}

/**
 * 查询当前版本
 */
function currentVersion() {
    var requestUrl = SVC_UPDATER_IP + "/service/version"
    $.ajax({
        url: requestUrl,
        async: false,
        type: 'get',
        success: function (data) {
            if (data.code == 20000) {
                $("#current").text(data.data.version);
            }else{
                layer.msg(data.message);
            }
        }
    });
}

/**
 * 跟新版本
 */
function updateVersion() {
    var selected=$("#dealer_id").val();
    if (selected =="" || selected== undefined) {
        layer.msg("请选择需要更新的版本！");
    }
    var requestUrl = SVC_UPDATER_IP + "/service/update/"+selected;
    $.ajax({
        url: requestUrl,
        async: false,
        type: 'get',
        success: function (data) {
            if (data.code == 20000) {
                layer.msg("更新成功");
            }else{
                layer.msg(data.message);
            }
        }
    });
}