package com.example.blink.Class;

import javafx.beans.property.*;

public class Livraison {

    private final IntegerProperty id;
    private final StringProperty statut;
    private final IntegerProperty id_livreur; // use IntegerProperty, default 0 if null
    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public Livraison(int id, String statut, Integer id_livreur) {
        this.id = new SimpleIntegerProperty(id);
        this.statut = new SimpleStringProperty(statut);
        this.id_livreur = new SimpleIntegerProperty(id_livreur != null ? id_livreur : 0);
    }

    // ------ Getters ------
    public int getId() { return id.get(); }
    public String getStatut() { return statut.get(); }
    public int getId_livreur() { return id_livreur.get(); }

    // ------ Property getters ------
    public IntegerProperty idProperty() { return id; }
    public StringProperty statutProperty() { return statut; }
    public IntegerProperty id_livreurProperty() { return id_livreur; }

    // ------ Setters if needed ------
    public void setId_livreur(int id_livreur) { this.id_livreur.set(id_livreur); }

    public BooleanProperty selectedProperty() { return selected; }
    public boolean isSelected() { return selected.get(); }
    public void setSelected(boolean value) { selected.set(value); }
}
