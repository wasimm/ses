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
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionCBinding;
import edu.aku.hassannaqvi.ses.ui.other.MainActivity;


public class SectionCActivity extends AppCompatActivity {

    ActivitySectionCBinding bi;
    Intent oF = null;
    String SectionCActivity;
    private int PhotoSerial;
    private List<String> somelist;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);

        PhotoSerial = 1;
    }

    private void setupSkip() {

        bi.C1.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.C116.getId()) {
                Clear.clearAllFields(bi.fldGrpCVC2);
                bi.fldGrpCVC2.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVC2.setVisibility(View.VISIBLE);
            }
        });

        bi.C3.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.C302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVC4);
                Clear.clearAllFields(bi.fldGrpCVC5);
                Clear.clearAllFields(bi.fldGrpCVC6);
                bi.fldGrpCVC4.setVisibility(View.GONE);
                bi.fldGrpCVC5.setVisibility(View.GONE);
                bi.fldGrpCVC6.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVC4.setVisibility(View.VISIBLE);
                bi.fldGrpCVC5.setVisibility(View.VISIBLE);
                bi.fldGrpCVC6.setVisibility(View.VISIBLE);
            }
        });

        bi.C7.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.C702.getId()) {
                Clear.clearAllFields(bi.fldGrpCVC8);
                bi.fldGrpCVC8.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVC8.setVisibility(View.VISIBLE);
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
            startActivity(new Intent(this, SectionD1Activity.class));
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
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SECTION_C, MainApp.form.getSection_C());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("C1", bi.C101.isChecked() ? "1"
                : bi.C102.isChecked() ? "2"
                : bi.C103.isChecked() ? "3"
                : bi.C104.isChecked() ? "4"
                : bi.C105.isChecked() ? "5"
                : bi.C106.isChecked() ? "6"
                : bi.C107.isChecked() ? "7"
                : bi.C108.isChecked() ? "8"
                : bi.C109.isChecked() ? "9"
                : bi.C110.isChecked() ? "10"
                : bi.C111.isChecked() ? "11"
                : bi.C112.isChecked() ? "12"
                : bi.C113.isChecked() ? "13"
                : bi.C114.isChecked() ? "14"
                : bi.C115.isChecked() ? "15"
                : bi.C116.isChecked() ? "16"
                : bi.C117.isChecked() ? "17"
                : bi.C196.isChecked() ? "96"
                : "-1");

        json.put("C196x", bi.C196x.getText().toString().trim().isEmpty() ? "-1" : bi.C196x.getText().toString());

        json.put("C2", bi.C201.isChecked() ? "1"
                : bi.C202.isChecked() ? "2"
                : bi.C298.isChecked() ? "98"
                : "-1");

        json.put("C3", bi.C301.isChecked() ? "1"
                : bi.C302.isChecked() ? "2"
                : bi.C398.isChecked() ? "98"
                : "-1");

        json.put("C4", bi.C401.isChecked() ? "1"
                : bi.C402.isChecked() ? "2"
                : bi.C403.isChecked() ? "3"
                : bi.C404.isChecked() ? "4"
                : bi.C405.isChecked() ? "5"
                : bi.C406.isChecked() ? "6"
                : bi.C407.isChecked() ? "7"
                : bi.C408.isChecked() ? "8"
                : bi.C409.isChecked() ? "9"
                : bi.C410.isChecked() ? "10"
                : bi.C411.isChecked() ? "11"
                : bi.C412.isChecked() ? "12"
                : "-1");

        json.put("C5", bi.C5.getText().toString().trim().isEmpty() ? "-1" : bi.C5.getText().toString().trim());

        json.put("C6", bi.C601.isChecked() ? "1"
                : bi.C602.isChecked() ? "2"
                : bi.C698.isChecked() ? "98"
                : "-1");

        json.put("C7", bi.C701.isChecked() ? "1"
                : bi.C702.isChecked() ? "2"
                : bi.C798.isChecked() ? "98"
                : "-1");

        json.put("C8", bi.C801.isChecked() ? "1"
                : bi.C802.isChecked() ? "2"
                : bi.C803.isChecked() ? "3"
                : bi.C804.isChecked() ? "4"
                : "-1");

        json.put("C9", bi.C901.isChecked() ? "1"
                : bi.C902.isChecked() ? "2"
                : bi.C998.isChecked() ? "98"
                : "-1");

        json.put("C10", bi.C1001.isChecked() ? "1"
                : bi.C1002.isChecked() ? "2"
                : bi.C1098.isChecked() ? "98"
                : "-1");

        json.put("C11", bi.C1101.isChecked() ? "1"
                : bi.C1102.isChecked() ? "2"
                : bi.C1198.isChecked() ? "98"
                : "-1");

        MainApp.setGPS(this, SectionCActivity);
        MainApp.form.setSection_C(String.valueOf(json));
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

}