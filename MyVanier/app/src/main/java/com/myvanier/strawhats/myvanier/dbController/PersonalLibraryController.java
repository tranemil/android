package com.myvanier.strawhats.myvanier.dbController;

import android.content.Context;

public class PersonalLibraryController extends LibraryController
{

    /**
     * Instantiates the PersonalLibrary Controller
     *
     * @param context
     */
    public PersonalLibraryController(Context context) {
        super(context);
        setTableName("PersonalLibrary");
    }
}
