package stone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by heshaoyi on 7/31/17.
 */
public abstract class ALog {
    protected final Log log = LogFactory.getLog(getClass());
}
