public class CityData {
    // Attributes
    private String nameCity;
    private int distanceCity;

    // Constructor
    public CityData(String nameCity, int distanceCity) {
        this.nameCity = nameCity;
        this.distanceCity = distanceCity;
    }

    // Getter for cityName
    public String getCityName() {
        return nameCity;
    }

    // Setter for cityName
    public void setCityName(String nameCity) {
        this.nameCity = nameCity;
    }

    // Getter for cityDistance
    public int getCityDistance() {
        return distanceCity;
    }

    // Setter for cityDistance
    public void setCityDistance(int distanceCity) {
        this.distanceCity = distanceCity;
    }

    // Override toString() method for better readability
    @Override
    public String toString() {
        return "CityData{" +
                "cityName='" + nameCity + '\'' +
                ", cityDistance=" + distanceCity +
                '}';
    }
}
