package Assignment;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hashing_Search_Array {

    public static void main(String[] args) {
        try {
            CSVReader reader = new CSVReader();
            String[] urlArray = reader.readAndPrintURLs2(10); // Assuming readData returns an array of URLs

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
                } else if (index < 0 || index > urlArray.length) {
                    System.out.println("\n======================================================================");
                    System.out.println(" Error Message : The position " + index + " of URL is out of bound ");
                    System.out.println("======================================================================\n");
                } else {
                    long startTime = System.nanoTime();
                    String value = urlArray[index-1];
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
