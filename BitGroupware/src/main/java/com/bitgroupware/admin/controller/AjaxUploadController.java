package com.bitgroupware.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bitgroupware.community.service.NoticeService;
import com.bitgroupware.community.vo.NoticeVo;
import com.bitgroupware.utils.MediaUtils;
import com.bitgroupware.utils.TemporaryFileUrl;
import com.bitgroupware.utils.UploadFileUtils;

@Controller
@RequestMapping("/admin")
public class AjaxUploadController {
	
	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> uploadAjax(MultipartFile file, HttpServletRequest request) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		String fileUrl = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		TemporaryFileUrl.fileUrl.put(fileUrl, fileUrl);
		return new ResponseEntity<String>(fileUrl, HttpStatus.OK);
	}

	@RequestMapping("/displayFile")
	@ResponseBody
	public ResponseEntity<byte[]> displayFile(String fileUrl, HttpServletRequest request) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		try {
			String formatName = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + fileUrl);
			if (mType != null) {
				headers.setContentType(mType);
			} else {
				fileUrl = fileUrl.substring(fileUrl.indexOf("_") + 1);
				// 다운로드용 컨텐트 타입
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				// 큰 따옴표 내부에 " \" "
				// 바이트배열을 스트링으로
				// iso-8859-1 서유럽언어
				// fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileUrl.getBytes("utf-8"), "iso-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if (in != null) in.close(); // 스트림 닫기
		}
		return entity;
	}

	@RequestMapping(value = "/deleteFileAjax", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteFileAjax(String fileUrl, HttpServletRequest request) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		String temporaryFileUrl = fileUrl.substring(1);
		new File(uploadPath + temporaryFileUrl).delete();
		TemporaryFileUrl.fileUrl.remove(fileUrl);
		noticeService.deleteNoticeFile(fileUrl);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
