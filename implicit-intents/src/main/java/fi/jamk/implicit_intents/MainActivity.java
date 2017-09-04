package fi.jamk.implicit_intents;

import android.content.Intent;
import android.net.Uri;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMap(View view) {
        EditText latitudeView = (EditText) findViewById(R.id.latitude);
        EditText longitudeView = (EditText) findViewById(R.id.longitude);
        double latitude = Double.parseDouble(latitudeView.getText().toString());
        double longitude = Double.parseDouble(longitudeView.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + latitude + "," + longitude));
        startActivity(intent);
    }
}
