/**
 * 
 */
var SchoolName;
var schoolid;
var areaid;
$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"GetSchoolUserInfo.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data)
				schoolid = data.schoolid;
				$.post(
						"QueryBySchoolId.action",
						{
							school_id:data.schoolid,
						}, 
						function(datas) {
							var datas = JSON.parse(datas);
							//console.log(datas);
							SchoolName = datas.schoolName;
							areaid = datas.areaId;
					}
				);
		}
	);
	
});


var html = '<table class="table table-bordered table-hover table-condensed" style="width:100%; margin-top:1%;">';
var stu_str;
// 标志是否已经导入了excel或者添加了学生数据，如果flag为0，则该项操作是第一次
var flag = 0;
// excel的记录条数
var excel_num = 0;
// 添加学生信息
$(document).ready(function(){
    $("#bt_add").click(function(){
        $("#add-modal").modal("show");
    });
});



$("#mySave").click(function() {
    excel_num += 1;
    if(flag == 0){
        html += '<tr><th>#</th><th>学生姓名</th><th>学生身份证号码</th><th>父亲姓名</th><th>母亲姓名</th><th>业主姓名</th><th>业主身份证</th><th>与业主关系</th><th>房产证号</th><th>房屋预告登记号</th><th>所属楼盘</th><th>楼号</th><th>房号</th><th>所属学校</th><th>学生联系电话</th><th>使用的日期</th><th>毕业小学</th></tr>';
        html += "<tr><td>"+excel_num+"</td><td>"+$("#studentName").val()+"</td><td>"+$("#identityId").val()+"</td><td>"+$("#farther").val()+"</td><td>"+$("#mother").val()+"</td><td>"+$("#houseOwner").val()+"</td><td>"+$("#houseOwnerId").val()+"</td><td>"+$("#relation").val()+"</td><td>"+$("#houseCard").val()+"</td><td>"+$("#housepreCard").val()+"</td><td>"+$("#studentbuildId").val()+"</td><td>"+
            $("#studentbuildNum").val()+"</td><td>"+$("#studenthomeId").val()+"</td><td>"+$("#studentSchool").val()+"</td><td>"+$("#studentTel").val()+"</td><td>"+$("#useTime").val()+"</td><td>"+$("#privateSchool").val()+"</td></tr></table>";

        stu_str = '[{"学生姓名":"'+$("#studentName").val()+'","学生身份证号码":"'+$("#identityId").val()+'","父亲姓名":"'+$("#farther").val()+'","母亲姓名":"'+$("#mother").val()+'","业主姓名":"'+$("#houseOwner").val()+'","业主身份证":"'+$("#houseOwnerId").val()+'","与业主关系":"'+$("#relation").val()+
        '","房产证号":"'+$("#houseCard").val()+'","房屋预告登记号":"'+$("#housepreCard").val()+'","所属楼盘":"'+$("#studentbuildId").val()+'","楼号":"'+$("#studentbuildNum").val()+'","房号":"'+$("#studenthomeId").val()+'","所属学校":"'+$("#studentSchool").val()+'","学生联系电话":"'+$("#studentTel").val()+'","使用的日期":"'+$("#useTime").val()+'","毕业小学":"'+$("#privateSchool").val()+'"}]';    
        //console.log(stu_str);
    }else if(flag == 1) {
        // 去掉后面的</table>
        html = html.substring(0,html.length-8);
        html += "<tr><td>"+excel_num+"</td><td>"+$("#studentName").val()+"</td><td>"+$("#identityId").val()+"</td><td>"+$("#farther").val()+"</td><td>"+$("#mother").val()+"</td><td>"+$("#houseOwner").val()+"</td><td>"+$("#houseOwnerId").val()+"</td><td>"+$("#relation").val()+"</td><td>"+$("#houseCard").val()+"</td><td>"+$("#housepreCard").val()+"</td><td>"+$("#studentbuildId").val()+"</td><td>"+
            $("#studentbuildNum").val()+"</td><td>"+$("#studenthomeId").val()+"</td><td>"+$("#studentSchool").val()+"</td><td>"+$("#studentTel").val()+"</td><td>"+$("#useTime").val()+"</td><td>"+$("#privateSchool").val()+"</td></tr></table>";
        
        stu_str = stu_str.substring(0, stu_str.length-1);
        stu_str += ',{"学生姓名":"'+$("#studentName").val()+'","学生身份证号码":"'+$("#identityId").val()+'","父亲姓名":"'+$("#farther").val()+'","母亲姓名":"'+$("#mother").val()+'","业主姓名":"'+$("#houseOwner").val()+'","业主身份证":"'+$("#houseOwnerId").val()+'","与业主关系":"'+$("#relation").val()+
        '","房产证号":"'+$("#houseCard").val()+'","房屋预告登记号":"'+$("#housepreCard").val()+'","所属楼盘":"'+$("#studentbuildId").val()+'","楼号":"'+$("#studentbuildNum").val()+'","房号":"'+$("#studenthomeId").val()+'","所属学校":"'+$("#studentSchool").val()+'","学生联系电话":"'+$("#studentTel").val()+'","使用的日期":"'+$("#useTime").val()+'","毕业小学":"'+$("#privateSchool").val()+'"}]';    
        //console.log(stu_str);
    };
    flag = 1;
    document.getElementById('result').innerHTML = html;
    //$("#add-edit-modal").css("display","none");
    $('#add-modal').modal('hide');
});
function selectFile() {
    $("#file").click();
}
// 读取本地excel文件
function readWorkbookFromLocalFile(file, callback) {
	var reader = new FileReader();
	reader.onload = function(e) {
		var data = e.target.result;
		var workbook = XLSX.read(data, {type: 'binary'});
		if(callback) callback(workbook);
	};
	reader.readAsBinaryString(file);
}

