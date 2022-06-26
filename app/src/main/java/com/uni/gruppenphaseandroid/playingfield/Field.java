package com.uni.gruppenphaseandroid.playingfield;

public class Field {

    private Field nextField;
    private Field previousField;
   protected int fieldID;
    private Figure currentFigure;
    private FieldUI fieldUIobject;


    public Field(FieldUI fieldUIobject, Field nextField, Field previousField, Figure currentFigure, int fieldID) {
        this.fieldUIobject = fieldUIobject;
        this.nextField = nextField;
        this.previousField = previousField;
        this.currentFigure = currentFigure;
        this.fieldID = fieldID;

    }

    public Field() {

    }

    protected void triggerSpecialFieldEffect() {
        //default implementation ignored
    }

    /**
     * Gibt das Feld zurück was distance Felder von dem jetzigen Feld entfernt ist.
     * Color muss mitgegeben werden damit man weiß welche Verzweigungen (normal vs Zielfelder) genommen werden müssen.
     * @param distance
     * @param color
     * @return
     */
    public Field getFieldAtDistance(int distance, Color color) {
        return getFieldAtDistanceRecursive(distance, color, this);
    }

    protected Field getFieldAtDistanceRecursive(int remainingDistance, Color color, Field originField) {
        if (remainingDistance == 0) {
            return this;
        } else if (remainingDistance < 0) {
            return getPreviousField().getFieldAtDistanceRecursive(remainingDistance + 1, color, originField);
        } else {//remainingFields > 0
            return getNextField().getFieldAtDistanceRecursive(remainingDistance - 1, color, originField);
        }
    }

    public Field getNextField() {
        return nextField;
    }

    public void setNextField(Field nextField) {
        this.nextField = nextField;
    }

    public Field getPreviousField() {
        return previousField;
    }

    public void setPreviousField(Field previousField) {
        this.previousField = previousField;
    }

    public FieldUI getFieldUIobject() {
        return fieldUIobject;
    }

    public void setFieldUIobject(FieldUI fieldUIobject) {
        this.fieldUIobject = fieldUIobject;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    /**
     * ja meine Lieben... pff das ist eine Methode... net so easy
     * es wird hier der Switch der Wurmlöcher gemacht, die Figuren werden getauscht,
     * die Felder werden getauscht, das UI wird getauscht und es wird noch geprüft
     * und damit das alles auch am Spielfeld dann gut funktioniert müssen wir
     * dann noch auf das vorherige und das nachfolgende Feld referenzieren
     * denn beim Tausch ist es auch wichtig, die Field ID zu berücksichtigen,
     * deshalb gibt es ganz unten noch den Dreieckstausch mit der Field ID
     * @param targetField
     */

    public void switchField(Field targetField) {

        Field currentNextField = getNextField();
        Field currentPreviousField = getPreviousField();
        Field targetNextField = targetField.getNextField();
        Field targetPreviousField = targetField.getPreviousField();

        targetField.setNextField(currentNextField);
        targetField.setPreviousField(currentPreviousField);

        setNextField(targetNextField);
        setPreviousField(targetPreviousField);

        currentNextField.setPreviousField(targetField);
        currentPreviousField.setNextField(targetField);


        if (targetNextField != null && targetPreviousField != null) {
            targetNextField.setPreviousField(this);
            targetPreviousField.setNextField(this);
        }

        FieldUI fieldUIimpl = getFieldUIobject();
        setFieldUIobject(targetField.getFieldUIobject());
        targetField.setFieldUIobject(fieldUIimpl);

        targetField.getFieldUIobject().turnIntoRegularField();
        getFieldUIobject().turnIntoWormhole();


        int helpID = targetField.getFieldID();
        targetField.setFieldID(getFieldID());
        setFieldID(helpID);

    }


}


