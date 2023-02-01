package space.sakibnm.fragments_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyCustomFragment.IfromFragmentToActivity, View.OnClickListener {

    final String TAG = "demo";
    private Button buttonFragment1, buttonFragment2, buttonFragment3;
    private TextView textviewFromFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Fragments");

        buttonFragment1 = findViewById(R.id.buttonFragment1);
        buttonFragment2 = findViewById(R.id.buttonFragment2);
        buttonFragment3 = findViewById(R.id.buttonFragment3);

//        When we are using the same fragment and changing the colors....
//        By default, we start with the First Fragment color....
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerFragment, MyCustomFragment
                        .newInstance("First Fragment", Color.YELLOW),"fragment")
                .commit();

        buttonFragment1.setOnClickListener(this);
        buttonFragment2.setOnClickListener(this);
        buttonFragment3.setOnClickListener(this);
//        See below for the onClick() implementation....


        /* Code for creating three different fragments */
//        findViewById(R.id.buttonFragment1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.containerFragment, new FirstFragment(), "fragment")
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//
//        findViewById(R.id.buttonFragment2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.containerFragment, new SecondFragment(), "fragment")
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//
//        findViewById(R.id.buttonFragment3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.containerFragment, new ThirdFragment(), "fragment")
//                        .addToBackStack(null)
//                        .commit();
//
//
//            }
//        });


    }
    //    Implementing button OnClicks.....
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonFragment1:
//                Screen 1...
                MyCustomFragment fragment1 = (MyCustomFragment) getSupportFragmentManager()
                        .findFragmentByTag("fragment");
                fragment1.updateValues("First Fragment", Color.YELLOW);
                break;

            case R.id.buttonFragment2:
//                Screen 2...
                MyCustomFragment fragment2 = (MyCustomFragment) getSupportFragmentManager()
                        .findFragmentByTag("fragment");
                fragment2.updateValues("Second Fragment", Color.GREEN);
                break;
            case R.id.buttonFragment3:
//                Screen 2...
                MyCustomFragment fragment3 = (MyCustomFragment) getSupportFragmentManager()
                        .findFragmentByTag("fragment");
                fragment3.updateValues("Third Fragment", Color.BLUE);
                break;
        }
    }


//  Implementing the methods for the sendData interface.....
    @Override
    public void fromFragment(boolean isOn, String message) {
        textviewFromFragment = findViewById(R.id.textViewDataFromFragment);
        String status = isOn?"Switch is ON!":"Switch is Off";
        textviewFromFragment.setText(status+". "+message);
    }


}