package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		String hql = "FROM Participant";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

//	public Participant findByLogin(String login) {
//		String hql = "FROM Participant where login= '" + login + "'";
//		Query query = connector.getSession().createQuery(hql);
//		return (Participant) query.getSingleResult();
//	}
	public Participant findByLogin(String login) {
//		String hql = "FROM Participant where login= :login";
//		Query query = connector.getSession().createQuery(hql);
//		query.setParameter("login",login);
//		return (Participant) query.getSingleResult();

		return connector.getSession().get(Participant.class, login);
	}

	public Participant addParticipant(Participant participant){
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
		return participant;
	}

	public void deleteParticipant(Participant participant){
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().remove(participant);
		transaction.commit();
	}

	public void updateParticipant(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().update(participant);
		transaction.commit();
	}
}
