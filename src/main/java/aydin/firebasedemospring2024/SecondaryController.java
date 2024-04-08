package aydin.firebasedemospring2024;

import java.io.IOException;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static aydin.firebasedemospring2024.DemoApp.fauth;

public class SecondaryController {

    @FXML
    public TextField emailBox;
    @FXML
    public TextField passwordBox;

    @FXML
    private void switchToPrimary() throws IOException {
        DemoApp.setRoot("primary");
    }

    @FXML
    void registerButtonClicked(ActionEvent event) {
        registerUser();
    }

    public boolean registerUser() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(emailBox.getText())
                .setEmailVerified(false)
                .setPassword(passwordBox.getText())
                .setPhoneNumber("")
                .setDisplayName("")
                .setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = fauth.createUser(request);
            System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                    + " check Firebase > Authentication > Users tab");
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error creating a new user in the firebase");
            return false;
        }

    }

    @FXML
    void signInButtonClicked(ActionEvent event) {signIn();}


    public void signIn(){
        UserRecord.CreateRequest newRequest = new UserRecord.CreateRequest()
                .setEmail(emailBox.getText())
                .setPassword(passwordBox.getText());
        UserRecord newUserRecord;
        try {
            newUserRecord = DemoApp.fauth.getUserByEmail(emailBox.getText());
            switchToPrimary();
        } catch (IOException | FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
    }
}
