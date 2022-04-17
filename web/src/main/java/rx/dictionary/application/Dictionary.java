package rx.dictionary.application;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.Definition;
import rx.dictionary.DefinitionRepository;
import rx.dictionary.Language;

@RequestScoped
@Named
public class Dictionary {
	private String keyword;
	private String imageUrl = "https://www.dreameant.com/wp-content/uploads/2021/07/Sun-Dream-Interpretation.jpg";
	private DefinitionRepository repo;
	private String result;
	
	@Inject
	public Dictionary(DefinitionRepository repo) {
		super();
		this.repo = repo;
	}
	public void search() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("when being invoked, the current phase is " + context.getCurrentPhaseId().getName());
		List<Definition> definitions = repo.find(keyword, Language.EN);
		result = definitions.get(0).getDefinition();
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
	public boolean getHasResult() {
		return result != null;
	}
	public String getResult() {
		return result;
	} 

	public String getImageUrl() {
		return imageUrl;
	}
}
