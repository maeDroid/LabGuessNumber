package a00077546.comp3717.bcit.ca.labguessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent;
        final int numGuesses;
        final int numGames;
        final int random;
        final double average;
        final TextView textview1;
        final TextView textview2;
        Bundle data;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intent      = getIntent();
        data        = intent.getExtras();
        random      = data.getInt("random");
        numGuesses  = data.getInt("numGuesses");
        numGames    = data.getInt("numGames");
        average     = data.getDouble("average");

        textview1   = (TextView)findViewById(R.id.outputNumGuesses);
        textview2   = (TextView) findViewById(R.id.statsData);

        if (random != -1) {
            textview1.setText("The correct number was " + random);
        } else {
            if (numGuesses == 1) {
                textview1.setText("You took only " + numGuesses + " guess!");
            } else {
                textview1.setText("You took " + numGuesses + " guesses");
            }

            //textview2.setText("Number of games played: " + numGames + "\nAverage number of guesses: " + average);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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

    /**
     *  Return to the previous activity when the Play Again button is clicked.
     */
    public void playNewGame(final View view) {
        this.finish();
    }
}
