package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertEquals(universite, result);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        Departement departement1 = new Departement(1, "Departement 1");
        Departement departement2 = new Departement(2, "Departement 2");
        Set<Departement> departements = new HashSet<>();
        departements.add(departement1);
        departements.add(departement2);
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);

        assertEquals(departements, result);
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = Arrays.asList(
                new Universite(1, "Universite 1"),
                new Universite(2, "Universite 2")
        );
        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(universites, result);
    }

    @Test
    void testUpdateUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);

        assertEquals(universite, result);
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1);

        assertEquals(universite, result);
    }

    @Test
    void testDeleteUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(1);

        verify(universiteRepository, times(1)).delete(universite);
    }
    @Test
    void testAssignUniversiteToDepartement() {
        Universite universite = new Universite(1, "Universite 1");
        Departement departement = new Departement(1, "Departement 1");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 1);

        assertTrue(universite.getDepartements().contains(departement));
    }

}
