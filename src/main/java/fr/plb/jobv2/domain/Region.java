package fr.plb.jobv2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Region")
public class Region implements Serializable {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqregion", initialValue = 5, allocationSize = 100)
    private Long id;

	@Column
    private String regionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
    public Region() {
    	
    }
}
