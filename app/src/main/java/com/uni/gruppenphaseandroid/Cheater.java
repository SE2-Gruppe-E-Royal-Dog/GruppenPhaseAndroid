package com.uni.gruppenphaseandroid;



public class Cheater {

    private int roundIndex;
    private int currentRoundIndex;
    private String playerID;
    private boolean cheatingAllowed;


    public Cheater() {
    }

    public Cheater(String playerID, int roundIndex){
        this.playerID = playerID;                             //bis jetzt nur am Server --> von gameManager?
        this.roundIndex = roundIndex;                         //merkt sich die Runde, in der geschummelt wurde
        setCheatingAllowed(false);                            //constructor --> erstes mal schummeln --> daher für nächsten runden false
    }


    /**
     * checks if cheating is permitted --> the player hasn't cheated within 5 rounds
     */
    public boolean cheatingAllowed(){
        if ((getCurrentRoundIndex()-getRoundIndex()) >= 5)
            setCheatingAllowed(true);

        return isCheatingAllowed();
    }




    //getter setter
    public int getRoundIndex() {
        return roundIndex;
    }

    public void setRoundIndex(int roundIndex) {
        this.roundIndex = roundIndex;
    }

    public int getCurrentRoundIndex() {
        return currentRoundIndex;
    }

    public void setCurrentRoundIndex(int currentRoundIndex) {
        this.currentRoundIndex = currentRoundIndex;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public boolean isCheatingAllowed() {
        return cheatingAllowed;
    }

    public void setCheatingAllowed(boolean cheatingAllowed) {
        this.cheatingAllowed = cheatingAllowed;
    }
}
