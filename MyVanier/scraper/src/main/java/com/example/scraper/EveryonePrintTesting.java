package com.example.scraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlFileInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class EveryonePrintTesting {

    // YOU MUST ENTER YOUR STUDENT-ID HERE
    static String username = "";
    // YOU MUST ENTER YOUR PASSWORD HERE
    static String password = "";

    public static void main(String[] args) {

    }
}
/***************************    FAILED ATTEMPTS     **********************************/
//        URL url = new URL("https://eop.vanier.college/login.cfm");
//        WebRequest requestInfo = new WebRequest(url, HttpMethod.POST);
//        requestInfo.getAdditionalHeaders().put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//        requestInfo.getAdditionalHeaders().put("Accept-Encoding","gzip, deflate, br");
//        requestInfo.getAdditionalHeaders().put("Accept-Language","en-US,en;q=0.9");
//        requestInfo.getAdditionalHeaders().put("Cache-Control","max-age=0");
//        requestInfo.getAdditionalHeaders().put("Connection","keep-alive");
//        requestInfo.getAdditionalHeaders().put("Host","eop.vanier.college");
//        requestInfo.getAdditionalHeaders().put("Origin","https://eop.vanier.college");
//        requestInfo.getAdditionalHeaders().put("Refer","https://eop.vanier.college/index.cfm");
//
//        List<NameValuePair> loginInfo = new ArrayList<NameValuePair>();
//        loginInfo.add(new NameValuePair("LoginAction","login"));
//        loginInfo.add(new NameValuePair("Username",username));
//        loginInfo.add(new NameValuePair("Password",password));
//        requestInfo.setRequestParameters(loginInfo);
//
//        Page loginCheck = webClient.getPage(requestInfo);
//        System.out.println(loginCheck.getUrl());
//        if(true) {
//            System.out.println("Successful login");
//            //Continuing to webprint.cfm
//            File file = new File(System.getProperty("user.home") + "/Desktop/test-upload.docx");
//            InputStream f = new FileInputStream(file);
//            url = new URL("https://eop.vanier.college/webprint.cfm");
//            requestInfo = new WebRequest(url, HttpMethod.POST);
//            requestInfo.getAdditionalHeaders().put("Connection","keep-alive");
//            requestInfo.getAdditionalHeaders().put("Host","eop.vanier.college");
//            requestInfo.getAdditionalHeaders().put("Origin","https://eop.vanier.college");
//            requestInfo.getAdditionalHeaders().put("Refer","https://eop.vanier.college/index.cfm");
//
//            List<NameValuePair> fileInfo = new ArrayList<NameValuePair>();
//            fileInfo.add(new NameValuePair("type","file"));
//            fileInfo.add(new NameValuePair("FileToPrint",file.getName()));
//            requestInfo.setRequestParameters(fileInfo);
//            webClient.getOptions().setJavaScriptEnabled(true);
//            webClient.getOptions().setCssEnabled(true);
//            webClient.getOptions().setTimeout(2000);
//            Page uploadCheck = webClient.getPage(requestInfo);
//            System.out.println(uploadCheck.getUrl());
            /*
            document = Jsoup.connect("https://eop.vanier.college/webprint.cfm")
                    .followRedirects(false)
                    .data("type","file")
                    .data("FileToPrint",file.getName(),f)
                    .post();
            System.out.println(document.location());

        }
        else {
            System.out.println("Failed");
        }

        System.out.println(loginCheck.getUrl());
        */
        /*
        try {
            Connection.Response loginForm = Jsoup.connect("https://eop.vanier.college/login.cfm")
                    .method(Connection.Method.POST)
                    .execute();
            Document document = Jsoup.connect("https://eop.vanier.college/login.cfm")
                    .data("LoginAction","login")
                    .data("Username", username)
                    .data("Password", password)
                    .cookies(loginForm.cookies())
                    .followRedirects(true)
                    .post();
            final String SUCCESS = "https://eop.vanier.college/index.cfm";
            if(document.location().equals(SUCCESS)) {
                System.out.println("Successful login");
                //Continuing to webprint.cfm
                File file = new File(System.getProperty("user.home") + "/Desktop/test-upload.docx");
                InputStream f = new FileInputStream(file);
                document = Jsoup.connect("https://eop.vanier.college/webprint.cfm")
                        .followRedirects(false)
                        .data("type","file")
                        .data("FileToPrint",file.getName(),f)
                        .post();
                System.out.println(document.location());
            }
            else {
                System.out.println("Failed");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        /*
        //Will create a new WebClient
        WebClient webClient = new WebClient();
        // Enabling css and js
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(true);
        // Assigning url to login page
        String url = "https://eop.vanier.college/login.cfm";
        // Loading login page
        HtmlPage loginPage = (HtmlPage) webClient.getPage(url);
        // Assigning form
        HtmlForm loginInput = (HtmlForm) loginPage.getForms().get(0);
        // Assigning form value for LoginAction input
        HtmlInput loginAction = (HtmlInput) loginInput.getInputByName("LoginAction");
        loginAction.setValueAttribute("login");
        // Assigning form value for Username input
        HtmlInput Username = (HtmlInput) loginInput.getInputByName("Username");
        Username.setValueAttribute(username);
        // Assigning form value for Password input
        HtmlInput Password = (HtmlInput) loginInput.getInputByName("Password");
        Password.setValueAttribute(password);
        // Locally creating a button
        HtmlElement button = (HtmlElement) loginPage.createElement("button");
        // Assigning submit value to button type
        button.setAttribute("type", "submit");
        // Appending button to form
        loginInput.appendChild(button);
        // Executing button click (Submitting form / executing login)
        loginPage = button.click();

        // URL if successful login
        final String SUCCESS = "https://eop.vanier.college/index.cfm";
        //Login check
        if (loginPage.getUrl().toString().equals(SUCCESS)) {
            //Login was successful
            System.out.println("Good");

            //Assigning new url path to webprint.cfm
            url = "https://eop.vanier.college/webprint.cfm";
            // Loading webprint page
            HtmlPage printPage = (HtmlPage) webClient.getPage(url);
            // Assigning form
            HtmlForm fileInput = (HtmlForm) printPage.getForms().get(0);
            // Assigning form HiddenInput value
            HtmlHiddenInput type = (HtmlHiddenInput) fileInput.getInputByName("type");
            type.setValueAttribute("file");
            // Assigning form FileInput values
            HtmlFileInput file = (HtmlFileInput) fileInput.getInputByName("FileToPrint");
            file.setValueAttribute("C:\\fakepath\\test-empty-doc.txt");
            file.setAttribute("filename", "test-empty-doc.txt");
            // Locally creating a button
            HtmlElement uploadBtn = (HtmlElement) printPage.createElement("button");
            // Assigning submit value to button type
            uploadBtn.setAttribute("type", "submit");
            // Appending button to upload form
            fileInput.appendChild(button);
            // Executing button click (Submitting form / executing file upload)
            printPage = uploadBtn.click();
            System.out.println(printPage.asText());
        } else {
            System.out.println("Bad Login");
        }
        */