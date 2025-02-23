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
        return "now at " + currentPage;
    }

    public String goBack(){
        System.out.println("Forward stack is T/F: ");
        System.out.println(forwardStack.size());
        System.out.println("\n");
        if(backStack.size() <= 0){
            return "No previous page available";
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        return "now at " + currentPage;
    }

    public String goForward(){
        System.out.println("Forward stack is T/F: ");
        System.out.println(forwardStack.size());
        System.out.println("\n");
        if(forwardStack.size() <= 0){
            return "No forward page available";
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        return "now at " + currentPage;
    }

    public String showHistory(){
        if(history.isEmpty()){
            throw new EmptyStackException();
        }
        StringBuilder sb = new StringBuilder("Browser History:\n");

        for(String url : history){
            sb.append(url).append("\n");
        }

        return sb.toString();
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
            writer.println(currentPage);
            for(String url : backStack){
                writer.println("B:" + url);
            }
            for(String url : forwardStack){
                writer.println("F:" + url);
            }
            for(String url : history){
                writer.println("H:" + url);
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

            String line;
            if((line = reader.readLine()) != null){ 
                currentPage = line;
            }

            while ((line = reader.readLine()) != null){
                if(line.startsWith("B:")){
                    backStack.push(line.substring(2));
                }
                if(line.startsWith("F:")){
                    forwardStack.push(line.substring(2));
                }
                if(line.startsWith("H:")){
                    history.enqueue(line.substring(2));
                }
            }
            return "Previous session restored.";
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
