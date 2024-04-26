package Assignment;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class Sequential_Search {
    private static String[] urlArray;
    private static LinkedList<String> urlLinkedList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            CSVReader reader = new CSVReader();
            readAndPrintURLs(reader.getFilePath(), 100000);

            int totalUrls = urlCount();
            System.out.println("\n=======================================");
            System.out.println("         Total URLs: " + totalUrls);
            System.out.println("=======================================\n");

            boolean continueSearching = true;
            String searchUrl;
            int numSearches = 0;
            long totalElapsedTimeArray = 0;
            long totalElapsedTimeLinkedList = 0;
            long maxElapsedTimeArray = Long.MIN_VALUE;
            long maxElapsedTimeLinkedList = Long.MIN_VALUE;

            do {
                try {
                    System.out.print("\nKindly enter the URL to retrieve its position (enter 'exit' to terminate): ");
                    searchUrl = scanner.nextLine();
                    if (searchUrl.equalsIgnoreCase("exit")) {
                        continueSearching = false;
                        break;
                    } else {
                        numSearches++;
                        int position = searchByUrlArray(searchUrl, urlArray);
                        if (position == -1) {
                            System.out.println("\n======================================================");
                            System.out.println(" Error Message : The URL is not found in the data set");
                            System.out.println("======================================================\n");
                        } else {
                        	long startArray = System.nanoTime();
                        	int positionArray = searchByUrlArray(searchUrl, urlArray);
                        	long finishArray = System.nanoTime();
                        	long elapsedTimeArray = finishArray - startArray;

                        	long startLinkedList = System.nanoTime();
                        	int positionLinkedList = searchByUrlLinkedList(searchUrl, urlLinkedList);
                        	long finishLinkedList = System.nanoTime();
                        	long elapsedTimeLinkedList = finishLinkedList - startLinkedList;


                            // Display Result
                            System.out.println("\n================================================================================================");
                            System.out.println(" Array: ");
                            System.out.println("   URL          : " + searchUrl);
                            System.out.println("   Position     : " + positionArray);
                            System.out.println("   Elapsed Time : " + elapsedTimeArray + " ns\n");
                            System.out.println(" Linked List: ");
                            System.out.println("   URL          : " + searchUrl);
                            System.out.println("   Position     : " + positionLinkedList);
                            System.out.println("   Elapsed Time : " + elapsedTimeLinkedList + " ns");
                            System.out.println("===============================================================================================\n");

                            // Calculation
                            totalElapsedTimeArray += elapsedTimeArray;
                            totalElapsedTimeLinkedList += elapsedTimeLinkedList;
                            maxElapsedTimeArray = Math.max(maxElapsedTimeArray, elapsedTimeArray);
                            maxElapsedTimeLinkedList = Math.max(maxElapsedTimeLinkedList, elapsedTimeLinkedList);
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n============================================");
                    System.out.println("   Error Message : Invalid Input Format !    ");
                    System.out.println("=============================================\n");
                    scanner.nextLine();
                }

            } while (continueSearching);

            if(numSearches>0) {
	            long avgElapsedTimeArray = totalElapsedTimeArray / numSearches;
	            long avgElapsedTimeLinkedList = totalElapsedTimeLinkedList / numSearches;
	            
	            System.out.println("\n================================================================================================");
	            System.out.println(" Average time (Average Case)  ");
	            System.out.println("   Array         : " + avgElapsedTimeArray + " ns");
	            System.out.println("   Linked List   : " + avgElapsedTimeLinkedList + " ns\n");
	
	            System.out.println(" Max time (Worst Case) ");
	            System.out.println("   Array         : " + maxElapsedTimeArray + " ns");
	            System.out.println("   Linked List   : " + maxElapsedTimeLinkedList + " ns");
	            System.out.println("===============================================================================================\n");
	            
            	}else {
                	System.out.println("\n================================");
                    System.out.println("   No searches were performed.");
                    System.out.println("=================================");
                }
            System.out.println("Console terminated ......");
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] readAndPrintURLs(String filePath, int numURLs) throws IOException {
        List<String> urlList = new ArrayList<>();
        urlLinkedList = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int count = 1;
            System.out.println("\n==================================");
            System.out.println("          Index\tURL");
            System.out.println("==================================");
            while ((line = reader.readLine()) != null && count <= numURLs) {
                // Print the index and URL
                System.out.println(count + "\t" + line.trim());
                urlList.add(line.trim());
                urlLinkedList.add(line.trim());
                count++;
            }

            urlArray = urlList.toArray(new String[0]);
            String[] urls = new String[urlList.size()];
            return urlList.toArray(urls);
        }
    }

    public static int urlCount() {
        if (urlArray == null && urlLinkedList == null) {
            return 0;
        }
        return urlArray.length;
    }

    private static int searchByUrlArray(String searchTerm, String[] urlArray) {
        for (int i = 0; i < urlArray.length; i++) {
            if (urlArray[i].equals(searchTerm)) {
                return i+1 ; 
            }
        }
        return -1; // URL not found
    }
    
    private static int searchByUrlLinkedList(String searchTerm, LinkedList<String> urlLinkedList) {
	    for (int i = 0; i < urlLinkedList.size(); i++) {
	        if (urlLinkedList.get(i).equals(searchTerm)) {
	            return i + 1; // Position is 1-based
	        }
	    }
	    return -1; // URL not found
	}

}
