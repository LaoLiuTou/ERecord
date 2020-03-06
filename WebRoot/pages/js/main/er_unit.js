


//选取的Unit
var unitList;
var unitIndex;
var currentUnit;
var allUnit;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////

/**
 * 查询单条资讯
 */
function selectUnit(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectUnit','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentUnit=data;
            //$('#title').val(data['title']);
            //$('#type').val(data['type']);
            //$('#content').val(data['content']);
            for (var item in data) {
                $('#'+item).val(data[item]);

            }
        }
    });
}


/**
 * 添加
 */
function addUnit(bodyParam){
    var httpR = new createHttpR(url+'addUnit','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            window.location.reload();
            //window.history.go(-1);
        }
    });
}
/**
 * 修改
 * @param id
 */
function updateUnit(bodyParam){

    var httpR = new createHttpR(url+'updateUnit','post','text',bodyParam,'callBack');
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
function deleteUnit(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteUnit','post','text',bodyParam,'callBack');
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
 * 所有
 */
function allUnit(bodyParam){
    var httpR = new createHttpR(url+'allUnit','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            allUnit=obj['msg'];
            var html='<option value="">请选择单位</option>';
            for (var item in allUnit) {
                html+='<option value="'+allUnit[item].id+'">'+allUnit[item].name+'</option>';

            }
            $('#select_update_unit_id').append(html);
            $('#select_unit_id').append(html);
        }
    });
}

/**
 * 查询
 * @param currentPage
 * @param pageSize
 */
function  queryUnit (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'searchText':'%'+searchText+'%'};
    }

    var httpR = new createHttpR(url+'listUnit','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            unitList=msg['data'];
            var html='';
            for(var o in data){

                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td>'+data[o].name+'</td>\n' +
                    '<td>'+data[o].phone+'</td>\n' +
                    '<td>'+data[o].comment+'</td>\n' +
                    '<td>'+data[o].count+'</td>\n' +
                    '<td>'+data[o].usecount+'</td>\n' +
                    '<td>'+data[o].c_dt+'</td>\n' ;

                html+='<td>\n' +
                    '  <button  index='+o+' class="btn btn-primary btn-xs updateUnit" data-toggle="modal" data-target="#update-box" title="修改"><i class="icon-pencil"></i></button>\n' +
                    '  <button  index='+o+' class="btn btn-danger btn-xs deleteUnit" data-toggle="modal" data-target="#delete-box" title="删除"><i class="icon-trash "></i></button>\n' +
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
