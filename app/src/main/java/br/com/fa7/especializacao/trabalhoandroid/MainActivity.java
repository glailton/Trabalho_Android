package br.com.fa7.especializacao.trabalhoandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextBrowser;
    private Button btnBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBrowser = (EditText) findViewById(R.id.edit_text_browser);
        btnBrowser = (Button) findViewById(R.id.btn_browser);

        btnBrowser.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_browser:
                String address = editTextBrowser.getText().toString();
                if(address.equals("")){
                    address = "http://www.google.com";
                }else{
                    address = "http://".concat(address);
                }
                Uri uri = Uri.parse(address);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                editTextBrowser.setText("");
                break;
        }
    }
}
