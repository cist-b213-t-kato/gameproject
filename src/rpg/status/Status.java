package rpg.status;



class Status {
    private int value;

    protected Status(int value){
        this.setValue(value);
    }

    protected void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}


