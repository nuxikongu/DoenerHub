public enum DoenerPrice {

    BASIC_PREIS(5),
    TOPPING_PREIS(1);

    private final int preis;
    DoenerPrice (int preis){
        this.preis = preis;}
    public int getPreis(){
        return preis;
    }
}
