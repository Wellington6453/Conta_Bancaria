package projeto.teste;
import persistencia.PersistenciaA;

public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}