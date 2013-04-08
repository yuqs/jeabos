package org.jeabos.core.web.struts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3739173720113854928L;
	public static final String RELOAD = "reload";

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private String lookup;

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	/**
	 * Action����, Ĭ�ϵ�action����, Ĭ�ϵ���list()����.
	 */
	@Override
	public String execute() throws Exception {
		return list();
	}

	/**
	 * Action����,��ʾEntity�б����.
	 * ����return SUCCESS.
	 */
	public abstract String list() throws Exception;

	/**
	 * Action����,��ʾ�������޸�Entity����.
	 * ����return INPUT.
	 */
	@Override
	public abstract String input() throws Exception;

	/**
	 * Action����,�������޸�Entity. 
	 * ����return RELOAD.
	 */
	public abstract String save() throws Exception;

	/**
	 * Action����,ɾ��Entity.
	 * ����return RELOAD.
	 */
	public abstract String delete() throws Exception;

	/**
	 * ʵ�ֿյ�prepare()����,����������Action�����ִ�еĹ����Ķ��ΰ�.
	 */
	public void prepare() throws Exception {
	}

	/**
	 * ������input()ǰִ�ж��ΰ�.
	 */
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * ������save()ǰִ�ж��ΰ�.
	 */
	public void prepareSave() throws Exception {
		prepareModel();
	}

	/**
	 * ��ͬ��prepare()���ڲ�����,��prepardMethodName()�������. 
	 */
	protected abstract void prepareModel() throws Exception;
}
