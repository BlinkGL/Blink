package com.example.blink.Class;

public class Client extends Utilisateur {

    private String adresseClient;


    public Client(int id, String nom, String prenom, String email, String motDePass, String role, String adresseClient)
    {
        super(id, nom, prenom, email, motDePass, role);
        this.adresseClient = adresseClient;
    }


    public String getAdresseClient()
    {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient)
    {
        this.adresseClient = adresseClient;
    }
}
