package com.spring.web.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.file.FileObject;
import com.spring.exception.FileUploadException;
import com.spring.service.file.FileService;
import com.spring.utils.DownloadUtils;
import com.spring.web.sys.BaseController;

@Controller
@RequestMapping(value = "/file", method = RequestMethod.GET)
public class FileController extends BaseController {
	@Value("${upload.filePath}")
	private String filePath;

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "fileindex";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<FileObject> list() {
		return fileService.list(filePath);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody FileObject upload(@RequestParam("file") MultipartFile file) throws FileUploadException {
		return fileService.upload(filePath, file);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody boolean upload(@RequestParam("name") String name) throws FileUploadException {
		return fileService.delete(filePath, name);
	}
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam("name") String name,HttpServletRequest req,HttpServletResponse res) throws IOException {
//		fileService.download(filePath, name,res);
		DownloadUtils.download(req, res, filePath+"/"+name, name);
	}
}
