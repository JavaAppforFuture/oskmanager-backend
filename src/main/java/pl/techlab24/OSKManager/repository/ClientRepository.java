package pl.techlab24.OSKManager.repository;

import javax.transaction.Transactional;

import pl.techlab24.OSKManager.model.Client;

@Transactional
public interface ClientRepository extends UserBaseRepository<Client> {
}
