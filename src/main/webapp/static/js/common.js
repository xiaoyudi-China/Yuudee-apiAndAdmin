/**
 * Created by lmq on 2016/6/20.
 */

var isCanSee = [["未发布", "warning"], ["已发布", "info"], ["已删除", "danger"]], levelData = [], templateData = [];
var templateDatas = {};
var cateIndexUrl = "/sv1/admin/index/getCateIndex";
var pageIndexUrl = "/sv1/admin/index/getIndexById";
var templateUrl = "/sv1/admin/template/getall";
var templateContent = "/sv1/admin/template/getTemContent";
var templateIndexUrl = "/sv1/admin/template/getTemIndex"
var elFrame, txtFrame;

//获取index模版数据
function renderTemplateIndex(templateId, indexId, addtype, classname, callback) {
    $.get(templateIndexUrl, {"templateId": templateId}, function (data) {
        if (data.status == -1) {
            showError(data.msg);
        } else {
            // constructTemContent(data.datas, classname)
            constructTemIndex(data.datas, indexId, addtype, classname, callback);
            // console.log(data);
        }
    }).error(function () {
        showError("获取模版类型失败");
    });
}
function constructTemIndex(data, indexId, addtype, classname, callback) {
    $(classname).html("");
    var length = data.length, i = 0;
    var str = "";

    for (i; i < length; i++) {
        str += '<div class="form-group">';
        if (data[i].tContentName != 'indexIcon') {
            if (data[i].tContentState == 1) {
                str += '<label style="color: red">*' + data[i].tContentValue + '</label>';
            } else {
                str += '<label>' + data[i].tContentValue + '</label>';
            }
            switch (data[i].tContentName) {
                case 'indexStatus':
                    str += '<select name="indexStatus" class="form-control indexStatus">' +
                        '<option value="1">已发布</option>' +
                        '<option value="0">未发布</option>' +
                        '<option value="2">已删除</option>' +
                        '</select>'
                    break;
                case 'selectBoxBytemplateId':
                    str += '<div class="selectBoxBytemplateId"></div>';
                    break;
                case 'indexTitle':
                    str += '<input type="text" id="indexTitle" maxlength="200" name="indexTitle" class="form-control">';
                    break;
                case 'indexIndex':
                    str += ' <input type="text" id="indexIndex" name="indexIndex" class="form-control">'
                    break;
                case 'indexResourceUrl':
                    str += '<input type="text" id="indexResourceUrl" name="indexResourceUrl" class="form-control">';
                    break;
                case 'indexContent':
                    str += '<input name="indexContent" id="indexContent" maxlength="200" class="form-control" >'
                    break;
            }
        } else {
            if (data[i].tContentState == 1) {
                str += '<label style="color: red"><div class="row">*' + data[i].tContentValue + '<input type="button" id="deleteImage" value="移除图片"></div></label>'
            } else {
                str += '<label><div class="row">' + data[i].tContentValue + '<input type="button" id="deleteImage" value="移除图片"></div></label>'
            }
            str += '<div id="uploader" class="wu-example">' +
                '<div class="queueList">' +
                '<div id="dndArea" class="placeholder">' +
                '<div id="filePicker" class="fileSelect webuploader-container"><span class="pickerSpan">添加图片</span>' +
                '<div class="imgBox"></div>' +
                '</div>' +
                '<div class="statusBar" style="display:none;">' +
                '<p class="progress" style="display: none;">' +
                '<span></span>' +
                '</p>' +
                '<div class="info">共0张（0B），已上传0张</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
        }

        str += '</div>';
    }
    str += '<input type="hidden" id="indexIcon" name="indexIcon"> ' +
        '<input type="hidden" id="indexId" name="indexId"> ' +
        '<input type="hidden" id="indexParentId" name="indexParent">'
    $(classname).html(str);
    $(".selectBoxBytemplateId").change(function () {
        var templateId = $(".selectBoxBytemplateId #templateId").val();
        renderTemplateIndex(templateId, indexId, addtype, ".templateIndex", function () {
            var datatemplate = getTemplateIndex(templateDatas.datas);
            constructSelectByTemplate(datatemplate, "templateId", ".selectBoxBytemplateId", templateId,
                function () {
                    if (addtype) {
                        resetPageInsert(indexId);
                    } else {
                        renderPageIndex(indexId);
                    }
                });

            uploadinit();
            $("#deleteImage").click(function () {
                deleteImage();
            });
        })
    })
    callback();
}
var ss;
//获取模版内容
function renderTemplateContent(templateId, classname) {
    console.log("我执行了啊");
    $.get(templateContent, {"templateId": templateId}, function (data) {
        if (data.status == -1) {
            showError(data.msg);
        } else {
            console.log("data1...." + data.datas1);
            console.log("data2..." + data.datas2);
            ss = constructTemContent(data.datas1, data.datas2, classname);
            console.log("ss" + ss);
            console.log(data);
        }
    }).error(function () {
        showError("获取模版类型失败");
    });

}
//模版页面内容格式
function constructTemContent(data1, data2, classname) {

    $(classname).html("");
    var length = data1.length, i = 0;
    var str = '<div class="form-group">' +
        '<label>分类排序</label>' +
        '<input type="number" id="indexIndex" name="indexIndex" class="form-control">' +
        '</div>';

    var length1 = data2.length, j = 0;
    for (j; j < length1; j++) {
        str += '<div class = "form-group">';
        var contentValue;
        if (data2[j].tContentState == 1) {
            contentValue = '<label style="color: red" >*' + data2[j].tContentValue + '</label>';
        } else {
            contentValue = '<label>' + data2[j].tContentValue + '</label>';
        }
        switch (data2[j].tContentType) {
            case 4:
                if (data2[j].tContentState == 1) {
                    str += contentValue +
                        '<input type = "text" needcheck="true" maxlength="200" id = "' + data2[j].tContentName + '" name = "' + data2[j].tContentValue + '" class="form-control">'
                } else {
                    str += contentValue +
                        '<input type = "text" maxlength="200" id = "' + data2[j].tContentName + '" name = "' + data2[j].tContentValue + '" class="form-control">'
                }
                break
            case 0:
                str += '<textarea class="editor" style="visibility:hidden;width:0;height:0" name="contentValue" temcontent="' + data1[i].tContentName + '" id="#editor" rows="10" cols="80"></textarea>'
                str += '<h3>' + data2[j].tContentValue +
                    '<small>&nbsp;拖动图片进入编辑框，即可上传</small>' +
                    '</h3>' +
                    '<div id="detailText">' +
                    '<iframe id="textEditor" src="/oss/sv1/page/textEditor?dir=resources/app/images/products/&aspect=0"></iframe> ' +
                    '</div>';
                // if (data2[j].tContentState == 1) {
                //     str += contentValue
                //     '<textarea  needcheck="true" name = "' + data2[j].tContentValue + '" maxlength="200" id = "' + data2[j].tContentName + '" class="form-control" rows="3"></textarea>'
                //
                // } else {
                //     str += contentValue +
                //         '<textarea name = "' + data2[j].tContentValue + '" maxlength="200" id = "' + data2[j].tContentName + '" class="form-control" rows="3"></textarea>'
                // }
                isfwb = true;
                break;
            case 1:

                if (data2[j].tContentState == 1) {
                    str += '<input type="hidden" needcheck="true" id="indexIcon" name="' + data2[j].tContentValue + '">';
                    str += '<label style="color: red"><div class="row">*' + data2[j].tContentValue + '</div></label>'

                } else {
                    str += '<input type="hidden"  id="indexIcon" name="' + data2[j].tContentValue + '">';
                    str += '<label><div class="row">' + data2[j].tContentValue + '</label>'

                }

                str += '<div class="row ">' +
                    '<div class="block col-md-3 itemAddPic relative"> ' +
                    '<div class="pics" style="background-image:url(' + '\'/oss/static/app/imgs/add-button.png\'' + ')"></div>' +
                    '<div class="btns"> ' +
                    '<button class="btnUpdate btn btn-info" disabled="true" >更新</button> ' +
                    '<button class="btnAdd btn btn-info">添加</button> ' +
                    '</div></div></div>'
                // '<div id="uploader" class="uploader wu-example">' +
                // '<div class="queueList">' +
                // '<div id="dndArea" class="placeholder">' +
                // '<div id="filePicker" class="fileSelect webuploader-container"><span ' +
                // 'class="pickerSpan">添加图片</span>' +
                // '<div id="imageADD" class="imgBox"></div>' +
                // '</div>' +
                // '<div class="statusBar" style="display:none;">' +
                // '<p class="progress" style="display: none;">' +
                // '<span></span>' +
                // '</p>' +
                // '<div class="info">共0张（0B），已上传0张</div>' +
                // '</div>' +
                // '</div>' +
                // '</div>' +
                // '</div>'
                break;
            case 2:
                str += contentValue +
                    '<input type="text" id="' + data2[j].tContentName + '"  name="indexResourceUrl" class="form-control"  >'
                break;
        }
        str += "</div>";
    }
    // str += '<div class = "form-group">' +
    //     '<label style="color: red">*索引标题 </label>' +
    //     '<input type = "text" needcheck="true" maxlength="200" id = "indexTitle" name = "索引标题" class="form-control" >' +
    //     '</div>' +
    //     '<div class="form-group">' +
    //     '<label style="color: red">*索引介绍</label>' +
    //     '<textarea  needcheck="true" name = "索引介绍" maxlength="200" id="indexContent" class="form-control" rows="3"></textarea>' +
    //     '<input type="hidden"  id="indexIcon" name="indexIcon">' +
    //     '</div>' +
    //     '<label><div class="row">索引图片<input type="button" id="deleteImage" class="deleteImage" value="移除图片"></div></label>' +
    //     '<div id="uploader" class="uploader wu-example">' +
    //     '<div class="queueList">' +
    //     '<div id="dndArea" class="placeholder">' +
    //     '<div id="filePicker" class="fileSelect webuploader-container"><span ' +
    //     'class="pickerSpan">添加图片</span>' +
    //     '<div id="imageADD" class="imgBox"></div>' +
    //     '</div>' +
    //     '<div class="statusBar" style="display:none;">' +
    //     '<p class="progress" style="display: none;">' +
    //     '<span></span>' +
    //     '</p>' +
    //     '<div class="info">共0张（0B），已上传0张</div>' +
    //     '</div>' +
    //     '</div>' +
    //     '</div>' +
    //     '</div>' +
    //     '<label>索引外连</label>' +
    //     '<input type="text" id="indexResourceUrl"  name="indexResourceUrl" class="form-control"  >';
    var isfwb = false, isimg = false;
    var defaultvalue = "";
    for (i; i < length; i++) {
        str += "<div class='form-group'>";
        // 内容类型
        // 0:富文本
        // 1:图片<img>
        // 2:视频<vedio>
        // 3:外部页面<iframe>
        var contentValue;
        if (data1[i].tContentState == 1) {
            contentValue = '<label style="color: red" >*' + data1[i].tContentValue + '</label>';

        } else {
            contentValue = '<label>' + data1[i].tContentValue + '</label>';
        }

        if (data1[i].tContentDefault) {
            defaultvalue = data1[i].tContentDefault;
        }
        switch (data1[i].tContentType) {
            case 4:
                if (data1[i].tContentState == 1) {
                    str += contentValue +
                        '<input type = "text" needcheck="true" name="' + data1[i].tContentValue + '" temcontent="' + data1[i].tContentName + '" id = "indexTitle"  class="form-control" value="' + defaultvalue + '">'

                } else {
                    str += contentValue +
                        '<input type = "text" temcontent="' + data1[i].tContentName + '"   class="form-control" value="' + defaultvalue + '">'
                }
                break;
            case 0:
                // if (data1[i].tContentState == 1) {
                //     str += contentValue +
                //         '<textarea class="editor" needcheck="true" name="' + data1[i].tContentValue + '" temcontent="' + data1[i].tContentName + '" id="#editor" rows="10" cols="80">' + defaultvalue + '</textarea>'
                //
                // } else {
                    str += '<textarea class="editor" style="visibility:hidden;width:0;height:0" name="contentValue" temcontent="' + data1[i].tContentName + '" id="#editor" rows="10" cols="80">' + defaultvalue + '</textarea>'
                // }
                str += '<h3>' + data1[i].tContentValue +
                    '<small>&nbsp;拖动图片进入编辑框，即可上传</small>' +
                    '</h3>' +
                    '<div id="detailText">' +
                    '<iframe id="textEditor" src="/oss/sv1/page/textEditor?dir=resources/app/images/products/&aspect=0"></iframe> ' +
                    '</div>';
                isfwb = true;
                break;
            case 1:
                str += '<input type = "hidden" id="userImage" temcontent="' + data1[i].tContentName + '"   class="form-control" value="' + defaultvalue + '">'
                str += '<label><div class="row">图片<input type="button" class="deleteImage" id="deleteImage" value="移除图片"></div></label><div id="upBox" temcontent="' + data1[i].tContentName + '">' +
                    '<div class="row ">' +
                    '<div class="block col-md-3 itemAddPic relative"> ' +
                    '<div class="pics" style="background-image:url(' + '\'/oss/static/app/imgs/add-button.png\'' + ')"></div>' +
                    '<div class="btns"> ' +
                    '<button class="btnUpdate btn btn-info" disabled="true" >更新</button> ' +
                    '<button class="btnAdd btn btn-info">添加</button> ' +
                    '</div></div></div>'
                // str += '<label><div class="row">图片<input type="button" class="deleteImage" id="deleteImage" value="移除图片"></div></label><div id="upBox" temcontent="' + data1[i].tContentName + '">' +
                //     '<div id="uploader1" class="uploader wu-example ">' +
                //     '<div class="queueList">' +
                //     '<div id="dndArea" class="placeholder">' +
                //     '<div id="filePicker1" class="fileSelect webuploader-container"><span ' +
                //     'class="pickerSpan">添加图片</span>' +
                //     '<div id="imgBoxUser" class="imgBox"></div>' +
                //     '</div>' +
                //     '<div class="statusBar" style="display:none;">' +
                //     '<p class="progress" style="display: none;">' +
                //     '<span></span>' +
                //     '</p>' +
                //     '<div class="info">共0张（0B），已上传0张</div>' +
                //     '</div>' +
                //     '</div>' +
                //     '</div>' +
                //     '</div>'
                isimg = true;
                break;
            case 2:
                str += contentValue +
                    '<input type="text" needcheck="true" id="indexResourceUrl" name="' + data1[i].tContentValue + '" temcontent="' + data1[i].tContentName + '"  class="form-control" value="' + defaultvalue + '">'
                break;
            case 3:
                break
        }
        str += "</div>";
    }
    $(classname).html(str);
    if (isfwb) {
        txtFrame = $('#textEditor');
        var txtWidth = $("#detailText").width();
        txtFrame.attr("width", txtWidth);
        txtFrame.attr("height", 800);
        var txtIframe = txtFrame[0], txtWindow = txtIframe.contentWindow, editorview;
        txtIframe.addEventListener("load", function () {
            editorview = txtWindow.editor;
            editorview.setData(defaultvalue);
            ss=editorview;
            console.log("editorview+ss:"+editorview);
        });
        txtIframe.addEventListener("error", function () {
            cAlert("编辑器未初始化");
        });
    }

    // uploadinit("#uploader", "#filePicker", "#indexIcon", "#imageADD");
    // if (isimg) {
    //     uploadinit("#uploader1", "#filePicker1", "#userImage", "#imgBoxUser");
    // }
    $(".btnAdd").click(function () {
        var iframeContainer = $("#uploadDiv");
        // <iframe src="/oss/sv1/page/upload?aspect=1&dir=/resources/app/images/products/&iframe="></iframe>
        var url = base + "&aspect=0";
        iframeContainer.css({"width": "535px", "min-height": "350px"});
        var width = iframeContainer.width();
        iframeContainer.html("");
        url = base + "&aspect=0";
        elFrame = document.createElement("iframe");
        elFrame.src = url;
        elFrame.width = 535;
        elFrame.height = 350;
        iframeContainer.append(elFrame);
        //iframeContainer.append('<iframe id="uploader" src="' + url + '" width="535" height="330"></iframe>');
        //cAlert("请点击下面的区域添加文件，选择完毕后请点击确认上传");
        $("#uploadModal").modal("show");
    });
    $("#updateAddrConfirm").click(function () {
        elFrame.contentWindow.postMessage("process", "*");
    });
    $("#deleteImage").click(function () {
        // alert("给去消失");
        deleteImage();
    });
    return ss;
    // //添加图片
    // var uploader = WebUploader.create({
    //     auto: true,
    //     swf: '${context}/static/admin/plugins/webuploader/js/Uploader.swf',
    //     // 文件接收服务端。
    //     server: '/sv1/admin/upload/up',
    //     // 选择文件的按钮。可选。
    //     // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    //     pick: '.fileSelect',
    //     // 只允许选择图片文件。
    //     accept: {
    //         title: 'Images',
    //         extensions: 'gif,jpg,jpeg,bmp,png',
    //         mimeTypes: 'image/gif, image/png, image/jpeg, image/bmp, image/jpg'
    //     }
    // });
    // uploader.on('uploadProgress', function (file, percentage) {
    //     var $li = $('#' + file.id),
    //         $percent = $li.find('.progress span');
    //
    //     // 避免重复创建
    //     if (!$percent.length) {
    //         $percent = $('<p class="progress"><span></span></p>')
    //             .appendTo($li)
    //             .find('span');
    //     }
    //     $percent.css('width', percentage * 100 + '%');
    // });
    // uploader.on('uploadSuccess', function (file, data) {
    //     $('#' + file.id).addClass('upload-state-done');
    //     $("#indexIcon").val(data.msg);
    //     var temcontent = $("#upBox").temcontent;
    //     $('.fileSelect .imgBox').html('<img id="image_icon" src="' + data.msg + '" temcontent="' + temcontent + '" />');
    // });
    // uploader.on('uploadError', function (file) {
    //     var $li = $('#' + file.id),
    //         $error = $li.find('div.error');
    //     // 避免重复创建
    //     if (!$error.length) {
    //         $error = $('<div class="error"></div>').appendTo($li);
    //     }
    //     $error.text('上传失败');
    // });
    // uploader.on('uploadComplete', function (file) {
    //     $('#' + file.id).find('.progress').remove();
    // });

}
function uploadinit(wrapId, pickId, inputId, imgBoxId) {
    //图片
    if (wrapId == null)
        wrapId = "#uploader";
    if (pickId == null)
        pickId = "#filePicker";
    if (imgBoxId == null)
        imgBoxId = ".imgBox";
    if (inputId == null)
        inputId = "#indexIcon";
    var $wrap = $(wrapId),
    // 图片容器
        $queue = $('<div class="statusBar" ></div>')
            .appendTo($wrap.find('.queueList')),
    //    图片展示的位置
        $imgBox = $(imgBoxId),
    // 状态栏，包括进度和控制按钮
        $statusBar = $wrap.find('.statusBar'),
    // 文件总体选择信息。
        $info = $statusBar.find('.info'),
    // 总体进度条
        $progress = $statusBar.find('.progress').hide(),
    // 添加的文件数量
        fileCount = 0,
    // 添加的文件总大小
        fileSize = 0,
    // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,
    // 缩略图大小
        thumbnailWidth = 110 * ratio,
        thumbnailHeight = 110 * ratio,
    // 可能有pedding, ready, uploading, confirm, done.
        state = 'pedding',
    // 所有文件的进度信息，key为file id
        percentages = {},
        supportTransition = (function () {
            var s = document.createElement('p').style,
                r = 'transition' in s ||
                    'WebkitTransition' in s ||
                    'MozTransition' in s ||
                    'msTransition' in s ||
                    'OTransition' in s;
            s = null;
            return r;
        })(),
    // WebUploader实例
        uploader;
    if (!WebUploader.Uploader.support()) {
        alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
        throw new Error('WebUploader does not support the browser you are using.');
    }
    // 实例化
    var uploader = WebUploader.create({
        auto: true,
        swf: '/static/webuploader/js/Uploader.swf',
        // 文件接收服务端。
        server: '/sv1/admin/upload/up',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: pickId,
        fileSingleSizeLimit: 1 * 1024 * 1024,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/gif, image/png, image/jpeg, image/bmp, image/jpg'
        }
    });

    function addFile(file) {
        $statusBar.show();
        if (file.getStatus() === 'invalid') {
            showError(file.statusText);
        } else {
            // @todo lazyload
            $imgBox.text('预览中');
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    $imgBox.text('不能预览');
                    return;
                }
                var img = $('<img src="' + src + '" height="300px" width="400px">');
                $imgBox.empty().append(img);
            }, thumbnailWidth, thumbnailHeight);
            percentages[file.id] = [file.size, 0];
        }
    }

    // 负责view的销毁
    function removeFile(file) {
        var $li = $('#' + file.id);
        delete percentages[file.id];
        $statusBar.hide();
        updateTotalProgress();
        // $li.off().find('.file-panel').off().end().remove();
    }

    function updateTotalProgress() {
        var loaded = 0,
            total = 0,
            spans = $progress.children(),
            percent;
        $.each(percentages, function (k, v) {
            total += v[0];
            loaded += v[0] * v[1];
        });
        percent = total ? loaded / total : 0;
        spans.eq(0).text(Math.round(percent * 100) + '%');
        spans.eq(1).css('width', Math.round(percent * 100) + '%');
        updateStatus();
    }

    function updateStatus() {
        var text = '', stats;
        if (state === 'ready') {
            text = '选中' + fileCount + '张图片，共' +
                WebUploader.formatSize(fileSize) + '。';
        } else if (state === 'confirm') {
            stats = uploader.getStats();
            if (stats.uploadFailNum) {
                text = '已成功上传' + stats.successNum + '张图片' +
                    stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
            }
        } else {
            stats = uploader.getStats();
            text = '共' + fileCount + '张（' +
                WebUploader.formatSize(fileSize) +
                '），已上传' + stats.successNum + '张';
            if (stats.uploadFailNum) {
                text += '，失败' + stats.uploadFailNum + '张';
            }
        }
        $info.html(text);
    }

    function setState(val) {
        var file, stats;
        if (val === state) {
            return;
        }
        state = val;
        switch (state) {
            case 'pedding':
                $statusBar.addClass('element-invisible');
                uploader.refresh();
                break;
            case 'ready':
                // $placeHolder.addClass( 'element-invisible' );
                // $( '#filePicker2' ).removeClass( 'element-invisible');
                // $queue.parent().addClass('filled');
                // $queue.show();
                $statusBar.removeClass('element-invisible');
                uploader.refresh();
                break;
            case 'uploading':
                // $( '#filePicker2' ).addClass( 'element-invisible' );
                $progress.show();
                // $upload.text( '暂停上传' );
                break;
            case 'paused':
                $progress.show();
                // $upload.text( '继续上传' );
                break;
            case 'confirm':
                $progress.hide();
                // $upload.text( '开始上传' ).addClass( 'disabled' );
                stats = uploader.getStats();
                if (stats.successNum && !stats.uploadFailNum) {
                    setState('finish');
                    return;
                }
                break;
            case 'finish':
                stats = uploader.getStats();
                // if ( stats.successNum ) {
                //     alert( '上传成功' );
                // } else {
                //     // 没有成功的图片，重设
                //     state = 'done';
                //     alert( '上传失败' );
                //     location.reload();
                // }
                break;
        }
        updateStatus();
    }

    uploader.onUploadProgress = function (file, percentage) {
        percentages[file.id][1] = percentage;
        setState('uploading');
        updateTotalProgress();
        // updateTotalProgress();
    };
    uploader.on('uploadSuccess', function (file, data) {
        $('#' + file.id).addClass('upload-state-done');
        $(inputId).val(data.msg);
        console.log($(inputId).val());
        $(pickId + ' .imgBox').html('<img src="' + data.msg + '" height="300" width="400" />');
        fileCount--;
        fileSize -= file.size;
        if (!fileCount) {
            setState('finish');
        }
        removeFile(file);
        updateTotalProgress();
    });
    // uploader.on('uploadComplete', function (file) {
    //     fileCount--;
    //     fileSize -= file.size;
    //     if (!fileCount) {
    //         setState('finish');
    //     }
    //     removeFile(file);
    //     updateTotalProgress();
    // });
    uploader.onFileQueued = function (file) {
        fileCount++;
        fileSize += file.size;
        if (fileCount === 1) {
            // $placeHolder.addClass( 'element-invisible' );
            $statusBar.show();
        }
        addFile(file);
        setState('uploading');
        updateTotalProgress();
    };
    uploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            alert("请上传JPG、PNG格式文件");
        } else if (type == "F_EXCEED_SIZE") {
            alert("文件大小不能超过1M");
        }
    });
    updateTotalProgress();
}


