package com.example.blink.Class;


public class Commande {

    private int idCommande;
    private int idClient;
    private Colis ListColis;


    public Commande(int idCommande, int idClient, Colis ListColis)
    {
        this.idCommande= idCommande;
        this.idClient= idClient;
        this.ListColis= ListColis;
    }

    public int getIdCommande(int idCommande)
    {
        return idCommande;
    }

    public void setIdCommande()
    {
        this.idCommande=idCommande;
    }

    public int getIdClient(int idClient)
    {
        return idClient;
    }

    public void setIdClient()
    {
        this.idClient=idClient;
    }


    public Colis getListColis(Colis listColis)
    {
        return listColis;
    }

    public void setListColis()
    {
        this.ListColis=ListColis;
    }


}
