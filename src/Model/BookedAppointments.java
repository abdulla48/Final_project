/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abdullah
 */
@Entity
@Table(name = "booked_appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookedAppointments.findAll", query = "SELECT b FROM BookedAppointments b")
    , @NamedQuery(name = "BookedAppointments.findById", query = "SELECT b FROM BookedAppointments b WHERE b.id = :id")
    , @NamedQuery(name = "BookedAppointments.findByStatus", query = "SELECT b FROM BookedAppointments b WHERE b.status = :status")})
public class BookedAppointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Lob
    @Column(name = "doctor_commnet")
    private String doctorCommnet;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appointments appointmentId;

    public BookedAppointments() {
    }

    public BookedAppointments(Integer id) {
        this.id = id;
    }

    public BookedAppointments(Integer id, String status, String doctorCommnet) {
        this.id = id;
        this.status = status;
        this.doctorCommnet = doctorCommnet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorCommnet() {
        return doctorCommnet;
    }

    public void setDoctorCommnet(String doctorCommnet) {
        this.doctorCommnet = doctorCommnet;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Appointments getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointments appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookedAppointments)) {
            return false;
        }
        BookedAppointments other = (BookedAppointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.BookedAppointments[ id=" + id + " ]";
    }
    
}
