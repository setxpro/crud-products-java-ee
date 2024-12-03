<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manutenção de Produtos</title>
<link rel="stylesheet" type="text/css" href="../css/styles.css"/>
</head>
<body>
		
		<div class="content-form">
			<form class="form" action="${produto == null ? 'novo' : 'update'}" method="post">
				<div class="input-group">
					<input type="hidden" name="idProduto" value="${ produto.getIdProduto() }">
				</div>
				<div class="input-group">
					<label>Descrição:</label> <input type="text" name="nomeProduto" value="${ produto.getNomeProduto() }">
				</div>
				<div class="input-group">
					<label>Quantidade:</label> <input type="text" name="qtdProduto" value="${ produto.getQtdProduto() }">
				</div>
				<div class="input-group">
					<label>Valor:</label> <input type="text" name="valorProduto" value="${ produto.getValorProduto() }">
				</div>
				<div>
					<input type="submit" value="Salvar">
					<input type="button" value="Cancelar" onclick="window.location.href='listar';" />
				</div>
			</form>
		</div>
</body>
</html>