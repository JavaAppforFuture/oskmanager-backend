package pl.techlab24.OSKManager.repository;

import javax.transaction.Transactional;

import pl.techlab24.OSKManager.model.Manager;

@Transactional
public interface ManagerRepository extends UserBaseRepository<Manager> {
}
