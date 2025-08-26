package com.Dorival.portfolio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.Scanner;

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

            Scanner sc = new Scanner(System.in);
            int escolha;

            do {
                System.out.println("\n=== PORTFÓLIO JAVA ===");
                System.out.println("1 - Cliente com pedidos acima de 2 unidades1");
                System.out.println("2 - Descobrir qual produto foi mais vendido! ");
                System.out.println("3 - ");
                System.out.println("4 - Sair");
                System.out.print("Escolha uma opção: ");

                if (sc.hasNextInt()) {
                    escolha = sc.nextInt();
                } else {
                    System.out.println("⚠ Opção inválida!");
                    sc.next();
                    escolha = 0;
                }

                switch (escolha) {
                    case 1:
                        Exercicio.executar1(conn);
                        break;
                    case 2:
                        Exercicio.executar2(conn);
                        break;
                    case 4:
                        System.out.println("Saindo do portfólio...");
                        break;
                    default:
                        if (escolha != 0) System.out.println("⚠ Opção inválida! Tente novamente.");
                }
            } while (escolha != 4);

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
