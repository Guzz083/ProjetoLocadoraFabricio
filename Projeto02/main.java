
package Projeto02;

import javax.swing.JOptionPane;
import java.text.ParseException;
import java.util.ArrayList;

public class main {

    // Listas de armazenamento
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    static ArrayList<Veiculo> veiculos = new ArrayList<>();

    public static void main(String[] args) throws ParseException {
        while (true) {
            String[] opcoesPainel = {
                    " Cadastrar Cliente",
                    " Cadastrar Funcionário",
                    " Cadastrar Veículo",
                    " Listar Clientes",
                    " Listar Funcionários",
                    " Listar Veículos",
                    "❌ Sair"
            };

            int escolha = JOptionPane.showOptionDialog(
                    null,
                    " Sistema de Locadora\n\nEscolha uma opção:",
                    "Locadora - Painel Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoesPainel,
                    opcoesPainel[0]
            );

            if (escolha == -1 || escolha == 6) {
                int confirmar = JOptionPane.showConfirmDialog(
                        null,
                        "Deseja realmente sair do sistema?",
                        "Confirmar Saída",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmar == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Sistema encerrado.",
                            "Saindo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                continue;
            }

            switch (escolha) {
                case 0 -> cadastrarCliente();
                case 1 -> cadastrarFuncionario();
                case 2 -> cadastrarVeiculo();
                case 3 -> listarClientes();
                case 4 -> listarFuncionarios();
                case 5 -> listarVeiculos();
            }
        }
    }


    static void cadastrarCliente() throws ParseException {
        String nome = JOptionPane.showInputDialog(null, "Nome do cliente:", "Cadastrar Cliente", JOptionPane.PLAIN_MESSAGE);
        if (nome == null || nome.isBlank()) { avisoVazio(); return; }

        String cpf = JOptionPane.showInputDialog(null, "CPF (somente números):", "Cadastrar Cliente", JOptionPane.PLAIN_MESSAGE);
        if (cpf == null || cpf.isBlank()) { avisoVazio(); return; }

        String endereco = JOptionPane.showInputDialog(null, "Endereço:", "Cadastrar Cliente", JOptionPane.PLAIN_MESSAGE);
        if (endereco == null || endereco.isBlank()) { avisoVazio(); return; }

        String telefone = JOptionPane.showInputDialog(null, "Telefone:", "Cadastrar Cliente", JOptionPane.PLAIN_MESSAGE);
        if (telefone == null || telefone.isBlank()) { avisoVazio(); return; }

        Cliente cliente = new Cliente(nome.trim(), cpf.trim(), endereco.trim());
        clientes.add(cliente);

        JOptionPane.showMessageDialog(null,
                "✅ Cliente cadastrado com sucesso!\n\n" + cliente,
                "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
    }


    static void cadastrarFuncionario() throws ParseException {
        String nome = JOptionPane.showInputDialog(null, "Nome do funcionário:", "Cadastrar Funcionário", JOptionPane.PLAIN_MESSAGE);
        if (nome == null || nome.isBlank()) { avisoVazio(); return; }

        String cpf = JOptionPane.showInputDialog(null, "CPF (somente números):", "Cadastrar Funcionário", JOptionPane.PLAIN_MESSAGE);
        if (cpf == null || cpf.isBlank()) { avisoVazio(); return; }

        String matricula = JOptionPane.showInputDialog(null, "Matrícula:", "Cadastrar Funcionário", JOptionPane.PLAIN_MESSAGE);
        if (matricula == null || matricula.isBlank()) { avisoVazio(); return; }

        String cargo = JOptionPane.showInputDialog(null, "Cargo:", "Cadastrar Funcionário", JOptionPane.PLAIN_MESSAGE);
        if (cargo == null || cargo.isBlank()) { avisoVazio(); return; }

        double salario = 0;
        while (true) {
            String salarioStr = JOptionPane.showInputDialog(null, "Salário:", "Cadastrar Funcionário", JOptionPane.PLAIN_MESSAGE);
            if (salarioStr == null) { avisoVazio(); return; }
            try {
                salario = Double.parseDouble(salarioStr.replace(",", ".").trim());
                if (salario < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, " Valor inválido. Digite um número positivo.",
                        "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }

        Funcionario func = new Funcionario(nome.trim(), cpf.trim(), matricula.trim(), cargo.trim(), salario);
        boolean add;
        if (funcionarios.add(func)) add = true;
        else add = false;

        JOptionPane.showMessageDialog(null,
                " Funcionário cadastrado com sucesso!\n\n" + func,
                "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
    }


    static void cadastrarVeiculo() {
        String placa = JOptionPane.showInputDialog(null, "Placa do veículo:", "Cadastrar Veículo", JOptionPane.PLAIN_MESSAGE);
        if (placa == null || placa.isBlank()) { avisoVazio(); return; }

        String marca = JOptionPane.showInputDialog(null, "Marca:", "Cadastrar Veículo", JOptionPane.PLAIN_MESSAGE);
        if (marca == null || marca.isBlank()) { avisoVazio(); return; }

        String modelo = JOptionPane.showInputDialog(null, "Modelo:", "Cadastrar Veículo", JOptionPane.PLAIN_MESSAGE);
        if (modelo == null || modelo.isBlank()) { avisoVazio(); return; }

        int ano = 0;
        while (true) {
            String anoStr = JOptionPane.showInputDialog(null, "Ano de fabricação:", "Cadastrar Veículo", JOptionPane.PLAIN_MESSAGE);
            if (anoStr == null) { avisoVazio(); return; }
            try {
                ano = Integer.parseInt(anoStr.trim());
                if (ano < 1900 || ano > 2100) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "️Ano inválido. Digite um ano entre 1900 e 2100.",
                        "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }

        double diaria = 0;
        while (true) {
            String diariaStr = JOptionPane.showInputDialog(null, "Valor da diária (ex: 150.00):", "Cadastrar Veículo", JOptionPane.PLAIN_MESSAGE);
            if (diariaStr == null) { avisoVazio(); return; }
            try {
                diaria = Double.parseDouble(diariaStr.replace(",", ".").trim());
                if (diaria < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, " Valor inválido. Digite um número positivo.",
                        "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }

        String[] tiposDisponiveis = {"CARRO", "MOTO", "VAN"};
        int tipoEscolhido = JOptionPane.showOptionDialog(
                null,
                "Selecione o tipo do veículo:",
                "Tipo de Veículo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                tiposDisponiveis,
                tiposDisponiveis[0]
        );
        if (tipoEscolhido == -1) { avisoVazio(); return; }

        Veiculo.Tipo tipo = Veiculo.Tipo.values()[tipoEscolhido];
        Veiculo veiculo = new Veiculo(placa.trim().toUpperCase(), marca.trim(), modelo.trim(), ano, diaria, tipo);
        veiculos.add(veiculo);

        JOptionPane.showMessageDialog(null,
                " Veículo cadastrado com sucesso!\n\n" + veiculo,
                "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
    }

    static void listarClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado ainda.",
                    "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("👥 CLIENTES CADASTRADOS (" + clientes.size() + ")\n");
        sb.append("─".repeat(60)).append("\n");
        for (int i = 0; i < clientes.size(); i++) {
            sb.append((i + 1)).append(". ").append(clientes.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }

    static void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado ainda.",
                    "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder(" FUNCIONÁRIOS CADASTRADOS (" + funcionarios.size() + ")\n");
        sb.append("─".repeat(60)).append("\n");
        for (int i = 0; i < funcionarios.size(); i++) {
            sb.append((i + 1)).append(". ").append(funcionarios.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
    }

    static void listarVeiculos() {
        if (veiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum veículo cadastrado ainda.",
                    "Lista de Veículos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder(" VEÍCULOS CADASTRADOS (" + veiculos.size() + ")\n");
        sb.append("─".repeat(60)).append("\n");
        for (int i = 0; i < veiculos.size(); i++) {
            sb.append((i + 1)).append(". ").append(veiculos.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Veículos", JOptionPane.INFORMATION_MESSAGE);
    }

    static void avisoVazio() {
        JOptionPane.showMessageDialog(null, " Operação cancelada ou campo vazio. Cadastro não realizado.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}