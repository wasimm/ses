package edu.aku.hassannaqvi.ses.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.moringaPlantation";

    public static abstract class FormsTable implements BaseColumns {
        public static final String TABLE_NAME = "form";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_SEEM_VID = "seem_vid";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_MP101 = "mp101";
        public static final String COLUMN_MP102 = "mp102";
        public static final String COLUMN_MP103 = "mp103";
        public static final String COLUMN_MP104 = "mp104";
        public static final String COLUMN_MP105 = "mp105";
        public static final String COLUMN_MP106 = "mp106";
        public static final String COLUMN_MP107 = "mp107";
        public static final String COLUMN_MP108 = "mp108";
        public static final String COLUMN_MP109 = "mp109";
        public static final String COLUMN_MP10910x = "mp10910x";
        public static final String COLUMN_MP110a = "mp110a";
        public static final String COLUMN_MP110b = "mp110b";
        public static final String COLUMN_MP110c = "mp110c";
        public static final String COLUMN_MP110d = "mp110d";

        //public static final String COLUMN_PID = "pid";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS96x = "istatus96x";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDATE = "gpsdate";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static String PATH = "forms";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();
        public static String SERVER_URL = "sync.php";


        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
