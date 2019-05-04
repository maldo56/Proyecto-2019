package obj.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.postgis.Geometry;
import org.postgis.Point;


@Entity
public class scooter implements Serializable {

	
	private static final long serialVersionUID = -2414162159235784544L;
	
	@Id
	private String guid;
	private Double bateryLevel;
	private Boolean isRented;
	private Boolean isAvailable;
	
//	@Column( columnDefinition="geometry" )
//	private Geometry location;
	
	public scooter() {
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		this.bateryLevel = 100.0;
		this.isRented = false;
		this.isAvailable = true;
	}
	
	public scooter(Double bateryLevel, Boolean isRented, Boolean isAvailable) {
		super();
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		this.bateryLevel = bateryLevel;
		this.isRented = isRented;
		this.isAvailable = isAvailable;
	}

	public String getGuid() {
		return guid;
	}
	
	public Double getBateryLevel() {
		return bateryLevel;
	}
	
	public void setBateryLevel(Double bateryLevel) {
		this.bateryLevel = bateryLevel;
	}
	
	public Boolean getIsRented() {
		return isRented;
	}
	
	public void setIsRented(Boolean isRented) {
		this.isRented = isRented;
	}
	
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

//	public Point getLocation() {
//		return (Point) location;
//	}
//
//	public void setLocation(Point location) {
//		this.location = location;
//	}
}
