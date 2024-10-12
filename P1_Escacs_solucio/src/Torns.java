public class Torns<E> {

    // Inner class representing each node in the linked sequence
    private class NodeTorn {
        public E move;
        public NodeTorn next;

        public NodeTorn(E move, NodeTorn next) {
            this.move = move;
            this.next = next;
        }
    }

    private NodeTorn listTorns; // Header node that holds the address of the file
    private String fileName;     // File name stored in the header

    // Constructor that initializes the linked sequence with the file name
    public Torns(String fileName) {
        this.fileName = fileName;
        this.listTorns = new NodeTorn(null, null); // Empty header node
    }

    // Method to add a move at the end of the list
    public void addMove(E move) {
        NodeTorn current = listTorns; // Start from the header

        // Traverse the sequence to the end
        while (current.next != null) {
            current = current.next;
        }

        // Add the new move at the end of the sequence
        current.next = new NodeTorn(move, null);
    }

    // Method to retrieve and remove the first move after the header
    public E getFirstMove() {
        if (listTorns.next == null) {
            throw new NoSuchElementException("No more moves available.");
        }

        // Save the first move and update the header
        NodeTorn first = listTorns.next;
        listTorns.next = first.next; // Remove the first move from the sequence

        return first.move; // Return the removed move
    }

    // Method to save the sequence of moves to a file
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        // Write all moves starting from the first after the header
        NodeTorn current = listTorns.next;
        while (current != null) {
            writer.write(current.move.toString());
            writer.newLine();
            current = current.next;
        }

        writer.close();
    }

    // Method to load the sequence of moves from a file
    public void loadFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // Clear existing moves to load new ones
        listTorns.next = null;

        String line;
        while ((line = reader.readLine()) != null) {
            addMove((E) line); // Add each line as a new move (assuming E is String)
        }

        reader.close();
    }

    // Method to get the file name from the header
    public String getFileName() {
        return this.fileName;
    }
}
