    package com.djoly.hello.RESTJWT.controller;


    import com.djoly.hello.RESTJWT.config.JwtTokenUtil;
    import com.djoly.hello.RESTJWT.model.JwtRequest;
    import com.djoly.hello.RESTJWT.model.JwtResponse;
    import com.djoly.hello.RESTJWT.service.JwtUserDetailsService;
    import net.bytebuddy.implementation.bind.MethodDelegationBinder;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.BadCredentialsException;
    import org.springframework.security.authentication.DisabledException;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @CrossOrigin
    public class JwtAuthenticationController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        @Autowired
        private JwtUserDetailsService jwtUserDetailsService;

        @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
        public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws  Exception {
            System.out.println("authenticate " + authenticationRequest.getUsername()+" "+authenticationRequest.getPassword());
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = jwtUserDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            System.out.println("UserDetails " +userDetails );

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        }

        private void authenticate(String username, String pasword) throws Exception {
            try{
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pasword));

                System.out.println("authentication " + authentication);
            } catch (DisabledException e) {
                System.out.println(e.getMessage());
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
                System.out.println(e.getMessage());
            throw new Exception("INVALID_CREDENTIALS", e);
        }
//            catch (Exception e) {
//                System.out.println(e.getMessage());
//
//            }

        }

    }
