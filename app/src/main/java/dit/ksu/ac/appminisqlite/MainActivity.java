package dit.ksu.ac.appminisqlite;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //ทำการประกาศตัวแปร
    private SQLiteDatabase sqLiteDatabase;
    ListView listmemory;
    EditText searchedittext;
    ImageButton btnsearch,btnexit,btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //เชื่อมต่อ db
        MemOpenHelper memOpenHelper = new MemOpenHelper(this);
        // ผูกตัวแปรกับไอดีใน layout
        listmemory = (ListView)findViewById(R.id.listmemory);
        searchedittext = (EditText)findViewById(R.id.searcheditText);
        btnsearch = (ImageButton)findViewById(R.id.btnsearch);
        btnexit = (ImageButton)findViewById(R.id.btnexit);
        btnadd = (ImageButton)findViewById(R.id.btnadd);
        // cleartable();
        showmemoryall();
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchmem(searchedittext.getText().toString());
            }
            private void searchmem(String s) {
                sqLiteDatabase =
                        openOrCreateDatabase(MemOpenHelper.DBNAME,MODE_PRIVATE,null);
                Cursor cursor = sqLiteDatabase.rawQuery("select * from tbmemory where title like '%"+s+"%' or" +
                        " detail like '%"+s+"%' ",null);
                cursor.moveToFirst();

                String[] titleStrings = new String[cursor.getCount()];
                String[] detailStrings = new String[cursor.getCount()];
                String[] dateStrings = new String[cursor.getCount()];
                for(int i = 0; i < cursor.getCount(); i++){
                    titleStrings[i] =
                            cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_TITLE));
                    detailStrings[i] =
                            cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_DETAIL));
                    dateStrings[i] =
                            cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_DATE));
                    cursor.moveToNext();
                }
                cursor.close();
                Showmem showmem = new
                        Showmem(MainActivity.this,titleStrings,detailStrings,dateStrings);
                listmemory.setAdapter(showmem);
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoadd = new Intent(MainActivity.this,addmemory.class);
                startActivity(gotoadd);
                finish();
            }
        });
    }

    private void cleartable() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MemOpenHelper.DBNAME,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MemOpenHelper.TBMEMORY, null, null);
    }
    private void showmemoryall() {
        sqLiteDatabase =
                openOrCreateDatabase(MemOpenHelper.DBNAME,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tbmemory ",null);
        cursor.moveToFirst();
        final String[] idStrings = new String[cursor.getCount()];
        final String[] titleStrings = new String[cursor.getCount()];
        final String[] detailStrings = new String[cursor.getCount()];
        final String[] dateStrings = new String[cursor.getCount()];
        for(int i = 0; i < cursor.getCount(); i++){
            idStrings[i] = cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_ID));
            titleStrings[i] =
                    cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_TITLE));
            detailStrings[i] =
                    cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_DETAIL));
            dateStrings[i] =
                    cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_DATE));
            cursor.moveToNext();
        }
        cursor.close();
        Showmem showmem = new
                Showmem(MainActivity.this,titleStrings,detailStrings,dateStrings);
        listmemory.setAdapter(showmem);
        listmemory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long
                    id) {
                AlertDialog.Builder alb = new AlertDialog.Builder(MainActivity.this);
                alb.setTitle("กรุณาเลือกเมนู");

                alb.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent gotoedit = new Intent(MainActivity.this,EditmemoryActivity.class);
                        gotoedit.putExtra("title",titleStrings[position]);
                        gotoedit.putExtra("detail",detailStrings[position]);
                        gotoedit.putExtra("date",dateStrings[position]);
                        startActivity(gotoedit);
                    }
                });
                alb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqLiteDatabase =
                                openOrCreateDatabase(MemOpenHelper.DBNAME,MODE_PRIVATE,null);
                        Cursor cursor = sqLiteDatabase.rawQuery("delete from tbmemory where _id ='"+idStrings[position]+"' ",null);
                                cursor.moveToFirst();
                        final String[] idStrings = new String[cursor.getCount()];
                        final String[] titleStrings = new String[cursor.getCount()];
                        final String[] detailStrings = new String[cursor.getCount()];
                        final String[] dateStrings = new String[cursor.getCount()];
                        for(int i = 0; i < cursor.getCount(); i++){
                            idStrings[i] =
                                    cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_ID));
                            titleStrings[i] =
                                    cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_TITLE));
                            detailStrings[i] =
                                    cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_DETAIL));
                            dateStrings[i] =
                                    cursor.getString(cursor.getColumnIndex(MemOpenHelper.COLUMN_DATE));
                            cursor.moveToNext();
                        }
                        cursor.close();

                        Showmem showmem = new
                                Showmem(MainActivity.this,titleStrings,detailStrings,dateStrings);
                        listmemory.setAdapter(showmem);
                    }
                });
                alb.setCancelable(true);
                alb.show();
            }
        });
    }
}