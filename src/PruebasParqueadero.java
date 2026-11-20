public class PruebasParqueadero {
    private static int pasaron = 0;
    private static int fallaron = 0;

    public static void main(String[] args) {
        probarLista();
        probarCola();
        probarPila();
        probarSistema();

        System.out.println();
        System.out.println("Pasaron: " + pasaron + " | Fallaron: " + fallaron);
    }

    private static void verificar(String nombre, boolean condicion) {
        if (condicion) {
            pasaron++;
            System.out.println("OK    " + nombre);
        } else {
            fallaron++;
            System.out.println("FALLO " + nombre);
        }
    }

    private static void probarLista() {
        Lista lista = new Lista();
        lista.agregar("A");
        lista.agregar("C");
        lista.agregarAlInicio("Z");
        lista.agregarEnPosicion(1, "B");
        verificar("lista: agregarEnPosicion en el medio", lista.buscarDato(1).equals("B"));
        verificar("lista: cuenta 4 elementos", lista.cuentaElementos() == 4);
        verificar("lista: contiene C", lista.contiene("C"));
        verificar("lista: no contiene Q", !lista.contiene("Q"));
        verificar("lista: eliminarEnPosicion del medio", lista.eliminarEnPosicion(1).equals("B"));
        verificar("lista: eliminarPrimero", lista.eliminarPrimero().equals("Z"));
        verificar("lista: eliminarUltimo", lista.eliminarUltimo().equals("C"));
        verificar("lista: queda 1 elemento", lista.cuentaElementos() == 1);
        lista.limpiar();
        verificar("lista: limpiar deja vacia", lista.cuentaElementos() == 0);
        verificar("lista: eliminar en lista vacia devuelve null", lista.eliminarPrimero() == null);
    }

    private static void probarCola() {
        Cola cola = new Cola();
        cola.encolar("uno");
        cola.encolar("dos");
        cola.encolar("tres");
        verificar("cola: peek es el primero", cola.peek().equals("uno"));
        verificar("cola: FIFO sale uno", cola.desencolar().equals("uno"));
        verificar("cola: FIFO sale dos", cola.desencolar().equals("dos"));
        verificar("cola: tamanio 1", cola.tamanio() == 1);
        cola.desencolar();
        verificar("cola: vacia al final", cola.esVacia());
        verificar("cola: desencolar vacia devuelve null", cola.desencolar() == null);
    }

    private static void probarPila() {
        Pila pila = new Pila();
        pila.apilar("uno");
        pila.apilar("dos");
        pila.apilar("tres");
        verificar("pila: peek es el ultimo", pila.peek().equals("tres"));
        verificar("pila: buscar tres en posicion 1", pila.buscar("tres") == 1);
        verificar("pila: buscar uno en posicion 3", pila.buscar("uno") == 3);
        verificar("pila: LIFO sale tres", pila.desapilar().equals("tres"));
        verificar("pila: LIFO sale dos", pila.desapilar().equals("dos"));
        verificar("pila: tamanio 1", pila.tamanio() == 1);
        pila.limpiar();
        verificar("pila: desapilar vacia devuelve null", pila.desapilar() == null);
    }

    private static void probarSistema() {
        SistemaDelCaso sistema = new SistemaDelCaso();
        VehiculoParqueadero carro = new VehiculoParqueadero("abc123", "Ana", "CARRO", "08:00");
        VehiculoParqueadero moto = new VehiculoParqueadero("XYZ12D", "Luis", "MOTO", "08:10");

        verificar("sistema: placa se guarda en mayusculas", carro.getPlaca().equals("ABC123"));
        verificar("sistema: registrar carro", sistema.registrarVehiculo(carro));
        verificar("sistema: registrar moto", sistema.registrarVehiculo(moto));
        verificar("sistema: no registra placa repetida",
                !sistema.registrarVehiculo(new VehiculoParqueadero("ABC123", "Otro", "CARRO", "09:00")));
        verificar("sistema: buscar por placa", sistema.buscarPorPlaca("xyz12d") == moto);
        verificar("sistema: cancelar pendiente", sistema.cancelarPendiente("ABC123"));
        verificar("sistema: cancelar otra vez falla", !sistema.cancelarPendiente("ABC123"));
        verificar("sistema: procesar queda la moto", sistema.procesarSiguiente() == moto);
        verificar("sistema: no hay mas pendientes", sistema.procesarSiguiente() == null);
        verificar("sistema: deshacer vuelve la moto", sistema.deshacerUltimo() == moto);
        verificar("sistema: deshacer sin historial devuelve null", sistema.deshacerUltimo() == null);
    }
}
