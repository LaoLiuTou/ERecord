


//选取的Records
var recordsList;
var recordsIndex;
var currentRecords;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////

/**
 * 查询单条资讯
 */
function selectRecords(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectRecords','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentRecords=data;
            for (var item in data) {
                if(item=='img'){
                    $('#img').prop('src',data[item]);
                }
                else{
                    $('#'+item).text(data[item]);
                }
            }
        }
    });
}


/**
 * 添加
 */
function addRecords(bodyParam){
    var httpR = new createHttpR(url+'addRecords','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            //window.location.reload();
            //window.history.go(-1);
        }
    });
}
function addRecordsM(bodyParam){
    var httpM = new createHttpM(url+'addRecords','post','text',bodyParam,'callBack');
    httpM.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            //window.location.reload();
            window.history.go(-1);
        }
    });
}
/**
 * 修改
 * @param id
 */
function updateRecords(bodyParam){

    var httpR = new createHttpR(url+'updateRecords','post','text',bodyParam,'callBack');
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
 * 删除
 * @param id
 */
function deleteRecords(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteRecords','post','text',bodyParam,'callBack');
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
 * 查询
 * @param currentPage
 * @param pageSize
 */
function  queryRecords (bodyParam,currentPage,pageSize) {
    //分页显示的页码数  必须为奇数
    var showPage=7;
    bodyParam.page=currentPage;
    bodyParam.size=pageSize;
    var httpR = new createHttpR(url+'listRecords','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            recordsList=msg['data'];
            var html='';
            for(var o in data){

                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td>'+data[o].name+'</td>\n' +
                    '<td>'+data[o].idnumber+'</td>\n' +
                    '<td>'+data[o].phone+'</td>\n' ;
                if(data[o].inorout=='进入'){
                    html+='<td><span class="label label-danger label-mini">'+data[o].inorout+'</span></td>\n';

                }
                else {
                    html+='<td><span class="label label-success label-mini">'+data[o].inorout+'</span></td>\n';
                }
                html+='<td>'+data[o].unit_name+'</td>\n' +
                    '<td>'+data[o].cp_name+'</td>\n' +
                    '<td>'+data[o].c_name+'</td>\n' +
                    '<td>'+data[o].dt+'</td>\n' ;


                html+='<td>\n' +
                    '  <button  index='+o+' class="btn btn-primary btn-xs detailRecords "  title="详情"><i class=" icon-ellipsis-horizontal"></i></button>\n' +
                    '</td>';
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
                /* pageHtml+='<li><input type="text" id="jumpPageText" class="paging-inpbox form-control"></li>\n' +
                     '<li><button type="button" id="jumpBtn" class="paging-btnbox btn btn-primary">跳转</button></li>\n' +
                     '<li><span class="number-of-pages">共'+totalPage+'页</span></li>';*/

                $('.pagination').html('<ul>'+pageHtml+'</ul>');
            }


        }
    });
}
