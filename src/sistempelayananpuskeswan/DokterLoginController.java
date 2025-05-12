/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class DokterLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private CheckBox login_checkBox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private TextField login_showPassword;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private TextField login_username;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private PasswordField register_konfirpassword;

    @FXML
    private TextField register_showPassword;

    @FXML
    private Button register_signupBtn;

    @FXML
    private TextField register_username;

    @FXML
    private Label validationLabel;

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    //   DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loginAccount() {
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Username atau password tidak boleh kosong!");
            return;
        }

        String sql = "SELECT * FROM user WHERE username = ? AND peran = ?";
        connect = Database.connectDB();

        try {
            String passwordText = login_showPassword.isVisible()
                    ? login_showPassword.getText()
                    : login_password.getText();

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_username.getText());
            prepare.setString(2, "Dokter");
            result = prepare.executeQuery();

            if (result.next()) {
                String storedPassword = result.getString("password"); // Ambil hash password dari database
                if (BCrypt.checkpw(passwordText, storedPassword)) {
                    // Login berhasil
                    Session.setIduser(result.getInt("id_user")); // Pastikan nama kolom benar
                    Session.setFullname(result.getString("fullname"));
                    Session.setUsername(result.getString("username"));

                    showAlert(Alert.AlertType.INFORMATION, "Informasi", "Login Berhasil!");
                    // Pindah ke DokterMainForm
                    Parent root = FXMLLoader.load(getClass().getResource("DokterMainForm.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Sistem Pelayanan Puskeswan | Dokter Portal");
                    stage.setScene(new Scene(root));
                    stage.show();
                    // Tutup form login
                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    // Password tidak cocok
                    showAlert(Alert.AlertType.ERROR, "Error", "Password tidak cocok!");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Username salah/tidak terdaftar!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan pada sistem!");
        }
    }

    public void loginShowPassword() {

        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }

    }

    public void setupPasswordValidation(TextField registrasi_password, TextField registrasi_konfirpassword, Label validationLabel) {
        ChangeListener<String> passwordListener = (observable, oldValue, newValue)
                -> validatePasswordMatch(registrasi_password, registrasi_konfirpassword, validationLabel);

        registrasi_password.textProperty().addListener(passwordListener);
        registrasi_konfirpassword.textProperty().addListener(passwordListener);

        // Tambahkan placeholder
        registrasi_password.setPromptText("Masukkan password");
        registrasi_konfirpassword.setPromptText("Konfirmasi password");
    }

    private void validatePasswordMatch(TextField register_password, TextField register_konfirpassword, Label validationLabel) {
        String password = register_password.getText();
        String confirmPassword = register_konfirpassword.getText();

        if (password.length() < 8) {
            validationLabel.setText("Password harus minimal 8 karakter!");
            validationLabel.setStyle("-fx-text-fill: red;");
        } else if (confirmPassword.isEmpty()) {
            validationLabel.setText("");
        } else if (password.equals(confirmPassword)) {
            validationLabel.setText("Password cocok!");
            validationLabel.setStyle("-fx-text-fill: green;");
        } else {
            validationLabel.setText("Password tidak cocok!");
            validationLabel.setStyle("-fx-text-fill: red;");
        }
    }

    public void registerAccount() {

        if (register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()) {
            // LETS CREATE OUR ALERT MESSAGE
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Username atau password tidak boleh kosong!");
        } else {

            // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
            connect = Database.connectDB();

            try {

                if (!register_showPassword.isVisible()) {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_password.setText(register_showPassword.getText());
                    }
                }

                String checkUsername = "SELECT * FROM user WHERE username = ?";
                prepare = connect.prepareStatement(checkUsername);
                prepare.setString(1, register_username.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    showAlert(ERROR, "Error", checkUsername + "telah terdaftar");
                } else if (register_password.getText().length() < 8) { // CHECK IF THE CHARACTERS OF THE PASSWORD IS LESS THAN TO 8
                    showAlert(ERROR, "Error", "Minimal password 8 karakter");
                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    String insertData = "INSERT INTO user (fullname, username, password, peran, date) VALUES(?,?,?,?,?)";

                    String hashedPassword = BCrypt.hashpw(register_password.getText(), BCrypt.gensalt());
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, hashedPassword);
                    prepare.setString(4, "Dokter");
                    prepare.setString(5, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    showAlert(Alert.AlertType.INFORMATION, "Informasi", "Registrasi Berhasil!");
                    registerClear();

                    // TO SWITCH THE FORM INTO LOGIN FORM
                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword() {

        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }

    }

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Data.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Pendaftaran Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Sistem Pelayanan Puskeswan");

                stage.setMinWidth(300);
                stage.setMinHeight(500);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Dokter Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("DokterLogin.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Sistem Pelayanan Puskeswan");

                stage.setMinWidth(300);
                stage.setMinHeight(500);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        login_user.getScene().getWindow().hide();

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == login_registerHere) {
            // REGISTRATION FORM WILL SHOW
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_loginHere) {
            // LOGIN FORM WILL SHOW
            login_form.setVisible(true);
            register_form.setVisible(false);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userList();

        // Setup validasi password
        setupPasswordValidation(register_password, register_konfirpassword, validationLabel);
    }
}
