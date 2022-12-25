package rx.dictionary.ui.jsf.addorupdate;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ExplanationsComponent extends AbstractList<ExplanationDTO> implements Serializable {
	private List<ExplanationDTO> explanationDTOs = new ArrayList<>();
	/**
	 * NOTE! add has to be overridden when extends AbstractList since the default implementation of AbstractList.add(int index, E t) throws UnsupportedOperationException
	 */
	@Override
	public void add(int index, ExplanationDTO e) {
		explanationDTOs.add(index, e);
	}
	
	@Override
	public ExplanationDTO get(int index) {
		return explanationDTOs.get(index);
	}

	@Override
	public int size() {
		return explanationDTOs.size();
	}

}
