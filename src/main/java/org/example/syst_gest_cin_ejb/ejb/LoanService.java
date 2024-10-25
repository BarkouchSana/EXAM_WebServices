package org.example.syst_gest_cin_ejb.entity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class LoanService {

    @PersistenceContext
    private EntityManager em;

    public Loan borrowCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isAvailable()) {
            cd.setAvailable(false);
            em.merge(cd);

            Loan loan = new Loan();
            loan.setCd(cd);
            loan.setLoanDate(new Date());

            em.persist(loan);
            return loan;
        }
        return null;
    }

    public void returnCD(Long loanId) {
        Loan loan = em.find(Loan.class, loanId);
        if (loan != null) {
            CD cd = loan.getCd();
            cd.setAvailable(true);
            em.merge(cd);

            loan.setReturnDate(new Date());
            em.merge(loan);
        }
    }

    public List<Loan> getActiveLoans() {
        return em.createQuery("SELECT l FROM Loan l WHERE l.returnDate IS NULL", Loan.class)
                .getResultList();
    }
}
