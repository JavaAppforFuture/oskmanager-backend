package pl.techlab24.OSKManager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import generators.OfficeGenerator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.NonTransientDataAccessException;
import pl.techlab24.OSKManager.exceptions.ServiceOperationException;
import pl.techlab24.OSKManager.model.Office;
import pl.techlab24.OSKManager.repository.OfficeRepository;

@ExtendWith(MockitoExtension.class)
class OfficeServiceTest {

    @Mock
    OfficeRepository repository;

    @InjectMocks
    OfficeService service;

    @Test
    void shouldAddOffice() throws ServiceOperationException {
        // given
        Office officeToAdd = OfficeGenerator.getRandomOffice();
        Office addedOffice = OfficeGenerator.getRandomOffice();
        when(repository.existsById(officeToAdd.getId())).thenReturn(false);
        when(repository.save(officeToAdd)).thenReturn(addedOffice);

        // when
        Office result = service.add(officeToAdd);

        // then
        assertEquals(addedOffice, result);
        verify(repository).existsById(officeToAdd.getId());
        verify(repository).save(officeToAdd);
    }

    @Test
    void shouldAddOfficeWithNullId() throws ServiceOperationException {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        Office officeToAdd = office.toBuilder().id(null).build();
        Office addedOffice = OfficeGenerator.getRandomOffice();
        when(repository.save(officeToAdd)).thenReturn(addedOffice);

        // when
        Office result = service.add(officeToAdd);

        // then
        assertEquals(addedOffice, result);
        verify(repository).save(officeToAdd);
    }

    @Test
    void addMethodShouldThrowServiceOperationExceptionForNullOffice() {
        assertThrows(ServiceOperationException.class, () -> service.add(null));
        verify(repository, never()).existsById(any());
        verify(repository, never()).save(any());
    }

    @Test
    void addMethodShouldThrowExceptionForOfficeExistingInDatabase() {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.existsById(office.getId())).thenReturn(true);

