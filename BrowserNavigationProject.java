import java.util.Scanner;
import java.util.Stack;

public class BrowserNavigationProject {
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    private String currentPage = "Home";

    public void visit(String url) {
        if (currentPage != null) {
            backStack.push(currentPage);
        }
        currentPage = url;
        forwardStack.clear();
        System.out.println("Visited: " + currentPage);
    }

    public void goBack() {
        if (backStack.isEmpty()) {
            System.out.println("No pages in back history.");
            return;
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        System.out.println("Back to: " + currentPage);
    }

    public void goForward() {
        if (forwardStack.isEmpty()) {
            System.out.println("No pages in forward history.");
            return;
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        System.out.println("Forward to: " + currentPage);
    }

    public void showCurrentPage() {
        System.out.println("Current Page: " + currentPage);
    }

    public void showHistory() {
        System.out.println("Back History: " + backStack);
        System.out.println("Forward History: " + forwardStack);
    }

    public static void main(String[] args) {
        BrowserNavigationProject browser = new BrowserNavigationProject();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Browser Navigation Simulator ===");
        System.out.println("Commands: visit <url>, back, forward, current, history, exit");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting browser...");
                break;
            } else if (input.equalsIgnoreCase("back")) {
                browser.goBack();
            } else if (input.equalsIgnoreCase("forward")) {
                browser.goForward();
            } else if (input.equalsIgnoreCase("current")) {
                browser.showCurrentPage();
            } else if (input.equalsIgnoreCase("history")) {
                browser.showHistory();
            } else if (input.toLowerCase().startsWith("visit ")) {
                String url = input.substring(6).trim();
                if (!url.isEmpty()) {
                    browser.visit(url);
                } else {
                    System.out.println("Please provide a valid URL.");
                }
            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }
}
