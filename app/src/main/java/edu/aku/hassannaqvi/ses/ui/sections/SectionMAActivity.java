package edu.aku.hassannaqvi.ses.ui.sections;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import edu.aku.hassannaqvi.ses.contracts.AssessmentContract;
import edu.aku.hassannaqvi.ses.core.DatabaseHelper;
import edu.aku.hassannaqvi.ses.core.MainApp;
import edu.aku.hassannaqvi.ses.databinding.ActivitySectionMaBinding;
import edu.aku.hassannaqvi.ses.models.Assessment;
import edu.aku.hassannaqvi.ses.models.Villages;
import edu.aku.hassannaqvi.ses.ui.other.EndingActivity;
import edu.aku.hassannaqvi.ses.ui.other.TakePhoto;

import static edu.aku.hassannaqvi.ses.CONSTANTS.FORM_MA;
import static edu.aku.hassannaqvi.ses.CONSTANTS.SELECTED_MODEL;
import static edu.aku.hassannaqvi.ses.core.MainApp.assessment;


public class SectionMAActivity extends AppCompatActivity {

    ActivitySectionMaBinding bi;
    int uc;
    private int PhotoSerial;
    private List<String> ucNames, ucCodes, villageNames, villageCodes, PIDs;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ma);
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


        ucNames = new ArrayList<>();
        ucCodes = new ArrayList<>();
        ucNames.add("....");
        ucCodes.add("....");

        Collection<Villages> pc = db.getVillageUc();
        for (Villages p : pc) {
            ucNames.add(p.getUcname());
            ucCodes.add(p.getUcid());
        }
        bi.mauc.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ucNames));

        bi.mauc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;
                villageNames = new ArrayList<>();
                villageCodes = new ArrayList<>();
                villageNames.add("....");
                villageCodes.add("....");

                Collection<Villages> pc = db.getVillageByUc(bi.mauc.getSelectedItem().toString());
                for (Villages p : pc) {
                    villageNames.add(p.getVillagename());
                    villageCodes.add(p.getSeem_vid());
                }

                bi.mavi.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, villageNames));

                PIDs = new ArrayList<>();
                PIDs.add("....");
                int selectedUC = bi.mauc.getSelectedItemPosition();
                if (selectedUC == 1) {
                    uc = 4;
                } else if (selectedUC == 2) {
                    uc = 5;
                } else if (selectedUC == 3) {
                    uc = 3;
                } else if (selectedUC == 4) {
                    uc = 2;
                }

                Cursor PIDsList = db.getRecords(uc);

                if (PIDsList.getCount() > 0) {

                    PIDsList.moveToFirst();
                    for (int i = 0; i < PIDsList.getCount(); i++) {
                        PIDs.add(PIDsList.getString(PIDsList.getColumnIndex("mp106")));
                        PIDsList.moveToNext();
                    }
                }

                //Toast.makeText(context, ""+PIDs, Toast.LENGTH_SHORT);

                bi.pid.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, PIDs));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bi.pid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (bi.pid.getSelectedItemPosition() > 0) {
                    bi.GrpName02.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(context, "Please select a PID", Toast.LENGTH_LONG).show();
                    bi.GrpName02.setVisibility(View.GONE);
                }
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
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true).putExtra(SELECTED_MODEL, FORM_MA));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addAssessment(assessment);
        assessment.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            assessment.setUid(assessment.getDeviceid() + assessment.get_ID());
            db.updatesAssessmentColumn(AssessmentContract.TableAssessment.COLUMN_UID, assessment.getUid());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() {

        assessment = new Assessment();

        assessment.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime()));
        assessment.setFormtype(CONSTANTS.FORM_MA);
        assessment.setUsername(MainApp.userName);
        assessment.setDeviceid(MainApp.appInfo.getDeviceID());

        //assessment.setUid(MainApp.form.get_UID());

        assessment.setDeviceTagId(MainApp.appInfo.getTagName());

        assessment.setAppversion(MainApp.appInfo.getAppVersion());
        //assessment.setSeem_vid(assessment.getSeem_vid());

        assessment.setSeem_vid(ucCodes.get(bi.mauc.getSelectedItemPosition()) + villageCodes.get(bi.mavi.getSelectedItemPosition()));

        //assessment.setMasysdate(assessment.getMasysdate());

        //assessment.setPid(bi.ma103.getText().toString().trim().isEmpty() ? "-1" : bi.ma103.getText().toString());

        assessment.setMa101(bi.ma101.getText().toString().trim().isEmpty() ? "-1" : bi.ma101.getText().toString());
        assessment.setMa102(MainApp.userName);
        assessment.setMa103(MainApp.userName);

        assessment.setMa104(bi.ma10401.isChecked() ? "1"
                : bi.ma10402.isChecked() ? "2"
                : "-1");

        assessment.setMa105(bi.ma105.getText().toString().trim().isEmpty() ? "-1" : bi.ma105.getText().toString());
        assessment.setMa106(bi.ma106.getText().toString().trim().isEmpty() ? "-1" : bi.ma106.getText().toString());

        assessment.setMavi(villageCodes.get(bi.mavi.getSelectedItemPosition()));
        assessment.setMauc(ucCodes.get(bi.mauc.getSelectedItemPosition()));
        assessment.setPid(bi.pid.getSelectedItem().toString().trim().isEmpty() ? "-1" : bi.pid.getSelectedItem().toString());

        String luid;
        luid = db.getFormUID("form", String.valueOf(assessment.getPid()));
        Toast.makeText(this, "PID hhh: " + assessment.getPid(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "LUID hhh: " + luid, Toast.LENGTH_SHORT).show();
        assessment.set_luid(luid);


        MainApp.setGPS(this, FORM_MA);
    }


    private void setupFields(int view) {
        bi.GrpName02.setVisibility(view);
        Clear.clearAllFields(bi.GrpName02);
    }


    private boolean formValidation() {

        Toast.makeText(this, "" + bi.mauc.getSelectedItemPosition(), Toast.LENGTH_LONG).show();

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        if (PhotoSerial <= 1) {
            Toast.makeText(this, "Minimum 1 and maximum 4 picture(s) must be taken", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


    public void ma103OnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setupFields(View.GONE);
    }


    /*public void BtnCheckFUP(View view) {


        if (!Validator.emptyCheckingContainer(this, bi.GrpName02)) return;

        getFupByID(bi.ma103.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Assessment>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Assessment fupContract) {
                        assessment = fupContract;
                        setupFields(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SectionMAActivity.this, "No Assessment up found!!", Toast.LENGTH_SHORT).show();
                        setupFields(View.GONE);
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });


    }

    private Observable<Assessment> getFupByID(String pid) {
        return Observable.create(emitter -> {
            emitter.onNext(appInfo.getDbHelper().getAssessment(Integer.valueOf(pid).toString()));
            emitter.onComplete();
        });
    }*/

    public void TakePhoto(int id) {
        if (bi.mauc.getSelectedItemPosition() == 0 || bi.mavi.getSelectedItemPosition() == 0 || bi.pid.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Please fill the form first", Toast.LENGTH_SHORT).show();
        } else if (PhotoSerial > 4) {
            Toast.makeText(this, "Maximum 4 pictures are allowed", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, TakePhoto.class);
            intent.putExtra("picID",
                    ucCodes.get(bi.mauc.getSelectedItemPosition())
                            + "_" + villageCodes.get(bi.mavi.getSelectedItemPosition())
                            + "_" + (bi.pid.getSelectedItem().toString().trim().isEmpty() ? "-1" : bi.pid.getSelectedItem().toString())
                            + PhotoSerial
            );

            intent.putExtra("childName", "");

            intent.putExtra("picView", "Trees".toUpperCase());
            if (id == 1) {
                intent.putExtra("viewFacing", "1");
            } else {
                intent.putExtra("viewFacing", "2");
            }

            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front
        }

        //Toast.makeText(this, ""+PhotoSerial, Toast.LENGTH_SHORT).show();
    }

     /* onActivityResult(resultCode) 0= Photo Cancel, 1=Photo Taken
        if resultCode = 1 than also returns -> Intent Extra (FileName)*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        // Toast.makeText(this, requestCode + "_" + resultCode, Toast.LENGTH_SHORT).show();
        if (resultCode == 1) {
            Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
            String fileName = data.getStringExtra("FileName");
            bi.fileName.setText(bi.fileName.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
            PhotoSerial++;

           /* try {
                SaveDraft(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                PhotoSerial++;
            }*/

        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

}