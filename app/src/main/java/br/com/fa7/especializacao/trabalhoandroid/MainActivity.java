package br.com.fa7.especializacao.trabalhoandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.fa7.especializacao.model.Photo;
import br.com.fa7.especializacao.util.Util;

public class MainActivity extends Activity implements View.OnClickListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Photo photo;



    private EditText editTextBrowser;
    private EditText editTextCall;
    private ImageView imageViewPhoto;
    private Button btnBrowser;
    private Button btnCamera;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo = new Photo();

        editTextBrowser = (EditText) findViewById(R.id.edit_text_browser);
        editTextCall = (EditText) findViewById(R.id.edit_text_call);
        imageViewPhoto = (ImageView) findViewById(R.id.image_view_camera);
        btnBrowser = (Button) findViewById(R.id.btn_browser);
        btnCamera = (Button) findViewById(R.id.btn_camera);
        btnCall = (Button) findViewById(R.id.btn_call);

        btnBrowser.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnCall.setOnClickListener(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_IMAGE_CAPTURE:
                if (data != null){
                    Bundle bundle = data.getExtras();
                    if(bundle != null){
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        imageViewPhoto.setImageBitmap(bitmap);
                        photo.setPhoto(Util.convertBitmapToByteArray(bitmap, 70));
                    }
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_browser:
                String address = editTextBrowser.getText().toString();
                if(address.equals("")){
                    address = "www.google.com";
                }
                Uri uri = Uri.parse("http://" + address);
                Intent intentBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentBrowser);
                editTextBrowser.setText("");
                break;
            case R.id.btn_camera:
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCamera, REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.btn_call:

                String number = editTextCall.getText().toString();
                if(number.equals("")){
                    number = "190";
                }
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                Uri uriCall = Uri.parse("tel:" + number);
                intentCall.setData(uriCall);
                startActivity(intentCall);
                editTextCall.setText("");
                break;
        }
    }
}
