<!DOCTYPE html>
<%@page import="br.com.alexgaeta.model.Cliente"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Email</title>
<script>
function exclui(pi){
	
	if (window.confirm("Tem certeza que deseja excluir?")){
		location.href="cliente?acao=exc&i="+pi;
		}
}
function edita(ei){
 
		location.href="cliente?acao=edit&i="+ei;
	} 
</script>
</head>
<body>


<div>

	${requestScope.msg}
	
</div>


<form method="post" action="cliente">

	<input type="hidden" name="i" value="${requestScope.iCli}"/>
	Nome:
	<input type="text" value="${requestScope.cli.nome}" name="nome"/>
	E-mail:
	<input type="text" value="${requestScope.cli.email}" name="email"/>

	<input type="submit" value="Save">
</form>


<table border="1">
<tr>
<th>Nome</th>
<th>Email</th>
<th>Ação</th>
</tr>
<c:set var="i" value="0"/>
<c:forEach items="${requestScope.lista}" var="c">
	<tr>
	<td>
	${c.nome} 
	</td>
	<td>
	${c.email} 
	</td>
	<td>
	 <input type="submit" onclick ="javascript:exclui(${i})" value="excluir" />|  
	 <input type="submit" onclick ="javascript:edita(${i})" value="editar"/>|
	</td> 
</tr>
<c:set var="i" value="${i+1}"/>
</c:forEach>
</table>

</body>
</html>