
/* 
 * 拓展jquery对象的方法;
 */
(function ($) {


    var PopDialogDefaultConfig = {
    hasCover: true,                             //是否带遮罩层
    colorOfCover: "#000",                       //遮罩层颜色
    transparencyOfCover: 80,                    //遮罩层透明度（alpha值,opacity值通过换算得到）
    borderColor: "blue",                        //边框标题背景颜色
    titleHeight: 50,                            //标题高度
    titleFont: '24px "Microsoft Yahei"',        //标题字体
    titleFontSize: 24,                          //标题文字大小
    titleColor: "white",                        //标题文字颜色
    titleFontFamily: "Microsoft Yahei",         //标题字体
    contentWidth: 560,                          //内容框宽度
    contentHeight: 480,                         //内容框的高度
    borderWidth: 2,                             //边框宽度
    backColor: "#EC90F6",                       //背景色
    serial: 1,                                  //序列号
    moveAble: true,                             //是否可以鼠标拖动
    /* 
     * 配置文件合并 
     */
    Merge: function (newConfig) {
        var result = {};
        for (var p in this) {
            result[p] = this[p];
            if (typeof (this[p]) != " function ") {
                for (var q in newConfig) {
                    if (p == q) {
                        result[p] = newConfig[q];
                    }
                }
            }
        }
        return result;
    }
};
function PopDialog(content, title, obj, popDialogConfig) {
    content = content || "这里什么都没有!";
    title = title || "提示";
    obj = obj || $(document.body);
    var dialog = new Object;
    /* 
     * 弹出框的默认样式
     */
    var config = PopDialogDefaultConfig.Merge(popDialogConfig);
    /* 
     * 根据配置文件和弹出对象，取得弹出框的html串;
     */
    if (!obj.id) {
        obj.id = "pop" + config.serial;
        PopDialogDefaultConfig.serial++;
    }
    var opacity = (100 - config.transparencyOfCover) / 100;
    var width = config.contentWidth + config.borderWidth * 2;
    var height = config.contentHeight + config.borderWidth + config.titleHeight;
    dialog.Html = '<div id="' + obj.id + '_Cover" style="background-color: ' + config.colorOfCover + ';width:100%;height:100%;position:absolute;filter: alpha(opacity=' + config.transparencyOfCover + ');opacity:' + opacity + ';top:0;left:0;"></div>'
        + '<div id="' + obj.id + '_Dialog" style="background-color:' + config.borderColor + ';position:absolute;width: ' + width + 'px;height: ' + height + 'px;top:calc(50% - ' + height / 2 + 'px);left:calc(50% - ' + width / 2 + 'px);">'
        + '    <div id="' + obj.id + '_Top" style="height:' + config.titleHeight + 'px;line-height:' + config.titleHeight + 'px;display:block;width:100%;background-color:' + config.borderColor + ';clear:both;vertical-align:middle">'
        + '        <span id="' + obj.id + '_Title" style="display:inline-block;font-size:' + config.titleFontSize + 'px;padding-left:10px;color:' + config.titleColor + ';">' + (title || "提示") + '</span>'
        + '        <a style="display:block;float:right;text-decoration: none;margin-right:20px;clear:right;color:white;font-size:' + config.titleHeight * 8 / 10 + 'px;" href="#" onclick="$(\'#' + obj.id + '_Dialog\').remove();$(\'#' + obj.id + '_Cover\').remove();">X</a>'
        + '    </div>'
        + '    <div id="' + obj.id + '_Body" style="background-color:' + config.backColor + ';width:' + config.contentWidth + 'px;height:' + config.contentHeight + 'px;border:' + config.borderWidth + 'px solid ' + config.borderColor + ';">'
        + '        <div id="' + obj.id + '_Content" style="background-color:' + config.backColor + ';">' + content + '</div>'
        + '    </div>'
        + '</div>';
    dialog.Config = config;
    dialog.PopDom = $(dialog.Html);
    dialog.x = dialog.x1 = dialog.y = dialog.y1 = dialog.mousekey = 0;
    /* 
     * 鼠标拖动弹出框
     */
    if (config.moveAble) {
        var top = dialog.PopDom.find("#" + obj.id + "_Top");
        top.on("mousedown", function (event) {
            dialog.mousekey = 1;
            $(this).css("cursor", "move");
            dialog.PopDom.find("#" + obj.id + "_Content").css("display", "none");
        });
        top.on("mouseup", function (event) {
            dialog.mousekey = 0; $(this).css("cursor", "default");
            dialog.PopDom.find("#" + obj.id + "_Content").css("display", "");
        });
        top.on("mouseout", function (event) {
            dialog.mousekey = 0; $(this).css("cursor", "default");
            dialog.PopDom.find("#" + obj.id + "_Content").css("display", "");
        });
        top.on("mousemove", function (event) {
            if (dialog.mousekey == 1) {
                if (dialog.x != 0 || dialog.y != 0) {
                    dialog.x = event.pageX - dialog.x1;
                    dialog.y = event.pageY - dialog.y1;
                    var parent = $(this.parentElement);
                    parent.css("left", (parent.position().left + dialog.x) + "px");
                    parent.css("top", (parent.position().top + dialog.y) + "px");
                    dialog.x = dialog.x1 = event.pageX;
                    dialog.y = dialog.y1 = event.pageY;
                } else {
                    dialog.x = dialog.x1 = event.pageX;
                    dialog.y = dialog.y1 = event.pageY;
                }
            } else {
                dialog.x = dialog.x1 = dialog.y = dialog.y1 = 0;
            }
            return false;
        });
    }
    return dialog;
};


    
    /* 
     * 关闭弹出框的方法
     */
    $.fn.ClosePopDialog = function () {
        var obj = this;
        while (obj) {
            var popdialog = $("#" + this.id + "_Dialog");
            if (popdialog) {
                popdialog.remove();
            }
            var popcover = $("#" + this.id + "_Cover");
            if (popcover) {
                popcover.remove();
            }
            obj = obj.parent;
        }
    };
    /* 
     * 根据url取得内容并弹出框显示的方法
     * url: 需要显示的内容的url
     * popDialogConfig： 自定义样式
     * Example: $(document.body).PopDialog("/home/login", { backColor: "gray", borderColor: "blue", borderWidth: 2, contentWidth: 600, contentHeight: 480 });
     */
    $.fn.PopDialogByUrl = function (url, title, popDialogConfig) {
        var obj = $(this);
        obj.ClosePopDialog();
        if (url) {
            $.ajax({
                url: url,
                cache: false,
                success: function (result) {
                    if (result == "[]" || result == "") {
                        result = "系统忙，请稍后再试！";
                    }
                    var pop = new PopDialog(result, title, this, popDialogConfig);
                    obj.append(pop.PopDom);
                },
                error: function (result) {
                    if (result == "[]" || result == "") {
                        result = "系统错误，请联系管理员！";
                    }
                    var pop = new PopDialog(result, title, this, popDialogConfig);
                    obj.append(pop.PopDom);
                }
            });
        }
    };
    /* 
     * 弹出框显示提供的内容的方法
     * content: 需要显示的内容
     * popDialogConfig： 自定义样式
     * Example: $(document.body).PopDialog("<div>这是一个弹出框的例子！</div>", { backColor: "gray", borderColor: "blue", borderWidth: 2, contentWidth: 600, contentHeight: 480 });
     */
    $.fn.PopDialogByContent = function (content, title, popDialogConfig) {
        var obj = $(this);
        obj.ClosePopDialog();
        var pop = new PopDialog(content, title, this, popDialogConfig);
        obj.append(pop.PopDom);
    };
})(jQuery); 