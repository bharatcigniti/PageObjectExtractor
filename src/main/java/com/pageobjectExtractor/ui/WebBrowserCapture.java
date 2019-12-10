package com.pageobjectExtractor.ui;

import com.pageobjectExtractor.utils.GlobalConstants;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebBrowserCapture extends Application  {
    WebEngine webEngine;
    public static void main(String[] args) {
        launch(args);
    }

    public void browserLaunch(String url){
        launch(url);
    }

    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://newtours.demoaut.com");
        //new WebEngine();
        webEngine
                .getLoadWorker()
                .stateProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (newValue == Worker.State.SUCCEEDED) {

                                String html = (String) webEngine
                                        .executeScript("document.documentElement.outerHTML");

                                GlobalConstants.PAGE_SOURCE = html;

                            }
                        });



        webEngine
                .getLoadWorker()
                .stateProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (newValue == Worker.State.SUCCEEDED) {

                                String title = (String) webEngine
                                        .executeScript("document.title");

                            GlobalConstants.PAGE_TITLE=title;
                            }
                        });

        //Listen for state change
        webEngine.getLoadWorker().stateProperty().addListener((ov, o, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                webEngine.setOnStatusChanged(webEvent -> {

                    //Call value change
                    onValueChange(webEvent.getData());
                });
            }
        });

        // Create the VBox
        VBox root = new VBox();
        // Add the WebView to the VBox
        root.getChildren().add(webView);

        // Set the Style-properties of the VBox
//        root.setStyle("-fx-padding: 10;" +
//                "-fx-border-style: solid inside;" +
//                "-fx-border-width: 2;" +
//                "-fx-border-insets: 5;" +
//                "-fx-border-radius: 5;" +
//                "-fx-border-color: blue;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add  the Scene to the Stage
        stage.setScene(scene);
        // Display the Stage
        stage.show();
    }

    //called when value changes
    private void onValueChange(String data){

        //Print out data

        //If the data is equal to "exit", close the program
        if("exit".equals(data)){

            //Print goodbye message


            //Exit
            System.exit(0);
        }
    }
}
