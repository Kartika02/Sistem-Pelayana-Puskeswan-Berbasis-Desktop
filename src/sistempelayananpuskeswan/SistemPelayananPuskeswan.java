/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class SistemPelayananPuskeswan extends Application {
    
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setMinWidth(300);
        stage.setMinHeight(500);
        
        stage.setScene(scene);
        stage.show();
        
        RangkumanHarian.startScheduler();
    }
    
    @Override
    public void stop() throws Exception {
        // Stop the scheduler when application is closed
        RangkumanHarian.stopScheduler();
        super.stop();
        System.out.println("Scheduler berhasil dihentikan.");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
