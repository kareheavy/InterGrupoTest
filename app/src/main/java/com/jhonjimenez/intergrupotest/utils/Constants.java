package com.jhonjimenez.intergrupotest.utils;

import com.jhonjimenez.intergrupotest.models.Prospect;

public class Constants {

    public static final String BASE_URL = "http://directotesting.igapps.co/";

    //Tables room
    public static final String NAME_TABLE_USER = "user";
    public static final String NAME_TABLE_PROSPECT = "prospect";
    public static final String NAME_TABLE_PROSPECT_BACKUP = "prospect_backup";


    public static final int STATUS_ERROR = 1;

    //Prospects Status
    public static final int PENDING = 0;
    public static final int APPROVED = 1;
    public static final int ACCEPTED = 2;
    public static final int REJECTED = 3;
    public static final int DISABLED = 4;
    public static final String INTENT = "intent";
}
