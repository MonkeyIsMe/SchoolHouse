/**
 * 
 */
var SchoolName;
var schoolid;
var areaid;



var html = '<table class="table table-bordered table-hover table-condensed" style="width:100%; margin-top:1%;">';
var stu_str;
// 标志是否已经导入了excel或者添加了学区数据，如果flag为0，则该项操作是第一次
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
        // html += '<tr><th>序号</th><th>学区名称</th></tr>';
        html += "<tr><td>"+excel_num+"</td><td>"+$("#areaName").val()+"</td></tr></table>";

        stu_str = '[{"学区名称":"'+$("#areaName").val()+'"}]';    
        //console.log(stu_str);
    }else if(flag == 1) {
        // 去掉后面的</table>
        html = html.substring(0,html.length-8);
        html += "<tr><td>"+excel_num+"</td><td>"+$("#areaName").val()+"</td></tr></table>";
        
        stu_str = stu_str.substring(0, stu_str.length-1);
        stu_str += ',{"学区名称":"'+$("#areaName").val()+'"}]';    
        //console.log(stu_str);
    };
    flag = 1;
    document.getElementById('result').innerHTML = html;
    //$("#add-edit-modal").css("display","none");
    $('#add-modal').modal('hide');
});
function selectFileXuequ() {
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
		//console.log(stu_str);
		//AddAreaFromExcel
		
		if(stu_str == null || stu_str == ""){
			alert("数据非法！");
		}
		else{
			stu_str = stu_str.replace(/[\r\n]/g,"");
			$.post(
					"AddAreaFromExcel.action",
					{
						area_info:stu_str
					}, 
					function(data) {
						var data = JSON.parse(data);
						if(data.fail == 0){
							alert("全部导入成功!");
							window.close();
						}
						else{
							alert("导入成功："+ data.success +"项 ，"+ " 导入失败：" + data.fail +" 项。");
							window.close();
						}
						
				}
			);
		}

})

function csv2table(csv)
{
    
	//var html = '<table class="table table-bordered table-hover table-condensed" style="width:100%; margin-top:1%;">';
	var rows = csv.split('\n');
    rows.pop(); // 最后一行没用
    if(flag == 0){
        excel_num -= 1;
        rows.forEach(function(row, idx) {
			//console.log("test");
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