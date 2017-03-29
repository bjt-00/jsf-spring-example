package com.bitguiders.dataaccess.dao;

import java.util.List;

public abstract interface GenericDAO<ORM> {

	 void add(ORM orm);
	 void update(ORM orm);
	 void delete(ORM orm);
	 public List<ORM> getList();

}
