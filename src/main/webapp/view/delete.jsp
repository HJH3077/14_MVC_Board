<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:800px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:10px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	.odd {background:silver}
</style>
<script type="text/javascript">
	function list_go(f) {
		/* f.action="${pageContext.request.contextPath}/MyController?cmd=list"; */
		history.go(-1);
		/* f.submit(); */
	}
	
	function delete_go(f) {
		if("${vo.pwd}" == f.pwd.value){
			var chk = confirm("정말 삭제할까요?");
			if(chk){
				f.action="${pageContext.request.contextPath}/MyController?cmd=delete_ok";
				f.submit();
			}else{
				history.go(-1);
			}
		} else{
			alert("비밀번호 틀림");
			f.pwd.value="";
			f.pwd.focus();
		}
	}
</script>
</head>
<body>
	<h2 style="text-align:center;">Board 글삭제</h2>
	<div id="bbs">
	<form method="post">
		<table>
			<tbody>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" name="pwd" size="12"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center">
						<input type="button" value="삭제" onclick="delete_go(this.form)" />
						<input type="button" value="목록" onclick="list_go(this.form)" />
						<input type="hidden" name="cPage" value="${cPage}">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>

