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

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
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
    public TextView tv_seekbar;
    public CardViewFragment.OnInputListener mOnInputListener;



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
         Button ok = view.findViewById(R.id.btn_playOneToSeven);
         ok.setVisibility(View.INVISIBLE);
         SeekBar optionOneToSeven = view.findViewById(R.id.sb_selectOneToSeven);
         optionOne.setVisibility(View.INVISIBLE);
         Button chancel = view.findViewById(R.id.btn_chancel);
         tv_seekbar = view.findViewById(R.id.tv_seekbar);

             textView.setText("Choose the value of your card:");

             switch (cardtype) {
                 case ONETOSEVEN:

                     optionOneToSeven.setVisibility(View.VISIBLE);
                     ok.setText("OK");
                     ok.setVisibility(View.VISIBLE);
                     optionOneToSeven.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                         @Override
                         public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                             selectedCardEffect = value;


                             int val = (value * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                             tv_seekbar.setVisibility(View.VISIBLE);
                             tv_seekbar.setText("" + (value + 1));
                             int valHelp = seekBar.getThumbOffset()/2;
                             tv_seekbar.setX(seekBar.getX() + val + valHelp);
                             //textView.setY(100); just added a value set this properly using screen with height aspect ratio , if you do not set it by default it will be there below seek bar


                         }

                         @Override
                         public void onStartTrackingTouch(SeekBar seekBar) {

                         }

                         @Override
                         public void onStopTrackingTouch(SeekBar seekBar) {

                         }
                     });

                     ok.setOnClickListener(view1 -> {
                         GameManager.getInstance().setCurrentEffect(selectedCardEffect);
                         GameManager.getInstance().cardGotPlayed(new Card(Cardtype.ONETOSEVEN));
                         getDialog().dismiss();

                     });

                     break;
                 case ONEORELEVEN_START:
                     optionOne.setText("ONE");
                     optionTwo.setText("ELEVEN");
                     ok.setText("Start");

                     optionOne.setVisibility(View.VISIBLE);
                     optionTwo.setVisibility(View.VISIBLE);
                     ok.setVisibility(View.VISIBLE);

                     optionOne.setOnClickListener(view12 -> {
                         GameManager.getInstance().setCurrentEffect(1);
                         GameManager.getInstance().cardGotPlayed(new Card(Cardtype.ONEORELEVEN_START));
                         getDialog().dismiss();
                     });
                     optionTwo.setOnClickListener(view13 -> {
                         GameManager.getInstance().setCurrentEffect(2);
                         GameManager.getInstance().cardGotPlayed(new Card(Cardtype.ONEORELEVEN_START));
                         getDialog().dismiss();
                     });
                     ok.setOnClickListener(view14 -> {
                         GameManager.getInstance().setCurrentEffect(0);
                         GameManager.getInstance().cardGotPlayed(new Card(Cardtype.ONEORELEVEN_START));
                         getDialog().dismiss();
                     });

                     break;
                 case FOUR_PLUSMINUS:
                     optionOne.setText("Four PLUS");
                     optionTwo.setText("Four MINUS");

                     optionOne.setVisibility(View.VISIBLE);
                     optionTwo.setVisibility(View.VISIBLE);

                     optionOne.setOnClickListener(view15 -> {
                         GameManager.getInstance().setCurrentEffect(1);
                         GameManager.getInstance().cardGotPlayed(new Card(Cardtype.FOUR_PLUSMINUS));
                         getDialog().dismiss();
                     });
                     optionTwo.setOnClickListener(view16 -> {

                         GameManager.getInstance().setCurrentEffect(0);
                         GameManager.getInstance().cardGotPlayed(new Card(Cardtype.FOUR_PLUSMINUS));
                         getDialog().dismiss();
                     });
                     break;
             }

             chancel.setOnClickListener(view17 -> getDialog().dismiss());

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
