package com.everis.security.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.everis.security.group.entity.Group;

@Entity
@Table(name = User.TABLE_NAME)
@NamedQueries(value = {
    @NamedQuery(name = User.NAMED_QUERY_FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User {

    public static final String TABLE_NAME = "users";
    public static final String NAMED_QUERY_FIND_BY_USERNAME = "security.user.FindByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    private Group group;

    public void update(User src){
        this.username = src.getUsername();
        this.password = src.getPassword();
        this.group = src.getGroup();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }
    
}