public class DoenerOrder {
    private String fleisch;
    private String brot;
    private int anzahl;
    private boolean glutenfrei;
    private boolean tomaten;
    private boolean rotkohl;
    private boolean zwiebeln;
    private boolean takeaway;
// Konstruktor der Klasse DoenerOrder.
    public DoenerOrder(String fleisch, String brot,
                       int anzahl, boolean glutenfrei,
                       boolean tomaten, boolean rotkohl,
                       boolean zwiebeln, boolean takeaway) {
        this.fleisch = fleisch;
        this.brot = brot;
        this.anzahl = anzahl;
        this.glutenfrei = glutenfrei;
        this.tomaten = tomaten;
        this.rotkohl = rotkohl;
        this.zwiebeln = zwiebeln;
        this.takeaway = takeaway;
    }
    //getter methoden
    public String getFleisch(){
        return fleisch;
    }
    public String getBrot(){
        return brot;
    }
    public int getAnzahl(){
        return anzahl;
    }
    public boolean isGlutenfrei(){
        return glutenfrei;
    }
    public boolean isTomaten(){
        return tomaten;
    }
    public boolean isRotkohl(){
        return rotkohl;
    }
    public boolean isZwiebeln(){
        return zwiebeln;
    }
    public boolean isTakeaway(){
        return takeaway;
    }
    //hier liefert eine Beschreibung der Besstellung
    public String getDetails(){
        return "Fleisch: " + fleisch+ ", Topping: "+
                (tomaten ? "Tomaten": "")+
                (rotkohl ? "Rotkohl": "")+
                (zwiebeln ? "Zwiebeln": "")+
                ", Glutenfrei: "+ (glutenfrei ? "Ja": "Nein")+
                ", Take Away: "+ (takeaway ? "Ja": "Nein")+ "\n" + "Anzahl: "+ anzahl;
    }
}
