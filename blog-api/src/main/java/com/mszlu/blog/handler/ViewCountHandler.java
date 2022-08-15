package com.mszlu.blog.handler;

import com.mszlu.blog.dao.mapper.ArticleMapper;
import com.mszlu.blog.dao.pojo.Article;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Slf4j
@EnableScheduling
public class ViewCountHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private ArticleMapper articleMapper;

    @Scheduled(cron = "0/10 * * * * *")
    //@Scheduled(cron = "0 0 3 * * *") 生产环境 每天凌晨3点执行
    @Async("taskExecutor") //扔到线程池 执行
    public void scheduled(){
        log.info("=====>>>>> 同步浏览量开始执行  {}",new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        Map<Object, Object> countMap = redisTemplate.opsForHash().entries("view_count");
        for (Map.Entry<Object,Object> entry : countMap.entrySet()){
            Long articleId = Long.parseLong(entry.getKey().toString());
            Integer count = Integer.parseInt(entry.getValue().toString());
            Article article = new Article();
            article.setId(articleId);
            article.setViewCounts(count);
            articleMapper.updateById(article);
        }
        log.info("=====>>>>> 同步浏览量结束  {}",new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }

}
