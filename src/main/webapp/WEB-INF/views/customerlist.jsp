<%--
  Created by IntelliJ IDEA.
  User: S6203-1-08
  Date: 2020/11/16
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <link href="${APP_PATH }/static/css/dui.css" rel="stylesheet">
    <link href="${APP_PATH }/static/css/main.css" rel="stylesheet">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${APP_PATH }/static/bootstrap-3.4.1-dist/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${APP_PATH }/static/bootstrap-3.4.1-dist/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${APP_PATH }/static/bootstrap-3.4.1-dist/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <style type="text/css">
        .type-list .dialog {height:360px !important;margin-left: -320px;margin-top: -180px;width: 740px;}
        .type-list .dialog input {width: 60% !important;}
        .type-list .dialog .btns-item {margin-top: 30px !important;}
        .type-add{display: none;}
        .dialog{height:320px;width:600px;}
        .dialog.dialog-page {height:320px;width:700px;}
        .dialog.dialog-page .body{height: 320px;width:700px;}
    </style>
</head>
<body onload="init()">
<div class="index container">
    <div class="header">
        <img class="logo" src="${APP_PATH }/static/images/logo.png">
        <div class="title">
            <h3>百疆书社 -- 图书馆管理</h3>
            <h5>BAIJIANG BOOKSTORE LIBRARY MANAGE</h5>
        </div>
        <div class="toolbar">
            <a id="account">${user.name}</a>
            <a id="pwdedit" onclick="pwdedit()">修改密码</a>
            <a id="signout" onclick="signout()">注销</a>
            <img class="usericon" src="${APP_PATH }/static/images/icons/usericon.png">
        </div>
    </div>
    <div class="main">
        <div class="toolbar">
            <div class="btns-wrap">
                <div class="btns-tool">
                    <a id="editbook" title="借阅管理" href="borrowList"></a>
                    <a id="addbook" title="添加图书" href="bookList"></a>
                    <a id="typemgr" title="类别管理" href="typeList"></a>
                    <a id="bookmgr" title="客户管理" href="customerList" class="cur"></a>
                    <a id="usermgr" title="用户管理" href="userList" ></a>
                </div>
            </div>
        </div>
        <div class="subpage">
            <div class="concierge-list">
                <div class="search-form">
                    <div class="form-item">
                        <label>手机号</label>
                        <input id="inputPhone">
                    </div>
                    <div class="form-item">
                        <label>用户名</label>
                        <input id="inputUsername">
                    </div>
                    <div class="form-item">
                        <label>地址</label>
                        <input id="inputAddress">
                    </div>
                    <div class="form-item">
                        <label>工作单位</label>
                        <input id="inputCompany">
                    </div>

                    <div class="form-item btns-item">
                        <button class="success" id="new" onclick="onNew()">新增</button>
                        <button class="primary" id="search" onclick="loadRecord()">查询</button>
                    </div>
                </div>
                <div class="listwrap">
                    <table>
                        <thead>
                        <tr>
                            <th style="width: 10%;">序号</th>
                            <th style="width: 10%;">手机号码</th>
                            <th style="width: 10%;">用户名</th>
                            <th style="width: 17%;">地址</th>
                            <th style="width: 17%;">工作单位</th>
                            <th style="width: 10%;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="list-rows">

                        <c:forEach var="customer" items="${pageInfo.list}">
                        <tr>
                            <td class="tC">${customer.id}</td>
                            <td class="tC">${customer.phone}</td>
                            <td class="tC">${customer.name}</td>
                            <td class="tC">${customer.address}</td>
                            <td class="tC">${customer.company}</td>
                            <td class="tC">
                                <a class="edit btn primary" href="#" onclick="onEdit(${customer.id})">修改信息</a>
                                <a class="delete btn danger" href="#" onclick="deleteEle(${customer.id})">删除</a>
                            </td>
                        </tr></c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- 分页条信息 -->
                <div >
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li><a href="${APP_PATH}/customerListSearch">首页</a></li>
                            <c:if test="${pageInfo.hasPreviousPage }">
                                <li><a href="${APP_PATH }/customerListSearch?pn=${pageInfo.pageNum-1}"
                                       aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                                </a></li>
                            </c:if>


                            <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                                <c:if test="${page_Num == pageInfo.pageNum }">
                                    <li class="active"><a href="${APP_PATH}/customerListSearch">${page_Num }</a></li>
                                </c:if>
                                <c:if test="${page_Num != pageInfo.pageNum }">
                                    <li><a href="${APP_PATH }/customerListSearch?pn=${page_Num }">${page_Num }</a></li>
                                </c:if>

                            </c:forEach>
                            <c:if test="${pageInfo.hasNextPage }">
                                <li><a href="${APP_PATH }/customerListSearch?pn=${pageInfo.pageNum+1 }"
                                       aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                                </a></li>
                            </c:if>
                            <li><a href="${APP_PATH }/customerListSearch?pn=${pageInfo.pages}">末页</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

            </div>
            <div class="dialog dialog-page" style="display: none;" id="edit">
                <div class="header">
                    <span>编辑客户</span><i class="close" onclick="closeEdit()"></i>
                </div>
                <div class="body">
                    <div class="concierge-add">

                            <div class="form">

                                <form  method="post" id="myform"  action="customerSave">
                                <div class="form-item">
                                    <label>用户名</label>
                                    <input id="username" name="name">
                                </div>
                                <div class="form-item">
                                    <label class="name-label">手机号码</label>
                                    <input id="phone" name="phone">
                                </div>
                                    <div class="form-item">
                                        <label class="name-label">住址</label>
                                        <input id="address" name="address">
                                    </div>
                                    <div class="form-item">
                                        <label class="name-label">工作单位</label>
                                        <input id="company" name="company">
                                    </div>
                                </form>

                                <div class="form-item btns-item">
                                    <button class="primary" id="save" onclick="newSave()">保存</button>
                                    <button id="cancle" onclick="closeEdit()">取消</button>
                                </div>

                            </div>

                    </div>
                </div>
            </div>
            <div id="shadow"></div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var count = 4;
    function signout(){
        if(confirm("确定要注销吗？")){
            location.href = "userLoginout";
        }
    }
    function deleteEle(e){
        if(confirm("确定要删除吗？")){
            window.location="customerDelete?id="+e;
        }
    }
    function onEdit(id){
        //使用js跳转页面
       window.location="customerEdit?id="+id;


    }
