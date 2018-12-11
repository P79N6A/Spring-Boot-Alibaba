package com.alibaba.middleware.container.pool;

/**
 * @Author: Lemon 2018/6/28 10:05
 */
//@EnableAsync
//@Configuration
public class TaskPoolConfig {

    //private Executor taskExecutor = newFixedThreadPool(15);

    /**
     * 自定义线程池
     * 用于Task
     */
    /*@Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(30);
        //让核心子线程超过30秒自动销毁
        executor.setAllowCoreThreadTimeOut(true);
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }*/


    /**
     *
     * java自带线程池
     * taskExecutor.execute(new MessageThread(""))
     * @param
     * @return
     */
    /*public void MyExecutor(){
        taskExecutor.execute(new MessageThread("nihao"));
    }*/


    /*class MessageThread implements Runnable {
        *//**
         * 消息输入流
         *//*
        private String stream;

        public MessageThread(String stream) {
            this.stream = stream;
        }
        @Override
        public void run() {
            try {
                System.out.println(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }*/
}
