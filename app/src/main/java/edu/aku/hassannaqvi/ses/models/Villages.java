package edu.aku.hassannaqvi.ses.models;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ses.contracts.VillagesContract;


public class Villages {

    private static final String TAG = "Villages_CONTRACT";

    Long id;
    String ucname;
    String villagename;
    String seem_vid;
    String ucid;

    public Villages() {
        // Default Constructor
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUcname() {
        return ucname;
    }

    public void setUcname(String ucname) {
        this.ucname = ucname;
    }


    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }


    public String getSeem_vid() {
        return seem_vid;
    }

    public void setSeem_vid(String seem_vid) {
        this.seem_vid = seem_vid;
    }


    public String getUcid() {
        return ucid;
    }

    public void setUcid(String ucid) {
        this.ucid = ucid;
    }


    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {
            json.put(VillagesContract.TableVillage._ID, this.id == null ? JSONObject.NULL : this.id);
            json.put(VillagesContract.TableVillage.COLUMN_UCNAME, this.ucname == null ? JSONObject.NULL : this.ucname);
            json.put(VillagesContract.TableVillage.COLUMN_VILLAGE_NAME, this.villagename == null ? JSONObject.NULL : this.villagename);
            json.put(VillagesContract.TableVillage.COLUMN_SEEM_VID, this.seem_vid == null ? JSONObject.NULL : this.seem_vid);
            json.put(VillagesContract.TableVillage.COLUMN_UCID, this.seem_vid == null ? JSONObject.NULL : this.seem_vid);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Villages Sync(JSONObject jsonObject) throws JSONException {
        this.ucname = jsonObject.getString(VillagesContract.TableVillage.COLUMN_UCNAME);
        this.villagename = jsonObject.getString(VillagesContract.TableVillage.COLUMN_VILLAGE_NAME);
        String seemVID = jsonObject.getString(VillagesContract.TableVillage.COLUMN_SEEM_VID);
        this.seem_vid = seemVID.substring(seemVID.length() - 3);
        this.ucid = "0" + seemVID.substring(0, 1);
        return this;
    }

    public Villages HydrateUc(Cursor cursor) {
        this.ucname = cursor.getString(cursor.getColumnIndex(VillagesContract.TableVillage.COLUMN_UCNAME));
        this.seem_vid = cursor.getString(cursor.getColumnIndex(VillagesContract.TableVillage.COLUMN_SEEM_VID));
        this.ucid = cursor.getString(cursor.getColumnIndex(VillagesContract.TableVillage.COLUMN_UCID));
        return this;
    }

    public Villages HydrateVil(Cursor cursor) {
        this.villagename = cursor.getString(cursor.getColumnIndex(VillagesContract.TableVillage.COLUMN_VILLAGE_NAME));
        this.seem_vid = cursor.getString(cursor.getColumnIndex(VillagesContract.TableVillage.COLUMN_SEEM_VID));
        return this;
    }
}