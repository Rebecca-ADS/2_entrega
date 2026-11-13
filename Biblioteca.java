public class Biblioteca {
    private final ItemBiblioteca[] acervo;
    private int totalItens;

    public Biblioteca(int capacidade) {
        this.acervo = new ItemBiblioteca[capacidade];
        this.totalItens = 0;
    }

    // Cadastro: devolve false se o array estiver cheio
    public boolean cadastrarItem(ItemBiblioteca item) {
        if (totalItens == acervo.length) {
            System.out.println("Acervo cheio. Não foi possível cadastrar: " + item.getTitulo());
            return false;
        }
        acervo[totalItens] = item;
        totalItens++;
        return true;
    }

    public boolean emprestar(Usuario usuario, ItemBiblioteca item) {
        if (!item.isDisponivel()) {
            System.out.println("RECUSADO: \"" + item.getTitulo() + "\" já está emprestado.");
            return false;
        }
        if (!usuario.podeEmprestar()) {
            System.out.println("RECUSADO: " + usuario.getNome()
                + " atingiu o limite de " + usuario.limiteItens() + " itens.");
            return false;
        }
        item.marcarEmprestado();
        usuario.registrarEmprestimo();
        System.out.println("OK: " + usuario.getNome() + " pegou \"" + item.getTitulo()
            + "\" (prazo: " + item.prazoDias() + " dias).");
        return true;
    }

    // Devolve e calcula a multa pelos dias de atraso
    public double devolver(Usuario usuario, ItemBiblioteca item, int diasAtraso) {
        if (item.isDisponivel() || usuario.getQuantidadeEmprestada() == 0) {
            System.out.println("RECUSADO: devolução inválida de \"" + item.getTitulo() + "\".");
            return 0;
        }
        item.marcarDevolvido();
        usuario.registrarDevolucao();

        double multa = diasAtraso > 0 ? diasAtraso * item.multaPorDia() : 0;
        System.out.println("OK: " + usuario.getNome() + " devolveu \"" + item.getTitulo()
            + "\". Multa: R$ " + String.format("%.2f", multa));
        return multa;
    }

    // Um único laço, sem nenhum if de tipo
    public void listarAcervo() {
        System.out.println("--- ACERVO ---");
        for (int i = 0; i < totalItens; i++) {
            ItemBiblioteca item = acervo[i];
            System.out.println(item.getCodigo()
                + " | " + item.getTitulo()
                + " | " + item.getClass().getSimpleName()
                + " | prazo " + item.prazoDias() + " dias"
                + " | " + (item.isDisponivel() ? "disponível" : "emprestado"));
        }
    }
}