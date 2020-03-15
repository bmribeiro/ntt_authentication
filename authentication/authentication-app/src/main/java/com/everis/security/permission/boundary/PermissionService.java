package com.everis.security.permission.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.everis.security.permission.entity.Permission;
import com.everis.security.user.exceptions.UserNotFoundException;

@Stateless
public class PermissionService {
    
    @PersistenceContext
    private EntityManager em;

    public Permission getById(long id){
        Permission permission = em.find(Permission.class, id);
        if (permission == null ){
            throw new UserNotFoundException();
        }
        return permission;
    }

    public Permission deleteById(long id){
        Permission permissionToRemove = this.getById(id);
        this.em.remove(permissionToRemove);
        return permissionToRemove;
    }

    public Permission create(Permission permission){
        this.em.persist(permission);
        this.em.flush();
        this.em.refresh(permission);
        return permission;
    }

    public Permission update(Permission permission){
        Permission permissionToUpdate = this.getById(permission.getId());
        permissionToUpdate.setName(permission.getName());
        return permissionToUpdate;
    }
}