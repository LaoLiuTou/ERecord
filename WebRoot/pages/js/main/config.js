//后台服务地址
var url = 'http://192.168.101.204/ERecord/';
//secret key
var sk = 'TTILY';


$(document).ready(function(){


    $('#logoutBtn').click(function () {
        sessionStorage.clear();
        window.location.href='login.html';
    });

    var userinfo=sessionStorage.getItem('userinfo');
    if(userinfo!=null){
        $('#loginName').text(JSON.parse(userinfo)['nickname']);
    }
    var html=' <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="reset-password" class="modal fade">\n' +
        '      <div class="modal-dialog">\n' +
        '          <div class="modal-content">\n' +
        '              <div class="modal-header">\n' +
        '                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\n' +
        '                  <h4 class="modal-title">重置密码</h4>\n' +
        '              </div>\n' +
        '              <div class="modal-body">\n' +
        '                  <form role="form">\n' +
        '                      <div class="form-group">\n' +
        '                          <label>旧密码</label>\n' +
        '                          <input type="password" class="form-control" id="old_password" placeholder="请输入旧密码">\n' +
        '                      </div>\n' +
        '                      <div class="form-group">\n' +
        '                          <label>新密码</label>\n' +
        '                          <input type="password" class="form-control" id="new_password" placeholder="请输入新密码">\n' +
        '                      </div>\n' +
        '                      <div class="form-group">\n' +
        '                          <label>确认新密码</label>\n' +
        '                          <input type="password" class="form-control" id="renew_repassword" placeholder="请输入新密码">\n' +
        '                      </div>\n' +
        '                  </form>\n' +
        '              </div>\n' +
        '              <div class="clearfix"></div>\n' +
        '              <div class="modal-footer">\n' +
        '                  <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>\n' +
        '                  <button class="btn btn-info popovers" type="button" id="resetPasswordBtn">确定</button>\n' +
        '              </div>\n' +
        '          </div>\n' +
        '      </div>\n' +
        '  </div>';

    $('body').append(html);
    $('#resetPasswordBtn').click(function () {
        var userinfo = JSON.parse(sessionStorage.getItem('userinfo'));
        if(md5($('#old_password').val())!=userinfo.password){
            alert('您输入的旧密码错误！');
        }
        else{
            if ($('#new_password').val() == '') {
                alert('新密码不能为空！');
                return false;
            }
            if ($('#renew_repassword').val() == '') {
                alert('确认新密码不能为空！');
                return false;
            }
            if ($('#new_password').val() != $('#renew_repassword').val()) {
                alert('两次输入新密码不一致！');
                return false;
            }
            var bodyParam = {'id': userinfo.id, 'password': md5($('#new_password').val())};
            var httpR = new createHttpR(url+'updateUser','post','text',bodyParam,'callBack');
            httpR.HttpRequest(function(response){
                var obj = JSON.parse(response);
                var status = obj['status'];
                //var msg = obj['msg'];
                if(status=='0'){
                    alert("修改成功！");
                    userinfo.password=md5($('#new_password').val());
                    sessionStorage.setItem('userinfo',JSON.stringify(userinfo));
                    $('#reset-password').modal('hide');
                }
            });
        }
    });



});

/**
 * 登录
 */
function login() {
    $.ajax({
        url : url+'login',
        type : 'POST',
        data : {
            'username' : $('#username').val(),
            'password' : $('#password').val()
        },
        success : function(response) {
            console.log(JSON.stringify(response));
            if(response['status']=='0'){
                var token = response['token'];
                var userinfo = JSON.stringify(response['msg']);
                //var timestamp = Date.parse(new Date());
                //var hash = md5(token + timestamp + sk);
                sessionStorage.setItem('username',$('#username').val());
                sessionStorage.setItem('userpwd',$('#password').val());
                sessionStorage.setItem('userinfo',userinfo);
                sessionStorage.setItem('token',token);

                //window.location.href='default-page.html?backurl='+window.location.href;
                window.location.href='index.html';
            }
            else{
                alert(response['msg']);
            }

        },
        error : function(response) {
            alert('登录失败！');
        }
    });

}


