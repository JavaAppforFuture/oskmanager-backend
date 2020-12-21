package pl.techlab24.OSKManager.repository;

import pl.techlab24.OSKManager.model.Instructor;

import javax.transaction.Transactional;

@Transactional
public interface InstructorRepository extends UserBaseRepository<Instructor> {
}
