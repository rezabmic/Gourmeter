/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.jee.gourmeter.test.utils;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

/**
 *
 * @author j
 */
public class ArchiveMaker {

    public static WebArchive createTestArchive() {
        WebArchive testWar = ShrinkWrap.create(WebArchive.class, "test.war");

        addPackageToArchive(testWar, "cz.cvut.fel.jee.gourmeter");
        testWar.addAsWebInfResource("Gourmeter-ds.xml", "Gourmeter-ds.xml");
        testWar.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
        // testWar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return testWar;
    }

    private static void addPackageToArchive(WebArchive ejbJar, String packageName) throws IllegalArgumentException {
        try {
            //rekurzivne probere vsechny balicky a prida tridy do archivu
            ClassPath cp = ClassPath.from(ClassLoader.getSystemClassLoader());
            ImmutableSet<ClassPath.ClassInfo> bb = cp.getTopLevelClassesRecursive(packageName);
            for (ClassPath.ClassInfo b : bb) {
                ejbJar.addClass(b.load());
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchiveMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        WebArchive jar = createTestArchive();
        Map<ArchivePath, Node> content = jar.getContent();
        for (Map.Entry<ArchivePath, Node> c : content.entrySet()) {
            System.out.println(c.getKey().get());
        }
    }
}
