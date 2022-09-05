package rx.backoffice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		files.forEach(part -> callables.add(() -> upload(part)));
		executorService.invokeAll(callables);
		System.out.println("Upload in a batch done!!!!!!!!!!!!!");
	}
	
	private boolean upload(Part file) {
		String fileName = file.getSubmittedFileName();
		System.out.println("upload file " + fileName);
		try(InputStream is = file.getInputStream();
			OutputStream os = new FileOutputStream(fileName)) {
			os.write(is.read());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("upload file thread completed: " + fileName);
		return true;
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
