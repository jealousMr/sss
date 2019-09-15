package art_server.art_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Queue;
import java.util.concurrent.Executor;

@Configuration
public class PoolConfig {
    private int corePoolSize=20;
    private int maxPoolSize=200;
    private int queueCapacity=12;
    @Bean
    public Executor executor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("exe");
        executor.initialize();
        return executor;
    }

}
