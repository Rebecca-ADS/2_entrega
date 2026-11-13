public class DVD extends ItemBiblioteca {

    public DVD(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int prazoDias() {
        return 3;
    }

    @Override
    public double multaPorDia() {
        return 2.00;
    }
}