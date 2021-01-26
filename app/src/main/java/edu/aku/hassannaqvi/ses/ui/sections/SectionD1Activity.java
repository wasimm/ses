package edu.aku.hassannaqvi.ses.ui.sections;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.aku.hassannaqvi.ses.R;
import edu.aku.hassannaqvi.ses.contracts.FormsContract;
import edu.aku.hassannaqvi.ses.core.DatabaseHelper;
import edu.aku.hassannaqvi.ses.core.MainApp;
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionD1Binding;
import edu.aku.hassannaqvi.ses.ui.other.MainActivity;


public class SectionD1Activity extends AppCompatActivity {

    ActivitySectionD1Binding bi;
    Intent oF = null;
    String SectionD1Activity;
    private int PhotoSerial;
    private List<String> somelist;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d1);
        bi.setCallback(this);
        setupSkip();
        PhotoSerial = 1;
    }

    private void setupSkip() {

        bi.D1.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D102.getId() && bi.D202.isChecked()) {
                Clear.clearAllFields(bi.fldGrpCVD2D21);
                bi.fldGrpCVD2D21.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD2D21.setVisibility(View.VISIBLE);
            }
        });

        bi.D2.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D202.getId() && bi.D102.isChecked()) {
                Clear.clearAllFields(bi.fldGrpCVD2D21);
                bi.fldGrpCVD2D21.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD2D21.setVisibility(View.VISIBLE);
            }
        });

        bi.D3.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD4);
                Clear.clearAllFields(bi.fldGrpCVD5);
                bi.fldGrpCVD4.setVisibility(View.GONE);
                bi.fldGrpCVD5.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD4.setVisibility(View.VISIBLE);
                bi.fldGrpCVD5.setVisibility(View.VISIBLE);
            }
        });

        bi.D6.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD7);
                Clear.clearAllFields(bi.fldGrpCVD8);
                bi.fldGrpCVD7.setVisibility(View.GONE);
                bi.fldGrpCVD8.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD7.setVisibility(View.VISIBLE);
                bi.fldGrpCVD8.setVisibility(View.VISIBLE);
            }
        });

        bi.D9.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD10);
                bi.fldGrpCVD10.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD10.setVisibility(View.VISIBLE);
            }
        });

        bi.D11.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D1102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD12);
                bi.fldGrpCVD12.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD12.setVisibility(View.VISIBLE);
            }
        });

        bi.D13.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D1302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD14);
                bi.fldGrpCVD14.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD14.setVisibility(View.VISIBLE);
            }
        });

        bi.D15.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D1502.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD16);
                bi.fldGrpCVD16.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD16.setVisibility(View.VISIBLE);
            }
        });

        bi.D19.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D1902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD20);
                bi.fldGrpCVD20.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD20.setVisibility(View.VISIBLE);
            }
        });

    }

    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            if (bi.D102.isChecked() && bi.D202.isChecked()) {
                startActivity(new Intent(this, SectionD2Activity.class).putExtra("skip", true));
            } else {
                startActivity(new Intent(this, SectionD2Activity.class));
            }
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SECTION_D, MainApp.form.getSection_D());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        /*assessment = new Assessment();
        assessment.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime()));
        assessment.setFormtype(CONSTANTS.FORM_MA);
        assessment.setUsername(MainApp.userName);
        assessment.setDeviceid(MainApp.appInfo.getDeviceID());
        assessment.setMa104(bi.ma10401.isChecked() ? "1"
                : bi.ma10402.isChecked() ? "2"
                : "-1");
        assessment.setMa105(bi.ma105.getText().toString().trim().isEmpty() ? "-1" : bi.ma105.getText().toString());*/

        JSONObject json = new JSONObject();

        json.put("D1", bi.D101.isChecked() ? "1"
                : bi.D102.isChecked() ? "2"
                : bi.D198.isChecked() ? "98"
                : "-1");

        json.put("D2", bi.D201.isChecked() ? "1"
                : bi.D202.isChecked() ? "2"
                : bi.D298.isChecked() ? "98"
                : "-1");

        json.put("D3", bi.D301.isChecked() ? "1"
                : bi.D302.isChecked() ? "2"
                : bi.D398.isChecked() ? "98"
                : "-1");

        json.put("D4", bi.D401.isChecked() ? "1"
                : bi.D402.isChecked() ? "2"
                : bi.D403.isChecked() ? "3"
                : bi.D404.isChecked() ? "4"
                : bi.D405.isChecked() ? "5"
                : bi.D406.isChecked() ? "6"
                : "-1");

        json.put("D501", bi.D501.isChecked() ? "1" : "-1");
        json.put("D502", bi.D502.isChecked() ? "2" : "-1");
        json.put("D503", bi.D503.isChecked() ? "3" : "-1");
        json.put("D504", bi.D504.isChecked() ? "4" : "-1");
        json.put("D505", bi.D505.isChecked() ? "5" : "-1");
        json.put("D505x", bi.D505x.getText().toString().trim().isEmpty() ? "-1" : bi.D505x.getText().toString().trim());

        json.put("D6", bi.D601.isChecked() ? "1"
                : bi.D602.isChecked() ? "2"
                : bi.D698.isChecked() ? "98"
                : "-1");

        json.put("D7", bi.D701.isChecked() ? "1"
                : bi.D702.isChecked() ? "2"
                : bi.D703.isChecked() ? "3"
                : bi.D704.isChecked() ? "4"
                : bi.D705.isChecked() ? "5"
                : bi.D706.isChecked() ? "6"
                : "-1");

        json.put("D801", bi.D801.isChecked() ? "1" : "-1");
        json.put("D802", bi.D802.isChecked() ? "2" : "-1");
        json.put("D896", bi.D896.isChecked() ? "96" : "-1");
        json.put("D896x", bi.D896x.getText().toString().trim().isEmpty() ? "-1" : bi.D896x.getText().toString().trim());

        json.put("D9", bi.D901.isChecked() ? "1"
                : bi.D902.isChecked() ? "2"
                : bi.D998.isChecked() ? "98"
                : "-1");

        json.put("D1001", bi.D1001.isChecked() ? "1" : "-1");
        json.put("D1002", bi.D1002.isChecked() ? "2" : "-1");
        json.put("D1096", bi.D1096.isChecked() ? "96" : "-1");
        json.put("D1096x", bi.D1096x.getText().toString().trim().isEmpty() ? "-1" : bi.D1096x.getText().toString().trim());


        json.put("D11", bi.D1101.isChecked() ? "1"
                : bi.D1102.isChecked() ? "2"
                : bi.D1198.isChecked() ? "98"
                : "-1");

        json.put("D1201", bi.D1201.isChecked() ? "1" : "-1");
        json.put("D1202", bi.D1202.isChecked() ? "2" : "-1");
        json.put("D1203", bi.D1203.isChecked() ? "3" : "-1");
        json.put("D1204", bi.D1204.isChecked() ? "4" : "-1");
        json.put("D1205", bi.D1205.isChecked() ? "5" : "-1");
        json.put("D1206", bi.D1206.isChecked() ? "6" : "-1");
        json.put("D1207", bi.D1207.isChecked() ? "7" : "-1");
        json.put("D1208", bi.D1208.isChecked() ? "8" : "-1");
        json.put("D1296", bi.D1296.isChecked() ? "96" : "-1");
        json.put("D1296x", bi.D1296x.getText().toString().trim().isEmpty() ? "-1" : bi.D1296x.getText().toString().trim());


        json.put("D13", bi.D1301.isChecked() ? "1"
                : bi.D1302.isChecked() ? "2"
                : bi.D1398.isChecked() ? "98"
                : "-1");

        json.put("D1401", bi.D1401.isChecked() ? "1" : "-1");
        json.put("D1402", bi.D1402.isChecked() ? "2" : "-1");
        json.put("D1496", bi.D1496.isChecked() ? "96" : "-1");
        json.put("D1496x", bi.D1496x.getText().toString().trim().isEmpty() ? "-1" : bi.D1496x.getText().toString().trim());


        json.put("D15", bi.D1501.isChecked() ? "1"
                : bi.D1502.isChecked() ? "2"
                : bi.D1598.isChecked() ? "98"
                : "-1");

        json.put("D1601", bi.D1601.isChecked() ? "1" : "-1");
        json.put("D1602", bi.D1602.isChecked() ? "2" : "-1");
        json.put("D1603", bi.D1603.isChecked() ? "3" : "-1");
        json.put("D1604", bi.D1604.isChecked() ? "4" : "-1");
        json.put("D1696", bi.D1696.isChecked() ? "96" : "-1");
        json.put("D1696x", bi.D1696x.getText().toString().trim().isEmpty() ? "-1" : bi.D1696x.getText().toString().trim());


        json.put("D17", bi.D1701.isChecked() ? "1"
                : bi.D1702.isChecked() ? "2"
                : bi.D1798.isChecked() ? "98"
                : "-1");

        json.put("D18", bi.D1801.isChecked() ? "1"
                : bi.D1802.isChecked() ? "2"
                : bi.D1898.isChecked() ? "98"
                : "-1");

        json.put("D19", bi.D1901.isChecked() ? "1"
                : bi.D1902.isChecked() ? "2"
                : bi.D1998.isChecked() ? "98"
                : "-1");

        json.put("D2001", bi.D2001.isChecked() ? "1" : "-1");
        json.put("D2002", bi.D2002.isChecked() ? "2" : "-1");
        json.put("D2003", bi.D2003.isChecked() ? "3" : "-1");
        json.put("D2004", bi.D2004.isChecked() ? "4" : "-1");

        json.put("D21", bi.D2101.isChecked() ? "1"
                : bi.D2102.isChecked() ? "2"
                : bi.D2198.isChecked() ? "98"
                : "-1");

        MainApp.setGPS(this, SectionD1Activity);

        MainApp.form.setSection_D(String.valueOf(json));
    }

    private boolean formValidation() {

        /*if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }*/

        /*if (PhotoSerial <= 1) {
            Toast.makeText(this, "Minimum 1 and maximum 4 picture(s) must be taken", Toast.LENGTH_LONG).show();
            return false;
        }*/

        return true;
    }

}