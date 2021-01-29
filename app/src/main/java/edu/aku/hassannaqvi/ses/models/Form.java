package edu.aku.hassannaqvi.ses.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ses.contracts.FormsContract.FormsTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Form extends LiveData<Form> {

    private final String projectName = "SES";
    // Form Variables
    public String section_B = "";
    public String section_C = "";
    public String section_D = "";
    public String section_E = "";
    private String endingdatetime = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String gpslat = "";
    private String gpslng = "";
    private String gpsdate = "";
    private String gpsacc = "";
    private String deviceid = "";
    private String tagid = "";
    public String section_F = "";
    // Other Variables
    private String id = "id";
    private String uid = "uid";
    private String istatus = "";
    private String istatus96x = "";
    private String sysdate = "";
    private String username = "";

    public Form() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus96x() {
        return istatus96x;
    }

    public void setIstatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }

    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getGpslat() {
        return gpslat;
    }

    public void setGpslat(String gpslat) {
        this.gpslat = gpslat;
    }

    public String getGpslng() {
        return gpslng;
    }

    public void setGpslng(String gpslng) {
        this.gpslng = gpslng;
    }

    public String getGpsdate() {
        return gpsdate;
    }

    public void setGpsdate(String gpsdate) {
        this.gpsdate = gpsdate;
    }

    public String getGpsacc() {
        return gpsacc;
    }

    public void setGpsacc(String gpsacc) {
        this.gpsacc = gpsacc;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getSection_B() {
        return section_B;
    }

    public void setSection_B(String section_B) {
        this.section_B = section_B;
    }

    public String getSection_C() {
        return section_C;
    }

    public void setSection_C(String section_C) {
        this.section_C = section_C;
    }

    public String getSection_D() {
        return section_D;
    }

    public void setSection_D(String section_D) {
        this.section_D = section_D;
    }

    public String getSection_E() {
        return section_E;
    }

    public void setSection_E(String section_E) {
        this.section_E = section_E;
    }

    public String getSection_F() {
        return section_F;
    }

    public void setSection_F(String section_F) {
        this.section_F = section_F;
    }

    public Form Sync(JSONObject jsonObject) throws JSONException {

        this.id = jsonObject.getString(FormsTable.COLUMN_ID);
        this.uid = jsonObject.getString(FormsTable.COLUMN_UID);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsTable.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APPVERSION);
        this.gpslat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpslng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsdate = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsacc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceid = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.tagid = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.sysdate = jsonObject.getString(FormsTable.COLUMN_SYSDATE);
        this.username = jsonObject.getString(FormsTable.COLUMN_USERNAME);
        this.section_B = jsonObject.getString(FormsTable.COLUMN_SECTION_B);
        this.section_C = jsonObject.getString(FormsTable.COLUMN_SECTION_C);
        this.section_D = jsonObject.getString(FormsTable.COLUMN_SECTION_D);
        this.section_E = jsonObject.getString(FormsTable.COLUMN_SECTION_E);
        this.section_F = jsonObject.getString(FormsTable.COLUMN_SECTION_F);

        return this;

    }


    public Form Hydrate(Cursor cursor) {

        this.id = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
        this.gpslat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpslng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsdate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsacc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.tagid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.sysdate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYSDATE));
        this.username = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USERNAME));
        this.section_B = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SECTION_B));
        this.section_C = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SECTION_C));
        this.section_D = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SECTION_D));
        this.section_E = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SECTION_E));
        this.section_F = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SECTION_F));

        return this;
    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable.COLUMN_ID, this.id == null ? JSONObject.NULL : this.id);
        json.put(FormsTable.COLUMN_UID, this.uid == null ? JSONObject.NULL : this.uid);
        json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsTable.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
        json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
        json.put(FormsTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(FormsTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);
        json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(FormsTable.COLUMN_GPSLAT, this.gpslat == null ? JSONObject.NULL : this.gpslat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpslng == null ? JSONObject.NULL : this.gpslng);
        json.put(FormsTable.COLUMN_GPSDATE, this.gpsdate == null ? JSONObject.NULL : this.gpsdate);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsacc == null ? JSONObject.NULL : this.gpsacc);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
        json.put(FormsTable.COLUMN_DEVICETAGID, this.tagid == null ? JSONObject.NULL : this.tagid);
        json.put(FormsTable.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
        json.put(FormsTable.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);

        if (!this.section_B.equals("")) {
            json.put(FormsTable.COLUMN_SECTION_B, this.section_B.equals("") ? JSONObject.NULL : new JSONObject(this.section_B));
        }

        if (!this.section_C.equals("")) {
            json.put(FormsTable.COLUMN_SECTION_C, this.section_C.equals("") ? JSONObject.NULL : new JSONObject(this.section_C));
        }

        if (!this.section_D.equals("")) {
            json.put(FormsTable.COLUMN_SECTION_D, this.section_D.equals("") ? JSONObject.NULL : new JSONObject(this.section_D));
        }

        if (!this.section_E.equals("")) {
            json.put(FormsTable.COLUMN_SECTION_E, this.section_E.equals("") ? JSONObject.NULL : new JSONObject(this.section_E));
        }

        if (!this.section_F.equals("")) {
            json.put(FormsTable.COLUMN_SECTION_F, this.section_F.equals("") ? JSONObject.NULL : new JSONObject(this.section_F));
        }

        return json;
    }
}
