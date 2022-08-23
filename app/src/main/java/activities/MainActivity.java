package activities;
import static lib.Utils.showInfoDialog;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.soundboard.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpToolbar();

        setUpFAB1();

        setUpFAB2();

        final MediaPlayer[] sounds = {MediaPlayer.create(this, R.raw.forest),
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
                sounds[0].setLooping(true);
                if(sounds[0].isPlaying() || sounds[1].isPlaying()|| sounds[2].isPlaying() || sounds[3].isPlaying()) {
                    sounds[1].stop();
                    sounds[2].stop();
                    sounds[3].stop();
                    sounds[0].reset();
                    sounds[0].start();
                    sounds[0] = MediaPlayer.create(getApplicationContext(), R.raw.forest);
                }
                sounds[0].start();
            }
        });

        rainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounds[1].setLooping(true);
                if (sounds[0].isPlaying() || sounds[1].isPlaying()|| sounds[2].isPlaying() || sounds[3].isPlaying()) {
                    sounds[0].stop();
                    sounds[2].stop();
                    sounds[3].stop();
                    sounds[1].reset();
                    sounds[1].start();
                    sounds[1] = MediaPlayer.create(getApplicationContext(), R.raw.rain);
                }
                sounds[1].start();
            }
        });

        cricketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounds[2].setLooping(true);
                while (sounds[0].isPlaying() || sounds[1].isPlaying()|| sounds[2].isPlaying() || sounds[3].isPlaying()) {
                    sounds[0].stop();
                    sounds[1].stop();
                    sounds[3].stop();
                    sounds[2].reset();
                    sounds[2].start();
                    sounds[2] = MediaPlayer.create(getApplicationContext(), R.raw.cricket);
                }
                sounds[2].start();

            }
        });

        whiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounds[3].setLooping(true);
                while (sounds[0].isPlaying() || sounds[1].isPlaying()|| sounds[2].isPlaying() || sounds[3].isPlaying()) {
                    sounds[0].stop();
                    sounds[1].stop();
                    sounds[2].stop();
                    sounds[3].reset();
                    sounds[3].start();
                    sounds[3] = MediaPlayer.create(getApplicationContext(), R.raw.cricket);
                }
                sounds[3].start();

            }
        });
//        setupFields();

    }



    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpFAB1() {
        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener((view) -> {handleFAB1Click();});
    }

    private void setUpFAB2() {
        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener((view) -> {handleFAB2Click();});
    }

    private void handleFAB1Click() {
        //TODO what to do when user clicks on fab

    }

    private void handleFAB2Click() {
        //TODO what to do when user clicks on fab

    }

//    private void setupFields() {
//        View layoutMain = findViewById(R.id.main_activity);
//    }

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
            showSettings();
            return true;
        }
        else if (id == R.id.action_about) {
            showAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showAbout() {
//      dismissSnackBarIfShown();
        showInfoDialog(MainActivity.this, "About Sound Board",
                "Greetings from the developers.\nRelax, study, or work to your choice of background sounds.\n" +
                        "\nCreated by Danny and Eli");
    }
    private void showSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

}
