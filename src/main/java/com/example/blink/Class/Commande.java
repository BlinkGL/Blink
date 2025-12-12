package com.example.blink.Class;

import javafx.beans.property.*;

public class Commande {
    private final IntegerProperty id_commande;
    private final IntegerProperty id_client;
    private final IntegerProperty id_livraison;
    private final ObjectProperty<StatutCommande> statut;
    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public Commande(int id_commande, int id_client, Integer id_livraison, StatutCommande statut) {
        this.id_commande = new SimpleIntegerProperty(id_commande);
        this.id_client = new SimpleIntegerProperty(id_client);
        this.id_livraison = new SimpleIntegerProperty(id_livraison != null ? id_livraison : 0);
        this.statut = new SimpleObjectProperty<>(statut);
    }

    // Getters and properties
    public int getId_commande() { return id_commande.get(); }
    public IntegerProperty id_commandeProperty() { return id_commande; }

    public int getId_client() { return id_client.get(); }
    public IntegerProperty id_clientProperty() { return id_client; }

    public int getId_livraison() { return id_livraison.get(); }
    public IntegerProperty id_livraisonProperty() { return id_livraison; }

    public StatutCommande getStatut() { return statut.get(); }
    public void setStatut(StatutCommande statut) { this.statut.set(statut); }
    public ObjectProperty<StatutCommande> statutProperty() { return statut; }

    // Selection
    public BooleanProperty selectedProperty() { return selected; }
    public boolean isSelected() { return selected.get(); }
    public void setSelected(boolean value) { selected.set(value); }
}

