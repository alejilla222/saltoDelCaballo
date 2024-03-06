package org.example;

import java.util.ArrayList;
import java.util.List;

public class CaballoAjedrez {

    private static final int N = 8;// Se declara el tamaño del tablero con N =  8
    private static int[][] tablero = new int[N][N];//Declaro la matriz para representar el tablero

    //Movimientos posibles del caballo en el tablero de Ajedrez
    private static final int[] movimientosFilas = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] movimientosColumnas = {1, 2, 2, 1, -1, -2, -2, -1};

    //Metodo para imprimir el tablero

    private static void imprimirTablero() {
        //Creo un bucle for que itera sobre las filas del tablero
        for (int i = 0; i < N; i++) {
            //Creo un bucle que itera sobre las columnas del tablero
            for (int j = 0; j < N; j++) {
                //Está condición verifica si la posición actual contiene al caballo
                if (tablero[i][j] == -1) {
                    //Si esa posición contiene al caballo imprime C
                    System.out.print("C\t"); // Posición del caballo
                } else {//si no contiene al caballo imprime el valor de la posición
                    System.out.print(tablero[i][j] + "\t"); // Posición normal
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //Metodo principal para resolver el problema del salto del caballo

    private static boolean resolverSaltoCaballo(int fila, int columna, int movimiento) {
        // Condición que verifica si ha realizado todos los movimientos requeridos en este caso queremos mostrar 3 movimientos
        if (movimiento == 4) {
            return true; // Mostrar solo los primeros 3 movimientos
        }
        // Itera sobre los 8 posibles movimientos del caballo
        for (int i = 0; i < 8; i++) {
            // Calcula la nueva posición del caballo
            int nuevaFila = fila + movimientosFilas[i];
            int nuevaColumna = columna + movimientosColumnas[i];
            // Verifica si la nueva posición es válida
            if (esMovimientoValido(nuevaFila, nuevaColumna)) {
                // Marca la nueva posición en el tablero con el número de movimiento
                tablero[nuevaFila][nuevaColumna] = movimiento;
                // Imprime el tablero con el nuevo movimiento
                System.out.println("Movimiento " + movimiento + ":");
                imprimirTablero();

                // Llama recursivamente a la función para el siguiente movimiento
                //La recursividad se encuentra en la función resolverSaltoCaballo. En esta función, se llama a sí misma
                // de manera recursiva para explorar diferentes movimientos del caballo en el tablero. La recursión ocurre
                // en el siguiente bloque de código:
                if (resolverSaltoCaballo(nuevaFila, nuevaColumna, movimiento + 1)) {
                    return true; // Si la recursión encuentra una solución, se devuelve true

                    // Esta llamada recursiva continúa hasta que se alcanza la condición base, que es cuando el movimiento es igual a 4.
                }
                // Si la recursión no encuentra una solución, se realiza backtracking
                //Aquí, resolverSaltoCaballo se llama a sí misma con las nuevas coordenadas (nuevaFila, nuevaColumna) y el siguiente
                // número de movimiento + 1.
                // Esta llamada recursiva continúa hasta que se alcanza la condición base, que es cuando movimiento es igual a 4.
                tablero[nuevaFila][nuevaColumna] = 0; // Deshace el movimiento en el backtracking
                //Aquí, se restablece la posición en el tablero (tablero[nuevaFila][nuevaColumna]) a 0 para deshacer
                // el último movimiento realizado. Este es un paso clave en el backtracking, ya que permite al algoritmo
                // explorar otras posibilidades desde ese punto.
            }
        }

        return false; // No se encontró ninguna solución en esta rama de la recursión
    }

    private static boolean esMovimientoValido(int fila, int columna) {
        // La función verifica si una posición (fila, columna) en el tablero es válida para un movimiento del caballo.
        // Retorna true si la posición está dentro del tablero, no ha sido visitada y está marcada como 0 en el tablero.
        return (fila >= 0 && fila < N && columna >= 0 && columna < N && tablero[fila][columna] == 0);
    }

    public static void main(String[] args) {
        // La función principal del programa.
        for (int k = 0; k < 4; k++) {
            // Se ejecuta un bucle para iniciar el salto del caballo desde diferentes posiciones iniciales.

            // Inicializar el tablero
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tablero[i][j] = 0; // Marca todas las posiciones del tablero como no visitadas (0).
                }
            }

            // Iniciar el salto del caballo desde diferentes posiciones
            tablero[k][0] = -1; // Establece la posición inicial del caballo en la columna 0 y la fila k.
            System.out.println("Solución encontrada para la posición inicial (" + k + ", 0):");
            imprimirTablero(); // Imprime el tablero después de establecer la posición inicial.

            // Llama al método resolverSaltoCaballo para explorar los posibles movimientos del caballo.
            if (resolverSaltoCaballo(k, 0, 1)) {
                System.out.println("Recorrido completo."); // Si se encuentra una solución, imprime un mensaje indicando que el recorrido está completo.
            } else {
                System.out.println("No hay solución."); // Si no se encuentra una solución, imprime un mensaje indicando que no hay solución.
            }
        }
    }
}













