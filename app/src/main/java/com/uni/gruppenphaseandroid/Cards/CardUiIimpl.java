package com.uni.gruppenphaseandroid.Cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;

import com.uni.gruppenphaseandroid.R;

public class CardUiIimpl implements CardUI {

    private ImageView cardImageView;
    private static LinearLayout linearLayout;
    private Drawable drawable;
    private View view;
    private Context context;


    public CardUiIimpl(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public CardUiIimpl() {
    }

    @Override
    public void createCardUI(){
        linearLayout = view.findViewById(R.id.linlayout_cardHolder);
        Resources res = context.getResources();
        drawable = ResourcesCompat.getDrawable(res, R.drawable.ic_card_2, null);
        cardImageView = new ImageView(view.getContext());
        cardImageView.setBackgroundResource(R.drawable.ic_card_2);  //drawable);
        linearLayout.addView(cardImageView);

    }

    public void addCardToHand(Card card) {
    }
}


