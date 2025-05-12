/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class PendaftaranMainformController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextArea area_periksaKeluhan;

    @FXML
    private AreaChart<String, Number> areaChart;

    @FXML
    private Button btn_cetakrekammedishewan;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_hewan;

    @FXML
    private Button btn_hewaancari;

    @FXML
    private Button btn_hewansimpan;

    @FXML
    private Button btn_hewanhapus;

    @FXML
    private Button btn_kartusimpan;

    @FXML
    private Button btn_kartutambah;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_pemilik;

    @FXML
    private Button btn_pemilkcari;

    @FXML
    private Button btn_pemilikcetakkartu;

    @FXML
    private Button btn_pemilikhapus;

    @FXML
    private Button btn_pendaftarankartu;

    @FXML
    private Button btn_pendaftaranperiksa;

    @FXML
    private Button btn_periksacari;

    @FXML
    private Button btn_periksasimpan;

    @FXML
    private ComboBox<?> combo_periksakeperluan;

    @FXML
    private ComboBox<?> combo_hewanjenhew;

    @FXML
    private ComboBox<?> combo_hewanjekel;

    @FXML
    private ComboBox<?> combo_kartujenhew;

    @FXML
    private ComboBox<?> combo_kartujekel;

    @FXML
    private Label dashboard_P;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_V;

    @FXML
    private Label iduser;

    @FXML
    private Label usermasuk;

    @FXML
    private Label username;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableColumn<?, ?> hewan_col_idhewan;

    @FXML
    private TableColumn<?, ?> hewan_col_jekel;

    @FXML
    private TableColumn<?, ?> hewan_col_jenhew;

    @FXML
    private TableColumn<?, ?> hewan_col_namhew;

    @FXML
    private TableColumn<?, ?> hewan_col_nmPemilik;

    @FXML
    private TableColumn<?, ?> hewan_col_ttl;

    @FXML
    private AnchorPane hewan_form;

    @FXML
    private AnchorPane chartpane;

    @FXML
    private TableColumn<?, ?> pemilik_col_alamat;

    @FXML
    private TableColumn<?, ?> pemilik_col_namapemilik;

    @FXML
    private TableColumn<?, ?> pemilik_col_nik;

    @FXML
    private TableColumn<?, ?> pemilik_col_nohp;

    @FXML
    private TableColumn<?, ?> pemilik_col_norm;

    @FXML
    private AnchorPane pemilik_form;

    @FXML
    private AnchorPane pendaftarankartu_form;

    @FXML
    private AnchorPane pendaftaranperiksa_form;

    @FXML
    private TableColumn<?, ?> periksa_col_bb;

    @FXML
    private TableColumn<?, ?> periksa_col_idhewan;

    @FXML
    private TableColumn<?, ?> periksa_col_keluhan;

    @FXML
    private TableColumn<?, ?> periksa_col_keperluan;

    @FXML
    private TableColumn<?, ?> periksa_col_namhew;

    @FXML
    private TableColumn<?, ?> periksa_col_status;

    @FXML
    private TableColumn<?, ?> periksa_col_tanggal;

    @FXML
    private TableColumn<?, ?> periksa_col_umur;

    @FXML
    private TableView<HewanData> tbl_hewan;

    @FXML
    private TableView<PemilikData> tbl_pemilik;

    @FXML
    private TableView<PemeriksaanData> tbl_periksa;

    @FXML
    private TextField txt_hewanIdHewan;

    @FXML
    private TextField txt_hewannamaPemilik;

    @FXML
    private TextField txt_hewancari;

    @FXML
    private TextField txt_hewannama;

    @FXML
    private TextField txt_hewannorm;

    @FXML
    private DatePicker date_hewanttl;

    @FXML
    private TextField txt_kartuIdHewan;

    @FXML
    private TextField txt_kartualamat;

    @FXML
    private TextField txt_kartunamahewan;

    @FXML
    private TextField txt_kartunamapemilik;

    @FXML
    private TextField txt_kartunik;

    @FXML
    private TextField txt_kartunohp;

    @FXML
    private TextField txt_kartunorm;

    @FXML
    private DatePicker kartu_dateTtl;

    @FXML
    private TextField txt_pemilikcari;

    @FXML
    private TextField txt_periksaBB;

    @FXML
    private TextField txt_periksaIdhewan;

    @FXML
    private TextField txt_periksakeluhan;

    @FXML
    private TextField txt_periksanmhewan;

    @FXML
    private TextField txt_periksatanggal;

    @FXML
    private TextField txt_periksaumur;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    // DASHBOARD
    public void dashboardTP() {

        String sql = "SELECT COUNT(id_pemeriksaan) AS countTotal FROM pemeriksaan WHERE tanggal = CURRENT_DATE";

        connect = Database.connectDB();

        int tempTP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTP = result.getInt("countTotal");
            }
            dashboard_TP.setText(String.valueOf(tempTP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardP() {

        String sql = "SELECT COUNT(id_pemeriksaan) AS countPeriksa "
                + "FROM pemeriksaan WHERE tanggal = CURRENT_DATE AND keperluan = 'Periksa'";

        connect = Database.connectDB();

        int tempP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                // Gunakan alias "countVaksin" yang didefinisikan dalam query
                tempP = result.getInt("countPeriksa");
            }
            dashboard_P.setText(String.valueOf(tempP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardV() {

        String sql = "SELECT COUNT(id_pemeriksaan) AS countVaksin "
                + "FROM pemeriksaan WHERE tanggal = CURRENT_DATE AND keperluan = 'Vaksin'";

        connect = Database.connectDB();

        int tempV = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                // Gunakan alias "countVaksin" yang didefinisikan dalam query
                tempV = result.getInt("countVaksin");
            }
            dashboard_V.setText(String.valueOf(tempV));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Chart() {
        // Create the X and Y axis for the chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Bulan");
        yAxis.setLabel("Total");

        // Create the AreaChart
        areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Summary Per Bulan");

        // Add series for each category
        XYChart.Series<String, Number> totalPengunjungSeries = new XYChart.Series<>();
        totalPengunjungSeries.setName("Total Pengunjung");

        XYChart.Series<String, Number> totalPemeriksaanSeries = new XYChart.Series<>();
        totalPemeriksaanSeries.setName("Total Pemeriksaan");

        XYChart.Series<String, Number> totalVaksinSeries = new XYChart.Series<>();
        totalVaksinSeries.setName("Total Vaksin");

        // Fetch data from database and populate the chart
        fetchDataAndPopulateChart(totalPengunjungSeries, totalPemeriksaanSeries, totalVaksinSeries);

        // Add series to the chart
        areaChart.getData().addAll(totalPengunjungSeries, totalPemeriksaanSeries, totalVaksinSeries);
        chartpane.getChildren().clear(); // Clear any existing children in the AnchorPane
        chartpane.getChildren().add(areaChart);

        AnchorPane.setTopAnchor(areaChart, 0.0);    // Memberi jarak dari atas
        AnchorPane.setLeftAnchor(areaChart, 0.0);   // Memberi jarak dari kiri
        AnchorPane.setRightAnchor(areaChart, 0.0);  // Memberi jarak dari kanan
        AnchorPane.setBottomAnchor(areaChart, 0.0); // Memberi jarak dari bawah

    }

    private void fetchDataAndPopulateChart(XYChart.Series<String, Number> totalPengunjungSeries,
            XYChart.Series<String, Number> totalPemeriksaanSeries,
            XYChart.Series<String, Number> totalVaksinSeries) {
        // SQL query to get monthly summary
        String sql = "SELECT MONTH(tanggal) AS bulan, YEAR(tanggal) AS tahun, "
                + "SUM(total_pengunjung) AS total_pengunjung, "
                + "SUM(total_pemeriksaan) AS total_pemeriksaan, "
                + "SUM(total_vaksin) AS total_vaksin "
                + "FROM summary "
                + "GROUP BY YEAR(tanggal), MONTH(tanggal) "
                + "ORDER BY YEAR(tanggal), MONTH(tanggal)";

        try {
            // Establish connection
            connect = Database.connectDB();
            PreparedStatement stmt = connect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Loop through the result set and populate the series
            while (rs.next()) {
                String month = rs.getString("bulan") + "-" + rs.getString("tahun");

                System.out.println("Month: " + month + ", Pengunjung: " + rs.getInt("total_pengunjung"));

                totalPengunjungSeries.getData().add(new XYChart.Data<>(month, rs.getInt("total_pengunjung")));
                totalPemeriksaanSeries.getData().add(new XYChart.Data<>(month, rs.getInt("total_pemeriksaan")));
                totalVaksinSeries.getData().add(new XYChart.Data<>(month, rs.getInt("total_vaksin")));
            }

            // Close the resources
            rs.close();
            stmt.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MENU PENDAFTARAN KARTU 
            public String generateNoRM() {
        String noRM = "";
        int nomorTerakhir = 0;
        LocalDate today = LocalDate.now();
        String bulan = String.format("%02d", today.getMonthValue()); // MM
        String tahun = String.format("%02d", today.getYear() % 100); // YY (dua digit terakhir dari tahun)

        String sql = "SELECT noRM FROM pemilikhewan WHERE noRM LIKE ? ORDER BY noRM DESC LIMIT 1";

        try (Connection connect = Database.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            // Format pencarian nomor terakhir di bulan & tahun ini
            String prefix = "R-" + bulan + "-" + tahun + "-%";
            prepare.setString(1, prefix);

            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    String noRMTerakhir = result.getString("noRM");
                    String[] parts = noRMTerakhir.split("-");

                    if (parts.length == 4) {
                        nomorTerakhir = Integer.parseInt(parts[3]); // Ambil nomor terakhir
                    }
                }
            }

            // Buat nomor RM baru dengan format R-MM-YY-XXX
            noRM = String.format("R-%s-%s-%03d", bulan, tahun, nomorTerakhir + 1);

        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error saat generate noRM: " + e.getMessage());
        }

        return noRM;
    }
    public String generateIdHewan(String noRM) {
        String idHewan = "";
        int nomorTerakhir = 0;

        try (Connection connect = Database.connectDB()) {
            // Query untuk mendapatkan ID hewan terakhir berdasarkan noRM
            String sql = "SELECT id_hewan FROM hewan WHERE id_hewan LIKE ? ORDER BY id_hewan DESC LIMIT 1";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, noRM + "-%"); // Wildcard untuk ID hewan dengan prefix noRM

            // Eksekusi query
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                // Ambil bagian angka dari ID terakhir
                String idHewanTerakhir = result.getString("id_hewan").substring(noRM.length() + 1).trim(); // Hilangkan noRM dan '-'
                nomorTerakhir = Integer.parseInt(idHewanTerakhir);
            }

            // Buat ID hewan baru dengan format "R0001-01"
            idHewan = String.format("%s-%02d", noRM, nomorTerakhir + 1);

            // Tutup PreparedStatement dan ResultSet
            result.close();
            prepare.close();

        } catch (SQLException e) {
            System.err.println("Database error saat generate ID Hewan: " + e.getMessage());
        }

        return idHewan;
    }

    public void generateNoRMAndIdHewan() {
        // Generate nomor RM baru
        String noRMBaru = generateNoRM();
        txt_kartunorm.setText(noRMBaru); // Tampilkan noRM di text field

        // Generate ID Hewan baru berdasarkan noRM
        String idhewanBaru = generateIdHewan(noRMBaru);
        txt_kartuIdHewan.setText(idhewanBaru); // Tampilkan idHewan di text field

        // Informasi tambahan untuk debug (opsional)
        System.out.println("No RM Baru: " + noRMBaru);
        System.out.println("ID Hewan Baru: " + idhewanBaru);
    }

    @FXML
    public void onSimpan() {
        // Ambil data dari form
        String noRM = txt_kartunorm.getText();
        String nik = txt_kartunik.getText();
        String namaPemilik = txt_kartunamapemilik.getText();
        String alamat = txt_kartualamat.getText();
        String noHP = txt_kartunohp.getText();
        String IdHewan = txt_kartuIdHewan.getText();
        String namaHewan = txt_kartunamahewan.getText();
        String jenis_hewan = (String) combo_kartujenhew.getValue();
        String jekel = (String) combo_kartujekel.getValue();
        LocalDate ttl = kartu_dateTtl.getValue();

        // Validasi input
        if (noRM.isEmpty() || nik.isEmpty() || namaPemilik.isEmpty() || alamat.isEmpty() || noHP.isEmpty() || IdHewan.isEmpty() || namaHewan.isEmpty() || jenis_hewan == null || jekel == null || ttl == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Semua data harus diisi!");
            return;
        }

        // Panggil fungsi validasi `noRM`
        try (Connection connect = Database.connectDB()) {
            // Simpan data pemilik
            String sqlPemilik = "INSERT INTO pemilikhewan (noRM, NIK, namaPemilik, alamat, noHP) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psPemilik = connect.prepareStatement(sqlPemilik);
            psPemilik.setString(1, noRM);
            psPemilik.setString(2, nik);
            psPemilik.setString(3, namaPemilik);
            psPemilik.setString(4, alamat);
            psPemilik.setString(5, noHP);
            int affectedRows = psPemilik.executeUpdate();

            // Pastikan data pemilik berhasil disimpan sebelum lanjut ke hewan
            if (affectedRows == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Gagal menyimpan data pemilik!");
                return;
            }

            // Simpan data hewan
            String sqlHewan = "INSERT INTO hewan (id_hewan, namaHewan, jenis_hewan, jekel, ttl, noRM) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psHewan = connect.prepareStatement(sqlHewan);

            // Pastikan noRM sudah ada di pemilikhewan
            psHewan.setString(1, IdHewan);
            psHewan.setString(2, namaHewan);
            psHewan.setString(3, jenis_hewan);
            psHewan.setString(4, jekel);
            if (ttl != null) {
                psHewan.setDate(5, java.sql.Date.valueOf(ttl));
            } else {
                psHewan.setNull(5, Types.DATE);
            }
            psHewan.setString(6, noRM);
            psHewan.addBatch();

            // Eksekusi batch
            psHewan.executeBatch();

            // Reset form dan tampilkan alert
            pendaftaranKartuClearBtn();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data pemilik dan hewan berhasil disimpan!");

            txt_kartunorm.setText(generateNoRM());
            HewanRefreshData();
            PemilikRefreshData();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal menyimpan data!");
        }
    }

    public void addNIKValidation(TextField txt_kartunik) {
        txt_kartunik.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Jika fokus berpindah dari TextField
                String nik = txt_kartunik.getText();
                if (!nik.isEmpty() && !isNikValid(nik)) { // Cek apakah noRM sudah ada
                    // Tampilkan pesan error jika noRM sudah ada
                    showAlert(Alert.AlertType.ERROR, "Error", "NIK  sudah terdaftar!");

                    // Kembalikan fokus ke txt_kartunorm
                    txt_kartunorm.requestFocus();
                }
            }
        });
    }

    private boolean isNikValid(String nik) {
        try (Connection connect = Database.connectDB()) {
            String query = "SELECT COUNT(*) FROM pemilikhewan WHERE nik = ?";
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, nik);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; // True jika noRM belum ada
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // False jika terjadi error
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();

    }

    public void pendaftaranKartuGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        combo_kartujekel.setItems(listData);

    }

    public void pendaftaranKartuJenisHewanList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.jenisHewan) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        combo_kartujenhew.setItems(listData);

    }

    public void pendaftaranKartuClearBtn() {
        txt_kartunik.clear();
        txt_kartunamapemilik.clear();
        txt_kartualamat.clear();
        txt_kartunohp.clear();
        txt_kartunamahewan.clear();
        combo_kartujenhew.setValue(null);
        combo_kartujekel.getSelectionModel().clearSelection();
        kartu_dateTtl.setValue(null);
    }

    // MENU PENDAFTARAN PERIKSA
    @FXML
    public void onCariHewan() {
        // Membuka jendela pencarian hewan
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cari.fxml"));
            Parent root = loader.load();

            System.out.println("Opening CariController...");
            CariController cariController = loader.getController();
            cariController.setMainController(this);  // Menyambungkan jendela pencarian ke controller utama
            System.out.println("Controller set to: " + this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Pencarian Hewan");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk menerima data yang dipilih dari jendela pencarian
    public void setHewanData(String id_hewan, String namaHewan, Date ttl) {
        System.out.println("Data received in PendaftaranMainformController:");
        System.out.println("ID Hewan: " + id_hewan);
        System.out.println("Nama Hewan: " + namaHewan);
        txt_periksaIdhewan.setText(id_hewan);
        txt_periksanmhewan.setText(namaHewan);
        // Hitung umur dan tampilkan di TextField umur

        if (ttl != null) {
            // Konversi Date ke String (format yyyy-MM-dd)
            LocalDate localTtl = ttl.toLocalDate();
            String umur = hitungUmur(localTtl.toString());// hitungUmur menerima String format yyyy-MM-dd
            txt_periksaumur.setText(umur);
        } else {
            txt_periksaumur.setText("Tanggal Lahir Tidak Diketahui");
        }
    }

    private String hitungUmur(String tanggalLahir) {
        if (tanggalLahir == null || tanggalLahir.isEmpty()) {
            return "Tanggal Lahir Tidak Dapat Kosong";
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate ttl = LocalDate.parse(tanggalLahir, formatter);
            LocalDate today = LocalDate.now();
            Period period = Period.between(ttl, today);

            StringBuilder umur = new StringBuilder();
            if (period.getYears() > 0) {
                umur.append(period.getYears()).append(" tahun");
            }
            if (period.getMonths() > 0) {
                if (umur.length() > 0) {
                    umur.append(", ");
                }
                umur.append(period.getMonths()).append(" bulan");
            }
            if (period.getYears() == 0 && period.getMonths() == 0) {
                umur.append("Kurang dari 1 bulan");
            }

            return umur.toString();
        } catch (DateTimeParseException e) {
            return "Format Tanggal Tidak Valid (Gunakan yyyy-MM-dd)";
        } catch (Exception e) {
            return "Terjadi Kesalahan: " + e.getMessage();
        }
    }

    public void tanggal() {
        // Membuat TextField

        // Mendapatkan tanggal hari ini
        LocalDate tanggalHariIni = LocalDate.now();

        // Menampilkan tanggal ke dalam TextField
        txt_periksatanggal.setText(tanggalHariIni.toString());

    }

    public void onPeriksaSimpan() throws SQLException {
        // Ambil data dari form
        String idHewanStr = txt_periksaIdhewan.getText();
        String tanggal = txt_periksatanggal.getText();
        String umur = txt_periksaumur.getText();
        String beratBadanStr = txt_periksaBB.getText();
        String keperluan = (String) combo_periksakeperluan.getValue();
        String gejala = area_periksaKeluhan.getText();

        // Validasi input kosong
        if (idHewanStr.isEmpty() || tanggal.isEmpty() || umur.isEmpty() || beratBadanStr.isEmpty() || keperluan == null || gejala.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Semua data harus diisi!");
            return;
        }

        // Simpan ke database
        try (Connection connect = Database.connectDB()) {

            // Validasi berat badan (double)
            double beratBadan = Double.parseDouble(beratBadanStr);

            String sqlPeriksa = "INSERT INTO pemeriksaan (tanggal, id_hewan, umur, beratbadan, keperluan, gejala) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psPeriksa = connect.prepareStatement(sqlPeriksa);
            psPeriksa.setDate(1, java.sql.Date.valueOf(tanggal));
            psPeriksa.setString(2, idHewanStr); // Konversi ke java.sql.Date
            psPeriksa.setString(3, umur);
            psPeriksa.setDouble(4, beratBadan);
            psPeriksa.setString(5, keperluan); // Validasi ENUM handled by database
            psPeriksa.setString(6, gejala);

            int affectedRows = psPeriksa.executeUpdate();
            if (affectedRows > 0) {
                // Reset form dan tampilkan pesan sukses
                pendaftaranPeriksaClearBtn();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data pemeriksaan berhasil disimpan!");
                PeriksaRefreshData();
                 try {
                RangkumanHarian.saveDailySummary();
            } catch (Exception e) {
                // Tangani kalau ada problem pada saat update summary
                System.err.println("Gagal update summary setelah simpan pemeriksaan: " + e.getMessage());
            }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Gagal menyimpan data pemeriksaan!");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Berat Badan harus berupa angka!");
        }
    }

    public void KeperluanList() {
        List<String> listK = new ArrayList<>();

        for (String data : Data.keperluan) {
            listK.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listK);
        combo_periksakeperluan.setItems(listData);

    }

    public void pendaftaranPeriksaClearBtn() {
        txt_periksaIdhewan.clear();
        txt_periksanmhewan.clear();
        txt_periksaumur.clear();
        combo_periksakeperluan.getSelectionModel().clearSelection();
        txt_periksaBB.clear();
        area_periksaKeluhan.clear();
    }

    public void PeriksaRefreshData() {
        // Clear data di ObservableList
        if (periksaListData != null) {
            periksaListData.clear();
        }

        // Ambil data baru dan set ke ObservableList
        periksaListData = PeriksaGetData();
        tbl_periksa.setItems(periksaListData);

        // Paksa tabel untuk merender ulang (jika dibutuhkan)
        tbl_periksa.refresh();
    }

    public ObservableList<PemeriksaanData> PeriksaGetData() {
        ObservableList<PemeriksaanData> listData = FXCollections.observableArrayList();

        String sql = "SELECT pemeriksaan.id_pemeriksaan, pemeriksaan.tanggal, pemeriksaan.id_hewan, "
                + "hewan.namaHewan, pemeriksaan.beratbadan, pemeriksaan.umur, "
                + "pemeriksaan.keperluan, pemeriksaan.gejala, pemeriksaan.status_periksa FROM pemeriksaan "
                + "INNER JOIN hewan ON pemeriksaan.id_hewan = hewan.id_hewan WHERE tanggal = CURRENT_DATE";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            PemeriksaanData pmData;
            while (result.next()) {
                pmData = new PemeriksaanData(result.getInt("id_pemeriksaan"),
                        result.getDate("tanggal"), result.getString("id_hewan"),
                        result.getString("namaHewan"), result.getString("umur"),
                        result.getDouble("beratbadan"), result.getString("keperluan"),
                        result.getString("gejala"), result.getString("status_periksa"));

                listData.add(pmData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<PemeriksaanData> periksaListData;

    public void periksaDisplayData() {
        periksaListData = PeriksaGetData();

        periksa_col_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        periksa_col_idhewan.setCellValueFactory(new PropertyValueFactory<>("id_hewan"));
        periksa_col_namhew.setCellValueFactory(new PropertyValueFactory<>("namaHewan"));
        periksa_col_umur.setCellValueFactory(new PropertyValueFactory<>("umur"));
        periksa_col_bb.setCellValueFactory(new PropertyValueFactory<>("beratbadan"));
        periksa_col_keperluan.setCellValueFactory(new PropertyValueFactory<>("keperluan"));
        periksa_col_keluhan.setCellValueFactory(new PropertyValueFactory<>("gejala"));
        periksa_col_status.setCellValueFactory(new PropertyValueFactory<>("status_periksa"));

        tbl_periksa.setItems(periksaListData);

    }

    // MENU HEWAN
    public void HewanRefreshData() {
        // Clear data di ObservableList
        if (hewanListData != null) {
            hewanListData.clear();
        }

        // Ambil data baru dan set ke ObservableList
        hewanListData = HewanGetData();
        tbl_hewan.setItems(hewanListData);

        // Paksa tabel untuk merender ulang (jika dibutuhkan)
        tbl_hewan.refresh();
    }

    public void HewanJenisHewanList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.jenisHewan) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        combo_hewanjenhew.setItems(listData);

    }

    public void HewanGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        combo_hewanjekel.setItems(listData);

    }

    public void hewanClearBtn() {
        txt_hewannorm.clear();
        txt_hewannamaPemilik.clear();
        txt_hewannama.clear();
        txt_hewanIdHewan.clear();
        combo_hewanjenhew.setValue(null);
        combo_hewanjekel.getSelectionModel().clearSelection();
        date_hewanttl.setValue(null);

    }

    @FXML
    public void onHewanCari(String noRM) {
        String query = "SELECT h.id_hewan, h.namaHewan, h.jenis_hewan, h.jekel, h.ttl, p.namaPemilik FROM hewan h JOIN pemilikhewan p ON h.noRM = p.noRM WHERE p.namaPemilik LIKE ? OR h.namaHewan LIKE ? OR p.noRM LIKE ? OR p.NIK LIKE ? ";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            // Mengisi parameter query
            pstmt.setString(1, "%" + noRM + "%");
            pstmt.setString(2, "%" + noRM + "%");
            pstmt.setString(3, "%" + noRM + "%");
            pstmt.setString(4, "%" + noRM + "%");

            // Menjalankan query
            try (ResultSet rs = pstmt.executeQuery()) {
                hewanListData.clear();
                while (rs.next()) {
                    String id_hewan = rs.getString("id_hewan");
                    String namaHewan = rs.getString("namaHewan");
                    String jenis_hewan = rs.getString("jenis_hewan");
                    String jekel = rs.getString("jekel");
                    Date ttl = rs.getDate("ttl");
                    String namaPemilik = rs.getString("namaPemilik");

                    hewanListData.add(new HewanData(id_hewan, namaHewan, jenis_hewan, jekel, ttl, namaPemilik));
                }
                tbl_hewan.setItems(hewanListData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat mengambil data.");
        }
    }

    public ObservableList<HewanData> HewanGetData() {
        ObservableList<HewanData> listData = FXCollections.observableArrayList();
        String sql = "SELECT hewan.id_hewan, hewan.namaHewan, hewan.jenis_hewan, hewan.jekel, hewan.ttl,  pemilikhewan.namaPemilik FROM hewan INNER JOIN pemilikhewan ON pemilikhewan.noRM = hewan.noRM ";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            HewanData hData;

            while (result.next()) {
                hData = new HewanData(result.getString("id_hewan"), result.getString("namaHewan"),
                        result.getString("jenis_hewan"), result.getString("jekel"),
                        result.getDate("ttl"), result.getString("namaPemilik"));

                listData.add(hData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listData;

    }

    public void namaPemilik(String noRM) {
        // Query SQL
        String sql = "SELECT namaPemilik FROM pemilikhewan WHERE noRM = ?";

        // Koneksi ke database
        connect = Database.connectDB();

        try {
            // Menyiapkan statement
            prepare = connect.prepareStatement(sql);
            // Mengisi parameter
            prepare.setString(1, noRM);

            // Menjalankan query
            result = prepare.executeQuery();

            // Memproses hasil query
            if (result.next()) {
                // Mengambil nama pemilik dari hasil query
                String namaPemilik = result.getString("namaPemilik");
                // Menampilkan di console (opsional)
                System.out.println("Nama Pemilik: " + namaPemilik);
                // Menampilkan nama pemilik di field teks
                txt_hewannamaPemilik.setText(namaPemilik);

            } else {
                System.out.println("Data tidak ditemukan untuk noRM: " + noRM);
                txt_hewannamaPemilik.setText("Tidak ditemukan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onHewanSimpan() {
        // Ambil data dari form
        String noRM = txt_hewannorm.getText();
        String Idhewan = txt_hewanIdHewan.getText();
        String namaHewan = txt_hewannama.getText();
        String jenis_hewan = (String) combo_hewanjenhew.getValue();
        String jekel = (String) combo_hewanjekel.getValue();
        LocalDate ttl = date_hewanttl.getValue();

        // Validasi input
        if (noRM.isEmpty() || Idhewan.isEmpty() || namaHewan.isEmpty() || jenis_hewan.isEmpty() || jekel == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Semua data harus diisi!");
            return;
        }

        try (Connection connect = Database.connectDB()) {
            // Simpan data hewan
            String sqlHewan = "INSERT INTO hewan (id_hewan, namaHewan, jenis_hewan, jekel, ttl, noRM) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psHewan = connect.prepareStatement(sqlHewan);

            // Pastikan noRM sudah ada di pemilikhewan
            psHewan.setString(1, Idhewan);
            psHewan.setString(2, namaHewan);
            psHewan.setString(3, jenis_hewan);
            psHewan.setString(4, jekel);
            if (ttl != null) {
                psHewan.setDate(5, java.sql.Date.valueOf(ttl));
            } else {
                psHewan.setNull(5, Types.DATE);
            }
            psHewan.setString(6, noRM);
            psHewan.addBatch();

            // Eksekusi batch
            psHewan.executeBatch();

            // Reset form dan tampilkan alert
            hewanClearBtn();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data hewan berhasil disimpan!");

            HewanRefreshData();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal menyimpan data!");
        }
    }

    private ObservableList<HewanData> hewanListData;

    public void HewanDisplayData() {
        hewanListData = HewanGetData();

        hewan_col_idhewan.setCellValueFactory(new PropertyValueFactory<>("id_hewan"));
        hewan_col_namhew.setCellValueFactory(new PropertyValueFactory<>("namaHewan"));
        hewan_col_jenhew.setCellValueFactory(new PropertyValueFactory<>("jenis_hewan"));
        hewan_col_jekel.setCellValueFactory(new PropertyValueFactory<>("jekel"));
        hewan_col_ttl.setCellValueFactory(new PropertyValueFactory<>("ttl"));
        hewan_col_nmPemilik.setCellValueFactory(new PropertyValueFactory<>("namaPemilik"));

        tbl_hewan.setItems(hewanListData);
        tbl_hewan.refresh();

    }

    @FXML
    public void hapusHewan() {
        // Ambil data dari baris yang dipilih
        HewanData selectedItem = tbl_hewan.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String id_hewan = selectedItem.getId_hewan(); // Asumsikan `id` adalah primary key
            Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Konfirmasi", "Apakah Anda yakin menghapus data ini?");

            // Cek jika tombol OK dipilih
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    hapushewandatabase(id_hewan); // Hapus dari database
                    System.out.println("Data dengan id hewan" + id_hewan + " berhasil dihapus.");

                    // Refresh tabel untuk mencerminkan perubahan
                    HewanRefreshData(); // Ganti dengan metode untuk memperbarui tabel Anda
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menghapus data: " + e.getMessage());
                }
            }
// Hapus dari database
        } else {
            System.out.println("Tidak ada baris yang dipilih!");
        }
    }

    private void hapushewandatabase(String id_hewan) {
        String sql = "DELETE FROM hewan WHERE id_hewan = ?"; // Sesuaikan nama_tabel dan kolom

        try (Connection connect = Database.connectDB();
                PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, id_hewan); // Mengisi parameter dengan ID
            int affectedRows = prepare.executeUpdate(); // Eksekusi query
            if (affectedRows > 0) {
                System.out.println("Data berhasil dihapus.");
                showAlert(Alert.AlertType.INFORMATION, "Infomasi", "Data berhasil dihapus");
                PemilikRefreshData(); // Perbarui tabel
            } else {
                System.out.println("Data gagal dihapus atau tidak ditemukan.");
                showAlert(Alert.AlertType.ERROR, "Error", "Data gagal dihapus");
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan database: " + e.getMessage());
        }

    }

    public void cetakRekamMedis() throws IOException {
           try {
            // Periksa apakah ada data yang dipilih
            HewanData selectedItem = tbl_hewan.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", "Data Belum dipilih");
                return;
            }

            // Koneksi ke database
            connect = Database.connectDB();
            if (connect == null) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal Koneksi ke Database");
                return;
            }

            // Ambil No RM dari data yang dipilih
            String idHewan= selectedItem.getId_hewan();

            // Lokasi file laporan
            InputStream reportStream = getClass().getResourceAsStream("/report/rekammedis.jasper");
            if (reportStream == null) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "File laporan tidak ditemukan");
                return;
            }

            // Parameter laporan
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("id_hewan", idHewan);

            // Cek apakah parameter sudah dimasukkan dengan benar
            System.out.println("Parameter id_hewan: " + parameters.get("id_hewan"));
            System.out.println("Parameters: " + parameters);

            // Isi laporan dengan data
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    reportStream, parameters, connect
            );

            // Tampilkan laporan
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            System.err.println("Error detail: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal Mencetak Rekam Medis Hewan");
        }
    }

    // MENU PEMILIK
    public void PemilikRefreshData() {
        // Clear data di ObservableList
        if (pemilikListData != null) {
            pemilikListData.clear();
        }

        // Ambil data baru dan set ke ObservableList
        pemilikListData = PemilikGetData();
        tbl_pemilik.setItems(pemilikListData);

        // Paksa tabel untuk merender ulang (jika dibutuhkan)
        tbl_pemilik.refresh();
    }

    @FXML
    public void onPemilkCari(String namaPemilik) {

        String query = "SELECT * FROM pemilikhewan WHERE namaPemilik LIKE ? OR NIK LIKE ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {

            pstmt.setString(1, "%" + namaPemilik + "%");
            pstmt.setString(2, "%" + namaPemilik + "%");
            ResultSet rs = pstmt.executeQuery();

            pemilikListData.clear();
            while (rs.next()) {
                String noRM = rs.getString("noRM");
                String nik = rs.getString("NIK");
                namaPemilik = rs.getString("namaPemilik");
                String alamat = rs.getString("alamat");
                String noHP = rs.getString("noHP");

                pemilikListData.add(new PemilikData(noRM, nik, namaPemilik, alamat, noHP));
            }

            tbl_pemilik.setItems(pemilikListData);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat mengambil data.");
        }
    }

    public ObservableList<PemilikData> PemilikGetData() {
        ObservableList<PemilikData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM pemilikhewan";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            PemilikData pData;
            while (result.next()) {
                pData = new PemilikData(result.getString("noRM"), result.getString("NIK"),
                        result.getString("namaPemilik"), result.getString("alamat"), result.getString("noHP"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<PemilikData> pemilikListData;

    public void pemilikDisplayData() {
        pemilikListData = PemilikGetData();

        pemilik_col_norm.setCellValueFactory(new PropertyValueFactory<>("noRM"));
        pemilik_col_nik.setCellValueFactory(new PropertyValueFactory<>("NIK"));
        pemilik_col_namapemilik.setCellValueFactory(new PropertyValueFactory<>("namaPemilik"));
        pemilik_col_alamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        pemilik_col_nohp.setCellValueFactory(new PropertyValueFactory<>("noHP"));

        tbl_pemilik.setItems(pemilikListData);
        tbl_pemilik.refresh();

    }

    @FXML
    public void hapusPemilik() {
        // Ambil data dari baris yang dipilih
        PemilikData selectedItem = tbl_pemilik.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String noRM = selectedItem.getNoRM(); // Asumsikan `id` adalah primary key
            Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Konfirmasi", "Apakah Anda yakin menghapus data ini?");

            // Cek jika tombol OK dipilih
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    hapuspemilikdatabase(noRM); // Hapus dari database
                    System.out.println("Data dengan No RM " + noRM + " berhasil dihapus.");

                    // Refresh tabel untuk mencerminkan perubahan
                    PemilikRefreshData(); // Ganti dengan metode untuk memperbarui tabel Anda
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menghapus data: " + e.getMessage());
                }
            }
// Hapus dari database
        } else {
            System.out.println("Tidak ada baris yang dipilih!");
        }
    }

    private void hapuspemilikdatabase(String noRM) {
        String sql = "DELETE FROM pemilikhewan WHERE noRM = ?"; // Sesuaikan nama_tabel dan kolom

        try (Connection connect = Database.connectDB();
                PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, noRM); // Mengisi parameter dengan ID
            int affectedRows = prepare.executeUpdate(); // Eksekusi query
            if (affectedRows > 0) {
                System.out.println("Data berhasil dihapus.");
                showAlert(Alert.AlertType.INFORMATION, "Infomasi", "Data berhasil dihapus");
                PemilikRefreshData(); // Perbarui tabel
            } else {
                System.out.println("Data gagal dihapus atau tidak ditemukan.");
                showAlert(Alert.AlertType.ERROR, "Error", "Data gagal dihapus");
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan database: " + e.getMessage());
        }

    }

    //CETAK KARTU
    public void cetakKartuPuskeswan() throws IOException {
        try {
            // Periksa apakah ada data yang dipilih
            PemilikData selectedItem = tbl_pemilik.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", "Data Belum dipilih");
                return;
            }

            // Koneksi ke database
            connect = Database.connectDB();
            if (connect == null) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal Koneksi ke Database");
                return;
            }

            // Ambil No RM dari data yang dipilih
            String noRM = selectedItem.getNoRM();

            // Lokasi file laporan
            InputStream reportStream = getClass().getResourceAsStream("/report/kartupuskeswan.jasper");
            if (reportStream == null) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "File laporan tidak ditemukan");
                return;
            }

            // Parameter laporan
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("noRM", noRM);

            InputStream logoStream = getClass().getResourceAsStream("/report/logo.png");
            if (logoStream == null) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Logo tidak ditemukan");
                return;
            }

            Path logoPath = Paths.get("src/report/logo.png");
            byte[] logoBytes = Files.readAllBytes(logoPath);
            parameters.put("logo", logoBytes);

            // Cek apakah parameter sudah dimasukkan dengan benar
            System.out.println("Parameter noRM: " + parameters.get("noRM"));
            System.out.println("Parameters: " + parameters);

            // Isi laporan dengan data
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    reportStream, parameters, connect
            );

            // Tampilkan laporan
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            System.err.println("Error detail: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal Mencetak Kartu Puskeswan");
        }
    }

    // LOGOUT
    @FXML
    private void logoutBtn(ActionEvent event) {

        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Konfirmasi", "Apakah Anda ingin keluar?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try { // Keluar
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                btn_logout.getScene().getWindow().hide();

                // Refresh tabel untuk mencerminkan perubahan
                PemilikRefreshData(); // Ganti dengan metode untuk memperbarui tabel Anda
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menghapus data: " + e.getMessage());
            }
        }
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == btn_dashboard) {
            dashboard_form.setVisible(true);
            pendaftarankartu_form.setVisible(false);
            pendaftaranperiksa_form.setVisible(false);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(false);

            dashboardTP();
            dashboardP();
            dashboardV();

        } else if (event.getSource() == btn_pendaftarankartu) {
            dashboard_form.setVisible(false);
            pendaftarankartu_form.setVisible(true);
            pendaftaranperiksa_form.setVisible(false);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(false);

        } else if (event.getSource() == btn_pendaftaranperiksa) {
            dashboard_form.setVisible(false);
            pendaftarankartu_form.setVisible(false);
            pendaftaranperiksa_form.setVisible(true);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(false);

        } else if (event.getSource() == btn_hewan) {
            dashboard_form.setVisible(false);
            pendaftarankartu_form.setVisible(false);
            pendaftaranperiksa_form.setVisible(false);
            hewan_form.setVisible(true);
            pemilik_form.setVisible(false);

        } else if (event.getSource() == btn_pemilik) {
            dashboard_form.setVisible(false);
            pendaftarankartu_form.setVisible(false);
            pendaftaranperiksa_form.setVisible(false);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(true);

        }

    }

