package com.erdemserhat.catchtom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declaring the view components...
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12;
    TextView time, score, max_score;
    int scoreFlag, timeFlag;
    Runnable runnable;
    Handler handler;

    ArrayList<ImageView> image_list = new ArrayList<>();
    MediaPlayer click, gameover, background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning the views.
        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image5 = findViewById(R.id.imageView5);
        image6 = findViewById(R.id.imageView6);
        image7 = findViewById(R.id.imageView7);
        image8 = findViewById(R.id.imageView8);
        image9 = findViewById(R.id.imageView9);
        image10 = findViewById(R.id.imageView10);
        image11 = findViewById(R.id.imageView11);
        image12 = findViewById(R.id.imageView12);
        time = findViewById(R.id.time_text);
        score = findViewById(R.id.score_text);
        max_score = findViewById(R.id.max_score_text);
        //Adding images to arrayList

        image_list.add(image1);
        image_list.add(image2);
        image_list.add(image3);
        image_list.add(image4);
        image_list.add(image5);
        image_list.add(image6);
        image_list.add(image7);
        image_list.add(image8);
        image_list.add(image9);
        image_list.add(image10);
        image_list.add(image11);
        image_list.add(image12);

        // Setting initial state of views
        viewController(0);

        // Randomly selecting an image to show


        // Setting click listeners for all image views


        timeFlag=30;
        time.setText("Remaining Time : " +timeFlag);
        startGame();
        setClickListeners(new ImageView[]{image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12});

        click = MediaPlayer.create(MainActivity.this, R.raw.click);
        gameover= MediaPlayer.create(MainActivity.this,R.raw.gameover);
        background= MediaPlayer.create(MainActivity.this, R.raw.background);
        background.start();

    }

    /**
     * Sets the visibility of all images in the image_list to either VISIBLE or INVISIBLE,
     * depending on the value of the parameter. If the parameter is 0, then all images will be
     * set to INVISIBLE. If the parameter is 1, then all images will be set to VISIBLE.
     *
     * @param visibility the visibility value to set (0 for INVISIBLE, 1 for VISIBLE)
     */

    public void viewController(int visibility) {
        if (visibility == 0) image_list.forEach((e) -> e.setVisibility(View.INVISIBLE));
        if (visibility == 1) image_list.forEach((e) -> e.setVisibility(View.VISIBLE));
    }


    /**
     * Randomly selects one image from a list of images and sets its visibility to VISIBLE.
     * The list of images is stored in the instance variable image_list.
     */
    public void randomizer() {
        int random = (int) (Math.random() * 12);
        image_list.get(random).setVisibility(View.VISIBLE);


    }

    /**
     * Sets a click listener for each image in the given array. When an image is clicked,
     * it will be made invisible and the score will be incremented by one. The score text
     * will also be updated with the new score. After the image is made invisible and the score
     * is updated, the randomizer() method will be called.
     *
     * @param image_list an array of ImageView objects to attach the click listener to
     */

    public void setClickListeners(ImageView[] image_list) {
        randomizer();
        for (int i = 0; i < image_list.length; i++) {
            final int index = i;
            image_list[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    image_list[index].setVisibility(View.INVISIBLE);
                    scoreFlag += (int) (10+Math.random()*10);
                    timeFlag++;
                    click.start();
                    score.setText("Score : " + scoreFlag);
                    randomizer();
                }
            });


        }


    }


    public void startGame(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(runnable,250);
                timeFlag--;
                time.setText("Remaining Time : " +timeFlag);
                if(timeFlag==0){
                    handler.removeCallbacks(runnable);
                    background.stop();
                    gameover.start();
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Game Over");
                    alert.setMessage("Congratulations ! Your Score : " +scoreFlag);
                    alert.setPositiveButton("Continue to Play", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //operations when replay button has been clicked.
                        }
                    });

                    alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //operation when exit button has been clicked
                           System.exit(0);
                        }
                    });
                    alert.show();


                }else{



                }
            }
        };
        handler.post(runnable);





    }
}
