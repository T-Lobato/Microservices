package io.github.tlobato.mscards.infra.repository;

import ch.qos.logback.core.net.server.Client;
import io.github.tlobato.mscards.domain.ClientCard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
    List<ClientCard> findByCpf(String cpf);
}