package rx.dictionary.ui.jsf.search;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@RequestScoped
@Named
public class AjaxSearchComponent {
	private String keyword;
	public void fuzzy(AjaxBehaviorEvent e) {
		System.out.println("::::::::::::::::!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

}
