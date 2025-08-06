import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
public class AdvancedFXApp extends Application {
    Stage window;
    Scene loginScene, dashboardScene;
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("JavaFX Advanced App");
        Label userLabel = new Label("Username:");
        TextField userInput = new TextField();
        userInput.setPromptText("Enter username");
        Label passLabel = new Label("Password:");
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Enter password");
        Button loginBtn = new Button("Login");
        Label loginStatus = new Label();
        loginBtn.setOnAction(e -> {
            String user = userInput.getText();
            String pass = passInput.getText();
            if (user.equals("Rithesh") && pass.equals("76Rithesh")) {
                window.setScene(dashboardScene);
            } else {
                loginStatus.setText("Invalid credentials. Try 'Rithesh' / '76Rithesh'");
            }
        });
        VBox loginLayout = new VBox(10, userLabel, userInput, passLabel, passInput, loginBtn, loginStatus);
        loginLayout.setPadding(new Insets(20));
        loginScene = new Scene(loginLayout, 400, 250);
        Label welcome = new Label("Welcome to the Dashboard!");
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> window.setScene(loginScene));
        VBox dashboardLayout = new VBox(20, welcome, logoutBtn);
        dashboardLayout.setPadding(new Insets(20));
        dashboardScene = new Scene(dashboardLayout, 400, 250);
        window.setScene(loginScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
