package com.example.blink.Class;

public class Colis {

    private int idColis;
    private int idClient;

    public Colis(int idColis,int idClient)
    {
        this.idColis= idColis;
        this.idClient= idClient;
    }

    public int getidColis()
    {
        return idColis;
    }

    public void setIdColis(int idColis)
    {
        this.idColis=idColis;
    }


    public int getIdClient()
    {
        return idClient;
    }

    public void setIdClient(int idClient)
    {
        this.idClient=idClient;
    }


}
