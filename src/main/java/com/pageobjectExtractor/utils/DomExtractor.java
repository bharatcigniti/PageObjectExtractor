package com.pageobjectExtractor.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author Bharath Kumar Reddy Vatrapu
 */

public class DomExtractor {
    Document document;
    String xpath=null;
    String findby=null;

    public void smartExtractor(String htmlContent, ArrayList<String> objList){
        try{
            if(objList.size()!=0) {
                if(htmlContent.contains("</")) {
                    document = Jsoup.parse(htmlContent);
                }else{
                    document = Jsoup.connect(htmlContent).get();
                }
                Elements input_elements = document.getElementsByTag("input");
                for (int k = 0; k <= objList.size() - 1; k++) {
                    switch (objList.get(k)) {
                        case "Link":
                            getLinks();
                            break;
                        case "Button":
                            getButtons(input_elements);
                            break;
                        case "CheckBox":
                            getCheckbox(input_elements);
                            break;
                        case "EditBox":
                            getEditBox(input_elements);
                            break;
                        case "RadioButton":
                            getRadioButton(input_elements);
                            break;
                        case "ComboBox":
                            getCombobox();
                            break;
                        case "Text":
                            getText();
                            break;
                    }
                }

            } else{
                JOptionPane.showMessageDialog(null, "Select atleast one Object control", "Warning.. " , JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }
    public void getButtons(Elements input_buttons){
        Elements buttons = document.getElementsByTag("button");
        buttonExtract(buttons);
        inputSubmitButtonExtract(input_buttons);
        inputImageButtonExtract(input_buttons);
    }
    public void getEditBox(Elements input_Edits){
        editBoxExtract(input_Edits);
        Elements textAreas = document.getElementsByTag("textarea");
        editBoxExtract(textAreas);
    }

    public void getLinks(){
        Elements links = document.getElementsByTag("a");
        linksExtract(links);
    }

    public void getCheckbox(Elements input_Checkboxes){
        checkboxExtract(input_Checkboxes);
    }
    public void getRadioButton(Elements input_radioboxes){
        radioboxExtract(input_radioboxes);
    }

    public void getCombobox(){
        Elements lists = document.getElementsByTag("select");
        listExtract(lists);
    }

    public void getText(){
        Elements text_h1s = document.getElementsByTag("h1");
        textExtract(text_h1s);
        Elements text_h2s = document.getElementsByTag("h2");
        textExtract(text_h2s);
        Elements text_h3s = document.getElementsByTag("h3");
        textExtract(text_h3s);
        Elements text_h4s = document.getElementsByTag("h4");
        textExtract(text_h4s);
        Elements text_h5s = document.getElementsByTag("h5");
        textExtract(text_h5s);
        Elements text_h6s = document.getElementsByTag("h6");
        textExtract(text_h6s);
    }

    public void textExtract(Elements texts){
        String[] textArr = null;
        String text_remove=null;
        String txttvarName = null;
        int iterator = 0;

        for (Element text : texts) {
            String getText[] = getTextValue(text).split("###");
            String tagName=text.tagName();
            String hiddenType = text.attr("type");
            text_remove=Generic.removeSpecialChars(getText[0]);
            if(!hiddenType.equalsIgnoreCase("hidden")) {
                if (!StringUtils.isEmpty(getText[0]) || !StringUtils.isEmpty(text_remove)) {

                    if (getText[1].equalsIgnoreCase("text()")) {
                        if (getText[0].contains("'")) {
                            textArr = getText[0].split("'");
                            xpath = getParentelements(text) + tagName + "[contains(text(),'" + textArr[0] + "')]";
                        } else {
                            xpath = getParentelements(text) + tagName + "[contains(text(),'" + getText[0] + "')]";
                        }
                    } else {
                        xpath = getParentelements(text) + tagName + "[" + getText[1] + "='" + getText[0] + "']";
                    }

                    findby = "xpath = " + xpath;
                    txttvarName = "txt_" + Generic.removeSpecialChars(getText[0]);



                    if (!StringUtils.isEmpty(xpath)) {
                        //if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                            if (GlobalConstants.textHashMap.containsKey(txttvarName)) {
                                iterator = iterator + 1;
                                GlobalConstants.pageObjectsHashMap.put(txttvarName + "_duplicate" + iterator, findby);
                                GlobalConstants.textHashMap.put(txttvarName + "_duplicate" + iterator, findby);

                            } else {
                                GlobalConstants.pageObjectsHashMap.put(txttvarName, findby);
                                GlobalConstants.textHashMap.put(txttvarName, findby);

                            }
                            nullvars();
                        }
                    //}

                }
            }

        }

    }


    public void listExtract(Elements lists){
        String text_remove=null;
        String listvarName = null;
        int iterator = 0;

        for (Element list : lists) {
            String getName = list.attr("name");
            String tagName=list.tagName();
            String hiddenType = list.attr("type");
            text_remove=Generic.removeSpecialChars(getName);
            if(!hiddenType.equalsIgnoreCase("hidden")) {
                if (!StringUtils.isEmpty(getName) || !StringUtils.isEmpty(text_remove)) {

                    xpath = getParentelements(list) + tagName + "[@name='" + getName + "']";

                    findby = "xpath = " + xpath;
                    listvarName = "list_" + Generic.removeSpecialChars(getName);


                    if (!StringUtils.isEmpty(xpath)) {
                        //if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                            if (GlobalConstants.listHashMap.containsKey(listvarName)) {
                                iterator = iterator + 1;
                                GlobalConstants.pageObjectsHashMap.put(listvarName + "_duplicate" + iterator, findby);
                                GlobalConstants.listHashMap.put(listvarName + "_duplicate" + iterator, findby);

                            } else {
                                GlobalConstants.pageObjectsHashMap.put(listvarName, findby);
                                GlobalConstants.listHashMap.put(listvarName, findby);

                            }
                            nullvars();
                      //  }
                    }

                }
            }

        }
    }

    public void radioboxExtract(Elements input_radioboxes){
        String text_remove=null;
        String radioboxvarName = null;
        int iterator = 0;

        for (Element radiobox : input_radioboxes) {
            if (radiobox.attr("type").equalsIgnoreCase("radio")) {
                String getValue = radiobox.attr("value");
                String tagName = radiobox.tagName();
                String hiddenType = radiobox.attr("type");
                text_remove = Generic.removeSpecialChars(getValue);
                if (!hiddenType.equalsIgnoreCase("hidden")) {
                    if (!StringUtils.isEmpty(getValue) || !StringUtils.isEmpty(text_remove)) {
                        xpath = getParentelements(radiobox) + tagName + "[@value='" + getValue + "']";
                    }
                }
                findby = "xpath = " + xpath;
                radioboxvarName = "radiobox_" + Generic.removeSpecialChars(getValue);


                if (!StringUtils.isEmpty(xpath)) {
                    //if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                        if (GlobalConstants.radioboxHashMap.containsKey(radioboxvarName)) {
                            iterator = iterator + 1;
                            GlobalConstants.pageObjectsHashMap.put(radioboxvarName + "_duplicate" + iterator, findby);
                            GlobalConstants.radioboxHashMap.put(radioboxvarName + "_duplicate" + iterator, findby);

                        } else {
                            GlobalConstants.pageObjectsHashMap.put(radioboxvarName, findby);
                            GlobalConstants.radioboxHashMap.put(radioboxvarName, findby);

                        }
                        nullvars();
                   // }
                }
            }
        }
    }
    public void checkboxExtract(Elements input_Checkboxes){
        String text_remove=null;
        String btnvarName = null;
        int iterator = 0;

        for (Element checkbox : input_Checkboxes) {
            if (checkbox.attr("type").equalsIgnoreCase("checkbox")) {
                String getName = checkbox.attr("name");
                String tagName = checkbox.tagName();
                String hiddenType = checkbox.attr("type");
                text_remove = Generic.removeSpecialChars(getName);
                if (!hiddenType.equalsIgnoreCase("hidden")) {
                    if (!StringUtils.isEmpty(getName) || !StringUtils.isEmpty(text_remove)) {
                        xpath = getParentelements(checkbox) + tagName + "[@value='" + getName + "']";
                    }
                }
                findby = "xpath = " + xpath;
                btnvarName = "chkbox_" + Generic.removeSpecialChars(getName);


                if (!StringUtils.isEmpty(xpath)) {
                 //   if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                        if (GlobalConstants.checkboxHashMap.containsKey(btnvarName)) {
                            iterator = iterator + 1;
                            GlobalConstants.pageObjectsHashMap.put(btnvarName + "_duplicate" + iterator, findby);
                            GlobalConstants.checkboxHashMap.put(btnvarName + "_duplicate" + iterator, findby);

                        } else {
                            GlobalConstants.pageObjectsHashMap.put(btnvarName, findby);
                            GlobalConstants.checkboxHashMap.put(btnvarName, findby);

                        }
                        nullvars();
                  //  }
                }
            }
        }
    }

    public void linksExtract(Elements links){
        String[] textArr = null;
        String text_remove=null;
        String lnkvarName = null;
        int iterator = 0;

        for (Element link : links) {
            String getText =link.text();
            System.out.println("link text::"+getText);
            String tagName=link.tagName();
            String hiddenType = link.attr("type");
            text_remove=Generic.removeSpecialChars(getText);
            if(!hiddenType.equalsIgnoreCase("hidden")) {
                if (!StringUtils.isEmpty(getText) || !StringUtils.isEmpty(text_remove)) {

                    if (getText.contains("'")) {
                        textArr = getText.split("'");
                        xpath = getParentelements(link) + tagName + "[contains(text(),'" + textArr[0] + "')]";
                    } else {
                        xpath = getParentelements(link) + tagName + "[contains(text(),'" + getText + "')]";
                    }
                    System.out.println("xpath::"+xpath);
                    findby = "xpath = " + xpath;
                    lnkvarName = "lnk_" + Generic.removeSpecialChars(getText);

                    if (!StringUtils.isEmpty(xpath)) {
                      //  if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                            if (GlobalConstants.linkHashMap.containsKey(lnkvarName)) {
                                iterator = iterator + 1;
                                GlobalConstants.pageObjectsHashMap.put(lnkvarName + "_duplicate" + iterator, findby);
                                GlobalConstants.linkHashMap.put(lnkvarName + "_duplicate" + iterator, findby);

                            } else {
                                GlobalConstants.pageObjectsHashMap.put(lnkvarName, findby);
                                GlobalConstants.linkHashMap.put(lnkvarName, findby);

                            }
                            nullvars();
                      //  }
                    }
                }
            }

        }
    }

    public void buttonExtract(Elements buttons){
        String[] textArr = null;
        String text_remove=null;
        String btnvarName = null;
        int iterator = 0;

        for (Element button : buttons) {
            String getText[] = getTextValue(button).split("###");
            String tagName=button.tagName();
            String hiddenType = button.attr("type");
            text_remove=Generic.removeSpecialChars(getText[0]);
            if(!hiddenType.equalsIgnoreCase("hidden")) {
                if (!StringUtils.isEmpty(getText[0]) || !StringUtils.isEmpty(text_remove)) {

                    if (getText[1].equalsIgnoreCase("text()")) {
                        if (getText[0].contains("'")) {
                            textArr = getText[0].split("'");
                            xpath = getParentelements(button) + tagName + "[contains(text(),'" + textArr[0] + "')]";
                        } else {
                            xpath = getParentelements(button) + tagName + "[contains(text(),'" + getText[0] + "')]";
                        }
                    } else {
                        xpath = getParentelements(button) + tagName + "[" + getText[1] + "='" + getText[0] + "']";
                    }

                    findby = "xpath = " + xpath;
                    btnvarName = "btn_" + Generic.removeSpecialChars(getText[0]);



                    if (!StringUtils.isEmpty(xpath)) {
                       // if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                            if (GlobalConstants.buttonsHashMap.containsKey(btnvarName)) {
                                iterator = iterator + 1;
                                GlobalConstants.pageObjectsHashMap.put(btnvarName + "_duplicate" + iterator, findby);
                                GlobalConstants.buttonsHashMap.put(btnvarName + "_duplicate" + iterator, findby);

                            } else {
                                GlobalConstants.pageObjectsHashMap.put(btnvarName, findby);
                                GlobalConstants.buttonsHashMap.put(btnvarName, findby);

                            }
                            nullvars();
                      //  }
                    }

                }
            }

        }
    }

    public void inputSubmitButtonExtract(Elements inputButtons){
        String text_remove=null;
        String btnvarName = null;
        int iterator = 0;

        for (Element input_button : inputButtons) {
            if (input_button.attr("type").equalsIgnoreCase("submit")) {
                String getValue = input_button.attr("value");
                String tagName = input_button.tagName();
                String hiddenType = input_button.attr("type");
                text_remove = Generic.removeSpecialChars(getValue);
                if (!hiddenType.equalsIgnoreCase("hidden")) {
                    if (!StringUtils.isEmpty(getValue) || !StringUtils.isEmpty(text_remove)) {
                        xpath = getParentelements(input_button) + tagName + "[@value='" + getValue + "']";
                    }
                }
                findby = "xpath = " + xpath;
                btnvarName = "btn_" + Generic.removeSpecialChars(getValue);


                if (!StringUtils.isEmpty(xpath)) {
                   // if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                        if (GlobalConstants.buttonsHashMap.containsKey(btnvarName)) {
                            iterator = iterator + 1;
                            GlobalConstants.pageObjectsHashMap.put(btnvarName + "_duplicate" + iterator, findby);
                            GlobalConstants.buttonsHashMap.put(btnvarName + "_duplicate" + iterator, findby);

                        } else {
                            GlobalConstants.pageObjectsHashMap.put(btnvarName, findby);
                            GlobalConstants.buttonsHashMap.put(btnvarName, findby);

                        }
                        nullvars();
                    //}
                }
            }
        }
    }

    public void inputImageButtonExtract(Elements inputButtons){
        String[] textArr = null;
        String text_remove=null;
        String btnvarName = null;
        int iterator = 0;

        for (Element input_button : inputButtons) {
            if (input_button.attr("type").equalsIgnoreCase("image")) {
                String getValue = input_button.attr("alt");
                String tagName = input_button.tagName();
                String hiddenType = input_button.attr("type");
                text_remove = Generic.removeSpecialChars(getValue);
                if (!hiddenType.equalsIgnoreCase("hidden")) {
                    if (!StringUtils.isEmpty(getValue) || !StringUtils.isEmpty(text_remove)) {
                        xpath = getParentelements(input_button) + tagName + "[@alt='" + getValue + "']";
                    }
                }
                findby = "xpath = " + xpath;
                btnvarName = "btn_" + Generic.removeSpecialChars(getValue);


                if (!StringUtils.isEmpty(xpath)) {
                    //if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                        if (GlobalConstants.buttonsHashMap.containsKey(btnvarName)) {
                            iterator = iterator + 1;
                            GlobalConstants.pageObjectsHashMap.put(btnvarName + "_duplicate" + iterator, findby);
                            GlobalConstants.buttonsHashMap.put(btnvarName + "_duplicate" + iterator, findby);

                        } else {
                            GlobalConstants.pageObjectsHashMap.put(btnvarName, findby);
                            GlobalConstants.buttonsHashMap.put(btnvarName, findby);

                        }
                        nullvars();
                    }
                //}
            }
        }
    }



    public void editBoxExtract(Elements input_Edits){
        String text_remove=null;
        String inputvarName = null;
        int iterator = 0;

        for (Element input_edit : input_Edits) {
            if (input_edit.attr("type").equalsIgnoreCase("input")||input_edit.attr("type").equalsIgnoreCase("text")||input_edit.attr("type").equalsIgnoreCase("password")||input_edit.attr("type").equalsIgnoreCase("email")||input_edit.attr("type").equalsIgnoreCase("number")||input_edit.attr("type").equalsIgnoreCase("search")||input_edit.attr("type").equalsIgnoreCase("url")||input_edit.attr("type").equalsIgnoreCase("tel")) {
                String getName = input_edit.attr("name");
                String tagName = input_edit.tagName();
                String hiddenType = input_edit.attr("type");
                text_remove = Generic.removeSpecialChars(getName);
                if (!hiddenType.equalsIgnoreCase("hidden")) {
                    if (!StringUtils.isEmpty(getName) || !StringUtils.isEmpty(text_remove)) {
                        xpath = getParentelements(input_edit) + tagName + "[@name='" + getName + "']";
                    }
                }
                findby = "xpath = " + xpath;
                inputvarName = "input_" + Generic.removeSpecialChars(getName);

                if (!StringUtils.isEmpty(xpath)) {
                    //if (GlobalConstants.driver.findElement(By.xpath(xpath)).isDisplayed()) {
                        if (GlobalConstants.inputHashMap.containsKey(inputvarName)) {
                            iterator = iterator + 1;
                            GlobalConstants.pageObjectsHashMap.put(inputvarName + "_duplicate" + iterator, findby);
                            GlobalConstants.inputHashMap.put(inputvarName + "_duplicate" + iterator, findby);

                        } else {
                            GlobalConstants.pageObjectsHashMap.put(inputvarName, findby);
                            GlobalConstants.inputHashMap.put(inputvarName, findby);

                        }
                        nullvars();
                    }
                //}
            }
        }
    }



    public static String getTextValue(Element element){
        String returnStatus=null;
        String getText = element.text();
        if(StringUtils.isEmpty(getText)){
            String getVale = element.attr("value");
            returnStatus = getVale+"###@value";
        } else{
            returnStatus = getText+"###text()";
        }
        return returnStatus;
    }

    public static String getParentelements(Element element){
        String returnVal=null;
        String id=null;
        String level1 = element.parentNode().nodeName();
        returnVal="//"+level1+"/";
        if(level1.equalsIgnoreCase("body")){
            return returnVal;
        } else {
            id = element.parentNode().attr("id");
            if(StringUtils.isEmpty(id)){
                String level2 = element.parentNode().parentNode().nodeName();
                returnVal = "//" + level2 + "/" + level1 + "/";
                if (level2.equalsIgnoreCase("body")) {
                    return returnVal;
                } else {
                    id=element.parentNode().parentNode().attr("id");
                    if(StringUtils.isEmpty(id)){
                        String level3 = element.parentNode().parentNode().parentNode().nodeName();
                        returnVal = "//" + level3 + "/" + level2 + "/" + level1 + "/";
                        if (level2.equalsIgnoreCase("body")) {
                            return returnVal;
                        } else {
                            id=element.parentNode().parentNode().parentNode().attr("id");
                            if(StringUtils.isEmpty(id)){
                                String level4 = element.parentNode().parentNode().parentNode().parentNode().nodeName();
                                returnVal = "//" + level4 + "/" + level3 + "/" + level2 + "/" + level1 + "/";
                                if (level4.equalsIgnoreCase("body")) {
                                    return returnVal;
                                } else {
                                    id=element.parentNode().parentNode().parentNode().parentNode().attr("id");
                                    if(StringUtils.isEmpty(id)){
                                        String level5 = element.parentNode().parentNode().parentNode().parentNode().parentNode().nodeName();
                                        returnVal = "//" + level5 + "/" + level4 + "/" + level3 + "/" + level2 + "/" + level1 + "/";
                                        if (level5.equalsIgnoreCase("body")) {
                                            return returnVal;
                                        } else {
                                            id=element.parentNode().parentNode().parentNode().parentNode().parentNode().attr("id");
                                            if(StringUtils.isEmpty(id)){
                                                id=element.parentNode().parentNode().parentNode().parentNode().parentNode().attr("class");

                                                if(StringUtils.isEmpty(id)){
                                                    return returnVal;
                                                } else {
                                                    returnVal="//"+level5+"[@class='"+id+"']"+"/" + level4 + "/" + level3 + "/" + level2 + "/" + level1 + "/";
                                                }
                                                return returnVal;
                                            } else {
                                                returnVal="//"+level5+"[@id='"+id+"']//"+level2+"//";
                                            }
                                        }

                                    } else{
                                        returnVal="//"+level4+"[@id='"+id+"']//"+level2+"//";
                                    }
                                }
                            } else {
                                returnVal="//"+level3+"[@id='"+id+"']//"+level2+"//";
                            }
                        }

                    } else{
                        returnVal="//"+level2+"[@id='"+id+"']//";
                    }
                }

            } else{
                returnVal="//"+level1+"[@id='"+id+"']/";
            }
        }
        return returnVal;
    }
    public String firstFive(String str) {
        return str.length() < 5 ? str : str.substring(0, 5);
    }
    public void nullvars(){
        findby = null;
//        webelement = null;
//        innerxpath = null;
//        text=null;
        xpath=null;
//        id=null;
//        enumPropertiesmodel=null;
//        pageObjectmodel=null;
    }
}
