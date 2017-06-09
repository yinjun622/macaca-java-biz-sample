package macaca.java.biz;



/**
 * 通用Page基类，用于页面的通用处理，比如页面返回以及其他
 * @author xiulian.yin
 *
 */
public class BasePage  {

	/**
	 * antClient driver
	 */
	public BaseMacacaClient driver;

	/**
	 * page desc
	 * @return
	 */
	public String pageDesc;


	public BaseMacacaClient getDriver() {
		return driver;
	}

	public void setDriver(BaseMacacaClient driver) {
		this.driver = driver;
	}

	/**
	 * Constrctor
	 */
	public BasePage(String pageDesc) {
		this.pageDesc = pageDesc;
	}

	public BasePage(String pageDesc,BaseMacacaClient driver){
		this.pageDesc = pageDesc;
		this.driver = driver;
	}

	/**
	 * 当前页面是否已经展示
	 * @return
	 * @throws Exception
	 */
	public boolean hasPageShown(CommonUIBean bean)  {
		return driver.isElementExistAfterWaiting(bean);
	}


}
