package Projeto02;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public abstract class Pessoa {
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf) throws ParseException {
        this.nome = nome;
        this.cpf = formatarCpf(cpf);
    }

    private String formatarCpf(String cpf) throws ParseException {
        // Remove tudo que não for dígito antes de aplicar a máscara
        String apenasDigitos = cpf.replaceAll("[^0-9]", "");

        MaskFormatter formatter = new MaskFormatter("###.###.###-##");
        formatter.setValueContainsLiteralCharacters(false);

        return formatter.valueToString(apenasDigitos);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) throws ParseException { this.cpf = formatarCpf(cpf); }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "[" + getTipo() + "] " + nome + " | CPF: " + cpf;
    }
}