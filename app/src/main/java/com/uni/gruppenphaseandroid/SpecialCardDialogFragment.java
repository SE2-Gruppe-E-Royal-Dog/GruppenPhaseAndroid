package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;

public class SpecialCardDialogFragment extends DialogFragment {

    //options for special cards | 4+- | 1-7 | 1 or 11

    private TextView textView;          //Display the titel of the card (one or eleven||4 +-||1-7) and asks to specificate the value
    private Button optionOne;
    private Button optionTwo;
    private Button ok;
    private Button chancel;
    private SeekBar optionOneTwoSeven;
    private Cardtype cardtype;
    private int selectedCardEffect;
    public CardViewFragment.OnInputListener mOnInputListener;

    //Values for seekbar //TODO does it work like this
    public static final String VAL1 = "1";
    public static final String VAL2 = "2";
    public static final String VAL3 = "3";
    public static final String VAL4 = "4";
    public static final String VAL5 = "5";
    public static final String VAL6 = "6";
    public static final String VAL7 = "7";

    public static enum Suit{VAL1,VAL2,VAL3,VAL4,VAL5,VAL6,VAL7,}

    public interface OnCardInputListener{
        void sendInputSpecialCardFragment (String input);
    }

    public OnCardInputListener specialCardInput;

    public SpecialCardDialogFragment(Cardtype cardtype) {
        this.cardtype = cardtype;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_card_dialog, container, false);

         TextView textView = view.findViewById(R.id.txt_userInfo);
         Button optionOne = view.findViewById(R.id.btn_optionOne);
         optionOne.setVisibility(View.INVISIBLE);
         Button optionTwo = view.findViewById(R.id.btn_optionTwo);
         optionTwo.setVisibility(View.INVISIBLE);
         Button optionThree = view.findViewById(R.id.btn_optionThree);
         optionThree.setVisibility(View.INVISIBLE);
         Button ok = view.findViewById(R.id.btn_playOneToSeven);
         ok.setVisibility(View.INVISIBLE);
         SeekBar optionOneToSeven = view.findViewById(R.id.sb_selectOneToSeven);
         optionOne.setVisibility(View.INVISIBLE);
         Button chancel = view.findViewById(R.id.btn_chancel);

        textView.setText("Choose the value of your card:");

        switch (cardtype){
            case ONETOSEVEN:

                optionOneToSeven.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                optionOneToSeven.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                        selectedCardEffect = value;
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameManager.getInstance().setCurrentEffect(selectedCardEffect);
                        getDialog().dismiss();

                    }
                });

                break;
            case ONEORELEVEN_START:
                optionOne.setText("ONE");
                optionTwo.setText("ELEVEN");
                optionThree.setText ("Start");

                optionOne.setVisibility(View.VISIBLE);
                optionTwo.setVisibility(View.VISIBLE);
                optionThree.setVisibility(View.VISIBLE);

                optionOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameManager.getInstance().setCurrentEffect(1);
                        getDialog().dismiss();
                    }
                });
                optionTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameManager.getInstance().setCurrentEffect(2);
                        getDialog().dismiss();
                    }
                });
                optionThree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameManager.getInstance().setCurrentEffect(0);
                        getDialog().dismiss();
                    }
                });

                break;
            case FOUR_PLUSMINUS:
                optionOne.setText("Four PLUS");
                optionTwo.setText("Four MINUS");

                optionOne.setVisibility(View.VISIBLE);
                optionTwo.setVisibility(View.VISIBLE);

                optionOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameManager.getInstance().setCurrentEffect(1);
                        getDialog().dismiss();
                    }
                });
                optionTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        GameManager.getInstance().setCurrentEffect(0);
                        getDialog().dismiss();
                    }
                });
                break;
        }

        chancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {            //Methode für DialogFragement communication
        super.onAttach(context);
        try{
            specialCardInput = (SpecialCardDialogFragment.OnCardInputListener) getTargetFragment();
        }catch (ClassCastException e){

            Log.e("SpecialCardDialog", "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
