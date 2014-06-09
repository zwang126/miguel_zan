/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.maxgigapop.mrs.system;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static junit.framework.Assert.assertFalse;
import net.maxgigapop.mrs.bean.DriverInstance;
import net.maxgigapop.mrs.bean.VersionGroup;
import net.maxgigapop.mrs.bean.persist.DriverInstancePersistenceManager;
import net.maxgigapop.mrs.bean.persist.PersistenceManager;
import net.maxgigapop.mrs.service.DriverModelPuller;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author xyang
 */
@RunWith(Arquillian.class)
public class HandleSystemCallTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(HandleSystemCall.class)
                .addPackages(true, "net.maxgigapop.mrs.bean")
                .addPackages(true, "net.maxgigapop.mrs.bean.persist")
                .addPackages(true, "net.maxgigapop.mrs.common")
                .addPackages(true, "net.maxgigapop.mrs.system")
                .addPackages(true, "net.maxgigapop.mrs.driver")
                .addPackages(true, "com.hp.hpl.jena")
                .addPackages(true, "org.apache.xerces")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
    }

    @Inject
    HandleSystemCall systemCallHandler;

    private @PersistenceContext(unitName = "RAINSAgentPU")
    EntityManager entityManager;

    private final String ttlModelDriver1 = "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>.\n"
            + "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>.\n"
            + "@prefix xsd: <http://www.w3.org/2001/XMLSchema#>.\n"
            + "@prefix owl: <http://www.w3.org/2002/07/owl#>.\n"
            + "@prefix nml: <http://schemas.ogf.org/nml/2013/03/base#>.\n"
            + "@prefix mrs: <http://schemas.ogf.org/mrs/2013/12/topology#>.\n"
            + "<http://www.maxgigapop.net/mrs/2013/topology#> a owl:Ontology;\n"
            + "    rdfs:label \"NML-MRS Description of the MAX Research Infrastructure\".\n"
            + "<urn:ogf:network:rains.maxgigapop.net:2013:topology>\n"
            + "    a   nml:Topology,\n"
            + "        owl:NamedIndividual;\n"
            + "    nml:hasNode\n"
            + "        <urn:ogf:network:rains.maxgigapop.net:2013:clpk-msx-1>,\n"
            + "        <urn:ogf:network:rains.maxgigapop.net:2013:clpk-msx-4>.";

    /**
     * Test of createHeadVersionGroup method, of class HandleSystemCall.
     */
    @Test
    public void testCreateHeadVersionGroup_Long() throws Exception {
        System.out.println("createHeadVersionGroup");
        if (PersistenceManager.getEntityManager() == null) {
            PersistenceManager.initialize(entityManager);
        }
        Long refId = 1L;
        VersionGroup expResult = null;
        VersionGroup result = systemCallHandler.createHeadVersionGroup(refId);
        System.out.println("createHeadVersionGroup result=" + result);
        assertFalse("createHeadVersionGroup results in null VersionGroup", expResult == null);
        //fail("The test case is a prototype.");
    }
}