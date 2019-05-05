package utils;

import java.util.List;

import obj.dto.DtoAlquiler;
import obj.dto.DtoLocation;

public class Utils {

	public Utils() {
		
	}
	
	public static String kmlLinestring(List<DtoLocation> ubicaciones) {
		String kml = "";
		
		DtoLocation point;
		
		point = ubicaciones.get(0);
		kml = point.getLat() + " " + point.getLng();
		
		for ( int x = 1; x < ubicaciones.size(); x ++ ) {
			point = ubicaciones.get(x);
			kml = kml + "," + point.getLat() + " " + point.getLng();
		}
		
		return "LINESTRING(" + kml + ")";
	}
}
