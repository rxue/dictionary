package rx.dictionary.application;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

//@RequestScoped
//@Named
public class SearchHistory implements ValueChangeListener {

	@Override
	public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
		System.out.println("year changed");
	}
	
}
