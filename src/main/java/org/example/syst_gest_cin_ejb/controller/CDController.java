package org.example.syst_gest_cin_ejb.controller;



import org.example.syst_gest_cin_ejb.entity.CD;
import org.example.syst_gest_cin_ejb.entity.LoanService;
import jakarta.ejb.EJB;
import jakarta.faces.bean.ManagedBean;

import java.util.List; // Importation corrigée

@ManagedBean
public class CDController {

    @EJB
    private LoanService loanService;

    private List<CD> availableCDs;

    public List<CD> getAvailableCDs() {
        if (availableCDs == null) {
            availableCDs = loanService.listAvailableCDs();
        }
        return availableCDs;
    }

    public void borrowCD(Long cdId) {
        loanService.borrowCD(cdId);
    }
}
