package Projeto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VeiculoDAO {
    public void inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, marca, modelo, ano, diaria, tipo, `status`) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            try (
                    Connection conn = Conexao.conectar();
                    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ) {
                stmt.setString(1, veiculo.getPlaca());
                stmt.setString(2, veiculo.getMarca());
                stmt.setString(3, veiculo.getModelo());
                stmt.setInt(4, veiculo.getAno());
                stmt.setDouble(5, veiculo.getDiaria());
                stmt.setString(6, veiculo.getTipo().name());
                stmt.setString(7, veiculo.getStatus().name());
                stmt.executeUpdate();

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        veiculo.setId(generatedKeys.getInt(1));
                    }
                }


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
                int id = rs.getInt("id");
                String placa = rs.getString("placa");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                double diaria = rs.getDouble("diaria");
                Veiculo.Tipo tipo = Veiculo.Tipo.valueOf(rs.getString("tipo"));
                Veiculo.Status status = Veiculo.Status.valueOf(rs.getString("status"));

                Veiculo veiculo = new Veiculo(id, placa, marca, modelo, ano, diaria, tipo, status);

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
                int id = rs.getInt("id");
                String placa = rs.getString("placa");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                double diaria = rs.getDouble("diaria");
                Veiculo.Tipo tipo = Veiculo.Tipo.valueOf(rs.getString("tipo"));
                Veiculo.Status status = Veiculo.Status.valueOf(rs.getString("status"));

                Veiculo veiculo = new Veiculo(id, placa, marca, modelo, ano, diaria, tipo, status);

                Todos.add(veiculo);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar veículos disponíveis: " + e.getMessage());
        }

        return Todos;
    }

    public void atualizarStatus(int id, Veiculo.Status novoStatus) {
        String sql = "UPDATE veiculo SET status = ? WHERE id = ?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, novoStatus.name());
            stmt.setInt(2, id); // Passando o ID numérico

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar status do veículo: " + e.getMessage());
        }
    }

    public Veiculo buscarPorId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String placa = rs.getString("placa");
                    String marca = rs.getString("marca");
                    String modelo = rs.getString("modelo");
                    int ano = rs.getInt("ano");
                    double diaria = rs.getDouble("diaria");
                    Veiculo.Tipo tipo = Veiculo.Tipo.valueOf(rs.getString("tipo"));
                    Veiculo.Status status = Veiculo.Status.valueOf(rs.getString("status"));

                    return new Veiculo(id, placa, marca, modelo, ano, diaria, tipo, status);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar veículo por ID: " + e.getMessage());
        }

        return null;
    }
}

