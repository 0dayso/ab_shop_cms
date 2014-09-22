package net.rytong.utils;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class PreLocalContainerEntityManagerFactoryBean extends
		LocalContainerEntityManagerFactoryBean {

	@Override
	public void setPersistenceUnitName(String persistenceUnitName) {
		String runTime = FileUtil.getProperties("customer.properties", "runtime").toLowerCase();
		persistenceUnitName = "punit_" + runTime;
		super.setPersistenceUnitName(persistenceUnitName);
	}
}
