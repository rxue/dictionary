package rx.backoffice;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class FileUploader {
	private Part file;
	public void upload() {
		System.out.println("going to UPLOAD FILE");
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	
}
