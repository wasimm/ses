package edu.aku.hassannaqvi.ses.contracts;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class AssessmentContract {

    private static final String TAG = "Assessment_CONTRACT";

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.moringaPlantation";


    public static abstract class TableAssessment implements BaseColumns {

        public static final String TABLE_NAME = "assessment";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String _ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN__LUID = "_luid";
        public static final String COLUMN_SEEM_VID = "seem_vid";
        public static final String COLUMN_MAUC = "mauc";
        public static final String COLUMN_MAVI = "mavi";
        //public static final String COLUMN_MASYSDATE = "mpsysdate";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_PID = "pid";
        public static final String COLUMN_MA101 = "ma101";
        public static final String COLUMN_MA102 = "ma102";
        public static final String COLUMN_MA103 = "ma103";
        public static final String COLUMN_MA104 = "ma104";
        public static final String COLUMN_MA105 = "ma105";
        public static final String COLUMN_MA106 = "ma106";
        public static final String COLUMN_MA106x = "ma106x";
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

        public static final String SERVER_URI = "assessment.php";

        public static String PATH = "assessmentlist";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();

        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}