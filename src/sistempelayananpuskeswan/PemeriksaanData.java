/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.sql.Date;

/**
 *
 * @author ACER
 */
public class PemeriksaanData {

    private Integer id_pemeriksaan;
    private Date tanggal;
    private Double beratbadan;
    private String umur;
    private String keperluan;
    private String gejala;
    private String diagnosis;
    private String terapi;
    private String keterangan;
    private String status_periksa;
    private String status_sehat;
    private String id_hewan;
    private String namaHewan;

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String nama_hewan) {
        this.namaHewan = namaHewan;
    }

    // tbl_pemeriksaanperiksa
    public PemeriksaanData(Integer id_pemeriksaan, String id_hewan, String namaHewan, String umur, Double beratbadan,  String keperluan, String gejala, String status_periksa) {
        this.id_pemeriksaan = id_pemeriksaan;
        this.id_hewan = id_hewan;
        this.namaHewan = namaHewan;
        this.umur = umur;
        this.beratbadan = beratbadan;
        this.keperluan = keperluan;
        this.gejala = gejala;
        this.status_periksa = status_periksa;

    }

    // tbl_periksa
    public PemeriksaanData(Integer id_pemeriksaan, Date tanggal, String id_hewan, String namaHewan, String umur, Double beratbadan, String keperluan, String gejala, String status_periksa) {
        this.id_pemeriksaan = id_pemeriksaan;
        this.tanggal = tanggal;
        this.id_hewan = id_hewan;
        this.namaHewan = namaHewan;
        this.umur = umur;
        this.beratbadan = beratbadan;
        this.keperluan = keperluan;
        this.gejala = gejala;
        this.status_periksa = status_periksa;

    }

    // tbl_pemeriksaanriwayat
    public PemeriksaanData(Date tanggal,  String umur, Double beratbadan, String gejala, String diagnosis, String terapi, String keterangan) {
     
        this.tanggal = tanggal;
        this.umur = umur;
        this.beratbadan = beratbadan;
        this.gejala = gejala;
        this.diagnosis = diagnosis;
        this.terapi = terapi;
        this.keterangan = keterangan;
    }

    // tbl_riwayatpemeriksaan
    public PemeriksaanData(Date tanggal, String namaHewan, String umur, Double beratbadan, String keperluan, String gejala, String diagnosis, String terapi, String keterangan) {
        this.tanggal = tanggal;
        this.namaHewan = namaHewan;
        this.umur = umur;
        this.beratbadan = beratbadan;
        this.keperluan = keperluan;
        this.gejala = gejala;
        this.diagnosis = diagnosis;
        this.terapi = terapi;
        this.keterangan = keterangan;
    }

    public Integer getId_pemeriksaan() {
        return id_pemeriksaan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public Double getBeratbadan() {
        return beratbadan;
    }

    public String getUmur() {
        return umur;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public String getGejala() {
        return gejala;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTerapi() {
        return terapi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getStatus_periksa() {
        return status_periksa;
    }

    public String getStatus_sehat() {
        return status_sehat;
    }

    public String getId_hewan() {
        return id_hewan;
    }
}
