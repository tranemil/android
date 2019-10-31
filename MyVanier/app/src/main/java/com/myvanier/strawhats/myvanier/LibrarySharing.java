package com.myvanier.strawhats.myvanier;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myvanier.strawhats.myvanier.dbController.Model.Book;
import com.myvanier.strawhats.myvanier.dbController.PersonalLibraryController;
import com.myvanier.strawhats.myvanier.libraryjsonparser.LibraryJSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LibrarySharing extends AppCompatActivity implements View.OnClickListener{

    private Button importBtn;
    private Button exportBtn;

    final private int FILE_REQUEST_CODE = 123;

    PersonalLibraryController personalLibraryController = new PersonalLibraryController(this);

    List<Book> bookList;

    JSONArray jsonArray = new JSONArray();

    String JSONString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_sharing);
        importBtn = (Button) findViewById(R.id.btn_import);
        exportBtn = (Button) findViewById(R.id.btn_export);
        importBtn.setOnClickListener(this);
        exportBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_import:
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, FILE_REQUEST_CODE);
                break;

            case R.id.btn_export:
                bookList = personalLibraryController.readAll();
                Book book;

                File myFile = getPrivateLibraryStorageDir(this, "PersonalLibrary");

                File file = new File(myFile.getAbsolutePath(), "PersonalLibraryBooks");
                file.setWritable(true);
                file.setReadable(true);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try
                {
                    FileWriter writer = new FileWriter(file);

                    for(int i = 0; i < bookList.size(); i++)
                    {
                        book = bookList.get(i);
                        JSONObject bookJSON = toJSONObject(book);
                        jsonArray.put(bookJSON);
                    }
                    JSONObject bookListJSON = new JSONObject();
                    bookListJSON.put("Books", jsonArray);
                    JSONString = bookListJSON.toString();
                    writer.append(JSONString);

                    writer.flush();
                    writer.close();

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setType("text/plain");
                    String message = "File to be shared is " + file.getName() + ".";
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (emailIntent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivity(Intent.createChooser(emailIntent, "Send Email"));
                        finish();
                    }

                    Toast.makeText(this, "Successfully Exported", Toast.LENGTH_SHORT).show();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK)
        {
            String filePath = data.getData().getPath();

            try
            {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while((line = reader.readLine()) != null)
                {
                    stringBuilder.append(line);
                }
                inputStream.close();

                addLibraryFileToPersonal(stringBuilder.toString());
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void addLibraryFileToPersonal(String importedLibrary)
    {
        try
        {
            JSONObject object = new JSONObject(importedLibrary);
            LibraryJSONParser libraryJSONParser = new LibraryJSONParser();
            List<Book> bookList = libraryJSONParser.parse(object);

            for(Book book : bookList)
            {
                personalLibraryController.createBook(book);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject(Book book)
    {
        JSONObject bookJSON = new JSONObject();
        try
        {
            bookJSON.put("title", book.getTitle());
            bookJSON.put("author", book.getAuthor());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return bookJSON;
    }



    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getPrivateLibraryStorageDir(Context context, String libraryName)
    {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath(), libraryName);
        if (!file.mkdirs()) {
            Log.e("oof", "Directory not created");
        }
        return file;
    }
}
