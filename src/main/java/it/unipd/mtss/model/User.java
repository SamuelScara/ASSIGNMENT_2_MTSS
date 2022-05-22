////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {
    private String nome;
    private String cognome;
    long ID;
    private LocalDate dataNascita;

    public User(String nome, String cognome, int ID, LocalDate dataNascita){
        this.nome = nome;
        this.cognome = cognome;
        this.ID = ID;
        if(dataNascita == null){
            throw new IllegalArgumentException("La data di nascita inserita è null");
        }
        if(dataNascita.isBefore(LocalDate.now())){
            this.dataNascita = dataNascita;
        }else{
            throw new IllegalArgumentException("La data di nascita inserita deve essere prima di quella di oggi");
        }
    }
    public String getNome(){
        return nome;
    }
    public String getCognome(){
        return cognome;
    }
    public long getID(){
        return ID;
    }
    public LocalDate getDataNascita(){
        return dataNascita;
    }
}