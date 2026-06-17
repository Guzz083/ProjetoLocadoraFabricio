package Projeto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FuncionarioDAO {
    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, matricula, cargo, salario) VALUES(?, ?, ?, ?, ?)";

        try {
            try (
                    Connection conn = Conexao.conectar();
                    PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getCpf());
                stmt.setString(3, funcionario.getMatricula());
                stmt.setString(4, funcionario.getCargo());
                stmt.setString(5, String.valueOf(funcionario.getSalario()));
                stmt.executeUpdate();
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
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String matricula = rs.getString("matricula");
                String cargo = rs.getString("cargo");
                double salario = rs.getDouble("salario");

                Funcionario funcionario = new Funcionario(nome, cpf, matricula, cargo, salario);

                funcBanco.add(funcionario);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar funcionários: " + e.getMessage());
        }

        return funcBanco;
    }
}

