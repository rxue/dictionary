package rx.backoffice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.servlet.http.Part;
import static rx.backoffice.FileUploadListener.FILE_NAME;
public class FileUploader implements Callable<Boolean>, ManagedTask {
	private final Part file;
	private final Map<String,String> executionProperties;
	public FileUploader(Part file) {
		this.file = file;
		this.executionProperties = new HashMap<>();
	}

	@Override
	public Boolean call() throws Exception {
		String fileName = file.getSubmittedFileName();
		try(InputStream is = file.getInputStream();
			OutputStream os = new FileOutputStream(fileName)) {
			os.write(is.read());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Map<String, String> getExecutionProperties() {
		executionProperties.put(FILE_NAME, file.getSubmittedFileName());
		return executionProperties;
	}

	@Override
	public ManagedTaskListener getManagedTaskListener() {
		return new FileUploadListener();
	}

}
