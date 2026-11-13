public abstract class Usuario {
    private final String nome;
    private int quantidadeEmprestada;

    public Usuario(String nome) {
        this.nome = nome;
        this.quantidadeEmprestada = 0;
    }

    public abstract int limiteItens();

    public String getNome() { return nome; }
    public int getQuantidadeEmprestada() { return quantidadeEmprestada; }

    public boolean podeEmprestar() {
        return quantidadeEmprestada < limiteItens();
    }

    // Sem "public": só a Biblioteca (mesmo pacote) altera
    void registrarEmprestimo() { quantidadeEmprestada++; }
    void registrarDevolucao() { quantidadeEmprestada--; }
}
