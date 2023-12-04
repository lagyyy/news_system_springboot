/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : news

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 04/12/2023 20:30:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员密码',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'WcTiq2OL46eUrT/Gto9HMQ==', '第一新闻', 'https://img2.woyaogexing.com/2020/06/19/02d268db09ff4e8e9608fd64adbbeed8!400x400.jpeg', '2023-11-29 19:39:36');

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告内容',
  `publish_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '栏目名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '栏目描述',
  `flag_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '最新', NULL, '0');
INSERT INTO `category` VALUES (2, '财经', NULL, '0');
INSERT INTO `category` VALUES (3, '科技', NULL, '0');
INSERT INTO `category` VALUES (4, '热点', NULL, '0');
INSERT INTO `category` VALUES (5, '国际', NULL, '0');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `news_id` int NOT NULL COMMENT '文章id',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `user_id` bigint NULL DEFAULT -1 COMMENT '根评论id',
  `content` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint NULL DEFAULT -1 COMMENT '所回复的目标评论的userid',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '回复目标评论id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除评论时间',
  `like` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '存储点赞人ID数组',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (17, 1, '0', 1, '回复weqedadsd', 3, NULL, '2022-01-29 22:38:00', 0, '2022-01-29 22:38:00', '[\"2\",\"3\"]');
