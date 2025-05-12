/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.chart.BarChart;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class DokterMainFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AreaChart<String, Number> areaChart;

    @FXML
    private TextArea area_pemeriksaandiagnosa;

    @FXML
    private TextArea area_pemeriksaangejala;

    @FXML
    private TextArea area_pemeriksaanketerangan;

    @FXML
    private TextArea area_pemeriksaanterapi;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_hewan;

    @FXML
    private Button btn_logout;


    @FXML
    private Button btn_pemeriksaan;

    @FXML
    private Button btn_pemeriksaansimpan;

    @FXML
    private Button btn_pemilik;

    @FXML
    private Button btn_pemilikcetak;

    @FXML
    private Button btn_riwayatpemeriksaan;

    @FXML
    private Button btn_riwayatcari;

    @FXML
    private Button btn_riwayatcetaklaporan;

    @FXML
    private Button btn_riwayatstatus;

    @FXML
    private ComboBox<?> combo_pemeriksaanstatus;

    @FXML
    private TableColumn<?, ?> daftarpemeriksaan_col_bb;

    @FXML
    private TableColumn<?, ?> daftarpemeriksaan_col_idhewan;

    @FXML
    private TableColumn<?, ?> daftarpemeriksaan_col_keluhan;

    @FXML
    private TableColumn<?, ?> daftarpemeriksaan_col_keperluan;

    @FXML
    private TableColumn<?, ?> daftarpemeriksaan_col_namhew;

    @FXML
    private TableColumn<?, ?> daftarpemeriksaan_col_status;

    @FXML
    private TableColumn<?, ?> daftarpemeriksaan_col_umur;

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
    private TableColumn<?, ?> hewan_col_ttl;

    @FXML
    private TableColumn<?, ?> hewan_col_namaPemilik;

    @FXML
    private AnchorPane hewan_form;

    @FXML
    private TableColumn<?, ?> pemeriksaan_col_bb;

    @FXML
    private TableColumn<?, ?> pemeriksaan_col_diagnosa;

    @FXML
    private TableColumn<?, ?> pemeriksaan_col_gejala;

    @FXML
    private TableColumn<?, ?> pemeriksaan_col_keterangan;

    @FXML
    private TableColumn<?, ?> pemeriksaan_col_tanggal;

    @FXML
    private TableColumn<?, ?> pemeriksaan_col_terapi;

    @FXML
    private TableColumn<?, ?> pemeriksaan_col_umur;

    @FXML
    private AnchorPane pemeriksaan_form;

    @FXML
    private AnchorPane chartpane;

    @FXML
    private TableColumn<?, ?> pemilik_col_alamat;

    @FXML
    private TableColumn<?, ?> pemilik_col_nik;

    @FXML
    private TableColumn<?, ?> pemilik_col_nmpemilik;

    @FXML
    private TableColumn<?, ?> pemilik_col_nohp;

    @FXML
    private TableColumn<?, ?> pemilik_col_norm;

    @FXML
    private AnchorPane pemilik_form;

    @FXML
    private AnchorPane riwayatpemeriksaan_form;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_bb;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_diagnosa;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_gejala;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_nmhewan;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_ket;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_keperluan;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_tanggal;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_terapi;

    @FXML
    private TableColumn<?, ?> riwayatpemeriksaan_col_umur;

    @FXML
    private TableView<HewanData> tbl_hewan;

    @FXML
    private TableView<PemeriksaanData> tbl_pemeriksaanperiksa;

    @FXML
    private TableView<PemeriksaanData> tbl_pemeriksaanriwayat;

    @FXML
    private TableView<PemilikData> tbl_pemilik;

    @FXML
    private TableView<PemeriksaanData> tbl_riwayatpemeriksaan;

    @FXML
    private TextField txt_cariRiwayat;

    @FXML
    private TextField txt_hewancari;

    @FXML
    private TextField txt_pemilikcari;

    @FXML
    private TextField txt_pemeriksaanBB;

    @FXML
    private TextField txt_pemeriksaanIdhewan;

    @FXML
    private TextField txt_pemeriksaanumur;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    //SIDEBAR
    public void iduser() {

        String sql = "SELECT id_user "
                + "FROM user WHERE tanggal = CURRENT_DATE AND keperluan = 'Periksa'";

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

    //HARDER
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

    // PEMERIKSAAN
    public ObservableList<PemeriksaanData> daftarPeriksaGetData() {
        ObservableList<PemeriksaanData> listData = FXCollections.observableArrayList();

        String sql = "SELECT pemeriksaan.id_pemeriksaan, hewan.id_hewan, "
                + "hewan.namaHewan, pemeriksaan.umur, pemeriksaan.beratbadan, "
                + "pemeriksaan.keperluan, pemeriksaan.gejala, pemeriksaan.status_periksa FROM pemeriksaan "
                + "INNER JOIN hewan ON pemeriksaan.id_hewan = hewan.id_hewan "
                + "WHERE pemeriksaan.status_periksa = 'Menunggu';";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            PemeriksaanData pmData;
            while (result.next()) {
//                DoctorData(Integer id, String doctorID, String password, String fullName,
//            String email, String gender, Long mobileNumber, String specialized, String address,
//            String image, Date date, Date dateModify, Date dateDelete, String status)
                pmData = new PemeriksaanData(result.getInt("id_pemeriksaan"),
                        result.getString("id_hewan"), result.getString("namaHewan"),
                        result.getString("umur"), result.getDouble("beratbadan"), result.getString("keperluan"),
                        result.getString("gejala"), result.getString("status_periksa"));

                listData.add(pmData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<PemeriksaanData> pemeriksaanperiksaListData;

    public void pemeriksaanperiksaDisplayData() {
        pemeriksaanperiksaListData = daftarPeriksaGetData();

        daftarpemeriksaan_col_idhewan.setCellValueFactory(new PropertyValueFactory<>("id_hewan"));
        daftarpemeriksaan_col_namhew.setCellValueFactory(new PropertyValueFactory<>("namaHewan"));
        daftarpemeriksaan_col_umur.setCellValueFactory(new PropertyValueFactory<>("umur"));
        daftarpemeriksaan_col_bb.setCellValueFactory(new PropertyValueFactory<>("beratbadan"));
        daftarpemeriksaan_col_keperluan.setCellValueFactory(new PropertyValueFactory<>("keperluan"));
        daftarpemeriksaan_col_keluhan.setCellValueFactory(new PropertyValueFactory<>("gejala"));
        daftarpemeriksaan_col_status.setCellValueFactory(new PropertyValueFactory<>("status_periksa"));

        tbl_pemeriksaanperiksa.setItems(pemeriksaanperiksaListData);

        tbl_pemeriksaanperiksa.getSelectionModel().clearSelection();
        tbl_pemeriksaanperiksa.setOnMouseClicked(event -> {
            System.out.println("Mouse clicked on table.");
            if (event.getClickCount() == 1) {
                PemeriksaanData selecteddaftarPeriksa = tbl_pemeriksaanperiksa.getSelectionModel().getSelectedItem();
                System.out.println("Selected item: " + selecteddaftarPeriksa);
                if (selecteddaftarPeriksa != null) {
                    setPemeriksaanData(selecteddaftarPeriksa.getId_hewan(), selecteddaftarPeriksa.getUmur(), selecteddaftarPeriksa.getBeratbadan(), selecteddaftarPeriksa.getGejala());
                }
            }
        });

    }

    public void setPemeriksaanData(String id_hewan, String umur, Double beratbadan, String gejala) {
        System.out.println("ID Hewan: " + id_hewan);
        System.out.println("Umur: " + umur);
        txt_pemeriksaanIdhewan.setText(id_hewan);
        txt_pemeriksaanumur.setText(umur);
        txt_pemeriksaanBB.setText(String.valueOf(beratbadan));
        area_pemeriksaangejala.setText(gejala);

    }

    public ObservableList<PemeriksaanData> RiwayatPeriksaGetData(String id_hewan) {
        ObservableList<PemeriksaanData> listData = FXCollections.observableArrayList();

        String sql = "SELECT tanggal, umur, beratbadan, gejala, diagnosis, terapi, keterangan "
                + "FROM pemeriksaan WHERE id_hewan = ? AND status_periksa = 'Selesai'"
                + "ORDER BY tanggal DESC";

        connect = Database.connectDB();
        if (connect == null) {
            System.out.println("Koneksi database gagal.");
        } else {
            System.out.println("Koneksi database berhasil.");
        }

        try {
            // Ambil ID Hewan dari TextField
            System.out.println("Memulai proses pengambilan data...");
            id_hewan = (txt_pemeriksaanIdhewan.getText());
            System.out.println("ID Hewan: " + txt_pemeriksaanIdhewan.getText());

            System.out.println("Menyiapkan query...");
            prepare = connect.prepareStatement(sql);
            System.out.println("Query disiapkan.");

            System.out.println("Set parameter id_hewan...");
            prepare.setString(1, id_hewan);
            System.out.println("Parameter id_hewan diset.");

            System.out.println("Eksekusi query...");
            result = prepare.executeQuery();
            System.out.println("Query dieksekusi.");

            PemeriksaanData pmData;

            while (result.next()) {
                System.out.println("Data found: " + result.getString("gejala")); // Cek salah satu kolom
                pmData = new PemeriksaanData(
                        result.getDate("tanggal"),
                        result.getString("umur"),
                        result.getDouble("beratbadan"),
                        result.getString("gejala"),
                        result.getString("diagnosis"),
                        result.getString("terapi"),
                        result.getString("keterangan")
                );
                listData.add(pmData);
            }
            System.out.println("Jumlah data yang dimuat: " + listData.size());

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<PemeriksaanData> pemeriksaanRiwayatListData;

    public void pemeriksaanRiwayatDisplayData(String id_hewan) {
        pemeriksaanRiwayatListData = RiwayatPeriksaGetData(id_hewan);

        // Pastikan PropertyValueFactory sesuai dengan nama properti di class PemeriksaanData
        pemeriksaan_col_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        pemeriksaan_col_umur.setCellValueFactory(new PropertyValueFactory<>("umur"));
        pemeriksaan_col_bb.setCellValueFactory(new PropertyValueFactory<>("beratbadan"));
        pemeriksaan_col_gejala.setCellValueFactory(new PropertyValueFactory<>("gejala"));
        pemeriksaan_col_diagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        pemeriksaan_col_terapi.setCellValueFactory(new PropertyValueFactory<>("terapi"));
        pemeriksaan_col_keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));

        // Set data ke tabel
        tbl_pemeriksaanriwayat.setItems(pemeriksaanRiwayatListData);
        System.out.println("Data berhasil dimasukkan ke tabel.");

    }

    public void StatusPeriksaList() {
        List<String> listK = new ArrayList<>();

        for (String data : Data.status) {
            listK.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listK);
        combo_pemeriksaanstatus.setItems(listData);

    }

    public void updateData(String id_hewan, String umur, Double beratbadan, String gejala, String diagnosa, String terapi, String keterangan, String status_periksa) {
        String sql = "UPDATE pemeriksaan SET id_user = ?, umur = ?, beratbadan = ?, gejala = ?, diagnosis = ?, terapi = ?, keterangan = ?, status_periksa = ? WHERE id_hewan = ? AND tanggal = CURRENT_DATE";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, Session.getIduser());
            prepare.setString(2, umur);
            prepare.setDouble(3, beratbadan);
            prepare.setString(4, gejala);
            prepare.setString(5, diagnosa);
            prepare.setString(6, terapi);
            prepare.setString(7, keterangan);
            prepare.setString(8, status_periksa);
            prepare.setString(9, id_hewan);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data pemeriksaan berhasil disimpan!");
                System.out.println("Data berhasil diperbarui!");
            } else {
                System.out.println("Data tidak ditemukan.");
            }
            PemeriksaanClearBtn();
            daftarPeriksaRefreshData();
            riwayatPemeriksaanRefreshData();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal menyimpan data!");
        }
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();

    }

    @FXML
    public void onUpdatePemeriksaan() {
        try {
            if (txt_pemeriksaanIdhewan.getText().isEmpty() || txt_pemeriksaanumur.getText().isEmpty() || txt_pemeriksaanBB.getText().isEmpty()
                    || area_pemeriksaangejala.getText().isEmpty() || area_pemeriksaandiagnosa.getText().isEmpty() || area_pemeriksaanterapi.getText().isEmpty()
                    || combo_pemeriksaanstatus == null) {
                System.out.println("Semua kolom harus diisi!");
                showAlert(Alert.AlertType.ERROR, "Error", "Semua kolom harus diisi!");
                return;
            }

            String id_hewan = txt_pemeriksaanIdhewan.getText();
            String umur = txt_pemeriksaanumur.getText();
            double beratbadan = Double.parseDouble(txt_pemeriksaanBB.getText());
            String gejala = area_pemeriksaangejala.getText();
            String diagnosa = area_pemeriksaandiagnosa.getText();
            String terapi = area_pemeriksaanterapi.getText();
            String keterangan = area_pemeriksaanketerangan.getText();
            String status_periksa = (String) combo_pemeriksaanstatus.getValue();

            updateData(id_hewan, umur, beratbadan, gejala, diagnosa, terapi, keterangan, status_periksa);

        } catch (NumberFormatException e) {
            System.out.println("ID Hewan dan Umur harus berupa angka!");
        }
    }

    public void PemeriksaanClearBtn() {
        txt_pemeriksaanIdhewan.clear();
        txt_pemeriksaanumur.clear();
        txt_pemeriksaanBB.clear();
        area_pemeriksaangejala.clear();
        area_pemeriksaandiagnosa.clear();
        area_pemeriksaanterapi.clear();
        area_pemeriksaanterapi.clear();
        area_pemeriksaanketerangan.clear();
        combo_pemeriksaanstatus.getSelectionModel().clearSelection();

    }

    public void daftarPeriksaRefreshData() {
        // Clear data di ObservableList
        if (pemeriksaanperiksaListData != null) {
            pemeriksaanperiksaListData.clear();
        }

        // Ambil data baru dan set ke ObservableList
        pemeriksaanperiksaListData = daftarPeriksaGetData();
        tbl_pemeriksaanperiksa.setItems(pemeriksaanperiksaListData);

        // Paksa tabel untuk merender ulang (jika dibutuhkan)
        tbl_pemeriksaanperiksa.refresh();
    }

    public void riwayatPemeriksaanRefreshData() {
        // Clear data di ObservableList
        if (riwayatpemeriksaanListData != null) {
            riwayatpemeriksaanListData.clear();
        }

        // Ambil data baru dan set ke ObservableList
        riwayatpemeriksaanListData = RiwayatPemeriksaanGetData();
        tbl_riwayatpemeriksaan.setItems(riwayatpemeriksaanListData);

        // Paksa tabel untuk merender ulang (jika dibutuhkan)
        tbl_riwayatpemeriksaan.refresh();
    }

    // RIWAYAT PEMERIKSAAN
    public ObservableList<PemeriksaanData> RiwayatPemeriksaanGetData() {
        ObservableList<PemeriksaanData> listData = FXCollections.observableArrayList();

        // Query untuk mengambil data pemeriksaan dan nama hewan terkait
        String sql = "SELECT pemeriksaan.tanggal, "
                + "hewan.namaHewan, pemeriksaan.umur, pemeriksaan.beratbadan, "
                + "pemeriksaan.keperluan, pemeriksaan.gejala, pemeriksaan.diagnosis, "
                + "pemeriksaan.terapi, pemeriksaan.keterangan "
                + "FROM pemeriksaan "
                + "INNER JOIN hewan ON pemeriksaan.id_hewan = hewan.id_hewan "
                + "WHERE pemeriksaan.status_periksa = 'Selesai'"
                + "ORDER BY pemeriksaan.tanggal DESC";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                // Membaca data dari result set dan membuat objek PemeriksaanData
                PemeriksaanData pmData = new PemeriksaanData(
                        result.getDate("tanggal"),
                        result.getString("namaHewan"),
                        result.getString("umur"),
                        result.getDouble("beratbadan"),
                        result.getString("keperluan"),
                        result.getString("gejala"),
                        result.getString("diagnosis"), // perbaiki nama kolom jika perlu
                        result.getString("terapi"),
                        result.getString("keterangan")// memastikan kolom ada
                );
                listData.add(pmData); // Menambahkan data pemeriksaan ke list
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<PemeriksaanData> riwayatpemeriksaanListData;

    public void RiwayatPemeriksaanDisplayData() {
        riwayatpemeriksaanListData = RiwayatPemeriksaanGetData();

        // Menyiapkan kolom-kolom pada tabel untuk menampilkan data
        riwayatpemeriksaan_col_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        riwayatpemeriksaan_col_nmhewan.setCellValueFactory(new PropertyValueFactory<>("namaHewan"));
        riwayatpemeriksaan_col_umur.setCellValueFactory(new PropertyValueFactory<>("umur"));
        riwayatpemeriksaan_col_bb.setCellValueFactory(new PropertyValueFactory<>("beratbadan"));
        riwayatpemeriksaan_col_keperluan.setCellValueFactory(new PropertyValueFactory<>("keperluan"));
        riwayatpemeriksaan_col_gejala.setCellValueFactory(new PropertyValueFactory<>("gejala"));
        riwayatpemeriksaan_col_diagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        riwayatpemeriksaan_col_terapi.setCellValueFactory(new PropertyValueFactory<>("terapi"));
        riwayatpemeriksaan_col_ket.setCellValueFactory(new PropertyValueFactory<>("keterangan"));

        // Menghubungkan data ke tabel
        tbl_riwayatpemeriksaan.setItems(riwayatpemeriksaanListData);
    }

    @FXML
    public void onRiwayatCari() {
        String namaHewan = txt_cariRiwayat.getText();
        if (txt_cariRiwayat == null || txt_cariRiwayat.getText().trim().isEmpty()) {
            riwayatPemeriksaanRefreshData();
            showAlert(Alert.AlertType.WARNING, "Input Kosong", "Masukkan No RM untuk pencarian.");
            return;
        }

        String query = "SELECT pemeriksaan.tanggal, "
                + "hewan.namaHewan, pemeriksaan.umur, pemeriksaan.beratbadan, "
                + "pemeriksaan.keperluan, pemeriksaan.gejala, pemeriksaan.diagnosis, "
                + "pemeriksaan.terapi, pemeriksaan.keterangan, pemeriksaan.status_periksa "
                + "FROM pemeriksaan "
                + "INNER JOIN hewan ON pemeriksaan.id_hewan = hewan.id_hewan "
                + "WHERE pemeriksaan.status_periksa = 'Selesai' AND  hewan.namaHewan LIKE ? OR hewan.id_hewan LIKE ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {

            pstmt.setString(1, "%" + namaHewan + "%");
            pstmt.setString(2, "%" + namaHewan + "%");
            ResultSet rs = pstmt.executeQuery();

            riwayatpemeriksaanListData.clear();
            while (rs.next()) {

                Date tanggal = rs.getDate("tanggal");
                namaHewan = rs.getString("namaHewan");
                String umur = rs.getString("umur");
                Double beratbadan = rs.getDouble("beratbadan");
                String keperluan = rs.getString("keperluan");
                String gejala = rs.getString("gejala");
                String diagnosis = rs.getString("diagnosis");
                String terapi = rs.getString("terapi");
                String keterangan = rs.getString("keterangan");

                riwayatpemeriksaanListData.add(new PemeriksaanData(tanggal, namaHewan, umur, beratbadan, keperluan, gejala, diagnosis, terapi, keterangan));
            }

            tbl_riwayatpemeriksaan.setItems(riwayatpemeriksaanListData);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat mengambil data.");
        }
    }

    public void showDateDialogR() {
        // Buat stage untuk dialog
        Stage dialog = new Stage();
        dialog.setTitle("Pilih Periode Laporan");

        // Label dan DatePicker untuk Tanggal Awal
        Label labelAwal = new Label("Tanggal Awal:");
        DatePicker datePickerStart = new DatePicker();

        // Label dan DatePicker untuk Tanggal Akhir
        Label labelAkhir = new Label("Tanggal Akhir:");
        DatePicker datePickerEnd = new DatePicker();

        // Tombol Cetak Laporan
        Button btnPrint = new Button("Cetak Laporan");

        btnPrint.setOnAction(event -> {
            // Validasi input tanggal
            if (datePickerStart.getValue() == null || datePickerEnd.getValue() == null) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", "Harap pilih tanggal awal dan akhir.");
                return;
            }

            if (datePickerStart.getValue().isAfter(datePickerEnd.getValue())) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Tanggal awal tidak boleh lebih dari tanggal akhir.");
                return;
            }

            // Ambil nilai tanggal
            java.util.Date tanggalAwal = java.sql.Date.valueOf(datePickerStart.getValue());
            java.util.Date tanggalAkhir = java.sql.Date.valueOf(datePickerEnd.getValue());

            // Cetak laporan
            cetakLaporanPemeriksaan(tanggalAwal, tanggalAkhir);

            // Tutup dialog
            dialog.close();
        });

        // Layout dialog menggunakan VBox dengan alignment center
        VBox layout = new VBox(10, labelAwal, datePickerStart, labelAkhir, datePickerEnd, btnPrint);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        // Scene dan Stage
        dialog.setScene(new javafx.scene.Scene(layout, 300, 250));
        dialog.showAndWait();
    }

    private void cetakLaporanPemeriksaan(java.util.Date tanggalAwal, java.util.Date tanggalAkhir) {

        if (tanggalAwal == null || tanggalAkhir == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Tanggal awal dan akhir harus diisi.");
            return;
        }

        try {
            // Path file JasperReport
            String reportPath = "src/report/laporanpemeriksaan.jasper";

            // Periksa apakah file laporan ada
            File fileCheck = new File(reportPath);
            if (!fileCheck.exists()) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "File laporan tidak ditemukan di path: " + reportPath);
                return;
            }

            // Map parameter untuk laporan
            HashMap<String, Object> parameters = new HashMap<>();

            parameters.put("tanggal_awal", new java.sql.Date(tanggalAwal.getTime()));
            parameters.put("tanggal_akhir", new java.sql.Date(tanggalAkhir.getTime()));
            InputStream logoStream = getClass().getResourceAsStream("/report/logo.png");
            if (logoStream == null) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Logo tidak ditemukan");
                return;
            }

            Path logoPath = Paths.get("src/report/logo.png");
            byte[] logoBytes = Files.readAllBytes(logoPath);
            parameters.put("logo", logoBytes);

            // Sumber data laporan (contoh menggunakan data kosong)
            JRDataSource dataSource = new JREmptyDataSource();

            // Jalankan laporan
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, connect);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);

            // Simpan laporan ke Excel
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Simpan Laporan sebagai Excel");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                JRXlsxExporter exporter = new JRXlsxExporter();

                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
