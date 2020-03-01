package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    TextView textview;
    public static ArrayList<Note> notes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //1. show the welcome message
        String userNameKey="username";
        // fetch shared preferences
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        textview= (TextView) findViewById(R.id.textView);
        //Intent intent=getIntent();
        // get name from shared preferences
        String str=sharedPreferences.getString(userNameKey,"");
        textview.setText("Welcome "+str);

        //2. get sql instance
        //notes=
        //DBHelper dbHelper = new DBHelper(notes);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase( "notes" ,
                Context.MODE_PRIVATE, null );

        // 3. initiate the notes class variable
        DBHelper dbHelper=new DBHelper(sqLiteDatabase);
        notes=dbHelper.readNotes(str);

        //4. create arraylist string object
        ArrayList<String> displayNotes=new ArrayList<>();
        for(Note note:notes){
            displayNotes.add(String.format("Title:%s\nDate:%s",note.getTitle(),note.getDate()));
        }
        //5. use listView to display notes on the screen
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,displayNotes);
        ListView listView=(ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        //6. add onItemClick listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // go to third activity
                Intent intent=new Intent(getApplicationContext(),Main3Activity.class);

                intent.putExtra("noteid",position);
                startActivity(intent);

            }
        });


    }
    public void gotoActivity1(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_res, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.itemLogOut:
                Toast.makeText(this,"Item 1 selected",Toast.LENGTH_SHORT).show();
                // clean preferences
                SharedPreferences sharedPreferences =
                        getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("username").apply();
                gotoActivity1();


                return true;
            case R.id.itemAddNote:
                Toast.makeText(this,"Item 2 selected",Toast.LENGTH_SHORT).show();

                // goes to third activity
                Intent intent=new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(intent);

                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }


}
