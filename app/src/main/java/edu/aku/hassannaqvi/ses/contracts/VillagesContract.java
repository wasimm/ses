package edu.aku.hassannaqvi.ses.contracts;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class VillagesContract {

    private static final String TAG = "Villages_CONTRACT";

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.SES";


    public static abstract class TableVillage implements BaseColumns {

        public static final String TABLE_NAME = "villages";

        public static final String _ID = "id";
        public static final String COLUMN_UCNAME = "ucname";
        public static final String COLUMN_VILLAGE_NAME = "villagename";
        public static final String COLUMN_SEEM_VID = "seem_vid";
        public static final String COLUMN_UCID = "ucid";

        public static final String SERVER_URI = "villages.php";

        public static String PATH = "villages";

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