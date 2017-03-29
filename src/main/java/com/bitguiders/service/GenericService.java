package com.bitguiders.service;

import java.util.List;

public abstract interface GenericService<ORM> {
	 void add(ORM orm);
	 void update(ORM orm);
	 void delete(ORM orm);
	 public List<ORM> getList();
}
