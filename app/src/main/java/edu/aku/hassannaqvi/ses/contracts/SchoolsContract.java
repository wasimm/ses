package edu.aku.hassannaqvi.ses.contracts;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class SchoolsContract {

    private static final String TAG = "Schools_CONTRACT";

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.SES";


    public static abstract class TableSchool implements BaseColumns {

        public static final String TABLE_NAME = "schools";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SERVER_ID = "_id";
        public static final String COLUMN_SEMIS_CODE = "semisCode";
        public static final String COLUMN_DIVISION_CODE = "divisionCode";
        public static final String COLUMN_DIVISION_NAME = "divisionName";
        public static final String COLUMN_DISTRICT_CODE = "districtCode";
        public static final String COLUMN_DISTRICT_NAME = "districtName";
        public static final String COLUMN_TEHSIL_CODE = "tehsilCode";
        public static final String COLUMN_TEHSIL_NAME = "tehsilName";
        public static final String COLUMN_S_NAME = "sName";
        public static final String COLUMN_S_HEAD = "sHead";
        public static final String COLUMN_S_LEVEL = "sLevel";
        public static final String COLUMN_S_TYPE = "sType";
        public static final String COLUMN_S_MEDIUM = "sMedium";
        public static final String COLUMN_S_CURRENT_STATUS = "sCurrentStatus";
        public static final String COLUMN_STATUS = "status";

        public static final String SERVER_URI = "schools.php";
        public static String PATH = "schools";

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