function readWorkbook(workbook) {
	var sheetNames = workbook.SheetNames; // 工作表名称的集合
    var worksheet = workbook.Sheets[sheetNames[0]]; // 暂时只取第一张sheet
    // 生成json数据
    var stu_json = XLSX.utils.sheet_to_json(worksheet);
    // 生成json字符串
    var stu_str_temp = JSON.stringify(stu_json);
    if(flag == 0){
        stu_str = stu_str_temp;
        //console.log(stu_str);
    } else if(flag == 1){
        stu_str = stu_str.substring(0, stu_str.length - 1);
        stu_str += ','
        stu_str += stu_str_temp.substring(1, stu_str_temp.length);;
        //console.log(stu_str);
    } 
    

	var csv = XLSX.utils.sheet_to_csv(worksheet);
	document.getElementById('result').innerHTML = csv2table(csv);
}

$("#bt_in").click(function(){
	console.log(stu_str);
	$.post(
			"DeleteAllTempStudent.action",
			{
				
			}, 
			function(data) {
		}
	);
	
	
	$.post(
			"AddStudentFromExcel.action",
			{
				student_info:stu_str,
				school_name:SchoolName,
				area_id:areaid,
			}, 
			function(data) {
   				var data = JSON.parse(data);
				//alert("总共 :" + data.all +"项" + "  ,因学校不匹配导入失败:" + (data.all - data.school) + "项" + "  ,因楼盘不匹配，导入失败 ：" + data.bcnt+"项")
				if(data.school != 0 && data.building != 0  && data.repeat != 0 && data.house!= 0){
					alert( "因学校不匹配导入失败:" + data.school  + "  ,因楼盘不匹配，导入失败 ：" + data.building+ "  ,因重复信息，导入失败 ：" + data.repeat + "  ,因房屋不匹配，导入失败 ：" + data.house);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.building != 0  && data.repeat != 0 && data.house!= 0){
					alert( "因楼盘不匹配，导入失败 ：" + data.building+ "  ,因重复信息，导入失败 ：" + data.repeat + "  ,因房屋不匹配，导入失败 ：" + data.house);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.school != 0  && data.repeat != 0 && data.house!= 0){
					alert( "因学校不匹配导入失败:" + data.school  + "  ,因重复信息，导入失败 ：" + data.repeat + "  ,因房屋不匹配，导入失败 ：" + data.house);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.school != 0 && data.building != 0&& data.house!= 0){
					alert( "因学校不匹配导入失败:" + data.school  + "  ,因楼盘不匹配，导入失败 ：" + data.building+ "  ,因房屋不匹配，导入失败 ：" + data.house);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.school != 0 && data.building != 0  && data.repeat != 0){
					alert( "因学校不匹配导入失败:" + data.school  + "  ,因楼盘不匹配，导入失败 ：" + data.building+ "  ,因重复信息，导入失败 ：" + data.repeat );
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.school != 0 && data.repeat != 0){
					alert( "因学校不匹配导入失败:" + data.school  +"  ,因重复信息，导入失败 ：" + data.repeat);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.repeat != 0 && data.building != 0){
					alert("因楼盘不匹配，导入失败 ：" + data.building+ "  ,因重复信息，导入失败 ：" + data.repeat);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.school != 0 && data.building != 0){
					alert( "因学校不匹配导入失败:" + data.school  + "  ,因楼盘不匹配，导入失败 ：" + data.building);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.school != 0 && data.house != 0){
					alert( "因学校不匹配导入失败:" + data.school  + "  ,因房屋不匹配，导入失败 ：" + data.house);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.house != 0 && data.building != 0){
					alert( "因房屋不匹配导入失败:" + data.school  + "  ,因楼盘不匹配，导入失败 ：" + data.building);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.house != 0 && data.repeat != 0){
					alert( "因房屋不匹配导入失败:" + data.house  + "  ,因重复，导入失败 ：" + data.repeat);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.house != 0){
					alert("因房屋不匹配导入失败:" + data.house);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.school != 0){
					//alert(data.school);
					alert("因学校不匹配导入失败:" + data.school);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.building != 0){
					alert("因楼盘不匹配，导入失败 ：" + data.building);
					window.location.replace("hw_table_student_all.html");
				}
				else if(data.repeat != 0){
					alert("因重复信息，导入失败 ：" + data.repeat);
					window.location.replace("hw_table_student_all.html");
				}
				else{
					alert("导入成功!");
					window.location.replace("hw_table_student_all.html");
				}
	});
})

