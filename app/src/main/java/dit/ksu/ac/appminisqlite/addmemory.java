package dit.ksu.ac.appminisqlite;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class addmemory  extends AppCompatActivity {
    //ประกาศตัวแปร
    private MemOpenHelper memOpenHelper;
    private EditText titleEditText,detailEditText,dateEditText;
    private String titlestring,detailstring,datestring;
    private Button btnaddmem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmemory);
        titleEditText = (EditText)findViewById(R.id.titleeditText);
        detailEditText = (EditText)findViewById(R.id.detaileditText);
        dateEditText = (EditText)findViewById(R.id.dateeditText);
        btnaddmem = (Button)findViewById(R.id.btnaddmem);
        memOpenHelper = new MemOpenHelper(this);
        btnaddmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titlestring = titleEditText.getText().toString();
                detailstring = detailEditText.getText().toString();
                datestring = dateEditText.getText().toString();
                if(titlestring.equals("")|| detailstring.equals("")){
                    Toast.makeText(addmemory.this,"ข้อมูลไม่ครบ ถ้วน",Toast.LENGTH_SHORT).show();
                }else{
                    memOpenHelper.addmemory(titlestring,detailstring,datestring);
                    Toast.makeText(addmemory.this,"บันทึกเรียบร้อย",Toast.LENGTH_SHORT).show();
                            Intent gotomain = new Intent(addmemory.this,MainActivity.class);
                    startActivity(gotomain);
                    finish();
                }
            }
        });
    }
}


