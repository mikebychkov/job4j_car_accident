package com.accident.store;

import com.accident.control.AccidentControl;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.accident.model.Accident;

import java.util.List;

@Repository
public class AccidentHibernate {

    private static final Logger logger = LogManager.getLogger(AccidentHibernate.class);

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        logger.debug("\n\n\nSAVING ACCIDENT: " + accident);
        logger.debug("\n\n\n");

        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("\n\n\n");
            logger.error(e.getMessage());
            logger.debug("\n\n\n");
        }
        return accident;
    }

    public Accident update(Accident accident) {
        logger.debug("\n\n\nUPDATING ACCIDENT: " + accident);
        logger.debug("\n\n\n");

        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(accident);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("\n\n\n");
            logger.error(e.getMessage());
            logger.debug("\n\n\n");
        }
        return accident;
    }

    public List<Accident> getAccidents() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    public Accident getAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }

    public List<AccidentType> getAccidentTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public AccidentType getAccidentTypeById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(AccidentType.class, id);
        }
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public Rule getRuleById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }
}
