package com.example.hr_system.security;

import com.example.hr_system.service.auth.UserDetailServicelmpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailServicelmpl userDetailServicelmpl;

    public AuthTokenFilter() {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailServicelmpl = userDetailServicelmpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);

            if(jwt != null && jwtTokenProvider.validationToken(jwt)){
                String username = jwtTokenProvider.getUsername(jwt);

                UserDetails userDetails = userDetailServicelmpl.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        } catch (Exception e){
            System.out.println(e);
        }

        filterChain.doFilter(request, response);
    }


    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }

        return null;
    }
}
