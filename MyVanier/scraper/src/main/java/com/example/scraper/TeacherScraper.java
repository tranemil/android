package com.example.scraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherScraper {

    //List to store teachers into
    //static List<Teacher> teacher;
    static List<String[]> teacherString;

    public static void main(String[] args) {
        toCSV(System.getProperty("user.home") + "/Desktop/teacherInfo.csv");
    }


    /**
     * Will create a csv file containing
     * @param filePath
     */
    public static void toCSV(String filePath) {
        File file = new File(filePath);
        try {
            FileWriter teacherList = new FileWriter(file);
            CSVWriter writer = new CSVWriter(teacherList);
            String[] header = {"teacher_name", "department", "phone_number", "teacher_email", "extension", "office"};
            writer.writeNext(header);
            List<String[]> dataSet = getTeachersString();
            writer.writeAll(dataSet);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getTeachersString() {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setRedirectEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setUseInsecureSSL(true);
        teacherString = new ArrayList<String[]>();
        try {
            //Loop through all letters, a-z and pass to url as query param
            for (char c = 'a'; c <= 'z'; c++) {
                String URL = "https://www.vaniercollege.qc.ca/staff-directory/?index=" + c;
                HtmlPage page = client.getPage(URL);
                //Number of rows per page(number of teachers per page)
                int listSize = page.getElementsByTagName("tbody").get(1).getChildElementCount();
                //Elements of table
                HtmlElement table = page.getDocumentElement().getElementsByTagName("tbody").get(1);
                //Looping teachers
                for (int i = 0; i < listSize; i++) {
                    //Selecting row
                    HtmlElement row = table.getElementsByTagName("tr").get(i);
                    //Selecting column 1, name
                    String name = row.getElementsByTagName("td").get(0).getTextContent();
                    //Selecting column 2, email
                    String email = row.getElementsByTagName("td").get(1).getTextContent();
                    //Selecting column 3, department
                    String department = row.getElementsByTagName("td").get(2).getTextContent();
                    //Selecting column 4, office
                    String office = row.getElementsByTagName("td").get(3).getTextContent();
                    //Selecting column 5, phone & extension
                    String phone = row.getElementsByTagName("td").get(4).getTextContent();
                    String ext = "";
                    if (phone.equals(""))
                        System.out.println("Empty");
                    else if (phone.equals("On Leave")) {
                        System.out.println("On Leave");
                    } else if (phone.equals(phone.substring(0, 13))) {
                        System.out.println("no extension");
                    } else {
                        ext = phone.substring(phone.indexOf(" x ") + 3);
                        phone = phone.substring(0, phone.indexOf(" x "));

                    }
                    teacherString.add(new String[]{name, department, phone, email, ext, office});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teacherString;
    }
}


/*
    public List<Teacher> getTeachers() {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setRedirectEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setUseInsecureSSL(true);
        teacher = new ArrayList<Teacher>();
        try {
            //Loop through all letters, a-z and pass to url as query param
            for (char c = 'a'; c <= 'z'; c++) {
                String URL = "https://www.vaniercollege.qc.ca/staff-directory/?index=" + c;
                HtmlPage page = client.getPage(URL);
                //Number of rows per page(number of teachers per page)
                int listSize = page.getElementsByTagName("tbody").get(1).getChildElementCount();
                //Elements of table
                HtmlElement table = page.getDocumentElement().getElementsByTagName("tbody").get(1);
                //Looping teachers
                for (int i = 0; i < listSize; i++) {
                    //Selecting row
                    HtmlElement row = table.getElementsByTagName("tr").get(i);
                    //Selecting column 1, name
                    String name = row.getElementsByTagName("td").get(0).getTextContent();
                    //Selecting column 2, email
                    String email = row.getElementsByTagName("td").get(1).getTextContent();
                    //Selecting column 3, department
                    String department = row.getElementsByTagName("td").get(2).getTextContent();
                    //Selecting column 4, office
                    String office = row.getElementsByTagName("td").get(3).getTextContent();
                    //Selecting column 5, phone & extension
                    String phone = row.getElementsByTagName("td").get(4).getTextContent();
                    String ext = "";
                    if (phone.equals(""))
                        System.out.println("Empty");
                    else if (phone.equals("On Leave")) {
                        System.out.println("On Leave");
                    } else if (phone.equals(phone.substring(0, 13))) {
                        System.out.println("no extension");
                    } else {
                        ext = phone.substring(phone.indexOf(" x ") + 3);
                        phone = phone.substring(0, phone.indexOf(" x "));

                    }
                    teacher.add(new Teacher(name, department, phone, email, ext, office));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teacher;
    }
    */
