package a00077546.comp3717.bcit.ca.labguessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText edittext;
    private TextView textview;
    private int randomNum;
    private int numGuesses = 0;
    private int totGuesses;
    private int gamesPlayed;
    private int surrendered;
    private double average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomNum   = generateRandomNumber(100);
        totGuesses  = 0;
        gamesPlayed = 0;
        surrendered = 0;
        average     = 0;
        edittext    = (EditText) findViewById(R.id.input);
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

    /**
     * Generate a random integer between 1 and n, inclusive.
     *
     * @param n
     * @return random number between 1 and n, inclusive
     */
    private int generateRandomNumber(int n) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(n) + 1;
    }

    /**
     *  Generates a new random number, resets the number of guesses to 0,
     *  and clears the textview message and edittext value.
     */
    private void reset() {
        randomNum   = generateRandomNumber(100);
        numGuesses  = 0;
        textview.setText("");
        edittext.setText("");
    }

    public void submitGuess(final View view) {

        final int input;
        final String inputStr;
        final Intent intent;
        Bundle bundle;

        textview    = (TextView) findViewById(R.id.HiLowMsg);
        inputStr    = edittext.getText().toString();
        intent      = new Intent(this, SecondActivity.class);
        bundle      = new Bundle();

        // check if nothing entered
        if (TextUtils.isEmpty(inputStr)) {

            Toast.makeText(this, "No number entered.  Try again.", Toast.LENGTH_LONG).show();

        } else {

            numGuesses++;
            input   = Integer.parseInt(inputStr);

            if (input == randomNum) {

                // update stats
                totGuesses += numGuesses;
                gamesPlayed++;
                average = (double) totGuesses / gamesPlayed;

                // pass number of guesses and stats via intent
                bundle.putInt("numGuesses", numGuesses);
                bundle.putInt("numGames", gamesPlayed);
                bundle.putDouble("average", average);
                bundle.putInt("surrendered", surrendered);
                bundle.putInt("random", -1);
                intent.putExtras(bundle);

                // get ready for the new game
                reset();

                startActivity(intent);

            } else if (input < randomNum) {
                textview.setText(input + " is too low");
                edittext.setText("");
            } else {
                textview.setText(input + " is too high");
                edittext.setText("");
            }
        }
    }

    public void surrender(final View view) {

        final Intent intent;
        final Bundle bundle;

        gamesPlayed++;
        textview    = (TextView) findViewById(R.id.HiLowMsg);
        intent      = new Intent(this, SecondActivity.class);
        bundle      = new Bundle();
        totGuesses += numGuesses;
        average     = (double) totGuesses / gamesPlayed;

        bundle.putInt("random", randomNum);
        bundle.putInt("numGuesses", numGuesses);
        bundle.putInt("numGames", gamesPlayed);
        bundle.putInt("surrendered", surrendered);
        bundle.putDouble("average", average);

        intent.putExtras(bundle);

        // get ready for the new game
        reset();

        startActivity(intent);
    }



}
