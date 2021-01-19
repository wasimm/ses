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

import edu.aku.hassannaqvi.ses.contracts.AssessmentContract;
import edu.aku.hassannaqvi.ses.contracts.BLRandomContract.BLRandomTable;
import edu.aku.hassannaqvi.ses.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.ses.contracts.UsersContract;
import edu.aku.hassannaqvi.ses.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.ses.contracts.VersionAppContract;
import edu.aku.hassannaqvi.ses.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.ses.contracts.VillagesContract;
import edu.aku.hassannaqvi.ses.models.Assessment;
import edu.aku.hassannaqvi.ses.models.BLRandom;
import edu.aku.hassannaqvi.ses.models.Form;
import edu.aku.hassannaqvi.ses.models.Users;
import edu.aku.hassannaqvi.ses.models.VersionApp;
import edu.aku.hassannaqvi.ses.models.Villages;

import static edu.aku.hassannaqvi.ses.core.MainApp.assessment;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_ASSESSMENT;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_BL_RANDOM;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_FORMS;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_VERSIONAPP;
import static edu.aku.hassannaqvi.ses.utils.CreateTable.SQL_CREATE_VILLAGES;


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

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_VILLAGES);
        db.execSQL(SQL_CREATE_BL_RANDOM);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_ASSESSMENT);
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

    public int syncVillage(JSONArray villageList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillagesContract.TableVillage.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < villageList.length(); i++) {

                JSONObject jsonObjectVil = villageList.getJSONObject(i);

                Villages village = new Villages();
                village.Sync(jsonObjectVil);
                ContentValues values = new ContentValues();

                values.put(VillagesContract.TableVillage.COLUMN_UCNAME, village.getUcname());
                values.put(VillagesContract.TableVillage.COLUMN_VILLAGE_NAME, village.getVillagename());
                values.put(VillagesContract.TableVillage.COLUMN_SEEM_VID, village.getSeem_vid());
                values.put(VillagesContract.TableVillage.COLUMN_UCID, village.getUcid());
                long rowID = db.insert(VillagesContract.TableVillage.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncVillage(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int syncAssessment(JSONArray AssessmentList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(AssessmentContract.TableAssessment.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < AssessmentList.length(); i++) {

                JSONObject jsonObjectVil = AssessmentList.getJSONObject(i);

                Assessment assessment = new Assessment();
                assessment.Sync(jsonObjectVil);
                ContentValues values = new ContentValues();
                values.put(AssessmentContract.TableAssessment.COLUMN__LUID, assessment.get_luid());
                //values.put(AssessmentContract.TableAssessment.COLUMN_MASYSDATE, assessment.getMasysdate());
                //values.put(AssessmentContract.TableAssessment.COLUMN_PID, assessment.getPid());
                values.put(AssessmentContract.TableAssessment.COLUMN_SEEM_VID, assessment.getSeem_vid());
                long rowID = db.insert(AssessmentContract.TableAssessment.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncAssessment(e): " + e);
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

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, form.getProjectName());
        values.put(FormsTable.COLUMN_UID, form.get_UID());
        values.put(FormsTable.COLUMN_SEEM_VID, form.getSeem_vid());
        //values.put(FormsTable.COLUMN_MPSYSDATE, form.getMpsysdate());
        values.put(FormsTable.COLUMN_FORMTYPE, form.getFormtype());
        values.put(FormsTable.COLUMN_USERNAME, form.getUsername());
        values.put(FormsTable.COLUMN_SYSDATE, form.getSysdate());
        values.put(FormsTable.COLUMN_MP101, form.getMp101());
        values.put(FormsTable.COLUMN_MP102, form.getMp102());
        values.put(FormsTable.COLUMN_MP103, form.getMp103());
        values.put(FormsTable.COLUMN_MP104, form.getMp104());
        values.put(FormsTable.COLUMN_MP105, form.getMp105());
        values.put(FormsTable.COLUMN_MP106, form.getMp106());
        values.put(FormsTable.COLUMN_MP107, form.getMp107());
        values.put(FormsTable.COLUMN_MP108, form.getMp108());
        values.put(FormsTable.COLUMN_MP109, form.getMp109());
        values.put(FormsTable.COLUMN_MP10910x, form.getmp10910x());
        values.put(FormsTable.COLUMN_MP110a, form.getMp110a());
        values.put(FormsTable.COLUMN_MP110b, form.getmp110b());
        values.put(FormsTable.COLUMN_MP110c, form.getmp110c());
        values.put(FormsTable.COLUMN_MP110d, form.getmp110d());
        //values.put(FormsTable.COLUMN_PID, form.getPid());
        values.put(FormsTable.COLUMN_ISTATUS, form.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS96x, form.getIstatus96x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, form.getEndingdatetime());
        values.put(FormsTable.COLUMN_GPSLAT, form.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, form.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDATE, form.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, form.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICETAGID, form.getDevicetagID());
        values.put(FormsTable.COLUMN_DEVICEID, form.getDeviceID());
        values.put(FormsTable.COLUMN_APPVERSION, form.getAppversion());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addAssessment(Assessment assessment) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AssessmentContract.TableAssessment.COLUMN_PROJECT_NAME, assessment.getProjectName());
        values.put(AssessmentContract.TableAssessment.COLUMN__LUID, assessment.get_luid());
        values.put(AssessmentContract.TableAssessment.COLUMN_UID, assessment.getUid());

        values.put(AssessmentContract.TableAssessment.COLUMN_MAUC, assessment.getMauc());
        values.put(AssessmentContract.TableAssessment.COLUMN_MAVI, assessment.getMavi());
        values.put(AssessmentContract.TableAssessment.COLUMN_PID, assessment.getPid());


        values.put(AssessmentContract.TableAssessment.COLUMN_SEEM_VID, assessment.getSeem_vid());
        //values.put(AssessmentContract.TableAssessment.COLUMN_MASYSDATE, assessment.getMasysdate());
        values.put(AssessmentContract.TableAssessment.COLUMN_FORMTYPE, assessment.getFormtype());
        values.put(AssessmentContract.TableAssessment.COLUMN_USERNAME, assessment.getUsername());
        values.put(AssessmentContract.TableAssessment.COLUMN_SYSDATE, assessment.getSysdate());
        values.put(AssessmentContract.TableAssessment.COLUMN_MA101, assessment.getMa101());
        values.put(AssessmentContract.TableAssessment.COLUMN_MA102, assessment.getMa102());
        values.put(AssessmentContract.TableAssessment.COLUMN_MA103, assessment.getMa103());
        values.put(AssessmentContract.TableAssessment.COLUMN_MA104, assessment.getMa104());
        values.put(AssessmentContract.TableAssessment.COLUMN_MA105, assessment.getMa105());
        values.put(AssessmentContract.TableAssessment.COLUMN_MA106, assessment.getMa106());
        //values.put(AssessmentContract.TableAssessment.COLUMN_PID, assessment.getPid());
        values.put(AssessmentContract.TableAssessment.COLUMN_ISTATUS, assessment.getIstatus());
        values.put(AssessmentContract.TableAssessment.COLUMN_ISTATUS96x, assessment.getIstatus96x());
        values.put(AssessmentContract.TableAssessment.COLUMN_ENDINGDATETIME, assessment.getEndingdatetime());
        values.put(AssessmentContract.TableAssessment.COLUMN_GPSLAT, assessment.getGpslat());
        values.put(AssessmentContract.TableAssessment.COLUMN_GPSLNG, assessment.getGpslat());
        values.put(AssessmentContract.TableAssessment.COLUMN_GPSDATE, assessment.getGpsdate());
        values.put(AssessmentContract.TableAssessment.COLUMN_GPSACC, assessment.getGpsacc());
        values.put(AssessmentContract.TableAssessment.COLUMN_DEVICETAGID, assessment.getDeviceTagId());
        values.put(AssessmentContract.TableAssessment.COLUMN_DEVICEID, assessment.getDeviceid());
        values.put(AssessmentContract.TableAssessment.COLUMN_APPVERSION, assessment.getAppversion());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                AssessmentContract.TableAssessment.TABLE_NAME,
                AssessmentContract.TableAssessment.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.form.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateAssessmentID() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(AssessmentContract.TableAssessment._ID, assessment.getUid());

        // Which row to update, based on the ID
        String selection = AssessmentContract.TableAssessment._ID + " = ?";
        String[] selectionArgs = {String.valueOf(assessment.getUid())};

        int count = db.update(AssessmentContract.TableAssessment.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<Form> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_MP101,
                FormsTable.COLUMN_MP102,
                FormsTable.COLUMN_MP103,
                FormsTable.COLUMN_MP104,
                FormsTable.COLUMN_MP105,
                FormsTable.COLUMN_MP106,
                FormsTable.COLUMN_MP107,
                FormsTable.COLUMN_MP108,
                FormsTable.COLUMN_MP109,
                FormsTable.COLUMN_MP10910x,
                FormsTable.COLUMN_MP110a,
                FormsTable.COLUMN_MP110b,
                FormsTable.COLUMN_MP110c,
                FormsTable.COLUMN_MP110d,
                //FormsTable.COLUMN_PID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<Form>();
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

    public Collection<Form> checkFormExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_MP101,
                FormsTable.COLUMN_MP102,
                FormsTable.COLUMN_MP103,
                FormsTable.COLUMN_MP104,
                FormsTable.COLUMN_MP105,
                FormsTable.COLUMN_MP106,
                FormsTable.COLUMN_MP107,
                FormsTable.COLUMN_MP108,
                //FormsTable.COLUMN_PID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<Form>();
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

    public Collection<Form> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_MP101,
                FormsTable.COLUMN_MP102,
                FormsTable.COLUMN_MP103,
                FormsTable.COLUMN_MP104,
                FormsTable.COLUMN_MP105,
                FormsTable.COLUMN_MP106,
                FormsTable.COLUMN_MP107,
                FormsTable.COLUMN_MP108,
                FormsTable.COLUMN_MP109,
                FormsTable.COLUMN_MP10910x,
                FormsTable.COLUMN_MP110a,
                FormsTable.COLUMN_MP110b,
                FormsTable.COLUMN_MP110c,
                FormsTable.COLUMN_MP110d,
                //FormsTable.COLUMN_PID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
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

    //Generic Un-Synced Forms
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

    public Collection<Form> getTodayForms(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_MP101,
                FormsTable.COLUMN_MP102,
                FormsTable.COLUMN_MP103,
                FormsTable.COLUMN_MP104,
                FormsTable.COLUMN_MP105,
                FormsTable.COLUMN_MP106,
                FormsTable.COLUMN_MP107,
                FormsTable.COLUMN_MP108,
                //FormsTable.COLUMN_PID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

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
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setFormtype(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMTYPE)));
                form.setUsername(c.getString(c.getColumnIndex(FormsTable.COLUMN_USERNAME)));
                form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setMp101(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP101)));
                form.setMp102(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP102)));
                form.setMp103(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP103)));
                form.setMp104(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP104)));
                form.setMp105(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP105)));
                form.setMp106(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP106)));
                form.setMp107(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP107)));
                form.setMp108(c.getString(c.getColumnIndex(FormsTable.COLUMN_MP108)));
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
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_MP101,
                FormsTable.COLUMN_MP102,
                FormsTable.COLUMN_MP103,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_MP101 + " = ? ";
        String[] whereArgs = new String[]{cluster};
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
        }
        return allForms;
    }

    public ArrayList<Form> getUnclosedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_MP102,
                FormsTable.COLUMN_MP103,
                FormsTable.COLUMN_MP101,
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
        }
        return allForms;
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
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int updateAssesmentEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(AssessmentContract.TableAssessment.COLUMN_ISTATUS, assessment.getIstatus());
        values.put(AssessmentContract.TableAssessment.COLUMN_ISTATUS96x, MainApp.assessment.getIstatus96x());
        values.put(AssessmentContract.TableAssessment.COLUMN_ENDINGDATETIME, MainApp.assessment.getEndingdatetime());

        // Which row to update, based on the ID
        String selection = AssessmentContract.TableAssessment._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.assessment.get_ID())};

        return db.update(AssessmentContract.TableAssessment.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Get BLRandom data
    public BLRandom getHHFromBLRandom(String subAreaCode, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;

        String[] columns = {
                BLRandomTable.COLUMN_ID,
                BLRandomTable.COLUMN_LUID,
                BLRandomTable.COLUMN_STRUCTURE_NO,
                BLRandomTable.COLUMN_FAMILY_EXT_CODE,
                BLRandomTable.COLUMN_HH,
                BLRandomTable.COLUMN_P_CODE,
                BLRandomTable.COLUMN_EB_CODE,
                BLRandomTable.COLUMN_RANDOMDT,
                BLRandomTable.COLUMN_HH_SELECTED_STRUCT,
                BLRandomTable.COLUMN_CONTACT,
                BLRandomTable.COLUMN_HH_HEAD,
                BLRandomTable.COLUMN_SNO_HH
        };

        String whereClause = BLRandomTable.COLUMN_P_CODE + "=? AND " + BLRandomTable.COLUMN_HH + "=?";
        String[] whereArgs = new String[]{subAreaCode, hh};
        String groupBy = null;
        String having = null;

        String orderBy =
                BLRandomTable.COLUMN_ID + " ASC";

        BLRandom allBL = null;
        try {
            c = db.query(
                    BLRandomTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allBL = new BLRandom().hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    //Get Form already exist
    public Form getFilledForm(String district, String refno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SEEM_VID,
                //FormsTable.COLUMN_MPSYSDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_MP101,
                FormsTable.COLUMN_MP102,
                FormsTable.COLUMN_MP103,
                FormsTable.COLUMN_MP104,
                FormsTable.COLUMN_MP105,
                FormsTable.COLUMN_MP106,
                FormsTable.COLUMN_MP107,
                FormsTable.COLUMN_MP108,
                //FormsTable.COLUMN_PID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION
        };

//        String whereClause = "(" + FormsTable.COLUMN_ISTATUS + " is null OR " + FormsTable.COLUMN_ISTATUS + "='') AND " + FormsTable.COLUMN_CLUSTERCODE + "=? AND " + FormsTable.COLUMN_HHNO + "=?";
        String whereClause = FormsTable.COLUMN_MP102 + "=? AND " + FormsTable.COLUMN_MP101 + "=?";
        String[] whereArgs = {district, refno};
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable._ID + " ASC";
        Form allForms = null;
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
                allForms = new Form().Hydrate(c);
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

    //Generic update FormColumn
    public int updatesFormColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    // ANDROID DATABASE MANAGER
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


    public Collection<Users> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                UsersTable.COLUMN_USERNAME,
                UsersTable.COLUMN_FULL_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = UsersTable.COLUMN_USERNAME + " ASC";

        Collection<Users> alluser = new ArrayList<>();
        try {
            c = db.query(
                    UsersContract.UsersTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                alluser.add(new Users().Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return alluser;
    }


    public Collection<Villages> getVillage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillagesContract.TableVillage.COLUMN_UCNAME,
                VillagesContract.TableVillage.COLUMN_VILLAGE_NAME,
                VillagesContract.TableVillage.COLUMN_SEEM_VID,
                VillagesContract.TableVillage.COLUMN_UCID
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                VillagesContract.TableVillage.COLUMN_UCNAME + " ASC";

        Collection<Villages> allVil = new ArrayList<Villages>();
        try {
            c = db.query(
                    VillagesContract.TableVillage.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Villages vil = new Villages();
                allVil.add(vil.HydrateUc(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVil;
    }


    public Collection<Villages> getVillageUc() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                "DISTINCT " + VillagesContract.TableVillage.COLUMN_UCNAME,
                VillagesContract.TableVillage.COLUMN_SEEM_VID,
                VillagesContract.TableVillage.COLUMN_UCID,
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = VillagesContract.TableVillage.COLUMN_UCNAME;
        String having = null;

        String orderBy =
                VillagesContract.TableVillage.COLUMN_UCNAME + " ASC";

        Collection<Villages> allVil = new ArrayList<Villages>();
        try {
            c = db.query(
                    VillagesContract.TableVillage.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Villages vil = new Villages();
                allVil.add(vil.HydrateUc(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVil;
    }


    public Assessment getAssessment(String fUP) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                AssessmentContract.TableAssessment.COLUMN__LUID,
                //AssessmentContract.TableAssessment.COLUMN_MASYSDATE,
                //AssessmentContract.TableAssessment.COLUMN_PID,
                AssessmentContract.TableAssessment.COLUMN_SEEM_VID,
        };

        String whereClause = AssessmentContract.TableAssessment._ID + "=?";
        String[] whereArgs = {fUP};
        String groupBy = null;
        String having = null;

        String orderBy = AssessmentContract.TableAssessment._ID + " ASC";

        Assessment allAssessment = null;
        try {
            c = db.query(
                    AssessmentContract.TableAssessment.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allAssessment = new Assessment().HydrateA(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allAssessment;
    }


    public Collection<Villages> getVillageByUc(String uc) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillagesContract.TableVillage.COLUMN_VILLAGE_NAME,
                VillagesContract.TableVillage.COLUMN_SEEM_VID
        };

        String whereClause = VillagesContract.TableVillage.COLUMN_UCNAME + "=?";
        String[] whereArgs = new String[]{uc};
        String groupBy = null;
        String having = null;

        String orderBy =
                VillagesContract.TableVillage.COLUMN_UCNAME + " ASC";

        Collection<Villages> allVil = new ArrayList<Villages>();
        try {
            c = db.query(
                    VillagesContract.TableVillage.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Villages vil = new Villages();
                allVil.add(vil.HydrateVil(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVil;
    }


    public Collection<Assessment> getUnsyncedAssesmentForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                AssessmentContract.TableAssessment._ID,
                AssessmentContract.TableAssessment.COLUMN_UID,
                AssessmentContract.TableAssessment.COLUMN__LUID,
                AssessmentContract.TableAssessment.COLUMN_SEEM_VID,
                AssessmentContract.TableAssessment.COLUMN_MAUC,
                AssessmentContract.TableAssessment.COLUMN_MAVI,
                AssessmentContract.TableAssessment.COLUMN_FORMTYPE,
                AssessmentContract.TableAssessment.COLUMN_USERNAME,
                AssessmentContract.TableAssessment.COLUMN_SYSDATE,
                AssessmentContract.TableAssessment.COLUMN_PID,
                AssessmentContract.TableAssessment.COLUMN_MA101,
                AssessmentContract.TableAssessment.COLUMN_MA102,
                AssessmentContract.TableAssessment.COLUMN_MA103,
                AssessmentContract.TableAssessment.COLUMN_MA104,
                AssessmentContract.TableAssessment.COLUMN_MA105,
                AssessmentContract.TableAssessment.COLUMN_MA106,
                AssessmentContract.TableAssessment.COLUMN_ISTATUS,
                AssessmentContract.TableAssessment.COLUMN_ISTATUS96x,
                AssessmentContract.TableAssessment.COLUMN_GPSLAT,
                AssessmentContract.TableAssessment.COLUMN_GPSLNG,
                AssessmentContract.TableAssessment.COLUMN_GPSDATE,
                AssessmentContract.TableAssessment.COLUMN_GPSACC,
                AssessmentContract.TableAssessment.COLUMN_APPVERSION,
                AssessmentContract.TableAssessment.COLUMN_ENDINGDATETIME,
                AssessmentContract.TableAssessment.COLUMN_DEVICETAGID,
                AssessmentContract.TableAssessment.COLUMN_DEVICEID,
        };

        String whereClause;
        String[] whereArgs;

        whereClause = AssessmentContract.TableAssessment.COLUMN_SYNCED + " is null OR " + AssessmentContract.TableAssessment.COLUMN_SYNCED + " == ''";
        whereArgs = null;

        String groupBy = null;
        String having = null;
        String orderBy = AssessmentContract.TableAssessment._ID + " ASC";

        Collection<Assessment> allForms = new ArrayList<>();
        try {
            c = db.query(
                    AssessmentContract.TableAssessment.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Assessment form = new Assessment();
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

    //Generic Un-Synced Forms
    public void updateSyncedAssesmentForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(AssessmentContract.TableAssessment.COLUMN_SYNCED, true);
        values.put(AssessmentContract.TableAssessment.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = AssessmentContract.TableAssessment._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                AssessmentContract.TableAssessment.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public void resetAll() {

        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, (byte[]) null);
        values.put(FormsTable.COLUMN_SYNCED_DATE, (byte[]) null);

        // Which row to update, based on the title
        String where = null;
        String[] whereArgs = null;

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);


        ContentValues values2 = new ContentValues();
        values2.put(AssessmentContract.TableAssessment.COLUMN_SYNCED, (byte[]) null);
        values2.put(AssessmentContract.TableAssessment.COLUMN_SYNCED_DATE, (byte[]) null);

        // Which row to update, based on the title
        String where2 = null;
        String[] whereArgs2 = null;

        int count2 = db.update(
                AssessmentContract.TableAssessment.TABLE_NAME,
                values2,
                where2,
                whereArgs2);
    }


    public int getPID(int uc) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select max(cast(mp106 as int)) as mp106 from form where cast((substr(seem_vid, 2, 1)) as int) = " + uc, null);
        res.moveToFirst();
        int pid = Integer.parseInt(res.getString(res.getColumnIndex("mp106")));
        res.close();
        db.close();
        return pid;
    }

    public int getRecord(int uc) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select cast((substr(seem_vid, 2, 1)) as int) as mp106 from form where cast((substr(seem_vid, 2, 1)) as int) = " + uc, null);
        return res.getCount();
    }

    public Cursor getRecords(int uc) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from form where cast((SUBSTR(seem_vid, 2, 1)) as int) = " + uc + " and mp106 not in(select pid from assessment)", null);
        return res;
    }


    public String getFormUID(String tableName, String pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select _uid from form where mp106 = '" + pid + "'", null);
        res.moveToFirst();
        String formUID = res.getString(res.getColumnIndex("_uid"));
        return formUID;
    }

    //Generic update AssessmentColumn
    public int updatesAssessmentColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);
        //values.put("_luid", getValueByColumn("form", pid));

        String selection = AssessmentContract.TableAssessment._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.assessment.get_ID())};

        return db.update(AssessmentContract.TableAssessment.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}