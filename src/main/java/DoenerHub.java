import javax.swing.*;
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
    private DoenerOrder[] doenerOrders = new DoenerOrder[3];

    public DoenerHub() {
        setTitle("DoenerHub");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setContentPane(hauptPanel);
        setResizable(false);
        setVisible(true);

        // Erzeugt 3 zufällige TooGoodToGo-Objekte
        initObjekt();

        // Zeigt alle Start-Bestellungen an
        displayAllOrders();

        // ActionListener für Buttons
        bestellenButton.addActionListener(e -> handleBestellen());
        abbrechenButton.addActionListener(e -> resetFields());
        filternButton.addActionListener(e -> displayGlutenfreiOrders());
    }

    /**
     * Erstellt mindestens drei DoenerOrder-Objekte mit Zufallswerten
     * und speichert sie in der Liste doenerOrders.
     * Wird beim Start des Programms aufgerufen.
     */
    private void initObjekt() {
        Random toogoodtogo = new Random();

        String[] fleisch = {"Lamm", "Hähnchen", "Kalb"};
        String[] brot = {"Fladenbrot", "Yufka", "Teller", "Döner Box"};

        for (int i = 0; i < doenerOrders.length; i++) {
            doenerOrders[i] = new DoenerOrder(
                    fleisch[toogoodtogo.nextInt(fleisch.length)],
                    brot[toogoodtogo.nextInt(brot.length)],
                    toogoodtogo.nextInt(3) + 1,
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean(),
                    toogoodtogo.nextBoolean()
            );
        }
    }

    //Berechnet den Gesamtpreis einer Bestellung.

    private int berechnePreis(DoenerOrder order) {
        int preis = DoenerPrice.BASIC_PREIS.getPreis();

        if (order.isTomaten()) preis += DoenerPrice.TOPPING_PREIS.getPreis();
        if (order.isRotkohl()) preis += DoenerPrice.TOPPING_PREIS.getPreis();
        if (order.isZwiebeln()) preis += DoenerPrice.TOPPING_PREIS.getPreis();

        return preis * order.getAnzahl();
    }

    private void handleBestellen() {
        String fleisch = (String) fleischcomboBox2.getSelectedItem();
        String brot = (String) brotcomboBox1.getSelectedItem();
        String text = anzahltextField1.getText();

        // Prüfung auf leere Eingabe
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

        int preis = berechnePreis(order);

        // Anzeige der Bestellung
        bestellung.setText(
                "BESTELLUNG\n" +
                        order.getDetails() +
                        "\nGesamtpreis: " + preis + " €"
        );
    }

    //Zeigt alle gespeicherten TooGoodToGo-Bestellungen an.

    private void displayAllOrders() {
        StringBuilder sb = new StringBuilder("Too Good To Go:\n");
        for (DoenerOrder o : doenerOrders) {
            sb.append(o.getDetails()).append("\n");
        }
        toogoogtogotextArea1.setText(sb.toString());
    }

    //Filtert alle glutenfreien Bestellungen und zeigt nur diese an.
    private void displayGlutenfreiOrders() {
        StringBuilder sb = new StringBuilder("Glutenfrei:\n");
        for (DoenerOrder o : doenerOrders) {
            if (o.isGlutenfrei()) {
                sb.append(o.getDetails()).append("\n");
            }
        }
        toogoogtogotextArea1.setText(sb.toString());
    }

    //Setzt alle Eingabefelder zurück.
    private void resetFields() {
        fleischcomboBox2.setSelectedIndex(0);
        brotcomboBox1.setSelectedIndex(0);
        anzahltextField1.setText("");
        glutenfreiCheckBox.setSelected(false);
        tomatenCheckBox.setSelected(false);
        rotkohlCheckBox.setSelected(false);
        zwiebelnCheckBox.setSelected(false);
        takeAwayCheckBox.setSelected(false);
        bestellung.setText("");
    }


    public static void main(String[] args) {
        new DoenerHub();
    }
}
