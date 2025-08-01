package pe.edu.upc.greenly.services;

import pe.edu.upc.greenly.entities.Authority;

public interface AuthorityService {
    public Authority addAuthority(Authority authority);

    public Authority findByName(String authorityName);
}
