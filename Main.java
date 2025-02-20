import java.util.Scanner;
import java.util.EmptyStackException;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BrowserNavigation browser = new BrowserNavigation(10);
        
        boolean running = true;
        while (running){
            System.out.println(browser.getSize());
            System.out.println("\nBrowser Navigation System");
            System.out.println("1. Visit Website");
            System.out.println("2. Go Back");
            System.out.println("3. Go Forward");
            System.out.println("4. Show Browsing History");
            System.out.println("5. Clear Browsing History");
            System.out.println("6. Close Browser");
            System.out.println("7. Restore Last Session");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
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
                        System.out.println(browser.showHistory());
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
                System.out.println("No previous/forward page available.");
            }
        }

        scanner.close();
        System.out.println("Browser closed.");
    }
}