package edu.aku.hassannaqvi.ses.ui.sections;


import android.content.Context;
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
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionEBinding;
import edu.aku.hassannaqvi.ses.ui.other.MainActivity;

public class SectionEActivity extends AppCompatActivity {

    ActivitySectionEBinding bi;
    Intent oF = null;
    String SectionEActivity;
    private int PhotoSerial;
    private List<String> somelist;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);

        PhotoSerial = 1;
    }

    private void setupSkip() {

        bi.E1.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE2E33);
                bi.fldGrpCVE2E33.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE2E33.setVisibility(View.VISIBLE);
            }
        });

        bi.E3.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE4);
                Clear.clearAllFields(bi.fldGrpCVE5);
                bi.fldGrpCVE4.setVisibility(View.GONE);
                bi.fldGrpCVE5.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE4.setVisibility(View.VISIBLE);
                bi.fldGrpCVE5.setVisibility(View.VISIBLE);
            }
        });


        bi.E6.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE7);
                Clear.clearAllFields(bi.fldGrpCVE8);
                bi.fldGrpCVE7.setVisibility(View.GONE);
                bi.fldGrpCVE8.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE7.setVisibility(View.VISIBLE);
                bi.fldGrpCVE8.setVisibility(View.VISIBLE);
            }
        });

        bi.E9.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE10);
                bi.fldGrpCVE10.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE10.setVisibility(View.VISIBLE);
            }
        });

        bi.E11.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E1102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE12);
                bi.fldGrpCVE12.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE12.setVisibility(View.VISIBLE);
            }
        });

        bi.E13.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E1302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE14);
                bi.fldGrpCVE14.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE14.setVisibility(View.VISIBLE);
            }
        });

        bi.E15.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E1502.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE16);
                bi.fldGrpCVE16.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE16.setVisibility(View.VISIBLE);
            }
        });

        bi.E19.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E1902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE20);
                bi.fldGrpCVE20.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE20.setVisibility(View.VISIBLE);
            }
        });

        bi.E22.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E2202.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE23);
                bi.fldGrpCVE23.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE23.setVisibility(View.VISIBLE);
            }
        });

        bi.E24.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E2402.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE25);
                bi.fldGrpCVE25.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE25.setVisibility(View.VISIBLE);
            }
        });

        bi.E26.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E2602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE27);
                bi.fldGrpCVE27.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE27.setVisibility(View.VISIBLE);
            }
        });

        bi.E28.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E2802.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE29);
                bi.fldGrpCVE29.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE29.setVisibility(View.VISIBLE);
            }
        });

        bi.E30.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.E3002.getId()) {
                Clear.clearAllFields(bi.fldGrpCVE31);
                bi.fldGrpCVE31.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVE31.setVisibility(View.VISIBLE);
            }
        });

    }

    private void populateSpinner(final Context context) {

        db = MainApp.appInfo.getDbHelper();

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
            startActivity(new Intent(this, SectionFActivity.class));
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
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SECTION_E, MainApp.form.getSection_E());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("E1", bi.E101.isChecked() ? "1"
                : bi.E102.isChecked() ? "2"
                : bi.E198.isChecked() ? "98"
                : "-1");

        json.put("E2", bi.E201.isChecked() ? "1"
                : bi.E202.isChecked() ? "2"
                : bi.E298.isChecked() ? "98"
                : "-1");

        json.put("E3", bi.E301.isChecked() ? "1"
                : bi.E302.isChecked() ? "2"
                : bi.E398.isChecked() ? "98"
                : "-1");

        json.put("E4", bi.E401.isChecked() ? "1"
                : bi.E402.isChecked() ? "2"
                : bi.E403.isChecked() ? "3"
                : bi.E404.isChecked() ? "4"
                : bi.E405.isChecked() ? "5"
                : bi.E406.isChecked() ? "6"
                : "-1");

        json.put("E501", bi.E501.isChecked() ? "1" : "-1");
        json.put("E502", bi.E502.isChecked() ? "2" : "-1");
        json.put("E503", bi.E503.isChecked() ? "3" : "-1");
        json.put("E504", bi.E504.isChecked() ? "4" : "-1");
        json.put("E505", bi.E505.isChecked() ? "5" : "-1");

        json.put("E6", bi.E601.isChecked() ? "1"
                : bi.E602.isChecked() ? "2"
                : bi.E698.isChecked() ? "98"
                : "-1");

        json.put("E7", bi.E701.isChecked() ? "1"
                : bi.E702.isChecked() ? "2"
                : bi.E703.isChecked() ? "3"
                : bi.E704.isChecked() ? "4"
                : bi.E705.isChecked() ? "5"
                : bi.E706.isChecked() ? "6"
                : "-1");

        json.put("E801", bi.E801.isChecked() ? "1" : "-1");
        json.put("E802", bi.E802.isChecked() ? "2" : "-1");
        json.put("E803", bi.E803.isChecked() ? "96" : "-1");
        json.put("E803x", bi.E803x.getText().toString());

        json.put("E9", bi.E901.isChecked() ? "1"
                : bi.E902.isChecked() ? "2"
                : bi.E998.isChecked() ? "98"
                : "-1");

        json.put("E1001", bi.E1001.isChecked() ? "1" : "-1");
        json.put("E1002", bi.E1002.isChecked() ? "2" : "-1");
        json.put("E1096", bi.E1096.isChecked() ? "96" : "-1");
        json.put("E1096x", bi.E1096x.getText().toString());


        json.put("E11", bi.E1101.isChecked() ? "1"
                : bi.E1102.isChecked() ? "2"
                : bi.E1198.isChecked() ? "98"
                : "-1");

        json.put("E1201", bi.E1201.isChecked() ? "1" : "-1");
        json.put("E1202", bi.E1202.isChecked() ? "2" : "-1");
        json.put("E1203", bi.E1203.isChecked() ? "3" : "-1");
        json.put("E1204", bi.E1204.isChecked() ? "4" : "-1");
        json.put("E1205", bi.E1205.isChecked() ? "5" : "-1");
        json.put("E1206", bi.E1206.isChecked() ? "6" : "-1");
        json.put("E1207", bi.E1207.isChecked() ? "7" : "-1");
        json.put("E1208", bi.E1208.isChecked() ? "8" : "-1");
        json.put("E1296", bi.E1296.isChecked() ? "96" : "-1");
        json.put("E1296x", bi.E1296x.getText().toString().trim().isEmpty() ? "-1" : bi.E1296x.getText().toString().trim());


        json.put("E13", bi.E1301.isChecked() ? "1"
                : bi.E1302.isChecked() ? "2"
                : bi.E1398.isChecked() ? "98"
                : "-1");

        json.put("E1401", bi.E1401.isChecked() ? "1" : "-1");
        json.put("E1402", bi.E1402.isChecked() ? "2" : "-1");
        json.put("E1496", bi.E1496.isChecked() ? "96" : "-1");
        json.put("E1496x", bi.E1496x.getText().toString().trim().isEmpty() ? "-1" : bi.E1496x.getText().toString().trim());

        json.put("E15", bi.E1501.isChecked() ? "1"
                : bi.E1502.isChecked() ? "2"
                : bi.E1598.isChecked() ? "98"
                : "-1");

        json.put("E1601", bi.E1601.isChecked() ? "1" : "-1");
        json.put("E1602", bi.E1602.isChecked() ? "2" : "-1");
        json.put("E1603", bi.E1603.isChecked() ? "3" : "-1");
        json.put("E1604", bi.E1604.isChecked() ? "4" : "-1");
        json.put("E1696", bi.E1696.isChecked() ? "96" : "-1");
        json.put("E1696x", bi.E1696x.getText().toString().trim().isEmpty() ? "-1" : bi.E1696x.getText().toString().trim());


        json.put("E17", bi.E1701.isChecked() ? "1"
                : bi.E1702.isChecked() ? "2"
                : bi.E1798.isChecked() ? "98"
                : "-1");

        json.put("E18", bi.E1801.isChecked() ? "1"
                : bi.E1802.isChecked() ? "2"
                : bi.E1898.isChecked() ? "98"
                : "-1");

        json.put("E19", bi.E1901.isChecked() ? "1"
                : bi.E1902.isChecked() ? "2"
                : bi.E1998.isChecked() ? "98"
                : "-1");

        json.put("E2001", bi.E2001.isChecked() ? "1" : "-1");
        json.put("E2002", bi.E2002.isChecked() ? "2" : "-1");
        json.put("E2003", bi.E2003.isChecked() ? "3" : "-1");
        json.put("E2004", bi.E2004.isChecked() ? "4" : "-1");

        json.put("E21", bi.E2101.isChecked() ? "1"
                : bi.E2102.isChecked() ? "2"
                : bi.E2198.isChecked() ? "98"
                : "-1");

        json.put("E22", bi.E2201.isChecked() ? "1"
                : bi.E2202.isChecked() ? "2"
                : bi.E2298.isChecked() ? "98"
                : "-1");

        json.put("E2301", bi.E2301.isChecked() ? "1" : "-1");
        json.put("E2302", bi.E2302.isChecked() ? "2" : "-1");
        json.put("E2396", bi.E2396.isChecked() ? "96" : "-1");
        json.put("E2396x", bi.E2396x.getText().toString().trim().isEmpty() ? "-1" : bi.E2396x.getText().toString().trim());


        json.put("E24", bi.E2401.isChecked() ? "1"
                : bi.E2402.isChecked() ? "2"
                : bi.E2498.isChecked() ? "98"
                : "-1");

        json.put("E2501", bi.E2501.isChecked() ? "1" : "-1");
        json.put("E2502", bi.E2502.isChecked() ? "2" : "-1");
        json.put("E2503", bi.E2503.isChecked() ? "3" : "-1");
        json.put("E2504", bi.E2504.isChecked() ? "4" : "-1");
        json.put("E2505", bi.E2505.isChecked() ? "5" : "-1");
        json.put("E2506", bi.E2506.isChecked() ? "6" : "-1");
        json.put("E2507", bi.E2507.isChecked() ? "7" : "-1");
        json.put("E2508", bi.E2508.isChecked() ? "8" : "-1");
        json.put("E2509", bi.E2509.isChecked() ? "9" : "-1");
        json.put("E2510", bi.E2510.isChecked() ? "10" : "-1");
        json.put("E2511", bi.E2511.isChecked() ? "11" : "-1");
        json.put("E2512", bi.E2512.isChecked() ? "12" : "-1");
        json.put("E2513", bi.E2513.isChecked() ? "13" : "-1");
        json.put("E2514", bi.E2514.isChecked() ? "14" : "-1");
        json.put("E2515", bi.E2515.isChecked() ? "15" : "-1");
        json.put("E2516", bi.E2516.isChecked() ? "16" : "-1");
        json.put("E2517", bi.E2517.isChecked() ? "17" : "-1");
        json.put("E2518", bi.E2518.isChecked() ? "18" : "-1");
        json.put("E2596", bi.E2596.isChecked() ? "96" : "-1");
        json.put("E2596x", bi.E2596x.getText().toString().trim().isEmpty() ? "-1" : bi.E2596x.getText().toString().trim());


        json.put("E26", bi.E2601.isChecked() ? "1"
                : bi.E2602.isChecked() ? "2"
                : bi.E2698.isChecked() ? "98"
                : "-1");

        json.put("E2701", bi.E2701.isChecked() ? "1" : "-1");
        json.put("E2702", bi.E2702.isChecked() ? "2" : "-1");
        json.put("E2703", bi.E2703.isChecked() ? "3" : "-1");
        json.put("E2796", bi.E2796.isChecked() ? "96" : "-1");
        json.put("E2796x", bi.E2796x.getText().toString().trim().isEmpty() ? "-1" : bi.E2796x.getText().toString().trim());

        json.put("E28", bi.E2801.isChecked() ? "1"
                : bi.E2802.isChecked() ? "2"
                : bi.E2898.isChecked() ? "98"
                : "-1");

        json.put("E2901", bi.E2901.isChecked() ? "1" : "-1");
        json.put("E2902", bi.E2902.isChecked() ? "2" : "-1");
        json.put("E2903", bi.E2903.isChecked() ? "3" : "-1");
        json.put("E2904", bi.E2904.isChecked() ? "4" : "-1");
        json.put("E2905", bi.E2905.isChecked() ? "5" : "-1");
        json.put("E2906", bi.E2906.isChecked() ? "6" : "-1");
        json.put("E2907", bi.E2907.isChecked() ? "7" : "-1");
        json.put("E2908", bi.E2908.isChecked() ? "8" : "-1");
        json.put("E2909", bi.E2909.isChecked() ? "9" : "-1");
        json.put("E2996", bi.E2996.isChecked() ? "96" : "-1");
        json.put("E2996x", bi.E2996x.getText().toString().trim().isEmpty() ? "-1" : bi.E2996x.getText().toString().trim());


        json.put("E30", bi.E3001.isChecked() ? "1"
                : bi.E3002.isChecked() ? "2"
                : bi.E3098.isChecked() ? "98"
                : "-1");

        json.put("E3101", bi.E3101.isChecked() ? "1" : "-1");
        json.put("E3102", bi.E3102.isChecked() ? "2" : "-1");
        json.put("E3103", bi.E3103.isChecked() ? "3" : "-1");
        json.put("E3104", bi.E3104.isChecked() ? "4" : "-1");
        json.put("E3105", bi.E3105.isChecked() ? "5" : "-1");
        json.put("E3106", bi.E3106.isChecked() ? "6" : "-1");
        json.put("E3107", bi.E3107.isChecked() ? "7" : "-1");
        json.put("E3108", bi.E3108.isChecked() ? "8" : "-1");
        json.put("E3109", bi.E3109.isChecked() ? "9" : "-1");
        json.put("E3110", bi.E3110.isChecked() ? "10" : "-1");
        json.put("E3111", bi.E3111.isChecked() ? "11" : "-1");
        json.put("E3196", bi.E3196.isChecked() ? "96" : "-1");
        json.put("E3196x", bi.E3196x.getText().toString().trim().isEmpty() ? "-1" : bi.E3196x.getText().toString().trim());


        json.put("E32", bi.E3201.isChecked() ? "1"
                : bi.E3202.isChecked() ? "2"
                : bi.E3298.isChecked() ? "98"
                : "-1");

        json.put("E33", bi.E3301.isChecked() ? "1"
                : bi.E3302.isChecked() ? "2"
                : bi.E3398.isChecked() ? "98"
                : "-1");

        MainApp.setGPS(this, SectionEActivity);

        MainApp.form.setSection_E(String.valueOf(json));
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