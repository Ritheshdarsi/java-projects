import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

public class UserFormApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Registration Form");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");

        Label genderLabel = new Label("Gender:");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleBtn = new RadioButton("Male");
        RadioButton femaleBtn = new RadioButton("Female");
        RadioButton otherBtn = new RadioButton("Other");

        maleBtn.setToggleGroup(genderGroup);
        femaleBtn.setToggleGroup(genderGroup);
        otherBtn.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(10, maleBtn, femaleBtn, otherBtn);
        genderBox.setAlignment(Pos.CENTER_LEFT);

        Button submitBtn = new Button("Submit");
        Button clearBtn = new Button("Clear");

        GridPane form = new GridPane();
        form.setPadding(new Insets(20));
        form.setHgap(10);
        form.setVgap(10);

        form.add(usernameLabel, 0, 0);
        form.add(usernameField, 1, 0);
        form.add(passwordLabel, 0, 1);
        form.add(passwordField, 1, 1);
        form.add(emailLabel, 0, 2);
        form.add(emailField, 1, 2);
        form.add(phoneLabel, 0, 3);
        form.add(phoneField, 1, 3);
        form.add(genderLabel, 0, 4);
        form.add(genderBox, 1, 4);
        form.add(submitBtn, 0, 5);
        form.add(clearBtn, 1, 5);

        submitBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            String gender = (selectedGender != null) ? selectedGender.getText() : "Not selected";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Submitted Data");
            alert.setHeaderText("User Details:");
            alert.setContentText(
                "Username: " + username + "\n" +
                "Password: " + password + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Gender: " + gender
            );
            alert.showAndWait();
        });

        clearBtn.setOnAction(e -> {
            usernameField.clear();
            passwordField.clear();
            emailField.clear();
            phoneField.clear();
            genderGroup.selectToggle(null);
        });

        Scene scene = new Scene(form, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
