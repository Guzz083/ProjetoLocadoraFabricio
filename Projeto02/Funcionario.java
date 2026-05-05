package Projeto02;

import java.text.ParseException;

public class Funcionario extends Pessoa {
    private String matricula;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cpf, String matricula, String cargo, double salario) throws ParseException {
        super(nome, cpf);
        this.matricula = matricula;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    @Override
    public String getTipo() { return "FUNCIONÁRIO"; }

    @Override
    public String toString() {
        return super.toString() + " | Matrícula: " + matricula
                + " | Cargo: " + cargo
                + " | Salário: R$ " + String.format("%.2f", salario);
    }
}