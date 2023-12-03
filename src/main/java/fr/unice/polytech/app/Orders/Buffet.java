package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.State.ReadyIState;

import java.util.ArrayList;
import java.util.List;

public class Buffet {
    private formula formula;

    private List<formula> formulaList;

    public Buffet(formula formula, int nombreDeFormules) throws Exception {
        this.formula = formula;
        this.formulaList = new ArrayList<>();
        for (int i = 0; i < nombreDeFormules; i++) {
            this.formulaList.add(new formula(formula.getName(), formula.getPrix(), formula.getItems()));
        }
    }

    public double getPrice() {
        return formula.getPrix() * formulaList.size();
    }

    public boolean ready() throws Exception {
        for (formula formula : formulaList) {
            if (!formula.getStatus().equals(new ReadyIState())) {
                return false;
            }
        }
        return true;
    }
    public void makeready() throws Exception {
        for (formula formula : formulaList) {
            formula.setStatus(new ReadyIState());
        }
    }

    public formula getFormule() {
        return formula;
    }

    public void setFormule(formula formula) {
        this.formula = formula;
    }

    public List<formula> getFormuleList() {
        return formulaList;
    }

    public void setFormuleList(List<formula> formulaList) {
        this.formulaList = formulaList;
    }

}