//获取模版类型
function renderTemplate(selectId, callback) {
    if (templateDatas || templateDatas.length == 0) {
        $.get(templateUrl, {selectId: selectId}, function (data) {
            if (data.status == -1) {
                console.log("error");
                showError(data.msg);
            } else {
                console.log(data);
                templateDatas = data;
                var datatemplate = getTemplateIndex(templateDatas.datas);
                console.log(datatemplate.length);
                constructSelectByTemplate(datatemplate, "templateId", ".selectBoxBytemplateId", templateDatas.status, callback);
            }
        }).error(function () {
            showError("获取模版类型失败");
        });
    } else {

    }


}
//删除已经上传的图片
function deleteImage() {
    $('.imgBox').html('<img src=""  />');
}

//获取类目索引 并渲染数据
function renderCateIndex(type, selectName, selectId, callback, root) {
    $.get(cateIndexUrl, {}, function (data) {
        if (data.status == -1) {
            showError(data.msg);
        } else {
            console.log(data.datas);
            var dataLevel = getLeveIndex(data.datas, root, root);
            /*console.log(dataLevel);*/
            console.log("走了1", dataLevel);
            if (selectName != null) {
                constructSelect(dataLevel, selectName, ".selectBox1", selectId, type, callback);
            } else {
                constructSelect(dataLevel, "indexParent", ".selectBox", selectId, type, callback);
            }

        }
    }).error(function () {
        showError("获取类目索引失败");
    });
}


