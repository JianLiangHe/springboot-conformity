/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.40 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

/*Table structure for table `t_log` */

DROP TABLE IF EXISTS `t_log`;

CREATE TABLE `t_log` (
  `log_id` varchar(64) DEFAULT NULL COMMENT 'id',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `remote_addr` varchar(64) DEFAULT NULL COMMENT '远程地址',
  `request_uri` varchar(64) DEFAULT NULL COMMENT '请求uri',
  `method` varchar(64) DEFAULT NULL COMMENT '方法',
  `params` varchar(64) DEFAULT NULL COMMENT '参数',
  `exception` varchar(64) DEFAULT NULL COMMENT '异常',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `operate_date` datetime DEFAULT NULL COMMENT '操作时间',
  `timeout` varchar(64) DEFAULT NULL COMMENT '超时'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
