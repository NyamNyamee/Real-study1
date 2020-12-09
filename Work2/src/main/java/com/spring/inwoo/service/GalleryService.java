package com.spring.inwoo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.inwoo.vo.GalleryVo;

public interface GalleryService {
	public List<GalleryVo> getList();
	
	public void insert(GalleryVo galleryVo,
			MultipartFile multipartfile);
	
	public boolean delete(Long userNo, Long no);
}
