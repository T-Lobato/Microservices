package io.github.tlobato.msclients.application;

import io.github.tlobato.msclients.domain.Client;
import io.github.tlobato.msclients.infra.repository.ClientRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public Optional<Client> getByCPF(String cpf){
        return clientRepository.findByCpf(cpf);
    }

}
