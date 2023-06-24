# 扑虎篮球论坛

这是一个专注于篮球领域，美观、功能齐全的篮球评论论坛，包括前端和后端两个独立的项目。提供了美观的界面和丰富的功能，让篮球爱好者可以进行交流、发表观点、分享信息。

![首页截图](https://github.com/Mlittleee/my_basketball_forum-front/blob/master/src/assets/ReadMeImage/img.png)

## 前端项目 [![Version](https://img.shields.io/badge/version-1.0.0-blue)](https://github.com/Mlittleee/my_basketball_forum-front)

- [前端地址](https://github.com/Mlittleee/my_basketball_forum-front)

## 后端项目 [![License](https://img.shields.io/badge/license-MIT-green)](https://github.com/Mlittleee/mybasketballforum)

- [后端地址](https://github.com/Mlittleee/mybasketballforum)

## 项目功能：

1. 用户登录，若是新用户则自动注册，登陆后会自动跳转到首页
2. 首页分为四个部分，头部导航栏，轮播图的展示，分页展示所有帖子，可以根据帖子标题进行搜索，最后侧边还有发表帖子入口和每日一句。
3. 其他板块下只会展示归属于该板块下的帖子，侧边同样也有发表帖子入口和每日一句。
4. 发表帖子使用 markdown 编辑器，支持自定义标签，帖子列表上显示有标签，浏览量，点赞数，发帖人和发帖时间。点击帖子标题即可查看帖子内容详情
5. 社区暂时只有显示所有活跃用户的功能，后续会添加私信，关注，粉丝等功能
6. 个人中心有用户信息的展示和编辑，发帖功能，展示点赞过的帖子功能，还可以管理自己所发的帖子，可以删除自己的帖子，也可以修改帖子的标签，标题和内容
7. 后台管理系统中可以管理用户，评论，帖子，标签，每日一句。

## 特点

- 界面美观：经过精心设计的用户界面，提供了良好的使用体验。
- 功能齐全：具备发表帖子、浏览帖子、评论、点赞等功能，满足用户的交流需求。
- 三大板块：主界面、用户中心和后台管理界面，方便用户进行浏览、操作和管理。
  ![用户中心截图](https://github.com/Mlittleee/my_basketball_forum-front/blob/master/src/assets/ReadMeImage/img2.png)

  ![管理员后台截图](https://github.com/Mlittleee/my_basketball_forum-front/blob/master/src/assets/ReadMeImage/img3.png)
- 分类浏览：用户可以根据自己的兴趣选择不同板块来浏览特定分类下的帖子。
- 自定义标签：用户在发帖时可以添加自定义标签，方便其他用户更好地了解帖子内容。
- 后台管理：管理员可以对用户、帖子、评论、篮球名言和板块简介进行管理，还可查看可视化统计信息。

## 技术栈

![Vue](https://img.shields.io/badge/Vue-2.x-brightgreen) ![axios](https://img.shields.io/badge/axios-Latest-blue) ![ElementUI](https://img.shields.io/badge/ElementUI-Latest-orange) ![Vuex](https://img.shields.io/badge/Vuex-Latest-green) ![Vue Router](https://img.shields.io/badge/Vue%20Router-Latest-yellow) ![Mavon Editor](https://img.shields.io/badge/Mavon%20Editor-Latest-lightgrey)

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-blue) ![MyBatis Plus](https://img.shields.io/badge/MyBatis%20Plus-Latest-green) ![Redis](https://img.shields.io/badge/Redis-Latest-red)

- 前端：Vue2 框架、axios、ElementUI、Vuex、Vue Router、Mavon Editor
- 后端：Spring Boot 框架、MyBatis Plus、Redis

## 快速开始

1. 克隆项目到本地：

```
git clone https://github.com/Mlittleee/mybasketballforum.git

git clone https://github.com/Mlittleee/my_basketball_forum-front.git
```

2. 安装前端依赖并运行：

```
cd my_basketball_forum-front
npm install
npm run serve
```

3. 启动后端服务：

```
cd mybasketballforum
```

```
在你的ide中启动MyBasketballForumApplication的主方法
```

4.数据库安装（需要提前安装好 MySQL 和使用相应的数据库管理工具）
在你的数据库中执行下述语句

```
SET NAMES utf8mb4;
--
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '密码',
  `status` int NULL DEFAULT NULL COMMENT '用户状态',
  `email` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '用户邮箱',
  `role_id` int NULL DEFAULT NULL COMMENT '用户角色',
  `gender` int NULL DEFAULT NULL COMMENT '性别',
  `sign` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '用户签名',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
	`post_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `description` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '帖子描述',
  `title` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '标题',
  `content` TEXT(65535) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '内容',
  `create_time` DATETIME NULL COMMENT'创建时间',
  `view_count` BIGINT NULL DEFAULT NULL COMMENT '浏览量',
  `like_count` BIGINT NULL DEFAULT NULL COMMENT '点赞量',
  `category_name` varchar(255) NULL COMMENT '板块名',
  `user_id` int COMMENT '帖子所属用户id',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '板块id',
  `description` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '板块描述',
  `user_id` int COMMENT '所属管理员id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '板块名',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '评论内容',
  `like_count` BIGINT COMMENT '点赞数',
  `post_id` int COMMENT '所属帖子id',
  `upper_id` int COMMENT '上一级评论id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '评论用户名',
  `create_time` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL,      -- 前端完成时间获取
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '标签名',
  `post_id` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '帖子id',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `thumb`;
CREATE TABLE `thumb`  (
  `thumb_id` int NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `user_id` int COMMENT '点赞所属用户id',
  `post_id` int COMMENT '帖子id',
  PRIMARY KEY (`thumb_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for favor
-- ----------------------------
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor`  (
  `favor_id` int NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` int COMMENT '收藏所属用户id',
  `post_id` int COMMENT '帖子id',
  PRIMARY KEY (`favor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tip
-- ----------------------------
DROP TABLE IF EXISTS `tip`;
CREATE TABLE `tip`  (
   `tip_id` int NOT NULL AUTO_INCREMENT COMMENT '名句id',
   `content` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '名句内容',
   `author` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '作者',
    PRIMARY KEY (`tip_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for postcard
-- ----------------------------
DROP TABLE IF EXISTS `postcard`;
CREATE TABLE `postcard`  (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '帖子id',
    `description` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '帖子描述',
    `title` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '标题',
    `content` TEXT(65535) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '内容',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `view_count` BIGINT NULL DEFAULT NULL COMMENT '浏览量',
    `like_count` BIGINT NULL DEFAULT NULL COMMENT '点赞量',
    `category_name` varchar(255) NULL COMMENT '板块名',
    `user_id` int COMMENT '帖子所属用户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
```

(注意根据你的数据库的信息更改 application 配置文件)

5.访问应用：

在浏览器中打开 `http://localhost:8080` 即可访问应用。

## 贡献

感谢以下贡献者对项目的贡献：

- 前端部分
  [![Contributors](https://contrib.rocks/image?repo=Mlittleee/my_basketball_forum-front)](https://contrib.rocks/image?repo=Mlittleee/my_basketball_forum-front)

- 后端部分
  [![Contributors](https://contrib.rocks/image?repo=Mlittleee/mybasketballforum)](https://contrib.rocks/image?repo=Mlittleee/mybasketballforum)

欢迎对项目进行贡献和提供反馈意见(但我们不一定会修改 LOL）。

## 许可证

该项目基于 MIT 许可证。更多信息请参阅 [LICENSE](../LICENSE) 文件。
