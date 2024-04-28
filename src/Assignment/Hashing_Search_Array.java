import java.util.Scanner;

public class Hashing_Search_Array {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] hashTable = new String[100000]; // Size of the hash table
        int totalUrls = 0;

        System.out.println("Enter URLs (type 'exit' to finish):");
        String inputUrl;

        while (true) {
            inputUrl = scanner.nextLine();
            if (inputUrl.equalsIgnoreCase("exit")) {
                break;
            }
            insertIntoHashTable(inputUrl, hashTable);
            totalUrls++;
        }

        System.out.println("\n=======================================");
        System.out.println("         Total URLs: " + totalUrls);
        System.out.println("=======================================\n");

        searchByHashing(scanner, hashTable);
        scanner.close();
    }

    public static void insertIntoHashTable(String url, String[] hashTable) {
        // Calculate hash index
        int index = Math.abs(url.hashCode()) % hashTable.length;

        // Linear probing for collision resolution
        while (hashTable[index] != null) {
            index = (index + 1) % hashTable.length;
        }

        hashTable[index] = url;
    }

    public static void searchByHashing(Scanner scanner, String[] hashTable) {
        while (true) {
            System.out.print("Enter URL to search (type 'exit' to finish): ");
            String inputUrl = scanner.nextLine();
            if (inputUrl.equalsIgnoreCase("exit")) {
                break;
            }

            int index = Math.abs(inputUrl.hashCode()) % hashTable.length;
            while (hashTable[index] != null && !hashTable[index].equals(inputUrl)) {
                index = (index + 1) % hashTable.length;
            }

            if (hashTable[index] != null && hashTable[index].equals(inputUrl)) {
                System.out.println("URL found at index " + index);
            } else {
                System.out.println("URL not found.");
            }
        }
    }
}
