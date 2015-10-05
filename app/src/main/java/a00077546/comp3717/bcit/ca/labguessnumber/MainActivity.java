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

    private EditText edit;
    private int randomNum;
    private int gamesPlayed = 0;
    private int numGuesses  = 0;
    private double average  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamesPlayed++;
        randomNum = generateRandomNumber(100);
        edit = (EditText) findViewById(R.id.input);
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

    public void submitGuess(final View view) {

        final int input;
        final String inputStr;
        final Intent intent;
        final TextView textview;
        Bundle bundle;

        textview    = (TextView) findViewById(R.id.HiLow);
        inputStr    = edit.getText().toString();
        intent      = new Intent(this, SecondActivity.class);
        bundle      = new Bundle();

        numGuesses++;

        // check if nothing entered
        if (TextUtils.isEmpty(inputStr)) {

            Toast.makeText(this, "No number entered.  Try again.", Toast.LENGTH_LONG).show();

        } else {

            input   = Integer.parseInt(inputStr);

            if (input == randomNum) {

                average = average + numGuesses / gamesPlayed;

                // pass number of guesses and stats via intent
                bundle.putInt("numGuesses", numGuesses);
                bundle.putInt("numGames", gamesPlayed);
                bundle.putDouble("average", average);
                bundle.putInt("random", -1);
                intent.putExtras(bundle);

                // generate a new random number, clear the number of guesses
                randomNum   = generateRandomNumber(100);
                numGuesses  = 0;
                textview.setText("");
                edit.setText("");

                startActivity(intent);

            } else if (input < randomNum) {
                textview.setText(input + " is too low");
                edit.setText("");
            } else {
                textview.setText(input + " is too high");
                edit.setText("");
            }
        }
    }

    public void surrender(final View view) {

        final Intent intent;
        final Bundle bundle;
        final TextView textview;

        textview    = (TextView) findViewById(R.id.HiLow);
        intent      = new Intent(this, SecondActivity.class);
        bundle      = new Bundle();

        System.out.println("----------------- surrender");
        System.out.println("randomNum: " + randomNum);
        bundle.putInt("random", randomNum);
        intent.putExtras(bundle);

        // generate a new random number, clear the number of guesses
        randomNum   = generateRandomNumber(100);
        numGuesses  = 0;
        textview.setText("");
        edit.setText("");

        startActivity(intent);
    }

}
