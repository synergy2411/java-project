package com.sk.security_demo.jwt;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomJwtConverter implements Converter<Jwt, CustomJwt> {
    @Override
    public CustomJwt convert(Jwt source) {
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<GrantedAuthority> grantedAuthorityList = extractAuthorities(source);
        CustomJwt customJwt = new CustomJwt(source, grantedAuthorityList);
        customJwt.setFirstName(source.getClaimAsString("given_name"));
        customJwt.setLastName(source.getClaimAsString("family_name"));
        customJwt.setEmail(source.getClaimAsString("email"));
        customJwt.setRoles(grantedAuthorityList);
        return customJwt;
    }

    private List<GrantedAuthority> extractAuthorities(Jwt source) {
        var result = new ArrayList<GrantedAuthority>();
        Map<String, Object> realmRoles = source.getClaimAsMap("realm_access");
        if(realmRoles != null && realmRoles.get("roles") != null) {
            var roles = realmRoles.get("roles");
            if(roles instanceof List l){
                l.forEach(role -> {
                System.out.println("----THE ROLE -----" + role);
                    result.add(new SimpleGrantedAuthority("ROLE_"+role));
                });
            }
        }
        return result;
    }
}
