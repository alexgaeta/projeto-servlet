<!DOCTYPE html>
<%@page import="br.com.alexgaeta.model.Cliente"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Email</title>
<script>
function confirma(pi){
	
	if (window.confirm("Tem certeza que deseja excluir?")){
		location.href="cliente?acao=exc&i="+pi;
	}
}
</script>
</head>
<body>


<div>

	<%
	Object msg=	request.getAttribute("msg");
	if (msg!=null){
		String msgStr = (String)msg;
		out.print(msg);
	}
	
	
	Cliente cli = (Cliente) request.getAttribute("cli");
	
	Object iCli =  request.getAttribute("iCli");
	
	%>
</div>


<form method="post" action="cliente">

	<input type="hidden" name="i" value="<%= iCli %>"/>
	Nome:
	<input type="text" value="<%= cli.getNome()%>" name="nome"/>
	E-mail:
	<input type="text" value="<%= cli.getEmail()%>" name="email"/>

	<input type="submit" value="Save">
</form>


<table border="1">
<tr>
<th>Nome</th>
<th>Email</th>
<th>Ação</th>
<tr>
<%
List<Cliente> lista=(List<Cliente>)request.getAttribute("lista");

int i=0;
for (Cliente c: lista){
	
%><tr>
	<td>
	<%=c.getNome()  %> 
	</td>
	<td>
	<%=c.getEmail()  %> 
	</td>
	<td>
	 <a href="javascript:confirma(<%=i%>)">excluir</a> |  
	 <a href="cliente?i=<%=i%>&acao=edit">editar</a>
	</td> 
</tr>
<%
	i++;
}
%>
</table>

</body>
</html>