package io.github.tlobato.mscards.application;

import io.github.tlobato.mscards.domain.ClientCard;
import io.github.tlobato.mscards.infra.repository.ClientCardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return clientCardRepository.findByCpf(cpf);
    }

}