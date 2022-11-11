package de.fhws.business.rooms.control;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import de.fhws.business.rooms.entity.Building;
import de.fhws.business.rooms.entity.BuildingEntity;
import de.fhws.business.rooms.entity.ChangeLogEntity;

public class BuildingService {

	@PersistenceContext
	EntityManager em;

	@Transactional(Transactional.TxType.REQUIRED)
	public BuildingEntity addBuilding(BuildingEntity building) {
		ChangeLogEntity cl = new ChangeLogEntity();
		cl.setComment("New Building");
		cl.setUpdatedBy("fhws");
		building.getChangeLog().add(cl);
		
		return em.merge(building);
	}

	public Building getBuiling(String name) {
		return getBuilingEntity(name).toDTO();
	}
	
	public BuildingEntity getBuilingEntity(String name) {
		return em.createNamedQuery(BuildingEntity.QUERY_BY_NAME, BuildingEntity.class)
				.setParameter(BuildingEntity.PARAM_NAME, name)
				.getSingleResult();
	}
	
	

	public Building getBuilingNullCheckProgrammed(String name) {
		List<BuildingEntity> buildingEntities = em
				.createQuery("SELECT b FROM BuildingEntity b WHERE b.name = :name", BuildingEntity.class)
				.setParameter("name", name).getResultList();
		if (buildingEntities.isEmpty())
			return null;
		return buildingEntities.get(0).toDTO();
	}

}
