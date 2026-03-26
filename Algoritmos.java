public class Algoritmos {

    public String metodoGaussSeidel(double[][] A, double[] b, double tolerancia) {
        int n = b.length;
        String salida = "";

        // Guardar copias exactas para la comprobacion al final
        double[][] matrizOriginal = new double[n][n];
        double[] vectorOriginal = new double[n];
        for (int i = 0; i < n; i++) {
            vectorOriginal[i] = b[i];
            for (int j = 0; j < n; j++) {
                matrizOriginal[i][j] = A[i][j];
            }
        }

        salida += "Acomodando filas\n";
        for (int k = 0; k < n; k++) {
            int filaMayor = k;
            double maximo = Math.abs(A[k][k]);

            for (int i = k + 1; i < n; i++) {
                if (Math.abs(A[i][k]) > maximo) {
                    maximo = Math.abs(A[i][k]);
                    filaMayor = i;
                }
            }

            if (filaMayor != k) {
                for (int j = 0; j < n; j++) {
                    double temp = A[k][j];
                    A[k][j] = A[filaMayor][j];
                    A[filaMayor][j] = temp;
                }
                double tempB = b[k];
                b[k] = b[filaMayor];
                b[filaMayor] = tempB;
            }
        }

        // Variables para iteracion de Gauss-Seidel
        double[] x = new double[n];
        int iteracion = 1;
        int limiteIteraciones = 1000;
        double errorMaximo;

        do {
            errorMaximo = 0.0;

            for (int i = 0; i < n; i++) {
                double suma = b[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        // Redondear cada paso de la multiplicacion
                        suma = redondear(suma - redondear(A[i][j] * x[j]));
                    }
                }

                if (A[i][i] == 0) {
                    return salida + "Error: Un elemento en la diagonal es 0. El sistema no se puede resolver.";
                }

                // Redondear el nuevo valor de la variable
                double xNuevo = redondear(suma / A[i][i]);

                // Calcular el error y redondearlo
                double errorActual = 0;
                if (xNuevo != 0) {
                    errorActual = (xNuevo - x[i]) / xNuevo;
                }

                if (errorActual > errorMaximo) {
                    errorMaximo = errorActual;
                }

                x[i] = xNuevo;
            }
            iteracion++;

        } while (errorMaximo > tolerancia && iteracion <= limiteIteraciones);

        // Imprimir resultados a 5 decimales
        if (iteracion > limiteIteraciones) {
            salida += "Se llego al limite de iteraciones.\n";
        }

        salida += "\nIteracion donde paro: " + (iteracion - 1) + "\n";
        salida += "Error maximo en que paro: " + String.format("%.5f", errorMaximo) + "\n\n";


        salida += "Valores finales de X:\n";
        for (int i = 0; i < n; i++) {
            salida += "x_" + (i + 1) + " = " + redondear(x[i]) + "\n";
        }

        salida += "\nComprobacion (Sustitucion en ecuaciones originales):\n";

        for (int i = 0; i < n; i++) {
            double comprobacion = 0;
            String textoSustitucion = ""; 

            for (int j = 0; j < n; j++) {
                double coeficiente = matrizOriginal[i][j];
                double valorX = redondear(x[j]);

                //Suma matematica interna
                comprobacion = redondear(comprobacion + redondear(coeficiente * valorX));

                // Construccion del texto de sustitucion
                textoSustitucion += "(" + coeficiente + ")(" + valorX + ")";

                // Poner el signo "+" si no es el ultimo numero de la ecuacion
                if (j < n - 1) {
                    textoSustitucion += " + ";
                }
            }

            //Imprimir la linea completa
            salida += "Fila " + (i + 1) + ": " + textoSustitucion + " = " + comprobacion + " | Era: "
                    + vectorOriginal[i] + "\n";
        }

        return salida;
    }

    // Funcion auxiliar para reducir a 5 decimales
    private double redondear(double valor) {
        return Math.round(valor * 100000.0) / 100000.0;
    }

}