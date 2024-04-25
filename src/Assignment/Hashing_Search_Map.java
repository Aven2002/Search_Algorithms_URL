package Assignment;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Hashing_Search_Map {

    public static void main(String[] args) {

    	 try {
    	        CSVReader reader = new CSVReader();
    	        Map<String, String> map = reader.readData(1000); 

    	        // Displaying the URLs
    	        System.out.println("==================================");
    	        System.out.println("          Index\tURL");
    	        System.out.println("==================================");
    	        for (Map.Entry<String, String> entry : map.entrySet()) {
    	            System.out.println(entry.getKey() + "\t" + entry.getValue());
    	        }

    	        int totalUrls = map.size();
                System.out.println("\n=======================================");
                System.out.println("         Total URLs: " + totalUrls);
                System.out.println("=======================================\n");
    	        // Search by hashing
    	        searchByHashing(map);
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    }

    public static void searchByHashing(Map<String, String> map) {
        Scanner sc = new Scanner(System.in);
        boolean continueLoop = true;
        long totalElapsedTimeHash = 0;
        long maxElapsedTimeHash = 0;
        int timeToSearch = 0;

        do {
            timeToSearch++;
            try {
                System.out.print("\nKindly enter the URL index to retrieve (enter -1 to terminate): ");
                int index = sc.nextInt();
                if (index == -1) {
                    continueLoop = false;
                } else if (index < 1 || index > map.size()) {
                    System.out.println("\n======================================================================");
                    System.out.println(" Error Message : The position " + index + " of URL is out of bound ");
                    System.out.println("======================================================================\n");
                } else {
                    long startTime = System.nanoTime();
                    String key = String.valueOf(index);
                    String value = map.get(key);
                    long endTime = System.nanoTime();
                    long elapsedTimeHash = endTime - startTime; // Calculate elapsed time for this search

                    if (value != null) {
                        totalElapsedTimeHash += elapsedTimeHash;
                        maxElapsedTimeHash = Math.max(maxElapsedTimeHash, elapsedTimeHash);
                        System.out.println("\n===========================================================");
                        System.out.println("URL          : " + value);
                        System.out.println("Elapsed Time : " + elapsedTimeHash + " ns");
                        System.out.println("===========================================================");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\n============================================");
                System.out.println("   Error Message : Invalid Input Format !    ");
                System.out.println("=============================================");
                sc.nextLine();
            }
        } while (continueLoop);

        long avgElapsedTimeHash = totalElapsedTimeHash / timeToSearch;

        System.out.println("\n================================================================================================");
        System.out.println(" Search By Hashing\n");
        System.out.println(" Average time (Average Case) : " + avgElapsedTimeHash + " ns");
        System.out.println(" Max time (Worst Case)       : " + maxElapsedTimeHash + " ns");
        System.out.println("===============================================================================================\n");
        System.out.println("Console terminated ......");
        sc.close();
    }

}
