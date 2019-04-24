
function getContextPath() {

    var webroot=document.location.href;
    webroot=webroot.substring(webroot.indexOf('//')+2,webroot.length);
    webroot=webroot.substring(webroot.indexOf('/')+1,webroot.length);
    webroot=webroot.substring(0,webroot.indexOf('/'));
    var rootpath="/"+webroot;
    if ("/XiaoyudiApplication" != rootpath){
        rootpath ="";
    }
    return rootpath;
}
var path = getContextPath();
var urls = path+'/manage/'
// var urls = 'http://47.95.244.242/XiaoyudiApplication/app/question'
// $.ajax({
//     url:'http://172.168.20.14:8080/app/user/shortcutLogin',
//     dataType:'json',
//     data:{
//         phone:'17795591253',
//         password:'1'
//     },
//     success:function (res) {
//         console.log(res);
//     }
// })
// var token = 'cNA33VmFEkL6ug2us2qQ4g=='
function get(url,params) {
    params = params || {}
    return new Promise(function(resolve, reject) {
        $.ajax({
            url:urls + url,
            type:'GET',
            // headers:{contentType:'application/json'},
            dataType:'json',
            data:params,
            success:function (response) {
                console.log(response);
                resolve(response)
            },
            error:function (err) {
                reject(err)
            }
        })
    });
}
function post(url,params) {
    params = params || {}
    params.token = token
    return new Promise(function(resolve, reject) {
        $.ajax({
            url:urls + url,
            type:'POST',
            // headers:{contentType:'application/json'},
            dataType:'json',
            data:params,
            success:function (response) {
                resolve(response)
            },
            error:function (err) {
                reject(err)
            }
        })
    });
}
function login(url,params) {
    params = params || {}
    // params.token = token
    return new Promise(function(resolve, reject) {
        $.ajax({
            url:urls + url,
            type:'POST',
            // headers:{contentType:'application/json'},
            dataType:'json',
            data:params,
            success:function (response) {
                resolve(response)
            },
            error:function (err) {
                reject(err)
            }
        })
    });
}
function tips(content){
    var p = document.createElement('p')
    $(p).addClass('tips')
    p.innerHTML = content;
    document.body.appendChild(p)
    setTimeout(function () {
        $(p).addClass('hides')
    },1000)
    setTimeout(function () {
        $(p).remove();
    },3000)
}