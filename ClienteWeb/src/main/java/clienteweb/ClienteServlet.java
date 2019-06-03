package clienteweb;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alexgaeta.model.Cliente;
import br.com.alexgaeta.service.ClienteService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/cliente", "/clienteServlet", "/ClienteService" })
public class ClienteServlet extends HttpServlet {

	ClienteService clienteService;

	public ClienteServlet() {
		System.out.println("Construindo Servlet...");

	}

	@Override
	public void init() throws ServletException {
		clienteService = new ClienteService();
		System.out.println("Inicializando Servlet");
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Chamando o service...");
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int indice = -1;
		Cliente cli = new Cliente();
		cli.setEmail("");
		cli.setNome("");		

		String i = req.getParameter("i");
		if (i != null && i != "") {
			indice = Integer.parseInt(i);
		}
		String acao = req.getParameter("acao");
		if (i != null && i != "" && acao != null && acao != "") {
			if (acao.equals("exc")) {

				clienteService.excluir(indice);
				//
			} else if (acao.equals("edit")) {

				cli = clienteService.buscarPorIndice(indice);
			}

		}
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("cli", cli);
		req.setAttribute("iCli", indice);
		req.setAttribute("lista", clienteService.getTodosClientes());

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int indice =-1;		
	    //Recebendo o nome
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		String i =  req.getParameter("i");

		if (i!=null && i!=""){
				indice = Integer.parseInt(i);
		
		//Colocando nome em um objeto cliente
		Cliente cli =  new Cliente();
		cli.setNome(nome);	
		cli.setEmail(email);	
		clienteService.salvar(indice, cli);
		
		cli = new Cliente();
		cli.setNome("");
		cli.setEmail("");

		
		//System.out.println("Chamou pelo método POST!");
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("msg","Cadastrado com sucesso!");
		req.setAttribute("cli", cli);
		req.setAttribute("iCli", -1);
		req.setAttribute("lista", clienteService.getTodosClientes());
		dispatcher.forward(req, resp);		
		
		}

	}		

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ???
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ???
	}

	@Override
	public void destroy() {
		System.out.println("Servlet está sendo destruido");
		super.destroy();
	}
}