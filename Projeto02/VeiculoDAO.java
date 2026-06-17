package Projeto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeiculoDAO {
    public void inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, marca, modelo, ano, diaria, tipo, `status`) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            try (
                    Connection conn = Conexao.conectar();
                    PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
                stmt.setString(1, veiculo.getPlaca());
                stmt.setString(2, veiculo.getMarca());
                stmt.setString(3, veiculo.getModelo());
                stmt.setString(4, String.valueOf(veiculo.getAno()));
                stmt.setString(5, String.valueOf(veiculo.getDiaria()));
                stmt.setString(6, String.valueOf(veiculo.getTipo()));
                stmt.setString(7, String.valueOf(veiculo.getStatus()));
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir veículo: " + e.getMessage());
        }
    }

    public ArrayList<Veiculo> buscarDisponiveis() {
        ArrayList<Veiculo> disponiveis = new ArrayList<>();

        String sql = "SELECT * FROM veiculo WHERE `status` = 'DISPONIVEL'";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                String placa = rs.getString("placa");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                double diaria = rs.getDouble("diaria");
                Veiculo.Tipo tipo = Veiculo.Tipo.valueOf(rs.getString("tipo"));

                Veiculo veiculo = new Veiculo(placa, marca, modelo, ano, diaria, tipo);

                veiculo.setStatus(Veiculo.Status.valueOf(rs.getString("status")));

                disponiveis.add(veiculo);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar veículos disponíveis: " + e.getMessage());
        }

        return disponiveis;
    }

    public ArrayList<Veiculo> buscarTodos() {
        ArrayList<Veiculo> Todos = new ArrayList<>();

        String sql = "SELECT * FROM veiculo";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                String placa = rs.getString("placa");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                double diaria = rs.getDouble("diaria");
                Veiculo.Tipo tipo = Veiculo.Tipo.valueOf(rs.getString("tipo"));

                Veiculo veiculo = new Veiculo(placa, marca, modelo, ano, diaria, tipo);

                veiculo.setStatus(Veiculo.Status.valueOf(rs.getString("status")));

                Todos.add(veiculo);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar veículos disponíveis: " + e.getMessage());
        }

        return Todos;
    }

}