//
                // Konfigurasi tambahan untuk Excel

                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(false); // Semua data di satu sheet
                configuration.setRemoveEmptySpaceBetweenRows(true); // Hilangkan ruang kosong
                configuration.setDetectCellType(true); // Deteksi tipe data untuk sel
                configuration.setRemoveEmptySpaceBetweenColumns(true);
                configuration.setWhitePageBackground(false);
                exporter.setConfiguration(configuration);

                exporter.exportReport();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Laporan berhasil disimpan sebagai Excel.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal mencetak laporan: " + e.getMessage());
        }
    }

    // HEWAN
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

    private ObservableList<HewanData> hewanListData;

    public void HewanDisplayData() {
        hewanListData = HewanGetData();

        hewan_col_idhewan.setCellValueFactory(new PropertyValueFactory<>("id_hewan"));
        hewan_col_namhew.setCellValueFactory(new PropertyValueFactory<>("namaHewan"));
        hewan_col_jenhew.setCellValueFactory(new PropertyValueFactory<>("jenis_hewan"));
        hewan_col_jekel.setCellValueFactory(new PropertyValueFactory<>("jekel"));
        hewan_col_ttl.setCellValueFactory(new PropertyValueFactory<>("ttl"));
        hewan_col_namaPemilik.setCellValueFactory(new PropertyValueFactory<>("namaPemilik"));

        tbl_hewan.setItems(hewanListData);

    }

    // PEMILIK
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
//                DoctorData(Integer id, String doctorID, String password, String fullName,
//            String email, String gender, Long mobileNumber, String specialized, String address,
//            String image, Date date, Date dateModify, Date dateDelete, String status)
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
        pemilik_col_nmpemilik.setCellValueFactory(new PropertyValueFactory<>("namaPemilik"));
        pemilik_col_alamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        pemilik_col_nohp.setCellValueFactory(new PropertyValueFactory<>("noHP"));

        tbl_pemilik.setItems(pemilikListData);

    }

    public void showDateDialog() {
        // Buat stage untuk dialog
        Stage dialog = new Stage();
        dialog.setTitle("Pilih Periode Laporan");

        // Label dan DatePicker untuk Tanggal Awal
        Label labelAwal = new Label("Tanggal Awal:");
        DatePicker datePickerStart = new DatePicker();

        // Label dan DatePicker untuk Tanggal Akhir
        Label labelAkhir = new Label("Tanggal Akhir:");
        DatePicker datePickerEnd = new DatePicker();

        // Tombol Cetak Laporan
        Button btnPrint = new Button("Cetak Laporan");

        btnPrint.setOnAction(event -> {
            // Validasi input tanggal
            if (datePickerStart.getValue() == null || datePickerEnd.getValue() == null) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", "Harap pilih tanggal awal dan akhir.");
                return;
            }

            if (datePickerStart.getValue().isAfter(datePickerEnd.getValue())) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Tanggal awal tidak boleh lebih dari tanggal akhir.");
                return;
            }

            // Ambil nilai tanggal
            java.util.Date tanggalAwal = java.sql.Date.valueOf(datePickerStart.getValue());
            java.util.Date tanggalAkhir = java.sql.Date.valueOf(datePickerEnd.getValue());

            // Cetak laporan
            cetakLaporanKunjungan(tanggalAwal, tanggalAkhir);

            // Tutup dialog
            dialog.close();
        });

        // Layout dialog menggunakan VBox dengan alignment center
        VBox layout = new VBox(10, labelAwal, datePickerStart, labelAkhir, datePickerEnd, btnPrint);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        // Scene dan Stage
        dialog.setScene(new javafx.scene.Scene(layout, 300, 250));
        dialog.showAndWait();
    }

    private void cetakLaporanKunjungan(java.util.Date tanggalAwal, java.util.Date tanggalAkhir) {

        try {
            // Path file JasperReport
            String reportPath = "src/report/laporanpengunjung.jasper";

            // Map parameter untuk laporan
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("tanggal_awal", new java.sql.Date(tanggalAwal.getTime()));
            parameters.put("tanggal_akhir", new java.sql.Date(tanggalAkhir.getTime()));

            // Jalankan laporan
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, connect);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal mencetak laporan: " + e.getMessage());
        }
    }

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
            pemeriksaan_form.setVisible(false);
            riwayatpemeriksaan_form.setVisible(false);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(false);

            dashboardTP();
            dashboardP();
            dashboardV();

