package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.State.ReadyIState;

import java.util.ArrayList;
import java.util.List;

/**
 * The Buffet class represents a buffet that contains multiple instances of a
 * formula.
 * It allows adding and retrieving formulas, calculating the total price,
 * checking if all formulas are ready,
 * and marking all formulas as ready.
 */
public class Buffet {
    private formula formula;

    private List<formula> formulaList;

    /**
     * Constructs a Buffet object with the specified formula and number of formulas.
     *
     * @param formula          the formula to be added to the buffet
     * @param nombreDeFormules the number of formulas to be added to the buffet
     * @throws Exception if an error occurs while creating the buffet
     */
    public Buffet(formula formula, int nombreDeFormules) throws Exception {
        this.formula = formula;
        this.formulaList = new ArrayList<>();
        for (int i = 0; i < nombreDeFormules; i++) {
            this.formulaList.add(new formula(formula.getName(), formula.getPrix(), formula.getItems()));
        }
    }

    /**
     * Calculates the total price of the buffet.
     *
     * @return the total price of the buffet
     */
    public double getPrice() {
        return formula.getPrix() * formulaList.size();
    }

    /**
     * Checks if all formulas in the buffet are ready.
     *
     * @return true if all formulas are ready, false otherwise
     * @throws Exception if an error occurs while checking the readiness of the
     *                   formulas
     */
    public boolean ready() throws Exception {
        for (formula formula : formulaList) {
            if (!formula.getStatus().equals(new ReadyIState())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Marks all formulas in the buffet as ready.
     *
     * @throws Exception if an error occurs while marking the formulas as ready
     */
    public void makeready() throws Exception {
        for (formula formula : formulaList) {
            formula.setStatus(new ReadyIState());
        }
    }

    /**
     * Retrieves the formula of the buffet.
     *
     * @return the formula of the buffet
     */
    public formula getFormule() {
        return formula;
    }

    /**
     * Sets the formula of the buffet.
     *
     * @param formula the formula to be set
     */
    public void setFormule(formula formula) {
        this.formula = formula;
    }

    /**
     * Retrieves the list of formulas in the buffet.
     *
     * @return the list of formulas in the buffet
     */
    public List<formula> getFormuleList() {
        return formulaList;
    }

    /**
     * Sets the list of formulas in the buffet.
     *
     * @param formulaList the list of formulas to be set
     */
    public void setFormuleList(List<formula> formulaList) {
        this.formulaList = formulaList;
    }
}
