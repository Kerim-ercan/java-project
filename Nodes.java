public class Nodes {
    // Attributes
    private String name;
    private CityData[] neighbours;

    // Constructor
    public Nodes(String name, CityData[] neighbours) {
        this.name = name;
        this.neighbours = neighbours;
    }


    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    

    // Setter for neighbours
    public void setNeighbours(CityData[] neighbours) {
        this.neighbours = neighbours;
    }
    // Getter for neighbours
    public CityData[] getNeighbours() {
        return neighbours;
    }

    // Override toString() method for better readability
    @Override
    public String toString() {
        StringBuilder stringB = new StringBuilder("Node{name='" + name + "', neighbours=[");
        if (neighbours != null) {
            for (CityData neighbour : neighbours) {
                stringB.append(neighbour.toString()).append(", ");
            }
            if (neighbours.length > 0) stringB.setLength(stringB.length() - 2); // Remove last comma
        }
        stringB.append("]}");
        return stringB.toString();
    }
}
