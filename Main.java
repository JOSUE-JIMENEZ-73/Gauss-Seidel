public class Main {
    public static void main(String[] args) {
        VentanaBase vista = new VentanaBase();
        Algoritmos modelo = new Algoritmos();
        GestorEventos gestor = new GestorEventos(vista, modelo);
        vista.setVisible(true);
    }
}