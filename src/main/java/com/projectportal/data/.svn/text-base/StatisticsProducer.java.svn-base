package com.projectportal.data;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 12/30/12
 * Time: 6:20 PM
 * To change this template use File | Settings | File Templates.
 */
@ApplicationScoped
public  class StatisticsProducer extends AbstractProducer{

    @Named
    @Produces
    private int totalProjectsCompleted;

    @Named
    @Produces
    private int totalProjectsInProgress;

    @Named
    @Produces
    private int totalResourcesOnBench;

    @Named
    @Produces
    private int totalResourcesAllocated;

    @PostConstruct
    public void initProducer(){

    }
}
