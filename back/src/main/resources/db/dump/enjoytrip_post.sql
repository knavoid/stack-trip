-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `user_code` int NOT NULL,
  `subject` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `type` int NOT NULL,
  `regtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `views` int DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `fk_board_user1_idx` (`user_code`),
  CONSTRAINT `fk_board_user1` FOREIGN KEY (`user_code`) REFERENCES `user` (`user_code`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (18,5,'일산호수공원에 다녀왔습니다!! ^^','고양시는 삶의 터전인 자연을 잘 보전하여 그 자연 속에서 청소년들이 미래에 대한 꿈을 꾸고, 어른들은 추억과 향수를 느끼는 우리 모두가 보다 나은 삶을 살아 갈 수 있는 배움과 체험의 장으로 풍요로운 생활공간을 창출하여 시민 여러분의 삶의 질을 높이기 위해 최선을 다하고 있습니다...',3,'2023-05-20 04:38:18',617),(19,6,'강원도 홍천 삼생마을로 놀러오세요!','강원도 홍천의 삼생마을은 군청소재지에서 35km 떨어진 화양강 상류의 청정지역으로 넉넉한 인심과 예의를 숭상하며 정감이 넘치는 사람들이 모여 살아가는 자연경관이 아름다운 고장이다. 동쪽으로는 내면과 평창군 봉평면, 서쪽으로는 화촌면과 내촌면, 남쪽으로는 동면과 횡성군 청일면, 북쪽으로는 인제군 상남면에 접하고 있는 비교적 고산지대이면서도 평야가 많은 곳이다. 삼생마을은 전형적인 농업면으로 수리시설이 발달된 옥토에서 질 좋은 수라쌀과 특산품으로는 매년 열리는 찰옥수수축제와 함께 인지도를 높여가고 있는 찰옥수수, 전국적으로 높은 인지도와 원조격의 전통을 자랑하는 옥수수찐빵, 오랜 작목반 생산으로 우수한 품질로 승부하는 느타리버섯이나 영지버섯, 생산을 시작한지 오래되지 않아 높은 지심의 밭에서 길러낸 6년근인삼, 그리고 옥선주 등이 있다. 승고적으로는 주변의 경관이 매우 아름다워 여름철이면 많은 피서인파가 찾아오고 있는 서봉사와 모둘자리 관광농원, 예술촌 등의 휴양시설은 어디에 내놓아도 손색이 없을 정도로 독특하면서도 훌륭한 시설을 갖추고 있으며 특히 서봉사계곡이나 아미산등산로, 생곡저수지 등은 삼생마을 특유의 청정한 자연환경을 자랑하고 있어, 자연관광과 휴양관광을 함께 즐길 수 있는 농촌마을이다.\n\n\n\n이\n렇\n게\n엔\n터\n를\n쳐\n봐\n요',3,'2023-05-20 04:38:18',349),(24,5,'게시판 이용 수칙','좋은말, 고운말 사용합시다.',1,'2023-05-22 00:49:30',1746),(27,5,'5월 이벤트 관련','5월 이벤트 시작합니다!',2,'2023-05-22 02:42:53',508),(28,5,'6월 이벤트 관련','6월 이벤트는 아직입니다!',2,'2023-05-22 02:43:41',501),(29,7,'잠실종합운동장에 대한 고찰','서울종합운동장(잠실종합운동장)은 송파구 잠실동에 위치하고 있으며, 1976년 12월에 완공하였다. 운동장을 중심으로 남쪽길 건너편에 아시아 선수촌아파트와 공원이 있으며, 동쪽으로는 잠실아파트 대단지와 북쪽으로 한강과 서족으로 탄천을 인접, 그리고 동남쪽 3.5km 지점에는 올림픽 공원이 자리잡고 있다. 지하철 2호선, 9호선 종합운동장역과 연계되어 있어 이용하기 쉽고, 올림픽주경기장, 야구장, 제1ㆍ2 수영장, 실내체육관 등 대형경기장과 그 외 육상보조경기장을 비롯한 서울시교육청에서 관리 및 운영하고 있는 학생실내체육관이 있으며, 야외예식을 할 수 있는 넓은 체육공원 등 다양한 경기장 시설을 갖추고 있다. 특히, 올림픽주경기장은 제10회 아시아 경기대회와 제24회 서울올림픽대회의 개회식과 폐회식, 육상ㆍ축구 등을 치룬 바 있는 역사적 의미를 갖는 경기장으로서 많은 외국 관광객이 방문하고 있는 관광명소가 되었다. 2001년 상암 서울 월드컵 경기장 개장 이후, 축구 경기는 줄어진 추세다.',3,'2023-05-23 00:27:09',28),(30,6,'프론트엔드를 잘하고 싶으면 이 글을 읽어주세요','프론트 엔드 개발은 사이트 방문자가 볼 수 있는 시각적 및 대화형 요소를 만드는 방법입니다. 여기에는 다음이 포함됩니다. HTML, CSS 자바 스크립트 슬라이드쇼, 사용자 인터페이스, 시뮬레이션, 게임 등과 같은 대화형 요소를 만들 수 있습니다.',4,'2023-05-23 00:30:23',202),(31,5,'안녕하세요','반갑습니다',4,'2023-05-23 00:32:20',16);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25  4:12:33
