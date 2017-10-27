package com.guanshan.phoenix.authentication.security;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Justin on 2017/6/3.
 */

@Service
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${token.tokenHeader}")
    private String tokenHeader;

    @Value("${token.tokenHead}")
    private String tokenHead;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String authHeader = request.getHeader(this.tokenHeader);
            if (authHeader != null && authHeader.startsWith(tokenHead)) {
                final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
                String username = jwtTokenUtil.getUsernameFromToken(authToken);

                logger.info("checking authentication " + username);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {   //??? rain

                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                        logger.info("authenticated user " + username + ", setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e){
            Utility.writeError(request, response, HttpStatus.OK, ErrorCode.TokenExpired);
        } catch (SignatureException e){
            Utility.writeError(request, response, HttpStatus.OK, ErrorCode.NeedAuthentication);
        } catch (MalformedJwtException e){
            Utility.writeError(request, response, HttpStatus.OK, ErrorCode.NeedAuthentication);
        } catch (UsernameNotFoundException e){
            Utility.writeError(request, response, HttpStatus.OK, ErrorCode.NeedAuthentication);
        }
    }
}
