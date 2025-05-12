/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

/**
 *
 * @author ACER
 */
public class PemilikData {
   
    private String noRM;
    private String NIK;
    private String namaPemilik;
    private String alamat;
    private String noHP;
    
    public PemilikData(String noRM, String NIK, String namPemilik, String alamat, String noHP){
        this.noRM = noRM;
        this.NIK = NIK;
        this.namaPemilik = namPemilik;
        this.alamat = alamat;
        this.noHP = noHP;
    }

    public String getNoRM() {
        return noRM;
    }

    public String getNIK() {
        return NIK;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoRM(String noRM) {
        this.noRM = noRM;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }
    
    
}
