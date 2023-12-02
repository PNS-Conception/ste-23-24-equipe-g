package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.State.ReadyIState;
import fr.unice.polytech.app.Orders.Formule;
import java.util.ArrayList;
import java.util.List;

public class Buffet {
    private Formule formule;

    private List<Formule> FormuleList;

    public Buffet(Formule formule, int nombreDeFormules) {
        this.formule = formule;
        this.FormuleList = new ArrayList<>();
        for (int i = 0; i < nombreDeFormules; i++) {
            this.FormuleList.add(formule);
        }
    }

    public double getPrice() {
        return formule.getPrix() * FormuleList.size();
    }

    public boolean ready() throws Exception {
        for (Formule formule : FormuleList) {
            if (!formule.getStatus().equals(new ReadyIState())) {
                return false;
            }
        }
        return true;
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public List<Formule> getFormuleList() {
        return FormuleList;
    }

    public void setFormuleList(List<Formule> formuleList) {
        FormuleList = formuleList;
    }

}
