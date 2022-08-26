package activities;

import static lib.Utils.showInfoDialog;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.soundboard.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private MediaPlayer[] mSounds;
    private static final int sNO_PAUSED = -1;
    private int mCurrentPaused = sNO_PAUSED;
    private int mCurrentPlaying = sNO_PAUSED;

    @Override
    protected void onPause() {
        super.onPause();
        setmCurrentPlayingToCurrent();

    }

    private void setmCurrentPlayingToCurrent() {
        for (int i = 0; i < mSounds.length; i++) {
            if (mSounds[i].isPlaying()) {
                mCurrentPlaying = i;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeIfWasPlaying();
    }

    private void resumeIfWasPlaying() {
        if (mCurrentPlaying != sNO_PAUSED) {
            mSounds[mCurrentPlaying].start();
            mSounds[mCurrentPlaying].setLooping(true);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Currently Paused", mCurrentPaused);
        outState.putInt("Currently Playing", mCurrentPlaying);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentPaused = savedInstanceState.getInt("Currently Paused", sNO_PAUSED);
        mCurrentPlaying = savedInstanceState.getInt("Currently Playing", sNO_PAUSED);
        resumeIfWasPlaying();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpToolbar();

        setUpFABPause();

        setUpFABPlay();

        setSounds();
    }

    private void setSounds() {
        mSounds = new MediaPlayer[]{MediaPlayer.create(this, R.raw.forest),
                MediaPlayer.create(this, R.raw.rain),
                MediaPlayer.create(this, R.raw.cricket),
                MediaPlayer.create(this, R.raw.white)};

        Button forestButton = (Button) findViewById(R.id.button1);
        Button rainButton = (Button) findViewById(R.id.button2);
        Button cricketButton = (Button) findViewById(R.id.button3);
        Button whiteButton = (Button) findViewById(R.id.button4);


        forestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSounds[0].isPlaying() || mSounds[1].isPlaying() || mSounds[2].isPlaying() || mSounds[3].isPlaying()) {
                    mSounds[0].setLooping(true);
                    mSounds[1].stop();
                    mSounds[2].stop();
                    mSounds[3].stop();
                    mSounds[0].reset();
                    mSounds[0] = MediaPlayer.create(getApplicationContext(), R.raw.forest);
                }
                mCurrentPaused = sNO_PAUSED;
                mSounds[0].start();
            }
        });

        rainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSounds[1].setLooping(true);
                if (mSounds[0].isPlaying() || mSounds[1].isPlaying() || mSounds[2].isPlaying() || mSounds[3].isPlaying()) {
                    mSounds[0].stop();
                    mSounds[2].stop();
                    mSounds[3].stop();
                    mSounds[1].reset();
                    mSounds[1] = MediaPlayer.create(getApplicationContext(), R.raw.rain);
                }
                mCurrentPaused = sNO_PAUSED;
                mSounds[1].start();
            }
        });

        cricketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSounds[2].setLooping(true);
                if (mSounds[0].isPlaying() || mSounds[1].isPlaying() || mSounds[2].isPlaying() || mSounds[3].isPlaying()) {
                    mSounds[0].stop();
                    mSounds[1].stop();
                    mSounds[3].stop();
                    mSounds[2].reset();
                    mSounds[2] = MediaPlayer.create(getApplicationContext(), R.raw.cricket);
                }
                mCurrentPaused = sNO_PAUSED;
                mSounds[2].start();

            }
        });

        whiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSounds[3].setLooping(true);
                if (mSounds[0].isPlaying() || mSounds[1].isPlaying() || mSounds[2].isPlaying() || mSounds[3].isPlaying()) {
                    mSounds[0].stop();
                    mSounds[1].stop();
                    mSounds[2].stop();
                    mSounds[3].reset();
                    mSounds[3] = MediaPlayer.create(getApplicationContext(), R.raw.white);
                }
                mCurrentPaused = sNO_PAUSED;
                mSounds[3].start();

            }
        });


    }


    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpFABPause() {
        FloatingActionButton fab = findViewById(R.id.fab_pause);
        fab.setOnClickListener((view) -> {
            handleFABPauseClick();
        });
    }

    private void setUpFABPlay() {
        FloatingActionButton fab = findViewById(R.id.fab_play);
        fab.setOnClickListener((view) -> {
            handleFABPlayClick();
        });
    }

    private void handleFABPauseClick() {
        for (int i = 0; i < mSounds.length; i++) {
            if (mSounds[i].isPlaying()) {
                mSounds[i].pause();
                mCurrentPaused = i;
            }
        }
    }

    private void handleFABPlayClick() {
        if (mCurrentPaused != -1) {
            mSounds[mCurrentPaused].start();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            showSettings();
            return true;
        } else if (id == R.id.action_about) {
            showAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAbout() {
        showInfoDialog(MainActivity.this, "About Soft Sound",
                "Greetings from the developers.\nRelax, study, or work to your choice of soft background sounds.\n" +
                        "\nCreated by Danny and Eli");
    }

    private void showSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

}