function createHttpR(url,type,dataType,bodyParam){
    this.url = url;
    this.type = type;
    this.dataType = dataType;
    this.bodyParam = bodyParam;
}
createHttpR.prototype.HttpRequest = function(callBack){

    if(sessionStorage.getItem('username')!=null||sessionStorage.getItem('token')!=null){
        var  token = sessionStorage.getItem('token');
        var timestamp = Date.parse(new Date());
        var hash = md5(token+timestamp+sk);
        $.ajax({
            url:this.url,
            type:this.type,
            cache:false,
            timeout:20,
            dataType:this.dataType,
            data :this.bodyParam,
            async:false,
            headers: {
                'token' : token,
                'timesamp' : timestamp,
                'sign' : hash
                //'content-Type': 'application/json'
            },
            success:function(response) {
                var obj = JSON.parse(response);
                var status = obj['status'];
                var msg = obj['msg'];
                if(status=='mismatch'||status=='expire'){
                    console.log(msg);
                    alert('验证信息错误，请重新登录！');
                    //无用户信息，重新登录
                    window.location.href='login.html';
                    //login();
                }
                else if(status=='0'){
                    callBack(response);
                }
                else{
                    alert(msg);
                }
            },
            error:function(response){
                alert('请求失败！');
                return false;
            },
            beforeSend:function(){
                //alert('before');
            },
            complete:function(){
                //alert('complete');
            }

        });
    }
    else{
        alert('访问权限已过期，请重新登录！');
        //无用户信息，重新登录
        window.location.href='login.html';
    }

}

function createJSONHttpR(url,type,dataType,bodyParam){
    this.url = url;
    this.type = type;
    this.dataType = dataType;
    this.bodyParam = bodyParam;
}
createJSONHttpR.prototype.HttpRequest = function(callBack){

    if(sessionStorage.getItem('username')!=null||sessionStorage.getItem('token')!=null){
        var  token = sessionStorage.getItem('token');
        var timestamp = Date.parse(new Date());
        var hash = md5(token+timestamp+sk);
        $.ajax({
            url:this.url,
            type:this.type,
            cache:false,
            timeout:20,
            dataType:this.dataType,
            data :this.bodyParam,
            async:false,
            headers: {
                'token' : token,
                'timesamp' : timestamp,
                'sign' : hash,
                'content-Type': 'application/json'
            },
            success:function(response) {
                var obj = JSON.parse(response);
                var status = obj['status'];
                var msg = obj['msg'];
                if(status=='mismatch'||status=='expire'){
                    console.log(msg);
                    alert('验证信息错误，请重新登录！');
                    //无用户信息，重新登录
                    window.location.href='login.html';
                    //login();
                }
                else if(status=='0'){
                    callBack(response);
                }
                else{
                    alert(msg);
                }
            },
            error:function(response){
                alert('请求失败！');
                return false;
            },
            beforeSend:function(){
                //alert('before');
            },
            complete:function(){
                //alert('complete');
            }

        });
    }
    else{
        alert('访问权限已过期，请重新登录！');
        //无用户信息，重新登录
        window.location.href='login.html';
    }

}



function createHttpM(url,type,dataType,bodyParam){
    this.url = url;
    this.type = type;
    this.dataType = dataType;
    this.bodyParam = bodyParam;
}
createHttpM.prototype.HttpRequest = function(callBack){

    if(sessionStorage.getItem('username')!=null||sessionStorage.getItem('token')!=null){
        var  token = sessionStorage.getItem('token');
        var timestamp = Date.parse(new Date());
        var hash = md5(token+timestamp+sk);
        $.ajax({
            url:this.url,
            type:this.type,
            cache:false,
            timeout:20,
            dataType:this.dataType,
            data :this.bodyParam,
            async:false,
            headers: {
                'token' : token,
                'timesamp' : timestamp,
                'sign' : hash
            },
            success:function(response) {
                var obj = JSON.parse(response);
                var status = obj['status'];
                var msg = obj['msg'];
                if(status=='mismatch'||status=='expire'){
                    console.log(msg);
                    alert('验证信息错误，请重新登录！');
                    //无用户信息，重新登录
                    window.location.href='m_login.html';
                    //login();
                }
                else if(status=='0'){
                    callBack(response);
                }
                else{
                    alert(msg);
                }
            },
            error:function(response){
                alert('请求失败！');
                return false;
            },
            beforeSend:function(){
                //alert('before');
            },
            complete:function(){
                //alert('complete');
            }

        });
    }
    else{
        alert('访问权限已过期，请重新登录！');
        //无用户信息，重新登录
        window.location.href='m_login.html';
    }

}

Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
}

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}



