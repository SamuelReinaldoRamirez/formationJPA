package fr.plb.jobv2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Address implements Serializable{
	
    private static final long serialVersionUID = 1L;

	
//	@Id
//	@GeneratedValue(generator = "sequence-generator")
//	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqaddress", initialValue = 5, allocationSize = 100)
//    private Long id;

    @Column
    private String streetAddress;

    @Column
    private String postalCode;

    @Column
    private String city;

    @Column
    private String stateProvince;

    @ManyToOne
	private Country country;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

	    public Country getCountry() {
	        return country;
	    }

	    public void setCountry(Country country) {
	        this.country = country;
	    }

}
