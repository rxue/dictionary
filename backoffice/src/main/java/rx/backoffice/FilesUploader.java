package rx.backoffice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import javax.enterprise.context.RequestScoped;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class FilesUploader {
	private List<Part> files = new ArrayList<>();
	@Resource
	private ManagedExecutorService executorService;
	public void upload() throws InterruptedException {
		Collection<Callable<Boolean>> callables = new ArrayList<>();
		files.forEach(part -> callables.add(new FileUploader(part)));
		executorService.invokeAll(callables);
		System.out.println("Upload in a batch done!!!!!!!!!!!!!");
	}
	
	/**
	 * NOTE! Declared merely due to the basic requirement of using manged bean in JSF application 
	 * @return
	 */
	public Part getFile() {
		return null;
	}
	public void setFile(Part file) {
		this.files.add(file);
	}
	
	
}
