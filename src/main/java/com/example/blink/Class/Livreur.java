package com.example.blink.Class;

public class Livreur extends Utilisateur {

    private boolean disponibilite;

    // Constructeur vide
    public Livreur() {
        super();
    }

    // Constructeur complet avec role fixe (toujours Livreur)
    public Livreur(int id, String nom, String prenom, String email, String motDePass,
                   boolean disponibilite) {
        super(id, nom, prenom, email, motDePass, Roles.livreur);
        this.disponibilite = disponibilite;
    }

    // Getter & Setter
    public boolean getZoneLivraison() {
        return disponibilite;
    }

    public void setZoneLivraison(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void Voir_Livraison_dispo(){

    }

    public void Choissi_livraison(){

    }
}
