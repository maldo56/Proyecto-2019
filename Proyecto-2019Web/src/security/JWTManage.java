package security;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import obj.dto.DtoAdmin;
import obj.dto.DtoClient;
import obj.dto.DtoUsuario;

public class JWTManage {
	
	private static String SECRET_KEY = "UHVUVFktVXNlci1LZXktRmlsZS0yOiBzc2gtcnNhDQpFbmNyeXB0aW9uOiBhZXMyNTYtY2JjDQpDb21tZW50OiByc2Eta2V5LTIwMTkwNTMwDQpQdWJsaWMtTGluZXM6IDYNCkFBQUFCM056YUMxeWMyRUFBQUFCSlFBQUFRRUF0aFF4Si9QZ1RwN0lxM1lrTGlrdTlyWDZ4R0d5MXlhd3BIKzYNCnBXd2cvZEU5ZENXbHR4RXFZa0N4cUxNcStvVnRUQUFnbXlEKzdEM0QyeTdtZ3RERFZ2QXFLa3FPSUZ4blVQNnMNCkMzaU1mdC9FV1hhZFFyMmlud0FyRjVESytpVjZWaGNTSWErQjdiOVZ3WGtHa3g1YUhYZjFjdXRMZE5wTFJMaHQNCm9pdVlPVjd1bnE2MlZMb3hCcUo5ZEJscGdlc0laZHZ4VXE3NlhSOGZCUCtVbW9UVDlqQ250elpjelZjTWUrRWYNCnN6NEYwVmNaZWloSUZGU1Y3TVF1cW8rRzMrQ040MjN0d1UrdkVLV0FoR01EL2pKVVRXUFJLU25HbklSSHdiMDQNCnNsOWJlZnkwMitId3NDZ25GUWpDQ0dOQ3lUNStCLzRpcHhlb0RNWGwyM09Fb0NTd1J3PT0NClByaXZhdGUtTGluZXM6IDE0DQorVFJ5VTRCODFMVC9qZld6Um5lQ0ZsTXhndlRScjd5TDVNWlZLZnNwSm5CZ25HVVg3Sis1SlE5T1dJejRCdTNFDQpWbkwxNFVtZnkzNEp3cnYxanRlTzlIa1pUQ0t0eURaUHJVWUpDMEtpcFp5V0liQ3d6YzU3aTBlQ1lldURwUlA3DQpXSFpUM3VPejcwd2s0Zm1oQnY1ay9Wdy9GRDA1T2ZURFE4RUNrc3lRK1lSZGRLU0lqRkV0ZFpub241d3RpdHU0DQpVK0V0T3JyZzRjbnZhSFMyZy8zcVNKWVZNZ1orajcvUjdVclE1ZzIvL3hDQXA5bFV6ODRSSTBRTDNvS1VCbC9UDQpZajhrbzA1b3pWMFhCVEQ1MTJ5T1p2eEo0ZVNtbUc5QjRYWTh5S0loSEJBd0RiemppQ2dMbDdYZWhFNWg0ZDVrDQpncTV0Rjk4b1dYTGxkb2U1ckloZE5pdDVKeWh1cGJyTHVEN1dqTEJLdnJ2ckJwaFRvZkNNUnBjOGRPTDFoN0ZhDQpYOUhNZXhqdTZwZFhBNmJBdUxFeWVaRk9EbGpBNnFBdXNSM1AyeEQzSnFMazBFZS94NFVWUloyRVRlTS9nNTVHDQpsUnlVZnowbjBSbGVDQm1NU21jYnBnWm9mNzVIa0RnaWhlKzlzVTEvd0h5eVRYUExRLytGZDNGQ3AwOTkwVzlVDQoyWWh1SVVTUUtFaGFlMVM5dG5KRDdCRGJsOXp4RTBLK2g5TlpBRHNLMTJCOENNUG91UVN5SWhmQVNUTlRMckYvDQpNcFpCVXlqSXcyV0tzMWZXODhrdjVVbHR5eHJhUkpBT2ZVRE90UFAwUjFCaHNpSGNGWmF1SFNQeEM1d0I3dksrDQpaVGdGY2JIVmJ2akRVVC9nYWd6V2dkbm9nc1pKK0xxV1NUQ2NjN3BPV01zVXdZQzhCRGpEbGlIb0NKNk1xaXhDDQpxbVhaMU5EajJlRzFCVGt5YnBXc3l6QUdJS0Q3bGxERWI1NzYyZEpLdzM3ajBubkdVYkJYdjBqVDVFZXZrb0trDQp5cE5RaTNpRXlrbDRUYTZmUTFIZStMMjhFZitmdkUrYzQvMUxXM1Uxa3FhZ25tZk9NWU1NQ0wrYmp4ajZSakhLDQpoazltZ2JUM0lQU1FWZ2QxVFNIODBPUXJuYzhFL20xQkJQTlphWUgzUGFDaVEzVEhFdDhWMW9rb3BxVG5SRjAvDQpQcml2YXRlLU1BQzogMWJlY2I5NGQ0OGQ2ZTIxMjA2MWI4ZWViYjIwY2M4YTc0OTZiZTJhMQ0K";

//	private static String SECRET_KEY = "AAAAB3NzaC1yc2EAAAABJQAAAQBWi9+Ye7avkdAfkb7xL8FXsysJGCea8fDt27mvG9JRzhM2jjp4V4023qsvWlphyDEJ1vN3WapRsnhGyvB5PyoNahW8llvchoHs9eKpKsjM8l9H5nm/kbmpCLZ9WKRHvy1rKfCWi0lyfk+7GCubsXfcMSyn0w0YSkuv6GxxUS6QitPbf8EXkpdgDhDnAfyFFDs89s0uGja6fu1zhjVhur/J7Z27lzTbSwJskJJwpLU509V13i+EtsxvrzKfwTxO7JXqZoi3CO3tHy2+JErP91pMTz8uVvY9IPOk+a0HSO/q7nkVdc7CTw2Q7JoYfe2JScn3sp41NlNYsI1JD9mXCIa7 rsa-key-20190530";
	
	
	public static String createJWT(DtoUsuario usuario) {
		
//		String type = "";
//		if ( usuario instanceof DtoAdmin ) {
//			type = "admin";
//		} else {
//			type = "client";
//		}

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        UUID uuid = UUID.randomUUID();
        
        JwtBuilder builder = Jwts.builder().setId(uuid.toString())
                .setIssuedAt(now)
//                .setPayload(type)
                .setSubject(usuario.getUsername())
                .signWith(signatureAlgorithm, signingKey);

        if (300000 >= 0) {
            long expMillis = nowMillis + 300000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

	public static void decodeJWT(String jwt) {

//		String[] parts = jwt.split(".");
//		String payload = StringUtils.newStringUtf8(Base64.decodeBase64(parts[1]));
//		
//		
//		
//		System.out.println("Payload:  " + payload);
		
		
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();

        

//        return claims;
    }
	
}