//获取文章索引 并渲染
function renderPageIndex(indexId) {
    $.post(pageIndexUrl, {"indexId": indexId}, function (data) {
        constructEdit(data.data);
    })
}
function deleteIndex(indexId) {
    $.post("/sv1/admin/index/delete", {
        indexId: indexId
    }, function (data) {
        if (data.status == -1) {
            showError(data.msg);
        } else {
            window.location.href = "/sv1/admin/index/cateIndex";
            console.log(data);
        }
    }).error(function () {
        showError("删除失败");
    });
}
//重置表单
function resetPageInsert(targetId) {
    $(".insertIndex .imgBox").html('<img src="" />');
    $(".insertIndex #indexIcon").val("");
    $(".insertIndex #indexContent").val("");
    $(".insertIndex #indexTitle").val("");
    $(".insertIndex #IndexPageId").val("");
    $(".insertIndex #indexId").val("");
    $(".insertIndex .indexStatus").val("");
    $(".insertIndex .template").val("");
    $(".insertIndex #indexResourceUrl").val("");
    $(".insertIndex #indexIndex").val("");
    $(".insertIndex").attr("action", "/sv1/admin/index/insertIndex")
    $("#indexParentId").val(targetId);
    console.log(targetId);
    $("#addPageIndex").modal("show");
}


