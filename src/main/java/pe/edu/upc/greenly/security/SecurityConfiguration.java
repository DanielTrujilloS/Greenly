package pe.edu.upc.greenly.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.edu.upc.greenly.entities.Authority;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST ={
            // -- Swagger
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",

            // -- Login
            "/Greenly/usuarios/login/**",

            // -- Registro
            "/Greenly/usuarios/register/**",
            "/Greenly/usuarios/register"
    };

    // Authentication Manager
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Security Filter Chain

    /*

    1. Cuales van a ser los Request(pedidos) que seran evaluados para saber si el usuario tiene permisos sobre estos
        a. AnyRequest -> Todos los pedidos
        b. RequestMatchers -> Se evalua solo los que coincidan con las rutas
        c. RequestMatches + HttpMethod -> Se evalua a los que coincidan con la ruta y con el metodo (GET, POST, etc.)

    2. Cual es la regla de autorización que se van a aplicar sobre estos Request(pedidos)
        a. permitAll()
        b. denyAll()
        c. requestMatchers()
        d. hasRole()
        e. hayAnyRole()
        f. hasAuthority()
        g. hasAnyAuthority()
        h. SpEL Spring Expression Language
        i. authenticated()

    */

    @Autowired
    //private pe.edu.upc.greenly.security.JwtRequestFilter jwtRequestFilter;
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.addFilterBefore( jwtRequestFilter , UsernamePasswordAuthenticationFilter.class );


        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());

        http.authorizeHttpRequests( (auth) -> auth
                    //Permite todos
                //.anyRequest().permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()


                //Poner una cadena de filtros iniciando por el más especifico hacia el más genérico
                /*"ROLE_ADMIN", "ROLE_DONANTE", "ROLE_ONGADMIN"*/
                .requestMatchers(HttpMethod.GET, "/Greenly/campañas/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/campañas/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/campañas/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/campañas/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/ongs/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/ongs/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/ongs/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/ongs/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/comentarios/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/comentarios/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/comentarios/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/comentarios/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/campañasFavoritas/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/campañasFavoritas/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/campañasFavoritas/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/campañasFavoritas/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/donaciones/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN","ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/donaciones/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/donaciones/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/donaciones/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/donantes/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/donantes/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/donantes/**").hasAnyAuthority("ROLE_DONANTE", "ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/donantes/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/estadoDonaciones/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN","ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/estadoDonaciones/**").hasAnyAuthority("ROLE_ONGADMIN","ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/estadoDonaciones/**").hasAnyAuthority("ROLE_ONGADMIN","ROLE_ADMIN")
                .requestMatchers("/Greenly/estadoDonaciones/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/posts/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/posts/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/posts/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/posts/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/tipoDonaciones/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/tipoDonaciones/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/tipoDonaciones/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/tipoDonaciones/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/ubicaciones/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/ubicaciones/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/ubicaciones/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/ubicaciones/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/Greenly/usuarios/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Greenly/usuarios/**").hasAnyAuthority("ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/Greenly/usuarios/**").hasAnyAuthority("ROLE_DONANTE","ROLE_ONGADMIN", "ROLE_ADMIN")
                .requestMatchers("/Greenly/usuarios/**").hasAnyAuthority("ROLE_ADMIN")



                .anyRequest().authenticated()

        );

        http.sessionManagement(
                (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }



    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
