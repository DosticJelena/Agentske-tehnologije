package messagemanager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AgentMessage implements Serializable {
	
	public Map<String, Serializable> userArgs;
	
	public AgentMessage() {
		userArgs = new HashMap<>();
	}
}