function CKupdate() {
    for (instance in CKEDITOR.instances)
        CKEDITOR.instances[instance].updateElement();
}


//相关函数
function constructSelect(data, name, select, selectId, type, callback) {
    var length = data.length, i = 0, str = "";
    // /*console.log("aaaaa",data);*/
    console.log("selectId" + selectId)
    str += "<select id=\"" + name + "\" name=\"" + name + "\" class=\"form-control\">";
    if (type == 2) {
        str += "<option value=\"\">全部</option>";
    }
    for (i; i < length; i++) {
        if (data[i].id == 258 || data[i].id == 290) {
            continue;
        }
        if (data[i].id == selectId) {
            str += "<option  value=\"" + data[i].id + "\" selected=\"selected\">" + data[i].level + data[i].title + "</option>";
        } else {
            if (type > 0) {
                str += "<option value=\"" + data[i].id + "\">" + data[i].level + data[i].title + "</option>";
            } else {
                if (data[i].rank != 1) {
                    str += "<option value=\"" + data[i].id + "\">" + data[i].level + data[i].title + "</option>";
                } else {
                    str += "<option disabled=\"\" value=\"" + data[i].id + "\">" + data[i].level + data[i].title + "</option>";
                }
            }

        }

    }
    str += "</select>";
    $(select).html(str);
    callback();
}
//相关函数
function constructSelectByTemplate(data, name, select, selectId, callback) {
    var length = data.length, i = 0, str = "";
    /*console.log("aaaaa",data);*/
    str += "<select id=\"" + name + "\" name=\"" + name + "\" class=\"form-control\">";
    for (i; i < length; i++) {
        if (data[i].id == selectId) {
            str += "<option  value=\"" + data[i].id + "\" selected=\"selected\">" + data[i].name + "</option>";
        } else {
            str += "<option value=\"" + data[i].id + "\">" + data[i].name + "</option>";
        }
    }
    str += "</select>";
    $(select).html(str);
    callback();
}

