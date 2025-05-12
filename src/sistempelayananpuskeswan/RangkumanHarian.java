/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class RangkumanHarian {

    private static ScheduledExecutorService scheduler;

     public static void startScheduler() {
        if (scheduler == null) {
            scheduler = Executors.newScheduledThreadPool(1);
            System.out.println("Scheduler dimulai...");
        }

        // Periksa apakah ringkasan hari ini sudah ada, kalau belum simpan sekarang juga
        checkAndSaveIfNotExists();

        Runnable dailyTask = () -> saveDailySummary();

        long initialDelay = getInitialDelay();
        long period = 24 * 60 * 60; // 24 jam dalam detik

        scheduler.scheduleAtFixedRate(dailyTask, initialDelay, period, TimeUnit.SECONDS);
    }

    private static long getInitialDelay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long currentTime = System.currentTimeMillis();
        long scheduledTime = calendar.getTimeInMillis();

        if (scheduledTime <= currentTime) {
            scheduledTime += 24 * 60 * 60 * 1000; // Tambahkan 24 jam
        }

        long delay = (scheduledTime - currentTime) / 1000;
        System.out.println("Jeda awal: " + delay + " detik.");
        return delay;
    }

    private static void checkAndSaveIfNotExists() {
        String checkSql = "SELECT COUNT(*) FROM summary WHERE tanggal = CURDATE()";

        try (Connection conn = Database.connectDB();
             PreparedStatement stmt = conn.prepareStatement(checkSql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Belum ada ringkasan hari ini. Menyimpan sekarang...");
                saveDailySummary();
            } else {
                System.out.println("Ringkasan hari ini sudah ada.");
            }

        } catch (SQLException e) {
            System.err.println("Gagal memeriksa ringkasan: " + e.getMessage());
        }
    }


    static void saveDailySummary() {
    String sql = 
        "INSERT INTO summary (tanggal, total_pengunjung, total_pemeriksaan, total_vaksin) " +
        "SELECT CURDATE(), " +
        "COUNT(DISTINCT id_pemeriksaan), " +
        "COUNT(CASE WHEN keperluan = 'Periksa' THEN 1 END), " +
        "COUNT(CASE WHEN keperluan = 'Vaksin' THEN 1 END) " +
        "FROM pemeriksaan " +
        "WHERE tanggal = CURDATE() " +
        "ON DUPLICATE KEY UPDATE " +
        "total_pengunjung = VALUES(total_pengunjung), " +
        "total_pemeriksaan = VALUES(total_pemeriksaan), " +
        "total_vaksin = VALUES(total_vaksin)";
        
    Connection connect = Database.connectDB();
    try (PreparedStatement prepare = connect.prepareStatement(sql)) {
        System.out.println("Ringkasan harian disimpan atau diperbarui pada: " + new Date());
        int rowsInserted = prepare.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Summary berhasil disimpan atau diperbarui.");
        }
    } catch (Exception e) {
        System.err.println("Gagal menyimpan atau memperbarui ringkasan harian: " + e.getMessage());
    }
}


    public static void stopScheduler() {
        if (scheduler != null) {
            scheduler.shutdown();
            scheduler = null;
        }
    }
}
