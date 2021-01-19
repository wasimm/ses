package edu.aku.hassannaqvi.ses.utils;

import edu.aku.hassannaqvi.ses.contracts.AssessmentContract;
import edu.aku.hassannaqvi.ses.contracts.BLRandomContract.BLRandomTable;
import edu.aku.hassannaqvi.ses.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.ses.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.ses.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.ses.contracts.VillagesContract;

public final class CreateTable {

    public static final String DATABASE_NAME = "moringa_plantation.db";
    public static final String DB_NAME = "moringa_plantation_copy.db";
    public static final String PROJECT_NAME = "moringa_plantation";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_UID + " TEXT,"
            + FormsTable.COLUMN_SEEM_VID + " TEXT,"
            //+ FormsTable.COLUMN_MPSYSDATE + " TEXT,"
            + FormsTable.COLUMN_FORMTYPE + " TEXT,"
            + FormsTable.COLUMN_USERNAME + " TEXT,"
            + FormsTable.COLUMN_SYSDATE + " TEXT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_MP101 + " TEXT,"
            + FormsTable.COLUMN_MP102 + " TEXT,"
            + FormsTable.COLUMN_MP103 + " TEXT,"
            + FormsTable.COLUMN_MP104 + " TEXT,"
            + FormsTable.COLUMN_MP105 + " TEXT,"
            + FormsTable.COLUMN_MP106 + " TEXT,"
            + FormsTable.COLUMN_MP107 + " TEXT,"
            + FormsTable.COLUMN_MP108 + " TEXT,"
            + FormsTable.COLUMN_MP109 + " TEXT,"
            + FormsTable.COLUMN_MP10910x + " TEXT,"
            + FormsTable.COLUMN_MP110a + " TEXT,"
            + FormsTable.COLUMN_MP110b + " TEXT,"
            + FormsTable.COLUMN_MP110c + " TEXT,"
            + FormsTable.COLUMN_MP110d + " TEXT,"
            //+ FormsTable.COLUMN_PID + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_ISTATUS96x + " TEXT,"
            + FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsTable.COLUMN_GPSDATE + " TEXT,"
            + FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsTable.COLUMN_ENDINGDATETIME + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULL_NAME + " TEXT"
            + " );";

    public static final String SQL_CREATE_VILLAGES = "CREATE TABLE " + VillagesContract.TableVillage.TABLE_NAME + "("
            + VillagesContract.TableVillage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VillagesContract.TableVillage.COLUMN_UCNAME + " TEXT,"
            + VillagesContract.TableVillage.COLUMN_VILLAGE_NAME + " TEXT,"
            + VillagesContract.TableVillage.COLUMN_SEEM_VID + " TEXT,"
            + VillagesContract.TableVillage.COLUMN_UCID + " TEXT"
            + " );";

    public static final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";

    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + BLRandomTable.TABLE_NAME + "("
            + BLRandomTable.COLUMN_ID + " TEXT,"
            + BLRandomTable.COLUMN_P_CODE + " TEXT,"
            + BLRandomTable.COLUMN_EB_CODE + " TEXT,"
            + BLRandomTable.COLUMN_LUID + " TEXT,"
            + BLRandomTable.COLUMN_HH + " TEXT,"
            + BLRandomTable.COLUMN_STRUCTURE_NO + " TEXT,"
            + BLRandomTable.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + BLRandomTable.COLUMN_HH_HEAD + " TEXT,"
            + BLRandomTable.COLUMN_CONTACT + " TEXT,"
            + BLRandomTable.COLUMN_HH_SELECTED_STRUCT + " TEXT,"
            + BLRandomTable.COLUMN_RANDOMDT + " TEXT,"
            + BLRandomTable.COLUMN_SNO_HH + " TEXT );";

    public static final String SQL_CREATE_ASSESSMENT = "CREATE TABLE " + AssessmentContract.TableAssessment.TABLE_NAME + "("
            + AssessmentContract.TableAssessment._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AssessmentContract.TableAssessment.COLUMN__LUID + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_UID + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MAUC + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MAVI + " TEXT,"
            //+ AssessmentContract.TableAssessment.COLUMN_MASYSDATE + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_PID + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_PROJECT_NAME + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_SEEM_VID + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_SYSDATE + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_FORMTYPE + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_USERNAME + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MA101 + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MA102 + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MA103 + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MA104 + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MA105 + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_MA106 + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_ISTATUS + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_ISTATUS96x + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_GPSLAT + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_GPSLNG + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_GPSDATE + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_GPSACC + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_DEVICEID + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_DEVICETAGID + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_APPVERSION + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_ENDINGDATETIME + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_SYNCED + " TEXT,"
            + AssessmentContract.TableAssessment.COLUMN_SYNCED_DATE + " TEXT"
            + " );";
}
