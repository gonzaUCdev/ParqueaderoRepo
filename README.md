# Ejercicio 9 - Parqueadero

Aplicacion de consola en Java para controlar los vehiculos que entran a un parqueadero.
El identificador principal de cada vehiculo es la placa.

## Estructuras usadas

- **Lista** (`Lista.java`): guarda todos los vehiculos registrados. Es la lista general, se puede recorrer hacia adelante y hacia atras y buscar por placa.
- **Cola** (`Cola.java`): guarda los vehiculos pendientes por procesar. Sale primero el que llego primero (FIFO).
- **Pila** (`Pila.java`): guarda el historial de los vehiculos procesados. Sale primero el ultimo que se proceso (LIFO), por eso sirve para deshacer.

## Archivos

| Archivo | Para que sirve |
|---|---|
| `Nodo.java` | Nodo con dato y referencias anterior y siguiente |
| `Lista.java` | Lista doblemente enlazada |
| `Cola.java` | Cola con frente y fin |
| `Pila.java` | Pila con tope |
| `VehiculoParqueadero.java` | Clase del caso (placa, propietario, tipo, hora de ingreso) |
| `SistemaDelCaso.java` | Logica del sistema y menu de consola |
| `PruebasParqueadero.java` | Pruebas automaticas de las estructuras y del sistema |

## Como ejecutar

```
javac -d out src/*.java
java -cp out SistemaDelCaso
```

Para correr las pruebas:

```
java -cp out PruebasParqueadero
```

## Reglas del sistema

- La placa debe tener formato ABC123 o ABC12D. Se guarda en mayusculas.
- El tipo solo puede ser CARRO o MOTO.
- No se puede registrar dos veces la misma placa.
- Cancelar un pendiente se hace con una cola auxiliar, porque la cola no permite borrar desde el medio.
- Deshacer el ultimo procesamiento saca el vehiculo de la pila y lo vuelve a encolar al final de pendientes.
