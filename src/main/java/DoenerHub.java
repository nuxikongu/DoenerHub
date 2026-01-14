import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class DoenerHub extends JFrame {

    private JPanel hauptPanel;
    private JComboBox<String> brotcomboBox1;
    private JComboBox<String> fleischcomboBox2;
    private JCheckBox rotkohlCheckBox;
    private JCheckBox tomatenCheckBox;
    private JCheckBox zwiebelnCheckBox;
    private JTextArea bestellung;
    private JButton bestellenButton;
    private JButton abbrechenButton;
    private JTextArea toogoogtogotextArea1;
    private JCheckBox glutenfreiCheckBox;
    private JButton filternButton;
    private JTextField anzahltextField1;
    private JCheckBox takeAwayCheckBox;

    // Liste für TooGoodToGo-Bestellungen
    private ArrayList<DoenerOrder> bestellungen = new ArrayList<>();

    // Mehrere Bestellungen möglich
    private ArrayList<DoenerOrder> kundenBestellungen = new ArrayList<>();

    public DoenerHub() {
        setTitle("DoenerHub");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setContentPane(hauptPanel);
        setResizable(false);
        setVisible(true);

        // Erzeugt 3 zufällige TooGoodToGo-Objekte
        initObjekt();

        // Zeigt alle Bestellungen an
        displayAllOrders();

        // ActionListener für Buttons
        bestellenButton.addActionListener(e -> handleBestellen());
        abbrechenButton.addActionListener(e -> resetFields());
        filternButton.addActionListener(e -> displayGlutenfreiOrders());
    }

    /**
     * Erstellt genau drei DoenerOrder-Objekte mit Zufallswerten
     * und speichert sie in der ArrayList 'bestellungen'.
     * Wird beim Start des Programms aufgerufen.
     */
    private void initObjekt() {
        Random toogoodtogo = new Random();

        String[] fleisch = {"Lamm", "Hähnchen", "Kalb"};
        String[] brot = {"Fladenbrot", "Yufka", "Teller", "Döner Box"};

        bestellungen.clear();

        for (int i = 0; i < 3; i++) {
            DoenerOrder order = new DoenerOrder(
                    fleisch[toogoodtogo.nextInt(fleisch.length)],
                    brot[toogoodtogo.nextInt(brot.length)],
                    toogoodtogo.nextInt(3) + 1,
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean()
            );
            bestellungen.add(order);
        }
    }

    // Berechnet den Gesamtpreis einer Bestellung.
    private int berechnePreis(DoenerOrder order) {
        int preis = DoenerPrice.BASIC_PREIS.getPreis();

        if (order.isTomaten()) preis += DoenerPrice.TOPPING_PREIS.getPreis();
        if (order.isRotkohl()) preis += DoenerPrice.TOPPING_PREIS.getPreis();
        if (order.isZwiebeln()) preis += DoenerPrice.TOPPING_PREIS.getPreis();

        return preis * order.getAnzahl();
    }

    /**
     * Jeder Klick auf "Bestellen" erzeugt ein neues DoenerOrder-Objekt
     * und speichert es in der Liste 'kundenBestellungen'.
     */
    private void handleBestellen() {
        String fleisch = (String) fleischcomboBox2.getSelectedItem();
        String brot = (String) brotcomboBox1.getSelectedItem();
        String text = anzahltextField1.getText();

        // Kontrolliert leere oder ungültige Anzahl
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte Anzahl eingeben!");
            return;
        }

        int anzahl;
        try {
            anzahl = Integer.parseInt(text);
            if (anzahl <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ungültige Anzahl!");
            return;
        }

        // Erzeugt neues Bestell-Objekt
        DoenerOrder order = new DoenerOrder(
                fleisch, brot, anzahl,
                glutenfreiCheckBox.isSelected(),
                tomatenCheckBox.isSelected(),
                rotkohlCheckBox.isSelected(),
                zwiebelnCheckBox.isSelected(),
                takeAwayCheckBox.isSelected()
        );

        // Speichern: mehrere Bestellungen möglich
        kundenBestellungen.add(order);

        // Anzeige: alle Kundenbestellungen + Gesamtsumme
        displayKundenBestellungen();
    }

    // Zeigt alle Kunden-Bestellungen in der TextArea 'bestellung' an.
    private void displayKundenBestellungen() {
        StringBuilder sb = new StringBuilder("Bestellung:\n");

        int gesamt = 0;
        for (int i = 0; i < kundenBestellungen.size(); i++) {
            DoenerOrder order = kundenBestellungen.get(i);
            int preis = berechnePreis(order);
            gesamt += preis;

            sb.append("BestellNr:").append(i + 1).append("\n");
            sb.append(order.getDetails()).append("\n");
            sb.append("Preis: ").append(preis).append(" €\n");
        }

        sb.append("GESAMTSUMME: ").append(gesamt).append(" €\n");

        bestellung.setText(sb.toString());
    }

    // Zeigt alle gespeicherten TooGoodToGo-Bestellungen an.
    private void displayAllOrders() {
        StringBuilder sb = new StringBuilder("Too Good To Go:\n");
        for (DoenerOrder o : bestellungen) {
            sb.append(o.getDetails()).append("\n");
        }
        toogoogtogotextArea1.setText(sb.toString());
    }

    // Filtert alle glutenfreien TooGoodToGo-Bestellungen und zeigt nur diese an.
    private void displayGlutenfreiOrders() {
        StringBuilder sb = new StringBuilder("Glutenfrei:\n");
        for (DoenerOrder o : bestellungen) {
            if (o.isGlutenfrei()) {
                sb.append(o.getDetails()).append("\n");
            }
        }
        toogoogtogotextArea1.setText(sb.toString());
    }

    // Setzt alle zurück.
    private void resetFields() {
        fleischcomboBox2.setSelectedIndex(0);
        brotcomboBox1.setSelectedIndex(0);
        anzahltextField1.setText("");
        glutenfreiCheckBox.setSelected(false);
        tomatenCheckBox.setSelected(false);
        rotkohlCheckBox.setSelected(false);
        zwiebelnCheckBox.setSelected(false);
        takeAwayCheckBox.setSelected(false);

    }

    public static void main(String[] args) {
        new DoenerHub();
    }
}
