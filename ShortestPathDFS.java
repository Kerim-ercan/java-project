import java.util.*;

public class ShortestPathDFS {

    public static class Result {
        List<String> shortestPath;
        int shortestDistance;

        public Result() {
            this.shortestPath = new ArrayList<>();
            this.shortestDistance = Integer.MAX_VALUE;
        }
    }
    static class StackFrame {
        String city;
        List<String> path;
        int distance;

        StackFrame(String city, List<String> path, int distance) {
            this.city = city;
            this.path = new ArrayList<>(path); // Derin kopya
            this.distance = distance;
        }
    }

    public static Result ShortestPath(Nodes[] nodes, String startCity, String endCity) {
        Result result = new Result();
        Stack_my<StackFrame> stack = new Stack_my<>();
        Set<String> visited = new HashSet<>();

        stack.push(new StackFrame(startCity, Arrays.asList(startCity), 0));

        while (!stack.isEmpty()) {
            StackFrame frame = stack.pop();
            String currentCity = frame.city;

            // Eğer hedef şehre ulaşırsak
            if (currentCity.equals(endCity)) {
                if (frame.distance < result.shortestDistance) {
                    result.shortestDistance = frame.distance;
                    result.shortestPath = new ArrayList<>(frame.path);
                }
                continue;
            }

            // Mevcut şehirden komşuları al
            Nodes currentNode = findNodeByName(nodes, currentCity);
            if (currentNode == null) continue;

            visited.add(currentCity);

            for (CityData neighbour : currentNode.getNeighbours()) {
                if (!visited.contains(neighbour.getCityName())) {
                    List<String> newPath = new ArrayList<>(frame.path);
                    newPath.add(neighbour.getCityName());
                    stack.push(new StackFrame(neighbour.getCityName(), newPath, frame.distance + neighbour.getCityDistance()));
                }
            }
        }

        // Eğer hedef şehre ulaşılamadıysa
        if (result.shortestPath.isEmpty()) {
            System.out.println("Hedef şehir '" + endCity + "' ulaşılamaz.");
            result.shortestDistance = -1;
        }

        return result;
    }

    private static Nodes findNodeByName(Nodes[] nodes, String cityName) {
        for (Nodes node : nodes) {
            if (node.getName().equals(cityName)) {
                return node;
            }
        }
        return null;
    }

    
}
