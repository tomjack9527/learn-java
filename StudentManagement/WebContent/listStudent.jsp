<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(function() {
		$("a").addClass("btn btn-default btn-xs");

	});
</script>
<table style="width: 500px; margin: 44px auto"
	class="table table-striped table-bordered table-hover  table-condensed"
	align='center' border='1' cellspacing='0'>
	<tr>
		<td>id</td>
		<td>姓名</td>
		<td>学号</td>
		<td>专业</td>
		<td></td>
		<td></td>
	</tr>
	<c:forEach items="${students}" var="student" varStatus="st">
		<tr>
			<td>${student.id}</td>
			<td>${student.name}</td>
			<td>${student.stuNumber}</td>
			<td>${student.major}</td>
			<td><a href="editStudent?id=${student.id}">编辑</a></td>
			<td><a href="deleteStudent?id=${student.id}">删除</a></td>
		</tr>
	</c:forEach>

</table>
<nav>
	<ul class="pager">

		<li><a href="?start=0">首 页</a></li>
		<li><a href="?start=${pre}">上一页</a></li>
		<li><a href="?start=${next}">下一页</a></li>
		<li><a href="?start=${last}">末 页</a></li>
	</ul>
</nav>