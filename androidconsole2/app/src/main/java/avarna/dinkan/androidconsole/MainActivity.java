package avarna.dinkan.androidconsole;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private String ping(String url){

        String str ="";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("/system/bin/ping -c 1 "+url).getInputStream()));
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while(true){
                int i=reader.read(buffer);
                if(i<=0){
                    break;
                }
                output.append(buffer,0,i);

            }
            reader.close();
            str=output.toString();
            ((TextView) findViewById(R.id.output)).setText(str);
        } catch (IOException e) {
            ((TextView) findViewById (R.id.output)).setText("Caught IOException while pinging");
            e.printStackTrace();
        }
        return str;

    }



    private String all(String url){

        String str ="";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(url).getInputStream()));
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while(true){
                int i=reader.read(buffer);
                if(i<=0){
                    break;
                }
                output.append(buffer,0,i);

            }
            reader.close();
            str=output.toString();
            ((TextView) findViewById(R.id.output)).setText(str);
        } catch (IOException e) {
            ((TextView) findViewById (R.id.output)).setText("Caught IOException while pinging");
            e.printStackTrace();
        }
        return str;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });







        Button bt1 = (Button) findViewById(R.id.ping);
        Button bt2 = (Button) findViewById(R.id.all);

        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                MainActivity.this.ping(((EditText) MainActivity.this.findViewById(R.id.textbox)).getText().toString());

            }

        });

        bt2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.this.all(((EditText) MainActivity.this.findViewById(R.id.textbox)).getText().toString());
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