function getTemplateIndex(data) {
    var length = data.length, i = 0;
    templateData = [];
    for (i; i < length; i++) {
        templateData.push({
            "id": data[i].template.templateId,
            "name": data[i].template.templateDisplayName
        });
    }
    return templateData;
}

function getLeveIndex(data, pid, root) {
    var length = data.length, i = 0;
    if (pid == root) {
        levelData = [];
    }
    for (i; i < length; i++) {
        if (data[i].indexId == root) {
            continue;
        }
        if (data[i].indexParent == pid && data[i].indexStatus >= 0) {
            var level = repeat("├─ ", data[i].indexLevel * 1 - 1);
            levelData.push({
                "id": data[i].indexId,
                "pid": data[i].indexParent,
                "level": level,
                "rank": data[i].indexLevel * 1,
                "title": data[i].indexTitle
            });
            getLeveIndex(data, data[i].indexId, root);
        }
    }
    return levelData;
}
function repeat(target, n) {
    var s = target, total = "";
    while (n > 0) {
        if (n % 2 == 1) {
            total += s;
        }
        if (n == 1) {
            break;
        }
        s += s;
        n = n >> 1;//相当于将n除以2取其商，或者说是开2次方
    }
    return total;
}
function constructEdit(data) {

    $(".insertIndex .imgBox").html('<img src="" height="300" width="400"/>');
    $(".insertIndex #indexIcon").val('');
    $(".insertIndex #indexContent").val('');
    $(".insertIndex #indexTitle").val('');
    $(".insertIndex #IndexPageId").val('');
    $(".insertIndex #indexId").val('');
    $(".insertIndex .indexStatus").val('');
    $(".insertIndex .template").val('');
    $(".insertIndex #indexResourceUrl").val('');
    $(".insertIndex #indexParentId").val('');
    $(".insertIndex #indexIndex").val('');
    if (data.indexIcon) {
        $(".insertIndex .imgBox").html('<img src="' + data.indexIcon + '" height="300" width="400"/>');
    }
    $(".insertIndex #indexIcon").val(data.indexIcon);
    $(".insertIndex #indexContent").val(data.indexContent);
    $(".insertIndex #indexTitle").val(data.indexTitle);
    $(".insertIndex #IndexPageId").val(data.indexPageId);
    $(".insertIndex #indexId").val(data.indexId);
    $(".insertIndex .indexStatus").val(data.indexStatus);
    $(".insertIndex .template").val(data.indexDefaultTemplate);
    $(".insertIndex #indexResourceUrl").val(data.indexResourceUrl);
    $(".insertIndex #indexParentId").val(data.indexParent);
    $(".insertIndex #indexIndex").val(data.indexIndex);
    $(".insertIndex").attr("action", "/sv1/admin/index/handleEdit")
    $("#addPageIndex").modal("show");
}

showError = function (msg) {
    $("#errorModal").find("#errorContent").html(msg);
    $("#errorModal").modal("show");
}


function ajaxGet(that) {
    var target;
    if ((target = that.attr('href')) || (target = that.attr('url'))) {
        $.get(target).success(function (data) {
            if (data['status'] == 1) {
                //alert(data.url);
                location.assign(data.url);
            } else {
                showError(data.msg);
            }
        }).error(function () {
            showError("请求失败");
        });
        ;

    }
    return false;
}


handelHttp = function () {
    //ajax get请求
    $('.ajax-get').click(function () {
        console.log("aaaaaaa");
        e.preventDefault;
        var target;
        var that = this;
        if ($(this).hasClass('confirm')) {
            if (!confirm('确认要执行该操作吗?')) {
                return false;
            }
        }
        if ((target = $(this).attr('href')) || (target = $(this).attr('url'))) {
            $.get(target).success(function (data) {
                if (data['status'] == 1) {
                    //alert(data.url);
                    window.location.href = data.url;
                } else {
                    showError(data.msg);
                }
            }).error(function () {
                showError("请求失败");
            });
            ;

        }
        return false;
    });
    //ajax post submit请求
    $('.ajax-post').click(function (e) {
        e.preventDefault;
        CKupdate();
        var target, query, form;
        var target_form = $(this).attr('target-form');
        var that = this;
        var nead_confirm = false;

        var target_model = $(this).attr('target-model');
        if (($(this).attr('type') == 'submit') || (target = $(this).attr('href')) || (target = $(this).attr('url'))) {
            form = $('.' + target_form);
            if ($(this).attr('hide-data') === 'true') {//无数据时也可以使用的功能
                form = $('.hide-data');
                query = form.serialize();
            } else if (form.get(0) == undefined) {
                return false;
            } else if (form.get(0).nodeName == 'FORM') {
                if ($(this).hasClass('confirm')) {
                    if (!confirm('确认要执行该操作吗?')) {
                        return false;
                    }
                }
                if ($(this).attr('url') !== undefined) {
                    target = $(this).attr('url');
                } else {
                    target = form.get(0).action;
                }
                query = form.serialize();
            } else if (form.get(0).nodeName == 'INPUT' || form.get(0).nodeName == 'SELECT' || form.get(0).nodeName == 'TEXTAREA') {
                form.each(function (k, v) {
                    if (v.type == 'checkbox' && v.checked == true) {
                        nead_confirm = true;
                    }
                })
                if (nead_confirm && $(this).hasClass('confirm')) {
                    if (!confirm('确认要执行该操作吗?')) {
                        return false;
                    }
                }
                query = form.serialize();
            } else {
                if ($(this).hasClass('confirm')) {
                    if (!confirm('确认要执行该操作吗?')) {
                        return false;
                    }
                }
                query = form.find('input,select,textarea').serialize();
            }
            //$(that).addClass('disabled').attr('autocomplete','off').prop('disabled',true);
            $.post(target, query).success(function (data) {
                //alert(data['status']);
                if (data['status'] > 0) {
                    window.location.href = data.url;
                } else {
                    showError(data.msg);
                }
            }).error(function () {
                showError("请求失败");
            });
        }
        return false;
    });
}


/*
 Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.4
 Version: 1.7.0
 Author: Sean Ngu
 Website: http://www.seantheme.com/color-admin-v1.7/admin/
 */
