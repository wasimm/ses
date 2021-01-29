package edu.aku.hassannaqvi.ses.models;


import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ses.contracts.SchoolsContract;


public class Schools {

    private static final String TAG = "Schools_CONTRACT";

    Long id;
    Long _id;
    String semisCode;
    String divisionCode;
    String divisionName;
    String districtCode;
    String districtName;
    String tehsilCode;
    String tehsilName;
    String sName;
    String sHead;
    String sMedium;
    String sLevel;
    String sType;
    String sCurrentStatus;
    String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Schools() {
        // Default Constructor
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getSemisCode() {
        return semisCode;
    }

    public void setSemisCode(String semisCode) {
        this.semisCode = semisCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getTehsilCode() {
        return tehsilCode;
    }

    public void setTehsilCode(String tehsilCode) {
        this.tehsilCode = tehsilCode;
    }

    public String getTehsilName() {
        return tehsilName;
    }

    public void setTehsilName(String tehsilName) {
        this.tehsilName = tehsilName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsHead() {
        return sHead;
    }

    public void setsHead(String sHead) {
        this.sHead = sHead;
    }

    public String getsMedium() {
        return sMedium;
    }

    public void setsMedium(String sMedium) {
        this.sMedium = sMedium;
    }

    public String getsLevel() {
        return sLevel;
    }

    public void setsLevel(String sLevel) {
        this.sLevel = sLevel;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getsCurrentStatus() {
        return sCurrentStatus;
    }

    public void setsCurrentStatus(String sCurrentStatus) {
        this.sCurrentStatus = sCurrentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Schools Sync(JSONObject jsonObject) throws JSONException {

        this._id = Long.valueOf(jsonObject.getString(SchoolsContract.TableSchool.COLUMN_SERVER_ID));
        this.semisCode = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_SEMIS_CODE);
        this.divisionCode = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_DIVISION_CODE);
        this.divisionName = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_DIVISION_NAME);
        this.districtCode = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_DISTRICT_CODE);
        this.districtName = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_DISTRICT_NAME);
        this.tehsilCode = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_TEHSIL_CODE);
        this.tehsilName = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_TEHSIL_NAME);
        this.sName = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_S_NAME);
        this.sHead = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_S_HEAD);
        this.sLevel = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_S_LEVEL);
        this.sType = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_S_TYPE);
        this.sMedium = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_S_MEDIUM);
        this.sCurrentStatus = jsonObject.getString(SchoolsContract.TableSchool.COLUMN_S_CURRENT_STATUS);

        return this;
    }


    /*public Schools Hydrate(Cursor cursor) {

        this._id = Long.valueOf(cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_SERVER_ID)));
        this.semisCode = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_SEMIS_CODE));
        this.divisionCode = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_DIVISION_CODE));
        this.divisionName = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_DIVISION_NAME));
        this.districtCode = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_DISTRICT_CODE));
        this.districtName = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_DISTRICT_NAME));
        this.tehsilCode = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_TEHSIL_CODE));
        this.tehsilName = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_TEHSIL_NAME));
        this.sName = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_S_NAME));
        this.sHead = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_S_HEAD));
        this.sMedium = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_S_MEDIUM));
        this.sLevel = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_S_LEVEL));
        this.sType = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_S_TYPE));
        this.sCurrentStatus = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_S_CURRENT_STATUS));
        this.status = cursor.getString(cursor.getColumnIndex(SchoolsContract.TableSchool.COLUMN_STATUS));

        return this;
    }*/

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {

            json.put(SchoolsContract.TableSchool.COLUMN_ID, this.id == null ? JSONObject.NULL : this.id);
            json.put(SchoolsContract.TableSchool.COLUMN_SERVER_ID, this._id == null ? JSONObject.NULL : this._id);
            json.put(SchoolsContract.TableSchool.COLUMN_SEMIS_CODE, this.semisCode == null ? JSONObject.NULL : this.semisCode);
            json.put(SchoolsContract.TableSchool.COLUMN_DIVISION_CODE, this.divisionCode == null ? JSONObject.NULL : this.divisionCode);
            json.put(SchoolsContract.TableSchool.COLUMN_DIVISION_NAME, this.divisionName == null ? JSONObject.NULL : this.divisionName);
            json.put(SchoolsContract.TableSchool.COLUMN_DISTRICT_CODE, this.districtCode == null ? JSONObject.NULL : this.districtCode);
            json.put(SchoolsContract.TableSchool.COLUMN_DISTRICT_NAME, this.districtName == null ? JSONObject.NULL : this.districtName);
            json.put(SchoolsContract.TableSchool.COLUMN_TEHSIL_CODE, this.tehsilCode == null ? JSONObject.NULL : this.tehsilCode);
            json.put(SchoolsContract.TableSchool.COLUMN_TEHSIL_NAME, this.tehsilName == null ? JSONObject.NULL : this.tehsilName);
            json.put(SchoolsContract.TableSchool.COLUMN_S_NAME, this.sName == null ? JSONObject.NULL : this.sName);
            json.put(SchoolsContract.TableSchool.COLUMN_S_HEAD, this.sHead == null ? JSONObject.NULL : this.sHead);
            json.put(SchoolsContract.TableSchool.COLUMN_S_MEDIUM, this.sMedium == null ? JSONObject.NULL : this.sMedium);
            json.put(SchoolsContract.TableSchool.COLUMN_S_LEVEL, this.sLevel == null ? JSONObject.NULL : this.sLevel);
            json.put(SchoolsContract.TableSchool.COLUMN_S_TYPE, this.sType == null ? JSONObject.NULL : this.sType);
            json.put(SchoolsContract.TableSchool.COLUMN_S_CURRENT_STATUS, this.sCurrentStatus == null ? JSONObject.NULL : this.sCurrentStatus);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}