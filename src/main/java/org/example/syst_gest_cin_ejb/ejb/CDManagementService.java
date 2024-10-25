package org.example.syst_gest_cin_ejb.ejb;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class CDManagementService {

    @PersistenceContext
    private EntityManager em;
    private CD currentCD;

    public void startCDManagement(Long cdId) {
        this.currentCD = em.find(CD.class, cdId);
    }

    public void updateCDDetails(String title, String artist, boolean available) {
        if (currentCD != null) {
            currentCD.setTitle(title);
            currentCD.setArtist(artist);
            currentCD.setAvailable(available);
            em.merge(currentCD);
        }
    }

    public void addCD(String title, String artist) {
        CD cd = new CD();
        cd.setTitle(title);
        cd.setArtist(artist);
        cd.setAvailable(true);
        em.persist(cd);
    }

    public void deleteCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public List<CD> listAllCDs() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
