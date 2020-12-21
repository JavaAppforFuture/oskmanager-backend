package pl.techlab24.OSKManager.repository;

import pl.techlab24.OSKManager.model.Office;

import javax.transaction.Transactional;

@Transactional
public interface OfficeRepository extends UserBaseRepository<Office> {
}
