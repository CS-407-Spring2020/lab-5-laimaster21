package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String userNameKey="username";
        // fetch shared preferences
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        textview= (TextView) findViewById(R.id.textView);
        //Intent intent=getIntent();
        // get name from shared preferences
        String str=sharedPreferences.getString(userNameKey,"");
        textview.setText("Welcome "+str);
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
                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }

}
