package fr.unice.polytech.app;

import fr.unice.polytech.app.State.ReadyIState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Buffet {
    private Formule formule;

    private List<Formule> FormuleList;

    public Buffet(Formule formule, int nombreDeFormules) throws Exception {
        this.formule = formule;
        this.FormuleList = new ArrayList<>();
        for (int i = 0; i < nombreDeFormules; i++) {
            this.FormuleList.add(new Formule(formule.getName(), formule.getPrix(), formule.getItems()));
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
    public void makeready() throws Exception {
        for (Formule formule : FormuleList) {
            formule.setStatus(new ReadyIState());
        }
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
