package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProdutoDAO;


@WebServlet(name = "produtos", urlPatterns = {"/produtos", "/produtos/listar", "/produtos/cadastrar", "/produtos/novo"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO produtoDAO = null;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        produtoDAO = new ProdutoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch (action) {
				case "/produtos/novo": 
					novo(request, response);
					break;
				
				case "/produtos/listar": 
					list(request, response);
					break;
				
				case "/produtos/cadastrar": 
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/produtos/produto-cadastro.jsp");
					dispatcher.forward(request, response);
					break;
				default:
					break;
				
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		Produto novoProduto = null;
		
		String nomeProduto = request.getParameter("nomeProduto");
		int qtdProduto = Integer.parseInt(request.getParameter("qtdProduto")); 
		float valorProduto = Float.parseFloat(request.getParameter("valorProduto"));
		
		if (nomeProduto != null && qtdProduto > 0 && valorProduto > 0) {
			novoProduto = new Produto(nomeProduto, qtdProduto, valorProduto);
			produtoDAO.inserir(novoProduto);
		}
		
		try {
			list(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		
		ArrayList<Produto> listaProdutos = produtoDAO.listar();
		request.setAttribute("listaProdutos", listaProdutos);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/produtos/produto-listar.jsp");
		dispatcher.forward(request, response);
		
	}

}
