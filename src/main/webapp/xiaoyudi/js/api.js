// var urls = 'http://47.95.244.242/XiaoyudiApplication/app/question'
var urls = 'http://api.xiaoyudi.org/app/question'
var cookie = {
      set:function(key,val,time){//设置cookie方法
          var date=new Date(); //获取当前时间
          var expiresDays=time;  //将date设置为n天以后的时间
          date.setTime(date.getTime()+expiresDays*24*3600*1000); //格式化为cookie识别的时间
          document.cookie=key + "=" + val +";expires="+date.toGMTString();  //设置cookie
      },
      get:function(key){//获取cookie方法
         /*获取cookie参数*/
         var getCookie = document.cookie.replace(/[ ]/g,"");  //获取cookie，并且将获得的cookie格式化，去掉空格字符
         var arrCookie = getCookie.split(";")  //将获得的cookie以"分号"为标识 将cookie保存到arrCookie的数组中
         var tips;  //声明变量tips
        for(var i=0;i<arrCookie.length;i++){   //使用for循环查找cookie中的tipsÒ变量
             var arr=arrCookie[i].split("=");   //将单条cookie用"等号"为标识，将单条cookie保存为arr数组
             if(key==arr[0]){  //匹配变量名称，其中arr[0]是指的cookie名称，如果该条变量为tips则执行判断语句中的赋值操作
                 tips=arr[1];   //将cookie的值赋给变量tips
                 break;   //终止for循环遍历
             }
         }
         return tips;
     },
     delete:function(key){ //删除cookie方法
         var date = new Date(); //获取当前时间
         date.setTime(date.getTime()-10000); //将date设置为过去的时间
         document.cookie = key + "=v; expires =" +date.toGMTString();//设置cookie
     }
 }
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}
    // cookie.set("token",'A3ncoojlYWZgRSlakJLnSQ==')
if(getUrlParam('token')){
	cookie.set('token',getUrlParam('token'))
}
// cookie.set('token','6h6MMdVEcW6NHNr0S/jnA==')

function get(url,params) {
    params = params || {}
    params.token = cookie.get('token')+'=='
    return new Promise(function(resolve, reject) {
        $.ajax({
            url:urls + url,
            type:'GET',
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
function post(url,params) {
    params = params || {}
    params.token = cookie.get('token')+'=='
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
