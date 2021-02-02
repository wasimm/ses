package edu.aku.hassannaqvi.ses.ui.sections;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.aku.hassannaqvi.ses.R;
import edu.aku.hassannaqvi.ses.contracts.FormsContract;
import edu.aku.hassannaqvi.ses.core.DatabaseHelper;
import edu.aku.hassannaqvi.ses.core.MainApp;
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionD2Binding;
import edu.aku.hassannaqvi.ses.ui.other.MainActivity;
import edu.aku.hassannaqvi.ses.utils.JSONUtils;


public class SectionD2Activity extends AppCompatActivity {

    ActivitySectionD2Binding bi;
    Intent oF = null;
    Boolean skip;
    String SectionD2Activity;
    private int PhotoSerial;
    private List<String> somelist;
    private DatabaseHelper db;
    private String semisCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d2);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);

        Intent intent = getIntent();
        skip = intent.getBooleanExtra("skip", false);
        semisCode = intent.getStringExtra("semisCode");
        if (skip) {
            BtnContinue();
            finish();
            startActivity(new Intent(this, SectionEActivity.class));
        }
    }

    private void setupSkip() {

        bi.D22.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D2202.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD23);
                bi.fldGrpCVD23.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD23.setVisibility(View.VISIBLE);
            }
        });

        bi.D24.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D2402.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD25);
                bi.fldGrpCVD25.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD25.setVisibility(View.VISIBLE);
            }
        });

        bi.D26.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D2602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD27);
                bi.fldGrpCVD27.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD27.setVisibility(View.VISIBLE);
            }
        });

        bi.D28.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D2802.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD29);
                bi.fldGrpCVD29.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD29.setVisibility(View.VISIBLE);
            }
        });

        bi.D30.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.D3002.getId()) {
                Clear.clearAllFields(bi.fldGrpCVD31);
                bi.fldGrpCVD31.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVD31.setVisibility(View.VISIBLE);
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
            startActivity(new Intent(this, SectionEActivity.class).putExtra("semisCode", semisCode));
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

        json.put("D22", bi.D2201.isChecked() ? "1"
                : bi.D2202.isChecked() ? "2"
                : bi.D2298.isChecked() ? "98"
                : "-1");

        json.put("D2301", bi.D2301.isChecked() ? "1" : "-1");
        json.put("D2302", bi.D2302.isChecked() ? "2" : "-1");
        json.put("D2396", bi.D2396.isChecked() ? "96" : "-1");
        json.put("D2396x", bi.D2396x.getText().toString().trim().isEmpty() ? "-1" : bi.D2396x.getText().toString().trim());


        json.put("D24", bi.D2401.isChecked() ? "1"
                : bi.D2402.isChecked() ? "2"
                : bi.D2498.isChecked() ? "98"
                : "-1");

        json.put("D2501", bi.D2501.isChecked() ? "1" : "-1");
        json.put("D2502", bi.D2502.isChecked() ? "2" : "-1");
        json.put("D2503", bi.D2503.isChecked() ? "3" : "-1");
        json.put("D2504", bi.D2504.isChecked() ? "4" : "-1");
        json.put("D2505", bi.D2505.isChecked() ? "5" : "-1");
        json.put("D2506", bi.D2506.isChecked() ? "6" : "-1");
        json.put("D2507", bi.D2507.isChecked() ? "7" : "-1");
        json.put("D2508", bi.D2508.isChecked() ? "8" : "-1");
        json.put("D2509", bi.D2509.isChecked() ? "9" : "-1");
        json.put("D2510", bi.D2510.isChecked() ? "10" : "-1");
        json.put("D2511", bi.D2511.isChecked() ? "11" : "-1");
        json.put("D2512", bi.D2512.isChecked() ? "12" : "-1");
        json.put("D2513", bi.D2513.isChecked() ? "13" : "-1");
        json.put("D2514", bi.D2514.isChecked() ? "14" : "-1");
        json.put("D2515", bi.D2515.isChecked() ? "15" : "-1");
        json.put("D2516", bi.D2516.isChecked() ? "16" : "-1");
        json.put("D2517", bi.D2517.isChecked() ? "17" : "-1");
        json.put("D2518", bi.D2518.isChecked() ? "18" : "-1");
        json.put("D2519", bi.D2519.isChecked() ? "19" : "-1");
        json.put("D2596", bi.D2596.isChecked() ? "96" : "-1");
        json.put("D2596x", bi.D2596x.getText().toString().trim().isEmpty() ? "-1" : bi.D2596x.getText().toString().trim());

        json.put("D26", bi.D2601.isChecked() ? "1"
                : bi.D2602.isChecked() ? "2"
                : bi.D2698.isChecked() ? "98"
                : "-1");

        json.put("D2701", bi.D2701.isChecked() ? "1" : "-1");
        json.put("D2702", bi.D2702.isChecked() ? "2" : "-1");
        json.put("D2703", bi.D2703.isChecked() ? "3" : "-1");
        json.put("D2704", bi.D2704.isChecked() ? "4" : "-1");
        json.put("D2705", bi.D2705.isChecked() ? "5" : "-1");
        json.put("D2706", bi.D2706.isChecked() ? "6" : "-1");
        json.put("D2707", bi.D2707.isChecked() ? "7" : "-1");
        json.put("D2708", bi.D2708.isChecked() ? "8" : "-1");
        json.put("D2709", bi.D2709.isChecked() ? "9" : "-1");
        json.put("D2796", bi.D2796.isChecked() ? "96" : "-1");
        json.put("D2796x", bi.D2796x.getText().toString().trim().isEmpty() ? "-1" : bi.D2796x.getText().toString().trim());


        json.put("D28", bi.D2801.isChecked() ? "1"
                : bi.D2802.isChecked() ? "2"
                : bi.D2898.isChecked() ? "98"
                : "-1");

        json.put("D2901", bi.D2901.isChecked() ? "1" : "-1");
        json.put("D2902", bi.D2902.isChecked() ? "2" : "-1");
        json.put("D2903", bi.D2903.isChecked() ? "3" : "-1");
        json.put("D2904", bi.D2904.isChecked() ? "4" : "-1");
        json.put("D2905", bi.D2905.isChecked() ? "5" : "-1");
        json.put("D2906", bi.D2906.isChecked() ? "6" : "-1");
        json.put("D2907", bi.D2907.isChecked() ? "7" : "-1");
        json.put("D2908", bi.D2908.isChecked() ? "8" : "-1");
        json.put("D2909", bi.D2909.isChecked() ? "96" : "-1");
        json.put("D2996", bi.D2996.isChecked() ? "96" : "-1");
        json.put("D2996x", bi.D2996x.getText().toString().trim().isEmpty() ? "-1" : bi.D2996x.getText().toString().trim());


        json.put("D30", bi.D3001.isChecked() ? "1"
                : bi.D3002.isChecked() ? "2"
                : bi.D3098.isChecked() ? "98"
                : "-1");

        json.put("D3101", bi.D3101.isChecked() ? "1" : "-1");
        json.put("D3102", bi.D3102.isChecked() ? "2" : "-1");
        json.put("D3103", bi.D3103.isChecked() ? "3" : "-1");
        json.put("D3104", bi.D3104.isChecked() ? "4" : "-1");
        json.put("D3105", bi.D3105.isChecked() ? "5" : "-1");
        json.put("D3106", bi.D3106.isChecked() ? "6" : "-1");
        json.put("D3107", bi.D3107.isChecked() ? "7" : "-1");
        json.put("D3108", bi.D3108.isChecked() ? "8" : "-1");
        json.put("D3109", bi.D3109.isChecked() ? "9" : "-1");
        json.put("D3110", bi.D3110.isChecked() ? "10" : "-1");
        json.put("D3111", bi.D3111.isChecked() ? "11" : "-1");
        json.put("D3196", bi.D3196.isChecked() ? "96" : "-1");
        json.put("D3196x", bi.D3196x.getText().toString().trim().isEmpty() ? "-1" : bi.D3196x.getText().toString().trim());


        json.put("D32", bi.D3201.isChecked() ? "1"
                : bi.D3202.isChecked() ? "2"
                : bi.D3203.isChecked() ? "3"
                : bi.D3204.isChecked() ? "4"
                : bi.D3205.isChecked() ? "5"
                : "-1");

        json.put("D33", bi.D3301.isChecked() ? "1"
                : bi.D3302.isChecked() ? "2"
                : bi.D3398.isChecked() ? "98"
                : "-1");

        json.put("D34", bi.D3401.isChecked() ? "1"
                : bi.D3402.isChecked() ? "2"
                : bi.D3498.isChecked() ? "98"
                : "-1");

        json.put("D35", bi.D3501.isChecked() ? "1"
                : bi.D3502.isChecked() ? "2"
                : bi.D3598.isChecked() ? "98"
                : "-1");

        json.put("D36", bi.D3601.isChecked() ? "1"
                : bi.D3602.isChecked() ? "2"
                : bi.D3698.isChecked() ? "98"
                : "-1");

        json.put("D37", bi.D3701.isChecked() ? "1"
                : bi.D3702.isChecked() ? "2"
                : bi.D3798.isChecked() ? "98"
                : "-1");

        json.put("D38", bi.D3801.isChecked() ? "1"
                : bi.D3802.isChecked() ? "2"
                : bi.D3898.isChecked() ? "98"
                : "-1");

        json.put("D39", bi.D3901.isChecked() ? "1"
                : bi.D3902.isChecked() ? "2"
                : bi.D3998.isChecked() ? "98"
                : "-1");

        MainApp.setGPS(this, SectionD2Activity);

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.form.getSection_D()), json);
            MainApp.form.setSection_D(String.valueOf(json_merge));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean formValidation() {

        if (skip == true) {

            return Validator.emptyCheckingContainer(this, bi.GrpName);
        }

        return true;
    }
}