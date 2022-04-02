package rx.dictionary.application;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class Dictionary {
	private String keyword;
	private String imageUrl = "https://www.dreameant.com/wp-content/uploads/2021/07/Sun-Dream-Interpretation.jpg";
	public void search() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("when being invoked, the current phase is " + context.getCurrentPhaseId());
		System.out.println("search word: " + keyword);
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
	public boolean getHasImage() {
		return keyword != null;
	}
	public String getImageUrl() {
		return imageUrl;
	}
}
