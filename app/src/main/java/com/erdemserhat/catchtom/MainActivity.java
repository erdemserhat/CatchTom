package com.erdemserhat.catchtom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declaring the view components...
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12;
    TextView time, score, max_score;

    ArrayList<ImageView> image_list = new ArrayList<>();

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

        viewController(0);

        randomizer();


    }

    /*
    if the value of parameter is 0, then the whole the images will be in-visible
     and  if the value of parameter is 1, then the whole the images will be visible
     */

    public void viewController(int param) {
        if (param == 0) image_list.forEach((e) -> e.setVisibility(View.INVISIBLE));
        if (param == 1) image_list.forEach((e) -> e.setVisibility(View.VISIBLE));
    }


    /*
    this function generates a random number between 0-12 (not including 12)
    and a random index will be visible.
     */
    public void randomizer(){
        int random = (int) (Math.random()*12);
        image_list.get(random).setVisibility(View.VISIBLE);


    }
}

