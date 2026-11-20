public class Cola {
    private Nodo frente;
    private Nodo fin;
    private int cantidad;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.cantidad = 0;
    }

    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        cantidad++;
    }

    public Object desencolar() {
        if (esVacia()) {
            return null;
        }
        Object dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        cantidad--;
        return dato;
    }

    public Object peek() {
        if (esVacia()) {
            return null;
        }
        return frente.getDato();
    }

    public int tamanio() {
        return cantidad;
    }

    public boolean esVacia() {
        return cantidad == 0;
    }

    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void limpiar() {
        frente = null;
        fin = null;
        cantidad = 0;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("La cola esta vacia");
            return;
        }
        Nodo actual = frente;
        int pos = 1;
        while (actual != null) {
            System.out.println(pos + ". " + actual.getDato());
            actual = actual.getSiguiente();
            pos++;
        }
    }
}
