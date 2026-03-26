import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GestorEventos implements ActionListener {
    
    private VentanaBase vista;
    private Algoritmos modelo;

    public GestorEventos(VentanaBase vista, Algoritmos modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.getBtnGenerar().addActionListener(this);
        this.vista.getBtnResolver().addActionListener(this);
        this.vista.crearCuadricula();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGenerar()) {
            vista.crearCuadricula();
        } 
        else if (e.getSource() == vista.getBtnResolver()) {
            resolverSistema();
        }
    }

    private void resolverSistema() {
        try {
            int n = Integer.parseInt(vista.getDimension());
            double tolerancia = Double.parseDouble(vista.getTolerancia());
            
            double[][] matrizA = new double[n][n];
            double[] vectorB = new double[n];
            
            JTextField[][] cajasMatriz = vista.getMatrizCampos();
            JTextField[] cajasVector = vista.getVectorCampos();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrizA[i][j] = Double.parseDouble(cajasMatriz[i][j].getText());
                }
                vectorB[i] = Double.parseDouble(cajasVector[i].getText());
            }

            String respuesta = modelo.metodoGaussSeidel(matrizA, vectorB, tolerancia);
            vista.setTextoResultados(respuesta);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Llene todos los campos con numeros validos.");
        }
    }
}