function init() {
        var opo="${opo}";
if(opo=="edit"){
 document.getElementById("username").value="${customer.name}";
   document.getElementById("phone").value="${customer.phone}";
    document.getElementById("address").value="${customer.address}";
    document.getElementById("company").value="${customer.company}";

          document.getElementById("edit").style.display="block";
       document.getElementById("shadow").style.display="block";
}
}
    function onNew(){
       window.location="customerAdd";
    }
    function newSave(){

        //获取表单的值
        var myform = document.getElementById("myform");

        //判断到底是更新还是添加
        var opo="${opo}";
        if(opo=="edit"&&myform.title!="save"){
            //不建议使用地址加参数，使用的是get请求
            myform.action="customerUpdate?id=${customer.id}";
        }
        myform.submit();
        //提交表单
    }
    function editSave(){
        alert("修改成功！");
        var para = document.createElement("tr");
        var value = document.getElementById("typename").value;
        var list = document.getElementById("list-rows");
        list.appendChild(para);
        closeEdit();
    }
    function closeEdit(){
        //还原表单 js代码
        document.getElementById("username").value="";
        document.getElementById("phone").value="";
        document.getElementById("address").value="";
        document.getElementById("company").value="";
        var myform = document.getElementById("myform");
        myform.title="save";//设置成一个添加标识
        myform.action="customerSave";

        document.getElementById("edit").style.display="none";
        document.getElementById("shadow").style.display="none";
    }
    function loadRecord(){
        let phone = document.getElementById("inputPhone").value
        let username = document.getElementById("inputUsername").value
        let address = document.getElementById("inputAddress").value
        let company = document.getElementById("inputCompany").value
        window.location="customerListSearch?phone=" + phone + "&username=" + username + "&address=" + address + "&company=" + company
        alert("查询成功！");
    }
    function pwdedit() {
        window.location="pwEdit"
    }
</script>
</html>