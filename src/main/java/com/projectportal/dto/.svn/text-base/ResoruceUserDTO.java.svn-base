package com.projectportal.dto;

import com.projectportal.entity.User;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/19/13
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResoruceUserDTO implements Serializable {

    private User user;
    private float percentUtil;


    @Override
    public boolean equals(Object o) {
        System.out.println("======= Compare: " + user.getUserId() + "/" + ((ResoruceUserDTO)o).user.getUserId());
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResoruceUserDTO that = (ResoruceUserDTO) o;

        System.out.println("======= Compare: " + user.getUserId() + "/" + that.user.getUserId());
        System.out.println("======= Compare: " + user.getUserId().equals(that.user.getUserId()));

        if (!user.getUserId().equals(that.user.getUserId())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getPercentUtil() {
        return percentUtil;
    }

    public void setPercentUtil(float percentUtil) {
        /** percentage convert */
        this.percentUtil = percentUtil / 100;
    }
}
