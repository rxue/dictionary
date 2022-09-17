package rx.backoffice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	private static final int BYTE_SIZE_1M = 1024*1024; 
	public FileUploader(Part file) {
		this.file = file;
		this.executionProperties = new HashMap<>();
	}

	@Override
	public Boolean call() throws Exception {
		String fileName = file.getSubmittedFileName();
		try(BufferedInputStream bufferedInputStream = new BufferedInputStream(file.getInputStream());
				BufferedOutputStream bufferedOS = new BufferedOutputStream(new FileOutputStream(fileName))) {
			int availableSize = 0;
            while ((availableSize = bufferedInputStream.available())>0) {
                int buffSize = BYTE_SIZE_1M >= availableSize ? availableSize : BYTE_SIZE_1M;
                byte[] data = new byte[buffSize];
                bufferedInputStream.read(data);
                bufferedOS.write(data);
            }
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
