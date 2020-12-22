package pl.techlab24.OSKManager.repository;

import javax.transaction.Transactional;

import pl.techlab24.OSKManager.model.Office;

@Transactional
public interface OfficeRepository extends UserBaseRepository<Office> {
}
