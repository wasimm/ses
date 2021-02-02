package edu.aku.hassannaqvi.ses.ui.sections;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.List;

import edu.aku.hassannaqvi.ses.R;
import edu.aku.hassannaqvi.ses.contracts.FormsContract;
import edu.aku.hassannaqvi.ses.core.DatabaseHelper;
import edu.aku.hassannaqvi.ses.core.MainApp;
import edu.aku.hassannaqvi.ses.databinding.ActivityCaptureImagesBinding;
import edu.aku.hassannaqvi.ses.ui.other.EndingActivity;
import edu.aku.hassannaqvi.ses.ui.other.MainActivity;
import edu.aku.hassannaqvi.ses.ui.other.TakePhoto;


public class CaptureImageActivity extends AppCompatActivity {

    ActivityCaptureImagesBinding bi;
    Intent oF = null;
    String skip_flag;
    private int PhotoSerial;
    private List<String> somelist;
    private DatabaseHelper db;
    private String semisCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_capture_images);
        bi.setCallback(this);
        setupSkip();
        PhotoSerial = 0;

        /*Intent intent = getIntent();
        skip_flag = intent.getStringExtra("skip_flag");
        if (skip_flag == "FB") {
            SectionCActivity C = new SectionCActivity();
            SectionD1Activity D1 = new SectionD1Activity();
            SectionD2Activity D2 = new SectionD2Activity();
            SectionEActivity E = new SectionEActivity();
            SectionFActivity F = new SectionFActivity();
            C.BtnContinue();
            D1.BtnContinue();
            D2.BtnContinue();
            E.BtnContinue();
            F.BtnContinue();
            finish();
        }*/

        Intent intent = getIntent();
        semisCode = intent.getStringExtra("semisCode");
    }


    private void setupSkip() {
    }

    public void BtnContinue() {
        if (!formValidation()) return;
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
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

    private boolean formValidation() {

        //if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {return false;}
        if (PhotoSerial < 1) {
            Toast.makeText(this, "minimum 1 picture must be taken", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void TakePhoto(int id) {

        if (PhotoSerial > 3) {
            Toast.makeText(this, "Maximum 4 pictures are allowed", Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent(this, TakePhoto.class);
            intent.putExtra("picID", semisCode + PhotoSerial);
            intent.putExtra("childName", "");
            intent.putExtra("picView", "School".toUpperCase());
            if (id == 1) {
                intent.putExtra("viewFacing", "1");
            } else {
                intent.putExtra("viewFacing", "2");
            }

            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
            String fileName = data.getStringExtra("FileName");
            bi.fileName.setText(bi.fileName.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
            PhotoSerial++;
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        Toast.makeText(this, "You Can't go back", Toast.LENGTH_LONG).show();
    }
}