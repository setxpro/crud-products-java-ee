package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;
import utils.ConnectionFactory;

public class ProdutoDAO {
	public boolean inserir(Produto produto) {
		boolean okay = false;
		String sql = "INSERT INTO TbProduct (nomeProduto, qtdProduto, valorProduto) VALUES (?,?,?)";
		
		try(
				@SuppressWarnings("static-access")
				Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement cmd = connection.prepareStatement(sql);
			) {
			
			cmd.setString(1, produto.getNomeProduto());
			cmd.setInt(2, produto.getQtdProduto());
			cmd.setFloat(3, produto.getValorProduto());
			
			int linhasAfetadas = cmd.executeUpdate();
			
			if (linhasAfetadas > 0) {
				okay = true;
			}
			
			
		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println(ex);
			ex.printStackTrace();
		}
		
		return okay;
	}
	
	public ArrayList<Produto> listar() {
		
		ArrayList<Produto> listaRetorno = new ArrayList<>();
		
		String sql = "SELECT * FROM TbProduct";
		
		try (
				@SuppressWarnings("static-access")
				Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement cmd = connection.prepareStatement(sql);
				ResultSet rs = cmd.executeQuery();
				) {
			
			while(rs.next()) {
				
				int idProduto = rs.getInt("idProduto");
				String nomeProduto = rs.getString("nomeProduto");
				int qtdProduto = rs.getInt("qtdProduto");
				float valorProduto = rs.getFloat("valorProduto");
				
				Produto objectList = new Produto(idProduto, nomeProduto, qtdProduto, valorProduto);
				
				listaRetorno.add(objectList);
				
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		

		return listaRetorno;
	}
}
