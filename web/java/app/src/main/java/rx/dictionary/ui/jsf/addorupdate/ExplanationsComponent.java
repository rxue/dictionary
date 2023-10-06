package rx.dictionary.ui.jsf.addorupdate;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ExplanationsComponent extends AbstractList<JSFExplanationDTO> implements Serializable {
	private List<JSFExplanationDTO> JSFExplanationDTOS = new ArrayList<>();
	/**
	 * NOTE! add has to be overridden when extends AbstractList since the default implementation of AbstractList.add(int index, E t) throws UnsupportedOperationException
	 */
	@Override
	public void add(int index, JSFExplanationDTO e) {
		JSFExplanationDTOS.add(index, e);
	}
	
	@Override
	public JSFExplanationDTO get(int index) {
		return JSFExplanationDTOS.get(index);
	}

	@Override
	public int size() {
		return JSFExplanationDTOS.size();
	}

}
