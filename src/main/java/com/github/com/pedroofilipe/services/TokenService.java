package com.github.com.pedroofilipe.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.github.com.pedroofilipe.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private static final long expireTime = 180;
	private static final String key = "AEOAPEAPOKAKCNVXCNV,MLIDUOSIDUFIOSDUOP34JJ3434!3334$%%%";
	
	public static String generateToken(Usuario usuario) {
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setSubject("tipoUsuario", usuario.getTipoUsuario().getDescricao())
				.setSubject("tipoUsuario")
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

}
