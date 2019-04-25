package edu.matc;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * The Waterfalls API base class.
 */
@ApplicationPath("/waterfalls")
public class WaterfallApplication extends Application {

    /**
     * Get Waterfalls API classes.
     *
     * @return the classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(GetWaterfalls.class );
        return h;
    }
}
