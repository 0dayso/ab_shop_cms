package net.rytong.action;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Subclaues;

public class SubClauseAction extends CRUDActionSupport<Subclaues> {

	private static final long serialVersionUID = 1L;

	@Override
	public CRUDService<Subclaues> getAutowiredService() {
		return null;
	}

}
