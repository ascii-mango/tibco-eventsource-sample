package com.eventsource;

import com.tibco.plugin.java.JavaProcessStarter;


public final class ProcessStarter extends JavaProcessStarter {

    private static final org.apache.log4j.Logger LOGGER =  org.apache.log4j.Logger.getLogger("bw.logger");

    /**
     * This class allows users to write custom process starters using the java programming
     * language. It extends JavaProcessStarter abstract class that interfaces with the ActiveMatrix Business
     * works engine. JavaProcessStarter class defines following four abstract methods:
     * 	 1) init() - Allows users to initialize their resources.
     * 	 2) onStart() - Allows users to activate their listeners or Observers or kick off a process
     *                   by calling onEvent(object inputData).
     * 	 3) onStop() - Allows user to de-activate their listeners or Observers.
     * 	 4) onShutdown() - Allows users to release the resources. This method is somewhat similar to finalize()
     *
     * JavaProcessStarter also defines three non-abstract methods:
     * 	 1) getGlobalInstance() - It returns an instance of Java Global Instance defined in the advanced tab.
     * 	 2) onEvent(Object object) - It is the main entry point into the ActiveMartix Business Works engine. This
     method must be called in onStart() or in the listener or observer interface
     *                                 to trigger the process.
     * 	 3) onError(Throwable throwable) - It allows the user to throw an exception if the listener or observer fails
     *                                      to generate an notification event.
     */

    private String processName;


    public ProcessStarter() {
        super();
    }

    /**
     *
     * @param processName sampleInputParam
     * @throws Exception exception
     */
    public void initProcess(final String processName) throws Exception {
        this.processName = processName;
        init();
    }

    /**
     * Initializes the Java Event Source component during ActiveMatrix Business Works engine
     * start-up. It is highly recommended to initialize resource connections
     * in this method. Resource connections can also be defined as Java Global Instance.
     * To configure Java Global Instance use the advanced tab. It is a common practice to
     * define resource connection as Java Global Instance. Users can get a handle to the Java
     * Global Instance by calling this.getJavaGlobalInstance().
     */
    @Override
    public void init() throws Exception {
        LOGGER.info("[ProcessStarter] Initializing " + processName + " Proces Starter");
        //Some init code
        LOGGER.info("[ProcessStarter] Successfully Initialized Proces Starter");
    }

    /**
     * This method is called by ActiveMatrix Business Works engine to activate the Java Event Source. This is
     * an ideal place for users to add notifier or resource observer. The notifier/observer
     * can then call onEvent(Object inputData) method to kick off a process instance.
     */
    @Override
    public void onStart() throws Exception {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10 ; i++) {
                    try {
                        onEvent(new MessageDataType(processName, i));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to do onEvent");
                    }
                }
            }
        });

        thread.start();
    }

    /** This method is called by ActiveMatrix Business Works engine to de-activate the Event Source. User can
     * add code to de-activate a notifier or resource observer.
     */
    @Override
    public void onStop() throws Exception {
        //some cleanup code
    }
    /** This method is called at ActiveMatrix Business Works engine shutdown. User can add cleanup code in
     *  this method
     */
    @Override
    public void onShutdown() {
        //some more cleanup code
    }
}
