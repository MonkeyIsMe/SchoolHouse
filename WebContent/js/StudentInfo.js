/**
 * 
 */

$(document).ready(function() {
    var obj_i=[];
    $.jgrid.defaults.styleUI = "Bootstrap";
    var mydata = [{
        id: "1",
        studentName: "李四",
        identityId: "43xxxxxxxxxxxxxxxx",
        father: "李xx",
        mother: "王xx",
        houseOwner: "李xx",
        houseOwnerId: "43xxxxxxxxxxxxxxxx",
        relation: "父子",
        houseCard: "123456xxx",
        housepreCard: "123456xxx",
        studentbuildId: "xx园",
        studentbuildNum: "x栋",
        studenthomeId: "xxx",
        isvalid: "是",
        studentSchool: "xxx学校",
        studentTel: "12345678912",
        useTime: "2019-01-01",
        privateSchool: "xxx学校",
        createTime: "2019-02-02"
    },
    {
        id: "2",
        studentName: "李四",
        identityId: "43xxxxxxxxxxxxxxxx",
        father: "李xx",
        mother: "王xx",
        houseOwner: "李xx",
        houseOwnerId: "43xxxxxxxxxxxxxxxx",
        relation: "父子",
        houseCard: "123456xxx",
        housepreCard: "123456xxx",
        studentbuildId: "xx园",
        studentbuildNum: "x栋",
        studenthomeId: "xxx",
        isvalid: "是",
        studentSchool: "xxx学校",
        studentTel: "12345678912",
        useTime: "2019-01-01",
        privateSchool: "xxx学校",
        createTime: "2019-02-02"
    },
    {
        id: "3",
        studentName: "李四",
        identityId: "43xxxxxxxxxxxxxxxx",
        father: "李xx",
        mother: "王xx",
        houseOwner: "李xx",
        houseOwnerId: "43xxxxxxxxxxxxxxxx",
        relation: "父子",
        houseCard: "123456xxx",
        housepreCard: "123456xxx",
        studentbuildId: "xx园",
        studentbuildNum: "x栋",
        studenthomeId: "xxx",
        isvalid: "是",
        studentSchool: "xxx学校",
        studentTel: "12345678912",
        useTime: "2019-01-01",
        privateSchool: "xxx学校",
        createTime: "2019-02-02"
    }];
    $("#table_list_2").jqGrid({
        data: mydata,
        datatype: "local",
        height: 450,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 20,
        rowList: [10, 20, 30],
        multiselect: true,
        celledit: true,
        onSelectRow: function(id, stats) {
            if (stats) {
                obj_i.push(id);
            } else {
                obj_i.shift();
            }
        },
        editurl:"",
        colNames: ["序号", "学生姓名", "学生身份证号", "父亲姓名", "母亲姓名", "业主姓名", "业主身份证号", "与业主关系", "房产证号", "房屋预告登记号", "所属楼盘", "楼号", "房号", "是否有效", "所属学校", "学生联系电话", "使用的日期", "毕业小学", "创建日期"],
        colModel: [{
            name: "id",
            index: "id",
            editable: true,
            width: 15,
            sorttype: "int",
            search: true
        },
        {
            name: "studentName",
            index: "studentName",
            editable: true,
            width: 25
        },
        {
            name: "identityId",
            index: "identityId",
            editable: true,
            width: 50
        },
        {
            name: "father",
            index: "father",
            editable: true,
            width: 25,
            align: "left"
        },
        {
            name: "mother",
            index: "mother",
            editable: true,
            width: 25,
            align: "left"
        },
        {
            name: "houseOwner",
            index: "houseOwner",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "houseOwnerId",
            index: "houseOwnerId",
            editable: true,
            width: 50,
            sortable: false
        },
        {
            name: "relation",
            index: "relation",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "houseCard",
            index: "houseCard",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "housepreCard",
            index: "housepreCard",
            editable: true,
            width: 40,
            align: "left"
        },
        {
            name: "studentbuildId",
            index: "studentbuildId",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "studentbuildNum",
            index: "studentbuildNum",
            editable: true,
            width: 20,
            align: "left"
        },
        {
            name: "studenthomeId",
            index: "studenthomeId",
            editable: true,
            width: 20,
            align: "left"
        },
        {
            name: "isvalid",
            index: "isvalid",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "studentSchool",
            index: "studentSchool",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "studentTel",
            index: "studentTel",
            editable: true,
            width: 40,
            align: "left"
        },
        {
            name: "useTime",
            index: "useTime",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "privateSchool",
            index: "privateSchool",
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "createTime",
            index: "createTime",
            editable: true,
            width: 30,
            align: "left"
        }],
        pager: "#pager_list_2",
        viewrecords: true,
        add: true,
        edit: true,
        addtext: "Add",
        edittext: "Edit",
        hidegrid: false
    });
    $("#table_list_2").setSelection(0, true);
    $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
        edit: true,
        add: true,
        del: true,
        search: false
    },
    {
        height: 300,
        reloadAfterSubmit: true
    });
    $(window).bind("resize",
    function() {
        var width = $(".jqGrid_wrapper").width();
        $("#table_list_2").setGridWidth(width)
    });

    var obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
    var obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");//获取到删除时多选的id，并放入到数组obj_del中
    $("#bt_add").click(function() {
        $("#mySave").css("display","inline");
        $("#myEdit").css("display","none");
        $("#bt_add").attr("data-target","#add-edit");
    });
    $("#bt_edit").click(function() {
        $("#mySave").css("display","none");
        $("#myEdit").css("display","inline");
        if(obj_i.length==1){
            // alert(obj_edit[obj_i[obj_i.length-1]-1].schoolName);
            $("#bt_edit").attr("data-target","#add-edit");
        }else if(obj_i.length < 1){
            $("#bt_edit").attr("data-target","");
            alert("请至少选择1项！");
        }else{
            $("#bt_edit").attr("data-target","");
            alert("请只选择1项！");
        }
    });
    $("#bt_del").click(function() {
        if(obj_del.length<1){
            alert("请至少选择1项！");
            $("#bt_del").attr("data-target","");
        }else{
            // alert(obj_del);
            $("#bt_del").attr("data-target","#mydel");
        }
        
    }); 
});