# Spring-Boot for alibaba
快速下载优雅基础框架 spring-boot，阿里pandora-boot框架改造

### 1、该框架集成的功能：针对APP
- 用户权限过滤 √
- 支持跨域 √
- 关键词敏感词过滤（dfa算法，精准匹配/部分匹配，中文分词）√
- 用户名随机 √
- 后端日志打点(cpu ms)（前端加入埋点更佳 pv uv s2xx s3xx s4xx s5xx等指标） √
- 图片上传（头像，审核）
- API统一返回样式注解方式 √
- 异常统一处理 √
- 消息推送通知（短信/应用）
- 抽奖组件（概率可控） √
- 并发秒杀组件 √
- 实时排名 √


### 2、针对框架
- 离线分析（收集特征）
- 定时器 √
- 缓存 √ （并发有两种情况：第一种并发是单点抢占资源 利用decr原子性，第二种并发锁是队列执行，先进先执行，原子性原理是根据分布式并发锁来解决）
- 队列
- 线程管理 √
- 自定义线程池和java自带线程池 √
- 任务框架

### 3、Author
- lemon.nzg@alibaba-inc.com
