package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.entities.Authority;
import pe.edu.upc.greenly.repositories.AuthorityRepository;
import pe.edu.upc.greenly.services.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findByName(String authorityName) {
        return authorityRepository.findByName(authorityName);
    }
}
