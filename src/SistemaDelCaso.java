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

    public VehiculoParqueadero procesarSiguiente() {
        if (colaPendientes.esVacia()) {
            return null;
        }
        VehiculoParqueadero procesado = (VehiculoParqueadero) colaPendientes.desencolar();
        historialProcesados.apilar(procesado);
        return procesado;
    }

    public boolean cancelarPendiente(String placa) {
        VehiculoParqueadero buscado = new VehiculoParqueadero(placa, "", "", "");
        Cola auxiliar = new Cola();
        boolean cancelado = false;

        while (!colaPendientes.esVacia()) {
            VehiculoParqueadero actual = (VehiculoParqueadero) colaPendientes.desencolar();
            if (!cancelado && actual.equals(buscado)) {
                cancelado = true;
            } else {
                auxiliar.encolar(actual);
            }
        }
        while (!auxiliar.esVacia()) {
            colaPendientes.encolar(auxiliar.desencolar());
        }
        return cancelado;
    }

    public VehiculoParqueadero deshacerUltimo() {
        if (historialProcesados.esVacia()) {
            return null;
        }
        VehiculoParqueadero ultimo = (VehiculoParqueadero) historialProcesados.desapilar();
        colaPendientes.encolar(ultimo);
        return ultimo;
    }

    public VehiculoParqueadero buscarPorPlaca(String placa) {
        VehiculoParqueadero buscado = new VehiculoParqueadero(placa, "", "", "");
        return (VehiculoParqueadero) listaGeneral.buscarDato(buscado);
    }

    private void registrar() {
        System.out.print("Placa (ABC123 o ABC12D): ");
        String placa = teclado.nextLine().trim().toUpperCase();
        if (!placa.matches("[A-Z]{3}[0-9]{2}[A-Z0-9]")) {
            System.out.println("Placa no valida, no se registro");
            return;
        }
        System.out.print("Propietario: ");
        String propietario = teclado.nextLine().trim();
        if (propietario.isEmpty()) {
            System.out.println("El propietario no puede estar vacio");
            return;
        }
        System.out.print("Tipo (CARRO/MOTO): ");
        String tipo = teclado.nextLine().trim().toUpperCase();
        if (!tipo.equals("CARRO") && !tipo.equals("MOTO")) {
            System.out.println("Tipo no valido, debe ser CARRO o MOTO");
            return;
        }
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

    private int leerOpcion() {
        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void ejecutar() {
        int opcion = 0;
        while (opcion != 10) {
            mostrarMenu();
            System.out.print("Escoja una opcion: ");
            opcion = leerOpcion();
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
                case 4:
                    VehiculoParqueadero procesado = procesarSiguiente();
                    if (procesado == null) {
                        System.out.println("No hay vehiculos pendientes");
                    } else {
                        System.out.println("Procesado: " + procesado);
                    }
                    break;
                case 5:
                    historialProcesados.mostrar();
                    break;
                case 7:
                    System.out.print("Placa a cancelar: ");
                    String placaCancelar = teclado.nextLine();
                    if (cancelarPendiente(placaCancelar)) {
                        System.out.println("Pendiente cancelado");
                    } else {
                        System.out.println("No esta en pendientes");
                    }
                    break;
                case 6:
                    System.out.print("Placa a buscar: ");
                    VehiculoParqueadero encontrado = buscarPorPlaca(teclado.nextLine());
                    if (encontrado == null) {
                        System.out.println("No se encontro ese vehiculo");
                    } else {
                        System.out.println("Encontrado: " + encontrado);
                    }
                    break;
                case 8:
                    VehiculoParqueadero deshecho = deshacerUltimo();
                    if (deshecho == null) {
                        System.out.println("No hay procesamientos para deshacer");
                    } else {
                        System.out.println("Deshecho, vuelve a pendientes: " + deshecho.getPlaca());
                    }
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
