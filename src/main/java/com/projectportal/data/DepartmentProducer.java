package com.projectportal.data;

import com.projectportal.entity.Department;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 2/3/13
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
@ApplicationScoped
public class DepartmentProducer extends AbstractProducer {

    @Named
    @Produces
    private List<Department> glbDepartmentList;

    @PostConstruct
    public void reterieveAllDepartments(){
        glbDepartmentList = em.createQuery("SELECT department FROM Department department ORDER BY department.departmentName").getResultList();
    }

    /**
     * Event monitor
     * @param department
     */
    public void onPriorityListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Department department){
        this.reterieveAllDepartments();
    }
}
