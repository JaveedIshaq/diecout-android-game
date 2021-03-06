package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Field to hold the roll result text
    TextView rollResult;

    // Field to hold the score
    int score;

    // Field to hold random number generator
    Random rand;

    // Fields to hold the dice Value

    int die1;
    int die2;
    int die3;

    // field to hold the Score text

    TextView scoreText;

    // array list to holde all three dice

    ArrayList<Integer> dice;

    // Array list to hold all three images

    ArrayList<ImageView> diceImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              rollDice(view);
            }
        });

        // Set initial score
        score = 0;

        // Create greeting
        Toast.makeText(getApplicationContext(),"Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        // Link instances to widgets in the activity view
        rollResult = (TextView) findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);

        // Initialize the random number generator
        rand = new Random();

        //create ArrayList container for the dice values

        dice = new ArrayList<Integer>();
        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        diceImageView = new ArrayList<ImageView>();
        diceImageView.add(die1Image);
        diceImageView.add(die2Image);
        diceImageView.add(die3Image);


    }

    public void rollDice(View v) {


        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        // set dice values into the array list

        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++){
            String imageName = "die_" + dice.get(dieOfSet) + ".png";
            try{
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageView.get(dieOfSet).setImageDrawable(d);


            } catch (IOException e){
                e.printStackTrace();
            }
        }

        // build message with the result

        String msg;
        if (die1 == die2 && die1 == die3){
            int scoreDelta = die1 * 100;

            msg = "You rolled a triple " + die1 + " ! You Score " + scoreDelta + " points!";
            score += scoreDelta;
        } else if(die1 == die2 || die1 == die3 || die2 == die3) {
            msg = "You rolled double for 50 points!";
            score += 50;

        }else{
            msg ="You didn,t score this roll. Try agian";
        }

        rollResult.setText(msg);
        scoreText.setText("Score: " + score);
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
}
