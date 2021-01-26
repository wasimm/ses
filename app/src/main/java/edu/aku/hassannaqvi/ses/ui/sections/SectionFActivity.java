package edu.aku.hassannaqvi.ses.ui.sections;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.aku.hassannaqvi.ses.R;
import edu.aku.hassannaqvi.ses.contracts.FormsContract;
import edu.aku.hassannaqvi.ses.core.DatabaseHelper;
import edu.aku.hassannaqvi.ses.core.MainApp;
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionFBinding;
import edu.aku.hassannaqvi.ses.ui.other.MainActivity;


public class SectionFActivity extends AppCompatActivity {

    ActivitySectionFBinding bi;
    Intent oF = null;
    String SectionFActivity;
    private int PhotoSerial;
    private List<String> somelist;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);

        PhotoSerial = 1;
    }


    private void setupSkip() {

       /* bi.ma105.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpCVma106);
            Clear.clearAllFields(bi.fldGrpCVma107);
            Clear.clearAllFields(bi.fldGrpCVma108);
            bi.fldGrpCVma106.setVisibility(View.GONE);
            bi.fldGrpCVma107.setVisibility(View.GONE);
            bi.fldGrpCVma108.setVisibility(View.GONE);

            if (i == bi.ma10501.getId()) {
                bi.fldGrpCVma107.setVisibility(View.VISIBLE);
            } else if (i == bi.ma10502.getId()) {
                bi.fldGrpCVma106.setVisibility(View.VISIBLE);
            } else if (i == bi.ma10503.getId()) {
                bi.fldGrpCVma108.setVisibility(View.VISIBLE);
            }
        });*/

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
            startActivity(new Intent(this, CaptureImageActivity.class));
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
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SECTION_F, MainApp.form.getSection_F());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("F1", bi.F101.isChecked() ? "1"
                : bi.F102.isChecked() ? "2"
                : bi.F198.isChecked() ? "98"
                : "-1");

        json.put("F2", bi.F201.isChecked() ? "1"
                : bi.F202.isChecked() ? "2"
                : bi.F203.isChecked() ? "3"
                : bi.F204.isChecked() ? "4"
                : "-1");

        json.put("F301", bi.F301.isChecked() ? "1" : "-1");
        json.put("F302", bi.F302.isChecked() ? "2" : "-1");
        json.put("F303", bi.F303.isChecked() ? "3" : "-1");
        json.put("F304", bi.F304.isChecked() ? "4" : "-1");
        json.put("F305", bi.F305.isChecked() ? "5" : "-1");
        json.put("F306", bi.F306.isChecked() ? "6" : "-1");
        json.put("F307", bi.F307.isChecked() ? "7" : "-1");
        json.put("F308", bi.F308.isChecked() ? "8" : "-1");
        json.put("F309", bi.F309.isChecked() ? "9" : "-1");
        json.put("F310", bi.F310.isChecked() ? "10" : "-1");
        json.put("F311", bi.F311.isChecked() ? "11" : "-1");
        json.put("F312", bi.F312.isChecked() ? "12" : "-1");
        json.put("F313", bi.F313.isChecked() ? "13" : "-1");
        json.put("F314", bi.F314.isChecked() ? "14" : "-1");
        json.put("F315", bi.F315.isChecked() ? "15" : "-1");
        json.put("F316", bi.F316.isChecked() ? "16" : "-1");
        json.put("F396", bi.F396.isChecked() ? "96" : "-1");
        json.put("F396x", bi.F396x.getText().toString().trim().isEmpty() ? "-1" : bi.F396x.getText().toString().trim());


        json.put("F401", bi.F401.isChecked() ? "1" : "-1");
        json.put("F402", bi.F402.isChecked() ? "2" : "-1");
        json.put("F403", bi.F403.isChecked() ? "3" : "-1");
        json.put("F404", bi.F404.isChecked() ? "4" : "-1");
        json.put("F405", bi.F405.isChecked() ? "5" : "-1");
        json.put("F496", bi.F496.isChecked() ? "96" : "-1");
        json.put("F496x", bi.F496x.getText().toString().trim().isEmpty() ? "-1" : bi.F496x.getText().toString().trim());

        json.put("F5", bi.F501.isChecked() ? "1"
                : bi.F502.isChecked() ? "2"
                : bi.F598.isChecked() ? "98"
                : "-1");

        json.put("F6", bi.F601.isChecked() ? "1"
                : bi.F602.isChecked() ? "2"
                : bi.F603.isChecked() ? "3"
                : bi.F604.isChecked() ? "4"
                : bi.F605.isChecked() ? "5"
                : "-1");

        json.put("F701", bi.F701.isChecked() ? "1" : "-1");
        json.put("F702", bi.F702.isChecked() ? "2" : "-1");
        json.put("F703", bi.F703.isChecked() ? "3" : "-1");
        json.put("F704", bi.F704.isChecked() ? "4" : "-1");
        json.put("F705", bi.F705.isChecked() ? "5" : "-1");
        json.put("F706", bi.F706.isChecked() ? "6" : "-1");
        json.put("F707", bi.F707.isChecked() ? "7" : "-1");
        json.put("F708", bi.F708.isChecked() ? "8" : "-1");
        json.put("F709", bi.F709.isChecked() ? "9" : "-1");
        json.put("F710", bi.F710.isChecked() ? "10" : "-1");
        json.put("F711", bi.F711.isChecked() ? "11" : "-1");
        json.put("F712", bi.F712.isChecked() ? "12" : "-1");
        json.put("F713", bi.F713.isChecked() ? "13" : "-1");
        json.put("F714", bi.F714.isChecked() ? "14" : "-1");
        json.put("F715", bi.F715.isChecked() ? "15" : "-1");
        json.put("F716", bi.F716.isChecked() ? "16" : "-1");
        json.put("F796", bi.F796.isChecked() ? "96" : "-1");
        json.put("F796x", bi.F796x.getText().toString().trim().isEmpty() ? "-1" : bi.F796x.getText().toString().trim());

        json.put("F801", bi.F801.isChecked() ? "1" : "-1");
        json.put("F802", bi.F802.isChecked() ? "2" : "-1");
        json.put("F803", bi.F803.isChecked() ? "3" : "-1");
        json.put("F804", bi.F804.isChecked() ? "4" : "-1");
        json.put("F805", bi.F805.isChecked() ? "5" : "-1");
        json.put("F896", bi.F896.isChecked() ? "96" : "-1");
        json.put("F896x", bi.F896x.getText().toString().trim().isEmpty() ? "-1" : bi.F896x.getText().toString().trim());

        MainApp.setGPS(this, SectionFActivity);

        MainApp.form.setSection_F(String.valueOf(json));
    }


    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

}