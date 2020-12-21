package pl.techlab24.OSKManager.repository;

import pl.techlab24.OSKManager.model.Client;

import javax.transaction.Transactional;

@Transactional
public interface ClientRepository extends UserBaseRepository<Client> {
}
