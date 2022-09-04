package rx.backoffice;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class FileUploader {
	private List<Part> files = new ArrayList<>();
	public void upload() {
		System.out.println("going to UPLOAD FILE " + files.get(0).getSubmittedFileName());
		System.out.println("going to UPLOAD FILE " + files.get(1).getSubmittedFileName());
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
