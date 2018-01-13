/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "ARTICULO", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByClave", query = "SELECT a FROM Articulo a WHERE a.clave = :clave")
    , @NamedQuery(name = "Articulo.findByTitulo", query = "SELECT a FROM Articulo a WHERE a.titulo = :titulo")
    , @NamedQuery(name = "Articulo.findByAutor", query = "SELECT a FROM Articulo a WHERE a.autor = :autor")
    , @NamedQuery(name = "Articulo.findByGenero", query = "SELECT a FROM Articulo a WHERE a.genero = :genero")
    , @NamedQuery(name = "Articulo.findByTipo", query = "SELECT a FROM Articulo a WHERE a.tipo = :tipo")
    , @NamedQuery(name = "Articulo.findByReservado", query = "SELECT a FROM Articulo a WHERE a.reservado = :reservado")})
public class Articulo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLAVE")
    private Integer clave;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "AUTOR")
    private String autor;
    @Column(name = "GENERO")
    private String genero;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "RESERVADO")
    private Boolean reservado;

    public Articulo() {
    }

    public Articulo(Integer clave) {
        this.clave = clave;
    }

    public Articulo(Integer clave, String titulo, String tipo) {
        this.clave = clave;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        Integer oldClave = this.clave;
        this.clave = clave;
        changeSupport.firePropertyChange("clave", oldClave, clave);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        String oldTitulo = this.titulo;
        this.titulo = titulo;
        changeSupport.firePropertyChange("titulo", oldTitulo, titulo);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        String oldAutor = this.autor;
        this.autor = autor;
        changeSupport.firePropertyChange("autor", oldAutor, autor);
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        String oldGenero = this.genero;
        this.genero = genero;
        changeSupport.firePropertyChange("genero", oldGenero, genero);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public Boolean getReservado() {
        return reservado;
    }

    public void setReservado(Boolean reservado) {
        Boolean oldReservado = this.reservado;
        this.reservado = reservado;
        changeSupport.firePropertyChange("reservado", oldReservado, reservado);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clave != null ? clave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.Articulo[ clave=" + clave + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
