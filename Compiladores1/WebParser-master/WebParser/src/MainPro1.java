/*
 * To change this license header, choose License Headers in Project Prope   rties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lizandro
 */
import GUI.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JFrame;
public class MainPro1 extends Application {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Interfaz Int = new Interfaz();
        Int.setVisible(true);
      //  Hola();
        System.out.println((5>6 && 0>-3) && (5>6 || -3>7.7));
    }
    
    
    public static void Hola(){
        JFrame jFrame = new JFrame();

        JFXPanel jfxPanel = new JFXPanel();
        
        jFrame.add(jfxPanel);

        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
   // webView.getEngine().load("http://www.stackoverflow.com/");

            webView.getEngine().loadContent("<html><head><title>Tutor de Programacion</title></head>"
                    + "<body><h1>Hello JavaFX WebView</h1></body></html>");
        });

        jFrame.show();

    }
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX WebView Example");

        WebView webView = new WebView();

        webView.getEngine().load("http://google.com");

        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}








