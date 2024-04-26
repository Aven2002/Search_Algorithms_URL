package Assignment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Binary_Search {
    private static String[] urlArray;
    private static LinkedList<String> urlLinkedList;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            CSVReader reader = new CSVReader();
            readAndPrintURLs(reader.getFilePath(),100000);

            int totalUrls = urlCount();
            System.out.println("\n=======================================");
            System.out.println("         Total URLs: " + totalUrls);
            System.out.println("=======================================\n");

            boolean continueSearching = true;
            int numSearches = 0;
            int position = 0;
            long totalElapsedTimeArray = 0;
            long totalElapsedTimeLinkedList = 0;
            long maxElapsedTimeArray = Long.MIN_VALUE;
            long maxElapsedTimeLinkedList = Long.MIN_VALUE;

            do {
                numSearches++;
                try {
                    System.out.print("Kindly enter the URL index to retrieve (enter -1 to terminate): ");
                    position = scanner.nextInt();
                    scanner.nextLine();
                    if (position == -1) {
                        continueSearching = false;
                    } else if (position < 1 || position > totalUrls) {
                        System.out.println("\n======================================================================");
                        System.out.println(" Error Message : The position " + position + " of URL is out of bound ");
                        System.out.println("======================================================================\n");
                    } else {
                        // Declaration
                        long startArray = System.nanoTime();
                        String urlArrayResult = binarySearchArray(position);
                        long finishArray = System.nanoTime();
                        long elapsedTimeArray = finishArray - startArray;

                        long startLinkedList = System.nanoTime();
                        String urlLinkedListResult = binarySearchLinkedList(position);
                        long finishLinkedList = System.nanoTime();
                        long elapsedTimeLinkedList = finishLinkedList - startLinkedList;

                        if (urlArrayResult != null && urlLinkedListResult != null) {

                            // Calculation
                            totalElapsedTimeArray += elapsedTimeArray;
                            totalElapsedTimeLinkedList += elapsedTimeLinkedList;
                            maxElapsedTimeArray = Math.max(maxElapsedTimeArray, elapsedTimeArray);
                            maxElapsedTimeLinkedList = Math.max(maxElapsedTimeLinkedList, elapsedTimeLinkedList);

                            // Display Result
                            System.out.println("\n================================================================================================");
                            System.out.println(" Array: ");
                            System.out.println("   Index        : " + position);
                            System.out.println("   URL          : " + urlArrayResult);
                            System.out.println("   Elapsed Time : " + elapsedTimeArray + " ns\n");
                            System.out.println(" Linked List: ");
                            System.out.println("   Index        : " + position);
                            System.out.println("   URL          : " + urlLinkedListResult);
                            System.out.println("   Elapsed Time : " + elapsedTimeLinkedList + " ns");
                            System.out.println("===============================================================================================\n");
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n============================================");
                    System.out.println("   Error Message : Invalid Input Format !    ");
                    System.out.println("=============================================\n");
                    scanner.nextLine();
                }

            } while (continueSearching);
            
            long avgElapsedTimeArray = totalElapsedTimeArray / numSearches;
            long avgElapsedTimeLinkedList = totalElapsedTimeLinkedList / numSearches;
            
            System.out.println("\n================================================================================================");
            System.out.println(" Average time (Average Case)  ");
            System.out.println("   Array         : " + avgElapsedTimeArray + " ns");
            System.out.println("   Linked List   : " + avgElapsedTimeLinkedList + " ns\n");

            System.out.println(" Max time (Worst Case) ");
            System.out.println("   Array         : " + maxElapsedTimeArray + " ns");
            System.out.println("   Linked List   : " + maxElapsedTimeLinkedList + " n");
            System.out.println("===============================================================================================\n");
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
            // Skip the first line (title)
            reader.readLine();
            
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

    public static String binarySearchArray(int position) {
        if (urlArray == null || position < 1 || position > urlArray.length) {
            return null;
        }

        int left = 0;
        int right = urlArray.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == position - 1) {
                return urlArray[mid];
            } else if (mid < position - 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Target not found
    }

    public static String binarySearchLinkedList(int position) {
        if (urlLinkedList == null || position < 1 || position > urlLinkedList.size()) {
            return null;
        }

        int left = 0;
        int right = urlLinkedList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == position - 1) {
                return urlLinkedList.get(mid);
            } else if (mid < position - 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Target not found
    }
}