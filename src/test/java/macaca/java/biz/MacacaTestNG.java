package macaca.java.biz;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.Random;

public class MacacaTestNG {


    private String udid, port, proxyport;
    boolean isFail;
    int initcount = 0;
    private MacacaClient driver = new MacacaClient();


    public MacacaClient initDriver() throws Exception {
        initcount = initcount +1;
        System.out.println("-----设备"+udid+"---第"+initcount+"次初始化-------------");
        String platform = "Android";

        JSONObject porps = new JSONObject();
        porps.put("platformName", platform);
//        porps.put("package", "com.tencent.wstt.gt");
        // TODO add app
        porps.put("app", "your apk");
        //0: 启动并安装 app。1 (默认): 卸载并重装 app。 2: 仅重装 app。3: 在测试结束后保持 app 状态。
        porps.put("reuse", 3);
        porps.put("udid", udid);
        porps.put("proxyPort", Integer.parseInt(proxyport));
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        desiredCapabilities.put("host", "127.0.0.1");
        desiredCapabilities.put("port", Integer.parseInt(port));

        if(port.equals("3457")){
            driver.sleep(2000);
        }
        return driver.initDriver(desiredCapabilities);
    }


    @BeforeTest
    @Parameters({ "port", "proxyport", "udid" })
    public void getpara(String port, String proxyport, String udid) {
        this.port = port;
        this.proxyport = proxyport;
        this.udid = udid;
    }


    @BeforeMethod
    public void beforecase() throws Exception {
        //重置isFail,判读是否执行 aftercase方法
        isFail = true;
    }

    @BeforeClass
    public void setUp() throws Exception {

        initDriver();
    }


    @Test
    public void test1(){
        try {

            System.out.println(driver.contexts.getPort());
            JSONObject windowsizeObject = driver.getWindowSize();
            int width = windowsizeObject.getIntValue("width");
            int height = windowsizeObject.getIntValue("height");
            Random random = new Random();
            for (int i = 0; i < 9000; i++) {
                int randNumX = random.nextInt(width - Math.round(width / 10)) + Math.round(width / 10);
                int randNumY = random.nextInt(height - Math.round(height / 15)) + Math.round(height / 15);
                driver.tap(randNumX, randNumY);
                driver.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterClass public void tearDown() throws Exception {
        driver.quit();
    }

}
