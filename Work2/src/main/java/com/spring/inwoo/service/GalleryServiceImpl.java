package com.spring.inwoo.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.inwoo.dao.GalleryDao;
import com.spring.inwoo.exception.GalleryUploadException;
import com.spring.inwoo.vo.GalleryVo;

@Service("galleryService")
@Transactional
public class GalleryServiceImpl implements GalleryService{
	private static final String SAVE_PATH
		= "C:\\Users\\user\\Desktop\\인우\\Workspaces\\STSWorkspace1\\Work2\\src\\main\\webapp\\resources\\upload";
	public static final String BASE_URL = "/upload";
	
	@Autowired
	GalleryDao galleryDao;
	
	
	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH + 1);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		
		return fileName;
	}
	
	private void writeFile(
			MultipartFile multipartfile,
			String saveFileName
			) throws IOException {
		byte[] fileData = multipartfile.getBytes();
		FileOutputStream fos
			= new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(fileData);
		fos.close();
	}
	

	@Override
	public List<GalleryVo> getList() {
		return galleryDao.getList();
	}


	@Override
	public void insert(GalleryVo galleryVo, MultipartFile multipartfile) throws GalleryUploadException {
		try {
			if(multipartfile.isEmpty() == true) {
				throw new GalleryUploadException("MultipartFile is Empty...");
			}
			
			String orgFileName
				= multipartfile.getOriginalFilename();
			String fileExtName
				= orgFileName.substring(orgFileName.lastIndexOf('.') + 1,
									    orgFileName.length());
			String saveFileName
				= generateSaveFileName(fileExtName);
			Long fileSize
				= multipartfile.getSize();
			
			writeFile(multipartfile, saveFileName);
			
			galleryVo.setOrgFileName(orgFileName);
			galleryVo.setSaveFileName(saveFileName);
			galleryVo.setFileExtName(fileExtName);
			galleryVo.setFileSize(fileSize);
			galleryDao.insert(galleryVo);
			
		} catch (IOException ex) {
			throw new GalleryUploadException("Save File Uploaded...");
		}
	}

	@Override
	public boolean delete(Long userNo, Long no) {
		GalleryVo galleryVo = new GalleryVo();
		galleryVo.setUserNo(userNo);
		galleryVo.setNo(no);
		
		return galleryDao.delete(galleryVo) == 1;
	}


}