//            current_form.setText("Dashboard Form");
        } else if (event.getSource() == btn_pemeriksaan) {
            dashboard_form.setVisible(false);
            pemeriksaan_form.setVisible(true);
            riwayatpemeriksaan_form.setVisible(false);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
//            current_form.setText("pendafaran periksas Form");
        } else if (event.getSource() == btn_riwayatpemeriksaan) {
            dashboard_form.setVisible(false);
            pemeriksaan_form.setVisible(false);
            riwayatpemeriksaan_form.setVisible(true);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
//            patientDisplayData();
//            patientActionButton();
//            current_form.setText("Patient's Form");
        } else if (event.getSource() == btn_hewan) {
            dashboard_form.setVisible(false);
            pemeriksaan_form.setVisible(false);
            riwayatpemeriksaan_form.setVisible(false);
            hewan_form.setVisible(true);
            pemilik_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
//            current_form.setText("Appointment's Form");
        } else if (event.getSource() == btn_pemilik) {
            dashboard_form.setVisible(false);
            pemeriksaan_form.setVisible(false);
            riwayatpemeriksaan_form.setVisible(false);
            hewan_form.setVisible(false);
            pemilik_form.setVisible(true);

//            paymentDisplayData();
//
//            current_form.setText("Payment Form");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        iduser.setText(String.valueOf(Session.getIduser()));
        username.setText(Session.getUsername());
        usermasuk.setText(Session.getFullname());

        dashboardTP();
        dashboardP();
        dashboardV();
        Chart();
        // dashboardPengunjungDataChart();

        pemeriksaanperiksaDisplayData();
        txt_pemeriksaanIdhewan.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    String id_hewan = (newValue); // Ambil ID Hewan dari TextField
                    pemeriksaanRiwayatDisplayData(id_hewan); // Panggil metode untuk menampilkan data
                } catch (NumberFormatException e) {
                    System.out.println("ID Hewan tidak valid: " + e.getMessage());
                }
            }
        });
        StatusPeriksaList();

        RiwayatPemeriksaanDisplayData();
        HewanDisplayData();
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
        pemilikDisplayData();

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
