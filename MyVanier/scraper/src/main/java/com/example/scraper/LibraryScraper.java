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

public class LibraryScraper {


    static List<String[]> libraryString;

    public static void main(String[] args) {
        toCSV(System.getProperty("user.home") + "/Desktop/libraryInfo.csv");
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
            List<String[]> dataSet = getLibraryString();
            writer.writeAll(dataSet);
            writer.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }


    public static List<String[]> getLibraryString() {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setRedirectEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setUseInsecureSSL(true);
        libraryString = new ArrayList<String[]>();
        try {
            //Loop through all letters, a-z and pass to url as query param
            for (int i = 0; i <= 1000; i+=20) {
                String URL = "http://koha.vaniercollege.qc.ca/cgi-bin/koha/opac-search.pl?&limit=mc-itype%2Cphr%3ABKS&limit=mc-itype%2Cphr%3ABKS_E&offset=" + i + "&sort_by=pubdate_dsc";
                System.out.println("Now looping: " + URL);
                HtmlPage page = client.getPage(URL);
                //Number of rows per page(number of books per page)
                int listSize = page.getElementsByTagName("tbody").get(0).getChildElementCount();
                System.out.println("List size: " + listSize);
                //Elements of table
                HtmlElement table = page.getDocumentElement().getElementsByTagName("tbody").get(0);
                //Looping teachers
                System.out.println("Looping table");
                for (int c = 0; c < listSize; c++) {
                    //Selecting row
                    HtmlElement row = table.getElementsByTagName("tr").get(c);
                    //Selecting tr with data
                    HtmlElement col = row.getElementsByTagName("td").get(2);
                    //Selecting data from the column
                    HtmlElement titleAndAuthor = col.getElementsByTagName("a").get(1);
                    //Selecting title from element above
                    String titleAndAuthorText = titleAndAuthor.getTextContent();
                    String title = "";
                    String author = "";
                    if((titleAndAuthorText.contains(" by "))) {
                        if(titleAndAuthorText.contains(" edited by ")) {
                            System.out.println(" edited by ");
                            title = titleAndAuthorText.substring(0,titleAndAuthorText.indexOf(" edited by "));
                            author = titleAndAuthorText.substring(title.length()+11,titleAndAuthorText.length()-2);
                        }
                        else{
                            System.out.println(" by ");
                            title = titleAndAuthorText.substring(0,titleAndAuthorText.indexOf(" by "));
                            author = titleAndAuthorText.substring(title.length()+4,titleAndAuthorText.length()-2);
                        }
                    }
                    else if(titleAndAuthorText.contains("/")){
                        System.out.println("/");
                        title = titleAndAuthorText.substring(0,titleAndAuthorText.indexOf('/')-1);
                        author = titleAndAuthorText.substring(title.length()+3,titleAndAuthorText.length()-2);
                    }
                    else {
                        title = titleAndAuthorText;
                        author = "No author";
                    }
                    System.out.println(title);
                    System.out.println(author);
                    libraryString.add(new String[]{title, author});
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return libraryString;
    }

}
