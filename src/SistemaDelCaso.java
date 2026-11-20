import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SistemaDelCaso {
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    private Lista listaGeneral;
    private Cola colaPendientes;
    private Pila historialProcesados;
    private Scanner teclado;

    public SistemaDelCaso() {
        listaGeneral = new Lista();
        colaPendientes = new Cola();
        historialProcesados = new Pila();
        teclado = new Scanner(System.in);
    }

    public boolean registrarVehiculo(VehiculoParqueadero vehiculo) {
        if (listaGeneral.contiene(vehiculo)) {
            return false;
        }
        listaGeneral.agregar(vehiculo);
        colaPendientes.encolar(vehiculo);
        return true;
    }

    private void registrar() {
        System.out.print("Placa: ");
        String placa = teclado.nextLine().trim().toUpperCase();
        System.out.print("Propietario: ");
        String propietario = teclado.nextLine().trim();
        System.out.print("Tipo (CARRO/MOTO): ");
        String tipo = teclado.nextLine().trim().toUpperCase();
        String hora = LocalTime.now().format(FORMATO_HORA);

        VehiculoParqueadero vehiculo = new VehiculoParqueadero(placa, propietario, tipo, hora);
        if (registrarVehiculo(vehiculo)) {
            System.out.println("Vehiculo registrado, quedo en pendientes");
        } else {
            System.out.println("Esa placa ya esta registrada");
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("===== PARQUEADERO =====");
        System.out.println("1. Registrar elemento");
        System.out.println("2. Ver todos los elementos registrados");
        System.out.println("3. Ver elementos pendientes");
        System.out.println("4. Procesar siguiente elemento");
        System.out.println("5. Ver historial de elementos procesados");
        System.out.println("6. Buscar elemento por codigo");
        System.out.println("7. Cancelar elemento pendiente");
        System.out.println("8. Deshacer ultimo procesamiento");
        System.out.println("9. Ver cantidad de elementos");
        System.out.println("10. Salir");
    }

    public void ejecutar() {
        int opcion = 0;
        while (opcion != 10) {
            mostrarMenu();
            System.out.print("Escoja una opcion: ");
            opcion = Integer.parseInt(teclado.nextLine().trim());
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    if (listaGeneral.cuentaElementos() == 0) {
                        System.out.println("No hay vehiculos registrados");
                    } else {
                        listaGeneral.mostrarAdelante();
                    }
                    break;
                case 3:
                    colaPendientes.mostrar();
                    break;
                case 9:
                    System.out.println("Registrados: " + listaGeneral.cuentaElementos()
                            + " | Pendientes: " + colaPendientes.tamanio()
                            + " | Procesados: " + historialProcesados.tamanio());
                    break;
                case 10:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    public static void main(String[] args) {
        new SistemaDelCaso().ejecutar();
    }
}
