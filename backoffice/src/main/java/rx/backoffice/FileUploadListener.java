package rx.backoffice;

import java.util.Map;
import java.util.concurrent.Future;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;

public class FileUploadListener implements ManagedTaskListener {
	static final String FILE_NAME = "file name";

	@Override
	public void taskAborted(Future<?> future, ManagedExecutorService executor, Object task, Throwable exception) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskDone(Future<?> future, ManagedExecutorService executor, Object task, Throwable exception) {
		Map<String,String> properties = ((ManagedTask) task).getExecutionProperties();
		String fileName = properties.get(FILE_NAME);
		System.out.println("upload file " + fileName + " DONE!");
	}

	@Override
	public void taskStarting(Future<?> future, ManagedExecutorService executor, Object task) {
		Map<String,String> properties = ((ManagedTask) task).getExecutionProperties();
		String fileName = properties.get(FILE_NAME);
		System.out.println("upload file " + fileName);
	}

	@Override
	public void taskSubmitted(Future<?> future, ManagedExecutorService executor, Object task) {
		// TODO Auto-generated method stub
		
	}

}