//     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi data pengguna dari sesi
        initSessionData();

        // Inisialisasi dashboard dan chart
        initDashboard();

        // Inisialisasi form pendaftaran kartu
        initFormPendaftaran();

        // Inisialisasi data hewan dan pemilik
        initHewanDanPemilik();

    }

    private void initSessionData() {
        iduser.setText(String.valueOf(Session.getIduser()));
        username.setText(Session.getUsername());
        usermasuk.setText(Session.getFullname());
    }

    private void initDashboard() {
        dashboardTP();
        dashboardP();
        dashboardV();
        Chart();
    }

    private void initFormPendaftaran() {
        String noRMBaru = generateNoRM();
        txt_kartunorm.setText(noRMBaru);
      
        txt_kartunorm.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    String noRM = newValue;  
                    String idhewanBaru = generateIdHewan(noRM);
                    txt_kartuIdHewan.setText(idhewanBaru);
                } catch (Exception e) {
                    // Tangani error jika ada
                    System.out.println("ID Hewan tidak valid: " + e.getMessage());
                }
            }
        }); 
        generateNoRMAndIdHewan();
        addNIKValidation(txt_kartunik);
        pendaftaranKartuGenderList();
        pendaftaranKartuJenisHewanList();
        KeperluanList();
        tanggal();
        periksaDisplayData();
    }

    private void initHewanDanPemilik() {
        HewanGenderList();
        HewanJenisHewanList();
        HewanDisplayData();
        pemilikDisplayData();

        // Cari data hewan 
        txt_hewancari.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    String noRM = (newValue); // Ambil ID Hewan dari TextField
                    onHewanCari(noRM); // Panggil metode untuk menampilkan data
                } catch (NumberFormatException e) {
                    System.out.println("ID Hewan tidak valid: " + e.getMessage());
                }
            }
        });
        
        txt_hewannorm.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    String noRM = (newValue); // Ambil ID Hewan dari TextField
                    onHewanCari(noRM); // Panggil metode untuk menampilkan data
                } catch (NumberFormatException e) {
                    System.out.println("ID Hewan tidak valid: " + e.getMessage());
                }
            }
        });
         // Listener untuk perubahan pada TextField txt_hewannorm
        txt_hewannorm.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    String noRM = newValue;  
                    String idhewanBaru = generateIdHewan(noRM);
                    txt_hewanIdHewan.setText(idhewanBaru);
                } catch (Exception e) {
                    // Tangani error jika ada
                    System.out.println("ID Hewan tidak valid: " + e.getMessage());
                }
            }
        });
//        tampil nama Pemilik
        txt_hewannorm.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    String noRM = (newValue); // Ambil ID Hewan dari TextField
                    namaPemilik(noRM); // Panggil metode untuk menampilkan data
                } catch (NumberFormatException e) {
                    System.out.println("ID Hewan tidak valid: " + e.getMessage());
                }
            }
        });
        // cari data pemilik
        txt_pemilikcari.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    String namaPemilik = (newValue); // Ambil ID Hewan dari TextField
                    onPemilkCari(namaPemilik); // Panggil metode untuk menampilkan data
                } catch (NumberFormatException e) {
                    System.out.println("ID Hewan tidak valid: " + e.getMessage());
                }
            }
        });
    }


}
