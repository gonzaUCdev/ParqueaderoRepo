public class Pila {
    private Nodo tope;
    private int cantidad;

    public Pila() {
        this.tope = null;
        this.cantidad = 0;
    }

    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        cantidad++;
    }

    public Object desapilar() {
        if (esVacia()) {
            return null;
        }
        Object dato = tope.getDato();
        tope = tope.getSiguiente();
        cantidad--;
        return dato;
    }

    public Object peek() {
        if (esVacia()) {
            return null;
        }
        return tope.getDato();
    }

    public int tamanio() {
        return cantidad;
    }

    public boolean esVacia() {
        return cantidad == 0;
    }

    public boolean contiene(Object dato) {
        return buscar(dato) != -1;
    }

    public void limpiar() {
        tope = null;
        cantidad = 0;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("La pila esta vacia");
            return;
        }
        Nodo actual = tope;
        int pos = 1;
        while (actual != null) {
            System.out.println(pos + ". " + actual.getDato());
            actual = actual.getSiguiente();
            pos++;
        }
    }

    public int buscar(Object dato) {
        Nodo actual = tope;
        int pos = 1;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return pos;
            }
            actual = actual.getSiguiente();
            pos++;
        }
        return -1;
    }
}
