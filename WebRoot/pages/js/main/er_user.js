$(document).ready(function(){


    //选取的User
    var userList;
    var userIndex;
    var currentUser;
});


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////用户管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加用户
 */
function addUser(bodyParam){
    var httpR = new createHttpR(url+'addUser','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}

/**
 * 修改用户
 * @param id
 */
function updateUser(bodyParam){

    var httpR = new createHttpR(url+'updateUser','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("修改成功！");
            window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}

/**
 * 删除用户
 * @param id
 */
function deleteUser(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteUser','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("删除成功！");
            window.location.reload();
        }
    });
}
/**
 * 查询用户
 * @param username
 * @param currentPage
 * @param pageSize
 */
function  queryUser (searchText,currentPage,pageSize) {

    var bodyParam={};
    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        bodyParam['page']=currentPage;
        bodyParam['size']=pageSize;
    }
    else{
        bodyParam['page']=currentPage;
        bodyParam['size']=pageSize;
        bodyParam['searchText']='%'+searchText+'%';
    }
    var userinfo = JSON.parse(sessionStorage.getItem('userinfo'));
    if(userinfo.role=='-1'){

    }
    else if(userinfo.role=='0'){
        bodyParam['role']='1';
    }
    else if(userinfo.role=='1'){
        //bodyParam['role']='2';
        bodyParam['unit_id']=userinfo.unit_id;
    }
    else{

    }

    var httpR = new createHttpR(url+'listUser','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            userList=msg['data'];
            var html='';
            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td>'+data[o].username+'</td>\n' +
                    '<td>'+data[o].nickname+'</td>\n' +
                    '<td>'+data[o].phone+'</td>\n' +
                    '<td>'+data[o].unit_name+'</td>\n' ;

                if(data[o].role=='0'){
                    html+='<td>系统管理员</td>\n';
                }
                else if(data[o].role=='1'){
                    html+='<td>单位管理员</td>\n';
                }
                else{
                    html+='<td>检查人员</td>\n';
                }
                html+='<td>'+data[o].c_dt+'</td>\n';
                if(data[o].id==userinfo.id){
                    html+='<td>\n' +
                        '  <button  index='+o+' class="btn btn-primary btn-xs updateUser" data-toggle="modal" data-target="#update-box" title="修改"><i class="icon-pencil"></i></button>\n' +
                        '</td>';
                }
                else{
                    html+='<td>\n' +
                        '  <button  index='+o+' class="btn btn-primary btn-xs updateUser" data-toggle="modal" data-target="#update-box" title="修改"><i class="icon-pencil"></i></button>\n' +
                        '  <button  index='+o+' class="btn btn-success btn-xs passwordUser" data-toggle="modal" data-target="#password-box" title="重置密码"><i class=" icon-unlock-alt"></i></button>\n' +
                        '  <button  index='+o+' class="btn btn-danger btn-xs deleteUser" data-toggle="modal" data-target="#delete-box" title="删除"><i class="icon-trash "></i></button>\n' +
                        '</td>';
                }

                html+='</tr>';
            }
            $('#contentTbody').html(html);
            var num=msg['num'];
            if(num>0) {
                var pageHtml = '';
                var totalPage = 0;
                if (num % pageSize == 0) {
                    totalPage = num / pageSize;
                }
                else {
                    totalPage = Math.ceil(num / pageSize);
                }
                if (currentPage == 1) {
                    pageHtml += '<li class="prev disabled" ><a href="#" >上一页</a></li>';
                }
                else {
                    pageHtml += '<li class="prev prevBtn" id="default-datatable_previous"><a href="#">上一页</a></li>';
                }
                if (totalPage <= showPage) {
                    for (var i = 1; i < Number(totalPage) + 1; i++) {
                        if (currentPage == i) {
                            pageHtml += '<li class="active">' +
                                '<a href="#" >' + i + '</a>' +
                                '</li>';
                        }
                        else {
                            pageHtml += '<li>' +
                                '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                '</li>';
                        }
                    }
                }
                else {
                    if (currentPage <= (showPage - 1) / 2) {
                        for (var i = 1; i <= showPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active">' +
                                    '<a href="#" >' + i + '</a>' +
                                    '</li>';
                            }
                            else {
                                pageHtml += '<li>' +
                                    '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                    '</li>';
                            }
                        }
                    }
                    else if (totalPage - currentPage < (showPage - 1) / 2) {
                        for (var i = Number(totalPage) - showPage; i <= totalPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active">' +
                                    '<a href="#" >' + i + '</a>' +
                                    '</li>';
                            }
                            else {
                                pageHtml += '<li>' +
                                    '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                    '</li>';
                            }
                        }
                    }
                    else {
                        for (var i = Number(currentPage) - (showPage - 1) / 2; i <= Number(currentPage) + (showPage - 1) / 2; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active">' +
                                    '<a href="#" >' + i + '</a>' +
                                    '</li>';
                            }
                            else {
                                pageHtml += '<li>' +
                                    '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                    '</li>';
                            }
                        }
                    }
                }
                if (currentPage == totalPage) {
                    pageHtml += ' <li class="next disabled" ><a href="#" >下一页</a>\n' +
                        '   </li>';
                }
                else {
                    pageHtml += ' <li class="next nextBtn" id="default-datatable_next"><a href="#" >下一页</a>\n' +
                        '   </li>';
                }

                $('.pagination').html('<ul>'+pageHtml+'</ul>');
            }


        }
    });
}
function  queryAdminUser (searchText,currentPage,pageSize) {

    var bodyParam={};
    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        bodyParam['page']=currentPage;
        bodyParam['size']=pageSize;
    }
    else{
        bodyParam['page']=currentPage;
        bodyParam['size']=pageSize;
        bodyParam['searchText']='%'+searchText+'%';
    }
    var userinfo = JSON.parse(sessionStorage.getItem('userinfo'));
    if(userinfo.role=='-1'){

    }
    else if(userinfo.role=='0'){
        bodyParam['role']='0';
    }
    else if(userinfo.role=='1'){
        //bodyParam['role']='2';
        bodyParam['unit_id']=userinfo.unit_id;
    }
    else{

    }

    var httpR = new createHttpR(url+'listUser','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            userList=msg['data'];
            var html='';
            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td>'+data[o].username+'</td>\n' +
                    '<td>'+data[o].nickname+'</td>\n' +
                    '<td>'+data[o].phone+'</td>\n' ;
                    /*'<td>'+data[o].unit_name+'</td>\n' ;*/

                if(data[o].role=='0'){
                    html+='<td>系统管理员</td>\n';
                }
                else if(data[o].role=='1'){
                    html+='<td>单位管理员</td>\n';
                }
                else{
                    html+='<td>检查人员</td>\n';
                }
                html+='<td>'+data[o].c_dt+'</td>\n';

                if(data[o].id=='1'){
                    html+='<td>\n' +
                        '  <button  index='+o+' class="btn btn-primary btn-xs updateUser" data-toggle="modal" data-target="#update-box" title="修改"><i class="icon-pencil"></i></button>\n' +
                        '  <button  index='+o+' class="btn btn-success btn-xs passwordUser" data-toggle="modal" data-target="#password-box" title="重置密码"><i class=" icon-unlock-alt"></i></button>\n' +
                        '</td>';
                }
                else{
                    html+='<td>\n' +
                        '  <button  index='+o+' class="btn btn-primary btn-xs updateUser" data-toggle="modal" data-target="#update-box" title="修改"><i class="icon-pencil"></i></button>\n' +
                        '  <button  index='+o+' class="btn btn-success btn-xs passwordUser" data-toggle="modal" data-target="#password-box" title="重置密码"><i class=" icon-unlock-alt"></i></button>\n' +
                        '  <button  index='+o+' class="btn btn-danger btn-xs deleteUser" data-toggle="modal" data-target="#delete-box" title="删除"><i class="icon-trash "></i></button>\n' +
                        '</td>';
                }

                html+='</tr>';
            }
            $('#contentTbody').html(html);
            var num=msg['num'];
            if(num>0) {
                var pageHtml = '';
                var totalPage = 0;
                if (num % pageSize == 0) {
                    totalPage = num / pageSize;
                }
                else {
                    totalPage = Math.ceil(num / pageSize);
                }
                if (currentPage == 1) {
                    pageHtml += '<li class="prev disabled" ><a href="#" >上一页</a></li>';
                }
                else {
                    pageHtml += '<li class="prev prevBtn" id="default-datatable_previous"><a href="#">上一页</a></li>';
                }
                if (totalPage <= showPage) {
                    for (var i = 1; i < Number(totalPage) + 1; i++) {
                        if (currentPage == i) {
                            pageHtml += '<li class="active">' +
                                '<a href="#" >' + i + '</a>' +
                                '</li>';
                        }
                        else {
                            pageHtml += '<li>' +
                                '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                '</li>';
                        }
                    }
                }
                else {
                    if (currentPage <= (showPage - 1) / 2) {
                        for (var i = 1; i <= showPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active">' +
                                    '<a href="#" >' + i + '</a>' +
                                    '</li>';
                            }
                            else {
                                pageHtml += '<li>' +
                                    '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                    '</li>';
                            }
                        }
                    }
                    else if (totalPage - currentPage < (showPage - 1) / 2) {
                        for (var i = Number(totalPage) - showPage; i <= totalPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active">' +
                                    '<a href="#" >' + i + '</a>' +
                                    '</li>';
                            }
                            else {
                                pageHtml += '<li>' +
                                    '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                    '</li>';
                            }
                        }
                    }
                    else {
                        for (var i = Number(currentPage) - (showPage - 1) / 2; i <= Number(currentPage) + (showPage - 1) / 2; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active">' +
                                    '<a href="#" >' + i + '</a>' +
                                    '</li>';
                            }
                            else {
                                pageHtml += '<li>' +
                                    '<a href="#" class="pageBtn" index="' + i + '" >' + i + '</a>' +
                                    '</li>';
                            }
                        }
                    }
                }
                if (currentPage == totalPage) {
                    pageHtml += ' <li class="next disabled" ><a href="#" >下一页</a>\n' +
                        '   </li>';
                }
                else {
                    pageHtml += ' <li class="next nextBtn" id="default-datatable_next"><a href="#" >下一页</a>\n' +
                        '   </li>';
                }

                $('.pagination').html('<ul>'+pageHtml+'</ul>');
            }


        }
    });
}
