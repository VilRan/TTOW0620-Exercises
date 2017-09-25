package xyz.placeholder.picasso;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        String url = "https://i.imgur.com/W6ywYUF.jpg";
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        Picasso
            .with(context)
            .load(url)
            .resize(1080, 1920) // Image is too large to not crash unless we resize it
            .centerCrop()
            .into(imageView);
    }
}
