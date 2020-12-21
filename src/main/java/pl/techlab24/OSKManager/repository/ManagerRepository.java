package pl.techlab24.OSKManager.repository;

import pl.techlab24.OSKManager.model.Manager;

import javax.transaction.Transactional;

@Transactional
public interface ManagerRepository extends UserBaseRepository<Manager> {
}
