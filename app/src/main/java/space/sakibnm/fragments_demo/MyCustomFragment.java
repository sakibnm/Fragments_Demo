package space.sakibnm.fragments_demo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MyCustomFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TEXT = "textFragment";
    private static final String ARG_COLOR = "colorFragment";
    private TextView textView;
    private String textFragment;
    private int colorFragment;

    //    Interface To Activity declaration....
    IfromFragmentToActivity sendData;

    public MyCustomFragment() {
        // Required empty public constructor
    }


    // Factory instance creator.....
    public static MyCustomFragment newInstance(String text, int color) {
        MyCustomFragment fragment = new MyCustomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            textFragment = getArguments().getString(ARG_TEXT);
            colorFragment = getArguments().getInt(ARG_COLOR);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_custom, container, false);
        textView = view.findViewById(R.id.textviewMyCustom);
        textView.setText(textFragment);
        View background  = view.findViewById(R.id.containerMyCustom);
        background.setBackgroundColor(colorFragment);

        Switch onOff = view.findViewById(R.id.switchFragment);
        EditText editTextFragment = view.findViewById(R.id.editTextFragment);
        Button buttonFragment = view.findViewById(R.id.buttonSendDataToActivity);

        final boolean[] stateOfTheSwitch = {false};
        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true) stateOfTheSwitch[0] = true;
                else stateOfTheSwitch[0] = false;
            }
        });

        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextFragment.getText().toString();
                sendData.fromFragment(stateOfTheSwitch[0],message);
            }
        });

        return view;
    }

    public void updateValues(String text, int color){
        this.textFragment = text;
        this.colorFragment = color;
        textView.setText(textFragment);
        getView().findViewById(R.id.containerMyCustom).setBackgroundColor(colorFragment);
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        Initializing the interface......
        if (context instanceof IfromFragmentToActivity){
            sendData = (IfromFragmentToActivity) context;
        }else{
            throw new RuntimeException(context.toString()+ "must implement IfromFragmentToActivity");
        }

    }
    public interface IfromFragmentToActivity{
        void fromFragment(boolean isOn, String message);
    }
}