        // then
        assertThrows(ServiceOperationException.class, () -> service.add(office));
        verify(repository).existsById(office.getId());
        verify(repository, never()).save(office);
    }

    @Test
    void addMethodShouldThrowExceptionWhenAnErrorOccursDuringAddingOfficeToDatabase() {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.existsById(office.getId())).thenReturn(false);
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).save(office);

        // then
        assertThrows(ServiceOperationException.class, () -> service.add(office));
        verify(repository).existsById(office.getId());
        verify(repository).save(office);
    }

    @Test
    void shouldUpdateOffice() throws ServiceOperationException {
        // given
        Office officeToUpdate = OfficeGenerator.getRandomOffice();
        Office updatedOffice = OfficeGenerator.getRandomOffice();
        when(repository.existsById(officeToUpdate.getId())).thenReturn(true);
        when(repository.save(officeToUpdate)).thenReturn(updatedOffice);

        // when
        Office result = service.update(officeToUpdate);

        // then
        assertEquals(updatedOffice, result);
        verify(repository).existsById(officeToUpdate.getId());
        verify(repository).save(officeToUpdate);
    }

    @Test
    void shouldUpdateOfficeWithNullId() throws ServiceOperationException {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        Office officeToUpdate = office.toBuilder().id(null).build();
        Office updatedOffice = OfficeGenerator.getRandomOffice();
        when(repository.save(officeToUpdate)).thenReturn(updatedOffice);

        // when
        Office result = service.update(officeToUpdate);

        // then
        assertEquals(updatedOffice, result);
        verify(repository).save(officeToUpdate);
    }

    @Test
    void updateMethodShouldThrowServiceOperationExceptionForNullOffice() {
        assertThrows(ServiceOperationException.class, () -> service.update(null));
        verify(repository, never()).existsById(any());
        verify(repository, never()).save(any());
    }

    @Test
    void updateMethodShouldThrowExceptionForOfficeExistingInDatabase() {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.existsById(office.getId())).thenReturn(false);

        // then
        assertThrows(ServiceOperationException.class, () -> service.update(office));
        verify(repository).existsById(office.getId());
        verify(repository, never()).save(office);
    }

    @Test
    void updateMethodShouldThrowExceptionWhenAnErrorOccursDuringUpdatingOffice() {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.existsById(office.getId())).thenReturn(true);
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).save(office);

        // then
        assertThrows(ServiceOperationException.class, () -> service.update(office));
        verify(repository).existsById(office.getId());
        verify(repository).save(office);
    }

    @Test
    void shouldGetAllOffices() throws ServiceOperationException {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.findAll()).thenReturn(Collections.singletonList(office));

        // when
        List result = service.getAll();

        // then
        assertEquals(Collections.singletonList(office), result);
        verify(repository).findAll();
    }

    @Test
    void getAllMethodShouldThrowExceptionWhenAnErrorOccursDuringGettingOffices() {
        // given
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).findAll();

        // then
        assertThrows(ServiceOperationException.class, () -> service.getAll());
        verify(repository).findAll();
    }

    @Test
    void shouldGetOfficeById() throws ServiceOperationException {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(office));

        // when
        Optional result = service.getOffice(office.getId());

        // then
        assertEquals(Optional.of(office), result);
        verify(repository).findById(office.getId());
    }

    @Test
    void getByIdMethodShouldThrowServiceOperationExceptionForNullId() {
        assertThrows(ServiceOperationException.class, () -> service.getOffice(null));
        verify(repository, never()).findById(any());
    }

    @Test
    void getByIdMethodShouldThrowExceptionWhenAnErrorOccursDuringGettingOffice() {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).findById(office.getId());

        // then
        assertThrows(ServiceOperationException.class, () -> service.getOffice(office.getId()));
        verify(repository).findById(office.getId());
    }

    @Test
    void shouldDeleteOfficeById() throws ServiceOperationException {
        // given
        Office office = OfficeGenerator.getRandomOffice();

        // when
        service.delete(office.getId());

        // then
        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void deleteByIdMethodShouldThrowServiceOperationExceptionForNullId() {
        assertThrows(ServiceOperationException.class, () -> service.delete(null));
        verify(repository, never()).findById(any());
    }

    @Test
    void deleteByIdMethodShouldThrowExceptionWhenAnErrorOccursDuringDeletingOffice() {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).deleteById(office.getId());

        // then
        assertThrows(ServiceOperationException.class, () -> service.delete(office.getId()));
        verify(repository).deleteById(office.getId());
    }

    @Test
    void shouldDeleteAllOffices() throws ServiceOperationException {
        // when
        service.deleteAll();

        // then
        verify(repository, times(1)).deleteAll();
    }

    @Test
    void deleteAllMethodShouldThrowExceptionWhenAnErrorOccursDuringDeletingOffices() {
        // given
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).deleteAll();

        // then
        assertThrows(ServiceOperationException.class, () -> service.deleteAll());
        verify(repository).deleteAll();
    }

    @Test
    void shouldCountOffices() throws ServiceOperationException {
        // given
        when(repository.count()).thenReturn(1L);

        // when
        Long count = service.count();

        // then
        verify(repository, times(1)).count();
        assertEquals(count, 1L);
    }

    @Test
    void countMethodShouldThrowExceptionWhenAnErrorOccursDuringCountingOffices() {
        // given
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).count();

        // then
        assertThrows(ServiceOperationException.class, () -> service.count());
        verify(repository).count();
    }

    @Test
    void shouldCheckOfficeExists() throws ServiceOperationException {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.existsById(any())).thenReturn(true);

        // when
        boolean exists = service.exists(office.getId());

        // then
        assertTrue(exists);
    }

    @Test
    void shouldCheckOfficeNotExists() throws ServiceOperationException {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        when(repository.existsById(any())).thenReturn(false);

        // when
        boolean exists = service.exists(office.getId());

        // then
        assertFalse(exists);
    }

    @Test
    void existsMethodShouldThrowServiceOperationExceptionForNullId() {
        assertThrows(ServiceOperationException.class, () -> service.exists(null));
        verify(repository, never()).findById(any());
    }

    @Test
    void existsMethodShouldThrowExceptionWhenAnErrorOccursDuringCheckingOfficeExistence() {
        // given
        Office office = OfficeGenerator.getRandomOffice();
        doThrow(new NonTransientDataAccessException("") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(repository).existsById(office.getId());

        // then
        assertThrows(ServiceOperationException.class, () -> service.exists(office.getId()));
        verify(repository).existsById(office.getId());
    }
}