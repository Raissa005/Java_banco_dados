/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author Aluno
 */
public class Account {

    private String ownerName;
    private int accountNumber;
    private Double accountBalance;

    public Account(String ownerName, int accountNumber) {

        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.accountBalance = 0.0;

    }

    public String getOwnerName() {

        return this.ownerName;

    }

    public Integer getAccountNumber() {

        return this.accountNumber;

    }

    public Double getAccountBalance() {

        return this.accountBalance;

    }

    public Double withdraw(Double value) {

        if (value > accountBalance) {

            return -1.0;

        }

        this.accountBalance -= value;

        return this.accountBalance;

    }

    public Double deposit(Double value){
        
        this.accountBalance += value;
        
        return this.accountBalance;
        
    }
    
}