function setMenu(par,sub){
    var userinfo=JSON.parse(sessionStorage.getItem('userinfo'));
    if(userinfo==null){
        window.location.href='login.html';
    }
    var menuStr='';
    if(userinfo.role=='-1'){
        menuStr='<ul class="sidebar-menu">\n' +
            '   <li id="menu1" class="active">\n' +
            '       <a class="" href="index.html">\n' +
            '           <i class="icon-dashboard"></i>\n' +
            '           <span>首页</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu2">\n' +
            '       <a href="er_unit_list.html" class="">\n' +
            '           <i class=" icon-sitemap"></i>\n' +
            '           <span>单位管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu3">\n' +
            '       <a class="" href="er_user_list.html">\n' +
            '           <i class="icon-user"></i>\n' +
            '           <span>用户管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu4">\n' +
            '       <a class="" href="er_checkpoint_list.html">\n' +
            '           <i class=" icon-shield"></i>\n' +
            '           <span>卡点管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu5">\n' +
            '       <a class="" href="er_records_list.html">\n' +
            '           <i class=" icon-paste"></i>\n' +
            '           <span>记录查询</span>\n' +
            '       </a>\n' +
            '   </li> \n' +
            '</ul>';
    }
    else if(userinfo.role=='0'){

        menuStr='<ul class="sidebar-menu">\n' +
            '   <li id="menu1" class="active">\n' +
            '       <a class="" href="index.html">\n' +
            '           <i class="icon-dashboard"></i>\n' +
            '           <span>首页</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu2">\n' +
            '       <a href="er_unit_list.html" class="">\n' +
            '           <i class=" icon-sitemap"></i>\n' +
            '           <span>单位管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu6">\n' +
            '       <a class="" href="er_user_list_admin.html">\n' +
            '           <i class=" icon-gears (alias)"></i>\n' +
            '           <span>系统用户管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu3">\n' +
            '       <a class="" href="er_user_list_unit.html">\n' +
            '           <i class="icon-user"></i>\n' +
            '           <span>单位用户管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu5">\n' +
            '       <a class="" href="er_records_list.html">\n' +
            '           <i class=" icon-paste"></i>\n' +
            '           <span>记录查询</span>\n' +
            '       </a>\n' +
            '   </li> \n' +
            '</ul>';
    }
    else if(userinfo.role=='1'){
        menuStr='<ul class="sidebar-menu">\n' +
            '   <li id="menu1" class="active">\n' +
            '       <a class="" href="index.html">\n' +
            '           <i class="icon-dashboard"></i>\n' +
            '           <span>首页</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu3">\n' +
            '       <a class="" href="er_user_list.html">\n' +
            '           <i class="icon-user"></i>\n' +
            '           <span>用户管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu4">\n' +
            '       <a class="" href="er_checkpoint_list.html">\n' +
            '           <i class=" icon-shield"></i>\n' +
            '           <span>卡点管理</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '   <li id="menu5">\n' +
            '       <a class="" href="er_records_list.html">\n' +
            '           <i class=" icon-paste"></i>\n' +
            '           <span>记录查询</span>\n' +
            '       </a>\n' +
            '   </li> \n' +
            '</ul>';
    }
    else{
        menuStr='<ul class="sidebar-menu">\n' +
            '   <li id="menu1" class="active">\n' +
            '       <a class="" href="index.html">\n' +
            '           <i class="icon-dashboard"></i>\n' +
            '           <span>首页</span>\n' +
            '       </a>\n' +
            '   </li>\n' +
            '</ul>';
    }


    $('#sidebar').html(menuStr);

    $('#sidebar li').removeClass("active");
    $('#menu'+par).addClass("active");
    $('#menu'+par+'-'+sub).addClass("active");



}