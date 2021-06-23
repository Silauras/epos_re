package khai.edu.epos_re.security.filters;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        if (authenticationToken == null){
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (StringUtils.hasText(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
                Jws<Claims> parsedToken = Jwts.parserBuilder()
                        .setSigningKey(signingKey)
                        .build()
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

                String username = parsedToken.getBody().getSubject();

                List<SimpleGrantedAuthority> permissions = ((List<?>) parsedToken.getBody().get("permissions"))
                                .stream().map(o -> new SimpleGrantedAuthority(o.toString())).collect(Collectors.toList());

                String userId = parsedToken.getBody().getId();

                if (StringUtils.hasText(username)) {
                    return new UsernamePasswordAuthenticationToken(UUID.fromString(userId), null, permissions);
                }
            }catch (SignatureException exception) {
                log.warn("JWT signature does not match locally computed signature : {} failed : {}", token, exception.getMessage());
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
            }
        }
        return null;
    }
}
