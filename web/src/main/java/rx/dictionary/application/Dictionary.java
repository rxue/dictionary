package rx.dictionary.application;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class Dictionary {
	private String keyword;
	private boolean hasImage;
	public void search() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("when being invoked, the current phase is " + context.getCurrentPhaseId());
		System.out.println("search word: " + keyword);
		hasImage = true;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
	public boolean getHasImage() {
		return hasImage;
	}
	public String getImage() {
		return "https://peda.net/aanekoski/p%C3%A4iv%C3%A4hoito/mp/maahiset2/maahiset2/aurinko-jpg:file/photo/40880a3811061a75dfc8373970b0aa45dbd45519/aurinko.jpg";
	}
}
