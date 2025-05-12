/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class CariController implements Initializable {
   @FXML
    private Button btn_carihewan;

    @FXML
    private Button btn_pilihhewaan;

    @FXML
    private TableColumn<HewanData, Integer> cari_col_idhewan;

    @FXML
    private TableColumn<HewanData, String> cari_col_jekel;

    @FXML
    private TableColumn<HewanData, String> cari_col_jenhew;

    @FXML
    private TableColumn<HewanData, String> cari_col_namahewan;

    @FXML
    private TableColumn<HewanData, Date> cari_col_ttl;

    @FXML
    private TableColumn<HewanData, String> cari_col_NamaPemilik;

    @FXML
    private TableColumn<HewanData, String> cari_col_norm;

    @FXML
    private TableView<HewanData> tbl_carihewan;

    @FXML
    private TextField txt_cariNoRM;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private ObservableList<HewanData> hewanList = FXCollections.observableArrayList();
    private PendaftaranMainformController controller;

    // Setter untuk menerima controller utama
    public void setMainController(PendaftaranMainformController mainController) {
        this.mainController = mainController;
        System.out.println("Main controller set: " + mainController);
    }

    private PendaftaranMainformController mainController;

    // Fungsi untuk mencari data hewan berdasarkan noRM
    @FXML
    public void onSearch() {
        String noRM = txt_cariNoRM.getText();
        if (txt_cariNoRM == null || txt_cariNoRM.getText().trim().isEmpty()) {
            showAlert(AlertType.WARNING, "Input Kosong", "Masukkan NoRM untuk pencarian.");
            return;
        }

        String query = "SELECT h.noRM, h.id_hewan, h.namaHewan, h.jenis_hewan, h.jekel, h.ttl, p.NIK FROM hewan h JOIN pemilikhewan p ON h.noRM = p.noRM WHERE p.namaPemilik LIKE ? OR h.namaHewan LIKE ? OR h.noRM = ? OR p.NIK LIKE ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {

            pstmt.setString(1, "%" + noRM + "%");
            pstmt.setString(2, "%" + noRM + "%");
            pstmt.setString(3, "%" + noRM + "%");
            pstmt.setString(4, "%" + noRM + "%");
            ResultSet rs = pstmt.executeQuery();

            hewanList.clear();
            while (rs.next()) {
                noRM = rs.getString("noRM");
                String id_hewan = rs.getString("id_hewan");
                String namaHewan = rs.getString("namaHewan");
                String jenis_hewan = rs.getString("jenis_hewan");
                String jekel = rs.getString("jekel");
                Date ttl = rs.getDate("ttl");
                String namaPemilik = rs.getString("namaPemilik");

                hewanList.add(new HewanData(noRM, id_hewan, namaHewan, jenis_hewan, jekel, ttl, namaPemilik));
            }

            tbl_carihewan.setItems(hewanList);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Terjadi kesalahan saat mengambil data.");
        }
    }

    public ObservableList<HewanData> HewanGetData() {
        hewanList.clear();
        String sql = "SELECT h.noRM, h.id_hewan, h.namaHewan, h.jenis_hewan, h.jekel, h.ttl, p.namaPemilik FROM hewan h INNER JOIN pemilikhewan p ON h.noRM = p.noRM";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            HewanData hData;

            while (result.next()) {

                hData = new HewanData(result.getString("noRM"), result.getString("id_hewan"), result.getString("namaHewan"),
                        result.getString("jenis_hewan"), result.getString("jekel"), result.getDate("ttl"), result.getString("namaPemilik")
                );

                hewanList.add(hData);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Terjadi kesalahan saat memuat data hewan.");
        }

        return hewanList;
    }

    public void HewanDisplayData() {
        hewanList = HewanGetData();
        // Set kolom tabel
        cari_col_norm.setCellValueFactory(new PropertyValueFactory<>("noRM"));
        cari_col_idhewan.setCellValueFactory(new PropertyValueFactory<>("id_hewan"));
        cari_col_namahewan.setCellValueFactory(new PropertyValueFactory<>("namaHewan"));
        cari_col_jenhew.setCellValueFactory(new PropertyValueFactory<>("jenis_hewan"));
        cari_col_jekel.setCellValueFactory(new PropertyValueFactory<>("jekel"));
        cari_col_ttl.setCellValueFactory(new PropertyValueFactory<>("ttl"));
        cari_col_NamaPemilik.setCellValueFactory(new PropertyValueFactory<>("namaPemilik"));

        tbl_carihewan.setItems(hewanList);
        // Tambahkan listener ke tabel
        tbl_carihewan.getSelectionModel().clearSelection();
        tbl_carihewan.setOnMouseClicked(event -> {
            System.out.println("Mouse clicked on table.");
            if (event.getClickCount() == 1) {
                HewanData selectedHewan = tbl_carihewan.getSelectionModel().getSelectedItem();
                System.out.println("Selected item: " + selectedHewan);
                if (selectedHewan != null) {
                    mainController.setHewanData(selectedHewan.getId_hewan(), selectedHewan.getNamaHewan(), selectedHewan.getTtl());

                }
                System.out.println("Closing CariController stage.");
                Stage stage = (Stage) tbl_carihewan.getScene().getWindow();
                stage.close();
            }
        });

    }

    // Fungsi untuk menampilkan alert
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HewanDisplayData();

    }
}
