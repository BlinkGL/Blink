package com.example.blink.Class;

public class Livreur extends Utilisateur {

    private boolean disponibilite;

    public Livreur(int id, String nom, String prenom, String email, String motDePass, String role, boolean disponibilite)
    {
        super(id, nom, prenom, email, motDePass, role);
        this.disponibilite = disponibilite;
    }


    public boolean getdisponibilite()
    {
        return disponibilite;
    }

    public void setdisponibilite(String adresseClient)
    {
        this.disponibilite = disponibilite;
    }
}
