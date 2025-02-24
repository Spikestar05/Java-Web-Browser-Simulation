import java.awt.Desktop;
import java.net.URI;
import java.io.*;
import java.util.EmptyStackException;

public class BrowserNavigation {
    //2 stacks for forward and back stacks, one queue to store history, and a string to store the current open page.
    private BrowserStack<String> backStack;
    private BrowserStack<String> forwardStack;
    private BrowserQueue<String> history;
    private String currentPage;

    //Constructor to initialize the navigation system with a starting history capacity
    public BrowserNavigation(int historyCapacity){
        backStack = new BrowserStack<>();
        forwardStack =  new BrowserStack<>();
        history = new BrowserQueue<>(historyCapacity);
        currentPage = null;
    }

    //method to visit a website
    public String visitWebsite(String url){
        if(currentPage != null){
            backStack.push(currentPage);//saves current webpage before navigating forward
        }
        
        //clear forward stack since a new branch is starting, add url to history, set url to current page, open url in webbrowser.
        forwardStack = new BrowserStack<>();
        history.enqueue(url);
        currentPage = url;
        openWebPage(url);
        return "now at " + currentPage;
    }

    //method to go back to previous page
    public String goBack(){
        if(backStack.size() <= 0){
            return "No previous page available";//no previous page to go to
        }

        //saves current page to forward stack, gets previous page from backward stack, and opens page in webrowser.
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        openWebPage(currentPage);
        return "now at " + currentPage;
    }

    //method to go to next page
    public String goForward(){
        if(forwardStack.size() <= 0){
            return "No forward page available";//no page in front to go to
        }

        //saves current page to back stack, gets page from forard stack, and opens page in webrowser.
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        openWebPage(currentPage);
        return "now at " + currentPage;
    }

    //method to open url in webrowser
    private void openWebPage(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Opening web page not supported on this system. URL: " + url);
            }
        } catch (Exception e) {
            System.out.println("Error opening web page: " + e.getMessage());
        }
    }
    
    //method to display history
    public void showHistory(){
        if(history.isEmpty()){
            throw new EmptyStackException();//throws exception if no history found
        }
        
        //print each url in reverse so it shows as most recent to least recent
        for (int i = history.size() - 1; i >= 0; i--){
            System.out.println(history.elementAt(i));
        }
    }

    //method to clear history
    public String clearHistory(){
        //reset history queue, forward stack, backward stack, and set current page to null.
        history = new BrowserQueue<>(10);
        backStack = new BrowserStack<>();
        forwardStack =  new BrowserStack<>();
        currentPage = null;
        return "Browser history cleared.";
    }

    //Method to close the browser and save the session state to a file
    public String closeBrowser(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("session_data.txt"))){
            writer.println("C:" + currentPage);//save current page
            
            //save back stack
            StackIterator<String> backIterator = backStack.getStackIterator();
            while(backIterator.hasNext()){
                writer.println("B:" + backIterator.next());
            }

            //save forward stack
            StackIterator<String> frontIterator = forwardStack.getStackIterator();
            while(frontIterator.hasNext()){
                writer.println("F:" + frontIterator.next());
            }
            
            //save history
            for (int i = history.size() - 1; i >= 0; i--){
                writer.println("H:" + (history.elementAt(i)));
            }

            return "Broser session saved to session_data.txt.";
        }catch(IOException e){
            return "Error saving session.";
        }
    }

    //method to restore history.
    public String restoreLastSession(){
        File file = new File("session_data.txt");
        if(!file.exists()){
            return "No previous session found.";//no session file found
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            backStack = new BrowserStack<>();
            forwardStack = new BrowserStack<>();
            history = new BrowserQueue<>(10);

            BrowserStack<String> tempForward = new BrowserStack<>();
            BrowserStack<String> tempBack = new BrowserStack<>();
            BrowserStack<String> tempHistory = new BrowserStack<>();

            String line;
            while ((line = reader.readLine()) != null){
                if(line.startsWith("C:")){
                    currentPage = line.substring(2);//sets current page
                }else if(line.startsWith("B:")){
                    tempBack.push(line.substring(2));//restores back stack
                }else if(line.startsWith("F:")){
                    tempForward.push(line.substring(2));//restores forward stack
                }else if(line.startsWith("H:")){
                    tempHistory.push(line.substring(2));//restores history
                }
            }
            
            //restore backward and forward stack and history in correct order
            while (!tempForward.isEmpty()){
                forwardStack.push(tempForward.pop());
            }
            while (!tempBack.isEmpty()){
                backStack.push(tempBack.pop());
            }
            while (!tempHistory.isEmpty()){
                history.enqueue(tempHistory.pop());
            }
            
            return "Previous session restored, now at: " + currentPage;
        }catch (IOException e){
            return "Error restoring session.";
        }
    }

    //method to get current page.
    public String getCurrectPage(){
        if(currentPage == null){
            return "No page open";
        } else{
            return "Now at " + currentPage;
        }
    }
}
