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
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getBookedAppointmentsList() == null) {
            users.setBookedAppointmentsList(new ArrayList<BookedAppointments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<BookedAppointments> attachedBookedAppointmentsList = new ArrayList<BookedAppointments>();
            for (BookedAppointments bookedAppointmentsListBookedAppointmentsToAttach : users.getBookedAppointmentsList()) {
                bookedAppointmentsListBookedAppointmentsToAttach = em.getReference(bookedAppointmentsListBookedAppointmentsToAttach.getClass(), bookedAppointmentsListBookedAppointmentsToAttach.getId());
                attachedBookedAppointmentsList.add(bookedAppointmentsListBookedAppointmentsToAttach);
            }
            users.setBookedAppointmentsList(attachedBookedAppointmentsList);
            em.persist(users);
            for (BookedAppointments bookedAppointmentsListBookedAppointments : users.getBookedAppointmentsList()) {
                Users oldUserIdOfBookedAppointmentsListBookedAppointments = bookedAppointmentsListBookedAppointments.getUserId();
                bookedAppointmentsListBookedAppointments.setUserId(users);
                bookedAppointmentsListBookedAppointments = em.merge(bookedAppointmentsListBookedAppointments);
                if (oldUserIdOfBookedAppointmentsListBookedAppointments != null) {
                    oldUserIdOfBookedAppointmentsListBookedAppointments.getBookedAppointmentsList().remove(bookedAppointmentsListBookedAppointments);
                    oldUserIdOfBookedAppointmentsListBookedAppointments = em.merge(oldUserIdOfBookedAppointmentsListBookedAppointments);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getId());
            List<BookedAppointments> bookedAppointmentsListOld = persistentUsers.getBookedAppointmentsList();
            List<BookedAppointments> bookedAppointmentsListNew = users.getBookedAppointmentsList();
            List<String> illegalOrphanMessages = null;
            for (BookedAppointments bookedAppointmentsListOldBookedAppointments : bookedAppointmentsListOld) {
                if (!bookedAppointmentsListNew.contains(bookedAppointmentsListOldBookedAppointments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BookedAppointments " + bookedAppointmentsListOldBookedAppointments + " since its userId field is not nullable.");
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
            users.setBookedAppointmentsList(bookedAppointmentsListNew);
            users = em.merge(users);
            for (BookedAppointments bookedAppointmentsListNewBookedAppointments : bookedAppointmentsListNew) {
                if (!bookedAppointmentsListOld.contains(bookedAppointmentsListNewBookedAppointments)) {
                    Users oldUserIdOfBookedAppointmentsListNewBookedAppointments = bookedAppointmentsListNewBookedAppointments.getUserId();
                    bookedAppointmentsListNewBookedAppointments.setUserId(users);
                    bookedAppointmentsListNewBookedAppointments = em.merge(bookedAppointmentsListNewBookedAppointments);
                    if (oldUserIdOfBookedAppointmentsListNewBookedAppointments != null && !oldUserIdOfBookedAppointmentsListNewBookedAppointments.equals(users)) {
                        oldUserIdOfBookedAppointmentsListNewBookedAppointments.getBookedAppointmentsList().remove(bookedAppointmentsListNewBookedAppointments);
                        oldUserIdOfBookedAppointmentsListNewBookedAppointments = em.merge(oldUserIdOfBookedAppointmentsListNewBookedAppointments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<BookedAppointments> bookedAppointmentsListOrphanCheck = users.getBookedAppointmentsList();
            for (BookedAppointments bookedAppointmentsListOrphanCheckBookedAppointments : bookedAppointmentsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the BookedAppointments " + bookedAppointmentsListOrphanCheckBookedAppointments + " in its bookedAppointmentsList field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
