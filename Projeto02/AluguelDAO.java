package Projeto02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AluguelDAO {
    public void inserir(Aluguel aluguel) {
        String sql = "INSERT INTO aluguel (veiculo_placa, cliente_cpf, dias, valor_total) VALUES(?, ?, ?, ?)";

        try {
            try (
                    Connection conn = Conexao.conectar();
                    PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
                stmt.setString(1, aluguel.getVeiculo().getPlaca());
                stmt.setString(2, aluguel.getCliente().getCpf());
                stmt.setInt(3, aluguel.getDias());
                stmt.setDouble(4, aluguel.getValorTotal());

                stmt.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir aluguel: " + e.getMessage());
        }
    }
}