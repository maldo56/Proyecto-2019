package obj.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class zona {
	
	@Id
	public String guid;
	
	public zona() {
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
