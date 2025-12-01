package com.example.blink.Class;

public class Client extends Utilisateur {

    private String adresseClient;

    // Constructeur vide
    public Client() {
        super(); // Appelle le constructeur vide de Utilisateur
    }

    public Client(int id, String nom, String prenom, String email, String motDePass, String adresseClient) {
        super(id, nom, prenom, email, motDePass, Roles.client);
        this.adresseClient = adresseClient;
    }


    // Getter & Setter
    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }
}
