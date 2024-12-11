import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetUp {

    public static int[][] array2DCsv(String filePath) {
        int[][] array; // 2D dizi tanımı
        
        try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {
            // İlk satırın uzunluğunu belirlemek için dosyayı bir kez okuyup satır sayısını buluyoruz
            String line;
            int rows = 0;
            int columns = 0;

            while ((line = read.readLine()) != null) {
                if (rows == 0) { // İlk satırın eleman sayısını sütun sayısı olarak belirle
                    columns = line.split(",").length;
                }
                rows++;
            }

            // 2D diziyi oluştur
            array = new int[rows - 1][columns - 1]; // İlk satır ve sütun atlandığı için boyutlar küçüldü

            // Dosyayı tekrar oku ve 2D diziye aktar
            BufferedReader read2 = new BufferedReader(new FileReader(filePath));
            int row = 0;
            
            while ((line = read2.readLine()) != null) {
                // İlk satırı atla
                if (row == 0) {
                    row++;
                    continue;
                }
            
                // Satırı ayır
                String[] value = line.split(",");
            
                // İlk sütunu atlayarak 2D diziye elemanları ekle
                for (int colum = 1; colum < value.length; colum++) { // col=1 ile ilk sütunu atlıyoruz
                    array[row - 1][colum - 1] = Integer.parseInt(value[colum]); // İlk sütun atlandığı için index ayarlaması
                }
                row++;
            }
            read2.close();

            return array;

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hata durumunda null döndür
        return null;
    }

    public enum City {
        ISTANBUL(0),
        ANKARA(1),
        IZMIR(2),
        BURSA(3),
        ADANA(4),
        GAZIANTEP(5),
        KONYA(6),
        DIYARBAKIR(7),
        ANTALYA(8),
        MERSIN(9),
        KAYSERI(10),
        URFA(11),
        MALATYA(12),
        SAMSUN(13),
        DENIZLI(14),
        BATMAN(15),
        TRABZON(16);
    
        // Enum özelliği
        private final int indexs;
    
        // Constructor
        City(int indexs) {
            this.indexs = indexs;
        }
    
        // Getter metodu
        public int getIndexs() {
            return indexs;
        }
    }

    public static Nodes[] generateN(int[][] distances, City[] cityList) {
        Nodes[] nodes = new Nodes[cityList.length]; // Tüm şehirler için Node dizisi

        // City dizisindeki şehirlerin indekslerini bir set içinde sakla
        Set<Integer> validIndices = new HashSet<>();
        for (City city : cityList) {
            validIndices.add(city.getIndexs()); // Şehrin indeksini sete ekle
        }
        
        for (int i = 0; i < cityList.length; i++) {
            City currentCity = cityList[i]; // Mevcut şehir
            int cityIndex = currentCity.getIndexs(); // Şehrin enum indeksini al
            String cityName = currentCity.name(); // Şehrin adını al
            
            // Komşuları bul
            ArrayList<CityData> neighboursList = new ArrayList<>();
            for (int j = 0; j < distances[cityIndex].length; j++) {
                if (i != j && distances[cityIndex][j] > 0 && distances[cityIndex][j] < 9999 && validIndices.contains(j)) {
                    // Komşu şehirse CityData oluştur
                    CityData neighbour = new CityData(City.values()[j].name(), distances[cityIndex][j]);
                    neighboursList.add(neighbour);
                }
            }
    
            // Komşuları bir diziye çevir
            CityData[] neighbours = neighboursList.toArray(new CityData[0]);
    
            // Node oluştur
            nodes[i] = new Nodes(cityName, neighbours);
        }
        
        return nodes; // Node dizisini döndür
    }
    

    public static void main(String[] args) {
        // Örnek kullanımı
        String file = "Turkish cities.csv"; // CSV dosyasının yolu
        int[][] distances = array2DCsv(file);  //distances holds the distances

        // 2D diziyi yazdır (kontrol amaçlı)
        if (distances != null) {
            System.out.println("CSV'den okunan 2D dizi:");
            for (int i = 0; i < distances.length; i++) {
                for (int j = 0; j < distances[i].length; j++) {
                    System.out.print(distances[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("CSV dosyası okunamadı.");
        }

        City[] cities = {City.ISTANBUL, City.ANKARA, City.IZMIR, City.URFA, City.BURSA};

        Nodes[] nodes = generateN(distances, cities);

        // Node'ları yazdır
        for (Nodes node : nodes) {
            System.out.println(node);
        }

        // Başlangıç ve hedef şehir
        String firstCity = "ISTANBUL";
        String lastCity = "BURSA";

        // En kısa yolu bul
        ShortestPathDFS.Result result = ShortestPathDFS.ShortestPath(nodes, firstCity, lastCity);

        // Sonuçları yazdır
        System.out.println("Shortest Path: " + result.shortestPath);
        System.out.println("Shortest Distance: " + result.shortestDistance);
    


    }








}
