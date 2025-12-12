package com.example.blink.Class;

import javafx.beans.property.*;

public class Colis {

    private final IntegerProperty id;
    private final IntegerProperty id_commande;
    private final StringProperty nom;
    private final StringProperty source;
    private final DoubleProperty poids;
    private final IntegerProperty quantite;

    // Add this for checkbox selection
    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public Colis(int id, Integer id_commande, String nom, String source, double poids, int quantite) {
        this.id = new SimpleIntegerProperty(id);
        this.id_commande = new SimpleIntegerProperty(id_commande != null ? id_commande : 0);
        this.nom = new SimpleStringProperty(nom);
        this.source = new SimpleStringProperty(source);
        this.poids = new SimpleDoubleProperty(poids);
        this.quantite = new SimpleIntegerProperty(quantite);
    }

    // ------ Getters ------
    public int getId() { return id.get(); }
    public int getId_commande() { return id_commande.get(); }
    public String getNom() { return nom.get(); }
    public String getSource() { return source.get(); }
    public double getPoids() { return poids.get(); }
    public int getQuantite() { return quantite.get(); }

    // ------ Property getters ------
    public IntegerProperty idProperty() { return id; }
    public IntegerProperty id_commandeProperty() { return id_commande; }
    public StringProperty nomProperty() { return nom; }
    public StringProperty sourceProperty() { return source; }
    public DoubleProperty poidsProperty() { return poids; }
    public IntegerProperty quantiteProperty() { return quantite; }

    // ------ Selection property ------
    public BooleanProperty selectedProperty() { return selected; }
    public boolean isSelected() { return selected.get(); }
    public void setSelected(boolean value) { selected.set(value); }
}
