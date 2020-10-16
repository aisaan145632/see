package dit.ksu.ac.appminisqlite;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditmemoryActivity extends AppCompatActivity {
    private EditText titleedit,detailedit,dateedit;
    private String titlestring,detailstring,datestring;
    private Button btneditmem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmemory);
        //bind widget
        titleedit = (EditText)findViewById(R.id.titleedit);
        detailedit = (EditText)findViewById(R.id.detailedit);
        dateedit = (EditText)findViewById(R.id.dateedit);
        btneditmem = (Button)findViewById(R.id.btneditmem);
        // get value;
        titlestring = getIntent().getStringExtra("title");
        detailstring = getIntent().getStringExtra("detail");
        datestring = getIntent().getStringExtra("date");
        //showvalue
        titleedit.setText(titlestring);
        detailedit.setText(detailstring);
        dateedit.setText(datestring);
        btneditmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titlestring = titleedit.getText().toString().trim();
                detailstring = detailedit.getText().toString().trim();
                datestring = dateedit.getText().toString().trim();
                SQLiteDatabase sqLiteDatabase =
                        openOrCreateDatabase(MemOpenHelper.DBNAME,MODE_PRIVATE,null);
                ContentValues convalue = new ContentValues();
                convalue.put("title",titlestring);
                convalue.put("detail",detailstring);
                convalue.put("date",datestring);
                sqLiteDatabase.update("tbmemory",convalue,"title='"+titlestring+"'",null);
                Toast.makeText(EditmemoryActivity.this, "แก้ไขเรียบร้อยแล้ว",Toast.LENGTH_SHORT).show();
                Intent gotomain = new Intent(EditmemoryActivity.this,MainActivity.class);
                startActivity(gotomain);
                finish();
            }
        });
    }
}

