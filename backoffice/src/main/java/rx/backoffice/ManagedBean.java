package rx.backoffice;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ManagedBean {
	private String name = "hello";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
