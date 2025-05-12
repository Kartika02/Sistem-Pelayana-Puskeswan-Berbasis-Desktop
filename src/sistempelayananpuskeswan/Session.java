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
public class Session {
    private static int iduser;
    private static String username;
    private static String fullname;

    // Getter and Setter
    public static int getIduser() {
        return iduser;
    }

    public static void setIduser(int iduser) {
        Session.iduser = iduser;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        Session.fullname = fullname;
    }


}
