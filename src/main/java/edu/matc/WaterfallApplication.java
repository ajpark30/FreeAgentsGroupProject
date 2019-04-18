package edu.matc;

import edu.matc.entity.Waterfall;
import edu.matc.persistence.GenericDao;
import org.eclipse.persistence.internal.oxm.schema.model.All;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/waterfalls") //You may want to add a value here so that all traffic isn't routed to the class below.

//The java class declares root resource and provider classes
public class WaterfallApplication extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(ByLocation.class );
        h.add(AllFalls.class);
        return h;
    }
}
