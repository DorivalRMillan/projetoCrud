package com.Dorival.portfolio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Criar tabelas
            stmt.execute("CREATE TABLE Cliente (" +
                    "id_cliente INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "data_nascimento DATE)");

            stmt.execute("CREATE TABLE Produto (" +
                    "id_produto INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "preco DECIMAL(10,2) NOT NULL)");

            stmt.execute("CREATE TABLE Pedido (" +
                    "id_pedido INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_cliente INT," +
                    "id_produto INT," +
                    "quantidade INT NOT NULL," +
                    "data_pedido DATE," +
                    "FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)," +
                    "FOREIGN KEY (id_produto) REFERENCES Produto(id_produto))");

            // Popular banco
            PopularBanco.popular(conn);

            // Consultas
            String sql = "SELECT c.nome AS cliente, p.nome AS produto, ped.quantidade, ped.data_pedido " +
                    "FROM Pedido ped " +
                    "INNER JOIN Cliente c ON ped.id_cliente = c.id_cliente " +
                    "INNER JOIN Produto p ON ped.id_produto = p.id_produto";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String cliente = rs.getString("cliente");
                String produto = rs.getString("produto");
                int quantidade = rs.getInt("quantidade");
                Date dataPedido = rs.getDate("data_pedido");

                System.out.printf("Cliente: %s | Produto: %s | Quantidade: %d | Data: %s%n",
                        cliente, produto, quantidade, dataPedido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
