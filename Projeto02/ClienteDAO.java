package Projeto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, endereco) VALUES(?, ?, ?)";

        try {
            try (
                    Connection conn = Conexao.conectar();
                    PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getCpf());
                stmt.setString(3, cliente.getEnde());
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public ArrayList<Cliente> buscarClientes() {
        ArrayList<Cliente> clientesBanco = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");

                Cliente cliente = new Cliente(nome, cpf, endereco);

                clientesBanco.add(cliente);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar clientes: " + e.getMessage());
        }

        return clientesBanco;
    }
}

