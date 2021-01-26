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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.ses.R;
import edu.aku.hassannaqvi.ses.contracts.FormsContract;
import edu.aku.hassannaqvi.ses.core.DatabaseHelper;
import edu.aku.hassannaqvi.ses.core.MainApp;
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionBBinding;
import edu.aku.hassannaqvi.ses.models.Form;
import edu.aku.hassannaqvi.ses.ui.other.MainActivity;

import static edu.aku.hassannaqvi.ses.core.MainApp.form;


public class SectionBActivity extends AppCompatActivity {

    ActivitySectionBBinding bi;
    Intent oF = null;
    private int PhotoSerial;
    String SectionBActivity;
    private DatabaseHelper db;
    private List<String> somelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);

        PhotoSerial = 1;
    }


    private void setupSkip() {

        bi.B5.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpCVB6B45);
            bi.fldGrpCVB6B45.setVisibility(View.GONE);
            if (i == bi.B501.getId() || i == bi.B504.getId()) {
                bi.fldGrpCVB6B45.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVB6B45);
                bi.fldGrpCVB6B45.setVisibility(View.GONE);
            }
        });

        bi.B6.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B601.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB7);
                bi.fldGrpCVB7.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB7.setVisibility(View.VISIBLE);
            }
        });

        bi.B13.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B1301.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB14);
                bi.fldGrpCVB14.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB14.setVisibility(View.VISIBLE);
            }
        });

        bi.B18.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B1802.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB19);
                Clear.clearAllFields(bi.fldGrpCVB20);
                bi.fldGrpCVB19.setVisibility(View.GONE);
                bi.fldGrpCVB20.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB19.setVisibility(View.VISIBLE);
                bi.fldGrpCVB20.setVisibility(View.VISIBLE);
            }
        });

        bi.B21.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B2102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB22);
                Clear.clearAllFields(bi.fldGrpCVB23);
                bi.fldGrpCVB22.setVisibility(View.GONE);
                bi.fldGrpCVB23.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB22.setVisibility(View.VISIBLE);
                bi.fldGrpCVB23.setVisibility(View.VISIBLE);
            }
        });

        bi.B26.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B2602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB27);
                bi.fldGrpCVB27.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB27.setVisibility(View.VISIBLE);
            }
        });


        bi.B33.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B3302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB34);
                bi.fldGrpCVB34.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB34.setVisibility(View.VISIBLE);
            }
        });

        bi.B35.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B3502.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB36);
                bi.fldGrpCVB36.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB36.setVisibility(View.VISIBLE);
            }
        });

        bi.B37.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B3702.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB38);
                bi.fldGrpCVB38.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB38.setVisibility(View.VISIBLE);
            }
        });

        bi.B39.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B3902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB40);
                bi.fldGrpCVB40.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB40.setVisibility(View.VISIBLE);
            }
        });

        bi.B41.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.B4102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVB42);
                Clear.clearAllFields(bi.fldGrpCVB43);
                bi.fldGrpCVB42.setVisibility(View.GONE);
                bi.fldGrpCVB43.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVB42.setVisibility(View.VISIBLE);
                bi.fldGrpCVB43.setVisibility(View.VISIBLE);
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
            if (bi.B502.isChecked() || bi.B503.isChecked()) {
                startActivity(new Intent(this, CaptureImageActivity.class).putExtra("skip_flag", "FB"));
            } else {
                startActivity(new Intent(this, SectionCActivity.class));
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
        long updcount = db.addForm(form);
        form.setId(String.valueOf(updcount));
        if (updcount > 0) {
            form.setUid(form.getDeviceid() + form.getId());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.getUid());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
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


        MainApp.form = new Form();
        MainApp.form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime()));
        MainApp.form.setUsername(MainApp.userName);
        MainApp.form.setDeviceid(MainApp.appInfo.getDeviceID());

        JSONObject json = new JSONObject();

        json.put("B1", bi.B1.getText().toString().trim().isEmpty() ? "-1" : bi.B1.getText().toString().trim());
        json.put("B2", bi.B2.getText().toString().trim().isEmpty() ? "-1" : bi.B2.getText().toString().trim());
        json.put("B3", bi.B3.getText().toString().trim().isEmpty() ? "-1" : bi.B3.getText().toString().trim());
        json.put("B4", bi.B4.getText().toString().trim().isEmpty() ? "-1" : bi.B4.getText().toString().trim());

        json.put("B5", bi.B501.isChecked() ? "1"
                : bi.B502.isChecked() ? "2"
                : bi.B503.isChecked() ? "3"
                : bi.B504.isChecked() ? "4"
                : "-1");

        json.put("B51", bi.B51.getText().toString().trim().isEmpty() ? "-1" : bi.B51.getText().toString().trim());
        json.put("B52", bi.B52.getText().toString().trim().isEmpty() ? "-1" : bi.B52.getText().toString().trim());

        json.put("B6", bi.B601.isChecked() ? "1"
                : bi.B602.isChecked() ? "2"
                : bi.B698.isChecked() ? "98"
                : "-1");

        json.put("B7", bi.B701.isChecked() ? "1"
                : bi.B702.isChecked() ? "2"
                : bi.B796.isChecked() ? "96"
                : "-1");

        json.put("B796x", bi.B796x.getText().toString().trim().isEmpty() ? "-1" : bi.B796x.getText().toString().trim());

        json.put("B8", bi.B801.isChecked() ? "1"
                : bi.B802.isChecked() ? "2"
                : bi.B803.isChecked() ? "3"
                : "-1");

        json.put("B9", bi.B901.isChecked() ? "1"
                : bi.B902.isChecked() ? "2"
                : bi.B903.isChecked() ? "3"
                : bi.B904.isChecked() ? "4"
                : "-1");

        json.put("B10", bi.B1001.isChecked() ? "1"
                : bi.B1002.isChecked() ? "2"
                : bi.B1003.isChecked() ? "3"
                : "-1");

        json.put("B1101", bi.B1101.getText().toString().trim().isEmpty() ? "-1" : bi.B1101.getText().toString().trim());
        json.put("B1102", bi.B1102.getText().toString().trim().isEmpty() ? "-1" : bi.B1102.getText().toString().trim());
        json.put("B1103", bi.B1103.getText().toString().trim().isEmpty() ? "-1" : bi.B1103.getText().toString().trim());
        json.put("B1104", bi.B1104.getText().toString().trim().isEmpty() ? "-1" : bi.B1104.getText().toString().trim());
        json.put("B1105", bi.B1105.getText().toString().trim().isEmpty() ? "-1" : bi.B1105.getText().toString().trim());


        json.put("B12", bi.B12.getText().toString().trim().isEmpty() ? "-1" : bi.B12.getText().toString().trim());

        json.put("B13", bi.B1301.isChecked() ? "1"
                : bi.B1302.isChecked() ? "2"
                : "-1");

        json.put("B14", bi.B14.getText().toString().trim().isEmpty() ? "-1" : bi.B14.getText().toString().trim());

        json.put("B15", bi.B1501.isChecked() ? "1"
                : bi.B1502.isChecked() ? "2"
                : "-1");

        json.put("B1601", bi.B1601.getText().toString().trim().isEmpty() ? "-1" : bi.B1601.getText().toString().trim());
        json.put("B1602", bi.B1602.getText().toString().trim().isEmpty() ? "-1" : bi.B1602.getText().toString().trim());

        json.put("B1701", bi.B1701.getText().toString().trim().isEmpty() ? "-1" : bi.B1701.getText().toString().trim());
        json.put("B1702", bi.B1702.getText().toString().trim().isEmpty() ? "-1" : bi.B1702.getText().toString().trim());
        json.put("B1703", bi.B1703.getText().toString().trim().isEmpty() ? "-1" : bi.B1703.getText().toString().trim());

        json.put("B18", bi.B1801.isChecked() ? "1"
                : bi.B1802.isChecked() ? "2"
                : "-1");

        json.put("B1901", bi.B1901.isChecked() ? "1" : "-1");
        json.put("B1902", bi.B1902.isChecked() ? "2" : "-1");
        json.put("B1903", bi.B1903.isChecked() ? "3" : "-1");
        json.put("B1904", bi.B1904.isChecked() ? "4" : "-1");

        json.put("B20", bi.B2001.isChecked() ? "1"
                : bi.B2002.isChecked() ? "2"
                : "-1");

        json.put("B21", bi.B2101.isChecked() ? "1"
                : bi.B2102.isChecked() ? "2"
                : "-1");

        json.put("B22", bi.B2201.isChecked() ? "1"
                : bi.B2202.isChecked() ? "2"
                : bi.B2203.isChecked() ? "3"
                : bi.B2204.isChecked() ? "4"
                : "-1");

        json.put("B23", bi.B2301.isChecked() ? "1"
                : bi.B2302.isChecked() ? "2"
                : "-1");

        json.put("B24", bi.B24.getText().toString().trim().isEmpty() ? "-1" : bi.B24.getText().toString().trim());

        json.put("B2501", bi.B25.getText().toString().trim().isEmpty() ? "-1" : bi.B25.getText().toString().trim());

        json.put("B26", bi.B2601.isChecked() ? "1"
                : bi.B2602.isChecked() ? "2"
                : "-1");

        json.put("B27", bi.B27.getText().toString().trim().isEmpty() ? "-1" : bi.B27.getText().toString().trim());

        json.put("B2801", bi.B2801.getText().toString().trim().isEmpty() ? "-1" : bi.B2801.getText().toString().trim());
        json.put("B2802", bi.B2802.getText().toString().trim().isEmpty() ? "-1" : bi.B2802.getText().toString().trim());

        json.put("B2901", bi.B2901.getText().toString().trim().isEmpty() ? "-1" : bi.B2901.getText().toString().trim());
        json.put("B2902", bi.B2902.getText().toString().trim().isEmpty() ? "-1" : bi.B2902.getText().toString().trim());
        json.put("B2903", bi.B2903.getText().toString().trim().isEmpty() ? "-1" : bi.B2903.getText().toString().trim());
        json.put("B2904", bi.B2904.getText().toString().trim().isEmpty() ? "-1" : bi.B2904.getText().toString().trim());
        json.put("B2905", bi.B2905.getText().toString().trim().isEmpty() ? "-1" : bi.B2905.getText().toString().trim());
        json.put("B2906", bi.B2906.getText().toString().trim().isEmpty() ? "-1" : bi.B2906.getText().toString().trim());

        json.put("B30", bi.B3001.isChecked() ? "1"
                : bi.B3002.isChecked() ? "2"
                : "-1");

        json.put("B31", bi.B3101.isChecked() ? "1"
                : bi.B3102.isChecked() ? "2"
                : "-1");

        json.put("B32", bi.B3201.isChecked() ? "1"
                : bi.B3202.isChecked() ? "2"
                : "-1");

        json.put("B33", bi.B3301.isChecked() ? "1"
                : bi.B3302.isChecked() ? "2"
                : "-1");

        json.put("B34", bi.B34.getText().toString().trim().isEmpty() ? "-1" : bi.B34.getText().toString().trim());

        json.put("B35", bi.B3501.isChecked() ? "1"
                : bi.B3502.isChecked() ? "2"
                : "-1");

        json.put("B36", bi.B36.getText().toString().trim().isEmpty() ? "-1" : bi.B36.getText().toString().trim());

        json.put("B37", bi.B3701.isChecked() ? "1"
                : bi.B3702.isChecked() ? "2"
                : "-1");

        json.put("B38", bi.B38.getText().toString().trim().isEmpty() ? "-1" : bi.B38.getText().toString().trim());

        json.put("B39", bi.B3901.isChecked() ? "1"
                : bi.B3902.isChecked() ? "2"
                : "-1");

        json.put("B40", bi.B4001.isChecked() ? "1"
                : bi.B4002.isChecked() ? "2"
                : "-1");

        json.put("B41", bi.B4101.isChecked() ? "1"
                : bi.B4102.isChecked() ? "2"
                : "-1");

        json.put("B42", bi.B4201.isChecked() ? "1"
                : bi.B4202.isChecked() ? "2"
                : "-1");

        json.put("B43", bi.B4301.isChecked() ? "1"
                : bi.B4302.isChecked() ? "2"
                : "-1");

        /*json.put("B44", bi.B44.getText().toString().trim().isEmpty() ? "-1" : bi.B44.getText().toString().trim());
        json.put("B45", bi.B45.getText().toString().trim().isEmpty() ? "-1" : bi.B45.getText().toString().trim());*/

        MainApp.setGPS(this, SectionBActivity);
        MainApp.form.setSection_B(String.valueOf(json));
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

}