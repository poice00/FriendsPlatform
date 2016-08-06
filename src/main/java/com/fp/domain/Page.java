package com.fp.domain;


import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int pageSize;
	private int currentPage;
	private int entityCount;
	private int pageCount;
	private List<T> entityList;
	private List<Integer> pagination;

	public Page(int pageSize, int currentPage, int entityCount, List<T> entityList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.entityCount = entityCount;
		this.entityList = entityList;
		pageCount = (entityCount == 0 ? 1 : ((entityCount + pageSize - 1) / pageSize));
		pagination = new ArrayList<Integer>();
		if (pageCount < 5) {
			for (Integer i = 1; i <= pageCount; i++) {
				pagination.add(i);
			}
		} else {
			for (int i = (((currentPage - 2) < 1) ? 1 : (currentPage - 2)); i <= (((currentPage + 2) > pageCount) ? pageCount : (currentPage + 2)); i++) {
				pagination.add(i);
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEntityCount() {
		return entityCount;
	}

	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

	public List<Integer> getPagination() {
		return pagination;
	}

	public void setPagination(List<Integer> pagination) {
		this.pagination = pagination;
	}
}
