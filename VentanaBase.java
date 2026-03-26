import javax.swing.*;
import java.awt.*;

public class VentanaBase extends JFrame {
    
    private JTextField txtDimension;
    private JTextField txtTolerancia;
    private JPanel panelMatriz;
    private JTextArea txtResultados;
    private JButton btnGenerar;
    private JButton btnResolver;
    
    private JTextField[][] matrizCampos;
    private JTextField[] vectorCampos;

    public VentanaBase() {
        setTitle("Metodo de Gauss-Seidel ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelControl = new JPanel();
        panelControl.add(new JLabel("Variables:"));
        txtDimension = new JTextField("3", 5);
        panelControl.add(txtDimension);

        panelControl.add(new JLabel("Tolerancia:"));
        txtTolerancia = new JTextField("0.001", 6);
        panelControl.add(txtTolerancia);

        btnGenerar = new JButton("Generar Matriz");
        panelControl.add(btnGenerar);

        btnResolver = new JButton("Resolver");
        panelControl.add(btnResolver);

        add(panelControl, BorderLayout.NORTH);

        panelMatriz = new JPanel();
        add(new JScrollPane(panelMatriz), BorderLayout.CENTER);

        txtResultados = new JTextArea(15, 50);
        txtResultados.setEditable(false);
        add(new JScrollPane(txtResultados), BorderLayout.SOUTH);
    }

    public void crearCuadricula() {
        try {
            int n = Integer.parseInt(txtDimension.getText());
            
            panelMatriz.removeAll();
            panelMatriz.setLayout(new GridLayout(n, 1));
            
            matrizCampos = new JTextField[n][n];
            vectorCampos = new JTextField[n];

            for (int i = 0; i < n; i++) {
                JPanel filaPanel = new JPanel();
                
                for (int j = 0; j < n; j++) {
                    matrizCampos[i][j] = new JTextField(4);
                    filaPanel.add(matrizCampos[i][j]);
                    
                    if (j < n - 1) {
                        filaPanel.add(new JLabel("x_" + (j + 1) + " + "));
                    } else {
                        filaPanel.add(new JLabel("x_" + (j + 1)));
                    }
                }
                
                filaPanel.add(new JLabel(" = "));
                vectorCampos[i] = new JTextField(4);
                filaPanel.add(vectorCampos[i]);
                
                panelMatriz.add(filaPanel);
            }
            
            panelMatriz.revalidate();
            panelMatriz.repaint();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un numero entero para la dimension.");
        }
    }

    // Getters
    public JButton getBtnGenerar() { return btnGenerar; }
    public JButton getBtnResolver() { return btnResolver; }
    public String getDimension() { return txtDimension.getText(); }
    public String getTolerancia() { return txtTolerancia.getText(); }
    public JTextField[][] getMatrizCampos() { return matrizCampos; }
    public JTextField[] getVectorCampos() { return vectorCampos; }
    
    public void setTextoResultados(String texto) { txtResultados.setText(texto); }
}