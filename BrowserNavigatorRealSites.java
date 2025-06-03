import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Stack;

public class BrowserNavigatorRealSites {
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    private String currentPage = "https://www.google.com"; // Start at Google

    public void openPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Failed to open: " + url);
        }
    }

    public void visit(String url) {
        if (!currentPage.isEmpty()) {
            backStack.push(currentPage);
        }
        currentPage = url;
        forwardStack.clear();
        System.out.println("Visiting: " + currentPage);
        openPage(currentPage);
    }

    public void goBack() {
        if (backStack.isEmpty()) {
            System.out.println("No pages in back history.");
            return;
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        System.out.println("Going back to: " + currentPage);
        openPage(currentPage);
    }

    public void goForward() {
        if (forwardStack.isEmpty()) {
            System.out.println("No pages in forward history.");
            return;
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        System.out.println("Going forward to: " + currentPage);
        openPage(currentPage);
    }

    public void showCurrentPage() {
        System.out.println("Current Page: " + currentPage);
    }

    public static void main(String[] args) {
        BrowserNavigatorRealSites browser = new BrowserNavigatorRealSites();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Real Site Browser Navigation ===");
        System.out.println("Commands: visit <url>, back, forward, current, exit");

        browser.openPage(browser.currentPage); // Open starting page

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting browser.");
                break;
            } else if (input.equalsIgnoreCase("back")) {
                browser.goBack();
            } else if (input.equalsIgnoreCase("forward")) {
                browser.goForward();
            } else if (input.equalsIgnoreCase("current")) {
                browser.showCurrentPage();
            } else if (input.toLowerCase().startsWith("visit ")) {
                String url = input.substring(6).trim();
                if (!url.startsWith("http")) {
                    url = "https://" + url;
                }
                browser.visit(url);
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }
}
