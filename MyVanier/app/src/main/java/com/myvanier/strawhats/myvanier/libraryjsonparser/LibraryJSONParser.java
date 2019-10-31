package com.myvanier.strawhats.myvanier.libraryjsonparser;

import com.myvanier.strawhats.myvanier.dbController.Model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LibraryJSONParser
{
    public LibraryJSONParser()
    {

    }

    public List<Book> parse(JSONObject object)
    {
        final String BOOKS = "Books";
        final String TITLE = "title";
        final String AUTHOR = "author";

        List<Book> bookList = new ArrayList<>();

        try
        {
            JSONArray booksArray = object.getJSONArray(BOOKS);

            for(int i = 0; i < booksArray.length(); i++)
            {
                JSONObject bookJSON = (JSONObject) booksArray.get(i);
                Book book = new Book();

                book.setTitle(bookJSON.getString(TITLE));
                book.setAuthor(bookJSON.getString(AUTHOR));

                bookList.add(book);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return bookList;
    }
}
