package edu.aku.hassannaqvi.ses.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.aku.hassannaqvi.ses.contracts.BLRandomContract.BLRandomTable;
import edu.aku.hassannaqvi.ses.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.ses.contracts.SchoolsContract;
import edu.aku.hassannaqvi.ses.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.ses.contracts.VersionAppContract;
import edu.aku.hassannaqvi.ses.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.ses.models.BLRandom;
import edu.aku.hassannaqvi.ses.models.Form;
import edu.aku.hassannaqvi.ses.models.Schools;
import edu.aku.hassannaqvi.ses.models.Users;
import edu.aku.hassannaqvi.ses.models.VersionApp;

import static edu.aku.hassannaqvi.ses.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_FORMS;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_SCHOOLS;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_VERSIONAPP;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_SCHOOLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int syncBLRandom(JSONArray blList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BLRandomTable.TABLE_NAME, null, null);

        JSONArray jsonArray = blList;
        int insertCount = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObjectCC = null;
            try {
                jsonObjectCC = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            BLRandom Vc = new BLRandom();
            Vc.Sync(jsonObjectCC);
            Log.d(TAG, "syncBLRandom: " + Vc.get_ID());
            ContentValues values = new ContentValues();

            values.put(BLRandomTable.COLUMN_ID, Vc.get_ID());
            values.put(BLRandomTable.COLUMN_LUID, Vc.getLUID());
            values.put(BLRandomTable.COLUMN_STRUCTURE_NO, Vc.getStructure());
            values.put(BLRandomTable.COLUMN_FAMILY_EXT_CODE, Vc.getExtension());
            values.put(BLRandomTable.COLUMN_HH, Vc.getHh());
            values.put(BLRandomTable.COLUMN_EB_CODE, Vc.getEbcode());
            values.put(BLRandomTable.COLUMN_P_CODE, Vc.getpCode());
            values.put(BLRandomTable.COLUMN_RANDOMDT, Vc.getRandomDT());
            values.put(BLRandomTable.COLUMN_HH_HEAD, Vc.getHhhead());
            values.put(BLRandomTable.COLUMN_CONTACT, Vc.getContact());
            values.put(BLRandomTable.COLUMN_HH_SELECTED_STRUCT, Vc.getSelStructure());
            values.put(BLRandomTable.COLUMN_SNO_HH, Vc.getSno());

            long row = db.insert(BLRandomTable.TABLE_NAME, null, values);
            if (row != -1) insertCount++;
        }
        return insertCount;
    }

    public Integer syncVersionApp(JSONObject VersionList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppContract.VersionAppTable.TABLE_NAME, null, null);
        long count = 0;
        try {
            JSONObject jsonObjectCC = ((JSONArray) VersionList.get(VersionAppContract.VersionAppTable.COLUMN_VERSION_PATH)).getJSONObject(0);
            VersionApp Vc = new VersionApp();
            Vc.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppContract.VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            count = db.insert(VersionAppContract.VersionAppTable.TABLE_NAME, null, values);
            if (count > 0) count = 1;

        } catch (Exception ignored) {
        } finally {
            db.close();
        }

        return (int) count;
    }

    public VersionApp getVersionApp() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VersionAppTable._ID,
                VersionAppTable.COLUMN_VERSION_CODE,
                VersionAppTable.COLUMN_VERSION_NAME,
                VersionAppTable.COLUMN_PATH_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionApp allVC = new VersionApp();
        try {
            c = db.query(
                    VersionAppTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allVC.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVC;
    }

    public int syncUser(JSONArray userList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < userList.length(); i++) {

                JSONObject jsonObjectUser = userList.getJSONObject(i);

                Users user = new Users();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
                values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
                values.put(UsersTable.COLUMN_FULL_NAME, user.getFull_name());
                long rowID = db.insert(UsersTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE " + UsersTable.COLUMN_USERNAME + "=? AND " + UsersTable.COLUMN_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {

                if (mCursor.moveToFirst()) {
//                    MainApp.DIST_ID = mCursor.getString(mCursor.getColumnIndex(Users.UsersTable.ROW_USERNAME));
                }
                return true;
            }
        }
        return false;
    }

    public Long addForm(Form form) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, form.getUid());
        values.put(FormsTable.COLUMN_DEVICEID, form.getDeviceid());
        values.put(FormsTable.COLUMN_DEVICETAGID, form.getDeviceid());
        values.put(FormsTable.COLUMN_USERNAME, form.getUsername());
        values.put(FormsTable.COLUMN_SYSDATE, form.getSysdate());
        values.put(FormsTable.COLUMN_GPSLAT, form.getGpslat());
        values.put(FormsTable.COLUMN_GPSLNG, form.getGpslng());
        values.put(FormsTable.COLUMN_GPSDATE, form.getGpsdate());
        values.put(FormsTable.COLUMN_GPSACC, form.getGpsacc());
        values.put(FormsTable.COLUMN_APPVERSION, form.getAppversion());
        values.put(FormsTable.COLUMN_ISTATUS, form.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS96x, form.getIstatus96x());
        values.put(FormsTable.COLUMN_SECTION_B, form.getSection_B());
        values.put(FormsTable.COLUMN_SECTION_C, form.getSection_C());
        values.put(FormsTable.COLUMN_SECTION_D, form.getSection_D());
        values.put(FormsTable.COLUMN_SECTION_E, form.getSection_E());
        values.put(FormsTable.COLUMN_SECTION_F, form.getSection_F());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public int updatesFormColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.getId())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.form.getId());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.form.getId())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.form.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS96x, MainApp.form.getIstatus96x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, MainApp.form.getEndingdatetime());

        // Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.getId())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public Collection<Form> getTodayForms(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_SECTION_B,
                FormsTable.COLUMN_SECTION_C,
                FormsTable.COLUMN_SECTION_D,
                FormsTable.COLUMN_SECTION_E,
                FormsTable.COLUMN_SECTION_F,

        };
        String whereClause = FormsTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                //form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                //form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                //form.setFormtype(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMTYPE)));
                //form.setUsername(c.getString(c.getColumnIndex(FormsTable.COLUMN_USERNAME)));
                //form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                //form.setMp101(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP101)));
                //form.setMp102(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP102)));
                //form.setMp103(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP103)));
                //form.setMp104(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP104)));
                //form.setMp105(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP105)));
                //form.setMp106(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP106)));
                //form.setMp107(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP107)));
                //form.setMp108(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP108)));
                //form.setPid(c.getString(c.getColumnIndex(FormsTable.COLUMN_PID)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> getFormsByCluster(String cluster) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                //FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                //FormsTable.COLUMN_FORMTYPE,
                //FormsTable.COLUMN_SYSDATE,
                //FormsTable.COLUMN_MP101,
                //FormsTable.COLUMN_MP102,
                //FormsTable.COLUMN_MP103,
                //FormsTable.COLUMN_ISTATUS,
                //FormsTable.COLUMN_SYNCED,

        };
        //String whereClause = FormsTable.COLUMN_MP101 + " = ? ";
        String[] whereArgs = new String[]{cluster};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<>();
        /*try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setFormtype(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMTYPE)));
                form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setMp101(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP101)));
                form.setMp102(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP102)));
                form.setMp103(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP103)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }*/
        return allForms;
    }

    public ArrayList<Form> getUnclosedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                //FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                //FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_SYSDATE,
                //FormsTable.COLUMN_MP102,
                //FormsTable.COLUMN_MP103,
                //FormsTable.COLUMN_MP101,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,
        };
        String whereClause = FormsTable.COLUMN_ISTATUS + " = ''";
        String[] whereArgs = null;
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";
        ArrayList<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                //form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                //form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                //form.setFormtype(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMTYPE)));
                //form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                //form.setMp101(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP101)));
                //form.setMp102(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP102)));
                //form.setMp103(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP103)));
                //form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                //form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> getUnsyncedForms() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_SECTION_B,
                FormsTable.COLUMN_SECTION_C,
                FormsTable.COLUMN_SECTION_D,
                FormsTable.COLUMN_SECTION_E,
                FormsTable.COLUMN_SECTION_F,
                FormsTable.COLUMN_ENDINGDATETIME,
        };

        String whereClause;
        String[] whereArgs;

        whereClause = FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " == ''";
        whereArgs = null;

        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                Form form = new Form();
                allForms.add(form.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    /*public int syncSchools_old(JSONArray schoolList) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SchoolsContract.TableSchool.TABLE_NAME, null, null);

        JSONArray jsonArray = schoolList;

        int insertCount = 0;
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObjectSchool = schoolList.getJSONObject(i);

            Schools S = new Schools();
            S.Sync(jsonObjectSchool);

            ContentValues values = new ContentValues();
            values.put(SchoolsContract.TableSchool.COLUMN_SERVER_ID, S.getServer_id());
            values.put(SchoolsContract.TableSchool.COLUMN_SEMIS_CODE, S.getSemis_code());
            values.put(SchoolsContract.TableSchool.COLUMN_DISTRICT, S.getDistrict());
            values.put(SchoolsContract.TableSchool.COLUMN_TEHSIL, S.getTehsil());
            values.put(SchoolsContract.TableSchool.COLUMN_NAME, S.getName());
            values.put(SchoolsContract.TableSchool.COLUMN_HEAD, S.getHead());
            values.put(SchoolsContract.TableSchool.COLUMN_MEDIUM, S.getMedium());
            values.put(SchoolsContract.TableSchool.COLUMN_S_LEVEL, S.getS_level());
            values.put(SchoolsContract.TableSchool.COLUMN_GENDER, S.getGender());
            values.put(SchoolsContract.TableSchool.COLUMN_STATUS, S.getStatus());
            values.put(SchoolsContract.TableSchool.COLUMN_ENROLLMENTS, S.getEnrollments());
            long row = db.insert(SchoolsContract.TableSchool.TABLE_NAME, null, values);
            if (row != -1) insertCount++;
        }
        return insertCount;
    }*/

    public int syncSchools(JSONArray schoolList) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SchoolsContract.TableSchool.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < schoolList.length(); i++) {

                JSONObject jsonObjectS = schoolList.getJSONObject(i);

                Schools school = new Schools();
                school.Sync(jsonObjectS);
                ContentValues values = new ContentValues();
                values.put(SchoolsContract.TableSchool.COLUMN_SERVER_ID, school.get_id());
                values.put(SchoolsContract.TableSchool.COLUMN_SEMIS_CODE, school.getSemisCode());
                values.put(SchoolsContract.TableSchool.COLUMN_DIVISION_CODE, school.getDivisionCode());
                values.put(SchoolsContract.TableSchool.COLUMN_DIVISION_NAME, school.getDivisionName());
                values.put(SchoolsContract.TableSchool.COLUMN_DISTRICT_CODE, school.getDistrictCode());
                values.put(SchoolsContract.TableSchool.COLUMN_DISTRICT_NAME, school.getDistrictName());
                values.put(SchoolsContract.TableSchool.COLUMN_TEHSIL_CODE, school.getTehsilCode());
                values.put(SchoolsContract.TableSchool.COLUMN_TEHSIL_NAME, school.getTehsilName());
                values.put(SchoolsContract.TableSchool.COLUMN_S_NAME, school.getsName());
                values.put(SchoolsContract.TableSchool.COLUMN_S_HEAD, school.getsHead());
                values.put(SchoolsContract.TableSchool.COLUMN_S_LEVEL, school.getsLevel());
                values.put(SchoolsContract.TableSchool.COLUMN_S_TYPE, school.getsType());
                values.put(SchoolsContract.TableSchool.COLUMN_S_MEDIUM, school.getsMedium());
                values.put(SchoolsContract.TableSchool.COLUMN_S_CURRENT_STATUS, school.getsCurrentStatus());
                values.put(SchoolsContract.TableSchool.COLUMN_STATUS, 0);
                long rowID = db.insert(SchoolsContract.TableSchool.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncSchool(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public Cursor getRecords() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from schools", null);
        return res;
    }

    public Cursor getSchool(String semisCode) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from schools where semisCode = '" + semisCode + "'", null);
        return res;
    }

}