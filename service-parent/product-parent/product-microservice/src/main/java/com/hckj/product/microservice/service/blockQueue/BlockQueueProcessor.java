package com.hckj.product.microservice.service.blockQueue;

import com.hckj.common.cache.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 阻塞队列(基于redis实现)
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/19 11:16
 */
@Service
public class BlockQueueProcessor implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BlockQueueBusinessProcess blockQueueBusinessProcess;

    // 处理异步请求的阻塞队列
    private LinkedBlockingQueue<BlockQueueHelp> blockingQueue = new LinkedBlockingQueue<>();

    /**
     * 阻塞队列内部线程类
     */
    private class BlockQueueServiceThread extends Thread {
        private final Logger log = LoggerFactory.getLogger(this.getClass());

        @Override
        public void run() {
            try {
                List<String> keyList = BlockQueueEnum.keys();
                int keyLength = keyList.size();
                ExecutorService executorService = Executors.newFixedThreadPool(keyLength);
                // 创建多个有返回值的任务
                List<Future<String>> futureList = new ArrayList<>();
                for (int k = 0; k < keyLength; k++) {
                    BlockQueueCallable callable = new BlockQueueCallable(keyList.get(k), "线程" + k);
                    callable.setBlockQueueBusinessProcess(blockQueueBusinessProcess);
                    Future<String> future = executorService.submit(callable);
                    futureList.add(future);
                }
                // 关闭线程池
                if (!executorService.isShutdown()) {
                    log.info("线程池关闭");
                    executorService.shutdown();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    /**
     * 阻塞队列内部接口类
     */
    private class BlockQueueCallable implements Callable<String> {
        private final Logger log = LoggerFactory.getLogger(this.getClass());

        private String threadName;
        private String key;
        private BlockQueueBusinessProcess blockQueueBusinessProcess;

        public void setBlockQueueBusinessProcess(BlockQueueBusinessProcess blockQueueBusinessProcess) {
            this.blockQueueBusinessProcess = blockQueueBusinessProcess;
        }

        public BlockQueueCallable(String key, String threadName) {
            this.key = key;
            this.threadName = threadName;
        }

        /**
         * 多线程处理方法
         */
        public String call() throws Exception {
            log.info(threadName + "处理key：" + key);
            try {
                while (true) {
                    List<String> list = redisUtil.brpop(key);
                    blockQueueBusinessProcess.processBusiness(key, list.get(1));
                }
            } catch (Exception e) {
                log.error("多线程处理阻塞队列出错，key=" + key, e);
            }
            return threadName + ",处理完毕";
        }
    }

    // 把请求放入阻塞队列
    public void pushToQueue(BlockQueueEnum blockQueueEnum, String value) {
        blockingQueue.add(new BlockQueueHelp(blockQueueEnum, value));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            while (true) {
                try {
                    BlockQueueHelp blockQueueHelp = blockingQueue.take();
                    redisUtil.lpush(blockQueueHelp.getBlockQueueEnum().getCode(), blockQueueHelp.getValue());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();

        new BlockQueueServiceThread().start();
    }
}
