package utils;

import java.util.ArrayList;
import java.util.List;

import obj.dto.DtoAlquiler;
import obj.dto.DtoGeometria;
import obj.dto.DtoLocation;
import obj.dto.DtoPunto;

public class Utils {

	public Utils() {
		
	}
	
	public static String kmlLinestring(List<DtoLocation> ubicaciones) {
		String kml = "";
		
		DtoLocation point;
		
		if ( ubicaciones.size() > 0 ) {
			point = ubicaciones.get(0);
			
			kml = point.getLat() + " " + point.getLng();
			
			for ( int x = 1; x < ubicaciones.size(); x ++ ) {
				point = ubicaciones.get(x);
				kml = kml + "," + point.getLat() + " " + point.getLng();
			}
		}
		
		return "LINESTRING(" + kml + ")";
	}
	
	
	public static DtoGeometria kmltoGeometria(String kml) {
		
//		LINESTRING(0 0,1 1,2 2,3 3,4 4,5 5,6 6,7 7,8 8,9 9)
		
		DtoGeometria geom = new DtoGeometria();
		List<DtoPunto> puntos = new ArrayList<DtoPunto>();
		
		DtoPunto punto;
		
		String[] aux1;
		String aux2;
		String[] aux3;
		try {
			
			aux1 = kml.split("\\(");
			geom.setType(aux1[0]);
			aux2 = aux1[1].substring(0, (aux1[1].length() - 1) );
			aux1 = aux2.split(",");
			
			for (int x = 0; x < aux1.length; x ++ ) {
				aux3 = aux1[x].split(" ");
				
				punto = new DtoPunto();
				punto.setLat(Float.valueOf(aux3[0].trim()).floatValue());
				punto.setLng(Float.valueOf(aux3[1].trim()).floatValue());
				
				puntos.add(punto);
			}
			
			geom.setPuntos(puntos);
			
			return geom;
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
