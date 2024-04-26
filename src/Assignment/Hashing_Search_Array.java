package Assignment;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hashing_Search_Array {

    public static void main(String[] args) {
        try {
            CSVReader reader = new CSVReader();
            String[] urlArray = reader.printAndReturnURLs(10); 

            int totalUrls = urlArray.length;
            System.out.println("\n=======================================");
            System.out.println("         Total URLs: " + totalUrls);
            System.out.println("=======================================\n");

            // Search by hashing
            searchByHashing(urlArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchByHashing(String[] urlArray) {
        Scanner sc = new Scanner(System.in);
        String inputUrl;
        boolean continueLoop = true;
        long totalElapsedTimeHash = 0;
        long maxElapsedTimeHash = 0;
        int timeToSearch = 0;

        do {
            try {
                System.out.print("\nKindly enter the URL to retrieve its position (enter 'exit' to terminate): ");
                inputUrl = sc.nextLine();
                if (inputUrl.equalsIgnoreCase("exit")) {
                    continueLoop = false;
                } else {
                    int position = searchInArray(inputUrl, urlArray);
                    if (position == -1) {
                        System.out.println("\n=======================================================");
                        System.out.println(" Error Message : The URL is not found in the data set");
                        System.out.println("=======================================================\n");
                    } else {
                        long startTime = System.nanoTime();
                        long endTime = System.nanoTime();
                        long elapsedTimeHash = endTime - startTime; // Calculate elapsed time for this search

                        totalElapsedTimeHash += elapsedTimeHash;
                        maxElapsedTimeHash = Math.max(maxElapsedTimeHash, elapsedTimeHash);
                        System.out.println("\n===========================================================");
                        System.out.println("URL          : " + inputUrl);
                        System.out.println("Position     : " + position);
                        System.out.println("Elapsed Time : " + elapsedTimeHash + " ns");
                        System.out.println("===========================================================");
                        timeToSearch++;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\n============================================");
                System.out.println("   Error Message : Invalid Input Format !    ");
                System.out.println("=============================================");
                sc.nextLine();
            }
        } while (continueLoop);

        if (timeToSearch > 0) {
            long avgElapsedTimeHash = totalElapsedTimeHash / timeToSearch;
            System.out.println("\n================================================================================================");
            System.out.println(" Search By Hashing\n");
            System.out.println(" Average time (Average Case) : " + avgElapsedTimeHash + " ns");
            System.out.println(" Max time (Worst Case)       : " + maxElapsedTimeHash + " ns");
            System.out.println("===============================================================================================\n");
        } else {
        	System.out.println("\n================================");
            System.out.println("   No searches were performed.");
            System.out.println("=================================");
        }

        System.out.println("Console terminated ......");
        sc.close();
    }
    
    private static int searchInArray(String targetUrl, String[] urlArray) {
        for (int i = 0; i < urlArray.length; i++) {
            if (urlArray[i].equals(targetUrl)) {
                return i + 1; // Position starts from 1
            }
        }
        return -1; // URL not found
    }
}
