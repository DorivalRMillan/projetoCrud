package com.Dorival.portfolio;

import java.sql.Connection;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Criar tabela Cliente
            stmt.execute("CREATE TABLE Cliente (" +
                    "id_cliente INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(100)," +
                    "email VARCHAR(100)," +
                    "data_nascimento DATE)");

            // Inserir dados de exemplo
            stmt.execute("INSERT INTO Cliente (nome, email, data_nascimento) VALUES" +
                    "('Ana Souza', 'ana@email.com', '1990-05-10')," +
                    "('Carlos Lima', 'carlos@email.com', '1985-09-22')");

            System.out.println("Banco em memória criado e dados inseridos!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
