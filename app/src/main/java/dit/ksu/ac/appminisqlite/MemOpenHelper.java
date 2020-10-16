package dit.ksu.ac.appminisqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by suksun on 7/1/2017 AD.
 */
public class MemOpenHelper extends SQLiteOpenHelper{

        //ประกาศตัวแปร
        public static final String DBNAME = "my_calandburn.db";
    private static final int DBVERSION = 1;
    // กรณีสร้างตารางชื่อว่า tbmemory เพียง 1 ตาราง
    private static final String MEMORY_TABLE = "create table tbmemory (_idinteger primary key, " +
            "title text, " +
            "detail text, " +
            "date text);";
    /* กรณีต้องการสร้างตารางเพิ่มให้ประกาศตัวแปรเพื่อเก็บคำสั่ง SQL สำหรับสร้างตารางเพิ่มเติม
 private static final String ACCOUT_TABLE = "create table tbaccout(_id integer
primary key, " +
 "name text, " +
 “balance text, " +
 "date text);";
*/
    private SQLiteDatabase writeSQLite, readSQLite;
    // ประกาศตัวแปรสำหรับอ้างอิงเพื่อสะดวกในการจัดการกับตาราง tbmemory
    public static final String TBMEMORY = "tbmemory";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DETAIL = "detail";
    public static final String COLUMN_DATE = "date";
    // ถ้าผู้อ่านมีตารางเพิ่มเติม สามารถประกาศคล้ายโค้ดข้างต้น
    public MemOpenHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
        writeSQLite = this.getWritableDatabase();
        readSQLite = this.getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// คำสั่งให้สร้างตารางและเพิ่มระเบียนในตาราง memory
        db.execSQL(MEMORY_TABLE);
        db.execSQL("INSERT INTO tbmemory(title, detail, date) VALUES ('title1','รายละเอียด', '30-07-2017');");
        db.execSQL("INSERT INTO tbmemory(title, detail, date) VALUES ('ชื่อ เรื่อง','รายละเอียด', '30-07-2017');");
        db.execSQL("INSERT INTO tbmemory(title, detail, date) VALUES ('title2','รายละเอียด', '30-07-2017');");
        db.execSQL("INSERT INTO tbmemory(title, detail, date) VALUES ('ชื่อ เรื่อง2','รายละเอียด', '30-07-2017');");
        db.execSQL("INSERT INTO tbmemory(title, detail, date) VALUES ('ชื่อ เรื่อง3','รายละเอียด', '30-07-2017');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long addmemory(String strTitle,
                          String strDetail,
                          String strDate) {
        ContentValues objvalue = new ContentValues();
        objvalue.put(COLUMN_TITLE, strTitle);
        objvalue.put(COLUMN_DETAIL, strDetail);
        objvalue.put(COLUMN_DATE, strDate);
        return writeSQLite.insert(TBMEMORY, null, objvalue);
    }
}