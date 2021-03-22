# Data Binding

[toc]

## 1、Data Binding是什么

### 1.1 基本概念

- MVVM
- 提高开发效率
- 性能高/功能强

### 1.2、基本用途

- 去掉Activity & Fragment内的UI代码
- XML变成UI的唯一真实来源
- 减少定义view id的主要用途 -- 不再需要findViewById(cached)

### 1.3、类似方案

- ButterKnife
- Android Annotations
- RoboBinding

### 1.4、主要用途

- 去除Activity/Fragment中的UI代码
- 性能超过手写代码，安全（不会id错而crash）
- 保证执行在主线程

### 1.5、主要劣势

- 报错信息不那么直接

## 2、Data Binding基础用法

### 2.1 如何启用Data Binding

App module - build.grade

```
android{
	dataBinding{
		enabled = true
	}
}
```

