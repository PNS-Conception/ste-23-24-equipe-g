package fr.unice.polytech.app.System;

import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Users.PaiementProxy;
import fr.unice.polytech.app.Util.RandomGenerator;

public class PaiementSystem  implements PaiementProxy {

    CampusUser user;
    private RandomGenerator randomGenerator;

    public PaiementSystem(CampusUser user){
        this.user = user;
    }

    public boolean makePayment(SingleOrder singleOrder) throws Exception {

        //9 fois sur 10, la commande est validée, 1 fois sur 10 il y a une erreur.
        if (Math.random() < 0.9) {
            if (user.getBalance() > 0){
                if(user.getBalance() >= singleOrder.getPrice()){
                    user.setBalance(user.getBalance() - singleOrder.getPrice());
                }else{
                    singleOrder.setPrice(singleOrder.getPrice() - user.getBalance());
                        user.setBalance(0);
                }
            }
            singleOrder.pay();
            user.getHistory().add(singleOrder);
            user.getCart().clear();
            return true;
        } else {
            return false;
        }
    }

    public boolean makePaymentmock(SingleOrder singleOrder) throws Exception {
        //9 fois sur 10, la commande est validée, 1 fois sur 10 il y a une erreur.
        if (randomGenerator.nextDouble() < 0.9) {
            if (user.getBalance() > 0){
                if(user.getBalance() >= singleOrder.getPrice()){
                    user.setBalance(user.getBalance() - singleOrder.getPrice());
                }else{
                    System.out.println("here");
                    singleOrder.setPrice(singleOrder.getPrice() - user.getBalance());
                    user.setBalance(0);
                    System.out.println(user.getBalance());
                }
            }
            singleOrder.pay();
            user.getHistory().add(singleOrder);
            user.getCart().clear();
            return true;
        } else {
            return false;
        }

    }
    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public void refund(SingleOrder singleOrder) {
        user.setBalance(singleOrder.getPrice());
        user.getHistory().remove(singleOrder);
    }
}
