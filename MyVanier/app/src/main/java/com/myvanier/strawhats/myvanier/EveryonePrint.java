package com.myvanier.strawhats.myvanier;

import com.myvanier.strawhats.myvanier.fragments.EveryonePrintFileUpload;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EveryonePrint extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameText, passwordText;
    private Button loginBtn;

    /**
     * Initializes components when activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everyone_print);
        loginBtn = (Button) findViewById(R.id.everyonePrintLogin);
        usernameText = (EditText) findViewById(R.id.usernameLogin);
        passwordText = (EditText) findViewById(R.id.passwordLogin);
        loginBtn.setOnClickListener(this);
    }

    /**
     * OnClick listener event
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.everyonePrintLogin:
                EveryonePrintLogin login = new EveryonePrintLogin(this);
                login.execute();
                //TODO: Hide keyboard here
                break;
        }
    }

    /**
     * used to body AsyncTask
     */
    private class EveryonePrintLogin extends AsyncTask<String, Void, String> {

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        Context context;

        public EveryonePrintLogin(Context context)
        {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection.Response loginPage = Jsoup.connect("https://eop.vanier.college/login.cfm")
                        .method(Connection.Method.POST)
                        .execute();

                //Getting Form
                FormElement loginForm = (FormElement)loginPage.parse().select("form").first();

                //Getting username element
                Element usernameElement = loginForm.select("input").get(1);
                usernameElement.val(username);

                //Getting password element
                Element passwordElement = loginForm.select("input").get(2);
                passwordElement.val(password);

                Connection.Response loginFormResponse = loginForm.submit()
                        .cookies(loginPage.cookies())
                        .followRedirects(true)
                        .execute();
                Document printPage = loginFormResponse.parse();

                final String SUCCESS = "https://eop.vanier.college/index.cfm";
                //Condition check if login was successful
                if(printPage.location().equals(SUCCESS))
                {
                    //We are now at https://eop.vanier.college/index.cfm (Logged in)

                    AssetFileDescriptor fileDescriptor = getAssets().openFd("newfile.txt");
                    FileInputStream fileStream = fileDescriptor.createInputStream();

                    FormElement form = (FormElement) printPage.getElementsByTag("form").get(0);
                    Connection fileUpload = form.submit();
                    Connection.KeyVal fileUploadData = fileUpload
                            .data("FileToPrint")
                            .value("newfile.txt");
                    fileUploadData.inputStream(fileStream);


                    Connection.Response res;
                    try {
                        res = fileUpload
                                .followRedirects(false)
                                .ignoreContentType(true)
                                .execute();

                        Log.i("TEXTTEXT_STAT",res.statusMessage());
                        Log.i("TEXTTEXT_URL",res.url().toString());
                        Log.i("TESTTEXT",res.parse().location());
                        Log.i("TESTTEXT",res.parse().text());
                    }
                    catch(Exception e) {
                        Log.i("TESTTEXT",e.getMessage());
                    }
                    finally{
                        Log.i("TESTTEXT","inFinally");
                        fileStream.close();
                    }

                    /*

                    //Getting print form from index page
                    FormElement form = (FormElement) printPage.getElementsByTag("form").get(0);
                    //Getting FileToPrint element
                    Element fileToPrint = form.select("input").get(1);
                    fileToPrint.val("tasdasdasdasdasdfsadfasdf.txt");
                    //Submitting file upload form
                    Connection.Response fileUploadResponse = form.submit()
                            .cookies(loginPage.cookies())
                            .followRedirects(false)
                            .execute();
                    Document webPrintPage = fileUploadResponse.parse();

                    File file = new File(System.getProperty("user.home") + "/Desktop/test-upload.txt");
                    InputStream f = new FileInputStream(file);

                    Document document = Jsoup.connect("https://eop.vanier.college/webprint.cfm")
                            .followRedirects(false)
                            .data("FileToPrint",file.getName(),f)
                            .post();


                    Log.i("TESTTEXT FORM SUBMIT", webPrintPage.location());
                    Log.i("TESTTEXT DOCUMENT", document.location());
                    */
                    return "Success";
                }
                else {
                    return "Failed";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Caught exception";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("TESTTEXT",s);
            if (s.equals("Success")) {
                //fragment
                FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
                tf.replace(R.id.successfulLogin, new EveryonePrintFileUpload());
                tf.commit();
                usernameText.setVisibility(View.GONE);
                passwordText.setVisibility(View.GONE);
                loginBtn.setVisibility(View.GONE);
            } else {
                Toast.makeText(context,"Invalid Login",Toast.LENGTH_LONG).show();
                usernameText.setText("");
                passwordText.setText("");
                Log.i("TESTTEXT", s);
            }

        }
    }
}
/******  ATTEMPT WITH HTMLUNIT FAIL  ******/
             /*
                try {
                    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                    String url = "https://eop.vanier.college/login.cfm";
                    HttpPost post = new HttpPost(url);

                    List<NameValuePair> loginInfo = new ArrayList<NameValuePair>();
                    loginInfo.add(new BasicNameValuePair("LoginAction", "login"));
                    loginInfo.add(new BasicNameValuePair("Username", username));
                    loginInfo.add(new BasicNameValuePair("Password", password));
                    post.setEntity(new UrlEncodedFormEntity(loginInfo));

                    CloseableHttpResponse response = closeableHttpClient.execute(post);
                    Log.i("TESTTEXT",response.toString());
                    closeableHttpClient.close();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            */

/****** JSOUP ATTEMPT  ********/
             /*
                    document = Jsoup.connect("https://eop.vanier.college/webprint.cfm")
                            .followRedirects(true)
                            .post();
                    Log.i("TESTTEXT",document.location());
                    // here
                    return "Success";
                    /*
                    //Continuing to webprint.cfm
                    File file = new File(System.getProperty("user.home") + "/Desktop/test-upload.docx");
                    InputStream f = new FileInputStream(file);
                    document = Jsoup.connect("https://eop.vanier.college/webprint.cfm")
                            .followRedirects(false)
                            .data("type","file")
                            .data("FileToPrint",file.getName(),f)
                            .post();
                    Log.i("TESTTEXT",document.location());
             */

             /* Attempt at file upload
                    // ** FAILED ** //
                    File fileToUpload = new File(System.getProperty("user.home") + "/Desktop/test-upload.docx");
                    FileInputStream fileStream = new FileInputStream(fileToUpload);

                    Connection fileUpload = form.submit();
                    Connection.KeyVal fileUploadData = fileUpload.data("FileToPrint");
                    fileUploadData.value("test-upload.docx");
                    fileUploadData.inputStream(fileStream);
                    Connection.Response res;
                    try {
                        res = fileUpload
                                .execute();
                    } finally{
                        Log.i("TESTTEXT","inFinally");
                        fileStream.close();
                    }
                    Log.i("TESTTEXT",res.parse().html());
                    */

             /* Checking cookies content
                    HashMap<String,String> test = new HashMap<>(loginPage.cookies());
                    for (String name: test.keySet()) {
                        String value = test.get(name);
                        Log.i("TESTTEXT",name + " " + value);
                    }
             */

             /*  File from i.s
                    byte[] buffer = new byte[fileStream.available()];
                    fileStream.read(buffer);

                    File newFile = new File(getFilesDir(),"newfile.txt");
                    OutputStream outputStream = new FileOutputStream(newFile);
                    outputStream.write(buffer);
                    */


