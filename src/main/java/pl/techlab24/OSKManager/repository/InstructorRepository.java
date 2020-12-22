package pl.techlab24.OSKManager.repository;

import javax.transaction.Transactional;

import pl.techlab24.OSKManager.model.Instructor;

@Transactional
public interface InstructorRepository extends UserBaseRepository<Instructor> {
}
