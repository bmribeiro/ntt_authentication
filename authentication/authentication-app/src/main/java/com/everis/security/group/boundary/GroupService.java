package com.everis.security.group.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.everis.security.group.entity.Group;
import com.everis.security.user.exceptions.UserNotFoundException;
import com.everis.security.user.exceptions.UserStateNotValid;

@Stateless
public class GroupService {
    
    @PersistenceContext
    private EntityManager em;

    public Group getById(long id){
        Group group = em.find(Group.class, id);
        if (group == null ){
            throw new UserNotFoundException();
        }
        return group;
    }

    public Group deleteById(long id){
        Group groupToRemove = this.getById(id);
        this.em.remove(groupToRemove);
        return groupToRemove;
    }

    public Group create(Group group){       
        try{            
            this.em.persist(group);
            this.em.flush();
            this.em.refresh(group);
        } catch (IllegalStateException exception){
            throw new UserStateNotValid("Permission wasn't found");
        }
        return group;
    }

    public Group update(Group group){
        Group groupToUpdate = this.getById(group.getId());
        groupToUpdate.setName(group.getName());
        return groupToUpdate;
    }
}