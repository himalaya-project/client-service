package com.pdomingo.client.domain.port.secondary;

import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.kernel.core.ddd.Repository;

public interface ClientRepository extends Repository<Client, ClientId> {

	Client create(ClientSpec clientSpec);

	Client update(Client updatedClient);

	void delete(ClientId clientId);
}
