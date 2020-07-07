package com.pdomingo.client.domain.adapter.usecase;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.port.primary.FetchClientProfilePort;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import com.pdomingo.client.infrastructure.web.mapper.ClientApiMapper;
import com.pdomingo.client.infrastructure.web.model.ClientView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class FetchClientProfileAdapter implements FetchClientProfilePort {

    private final ClientRepository clientRepository;

    @Override
    public ClientView fetch(ClientId clientId) {
        Objects.requireNonNull(clientId);

        log.info("Proceeding to lookup client<{}>", clientId);

        return clientRepository.findById(clientId)
                .map(ClientApiMapper::map)
                .orElseThrow(
                // TODO add better exception
        );
    }
}
