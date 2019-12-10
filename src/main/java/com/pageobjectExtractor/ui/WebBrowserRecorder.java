package com.pageobjectExtractor.ui;

import com.pageobjectExtractor.utils.Generic;
import com.pageobjectExtractor.utils.GlobalConstants;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class WebBrowserRecorder extends Application{

    String id,name,value,xpath,css;
    int iteratpor=1;
    WebEngine engine;

    public static void main(String[] args) {
        launch(args);
    }
    public void browserLaunch(String url){
        launch(url);
    }

    public void browserStop()throws Exception{
        stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        engine = webView.getEngine();

        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                //if the page is loaded
                engine.setJavaScriptEnabled(true);
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("app", this);
//                engine.executeScript("document.addEventListener('click', function(event) { app.getCallBack(event.target.name);}, false);");
                engine.executeScript("document.addEventListener('click', function(event) { " +
                        "if(event.target.id === ''){" +
                            "if(event.target.name === ''){"+
                                "app.getIdentifier(event.target.outerText,'text',event.target.type,event.target.nodeName);" +

                            "} else{"+
                                "app.getIdentifier(event.target.name,'name',event.target.type,event.target.nodeName);" +
                            "}"+
                        "}else{" +
                        "app.getIdentifier(event.target.id,'id',event.target.type,event.target.nodeName);" +
                        "} " +
                        "}, false);");

//                engine.executeScript("var allInputs = document.getElementsByTagName('INPUT');" +
//                        "for(var i = 0; i < allInputs.length; i++) {" +
//                        "allInputs[i].addEventListener('blur',function(event){" +
//                        "app.getData(event.target.value);" +
//                        "})" +
//                        "}");
            }
        });

//        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
//            if (newState == Worker.State.SUCCEEDED) {
//                //if the page is loaded
//
//                JSObject window = (JSObject) engine.executeScript("window");
//                window.setMember("app", this);
//                engine.setJavaScriptEnabled(true);
//                name = "userName";
//                engine.executeScript("document.getElementsByName('"+name+"')[0].addEventListener('blur', function(event) { app.getCallBack(event.target.value);}, false);");
//
//            }
//        });

        engine.load(GlobalConstants.BASE_URL);

        VBox root = new VBox();
        root.getChildren().add(webView);

        root.setStyle("-fx-padding: 10;" +

                "-fx-border-style: solid inside;" +

                "-fx-border-width: 2;" +

                "-fx-border-insets: 5;" +

                "-fx-border-radius: 5;" +

                "-fx-border-color: red;");

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
    public void hilightElement(String xpath){
        engine.executeScript("document.evaluate('"+xpath+"', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.style.borderColor = \"red\";");
    }
    public void getData(String data) {
       //    System.out.println("---->" + data);
        GlobalConstants.tblRowno_incrementor = GlobalConstants.tblRowno_incrementor+1;
        Main.tblRecordmodel.addRow(new Object[]{GlobalConstants.tblRowno_incrementor,"type",name,data});
    }
    public void getIdentifier(String value,String By,String type,String tagname) {

       // System.out.println("---->" + data+">>>>>"+By);
//        GlobalConstants.tblRowno_incrementor = GlobalConstants.tblRowno_incrementor+1;
//        System.out.println("data:"+value+">> by:"+By+">> type:"+type+">> tagname:"+tagname);
        Generic.getPageObjectStructure(tagname,type,By,value);
       // Main.tblRecordmodel.addRow(new Object[]{GlobalConstants.tblRowno_incrementor,"click",By+"="+data});
        //name = By+"="+data;
    }

//    private void showAlert(String message) {
//        Dialog<Void> alert = new Dialog<>();
//        alert.getDialogPane().setContentText("message:"+message);
//        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        alert.showAndWait();
//    }
//
//    private boolean showConfirm(String message) {
//        Dialog<ButtonType> confirm = new Dialog<>();
//        confirm.getDialogPane().setContentText(message);
//        confirm.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
//        boolean result = confirm.showAndWait().filter(ButtonType.YES::equals).isPresent();
//
//        // for debugging:
//        System.out.println(result);
//
//        return result ;
//    }













//    NodeList nodeList = document.getElementsByTagName("a");
//            for (int i = 0; i < nodeList.getLength(); i++)
//    {
//        Node node= nodeList.item(i);
//        EventTarget eventTarget = (EventTarget) node;
//        eventTarget.addEventListener("click", new EventListener()
//        {
//            @Override
//            public void handleEvent(Event evt)
//            {
//                EventTarget target = evt.getCurrentTarget();
//                HTMLAnchorElement anchorElement = (HTMLAnchorElement) target;
//                String href = anchorElement.getHref();
//                //handle opening URL outside JavaFX WebView
//                System.out.println(href);
//                evt.preventDefault();
//            }
//        }, false);
//    }

}

