package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public void clickFunction1( View view){
        Log.i("info","button1 pressed");
        EditText myTextField=(EditText) findViewById(R.id.editText);
        //Toast.makeText(MainActivity.this,myTextField.getText().toString(),Toast.LENGTH_LONG).show();
        String str=myTextField.getText().toString();
        gotoActivity2(str);
    }

    public void gotoActivity2(String s){
        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("username",s);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
