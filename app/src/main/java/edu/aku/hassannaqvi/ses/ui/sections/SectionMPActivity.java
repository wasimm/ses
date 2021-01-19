package edu.aku.hassannaqvi.ses.ui.sections;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.ses.CONSTANTS;
import edu.aku.hassannaqvi.ses.R;
import edu.aku.hassannaqvi.ses.contracts.FormsContract;
import edu.aku.hassannaqvi.ses.core.DatabaseHelper;
import edu.aku.hassannaqvi.ses.core.MainApp;
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionMpBinding;
import edu.aku.hassannaqvi.ses.models.Form;
import edu.aku.hassannaqvi.ses.models.Villages;
import edu.aku.hassannaqvi.ses.ui.other.EndingActivity;

import static edu.aku.hassannaqvi.ses.CONSTANTS.FORM_MP;
import static edu.aku.hassannaqvi.ses.CONSTANTS.SELECTED_MODEL;
import static edu.aku.hassannaqvi.ses.core.MainApp.form;


public class SectionMPActivity extends AppCompatActivity {

    ActivitySectionMpBinding bi;
    int uc;
    private List<String> usersFullName, ucNames, ucCodes, villageNames, villageCodes;
    private DatabaseHelper db;
    private int PID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_mp);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);

        //db.resetAll();
        //Toast.makeText(this, "Updated: " + new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()), Toast.LENGTH_SHORT).show();
    }


    private void setupSkip() {

        bi.mp108.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bi.mp10801.getId()) {
                Clear.clearAllFields(bi.fldGrpCVmp109);
                bi.fldGrpCVmp109.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVmp109.setVisibility(View.VISIBLE);
            }
        });
    }


    private void populateSpinner(final Context context) {

        db = MainApp.appInfo.getDbHelper();
        // Spinner Drop down elements
        /*usersFullName = new ArrayList<String>() {
            {
                add("....");
            }
        };

        Collection<Users> dc = db.getUsers();
        for (Users us : dc) {
            usersFullName.add(us.getFull_name());
        }

        bi.mp102.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, usersFullName));
        bi.mp102.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    bi.mp103.setSelection(0);
                    bi.mp103.setEnabled(false);
                    return;
                }

                bi.mp103.setEnabled(true);
                List<String> user2 = new ArrayList<>();

                for (String names : usersFullName) {
                    if (names.equals(bi.mp102.getSelectedItem().toString())) continue;
                    user2.add(names);
                }

                bi.mp103.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, user2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        bi.mp103.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;
                ucNames = new ArrayList<>();
                ucCodes = new ArrayList<>();
                ucNames.add("....");
                ucCodes.add("....");

                Collection<Villages> pc = db.getVillageUc();
                for (Villages p : pc) {
                    ucNames.add(p.getUcname());
                    ucCodes.add(p.getUcid());
                }

                bi.mp105.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ucNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        ucNames = new ArrayList<>();
        ucCodes = new ArrayList<>();
        ucNames.add("....");
        ucCodes.add("....");

        Collection<Villages> pc = db.getVillageUc();
        for (Villages p : pc) {
            ucNames.add(p.getUcname());
            ucCodes.add(p.getUcid());
        }
        bi.mp105.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ucNames));

        bi.mp105.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;
                villageNames = new ArrayList<>();
                villageCodes = new ArrayList<>();
                villageNames.add("....");
                villageCodes.add("....");

                Collection<Villages> pc = db.getVillageByUc(bi.mp105.getSelectedItem().toString());
                for (Villages p : pc) {
                    villageNames.add(p.getVillagename());
                    villageCodes.add(p.getSeem_vid());
                }

                bi.mp104.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, villageNames));

                int selectedUC = bi.mp105.getSelectedItemPosition();
                if (selectedUC == 1) {
                    uc = 4;
                } else if (selectedUC == 2) {
                    uc = 5;
                } else if (selectedUC == 3) {
                    uc = 3;
                } else if (selectedUC == 4) {
                    uc = 2;
                }

                int flag;
                flag = db.getRecord(uc);
                if (flag > 0) {
                    //Toast.makeText(context, "Has Records", Toast.LENGTH_SHORT).show();
                    PID = db.getPID(uc) + 1;
                } else {
                    //Toast.makeText(context, "No Records", Toast.LENGTH_SHORT).show();
                    if (uc == 2) {
                        PID = 35001;
                    } else if (uc == 3) {
                        PID = 30001;
                    } else if (uc == 4) {
                        PID = 20001;
                    } else if (uc == 5) {
                        PID = 25001;
                    }
                }

                bi.pid.setText("PID: " + PID);
                bi.mp106.setText(String.valueOf(PID));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true).putExtra(SELECTED_MODEL, FORM_MP));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime()));
        form.setFormtype(CONSTANTS.FORM_MP);
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());
        //form.set_luid(form.get_luid());
        form.setMp101(bi.mp101.getText().toString().trim().isEmpty() ? "-1" : bi.mp101.getText().toString());
        form.setMp102(MainApp.userName);
        form.setMp103(MainApp.userName);
        form.setMp104(villageCodes.get(bi.mp104.getSelectedItemPosition()));
        form.setMp105(ucCodes.get(bi.mp105.getSelectedItemPosition()));
        //form.setMp106(MainApp.userName);
        form.setMp106(bi.mp106.getText().toString().trim().isEmpty() ? "-1" : bi.mp106.getText().toString());
        form.setMp107(bi.mp107.getText().toString().trim().isEmpty() ? "-1" : bi.mp107.getText().toString());

        form.setMp108(bi.mp10801.isChecked() ? "1"
                : bi.mp10802.isChecked() ? "2"
                : "-1");

        form.setSeem_vid(ucCodes.get(bi.mp105.getSelectedItemPosition()) + villageCodes.get(bi.mp104.getSelectedItemPosition()));

        form.setMp109(bi.mp10901.isChecked() ? "1"
                : bi.mp10902.isChecked() ? "2"
                : bi.mp10903.isChecked() ? "3"
                : bi.mp10904.isChecked() ? "4"
                : bi.mp10905.isChecked() ? "5"
                : bi.mp10906.isChecked() ? "6"
                : bi.mp10907.isChecked() ? "7"
                : bi.mp10908.isChecked() ? "8"
                : bi.mp10909.isChecked() ? "9"
                : bi.mp10910.isChecked() ? "10"
                : "-1");
        form.setMp10910x(bi.mp10910x.getText().toString().trim().isEmpty() ? "-1" : bi.mp10910x.getText().toString());

        form.setMp110a(bi.mp110a.getText().toString().trim().isEmpty() ? "-1" : bi.mp110a.getText().toString());
        form.setMp110b(bi.mp110b.getText().toString().trim().isEmpty() ? "-1" : bi.mp110b.getText().toString());
        form.setMp110c(bi.mp110c.getText().toString().trim().isEmpty() ? "-1" : bi.mp110c.getText().toString());
        form.setMp110d(bi.mp110d.getText().toString().trim().isEmpty() ? "-1" : bi.mp110d.getText().toString());


        Toast.makeText(getApplicationContext(), "" + this, Toast.LENGTH_SHORT).show();

        MainApp.setGPS(this, FORM_MP);
    }


    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    /*public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }*/

}