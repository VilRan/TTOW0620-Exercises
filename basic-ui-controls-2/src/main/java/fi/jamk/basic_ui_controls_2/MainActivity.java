package fi.jamk.basic_ui_controls_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView loginView = (AutoCompleteTextView) findViewById(R.id.loginField);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line,
                new String[]{"Pasi", "Juha", "Kari", "Jouni", "Esa", "Hannu"}
        );
        loginView.setAdapter(aa);
    }

    public void onLoginButtonClicked(View view) {
        AutoCompleteTextView loginView = (AutoCompleteTextView) findViewById(R.id.loginField);
        String login = loginView.getText().toString();

        EditText passwordView = (EditText) findViewById(R.id.passwordField);
        String password = passwordView.getText().toString();

        Toast.makeText(getApplicationContext(), login + " " + password, Toast.LENGTH_SHORT).show();
    }
}
