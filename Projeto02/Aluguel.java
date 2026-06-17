
package Projeto02;

import java.time.LocalDate;

public class Aluguel {

    private int id;
    private Veiculo veiculo;
    private Cliente cliente;
    private Funcionario funcionario;
    private int dias;
    private double valorTotal;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativo;

    public Aluguel(Veiculo veiculo, Cliente cliente, Funcionario funcionario, int dias) {
        if (veiculo.getStatus() != Veiculo.Status.DISPONIVEL) {
            throw new IllegalStateException("Veículo não está disponível para locação.");
        }
        if (dias <= 0) {
            throw new IllegalArgumentException("A quantidade de dias deve ser maior que zero.");
        }

        this.veiculo = veiculo;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dias = dias;
        this.valorTotal = veiculo.getDiaria() * dias;
        this.dataInicio = LocalDate.now();
        this.dataFim = null;
        this.ativo = true;

        veiculo.setStatus(Veiculo.Status.ALUGADO);
    }

    public Aluguel(int id, Veiculo veiculo, Cliente cliente, Funcionario funcionario,
                   int dias, double valorTotal, LocalDate dataInicio, LocalDate dataFim, boolean ativo) {
        this.id = id;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dias = dias;
        this.valorTotal = valorTotal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = ativo;
    }

    public void encerrarLocacao() {
        if (!ativo) {
            throw new IllegalStateException("Esta locação já foi encerrada anteriormente.");
        }
        this.dataFim = LocalDate.now();
        this.ativo = false;
        veiculo.setStatus(Veiculo.Status.DISPONIVEL);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Veiculo getVeiculo() { return veiculo; }
    public Cliente getCliente() { return cliente; }
    public Funcionario getFuncionario() { return funcionario; }
    public int getDias() { return dias; }
    public double getValorTotal() { return valorTotal; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public boolean isAtivo() { return ativo; }

    @Override
    public String toString() {
        String status = ativo ? "ATIVA" : "ENCERRADA";
        String periodo = "Início: " + dataInicio + (dataFim != null ? " | Fim: " + dataFim : "");

        return "[" + status + "] " + veiculo.getMarca() + " " + veiculo.getModelo()
                + " (Placa: " + veiculo.getPlaca() + ")"
                + " | Cliente: " + cliente.getNome()
                + " | Funcionário: " + funcionario.getNome()
                + " | Dias: " + dias
                + " | Valor Total: R$ " + String.format("%.2f", valorTotal)
                + " | " + periodo;
    }
}
 