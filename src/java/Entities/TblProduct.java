/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adler
 */
@Entity
@Table(name = "TblProduct", catalog = "XML", schema = "SA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProduct.findAll", query = "SELECT t FROM TblProduct t")
    , @NamedQuery(name = "TblProduct.findByProductID", query = "SELECT t FROM TblProduct t WHERE t.productID = :productID")
    , @NamedQuery(name = "TblProduct.findByProductName", query = "SELECT t FROM TblProduct t WHERE t.productName = :productName")
    , @NamedQuery(name = "TblProduct.findByPrice", query = "SELECT t FROM TblProduct t WHERE t.price = :price")
    , @NamedQuery(name = "TblProduct.findByThumbnail", query = "SELECT t FROM TblProduct t WHERE t.thumbnail = :thumbnail")
    , @NamedQuery(name = "TblProduct.findByCategoryID", query = "SELECT t FROM TblProduct t WHERE t.categoryID = :categoryID")
    , @NamedQuery(name = "TblProduct.findByIsActive", query = "SELECT t FROM TblProduct t WHERE t.isActive = :isActive")
    , @NamedQuery(name = "TblProduct.findByResourceDomain", query = "SELECT t FROM TblProduct t WHERE t.resourceDomain = :resourceDomain")})
public class TblProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ProductID", nullable = false, length = 500)
    private String productID;
    @Column(name = "ProductName", length = 500)
    private String productName;
    @Column(name = "Price", length = 500)
    private String price;
    @Column(name = "Thumbnail", length = 500)
    private String thumbnail;
    @Column(name = "CategoryID", length = 500)
    private String categoryID;
    @Column(name = "IsActive", length = 500)
    private String isActive;
    @Column(name = "ResourceDomain", length = 500)
    private String resourceDomain;

    public TblProduct() {
    }

    public TblProduct(String productID) {
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getResourceDomain() {
        return resourceDomain;
    }

    public void setResourceDomain(String resourceDomain) {
        this.resourceDomain = resourceDomain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProduct)) {
            return false;
        }
        TblProduct other = (TblProduct) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TblProduct[ productID=" + productID + " ]";
    }
    
}
