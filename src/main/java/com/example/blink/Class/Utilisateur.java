package com.example.blink.Class;

import javafx.beans.property.*;

public class Utilisateur {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty motDePass = new SimpleStringProperty();
    private final StringProperty role = new SimpleStringProperty();

    // Constructeur par défaut
    public Utilisateur() {
        // Constructeur par défaut pour JavaFX
    }

    // Constructeur avec paramètres
    public Utilisateur(int id, String nom, String prenom, String email, String motDePass, String role) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setMotDePass(motDePass);
        setRole(role);
    }

    // Property getters (for JavaFX binding)
    public IntegerProperty idProperty() { return id; }
    public StringProperty nomProperty() { return nom; }
    public StringProperty prenomProperty() { return prenom; }
    public StringProperty emailProperty() { return email; }
    public StringProperty motDePassProperty() { return motDePass; }
    public StringProperty roleProperty() { return role; }

    // Regular getters
    public int getId() { return id.get(); }
    public String getNom() { return nom.get(); }
    public String getPrenom() { return prenom.get(); }
    public String getEmail() { return email.get(); }
    public String getMotDePass() { return motDePass.get(); }
    public String getRole() { return role.get(); }

    // Regular setters
    public void setId(int id) { this.id.set(id); }
    public void setNom(String nom) { this.nom.set(nom); }
    public void setPrenom(String prenom) { this.prenom.set(prenom); }
    public void setEmail(String email) { this.email.set(email); }
    public void setMotDePass(String motDePass) { this.motDePass.set(motDePass); }
    public void setRole(String role) { this.role.set(role); }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}