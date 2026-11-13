public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10);

        ItemBiblioteca l1 = new Livro("L1", "Dom Casmurro");
        ItemBiblioteca l2 = new Livro("L2", "O Cortiço");
        ItemBiblioteca r1 = new Revista("R1", "Superinteressante");
        ItemBiblioteca l3 = new Livro("L3", "Memórias Póstumas");

        biblioteca.cadastrarItem(l1);
        biblioteca.cadastrarItem(l2);
        biblioteca.cadastrarItem(r1);
        biblioteca.cadastrarItem(l3);

        Usuario ana = new Aluno("Ana");

        // Empréstimos bem-sucedidos (Ana tem limite 3)
        biblioteca.emprestar(ana, l1);
        biblioteca.emprestar(ana, l2);
        biblioteca.emprestar(ana, r1);

        // Recusado: limite atingido
        biblioteca.emprestar(ana, l3);
        ItemBiblioteca d1 = new DVD("D1", "Matrix");
        biblioteca.cadastrarItem(d1);
        biblioteca.listarAcervo();

        // Devolução com 3 dias de atraso
        biblioteca.devolver(ana, r1, 3);

        biblioteca.listarAcervo();
    }
}