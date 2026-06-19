package Projeto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class AluguelDAO {
    public void inserir(Aluguel aluguel) {
        String sql = "INSERT INTO aluguel (veiculo_id, cliente_id, funcionario_id, dias, valor_total, data_inicio, data_fim, ativo) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setInt(1, aluguel.getVeiculo().getId());
            stmt.setInt(2, aluguel.getCliente().getId());
            stmt.setInt(3, aluguel.getFuncionario().getId());
            stmt.setInt(4, aluguel.getDias());
            stmt.setDouble(5, aluguel.getValorTotal());
            stmt.setObject(6, aluguel.getDataInicio());
            stmt.setObject(7, aluguel.getDataFim());
            stmt.setBoolean(8, aluguel.isAtivo());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    aluguel.setId(generatedKeys.getInt(1));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir aluguel: " + e.getMessage());
        }
    }

    public void encerrar(Aluguel aluguel) {
        String sql = "UPDATE aluguel SET data_fim = ?, ativo = ? WHERE id = ?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setObject(1, aluguel.getDataFim());
            stmt.setBoolean(2, aluguel.isAtivo());
            stmt.setInt(3, aluguel.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encerrar aluguel no banco: " + e.getMessage());
        }
    }

    public ArrayList<Aluguel> buscarAtivas() {
        ArrayList<Aluguel> ativas = new ArrayList<>();
        String sql = "SELECT * FROM aluguel WHERE ativo = 1";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            VeiculoDAO veiculoDao = new VeiculoDAO();
            ClienteDAO clienteDao = new ClienteDAO();
            FuncionarioDAO funcionarioDao = new FuncionarioDAO();

            while (rs.next()) {
                int id = rs.getInt("id");
                int veiculoId = rs.getInt("veiculo_id");
                int clienteId = rs.getInt("cliente_id");
                int funcionarioId = rs.getInt("funcionario_id");
                int dias = rs.getInt("dias");

                Veiculo veiculo = veiculoDao.buscarPorId(veiculoId);
                Cliente cliente = clienteDao.buscarPorId(clienteId);
                Funcionario func = funcionarioDao.buscarPorId(funcionarioId);

                double valorTotal = rs.getDouble("valor_total");
                LocalDate dataInicio = rs.getDate("data_inicio").toLocalDate();


                LocalDate dataFim = null;
                if (rs.getDate("data_fim") != null) {
                    dataFim = rs.getDate("data_fim").toLocalDate();
                }

                boolean ativo = rs.getBoolean("ativo");

                Aluguel aluguel = new Aluguel(id, veiculo, cliente, func, dias, valorTotal, dataInicio, dataFim, ativo);
                ativas.add(aluguel);

                ativas.add(aluguel);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar aluguéis ativos: " + e.getMessage());
        }

        return ativas;
    }

    public ArrayList<Aluguel> buscarTodos() {

        ArrayList<Aluguel> todos = new ArrayList<>();

        String sql = "SELECT * FROM aluguel";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            VeiculoDAO veiculoDao = new VeiculoDAO();
            ClienteDAO clienteDao = new ClienteDAO();
            FuncionarioDAO funcionarioDao = new FuncionarioDAO();

            while (rs.next()) {
                int id = rs.getInt("id");
                int veiculoId = rs.getInt("veiculo_id");
                int clienteId = rs.getInt("cliente_id");
                int funcionarioId = rs.getInt("funcionario_id");
                int dias = rs.getInt("dias");

                Veiculo veiculo = veiculoDao.buscarPorId(veiculoId);
                Cliente cliente = clienteDao.buscarPorId(clienteId);
                Funcionario func = funcionarioDao.buscarPorId(funcionarioId);

                double valorTotal = rs.getDouble("valor_total");
                LocalDate dataInicio = rs.getDate("data_inicio").toLocalDate();

                LocalDate dataFim = null;
                if (rs.getDate("data_fim") != null) {
                    dataFim = rs.getDate("data_fim").toLocalDate();
                }

                boolean ativo = rs.getBoolean("ativo");

                Aluguel aluguel = new Aluguel(id, veiculo, cliente, func, dias, valorTotal, dataInicio, dataFim, ativo);

                todos.add(aluguel);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os aluguéis: " + e.getMessage());
        }

        return todos;
    }
}