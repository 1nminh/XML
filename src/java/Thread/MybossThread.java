/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import Constant.AppConstant;
import Crawler.MybossCategoriesCrawler;
import Crawler.MybossCrawler;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author adler
 */
public class MybossThread extends BaseThread implements Runnable {

    private ServletContext context;

    public MybossThread(ServletContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        while (true) {
            try {
                MybossCategoriesCrawler categoriesCrawler = new MybossCategoriesCrawler(context);
                Map<String, String> categories = categoriesCrawler.getCategories(AppConstant.urlMyboss);
                for (Map.Entry<String, String> entry : categories.entrySet()) {
                    Thread crawlingThread = new Thread(new MybossCrawler(context, entry.getKey(), entry.getValue()));
                    crawlingThread.start();
                    synchronized (BaseThread.getInstance()) {
                        while (BaseThread.isSuspended()) {
                            BaseThread.getInstance().wait();
                        }
                    }
                }
                MybossThread.sleep(TimeUnit.DAYS.toMillis(AppConstant.breakTimeCrawling));
                synchronized (BaseThread.getInstance()) {
                    while (BaseThread.isSuspended()) {
                        BaseThread.getInstance().wait();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MybossThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
