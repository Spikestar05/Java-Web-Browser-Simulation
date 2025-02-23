import java.awt.Desktop;
import java.net.URI;
import java.io.*;
import java.util.EmptyStackException;

public class BrowserNavigation {
    private BrowserStack<String> backStack;
    private BrowserStack<String> forwardStack;
    private BrowserQueue<String> history;
    private String currentPage;

    public BrowserNavigation(int historyCapacity){
        backStack = new BrowserStack<>();
        forwardStack =  new BrowserStack<>();
        history = new BrowserQueue<>(historyCapacity);
        currentPage = null;
    }

    public String visitWebsite(String url){
        if(currentPage != null){
            backStack.push(currentPage);
        }
        
        forwardStack = new BrowserStack<>();
        history.enqueue(url);
        currentPage = url;
        openWebPage(url);
        return "now at " + currentPage;
    }

    public String goBack(){
        if(backStack.size() <= 0){
            return "No previous page available";
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        openWebPage(currentPage);
        return "now at " + currentPage;
    }

    public String goForward(){
        if(forwardStack.size() <= 0){
            return "No forward page available";
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        openWebPage(currentPage);
        return "now at " + currentPage;
    }

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
    
    public void showHistory(){
        if(history.isEmpty()){
            throw new EmptyStackException();
        }
        
        for (int i = history.size() - 1; i >= 0; i--){
            System.out.println(history.elementAt(i));
        }
    }

    public String clearHistory(){
        history = new BrowserQueue<>(10);
        backStack = new BrowserStack<>();
        forwardStack =  new BrowserStack<>();
        currentPage = null;
        return "Browser history cleared.";
    }

    public String closeBrowser(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("session_data.txt"))){
            writer.println("C:" + currentPage);
            
            StackIterator<String> backIterator = backStack.getStackIterator();
            while(backIterator.hasNext()){
                writer.println("B:" + backIterator.next());
            }

            StackIterator<String> frontIterator = forwardStack.getStackIterator();
            while(frontIterator.hasNext()){
                writer.println("F:" + frontIterator.next());
            }
    
            for (int i = history.size() - 1; i >= 0; i--){
                writer.println("H:" + (history.elementAt(i)));
            }

            return "Broser session saved to session_data.txt.";
        }catch(IOException e){
            return "Error saving session.";
        }
    }

    public String restoreLastSession(){
        File file = new File("session_data.txt");
        if(!file.exists()){
            return "No previous session found.";
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
                    currentPage = line.substring(2);
                }else if(line.startsWith("B:")){
                    tempBack.push(line.substring(2));
                }else if(line.startsWith("F:")){
                    tempForward.push(line.substring(2));
                }else if(line.startsWith("H:")){
                    tempHistory.push(line.substring(2));
                }
            }
            
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

    public String getCurrectPage(){
        if(currentPage == null){
            return "No page open";
        } else{
            return "Now at " + currentPage;
        }
    }
}