INSERT INTO `comment` VALUES (18, 1, '0', 1, 'sdasd', -1, NULL, '2022-01-29 23:18:19', 0, '2022-01-29 22:38:00', '[\"2\",\"3\"]');
INSERT INTO `comment` VALUES (23, 1, '1', 2, '回复友链评论3', 1, 17, '2022-01-30 10:08:50', 0, '2022-01-29 22:38:00', '[\"1\",\"2\",\"3\"]');
INSERT INTO `comment` VALUES (24, 1, '1', 2, '友链评论4444', -1, NULL, '2022-01-30 10:09:03', 0, '2022-01-29 22:38:00', '[\"1\",\"2\",\"3\"]');
INSERT INTO `comment` VALUES (25, 1, '1', 2, '收到的', 1, NULL, '2022-01-30 10:13:28', 0, '2022-01-29 22:38:00', '[\"1\",\"2\",\"3\"]');
INSERT INTO `comment` VALUES (26, 1, '0', 3, 'sda', -1, NULL, '2022-01-30 10:39:05', 0, '2022-01-29 22:38:00', '[\"1\",\"2\",\"3\"]');
INSERT INTO `comment` VALUES (27, 1, '0', 3, '说你咋地', 1, 17, '2022-01-30 17:19:30', 0, '2022-01-29 22:38:00', '[\"1\",\"2\",\"3\"]');
INSERT INTO `comment` VALUES (28, 1, '0', 3, 'sdad', 1, 17, '2022-01-31 11:11:20', 0, '2022-01-29 22:38:00', '[\"1\",\"2\",\"3\"]');
INSERT INTO `comment` VALUES (29, 1, '0', 3, '你说是的ad', -1, 17, '2022-01-31 14:10:11', 0, '2022-01-29 22:38:00', '[\"1\",\"2\",\"3\"]');
INSERT INTO `comment` VALUES (31, 1, '1', 3, '这是一条测试评论', 0, 17, '2022-10-24 16:22:21', 0, '2022-01-29 22:38:00', '[]');
INSERT INTO `comment` VALUES (53, 0, '0', 15, 'testttttttttttttttt', -1, NULL, '2023-11-30 21:10:06', 0, NULL, NULL);
INSERT INTO `comment` VALUES (54, 0, '0', 15, '2344SDF是德国水电费', -1, NULL, '2023-11-30 21:11:08', 0, NULL, NULL);
INSERT INTO `comment` VALUES (57, 1, '0', 15, 'dfsf', -1, NULL, '2023-12-02 21:25:04', 0, NULL, NULL);
INSERT INTO `comment` VALUES (58, 7, '0', 15, 'etst', -1, NULL, '2023-12-04 14:04:27', 0, NULL, NULL);

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新闻标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '新闻内容',
  `category_id` int NULL DEFAULT NULL COMMENT '关联栏目',
  `publish_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布日期',
  `flag_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻是否删除',
  `admin_id` bigint NULL DEFAULT NULL COMMENT '标识发布者',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id` ASC) USING BTREE,
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '被清北人大学子争抢的“鹅腿阿姨”火出圈，阿姨多日未出摊，本人发视频哽咽：压力太大，这两天不敢干活了', '11月28日晚上11点29分，陈阿姨在群聊内发信息称：“对不起，这几天都不做，太乱了”，称给没有取鹅腿的同学退钱。据现场值班保安介绍，之前，阿姨每晚九点左右出摊，有时凌晨一点还在守摊。走红这几天，不到一小时就能卖完收摊。一位等候的学生说，朋友圈的同学晒出抢到鹅腿的图片，她刷到后立马从宿舍床上爬起来抢，“抢到一次就够了，也不能天天来吃这个”。有主播在直播阿姨卖鹅腿的全过程，另一部手机展示他的直播成绩——昨晚共有三万人看过。他告诉记者，他已在这里（北京大学西南门）连续直播三晚了。根据他的直播记录，第一晚，鹅腿56分钟卖完。昨晚，30分钟。今晚，23分钟。11月29日，“鹅腿阿姨”开通抖音首次发视频，哽咽表示压力太大了。视频中阿姨几度哽咽，直言社会量太大，自己就是平平常常的人，就想平平安安做烧烤，人流量太大，压力太大，这两天不敢干活了。\r\n\r\n此前报道>>\r\n\r\n清北人大三校爆发鹅腿之争，鹅腿阿姨回应：每个学校都会去卖几天；人大食堂：将推出烤鹅腿，15元一个不限量\r\n\r\n近日，“鹅腿阿姨清北之争”话题引爆网络。\r\n\r\n据红星新闻报道，鹅腿阿姨在北京大学、清华大学、中国人民大学都有微信群，同学们在群里订购后，阿姨每晚9点左右到校门口送货。11月26日晚，一周没来北京大学的鹅腿阿姨“回归北大”，现场还有从哈尔滨赶来的“吃货”。鹅腿阿姨回应大家的热情抢购称，“我家孩子在家上学，看到这边的孩子，就想到我家孩子。”据北京晚报报道，“鹅腿阿姨”最早是在人民大学周边卖烤鹅腿，每天晚上九点多出摊，已经卖了近两年。\r\n\r\n26日晚间，一名排队买鹅腿的学生称，“鹅腿阿姨”在群里收了20个红包。“听说每个群里每天只接受20个人预定，光我知道的清华就有9个群了。”他说，“今天是中午12点多开始在群里接龙预定的，不到半小时就定满了。”图片源自北京晚报微信公众号\r\n\r\n“鹅腿阿姨”则表示自己不太会用手机，很多群都是学生帮忙建的。“北大和人大群里天天有孩子问，什么时候能吃上烤鹅腿，也不能光在清华卖，每个学校都得轮流去几天。”阿姨说。\r\n\r\n阿姨表示，这些鹅腿都是她亲手冲洗、切配、腌制和烤制的。固定供货商把鹅腿肉配到家后，每天早上6点多就起床开始收拾，一直忙到晚上天黑，两只手都被冷水泡得指关节肿大，“孩子们这么喜欢也就挺值得。”\r\n\r\n另据读秒财经报道，中国人民大学食堂工作人员表示，食堂之前没有烤鹅腿，如今准备在食堂上新这一菜品，有可能在11月27日晚5点半以后开始售卖。鹅腿定价15元一个，目前没有限量的打算。\r\n\r\n【来源：综合九派新闻视频、当事人账号、红星新闻、北京晚报、读秒财经等】\r\n\r\n声明：此文版权归原作者所有，若有来源错误或者侵犯您的合法权益，您可通过邮箱与我们取得联系，我们将及时进行处理。邮箱地址：jpbl@jp.jiupainews.com', 1, '2023-11-29 19:02:54', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (2, '桂林这位九旬老奶奶火了！超4000万网友围观', '来源：桂林晚报\r\n\r\n最近，荔浦一位泼墨作画的九旬老奶奶在网络上走红，光是其孙子在个人短视频账号发布的10条相关短视频，累计播放量就超过4000万次。网友纷纷称赞奶奶的画作好看、大气、有意境。\r\n\r\n这位老奶奶名叫伍桂兰。日前，记者来到荔浦找到了老人，和她聊起了成为网红的感受。\r\n\r\n九旬老人泼墨作画\r\n\r\n视频火了\r\n\r\n这位老奶奶名叫伍桂兰，是荔浦东昌镇人，目前大多数时间和孙子罗科通一家生活在荔浦城区。近日，记者来到荔浦见到了伍桂兰，虽然老人家头发花白，还有点驼背，但满脸笑容，精神状态很好。\r\n\r\n\r\n93岁的老人伍桂兰。\r\n\r\n记者询问得知，伍桂兰1930年出生，今年93岁，养育了4个儿子和4个女儿，如今已是五世同堂，最大的女儿72岁，最小的曾孙1岁半，整个大家庭有60多人。孙子罗科通为了照顾奶奶，2021年从桂林回到荔浦，在城区荔松路租了一栋6层楼房开设画室。\r\n\r\n“奶奶住在三楼，泼墨创作时会到6楼天台，那里空间大。”罗科通说，泼墨画创作跟一般的画画不一样，必须让底色干了再画上一层，有时候画一幅画，奶奶要花一周的时间。创作前，奶奶会跟他交流想法，他则根据奶奶的要求做好准备工作，包括铺好夹宣，调好颜料等。“画画不仅耗费脑力，长时间站立也费体力，所以奶奶的作品并不多。”\r\n\r\n罗科通说，自己从今年6月28日开始发布奶奶画画的视频，目前已发了10条，截止到11月20日，这些视频累计播放量超过4000万次，评论3万余条，点赞75万个。“奶奶在网上走红是从今年10月1日开始的。”罗科通说，当天下午1点多，他发布了一条奶奶创作山水画为国庆献礼的视频，这条视频短短两天的播放量就达到1700多万次，转发量、评论数都很高，“一开始我还和网友互动，后来已经完全看不过来了。”\r\n\r\n记者看到，在这则时长1分44秒的视频里，老人拿着画笔蘸上黑色颜料，先在桌面铺开的宣纸上随意画了一番，再用画笔点缀形成黑色区域，之后在纸的另一部分用其他颜色点缀、描绘，接下来用纸杯在黑色区域浇上蓝色、浅绿、黄色的颜料……渐渐地，一幅充满意境的泼墨山水画就呈现出来了。\r\n\r\n\r\n孙子罗科通给伍桂兰的画作起名为《森林》。\r\n\r\n“奶奶好棒”“高手在民间”“太厉害了”“出神入化”……在相关短视频的评论区，这些都是出现频率较高的评语。广东网友“兖哥的一点甜”称赞，“接地气的手法、高端的作品，年龄不是问题，主要有颗火红的心。”\r\n\r\n画画纯粹为开心\r\n\r\n老人吐露长寿秘诀\r\n\r\n“奶奶画画开始于2021年，当时纯粹是为开心，她自己想画，我们就让她画。”孙女罗浩玲介绍说，爷爷是位手艺人，雕刻、画画、唱戏等都会，奶奶可能当时也耳濡目染吧。2021年，哥哥罗科通回荔浦开了画室，把奶奶也接到身边，奶奶看到画笔兴致渐起，就开始随心所欲地画起来了，她自己也蛮开心。\r\n\r\n采访时记者观察到，伍桂兰如今听力不太好，需要人大声在耳边说话才能听见，但视力还不错，身体依然健朗，能自己扶着栏杆上下楼。\r\n\r\n据了解，老奶奶除了大部分时间在城区和孙子住以外，时不时还要回老家东昌镇的村里生活一段时间，短则一周，长则一两个月。老人在村里生活都是自理，不但自己做饭，还种菜，有时还会去看看中风在家的大儿子。\r\n\r\n“奶奶不挑食，这几年牙掉光后，除了不能吃太硬的食物，吃得相对清淡，蔬菜多一些，但其他食物也愿意尝试，即使咬不动也会尝尝味道。”罗浩玲说。\r\n\r\n面对记者，伍桂兰透露了自己健康长寿的秘诀，就是闲事少管，不记仇，保持对生活的热爱。她说，自己从不把烦心事记心上，平时在家里喜欢跟大家坐在一起，即使听不清说些什么，但享受这种其乐融融的氛围。\r\n\r\n罗浩玲觉得奶奶长寿健康很重要的一点是，睡眠质量非常好。“奶奶一般晚上8点休息，早上6点起床，一觉睡到天亮，不起夜。中午有时也会睡一两个小时。”\r\n\r\n家人称不会售卖画作\r\n\r\n将作为传家宝保存\r\n\r\n“您的视频在网上很受欢迎，有数千万人看过。”听到记者的称赞，伍桂兰高兴地说：“开心，没想到我这么老了还有价值。”伍桂兰还说，孙子罗科通常常把网友的评论念给她听，一开始自己还不懂，也没啥想法，后来听得多了，知道有很多人喜欢自己，自己画画的视频能给大家带来欢乐，她也很高兴。\r\n\r\n\r\n伍桂兰在专心画画。\r\n\r\n记者采访时了解到，老奶奶泼墨作画用的纸张是夹宣，最大的画作尺寸是2.4米×1米，最小的画作尺寸是0.68米×0.68米。老奶奶的画作除了两三幅在画室展示，其他的都被罗科通细心地收藏起来了。\r\n\r\n奶奶走红后，很多网友询问老人的画作是否售卖，并询问价格。\r\n\r\n罗科通说，面对网友的好意，他都婉拒了。“我发奶奶画画的视频初衷，只是为了记录、分享奶奶的晚年生活，留下奶奶的影像资料，并没有想借此卖画赚钱。奶奶是我们大家族的宝贝和主心骨，我们将把她的画作为传家宝保存，好好传下去。”罗科通说。\r\n\r\n\r\n伍桂兰和孙子、孙女在画作前合影。\r\n\r\n“艺术来自生活，今后我会发一些奶奶日常生活的视频，特别是在农村的。”罗科通表示，虽然奶奶画画的视频很受关注，但他觉得也应该展现奶奶平凡普通的日常生活，让广大网友看到老年人积极乐观的生活态度。\r\n\r\n记者邢刚', 2, '2023-11-29 19:22:10', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (3, '时政微观察丨法治是最好的营商环境', '时政微观察丨法治是最好的营商环境\r\n原创2023-11-29 09:28·央视新闻\r\n\r\n11月27日，中共中央政治局就加强涉外法制建设进行第十次集体学习。习近平总书记在主持学习时强调，深刻认识做好涉外法治工作的重要性和紧迫性，建设同高质量发展、高水平开放要求相适应的涉外法治体系和能力，为中国式现代化行稳致远营造有利法治条件和外部环境。\r\n\r\n开放是人类文明进步的重要动力，是世界繁荣发展的必由之路。而开放的过程，离不开法治的保驾护航。\r\n\r\n如何在法治基础上推进高水平对外开放，在扩大开放中推进涉外法治建设？《时政微观察》为你解读。\r\n\r\n完善涉外法律体系\r\n\r\n不久前，广州仲裁委智能庭室内，一场仲裁庭审顺利完成。案件从立案到进入审理程序不到40天，这样处理国际商事纠纷的效率获得了各方当事人的称赞。\r\n\r\n自2019年11月尝试跨国远程庭审以来，广州仲裁委以在线争议纠纷解决平台实现“谈判、调解、仲裁”三种机制衔接，为跨境商事主体提供高效、易用、低成本的一站式在线纠纷解决服务。\r\n\r\n随着对外开放水平不断提高，中国与世界的联系越来越密切，越来越多的中国企业走向世界，越来越多的外资企业来华兴业。要营造一流营商环境，运用法治思维和法治方式应对外部风险挑战，首先需要完善涉外法律体系。\r\n\r\n\r\n△2023年服贸会法律服务专题展现场\r\n\r\n习近平总书记在主持集体学习时强调，法治是最好的营商环境，要完善公开透明的涉外法律体系，加强知识产权保护，维护外资企业合法权益，用好国内国际两类规则，营造市场化、法治化、国际化一流营商环境。\r\n\r\n今年，我国加快招标投标法、会计法、保险法、反不正当竞争法等重要法律的修订工作。目前，司法部正在牵头修订仲裁法，进一步完善具有中国特色、提高涉外仲裁制度开放性的仲裁制度。\r\n\r\n为加快提升涉外法律服务水平，我国在北京、上海、广东、海南等地加快推进国际商事仲裁中心建设。同时，充分发挥“一带一路”律师联盟作用，为国际贸易、跨境投资、海事海商等提供更加优质的法律服务。\r\n\r\n主动对接国际经贸规则\r\n\r\n“要主动对接、积极吸纳高标准国际经贸规则，稳步扩大制度型开放，提升贸易和投资自由化便利化水平，建设更高水平开放型经济新体制。”习近平总书记在主持集体学习时强调。\r\n\r\n这既是中国进一步以开放促改革的需要，也是中国越来越深入融入全球化、参与国际竞争的需要。\r\n\r\n一方面，伴随产业创新发展，全球价值链深度重塑，要求各国在规则、规制、管理、标准等方面兼容，实现各要素无缝衔接和深层融合发展。另一方面，全球产业链供应链区域化、本土化、短链化趋势明显，亟需各国加强经贸合作。\r\n\r\n\r\n△2023年11月28日，X8155次中欧班列从西安国际港站缓缓驶出，开往德国汉堡。\r\n\r\n今年，我国率先在上海、广东、天津、福建、北京等具备条件的自由贸易试验区和海南自由贸易港，试点对接相关国际高标准经贸规则，聚焦货物贸易、服务贸易、商务人员临时入境、数字贸易、营商环境、风险防控等，提出试点政策措施和风险防控举措。\r\n\r\n比如，结合经营主体诉求建议，允许试点地区内的外商投资企业内部调动专家的随行配偶和家属享有与该专家相同的入境和停居留期限，为外国专家的家人来华共同生活提供便利条件等。\r\n\r\n对接国际高标准经贸规则，有利于充分运用国内国际两个市场两种资源，为构建新发展格局、推动高质量发展增添动力。\r\n\r\n对标国际先进水平\r\n\r\n“企业名称：上海百家合信息技术发展有限公司；投资总额：23700万美元”……\r\n\r\n在上海自贸试验区保税区展示馆，一张编号为“No.000001”的《中国（上海）自由贸易试验区外商投资企业备案证明》有着重要意义。\r\n\r\n2013年9月29日，我国第一个自贸试验区——中国（上海）自由贸易试验区在上海外高桥挂牌。同一天，这张“1号”备案证颁发。备案证所有者是中国企业百视通和美国微软公司合资成立的上海百家合信息技术发展有限公司。\r\n\r\n百视通牵手微软，得益于自贸区的进一步对外开放政策。\r\n\r\n\r\n△海南自贸港洋浦经济开发区\r\n\r\n11月27日，习近平总书记在主持集体学习时指出，要对标国际先进水平，把自由贸易试验区等高水平对外开放的有效举措和成熟经验及时上升为法律，打造开放层次更高、营商环境更优、辐射作用更强的对外开放新高地。\r\n\r\n这为进一步打造对外开放新高地指明了方向。\r\n\r\n10年来，自贸试验区不断扩围发展，在外商投资、跨境服务贸易等领域率先实施负面清单管理制度，在转变政府职能、金融服务实体经济等方面实践诸多首创性改革等，为全面深化改革和扩大开放进行了有效探索、积累了宝贵经验。\r\n\r\n未来，自贸试验区将继续发挥好改革开放综合试验平台作用，为全面深化改革、扩大开放探索新路径。\r\n\r\n\r\n△2023年11月11日，中国（新疆）自由贸易试验区喀什片区揭牌仪式在喀什综合保税区举行，首批35家企业入驻。\r\n\r\n法治同开放相伴而行，对外开放向前推进一步，涉外法治建设就要跟进一步。\r\n\r\n在法治基础上推进高水平对外开放，在扩大开放中推进涉外法治建设，将不断夯实高水平开放的法治根基，为高水平对外开放提供优质法治保障。\r\n\r\n\r\n\r\n监制丨耿志民\r\n\r\n制片人丨兴来\r\n\r\n执笔丨宁黎黎\r\n\r\n视觉丨江雨航\r\n\r\n责任编辑：张景', 3, '2023-11-29 19:24:14', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (4, '滴滴再次发文致歉：初步确定事故起因是底层系统软件发生故障，并非网传的“遭受攻击”', '滴滴在小程序发布道歉声明，并补偿所有用户10元优惠券。 但许多网友表示优惠券加载异常，无法领取。\r\n\r\n\r\n滴滴出行今日就11月27日夜间发生的系统故障进行了说明，并再一次道歉。滴滴表示，目前滴滴 App 的所有服务已经全部恢复。初步确定，这起事故的起因是底层系统软件发生故障，并非网传的“遭受攻击”，后续我们将深入开展技术风险隐患排查和升级工作，全面保障服务稳定性。', 4, '2023-11-29 19:28:01', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (5, 'test1', 'test1', 1, '2023-12-02 22:59:01', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (7, 'test3', 'test3', 1, '2023-12-02 22:59:39', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (8, 'test4', 'test4', 1, '2023-12-02 22:59:56', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (9, 'test5', 'test5', 1, '2023-12-02 23:00:11', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (10, 'test6', 'test7', 1, '2023-12-02 23:00:34', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (11, 'test7', 'ttttttte', 1, '2023-12-02 23:00:56', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (14, '213', '<h1>富强、民主、文明、和谐</h1><br/><h2><font color=\"red\">自由、平等、公正、法治</font></h2><br/><h3><font color=\"blue\">爱国、敬业、诚信、友善</font></h3>', 1, '2023-12-02 23:01:41', '0', 1, NULL, NULL);
INSERT INTO `news` VALUES (16, 'test123', '<p>1111111111123333333333333333333333333<img src=\"http://127.0.0.1:8089/file/438d10a2-1fda-4eae-a5c0-01a0bb74b1662.jpg\" alt=\"null\" data-href=\"null\" style=\"width: 30%;\"/></p>', NULL, '2023-12-03 18:44:03', '0', 1, '1231111', 'blob:http://localhost:8080/495bf2a9-e30b-499b-b6d3-a50abb133dab');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `we_chat` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '李四', '偶尔躲躲乌云', 'TCYI4TXy1U4gfRJGuKj40w==', '1', '0', '23412332@qq.com', '16624769179', '1', 'https://ngz.oss-cn-hangzhou.aliyuncs.com/loveP/test.jpg', NULL, '2022-01-05 09:01:56.000000', 1, '2022-01-30 15:37:03', 0, NULL);
INSERT INTO `sys_user` VALUES (2, '王五', '当归。', 'TCYI4TXy1U4gfRJGuKj40w==', '1', '0', NULL, NULL, '0', 'https://ngz.oss-cn-hangzhou.aliyuncs.com/bbs_img/2022-08-03%2015%3A44%3A4216595126825867390.png', NULL, '2022-01-05 13:28:43.000000', NULL, '2022-01-05 13:28:43', 0, NULL);
INSERT INTO `sys_user` VALUES (3, 'sg2', '起灵', 'TCYI4TXy1U4gfRJGuKj40w==', '1', '0', '23412332@qq.com', '16624769178', '0', 'https://ngz.oss-cn-hangzhou.aliyuncs.com/bbs_img/2022-08-03%2016%3A43%3A2916595162099126931.png', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (5, 'sg2233', 'tteqe', 'TCYI4TXy1U4gfRJGuKj40w==', '1', '0', NULL, '18246845873', '1', 'https://img2.woyaogexing.com/2020/06/19/02d268db09ff4e8e9608fd64adbbeed8!400x400.jpeg', NULL, '2022-01-06 03:51:13.000000', NULL, '2022-01-06 07:00:50', 0, NULL);
INSERT INTO `sys_user` VALUES (6, 'sangeng', 'sangeng', '$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy', '1', '0', '2312321', '17777777777', '0', 'https://img2.woyaogexing.com/2020/06/19/02d268db09ff4e8e9608fd64adbbeed8!400x400.jpeg', NULL, '2022-01-16 06:54:26.000000', NULL, '2022-01-16 07:06:34', 0, NULL);
INSERT INTO `sys_user` VALUES (8, 'weixin', 'weixin', '$2a$10$y3k3fnMZsBNihsVLXWfI8uMNueVXBI08k.LzWYaKsW8CW7xXy18wC', '0', '0', 'weixin@qq.com', NULL, NULL, 'https://img2.woyaogexing.com/2020/06/19/02d268db09ff4e8e9608fd64adbbeed8!400x400.jpeg', -1, '2022-01-30 17:18:44.000000', -1, '2022-01-30 17:18:44', 0, NULL);
INSERT INTO `sys_user` VALUES (9, 'NULL', '未命名', 'TCYI4TXy1U4gfRJGuKj40w==', '0', '0', NULL, '12412', NULL, '', NULL, '2022-12-09 14:05:55.953000', NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (10, 'NULL', '未命名', 'TCYI4TXy1U4gfRJGuKj40w==', '0', '0', NULL, '16624769888', NULL, '', NULL, '2022-12-09 14:11:29.232000', NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (11, 'NULL', '未命名', 'TCYI4TXy1U4gfRJGuKj40w==', '0', '0', NULL, '16624769177', NULL, 'https://ngz.oss-cn-hangzhou.aliyuncs.com/bbs_img/avatar.jpg', NULL, '2022-12-09 14:16:24.497000', NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (12, 'NULL', '未命名', 'WcTiq2OL46eUrT/Gto9HMQ==', '0', '0', NULL, '16624769111', NULL, 'https://img2.woyaogexing.com/2020/06/19/02d268db09ff4e8e9608fd64adbbeed8!400x400.jpeg', NULL, '2023-11-30 20:03:22.305000', NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (13, 'NULL', '未命名', 'wZC33CcRJNL3kvk2G7akWQ==', '0', '0', NULL, '12345678', NULL, 'https://ngz.oss-cn-hangzhou.aliyuncs.com/bbs_img/avatar.jpg', NULL, '2023-11-30 20:08:40.941000', NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (14, 'NULL', '未命名', 'WcTiq2OL46eUrT/Gto9HMQ==', '0', '0', NULL, NULL, NULL, 'https://ngz.oss-cn-hangzhou.aliyuncs.com/bbs_img/avatar.jpg', NULL, '2023-11-30 20:13:13.943000', NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (15, 'NULL', '未命名', 'WcTiq2OL46eUrT/Gto9HMQ==', '0', '0', NULL, '15977154728', NULL, 'https://ngz.oss-cn-hangzhou.aliyuncs.com/bbs_img/avatar.jpg', NULL, '2023-11-30 20:17:16.070000', NULL, NULL, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
