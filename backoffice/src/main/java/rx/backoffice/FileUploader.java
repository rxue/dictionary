package rx.backoffice;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class FileUploader {
	private Part uploadedFile;
	public void upload() {
		System.out.println("going to UPLOAD FILE");
	}
	public Part getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
}
