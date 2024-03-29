package by.it_academy.MDK29522.ApplicationContext.fabric;

import by.it_academy.MDK29522.ApplicationContext.ApplicationCont;
import by.it_academy.MDK29522.ApplicationContext.api.IApplicationContextBeen;

public class ApplicationContextSingleton {
    private static volatile IApplicationContextBeen instance;

    private ApplicationContextSingleton() {
    }

    public static IApplicationContextBeen getInstance() {
        if(instance==null){
            synchronized (ApplicationContextSingleton.class){
                if(instance==null){
                    instance = new ApplicationCont();
                }
            }
        }
        return instance;
    }

}
