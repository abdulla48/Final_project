/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.exceptions.IllegalOrphanException;
import Model.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdullah
 */
public class AppointmentsJpaController implements Serializable {

    public AppointmentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Appointments appointments) {
        if (appointments.getBookedAppointmentsList() == null) {
            appointments.setBookedAppointmentsList(new ArrayList<BookedAppointments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<BookedAppointments> attachedBookedAppointmentsList = new ArrayList<BookedAppointments>();
            for (BookedAppointments bookedAppointmentsListBookedAppointmentsToAttach : appointments.getBookedAppointmentsList()) {
                bookedAppointmentsListBookedAppointmentsToAttach = em.getReference(bookedAppointmentsListBookedAppointmentsToAttach.getClass(), bookedAppointmentsListBookedAppointmentsToAttach.getId());
                attachedBookedAppointmentsList.add(bookedAppointmentsListBookedAppointmentsToAttach);
            }
            appointments.setBookedAppointmentsList(attachedBookedAppointmentsList);
            em.persist(appointments);
            for (BookedAppointments bookedAppointmentsListBookedAppointments : appointments.getBookedAppointmentsList()) {
                Appointments oldAppointmentIdOfBookedAppointmentsListBookedAppointments = bookedAppointmentsListBookedAppointments.getAppointmentId();
                bookedAppointmentsListBookedAppointments.setAppointmentId(appointments);
                bookedAppointmentsListBookedAppointments = em.merge(bookedAppointmentsListBookedAppointments);
                if (oldAppointmentIdOfBookedAppointmentsListBookedAppointments != null) {
                    oldAppointmentIdOfBookedAppointmentsListBookedAppointments.getBookedAppointmentsList().remove(bookedAppointmentsListBookedAppointments);
                    oldAppointmentIdOfBookedAppointmentsListBookedAppointments = em.merge(oldAppointmentIdOfBookedAppointmentsListBookedAppointments);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Appointments appointments) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointments persistentAppointments = em.find(Appointments.class, appointments.getId());
            List<BookedAppointments> bookedAppointmentsListOld = persistentAppointments.getBookedAppointmentsList();
            List<BookedAppointments> bookedAppointmentsListNew = appointments.getBookedAppointmentsList();
            List<String> illegalOrphanMessages = null;
            for (BookedAppointments bookedAppointmentsListOldBookedAppointments : bookedAppointmentsListOld) {
                if (!bookedAppointmentsListNew.contains(bookedAppointmentsListOldBookedAppointments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BookedAppointments " + bookedAppointmentsListOldBookedAppointments + " since its appointmentId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<BookedAppointments> attachedBookedAppointmentsListNew = new ArrayList<BookedAppointments>();
            for (BookedAppointments bookedAppointmentsListNewBookedAppointmentsToAttach : bookedAppointmentsListNew) {
                bookedAppointmentsListNewBookedAppointmentsToAttach = em.getReference(bookedAppointmentsListNewBookedAppointmentsToAttach.getClass(), bookedAppointmentsListNewBookedAppointmentsToAttach.getId());
                attachedBookedAppointmentsListNew.add(bookedAppointmentsListNewBookedAppointmentsToAttach);
            }
            bookedAppointmentsListNew = attachedBookedAppointmentsListNew;
            appointments.setBookedAppointmentsList(bookedAppointmentsListNew);
            appointments = em.merge(appointments);
            for (BookedAppointments bookedAppointmentsListNewBookedAppointments : bookedAppointmentsListNew) {
                if (!bookedAppointmentsListOld.contains(bookedAppointmentsListNewBookedAppointments)) {
                    Appointments oldAppointmentIdOfBookedAppointmentsListNewBookedAppointments = bookedAppointmentsListNewBookedAppointments.getAppointmentId();
                    bookedAppointmentsListNewBookedAppointments.setAppointmentId(appointments);
                    bookedAppointmentsListNewBookedAppointments = em.merge(bookedAppointmentsListNewBookedAppointments);
                    if (oldAppointmentIdOfBookedAppointmentsListNewBookedAppointments != null && !oldAppointmentIdOfBookedAppointmentsListNewBookedAppointments.equals(appointments)) {
                        oldAppointmentIdOfBookedAppointmentsListNewBookedAppointments.getBookedAppointmentsList().remove(bookedAppointmentsListNewBookedAppointments);
                        oldAppointmentIdOfBookedAppointmentsListNewBookedAppointments = em.merge(oldAppointmentIdOfBookedAppointmentsListNewBookedAppointments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = appointments.getId();
                if (findAppointments(id) == null) {
                    throw new NonexistentEntityException("The appointments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointments appointments;
            try {
                appointments = em.getReference(Appointments.class, id);
                appointments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The appointments with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<BookedAppointments> bookedAppointmentsListOrphanCheck = appointments.getBookedAppointmentsList();
            for (BookedAppointments bookedAppointmentsListOrphanCheckBookedAppointments : bookedAppointmentsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Appointments (" + appointments + ") cannot be destroyed since the BookedAppointments " + bookedAppointmentsListOrphanCheckBookedAppointments + " in its bookedAppointmentsList field has a non-nullable appointmentId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(appointments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Appointments> findAppointmentsEntities() {
        return findAppointmentsEntities(true, -1, -1);
    }

    public List<Appointments> findAppointmentsEntities(int maxResults, int firstResult) {
        return findAppointmentsEntities(false, maxResults, firstResult);
    }

    private List<Appointments> findAppointmentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Appointments.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Appointments findAppointments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Appointments.class, id);
        } finally {
            em.close();
        }
    }

    public int getAppointmentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Appointments> rt = cq.from(Appointments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
