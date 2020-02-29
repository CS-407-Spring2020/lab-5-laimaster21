package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public void clickFunction1( View view){
        Log.i("info","button1 pressed");
        EditText myTextField=(EditText) findViewById(R.id.editText_username);
        //Toast.makeText(MainActivity.this,myTextField.getText().toString(),Toast.LENGTH_LONG).show();
        String str=myTextField.getText().toString();

        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply(); // save username in prefer

        gotoActivity2();
    }

    public void gotoActivity2(){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userNameKey="username";
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(userNameKey,"").equals("")){
            String username_give=sharedPreferences.getString(userNameKey,"");
            gotoActivity2();
        }else{
            setContentView(R.layout.activity_main);
        }
    }
}
