package com.Dorival.portfolio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exercicio {
    public static void executar1(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT c.nome AS cliente, p.nome AS produto, ped.quantidade " +
                    "FROM Pedido ped " +
                    "INNER JOIN Cliente c ON ped.id_cliente = c.id_cliente " +
                    "INNER JOIN Produto p ON ped.id_produto = p.id_produto " +
                    "WHERE ped.quantidade > 2";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String cliente = rs.getString("cliente");
                String produto = rs.getString("produto");
                int quantidade = rs.getInt("quantidade");

                System.out.printf("Cliente: %s | Produto: %s | Quantidade: %d%n",
                        cliente, produto, quantidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executar2(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT prod.nome AS nomeProduto, " +
                    "SUM(ped.quantidade) AS quantidadeProduto, " +
                    "SUM(ped.quantidade * prod.preco) AS totalProdutos " +
                    "FROM Produto prod " +
                    "INNER JOIN Pedido ped ON prod.id_produto = ped.id_produto " +
                    "GROUP BY prod.nome ";




            ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String nomeProduto = rs.getString("nomeProduto");
            int quantiadeProduto = rs.getInt("quantidadeProduto");
            int totalProdutos = rs.getInt("totalProdutos");



            System.out.printf("Nome do produto: %s | Quantidade de Vendas :  %s | no total de vendas de: %S \n",
                                    nomeProduto, quantiadeProduto, totalProdutos);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


public static void executar3(Connection conn) {
    try (Statement stmt = conn.createStatement()) {
        String sql = "SELECT c.nome as nomeCliente ," +
                "SUM(ped.quantidade * prod.preco) AS totalGasto " +
                "FROM Produto prod " +
                "INNER JOIN Pedido ped ON prod.id_produto = ped.id_produto " +
                "INNER JOIN Cliente c ON ped.id_cliente = c.id_cliente " +
                "GROUP BY c.nome " +
                "ORDER BY totalGasto DESC";




        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String nomeCliente = rs.getString("nomeCliente");

            int totalGasto = rs.getInt("totalGasto");




            System.out.printf("O Cliente : %s | Gastou em produtos : %s  \n",
                    nomeCliente,totalGasto);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
