package academy.learnprogramming.service;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * 
 * @author jose casal
 */
public class CDIProducer {

    @Produces
    @PersistenceContext
    EntityManager entityManager;
}
