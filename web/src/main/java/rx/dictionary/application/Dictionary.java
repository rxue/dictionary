package rx.dictionary.application;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class Dictionary {
	private String keyword;
	public void search() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("when being invoked, the current phase is " + context.getCurrentPhaseId());
		System.out.println("search word: " + keyword);
	}
	public void setKeyword(String keyword) {
		System.out.println("The set keyword is " + keyword);
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
}
