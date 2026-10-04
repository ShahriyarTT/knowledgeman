package com.virtusbellatoris.knowledgeman.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter { // guarantees your filter runs exactly once per request — not twice, not zero times

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    public JWTFilter(JWTService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService; // you need to load the actual user from the database
    }

    // This runs for every request
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // STEP 1: Get the Authorization header. Every HTTP request has headers — key-value pairs of metadata.
        // The Authorization header is where clients send their JWT token. Format is always: Bearer eyJhbGci...
        String authHeader = request.getHeader("Authorization");

        // STEP 2: Two cases where we skip JWT processing:
        // No Authorization header at all — public request: endpoint like GET /api/tags, no token
        // Header doesn't start with "Bearer " — wrong format
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // In both cases this passes the request to the next filter in the chain.
            // We don't block it here. Spring Security will block it later if the endpoint requires authentication.
            return; // stop executing this filter, exits the method immediately — no point continuing if there's no token.
        }

        // STEP 3: Extract just the token part — remove "Bearer " (7 characters)
        String token = authHeader.substring(7);

        // STEP 4: Extract the email from the token
        String email = jwtService.extractEmail(token);

        // STEP 5: Check if email was extracted and user is not already authenticated
        // SecurityContextHolder.getContext().getAuthentication() — checks if Spring Security
        // already knows who this user is from a previous filter. If already set — skip.
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // STEP 5.1: Load user from database using the email
            // UserDetailsService goes to your UserRepository, finds & returns a UserDetails object Spring Security understands
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            // UserDetails is Spring Security's representation of a user — contains username, password, and roles.

            // STEP 5.2: Validate the token, Check it's not expired, not malformed, signature matches
            if (jwtService.isTokenValid(token)) {

                // STEP 6.1: Creates an Authentication object — Spring Security's way of representing
                // "this request belongs to this authenticated user." Three parts:
                // 1. userDetails — who the user is
                // 2. null — credentials (password), not needed here, token already verified
                // 3. userDetails.getAuthorities() — their roles (ADMIN, REGULAR)
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );

                // STEP 6.2: Add request details to the authentication object
                // Adds extra request info to the authentication from the HTTP request — IP address, session details.
                // Useful for security logging. Not strictly required but good practice.
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // STEP 6.3: This is the most important line. Puts the authentication into SecurityContext
                // Spring Security's memory for the current request. After this line, Spring Security knows:
                //Who is making this request: "This request belongs to this authenticated user"
                //What their roles are
                // From this point, Spring Security allows the request through
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // STEP 7: Continue the filter chain
        // Every filter must pass the request to the next filter when done.
        // This is mandatory — without it the request never reaches your controller.
        filterChain.doFilter(request, response);

    }

    /* UserDetailsService: is the bridge between your database and Spring Security.
        When a request comes in with a JWT token:
            Your JwtFilter extracts the email from the token
            Calls userDetailsService.loadUserByUsername(email)
            Spring Security gets a UserDetails object back
            Uses it to verify the user exists and check their role

     */

    /* SecurityContext: After this line, Spring Security knows the request is authenticated and
        allows it through to the controller.
        If you never set this — Spring Security treats every request as anonymous and blocks protected endpoints.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    */

}
