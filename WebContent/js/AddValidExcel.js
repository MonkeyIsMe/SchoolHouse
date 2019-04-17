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
				schoolid = data.schoolid,
				$.post(
						"QueryBySchoolId.action",
						{
							school_id:data.schoolid,
						}, 
						function(datas) {
							var datas = JSON.parse(datas);
							//console.log(datas);
							SchoolName = datas.schoolName;
							areaid = datas.areaId;S
					}
				);
		}
	);
	
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
        //传到后端
        var stu_str = JSON.stringify(stu_json);  
       // console.log(stu_str);
/*        $.ajax({  
            url: "AddStudentFromExcel.action",  
            contentType: 'application/json',  
            data: stu_str,  
            type: "post",  
            datatype: "json",  
            success: function(data){  
                //console.log(data);  
            }  
        }); */         
        //console.log(stu_json);
        
        $("#bt_add").click(function(){
        	$.post(
        			"AddStudentFromExcelValid.action",
        			{
        				student_info:stu_str,
        				school_name:SchoolName,
        				area_id:areaid,
        			}, 
        			function(data) {
        				var data = JSON.parse(data);
        				//alert("总共 :" + data.all +"项" + "  ,因学校不匹配导入失败:" + (data.all - data.school) + "项" + "  ,因楼盘不匹配，导入失败 ：" + data.bcnt+"项")
        				if(data.school != 0 && data.building != 0  && data.repeat != 0){
        					alert( "因学校不匹配导入失败:" + data.sinfo  + "  ,因楼盘不匹配，导入失败 ：" + data.binfo+ "  ,因重复信息，导入失败 ：" + data.rinfo);
        					window.close();
        				}
        				else if(data.school != 0 && data.repeat != 0){
        					alert( "因学校不匹配导入失败:" + data.sinfo  +"  ,因重复信息，导入失败 ：" + data.rinfo);
        					window.close();
        				}
        				else if(data.repeat != 0 && data.building != 0){
        					alert("因楼盘不匹配，导入失败 ：" + data.binfo+ "  ,因重复信息，导入失败 ：" + data.rinfo);
        					window.close();
        				}
        				else if(data.school != 0 && data.building != 0){
        					alert( "因学校不匹配导入失败:" + data.sinfo  + "  ,因楼盘不匹配，导入失败 ：" + data.binfo);
        					window.close();
        				}
        				else if(data.school != 0){
        					alert("因学校不匹配导入失败:" + data.sinfo);
        					window.close();
        				}
        				else if(data.building != 0){
        					alert("因楼盘不匹配，导入失败 ：" + data.binfo);
        					window.close();
        				}
        				else if(data.repeat != 0){
        					alert("因重复信息，导入失败 ：" + data.rinfo);
        					window.close();
        				}
        				else{
        					alert("导入成功!");
        					window.close();
        				}
        				
        	});
        })
        
    	
		var csv = XLSX.utils.sheet_to_csv(worksheet);
		document.getElementById('result').innerHTML = csv2table(csv);
	}

	function csv2table(csv)
	{
		var html = '<table class="table table-bordered table-hover table-condensed" style="width:100%; margin-top:1%;">';
		var rows = csv.split('\n');
        rows.pop(); // 最后一行没用
		rows.forEach(function(row, idx) {
            var columns = row.split(',');
            //行号
            if(idx == 0) {
                columns.unshift("#")
            }else {
                columns.unshift(idx); 
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
		loadRemoteFile('./sample/test.xlsx');
	});		