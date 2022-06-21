package com.uni.gruppenphaseandroid.manager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import com.uni.gruppenphaseandroid.MenuFragment;
import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.CardUI;

public class VisualEffectsManagerImpl extends VisualEffectsManager {

    ImageView stackImage;
    ImageButton cardHolder;
    TextView cheaterNote;
    Context context;
    //other attributes...

    public VisualEffectsManagerImpl(ImageView stackImage, Context context, ImageButton cardHolder, TextView cheaterNote) {
        this.stackImage = stackImage;
        this.context = context;
        this.cardHolder = cardHolder;
        this.cheaterNote = cheaterNote;
    }

        @Override
        protected void setInitialStackImage () {
            stackImage.setImageResource(R.drawable.ic_card_ablagestapel);
        }

        @Override
        protected void setStackImage () {
                LastTurn lastTurn = GameManager.getInstance().getLastTurn();
                if (lastTurn.getCardtype() == null) {
                    throw new IllegalArgumentException("No Cardtype has been set");
                }

                int imageId = GameManager.getInstance().getCardUI().findImageView(new Card(lastTurn.getCardtype()));
                stackImage.setImageResource(imageId);

        }

        public void setStackImageAfterMyMove (Card card){
            stackImage.setImageResource(GameManager.getInstance().getCardUI().findImageView(card));
        }

        @Override
        protected void showIllegalMoveMessage () {
            showToast("This is not a legal move. Choose a new card.");
        }

        @Override
        protected void showWinningScreen () {
            NavHostFragment.findNavController(getInGameFragment())
                    .navigate(R.id.end_of_game);
        }

        @Override
        protected void showCanNotAcccusePlayerMessage () {
            showToast("Cannot accuse this player since they don't have any figure on board.");
        }

        @Override
        protected void setCardHolderUI () {
                        cardHolder.setImageResource(R.drawable.ic_card_cardholder);
            cheaterNote.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void showNoPossibleMoveMessage () {
            showToast("No possible move with this hand. Please discard 1 card.");
        }

        @Override
        protected void showNextTurnMessage (String turnPlayerName){
            showToast("It's " + turnPlayerName + "'s turn now!");
        }

        private void showToast (String message){
            var toast = Toast.makeText(context,
                    message,
                    Toast.LENGTH_LONG);

            toast.show();
        }

}
