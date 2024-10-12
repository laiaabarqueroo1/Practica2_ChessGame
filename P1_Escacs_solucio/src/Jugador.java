import java.util.ArrayList;

class Jugador<E extends ItipoPieza> {
    private NodePieza piezasVivas;

    private class NodePieza{
        public E pieza;
        public NodePieza seguent;
        public NodePieza(E pieza, NodePieza seguent){
            this.pieza = pieza;
            this.seguent = seguent;
        }
    }

    public Jugador(ArrayList<E> piezasInicials){
        piezasVivas = null;
        for (int i = piezasInicials.size() - 1; i >= 0; i--) {
        }
        piezasVivas = new NodePieza(piezasInicials.get(i), piezasVivas);
    }

    public ArrayList<E> getPiezasVivas() {
        ArrayList<E> listaPiezas = new ArrayList<>();
        NodePieza actual = piezasVivas;

        while (actual != null) {
            listaPiezas.add(actual.pieza);
            actual = actual.seguent;
        }

        return listaPiezas; // Devolvemos el ArrayList con las piezas vivas
    }


    // Método para mover una pieza usando la posición anterior
    public void moverPieza(int columnaAnterior, int filaAnterior, int nuevaColumna, int nuevaFila) {
        if (this.buscarEnPosicion(nuevaFila, nuevaColumna) != null)
            throw new RuntimeException("Posició ocupada per una peça del mateix jugador");

        NodePieza actual = piezasVivas;
        for (NodePieza actual = piezasVivas; actual != null; actual = actual.seguent) {
            if (actual.pieza.getFila() == filaAnterior && actual.pieza.getColumna() == columnaAnterior){
                actual.pieza.setPosicion(nuevaFila, nuevaColumna);
                System.out.println("Peça moguda");
                return;
            }
            actual = actual.seguent;
        }
        if( item == null)
            throw new RuntimeException("Peça no trobada fila:" + filaAnterior + " columna:" + columnaAnterior);
    }

    // Método para buscar en una posición específica
    private E buscarEnPosicion(int nuevaFila, int nuevaColumna) {
        NodePieza actual = piezasVivas;

        for (NodePieza actual = piezasVivas; actual != null; actual = actual.seguent) {
            if (actual.pieza.getFila() == nuevaFila && pieza.getColumna() == nuevaColumna) {
                return actual.pieza;
            }
            actual = actual.seguent;
        }
        return null;
    }


    // Método para buscar y eliminar una pieza en una posición específica
    public boolean eliminarPiezaEnPosicion(int columna, int fila) throws FiJocException {
        if (piezasVivas == null) {
            // La lista está vacía, no hay piezas que eliminar
            return false;
        }

        // Si el primer nodo (la cabeza de la lista enlazada) es la pieza que queremos eliminar
        if (piezasVivas.pieza.getColumna() == columna && piezasVivas.pieza.getFila() == fila) {
            E piezaAEliminar = piezasVivas.pieza;
            piezasVivas = piezasVivas.seguent;

            if (piezaAEliminar.fiJoc()) {
                throw new FiJocException();
            }

            System.out.println("Peça eliminada.");
            return true;
        }

        // Recorremos la lista enlazada a partir del segundo nodo
        NodePieza actual = piezasVivas;
        while (actual.seguent != null) {
            if (actual.seguent.pieza.getColumna() == columna && actual.seguent.pieza.getFila() == fila) {
                E piezaAEliminar = actual.seguent.pieza;
                actual.seguent = actual.seguent.seguent;

                if (piezaAEliminar.fiJoc()) {
                    // Si la pieza eliminada es el rey, lanzamos la excepción
                    throw new FiJocException();
                }
                System.out.println("Peça eliminada.");
                return true;
            }
            actual = actual.seguent;
        }
        return false;
    }
}
