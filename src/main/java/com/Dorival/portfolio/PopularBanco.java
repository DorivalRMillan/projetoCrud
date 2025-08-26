package com.Dorival.portfolio;

import java.sql.Connection;
import java.sql.Statement;

public class PopularBanco {

    public static void popular(Connection conn) {
        try (Statement stmt = conn.createStatement()) {

            // Inserir Clientes
            stmt.execute("INSERT INTO Cliente (nome, email, data_nascimento) VALUES" +
                    "('Ana Silva', 'ana@email.com', '1990-05-12')," +
                    "('Carlos Pereira', 'carlos@email.com', '1985-10-30')," +
                    "('Mariana Souza', 'mariana@email.com', '2000-07-21')," +
                    "('João Mendes', 'joao@email.com', '1995-03-15')," +
                    "('Fernanda Lima', 'fernanda@email.com', '1988-12-01')," +
                    "('Rafael Torres', 'rafael@email.com', '1992-09-18')," +
                    "('Beatriz Rocha', 'beatriz@email.com', '1998-06-25')," +
                    "('Pedro Alves', 'pedro@email.com', '1983-02-11')," +
                    "('Juliana Castro', 'juliana@email.com', '1997-08-09')," +
                    "('Lucas Martins', 'lucas@email.com', '1994-11-27')");

            // Inserir Produtos
            stmt.execute("INSERT INTO Produto (nome, preco) VALUES" +
                    "('Notebook', 3500.00)," +
                    "('Smartphone', 2500.00)," +
                    "('Tablet', 1500.00)," +
                    "('Monitor 24\"', 900.00)," +
                    "('Teclado Mecânico', 350.00)," +
                    "('Mouse Gamer', 120.00)," +
                    "('Headset', 200.00)," +
                    "('Cadeira Gamer', 1200.00)," +
                    "('Impressora', 600.00)," +
                    "('HD Externo 1TB', 450.00)");

            // Inserir Pedidos
            stmt.execute("INSERT INTO Pedido (id_cliente, id_produto, quantidade, data_pedido) VALUES" +
                    "(1, 1, 2, '2024-01-10')," +
                    "(2, 2, 1, '2024-01-12')," +
                    "(3, 3, 4, '2024-01-15')," +
                    "(4, 4, 2, '2024-01-20')," +
                    "(5, 5, 5, '2024-01-22')," +
                    "(6, 6, 1, '2024-01-25')," +
                    "(7, 7, 2, '2024-02-01')," +
                    "(8, 8, 3, '2024-02-05')," +
                    "(9, 9, 1, '2024-02-07')," +
                    "(10, 10, 4, '2024-02-10')," +
                    "(1, 2, 2, '2024-02-12')," +
                    "(2, 3, 1, '2024-02-14')," +
                    "(3, 4, 3, '2024-02-15')," +
                    "(4, 5, 2, '2024-02-18')," +
                    "(5, 1, 1, '2024-02-20')," +
                    "(6, 6, 4, '2024-02-22')," +
                    "(7, 7, 1, '2024-02-25')," +
                    "(8, 8, 2, '2024-02-28')," +
                    "(9, 9, 3, '2024-03-01')," +
                    "(10, 10, 1, '2024-03-03')," +
                    "(1, 3, 2, '2024-03-05')," +
                    "(2, 4, 1, '2024-03-08')," +
                    "(3, 5, 3, '2024-03-10')," +
                    "(4, 6, 2, '2024-03-12')," +
                    "(5, 7, 1, '2024-03-15')," +
                    "(6, 8, 2, '2024-03-18')," +
                    "(7, 9, 4, '2024-03-20')," +
                    "(8, 10, 1, '2024-03-22')," +
                    "(9, 1, 3, '2024-03-25')," +
                    "(10, 2, 2, '2024-03-28')");

            System.out.println("Banco populado com 10 clientes, 10 produtos e 30 pedidos!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
