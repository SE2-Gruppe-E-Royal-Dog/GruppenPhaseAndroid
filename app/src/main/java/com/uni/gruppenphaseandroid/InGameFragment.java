package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.CardUI;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.LeaveLobbyPayload;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.StartGamePayload;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class InGameFragment extends Fragment implements SensorEventListener, CardViewFragment.OnInputListener, SpecialCardDialogFragment.OnCardInputListener {
    private Client websocketClient;
    private final Gson gson = new Gson();
    private SensorManager sensorManager;
    private Sensor sensor;
    private ImageButton btnCardholder;
    private Cardtype selectedCardtype;
    private CardViewFragment cardholder;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_ingame, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        PlayingField playingField = new PlayingField(view);
        GameManager.getInstance().setPlayingField(playingField);
        GameManager.getInstance().setWebSocketClient(((MainActivity) getContext()).getClient());

        btnCardholder = playingField.getView().findViewById(R.id.btn_cardholderButton);



        view.findViewById(R.id.start_game_button).setOnClickListener(view12 -> {
            //deactivate start game button
            playingField.getView().findViewById(R.id.start_game_button).setVisibility(View.INVISIBLE);
            //activate cardholder
            btnCardholder.setVisibility(View.VISIBLE);

            websocketClient = ((MainActivity)getContext()).getClient();
            var lobbyId = ((MainActivity) getContext()).getLobbyId();
            var message = new Message();
            message.setType(MessageType.START_GAME);

            var payload = new StartGamePayload(lobbyId, 0, 0);
            message.setPayload(gson.toJson(payload));

            websocketClient.send(message);


        });

        view.findViewById(R.id.btn_accusation).setOnClickListener(view1 -> {
                AccusationFragment accusation = new AccusationFragment();
                accusation.show(getFragmentManager(), "Anschuldigung");
                accusation.setTargetFragment(InGameFragment.this, 1);

        });


        btnCardholder.setOnClickListener(view1 ->  {
                cardholder = new CardViewFragment();
                cardholder.show(getFragmentManager(), "cardholder Dialog");
                cardholder.setTargetFragment(InGameFragment.this, 1);
        });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT); // Type_Light ist der int Wert 5
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();       //to get rid of dublicated menu items
        menuInflater.inflate(R.menu.menu_main, menu);
        menu.getItem(1).setVisible(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_leaveGame:{
                websocketClient = ((MainActivity)getContext()).getClient();
                var lobbyId = ((MainActivity) getContext()).getLobbyId();
                var playerId = ((MainActivity) getContext()).getPlayerId();
                var message = new Message();
                message.setType(MessageType.LEAVE_LOBBY);
                var payload = new LeaveLobbyPayload(lobbyId, playerId);

                message.setPayload(gson.toJson(payload));

                websocketClient.send(message);
                NavHostFragment.findNavController(InGameFragment.this)
                        .navigate(R.id.action_InGameFragment_to_FirstFragment);
                return true;
            }
            case R.id.action_howPlay: {
                HowToPlayFragment dialog = new HowToPlayFragment();
                dialog.show(getFragmentManager(), "NoticeDialogFragment");
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];

        if (x < 40 && !GameManager.getInstance().getHasMovedWormholes()) {
            Log.e("Code", "sensor_light");
            GameManager.getInstance().initiateMoveWormholes();

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    //method for Dialog Fragment - Card Holder
    @Override
    public void sendInputCardFragment(String input, String cheaterNote) {               //get's input from cardholder aka choosen card
        setCardViewImage(Integer.parseInt(input));

        //set's cheater note over cardholder
        if (!cheaterNote.equals("0")){
        TextView note = getActivity().findViewById(R.id.txt_cheater);
        note.setText(cheaterNote);
        note.setVisibility(View.VISIBLE);
        }

    }

    public void setCardViewImage (int imageID){                     //set's the cardholder image to the choosen card, so the player sees what card he has choosen
        btnCardholder.setVisibility(View.VISIBLE);
        if (imageID != -1){
            btnCardholder.setImageResource(imageID);
            selectedCardtype = CardUI.getInstance().idToCardType(imageID);
            GameManager.getInstance().setSelectedCard(new Card(CardUI.getInstance().idToCardType(imageID)));
            GameManager.getInstance().setSelectCardIndex(cardholder.getClickedCardIndex());
            checkCard(imageID);
        } else{
            btnCardholder.setImageResource(R.drawable.ic_card_cardholder);
            GameManager.getInstance().setSelectedCard(new Card(CardUI.getInstance().idToCardType(imageID)));
            GameManager.getInstance().setSelectCardIndex(cardholder.getClickedCardIndex());
            GameManager.getInstance().getCardManager().discardHandcard(cardholder.getClickedCardIndex());
            GameManager.getInstance().sendLastTurnServerMessage();


        }
    }

    public void checkCard (int imageID){                            //checks if choosen card is a special card and requires to set an effect/if the user is required to specify the value of the card
            if ((CardUI.getInstance().idToCardType(imageID) == Cardtype.EQUAL && GameManager.getInstance().getLastTurn() != null && checkIfSpecialNumberCardEffect(GameManager.getInstance().getLastTurn().getCardtype()))) {
                //checks if EQUAL CARD + special card
                    new SpecialCardDialogFragment(GameManager.getInstance().getLastTurn().getCardtype()).show(getChildFragmentManager(), "specialcarddialog für =");
                }else {
                    if (CardUI.getInstance().idToCardType(imageID) != Cardtype.EQUAL && checkIfSpecialNumberCardEffect(CardUI.getInstance().idToCardType(imageID))) {
                    //if not EQUAL, open normal dialog
                    new SpecialCardDialogFragment(selectedCardtype).show(getChildFragmentManager(), "specialcarddialog");

                } else {
                    GameManager.getInstance().setCurrentEffect(-1);
                    GameManager.getInstance().cardGotPlayed(new Card(selectedCardtype));
            }
        }
    }

    public boolean checkIfSpecialNumberCardEffect(Cardtype cardtype){
        return cardtype == Cardtype.ONEORELEVEN_START || cardtype == Cardtype.FOUR_PLUSMINUS || cardtype == Cardtype.ONETOSEVEN || cardtype == Cardtype.THIRTEEN_START;

    }

    //methods for selecting special cards
    @Override
    public void sendInputSpecialCardFragment(String input) {

        Log.d("selectedSpecialCard", input);

    }

}
