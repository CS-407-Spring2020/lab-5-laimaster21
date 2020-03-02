package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    int noteid=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 1. get EditText view.
        TextView editTextView= (TextView) findViewById(R.id.editText);

        //2. get intent
        Intent intent=getIntent();
        int noteId = intent.getIntExtra("noteId",-1);
        noteid=noteId;
        //3.

        if(noteid!=-1){
            Note note= Main2Activity.notes.get(noteid);
            String noteContent=note.getContent();
            editTextView.setText(noteContent);
        }
    }

    public void SaveMethod(View view){
        //1. get edit text view and content entered
        TextView editTextView= (TextView) findViewById(R.id.editText);
        String content=editTextView.getText().toString();

        //2. Initialize SQL database instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase( "notes" ,
                Context.MODE_PRIVATE, null );

        // 3. initiate the notes class variable
        DBHelper dbHelper=new DBHelper(sqLiteDatabase);

        // 4. get username
        String userNameKey="username";
        // fetch shared preferences
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString(userNameKey,"");

        //5. save info to database
        String title;
        DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date=dateFormat.format(new Date());

        if(noteid==-1){
            title="NOTE_"+(Main2Activity.notes.size()+1);
            dbHelper.saveNotes(username,title,content,date);
        }else{
            title="NOTE_"+(noteid+1);
            dbHelper.updateNotes(username,title,content,date);
        }


        // go to second activity
        Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }
}
