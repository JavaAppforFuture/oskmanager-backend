package pl.techlab24.OSKManager.service;

import java.util.List;
import java.util.Optional;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.exceptions.ServiceOperationException;
import pl.techlab24.OSKManager.model.Office;
import pl.techlab24.OSKManager.repository.OfficeRepository;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List getAll() throws ServiceOperationException {
        try {
            return officeRepository.findAll();
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during getting Offices", e);
        }
    }

    public Optional getOffice(Long id) throws ServiceOperationException {
        if (id == null) {
            throw new ServiceOperationException("Id cannot be null.");
        }
        try {
            return officeRepository.findById(id);
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during getting Office", e);
        }
    }

    public Office add(Office office) throws ServiceOperationException {
        if (office == null) {
            throw new ServiceOperationException("Office cannot be null.");
        }
        Long id = office.getId();
        if (id != null && officeRepository.existsById(id)) {
            throw new ServiceOperationException("Office already exists in database.");
        }
        try {
            return officeRepository.save(office);
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during adding Office.", e);
        }
    }

    public Office update(Office office) throws ServiceOperationException {
        if (office == null) {
            throw new ServiceOperationException("Office cannot be null.");
        }
        Long id = office.getId();
        if (id != null && !officeRepository.existsById(id)) {
            throw new ServiceOperationException("Office does not exist in database.");
        }
        try {
            return officeRepository.save(office);
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during updating Office.", e);
        }
    }

    public void delete(Long id) throws ServiceOperationException {
        if (id == null) {
            throw new ServiceOperationException("Id cannot be null.");
        }
        try {
            officeRepository.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during deleting Office.", e);
        }
    }

    public void deleteAll() throws ServiceOperationException {
        try {
            officeRepository.deleteAll();
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during deleting Offices.", e);
        }
    }

    public long count() throws ServiceOperationException {
        try {
            return officeRepository.count();
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during counting Offices.", e);
        }
    }

    public boolean exists(Long id) throws ServiceOperationException {
        if (id == null) {
            throw new ServiceOperationException("Id cannot be null.");
        }
        try {
            return officeRepository.existsById(id);
        } catch (NonTransientDataAccessException e) {
            throw new ServiceOperationException("An error occurred during checking Office.", e);
        }
    }
}

