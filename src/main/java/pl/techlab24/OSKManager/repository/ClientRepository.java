package pl.techlab24.OSKManager.repository;

import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.Client;

@Repository
public interface ClientRepository extends UserBaseRepository<Client> {
}
