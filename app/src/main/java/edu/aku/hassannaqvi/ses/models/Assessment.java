package edu.aku.hassannaqvi.ses.models;


import android.database.Cursor;

import androidx.lifecycle.LiveData;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ses.contracts.AssessmentContract;


public class Assessment extends LiveData<Assessment> {

    private static final String TAG = "Assessment_CONTRACT";

    private final String projectName = "moringa_plantation";
    String pid = "";
    private String _ID = "";
    private String uid = "";
    private String _luid = "";
    private String seem_vid = "";
    private String masysdate = "";
    private String formtype = "";
    private String username;
    private String sysdate = "";
    private String ma101 = "";
    private String ma102 = "";
    private String ma103 = "";
    private String ma104 = "";
    private String ma105 = "";
    private String ma106 = "";
    private String mauc = "";
    private String mavi = "";
    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String endingdatetime = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String gpslat = "";
    private String gpslng = "";
    private String gpsdate = "";
    private String gpsacc = "";
    private String deviceid = "";
    private String devicetagid = "";


    public Assessment() {
        // Default Constructor
    }

    public static String getTAG() {
        return TAG;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMauc() {
        return mauc;
    }

    public void setMauc(String mauc) {
        this.mauc = mauc;
    }

    public String getMavi() {
        return mavi;
    }

    public void setMavi(String mavi) {
        this.mavi = mavi;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDevicetagid() {
        return devicetagid;
    }

    public void setDevicetagid(String devicetagid) {
        this.devicetagid = devicetagid;
    }

    public String get_luid() {
        return _luid;
    }

    public void set_luid(String _luid) {
        this._luid = _luid;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getMasysdate() {
        return masysdate;
    }

    public void setMasysdate(String masysdate) {
        this.masysdate = masysdate;
    }

    public String getMa101() {
        return ma101;
    }

    public void setMa101(String ma101) {
        this.ma101 = ma101;
    }

    public String getMa102() {
        return ma102;
    }

    public void setMa102(String ma102) {
        this.ma102 = ma102;
    }

    public String getMa103() {
        return ma103;
    }

    public void setMa103(String ma103) {
        this.ma103 = ma103;
    }

    public String getMa104() {
        return ma104;
    }

    public void setMa104(String ma104) {
        this.ma104 = ma104;
    }

    public String getMa105() {
        return ma105;
    }

    public void setMa105(String ma105) {
        this.ma105 = ma105;
    }

    public String getMa106() {
        return ma106;
    }

    public void setMa106(String ma106) {
        this.ma106 = ma106;
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

    public String getDeviceTagId() {
        return devicetagid;
    }

    public void setDeviceTagId(String devicetagid) {
        this.devicetagid = devicetagid;
    }

    public String getSeem_vid() {
        return seem_vid;
    }

    public void setSeem_vid(String seem_vid) {
        this.seem_vid = seem_vid;
    }

    public Assessment Sync(JSONObject jsonObject) throws JSONException {

        /*this._luid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN__LUID);
        this.masysdate = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MASYSDATE);
        this.pid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_PID);
        this.seem_vid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_SEEM_VID);*/

        this._luid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN__LUID);
        this.uid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_UID);
        this.mauc = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MAUC);
        this.mavi = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MAVI);
        this.pid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_PID);
        this.seem_vid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_SEEM_VID);
        this.formtype = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_FORMTYPE);
        this.username = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_USERNAME);
        this.sysdate = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_SYSDATE);
        this.ma101 = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MA101);
        this.ma102 = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MA102);
        this.ma103 = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MA103);
        this.ma104 = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MA104);
        this.ma105 = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MA105);
        this.ma106 = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_MA106);
        this.istatus = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_ENDINGDATETIME);
        this.synced = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_APPVERSION);
        this.gpslat = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_GPSLAT);
        this.gpslng = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_GPSLNG);
        this.gpsdate = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_GPSDATE);
        this.gpsacc = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_GPSACC);
        this.deviceid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_DEVICEID);
        this.devicetagid = jsonObject.getString(AssessmentContract.TableAssessment.COLUMN_DEVICETAGID);

        return this;
    }

    public Assessment Hydrate(Cursor cursor) {

        this._luid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN__LUID));
        this.uid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_UID));
        this.mauc = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MAUC));
        this.mavi = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MAVI));
        this.pid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_PID));
        this.seem_vid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_SEEM_VID));
        this.formtype = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_FORMTYPE));
        this.username = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_USERNAME));
        this.sysdate = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_SYSDATE));
        this.ma101 = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MA101));
        this.ma102 = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MA102));
        this.ma103 = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MA103));
        this.ma104 = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MA104));
        this.ma105 = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MA105));
        this.ma106 = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MA106));
        this.istatus = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_ENDINGDATETIME));
        this.appversion = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_APPVERSION));
        this.gpslat = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_GPSLAT));
        this.gpslng = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_GPSLNG));
        this.gpsdate = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_GPSDATE));
        this.gpsacc = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_GPSACC));
        this.deviceid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_DEVICEID));
        this.devicetagid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_DEVICETAGID));

        return this;
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {

            json.put(AssessmentContract.TableAssessment._ID, this._ID == null ? JSONObject.NULL : this._ID);
            json.put(AssessmentContract.TableAssessment.COLUMN_UID, this.uid == null ? JSONObject.NULL : this.uid);
            json.put(AssessmentContract.TableAssessment.COLUMN_MAUC, this.mauc == null ? JSONObject.NULL : this.mauc);
            json.put(AssessmentContract.TableAssessment.COLUMN_MAVI, this.mavi == null ? JSONObject.NULL : this.mavi);
            json.put(AssessmentContract.TableAssessment.COLUMN_PID, this.pid == null ? JSONObject.NULL : this.pid);
            json.put(AssessmentContract.TableAssessment.COLUMN__LUID, this._luid == null ? JSONObject.NULL : this._luid);
            json.put(AssessmentContract.TableAssessment.COLUMN_SEEM_VID, this.seem_vid == null ? JSONObject.NULL : this.seem_vid);
            //json.put(AssessmentContract.TableAssessment.COLUMN_MASYSDATE, this.masysdate == null ? JSONObject.NULL : this.masysdate);
            json.put(AssessmentContract.TableAssessment.COLUMN_FORMTYPE, this.formtype == null ? JSONObject.NULL : this.formtype);
            json.put(AssessmentContract.TableAssessment.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);
            json.put(AssessmentContract.TableAssessment.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            //json.put(AssessmentContract.TableAssessment.COLUMN_PID, this.pid == null ? JSONObject.NULL : this.pid);
            json.put(AssessmentContract.TableAssessment.COLUMN_MA101, this.ma101 == null ? JSONObject.NULL : this.ma101);
            json.put(AssessmentContract.TableAssessment.COLUMN_MA102, this.ma102 == null ? JSONObject.NULL : this.ma102);
            json.put(AssessmentContract.TableAssessment.COLUMN_MA103, this.ma103 == null ? JSONObject.NULL : this.ma103);
            json.put(AssessmentContract.TableAssessment.COLUMN_MA104, this.ma104 == null ? JSONObject.NULL : this.ma104);
            json.put(AssessmentContract.TableAssessment.COLUMN_MA105, this.ma105 == null ? JSONObject.NULL : this.ma105);
            json.put(AssessmentContract.TableAssessment.COLUMN_MA106, this.ma106 == null ? JSONObject.NULL : this.ma106);
            json.put(AssessmentContract.TableAssessment.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
            json.put(AssessmentContract.TableAssessment.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
            json.put(AssessmentContract.TableAssessment.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
            json.put(AssessmentContract.TableAssessment.COLUMN_GPSLAT, this.gpslat == null ? JSONObject.NULL : this.gpslat);
            json.put(AssessmentContract.TableAssessment.COLUMN_GPSLNG, this.gpslng == null ? JSONObject.NULL : this.gpslng);
            json.put(AssessmentContract.TableAssessment.COLUMN_GPSDATE, this.gpsdate == null ? JSONObject.NULL : this.gpsdate);
            json.put(AssessmentContract.TableAssessment.COLUMN_GPSACC, this.gpsacc == null ? JSONObject.NULL : this.gpsacc);
            json.put(AssessmentContract.TableAssessment.COLUMN_DEVICEID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
            json.put(AssessmentContract.TableAssessment.COLUMN_DEVICETAGID, this.devicetagid == null ? JSONObject.NULL : this.devicetagid);
            json.put(AssessmentContract.TableAssessment.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Assessment HydrateA(Cursor cursor) {
        this._luid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN__LUID));
        //this.masysdate = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_MASYSDATE));
        //this.pid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_PID));
        this.seem_vid = cursor.getString(cursor.getColumnIndex(AssessmentContract.TableAssessment.COLUMN_SEEM_VID));

        return this;
    }
}