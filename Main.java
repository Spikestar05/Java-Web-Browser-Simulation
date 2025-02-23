import java.util.Scanner;
import java.util.EmptyStackException;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BrowserNavigation browser = new BrowserNavigation(10);
        boolean running = true;
        while (running){
            System.out.println("\nBrowser Navigation System");
            System.out.println("1. Visit Website");
            System.out.println("2. Go Back");
            System.out.println("3. Go Forward");
            System.out.println("4. Show Browsing History");
            System.out.println("5. Clear Browsing History");
            System.out.println("6. Close Browser (saves session to file)");
            System.out.println("7. Restore Last Session");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            while(!scanner.hasNextInt()){
                System.out.println("Invalid input. Please enter a number");
                scanner.nextLine();
            }

            int choice = scanner.nextInt();
            scanner.nextLine();
            
            try{
                switch (choice){
                    case 1:
                        System.out.print("Enter website URL: ");
                        String url = scanner.nextLine();
                        System.out.println(browser.visitWebsite(url));
                        break;
                    case 2:
                        System.out.println(browser.goBack());
                        break;
                    case 3:
                        System.out.println(browser.goForward());
                        break;
                    case 4:
                        browser.showHistory();
                        break;
                    case 5:
                        System.out.println(browser.clearHistory());
                        break;
                    case 6:
                        System.out.println(browser.closeBrowser());
                        break;
                    case 7:
                        System.out.println(browser.restoreLastSession());
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (EmptyStackException e){
                System.out.println("No history available.");
            }
        }

        scanner.close();
        System.out.println("Browser closed.");
    }
}