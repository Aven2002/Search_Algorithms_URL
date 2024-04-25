package Assignment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CSVReader {
	private int urlCount;
	private final String filePath = "src/url.csv"; 
    
    public void readAndPrintURLs(int numURLs) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (title)
            reader.readLine();
            
            String line;
            urlCount = 1;
            System.out.println("\n==================================");
            System.out.println("          Index\tURL");
            System.out.println("==================================");
            while ((line = reader.readLine()) != null && urlCount <= numURLs) {
                // Print the index and URL
                System.out.println(urlCount + "\t" + line.trim());
                urlCount++;
            }
        }
    }
    
    public int getUrlCount() {
       return urlCount-1;
    }
    
    public String getFilePath() {
    	return filePath;
    }
    
    public Map<String, String> readData(int numURLs) throws IOException {
        Map<String, String> urlMap = new LinkedHashMap<>(); // LinkedHashMap to preserve insertion order

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (title)
            reader.readLine();

            String line;
            int count = 1;
            while ((line = reader.readLine()) != null && count <= numURLs) {
                // Store the index and URL in the map
                urlMap.put(String.valueOf(count), line.trim());
                count++;
            }
        }

        return urlMap;
    }
}
