package rx.dictionary.ui.jsf.search;

import org.junit.Before;
import org.junit.Test;

public class AjaxSearchComponentTest {
	private AjaxSearchComponent tested;
	@Before
	public void init() {
		tested = new AjaxSearchComponent();
	}
	@Test
	public void voidSearchBeforeLoad() {
		tested.search();
	}

}
