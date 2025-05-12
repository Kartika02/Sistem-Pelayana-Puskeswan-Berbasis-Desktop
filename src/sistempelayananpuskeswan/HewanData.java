/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author ACER
 */
public class HewanData {

    private String id_hewan;
    private String namaHewan;
    private String jenis_hewan;
    private String jekel;
    private String noRM;
    private Date ttl;
    private String namaPemilik;
    private String NIK;

    public HewanData(String id_hewan, String namaHewan, String jenis_hewan, String jekel, Date ttl, String namaPemilik) {
        this.id_hewan = id_hewan;
        this.namaHewan = namaHewan;
        this.jenis_hewan = jenis_hewan;
        this.jekel = jekel;
        this.ttl = ttl;
        this.namaPemilik = namaPemilik;
    }

    public HewanData(String noRM, String id_hewan, String namaHewan, String jenis_hewan, String jekel, Date ttl, String namaPemilik) {
        this.noRM = noRM;
        this.id_hewan = id_hewan;
        this.namaHewan = namaHewan;
        this.jenis_hewan = jenis_hewan;
        this.jekel = jekel;
        this.ttl = ttl;
        this.namaPemilik = namaPemilik;

    }

    public String getId_hewan() {
        return id_hewan;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public String getJenis_hewan() {
        return jenis_hewan;
    }

    public String getJekel() {
        return jekel;
    }

    public Date getTtl() {
        return ttl;
    }

    public String getNoRM() {
        return noRM;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String nama_pemilik) {
        this.namaPemilik = namaPemilik;
    }
    
     public String getNIK() {
        return NIK;
    }

    public void setNik(String NIK) {
        this.NIK = NIK;
    }

}
