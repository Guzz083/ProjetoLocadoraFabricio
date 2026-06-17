
package Projeto02;

import javax.swing.JOptionPane;
import java.text.ParseException;
import java.util.ArrayList;

public class    main {

    static ClienteDAO clienteDao = new ClienteDAO();
    static FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    static VeiculoDAO veiculoDao = new VeiculoDAO();
    static AluguelDAO aluguelDAO = new AluguelDAO();

    public static void main(String[] args) throws ParseException {
        while (true) {
            String[] opcoesPainel = {
                    " Cadastrar Cliente",
                    " Cadastrar Funcionário",
                    " Cadastrar Veículo",
                    " Alugar Veículo",
                    " Encerrar Locação",
                    " Listar Clientes",
                    " Listar Funcionários",
                    " Listar Veículos",
                    " Listar Locações",
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

            if (escolha == -1 || escolha == 9) {
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
                case 3 -> alugarVeiculo();
                case 4 -> encerrarLocacao();
                case 5 -> listarClientes();
                case 6 -> listarFuncionarios();
                case 7 -> listarVeiculos();
                case 8 -> listarLocacoes();
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

        Cliente cliente = new Cliente(nome.trim(), cpf.trim(), endereco.trim());
        //clientes.add(cliente);

        clienteDao.inserir(cliente);

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
        if (matricula == null || matricula.isBlank()) { avisoVazio();        return; }

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
        //boolean add;
        //if (funcionarios.add(func)) add = true;
        //else add = false;

        //JOptionPane.showMessageDialog(null,
        //        " Funcionário cadastrado com sucesso!\n\n" + func,
        //        "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);

        funcionarioDao.inserir(func);
        JOptionPane.showMessageDialog(null,
                "✅ Funcionário cadastrado com sucesso!\n\n" + func,
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
        //veiculos.add(veiculo);
        veiculoDao.inserir(veiculo);



        JOptionPane.showMessageDialog(null,
                " Veículo cadastrado com sucesso!\n\n" + veiculo,
                "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
    }

    static void alugarVeiculo() {

        ArrayList<Veiculo> disponiveis = veiculoDao.buscarDisponiveis();
        ArrayList<Cliente> clientesBanco = clienteDao.buscarClientes();
        ArrayList<Funcionario> funcionariosBanco = funcionarioDao.buscarFunc();

        if (disponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum veículo disponível para locação no momento.",
                    "Alugar Veículo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (clientesBanco.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado. Cadastre um cliente antes de alugar.",
                    "Alugar Veículo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (funcionariosBanco.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado. Cadastre um funcionário antes de alugar.",
                    "Alugar Veículo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Veiculo veiculoEscolhido = (Veiculo) JOptionPane.showInputDialog(
                null, "Selecione o veículo a ser alugado:", "Alugar Veículo",
                JOptionPane.PLAIN_MESSAGE, null, disponiveis.toArray(), disponiveis.get(0));
        if (veiculoEscolhido == null) { avisoVazio(); return; }

        Cliente clienteEscolhido = (Cliente) JOptionPane.showInputDialog(
                null, "Selecione o cliente:", "Alugar Veículo",
                JOptionPane.PLAIN_MESSAGE, null, clientesBanco.toArray(), clientesBanco.get(0));
        if (clienteEscolhido == null) { avisoVazio(); return; }

        Funcionario funcionarioEscolhido = (Funcionario) JOptionPane.showInputDialog(
                null, "Selecione o funcionário responsável pela locação:", "Alugar Veículo",
                JOptionPane.PLAIN_MESSAGE, null, funcionariosBanco.toArray(), funcionariosBanco.get(0));
        if (funcionarioEscolhido == null) { avisoVazio(); return; }

        int dias = 0;
        while (true) {
            String diasStr = JOptionPane.showInputDialog(null, "Quantidade de dias de locação:",
                    "Alugar Veículo", JOptionPane.PLAIN_MESSAGE);
            if (diasStr == null) { avisoVazio(); return; }
            try {
                dias = Integer.parseInt(diasStr.trim());
                if (dias <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, " Valor inválido. Digite um número inteiro maior que zero.",
                        "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }

        try {
            Aluguel aluguel = new Aluguel(veiculoEscolhido, clienteEscolhido, funcionarioEscolhido, dias);

            aluguelDAO.inserir(aluguel);

            veiculoDao.atualizarStatus(veiculoEscolhido.getId(), Veiculo.Status.ALUGADO);

            JOptionPane.showMessageDialog(null,
                    "✅ Locação realizada com sucesso!\n\n" + aluguel,
                    "Locação Confirmada", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalStateException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a locação: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void encerrarLocacao() {

        ArrayList<Aluguel> ativas = aluguelDAO.buscarAtivas();

        if (ativas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há locações ativas no momento.",
                    "Encerrar Locação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Aluguel aluguelEscolhido = (Aluguel) JOptionPane.showInputDialog(
                null, "Selecione a locação a ser encerrada:", "Encerrar Locação",
                JOptionPane.PLAIN_MESSAGE, null, ativas.toArray(), ativas.get(0));
        if (aluguelEscolhido == null) {
            avisoVazio();
            return;
        }

        aluguelEscolhido.encerrarLocacao();

        aluguelDAO.encerrar(aluguelEscolhido);

        veiculoDao.atualizarStatus(aluguelEscolhido.getVeiculo().getId(), Veiculo.Status.DISPONIVEL);

        JOptionPane.showMessageDialog(null,
                "✅ Locação encerrada com sucesso! O veículo está disponível novamente.\n\n" + aluguelEscolhido,
                "Locação Encerrada", JOptionPane.INFORMATION_MESSAGE);
    }

    static void listarLocacoes() {

        ArrayList<Aluguel> locacoesBanco = aluguelDAO.buscarTodos(); // Busca do banco

        if (locacoesBanco.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma locação registrada ainda.",
                    "Lista de Locações", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder(" LOCAÇÕES REGISTRADAS (" + locacoesBanco.size() + ")\n");
        sb.append("─".repeat(60)).append("\n");
        for (int i = 0; i < locacoesBanco.size(); i++) {
            sb.append((i + 1)).append(". ").append(locacoesBanco.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Locações", JOptionPane.INFORMATION_MESSAGE);
    }

    static void listarClientes() {

        ArrayList<Cliente> clientes = clienteDao.buscarClientes();

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

        ArrayList<Funcionario> funcionariosBanco = funcionarioDao.buscarFunc();

        if (funcionariosBanco.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado ainda.",
                    "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder(" FUNCIONÁRIOS CADASTRADOS (" + funcionariosBanco.size() + ")\n");
        sb.append("─".repeat(60)).append("\n");
        for (int i = 0; i < funcionariosBanco.size(); i++) {
            sb.append((i + 1)).append(". ").append(funcionariosBanco.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
    }

    static void listarVeiculos() {

        ArrayList<Veiculo> listaVeiculos = veiculoDao.buscarTodos();

        if (listaVeiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum veículo cadastrado ainda.",
                    "Lista de Veículos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder(" VEÍCULOS CADASTRADOS (" + listaVeiculos.size() + ")\n");
        sb.append("─".repeat(60)).append("\n");
        for (int i = 0; i < listaVeiculos.size(); i++) {
            sb.append((i + 1)).append(". ").append(listaVeiculos.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Veículos", JOptionPane.INFORMATION_MESSAGE);
    }

    static void avisoVazio() {
        JOptionPane.showMessageDialog(null, " Operação cancelada ou campo vazio. Cadastro não realizado.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}