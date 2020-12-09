package com.spring.inwoo.dao;

import java.util.List;

import com.spring.inwoo.vo.GalleryVo;

public interface GalleryDao {
	
	public List<GalleryVo> getList();
	
	public void insert(GalleryVo galleryVo);
	
	public int delete(GalleryVo galleryVo);
}










