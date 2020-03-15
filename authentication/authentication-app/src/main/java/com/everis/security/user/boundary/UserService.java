package com.everis.security.user.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.everis.security.group.entity.Group;
import com.everis.security.permission.entity.Permission;
import com.everis.security.user.entity.User;
import com.everis.security.user.exceptions.UserNotFoundException;
import com.everis.security.user.exceptions.UserStateNotValid;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public User getById(long id){
        User user = em.find(User.class, id);
        if (user == null ){
            throw new UserNotFoundException();
        }
        return user;
    }

    public User deleteById(long id){
        User userToRemove = this.getById(id);
        this.em.remove(userToRemove);
        return userToRemove;
    }

    public User createUser(User user){
        try{            
            this.em.persist(user);
            this.em.flush();
            this.em.refresh(user);
        } catch (IllegalStateException exception){
            throw new UserStateNotValid("Group or Permission wasn't found");
        }
        return user;
    }

    public User updateUser(User user){
        User userToUpdate = this.getById(user.getId());
        userToUpdate.update(user);
        return userToUpdate;
    }

    public User getUserByUsername(String username){
        Query query = this.em.createNamedQuery(User.NAMED_QUERY_FIND_BY_USERNAME);
        query.setParameter("username", username);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException exception){
            throw new UserNotFoundException();
        }
    }

    public List<User> getUsers(String groupName, List<String> permissionNames, int pageSize, int pageOffset){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> fromUser = query.from(User.class);

        final Path<String> pathGroupName = fromUser.get("group").get("name");
        final Path<String> pathPermissionName = fromUser.get("group").get("permissions").get("name");

        List<Predicate> conditions = new ArrayList<>();
        if(groupName != null){
            conditions.add(builder.equal(pathGroupName, groupName));
        }
        if(permissionNames != null){
            for(String permissionName : permissionNames){
                conditions.add(builder.equal(pathPermissionName, permissionName));
            }
        }
        
        TypedQuery<User> typedQuery = em.createQuery(query
            .select(fromUser)
            .where(builder.or(conditions.toArray(new Predicate[] {})))
            .distinct(true));

        if (pageSize > 0) {
            typedQuery.setMaxResults(pageSize)
                        .setFirstResult(pageOffset);
        }

        List<User> users = typedQuery.getResultList();
        return users;
    }

}