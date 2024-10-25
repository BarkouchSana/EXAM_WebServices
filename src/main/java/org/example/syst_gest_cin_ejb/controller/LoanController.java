package org.example.syst_gest_cin_ejb.controller;

import jakarta.ejb.EJB;
import jakarta.faces.bean.ManagedBean;
import org.example.syst_gest_cin_ejb.entity.Loan;
import org.example.syst_gest_cin_ejb.entity.LoanService;

import java.util.List;

@ManagedBean
public class LoanController {

    @EJB
    private LoanService loanService;

    public List<Loan> getActiveLoans() {
        return loanService.getActiveLoans();
    }

    public void returnCD(Long loanId) {
        loanService.returnCD(loanId);
    }
}