function csv2table(csv)
{
    
	//var html = '<table class="table table-bordered table-hover table-condensed" style="width:100%; margin-top:1%;">';
	var rows = csv.split('\n');
    rows.pop(); // 最后一行没用
    if(flag == 0){
        excel_num -= 1;
        rows.forEach(function(row, idx) {
            var columns = row.split(',');
            //行号
            if(idx == 0) {
                columns.unshift("#")
            }else {
                columns.unshift(idx); 
            }
            excel_num += 1;
            html += '<tr>';
                if(idx == 0){
                    columns.forEach(function(column) {
                        html += '<th>'+column+'</th>';
                    });
                    //html += '<th>是否设置为合格</th>';
                }else{
                    columns.forEach(function(column) {
                        html += '<td>'+column+'</td>';
                    });
                    //html += '<td><button type="button" class="btn btn-primary btn-xs" id="check_yes">合格</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" id="check_no">不合格</button></td>';
                }
            html += '</tr>';
	    });
    } else if(flag == 1){
        html = html.substring(0,html.length-8);
        rows.forEach(function(row, idx) {
            var columns = row.split(',');
            //跳过第一行的表头
            if(idx == 0) {
                return true;
            }else {
                excel_num += 1;
                columns.unshift(excel_num); 
            }
            
            html += '<tr>';
                if(idx == 0){
                    columns.forEach(function(column) {
                        html += '<th>'+column+'</th>';
                    });
                    //html += '<th>是否设置为合格</th>';
                }else{
                    columns.forEach(function(column) {
                        html += '<td>'+column+'</td>';
                    });
                    //html += '<td><button type="button" class="btn btn-primary btn-xs" id="check_yes">合格</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" id="check_no">不合格</button></td>';
                }
            html += '</tr>';
	    });
    };
    flag = 1;
	html += '</table>';
	return html;
}

$(function() {
	document.getElementById('file').addEventListener('change', function(e) {
		var files = e.target.files;
		if(files.length == 0) return;
		var f = files[0];
		if(!/\.xlsx$/g.test(f.name)) {
			alert('仅支持读取xlsx格式！');
			return;
		}
		readWorkbookFromLocalFile(f, function(workbook) {
			readWorkbook(workbook);
		});
	});
});	