var handleSlimScroll = function () {
        'use strict';
        $('[data-scrollbar=true]').each(function () {
            generateSlimScroll($(this))
        })
    },
    generateSlimScroll = function (e) {
        var a = $(e).attr('data-height');
        a = a ? a : $(e).height();
        var t = {
            height: a,
            alwaysVisible: !0
        };
        /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ? ($(e).css('height', a), $(e).css('overflow-x', 'scroll')) : $(e).slimScroll(t)
    },
    handleSidebarMenu = function () {
        'use strict';
        $('.sidebar .nav > .has-sub > a').click(function () {
            var e = $(this).next('.sub-menu'),
                a = '.sidebar .nav > li.has-sub > .sub-menu';
            0 === $('.page-sidebar-minified').length && ($(a).not(e).slideUp(250, function () {
                $(this).closest('li').removeClass('expand')
            }), $(e).slideToggle(250, function () {
                var e = $(this).closest('li');
                $(e).hasClass('expand') ? $(e).removeClass('expand') : $(e).addClass('expand')
            }))
        }),
            $('.sidebar .nav > .has-sub .sub-menu li.has-sub > a').click(function () {
                if (0 === $('.page-sidebar-minified').length) {
                    var e = $(this).next('.sub-menu');
                    $(e).slideToggle(250)
                }
            })
    },
    handleMobileSidebarToggle = function () {
        var e = !1;
        $('.sidebar').on('click touchstart', function (a) {
            0 !== $(a.target).closest('.sidebar').length ? e = !0 : (e = !1, a.stopPropagation())
        }),
            $(document).on('click touchstart', function (a) {
                0 === $(a.target).closest('.sidebar').length && (e = !1),
                a.isPropagationStopped() || e === !0 || ($('#page-container').hasClass('page-sidebar-toggled') && (e = !0, $('#page-container').removeClass('page-sidebar-toggled')), $('#page-container').hasClass('page-right-sidebar-toggled') && (e = !0, $('#page-container').removeClass('page-right-sidebar-toggled')))
            }),
            $('[data-click=right-sidebar-toggled]').click(function (a) {
                a.stopPropagation();
                var t = '#page-container',
                    i = 'page-right-sidebar-collapsed';
                i = $(window).width() < 979 ? 'page-right-sidebar-toggled' : i,
                    $(t).hasClass(i) ? $(t).removeClass(i) : e !== !0 ? $(t).addClass(i) : e = !1,
                $(window).width() < 480 && $('#page-container').removeClass('page-sidebar-toggled')
            }),
            $('[data-click=sidebar-toggled]').click(function (a) {
                a.stopPropagation();
                var t = 'page-sidebar-toggled',
                    i = '#page-container';
                $(i).hasClass(t) ? $(i).removeClass(t) : e !== !0 ? $(i).addClass(t) : e = !1,
                $(window).width() < 480 && $('#page-container').removeClass('page-right-sidebar-toggled')
            })
    },
    handleSidebarMinify = function () {
        $('[data-click=sidebar-minify]').click(function (e) {
            e.preventDefault();
            var a = 'page-sidebar-minified',
                t = '#page-container';
            $(t).hasClass(a) ? ($(t).removeClass(a), $(t).hasClass('page-sidebar-fixed') && (generateSlimScroll($('#sidebar [data-scrollbar="true"]')), $('#sidebar [data-scrollbar=true]').trigger('mouseover'), $('#sidebar [data-scrollbar=true]').stop(), $('#sidebar [data-scrollbar=true]').css('margin-top', '0'))) : ($(t).addClass(a), /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ? ($('#sidebar [data-scrollbar="true"]').css('margin-top', '0'), $('#sidebar [data-scrollbar="true"]').css('overflow', 'visible')) : ($(t).hasClass('page-sidebar-fixed') && ($('#sidebar [data-scrollbar="true"]').slimScroll({
                destroy: !0
            }), $('#sidebar [data-scrollbar="true"]').removeAttr('style')), $('#sidebar [data-scrollbar=true]').trigger('mouseover'))),
                $(window).trigger('resize')
        })
    },
    handlePageContentView = function () {
        'use strict';
        $.when($('#page-loader').addClass('hide')).done(function () {
            $('#page-container').addClass('in')
        })
    },
    handlePanelAction = function () {
        'use strict';
        $('[data-click=panel-remove]').hover(function () {
            $(this).tooltip({
                title: 'Remove',
                placement: 'bottom',
                trigger: 'hover',
                container: 'body'
            }),
                $(this).tooltip('show')
        }),
            $('[data-click=panel-remove]').click(function (e) {
                e.preventDefault(),
                    $(this).tooltip('destroy'),
                    $(this).closest('.panel').remove()
            }),
            $('[data-click=panel-collapse]').hover(function () {
                $(this).tooltip({
                    title: 'Collapse / Expand',
                    placement: 'bottom',
                    trigger: 'hover',
                    container: 'body'
                }),
                    $(this).tooltip('show')
            }),
            $('[data-click=panel-collapse]').click(function (e) {
                e.preventDefault(),
                    $(this).closest('.panel').find('.panel-body').slideToggle()
            }),
            $('[data-click=panel-reload]').hover(function () {
                $(this).tooltip({
                    title: 'Reload',
                    placement: 'bottom',
                    trigger: 'hover',
                    container: 'body'
                }),
                    $(this).tooltip('show')
            }),
            $('[data-click=panel-reload]').click(function (e) {
                e.preventDefault();
                var a = $(this).closest('.panel');
                if (!$(a).hasClass('panel-loading')) {
                    var t = $(a).find('.panel-body'),
                        i = '<div class="panel-loader"><span class="spinner-small"></span></div>';
                    $(a).addClass('panel-loading'),
                        $(t).prepend(i),
                        setTimeout(function () {
                            $(a).removeClass('panel-loading'),
                                $(a).find('.panel-loader').remove()
                        }, 2000)
                }
            }),
            $('[data-click=panel-expand]').hover(function () {
                $(this).tooltip({
                    title: 'Expand / Compress',
                    placement: 'bottom',
                    trigger: 'hover',
                    container: 'body'
                }),
                    $(this).tooltip('show')
            }),
            $('[data-click=panel-expand]').click(function (e) {
                e.preventDefault();
                var a = $(this).closest('.panel'),
                    t = $(a).find('.panel-body'),
                    i = 40;
                if (0 !== $(t).length) {
                    var n = $(a).offset().top,
                        o = $(t).offset().top;
                    i = o - n
                }
                if ($('body').hasClass('panel-expand') && $(a).hasClass('panel-expand')) $('body, .panel').removeClass('panel-expand'),
                    $('.panel').removeAttr('style'),
                    $(t).removeAttr('style');
                else if ($('body').addClass('panel-expand'), $(this).closest('.panel').addClass('panel-expand'), 0 !== $(t).length && 40 != i) {
                    var l = 40;
                    $(a).find(' > *').each(function () {
                        var e = $(this).attr('class');
                        'panel-heading' != e && 'panel-body' != e && (l += $(this).height() + 30)
                    }),
                    40 != l && $(t).css('top', l + 'px')
                }
                $(window).trigger('resize')
            })
    },
    handleDraggablePanel = function () {
        'use strict';
        var e = $('.panel').parent('[class*=col]'),
            a = '.panel-heading',
            t = '.row > [class*=col]';
        $(e).sortable({
            handle: a,
            connectWith: t,
            stop: function (e, a) {
                a.item.find('.panel-title').append('<i class="fa fa-refresh fa-spin m-l-5" data-id="title-spinner"></i>'),
                    handleSavePanelPosition(a.item)
            }
        })
    },
    handelTooltipPopoverActivation = function () {
        'use strict';
        $('[data-toggle=tooltip]').tooltip(),
            $('[data-toggle=popover]').popover()
    },
    handleScrollToTopButton = function () {
        'use strict';
        $(document).scroll(function () {
            var e = $(document).scrollTop();
            e >= 200 ? $('[data-click=scroll-top]').addClass('in') : $('[data-click=scroll-top]').removeClass('in')
        }),
            $('[data-click=scroll-top]').click(function (e) {
                e.preventDefault(),
                    $('html, body').animate({
                        scrollTop: $('body').offset().top
                    }, 500)
            })
    },
    handleThemePageStructureControl = function () {
        if ($.cookie && $.cookie('theme')) {
            0 !== $('.theme-list').length && ($('.theme-list [data-theme]').closest('li').removeClass('active'), $('.theme-list [data-theme="' + $.cookie('theme') + '"]').closest('li').addClass('active'));
            var e = 'assets/css/theme/' + $.cookie('theme') + '.css';
            $('#theme').attr('href', e)
        }
        $.cookie && $.cookie('sidebar-styling') && 0 !== $('.sidebar').length && 'grid' == $.cookie('sidebar-styling') && ($('.sidebar').addClass('sidebar-grid'), $('[name=sidebar-styling] option[value="2"]').prop('selected', !0)),
        $.cookie && $.cookie('header-styling') && 0 !== $('.header').length && 'navbar-inverse' == $.cookie('header-styling') && ($('.header').addClass('navbar-inverse'), $('[name=header-styling] option[value="2"]').prop('selected', !0)),
        $.cookie && $.cookie('content-gradient') && 0 !== $('#page-container').length && 'enabled' == $.cookie('content-gradient') && ($('#page-container').addClass('gradient-enabled'), $('[name=content-gradient] option[value="2"]').prop('selected', !0)),
        $.cookie && $.cookie('content-styling') && 0 !== $('body').length && 'black' == $.cookie('content-styling') && ($('body').addClass('flat-black'), $('[name=content-styling] option[value="2"]').prop('selected', !0)),
            $('.theme-list [data-theme]').live('click', function () {
                var e = 'assets/css/theme/' + $(this).attr('data-theme') + '.css';
                $('#theme').attr('href', e),
                    $('.theme-list [data-theme]').not(this).closest('li').removeClass('active'),
                    $(this).closest('li').addClass('active'),
                    $.cookie('theme', $(this).attr('data-theme'))
            }),
            $('.theme-panel [name=header-styling]').live('change', function () {
                var e = 1 == $(this).val() ? 'navbar-default' : 'navbar-inverse',
                    a = 1 == $(this).val() ? 'navbar-inverse' : 'navbar-default';
                $('#header').removeClass(a).addClass(e),
                    $.cookie('header-styling', e)
            }),
            $('.theme-panel [name=sidebar-styling]').live('change', function () {
                2 == $(this).val() ? ($('#sidebar').addClass('sidebar-grid'), $.cookie('sidebar-styling', 'grid')) : ($('#sidebar').removeClass('sidebar-grid'), $.cookie('sidebar-styling', 'default'))
            }),
            $('.theme-panel [name=content-gradient]').live('change', function () {
                2 == $(this).val() ? ($('#page-container').addClass('gradient-enabled'), $.cookie('content-gradient', 'enabled')) : ($('#page-container').removeClass('gradient-enabled'), $.cookie('content-gradient', 'disabled'))
            }),
            $('.theme-panel [name=content-styling]').live('change', function () {
                2 == $(this).val() ? ($('body').addClass('flat-black'), $.cookie('content-styling', 'black')) : ($('body').removeClass('flat-black'), $.cookie('content-styling', 'default'))
            }),
            $('.theme-panel [name=sidebar-fixed]').live('change', function () {
                1 == $(this).val() ? (2 == $('.theme-panel [name=header-fixed]').val() && (alert('Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar.'), $('.theme-panel [name=header-fixed] option[value="1"]').prop('selected', !0), $('#header').addClass('navbar-fixed-top'), $('#page-container').addClass('page-header-fixed')), $('#page-container').addClass('page-sidebar-fixed'), $('#page-container').hasClass('page-sidebar-minified') || generateSlimScroll($('.sidebar [data-scrollbar="true"]'))) : ($('#page-container').removeClass('page-sidebar-fixed'), 0 !== $('.sidebar .slimScrollDiv').length && ($(window).width() <= 979 ? $('.sidebar').each(function () {
                    if (!$('#page-container').hasClass('page-with-two-sidebar') || !$(this).hasClass('sidebar-right')) {
                        $(this).find('.slimScrollBar').remove(),
                            $(this).find('.slimScrollRail').remove(),
                            $(this).find('[data-scrollbar="true"]').removeAttr('style');
                        var e = $(this).find('[data-scrollbar="true"]').parent(),
                            a = $(e).html();
                        $(e).replaceWith(a)
                    }
                }) : $(window).width() > 979 && ($('.sidebar [data-scrollbar="true"]').slimScroll({
                    destroy: !0
                }), $('.sidebar [data-scrollbar="true"]').removeAttr('style'))), 0 === $('#page-container .sidebar-bg').length && $('#page-container').append('<div class="sidebar-bg"></div>'))
            }),
            $('.theme-panel [name=header-fixed]').live('change', function () {
                1 == $(this).val() ? ($('#header').addClass('navbar-fixed-top'), $('#page-container').addClass('page-header-fixed'), $.cookie('header-fixed', !0)) : (1 == $('.theme-panel [name=sidebar-fixed]').val() && (alert('Default Header with Fixed Sidebar option is not supported. Proceed with Default Header with Default Sidebar.'), $('.theme-panel [name=sidebar-fixed] option[value="2"]').prop('selected', !0), $('#page-container').removeClass('page-sidebar-fixed'), 0 === $('#page-container .sidebar-bg').length && $('#page-container').append('<div class="sidebar-bg"></div>')), $('#header').removeClass('navbar-fixed-top'), $('#page-container').removeClass('page-header-fixed'), $.cookie('header-fixed', !1))
            })
    },
    handleThemePanelExpand = function () {
        $('[data-click="theme-panel-expand"]').live('click', function () {
            var e = '.theme-panel',
                a = 'active';
            $(e).hasClass(a) ? $(e).removeClass(a) : $(e).addClass(a)
        })
    },
    handleAfterPageLoadAddClass = function () {
        0 !== $('[data-pageload-addclass]').length && $(window).load(function () {
            $('[data-pageload-addclass]').each(function () {
                var e = $(this).attr('data-pageload-addclass');
                $(this).addClass(e)
            })
        })
    },
    handleSavePanelPosition = function (e) {
        'use strict';
        if (0 !== $('.ui-sortable').length) {
            var a = [],
                t = 0;
            $.when($('.ui-sortable').each(function () {
                var e = $(this).find('[data-sortable-id]');
                if (0 !== e.length) {
                    var i = [];
                    $(e).each(function () {
                        var e = $(this).attr('data-sortable-id');
                        i.push({
                            id: e
                        })
                    }),
                        a.push(i)
                } else a.push([]);
                t++
            })).done(function () {
                var t = window.location.href;
                t = t.split('?'),
                    t = t[0],
                    localStorage.setItem(t, JSON.stringify(a)),
                    $(e).find('[data-id="title-spinner"]').delay(500).fadeOut(500, function () {
                        $(this).remove()
                    })
            })
        }
    },
    handleLocalStorage = function () {
        'use strict';
        if ('undefined' != typeof Storage) {
            var e = window.location.href;
            e = e.split('?'),
                e = e[0];
            var a = localStorage.getItem(e);
            if (a) {
                a = JSON.parse(a);
                var t = 0;
                $('.panel').parent('[class*="col-"]').each(function () {
                    var e = a[t],
                        i = $(this);
                    e && $.each(e, function (e, a) {
                        var t = '[data-sortable-id="' + a.id + '"]';
                        if (0 !== $(t).length) {
                            var n = $(t).clone();
                            $(t).remove(),
                                $(i).append(n)
                        }
                    }),
                        t++
                })
            }
        } else alert('Your browser is not supported with the local storage')
    },
    handleResetLocalStorage = function () {
        'use strict';
        $('[data-click=reset-local-storage]').live('click', function (e) {
            e.preventDefault();
            var a = '<div class="modal fade" data-modal-id="reset-local-storage-confirmation">    <div class="modal-dialog">        <div class="modal-content">            <div class="modal-header">                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>                <h4 class="modal-title"><i class="fa fa-refresh m-r-5"></i> Reset Local Storage Confirmation</h4>            </div>            <div class="modal-body">                <div class="alert alert-info m-b-0">Would you like to RESET all your saved widgets and clear Local Storage?</div>            </div>            <div class="modal-footer">                <a href="javascript:;" class="btn btn-sm btn-white" data-dismiss="modal"><i class="fa fa-close"></i> No</a>                <a href="javascript:;" class="btn btn-sm btn-inverse" data-click="confirm-reset-local-storage"><i class="fa fa-check"></i> Yes</a>            </div>        </div>    </div></div>';
            $('body').append(a),
                $('[data-modal-id="reset-local-storage-confirmation"]').modal('show')
        }),
            $('[data-modal-id="reset-local-storage-confirmation"]').live('hidden.bs.modal', function () {
                $('[data-modal-id="reset-local-storage-confirmation"]').remove()
            }),
            $('[data-click=confirm-reset-local-storage]').live('click', function (e) {
                e.preventDefault();
                var a = window.location.href;
                a = a.split('?'),
                    a = a[0],
                    localStorage.removeItem(a),
                    window.location.href = document.URL
            })
    },
    handleIEFullHeightContent = function () {
        var e = window.navigator.userAgent,
            a = e.indexOf('MSIE ');
        (a > 0 || navigator.userAgent.match(/Trident.*rv\:11\./)) && $('.vertical-box-row [data-scrollbar="true"][data-height="100%"]').each(function () {
            var e = $(this).closest('.vertical-box-row'),
                a = $(e).height();
            $(e).find('.vertical-box-cell').height(a)
        })
    },
    handleUnlimitedTabsRender = function () {
        function e(e, a) {
            var t = (parseInt($(e).css('margin-left')), $(e).width()),
                i = $(e).find('li.active').width(),
                n = a > -1 ? a : 150,
                o = 0;
            if ($(e).find('li.active').prevAll().each(function () {
                    i += $(this).width()
                }), $(e).find('li').each(function () {
                    o += $(this).width()
                }), i >= t) {
                var l = i - t;
                o != i && (l += 40),
                    $(e).find('.nav.nav-tabs').animate({
                        marginLeft: '-' + l + 'px'
                    }, n)
            }
            i != o && o >= t ? $(e).addClass('overflow-right') : $(e).removeClass('overflow-right'),
                i >= t && o >= t ? $(e).addClass('overflow-left') : $(e).removeClass('overflow-left')
        }

        function a(e, a) {
            var t = $(e).closest('.tab-overflow'),
                i = parseInt($(t).find('.nav.nav-tabs').css('margin-left')),
                n = $(t).width(),
                o = 0,
                l = 0;
            switch ($(t).find('li').each(function () {
                $(this).hasClass('next-button') || $(this).hasClass('prev-button') || (o += $(this).width())
            }), a) {
                case 'next':
                    var s = o + i - n;
                    n >= s ? (l = s - i, setTimeout(function () {
                        $(t).removeClass('overflow-right')
                    }, 150)) : l = n - i - 80,
                    0 != l && $(t).find('.nav.nav-tabs').animate({
                        marginLeft: '-' + l + 'px'
                    }, 150, function () {
                        $(t).addClass('overflow-left')
                    });
                    break;
                case 'prev':
                    var s = -i;
                    n >= s ? ($(t).removeClass('overflow-left'), l = 0) : l = s - n + 80,
                        $(t).find('.nav.nav-tabs').animate({
                            marginLeft: '-' + l + 'px'
                        }, 150, function () {
                            $(t).addClass('overflow-right')
                        })
            }
        }

        function t() {
            $('.tab-overflow').each(function () {
                var a = $(this).width(),
                    t = 0,
                    i = $(this),
                    n = a;
                $(i).find('li').each(function () {
                    var e = $(this);
                    t += $(e).width(),
                    $(e).hasClass('active') && t > a && (n -= t)
                }),
                    e(this, 0)
            })
        }

        $('[data-click="next-tab"]').live('click', function (e) {
            e.preventDefault(),
                a(this, 'next')
        }),
            $('[data-click="prev-tab"]').live('click', function (e) {
                e.preventDefault(),
                    a(this, 'prev')
            }),
            $(window).resize(function () {
                $('.tab-overflow .nav.nav-tabs').removeAttr('style'),
                    t()
            }),
            t()
    },
    handleMobileSidebar = function () {
        /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) && $('#page-container').hasClass('page-sidebar-minified') && ($('#sidebar [data-scrollbar="true"]').css('overflow', 'visible'), $('.page-sidebar-minified #sidebar [data-scrollbar="true"]').slimScroll({
            destroy: !0
        }), $('.page-sidebar-minified #sidebar [data-scrollbar="true"]').removeAttr('style'), $('.page-sidebar-minified #sidebar [data-scrollbar=true]').trigger('mouseover'));
        var e = 0;
        $('.page-sidebar-minified .sidebar [data-scrollbar=true] a').live('touchstart', function (a) {
            var t = a.originalEvent.touches[0] || a.originalEvent.changedTouches[0],
                i = t.pageY;
            e = i - parseInt($(this).closest('[data-scrollbar=true]').css('margin-top'))
        }),
            $('.page-sidebar-minified .sidebar [data-scrollbar=true] a').live('touchmove', function (a) {
                if (a.preventDefault(), /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
                    var t = a.originalEvent.touches[0] || a.originalEvent.changedTouches[0],
                        i = t.pageY,
                        n = i - e;
                    $(this).closest('[data-scrollbar=true]').css('margin-top', n + 'px')
                }
            }),
            $('.page-sidebar-minified .sidebar [data-scrollbar=true] a').live('touchend', function () {
                var a = $(this).closest('[data-scrollbar=true]'),
                    t = $(window).height();
                e = $(a).css('margin-top');
                var i = 0;
                $('.sidebar').find('.nav').each(function () {
                    i += $(this).height()
                });
                var n = -parseInt(e) + $('.sidebar').height();
                if (n >= i) {
                    var o = t - i;
                    $(a).animate({
                        marginTop: o + 'px'
                    })
                } else parseInt(e) >= 0 && $(a).animate({
                    marginTop: '0px'
                });
                return !0
            })
    },

    App = function () {
        'use strict';
        return {
            init: function () {
                handelHttp(),
                    handleDraggablePanel(),
                    handleLocalStorage(),
                    handleResetLocalStorage(),
                    handleSlimScroll(),
                    handleSidebarMenu(),
                    handleMobileSidebarToggle(),
                    handleSidebarMinify(),
                    handleMobileSidebar(),
                    handleThemePageStructureControl(),
                    handleThemePanelExpand(),
                    handleAfterPageLoadAddClass(),
                    handlePanelAction(),
                    handelTooltipPopoverActivation(),
                    handleScrollToTopButton(),
                    handlePageContentView(),
                    handleIEFullHeightContent(),
                    handleUnlimitedTabsRender()
            }
        }
    }();
