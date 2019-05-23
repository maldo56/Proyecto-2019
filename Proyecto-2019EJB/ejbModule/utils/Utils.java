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

	public static String geometriaToKml(DtoGeometria geometry) {
		String kml = "";
		
		kml = geometry.getType();
		
		if ( geometry.getPuntos() == null || geometry.getPuntos().size() == 0 ) {
			kml = kml + "()";
		} else {
			
			kml = kml + "((" + geometry.getPuntos().get(0).getLat() + " " + geometry.getPuntos().get(0).getLng();
			
			for ( int x = 1; x < geometry.getPuntos().size(); x ++ ) {
				kml = kml + "," + geometry.getPuntos().get(x).getLat() + " " + geometry.getPuntos().get(x).getLng();
			}
			
			kml = kml + "))";
		}
		
		return kml;
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
	
	public static DtoGeometria kmlMultiLinestringToGeometryPolygon(String kml) {
		
		System.out.println("KML: " + kml);
		
//		 POLYGON((0 0,0 1,1 1,1 0,0 0))
		
//		MULTILINESTRING((-34.898994 -56.168793,-34.898697 -56.167763),(0 0,1 1),(1 1,2 2),(2 2,3 3),(3 3,4 4),(4 4,5 5),(5 5,6 6),(6 6,7 7),(7 7,8 8),(8 8,9 9))
		String aux = "";
		String[] auxPoints;
		
		String[] auxcoord;
		DtoGeometria geom = new DtoGeometria();
		
		List<DtoPunto> puntos = new ArrayList<DtoPunto>();
		DtoPunto punto;
		
		int index = 0;
		
		try {
			index = kml.indexOf("(");
			
			System.out.println(index);
			
			geom.setType(kml.substring(0, index));
			
			aux = kml.substring(index, kml.length()); //no va index en lugar de 15? dale gas
			
			aux = aux.replace("(", "");
			aux = aux.replace(")", "");
			
			auxPoints = aux.split(",");
			
			for ( int x = 0; x < auxPoints.length; x ++ ) {
				
				System.out.println(auxPoints[x]);
				
				auxcoord = auxPoints[x].split(" ");
				
				punto = new DtoPunto();
				punto.setLat(Float.parseFloat(auxcoord[0]));
				punto.setLng(Float.parseFloat(auxcoord[1]));
				
				puntos.add(punto);
			}
			
			geom.setPuntos(puntos);
			
			return geom;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
		System.out.println("retorna null");
		return null;
	}
}
