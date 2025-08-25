package com.Dorival.portfolio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;


public class Main {
    public static void main(String[] args) {
        try (
             //Abrir conexão com o BD em Mémoria
             Connection conn = DatabaseConnection.getConnection();
             //Abrir o Statement
             Statement stmt = conn.createStatement()) {

            // Criar tabela Cliente
            stmt.execute("CREATE TABLE Cliente (" +
                    "id_cliente INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "data_nascimento DATE)");

            // Criar tabela Produto
            stmt.execute("CREATE TABLE Produto (" +
                    "id_produto INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "preco DECIMAL(10,2) NOT NULL)");

            // Criar tabela Pedido
            stmt.execute("CREATE TABLE Pedido (" +
                    "id_pedido INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_cliente INT," +
                    "id_produto INT," +
                    "quantidade INT NOT NULL," +
                    "data_pedido DATE," +
                    "FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)," +
                    "FOREIGN KEY (id_produto) REFERENCES Produto(id_produto))");

            // Inserir Clientes
            stmt.execute("INSERT INTO Cliente (nome, email, data_nascimento) VALUES" +
                    "('Ana Souza', 'ana@email.com', '1990-05-10')," +
                    "('Carlos Lima', 'carlos@email.com', '1985-09-22')," +
                    "('Fernanda Torres', 'fernanda@email.com', '1995-12-01')");

            // Inserir Produtos
            stmt.execute("INSERT INTO Produto (nome, preco) VALUES" +
                    "('Notebook', 3500.00)," +
                    "('Smartphone', 2200.00)," +
                    "('Fone de Ouvido', 150.00)");

            // Inserir Pedidos
            stmt.execute("INSERT INTO Pedido (id_cliente, id_produto, quantidade, data_pedido) VALUES" +
                    "(1, 1, 1, '2023-01-15')," + // Ana comprou 1 Notebook
                    "(2, 2, 2, '2023-02-10')," + // Carlos comprou 2 Smartphones
                    "(3, 3, 3, '2023-03-05')");  // Fernanda comprou 3 Fones

            System.out.println("Banco em memória criado e populado com dados!");

            String sql = "SELECT nome, data_nascimento\n" +
                    "FROM Cliente\n" +
                    "WHERE (YEAR(CURRENT_DATE) - YEAR(data_nascimento)) > 20\n" +
                    "ORDER BY data_nascimento ASC;\n";
            ResultSet rs = stmt.executeQuery(sql);



            while (rs.next()) {
                String nome = rs.getString("nome");
                LocalDate nascimento = rs.getDate("data_nascimento").toLocalDate();
                int idadeAtual = Period.between(nascimento, LocalDate.now()).getYears();


                System.out.println("Nome: " + nome + " | Data de nascimento: " + nascimento + " | Idade: " + idadeAtual);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
