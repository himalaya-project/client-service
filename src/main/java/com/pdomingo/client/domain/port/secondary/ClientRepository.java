package com.pdomingo.client.domain.port.secondary;

import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdoming.kernel.core.ddd.Repository;

public interface ClientRepository extends Repository<Client, Client.Id> {

	Client create(ClientSpec clientSpec);

	Client update(Client updatedClient);

	void delete(Client.Id clientId);
}
