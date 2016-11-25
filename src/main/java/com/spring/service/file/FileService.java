package com.spring.service.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.file.FileObject;
import com.spring.exception.FileUploadException;

@Service
public class FileService {

	private String msgFileEmpty="File is empty";

	private String msgFileExists="File exists";

	public List<FileObject> list(String path) {
		List<FileObject> files = new ArrayList<FileObject>();

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		assert listOfFiles != null;
		for (File file : listOfFiles) {
			files.add(FileObject.fromFile(file));
		}

		return files;
	}

	public FileObject upload(String path, MultipartFile content) throws FileUploadException {
		File file = new File(path + "/" + content.getOriginalFilename());
		if (!file.exists()) {
			if (!content.isEmpty()) {
				try {
					byte[] bytes = content.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
				} catch (IOException e) {
					throw new FileUploadException(e.getLocalizedMessage());
				}
			} else {
				throw new FileUploadException(msgFileEmpty);
			}
		} else {
			throw new FileUploadException(msgFileExists);
		}

		return FileObject.fromFile(file);
	}

	public boolean delete(String path, String name) {
		File file = new File(path + "/" + name);
		return file.exists() && file.delete();
	}
}
