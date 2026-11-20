public class Lista {
    private Nodo primero;
    private Nodo ultimo;
    private int cantidad;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    public void agregar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        cantidad++;
    }

    public void agregarAlInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            primero = nuevo;
        }
        cantidad++;
    }

    public void agregarEnPosicion(int indice, Object dato) {
        if (indice < 0 || indice > cantidad) {
            throw new IndexOutOfBoundsException("Posicion no valida: " + indice);
        }
        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        if (indice == cantidad) {
            agregar(dato);
            return;
        }
        Nodo nuevo = new Nodo(dato);
        Nodo derecha = nodoEn(indice);
        Nodo izquierda = derecha.getAnterior();
        izquierda.setSiguiente(nuevo);
        nuevo.setAnterior(izquierda);
        nuevo.setSiguiente(derecha);
        derecha.setAnterior(nuevo);
        cantidad++;
    }

    private Nodo nodoEn(int indice) {
        Nodo actual;
        if (indice < cantidad / 2) {
            actual = primero;
            for (int i = 0; i < indice; i++) {
                actual = actual.getSiguiente();
            }
        } else {
            actual = ultimo;
            for (int i = cantidad - 1; i > indice; i--) {
                actual = actual.getAnterior();
            }
        }
        return actual;
    }
}
