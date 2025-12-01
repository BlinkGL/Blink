package com.example.blink.Class;

public class Utilisateur {

    private int Id;
    private String Nom;
    private String Prenom;
    private String Email;
    private String MotDePass;
    public enum Roles {admin, livreur, client}
    public Roles Role;

    // Constructor vide
    public Utilisateur() {
    }

    // Constructeur avec paramètres
    public Utilisateur(int id, String nom, String prenom, String email, String motDePass, Roles role) {
        this.Id = id;
        this.Nom = nom;
        this.Prenom = prenom;
        this.Email = email;
        this.MotDePass = motDePass;
        this.Role = role;
    }

    // Getters et setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        this.Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getMotDePass() {
        return MotDePass;
    }

    public void setMotDePass(String motDePass) {
        this.MotDePass = motDePass;
    }

    public Roles getRole() {
        return Role;
    }

    public void setRole(Roles role) {
        this.Role = role;
    }

   public void Modifer_Profil(){

   }
}