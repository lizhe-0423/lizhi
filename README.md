## 问题描述

redis 启动不起来 或者超时报错 可更改yml timeout超时调试

## 问题定位

- config 包下 RedissonConfig 异常
- manage 包下 RedisLimitManager 限流器 依赖于 上述RedissonConfig类

## 问题修复

### 请详细说明如何解决该问题 1、2 分条叙述


## 在此处可写下你的信息 如昵称、git地址、所属组织

解决人: [] 