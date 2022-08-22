package activities;
import static lib.Utils.showInfoDialog;

import android.content.Intent;
import android.os.Bundle;

import com.example.soundboard.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpToolbar();

        setUpFAB1();

        setUpFAB2();

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
