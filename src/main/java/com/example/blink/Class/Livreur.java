package com.example.blink.Class;

import javafx.beans.property.*;

public class Livreur extends Utilisateur {
    private final BooleanProperty disponibilite = new SimpleBooleanProperty();

    // Constructeur par défaut
    public Livreur() {
        super();
    }

    // Constructeur avec paramètres
    public Livreur(int id, String nom, String prenom, String email, String motDePass, String role, boolean disponibilite) {
        super(id, nom, prenom, email, motDePass, role);
        setDisponibilite(disponibilite);
    }

    // Property getter (for JavaFX binding)
    public BooleanProperty disponibiliteProperty() { return disponibilite; }

    // Regular getter
    public boolean getDisponibilite() { return disponibilite.get(); }

    // Regular setter
    public void setDisponibilite(boolean disponibilite) { this.disponibilite.set(disponibilite); }

    @Override
    public String toString() {
        return "Livreur{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                ", disponibilite=" + getDisponibilite() +
                '}';
    }
}