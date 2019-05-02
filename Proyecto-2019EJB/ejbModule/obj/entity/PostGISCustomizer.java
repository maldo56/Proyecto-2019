package obj.entity;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.RelationalDescriptor;
import org.eclipse.persistence.mappings.DirectToFieldMapping;
import org.eclipse.persistence.sessions.Session;

public class PostGISCustomizer implements SessionCustomizer {
	
	public void customize(Session s) throws Exception
	{
		RelationalDescriptor desc;
		DirectToFieldMapping mapping;
		
		desc = (RelationalDescriptor) s.getDescriptor(scooter.class);
		mapping = (DirectToFieldMapping) desc.getMappingForAttributeName("location");
		mapping.setConverter(null);
	}
}