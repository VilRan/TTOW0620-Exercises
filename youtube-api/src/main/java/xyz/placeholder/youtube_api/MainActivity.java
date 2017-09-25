package xyz.placeholder.youtube_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeFailureRecoveryActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(
            YouTubePlayer.Provider provider,
            YouTubePlayer player,
            boolean wasRestored
    ) {
        if (!wasRestored) {
            player.cueVideo("kPRA0W1kECg");
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

}
