package Projeto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionarioDAO {
    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, matricula, cargo, salario) VALUES(?, ?, ?, ?, ?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getMatricula());
            stmt.setString(4, funcionario.getCargo());
            stmt.setDouble(5, funcionario.getSalario());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcionario.setId(generatedKeys.getInt(1));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    public ArrayList<Funcionario> buscarFunc() {
        ArrayList<Funcionario> funcBanco = new ArrayList<>();

        String sql = "SELECT * FROM funcionario";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String matricula = rs.getString("matricula");
                String cargo = rs.getString("cargo");
                double salario = rs.getDouble("salario");

                Funcionario funcionario = new Funcionario(id, nome, cpf, matricula, cargo, salario);

                funcBanco.add(funcionario);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar funcionários: " + e.getMessage());
        }

        return funcBanco;
    }

    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    String matricula = rs.getString("matricula");
                    String cargo = rs.getString("cargo");
                    double salario = rs.getDouble("salario");

                    // Retorna o objeto montado usando o construtor com ID
                    return new Funcionario(id, nome, cpf, matricula, cargo, salario);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar funcionário por ID: " + e.getMessage());
        }

        return null;
    }
}

