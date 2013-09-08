-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 12, 2013 at 01:23 AM
-- Server version: 5.5.24
-- PHP Version: 5.3.10-1ubuntu3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `picvik`
--

-- --------------------------------------------------------

--
-- Table structure for table `picvik_activity`
--

CREATE TABLE IF NOT EXISTS `picvik_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `activity_type_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `activity_source_id` int(11) NOT NULL,
  `activity_source_type_id` int(11) NOT NULL,
  `data` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`activity_type_id`,`activity_source_id`,`activity_source_type_id`),
  KEY `activity_type_id` (`activity_type_id`),
  KEY `activity_source_id` (`activity_source_id`,`activity_source_type_id`),
  KEY `activity_source_type_id` (`activity_source_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Dumping data for table `picvik_activity`
--

INSERT INTO `picvik_activity` (`id`, `user_id`, `activity_type_id`, `timestamp`, `activity_source_id`, `activity_source_type_id`, `data`) VALUES
(21, 28, 2, '2013-01-07 12:41:14', 10, 1, 'Ananda  Created a New Picture Album.'),
(22, 28, 1, '2013-01-07 12:41:14', 17, 2, 'Ananda  Uploaded a New Picture.'),
(23, 28, 2, '2013-01-08 04:48:51', 11, 1, 'Ananda  Created a New Picture Album.'),
(24, 28, 1, '2013-01-08 04:48:51', 18, 2, 'Ananda  Uploaded a New Picture.'),
(25, 28, 2, '2013-01-11 10:07:17', 0, 1, 'Ananda  Created a New Picture Album.'),
(26, 28, 1, '2013-01-11 10:07:17', 13, 2, 'Ananda  Uploaded a New Picture.'),
(27, 28, 2, '2013-01-11 10:10:53', 0, 1, 'Ananda  Created a New Picture Album.'),
(28, 28, 1, '2013-01-11 10:10:53', 13, 2, 'Ananda  Uploaded a New Picture.'),
(29, 28, 2, '2013-01-11 10:17:43', 0, 1, 'Ananda  Created a New Picture Album.'),
(30, 28, 1, '2013-01-11 10:17:43', 13, 2, 'Ananda  Uploaded a New Picture.'),
(33, 28, 4, '2013-01-11 17:42:37', 8, 4, 'Ananda  Created a New Video channel.'),
(34, 28, 3, '2013-01-11 17:42:37', 9, 3, 'Ananda  Uploaded a New Video.');

-- --------------------------------------------------------

--
-- Table structure for table `picvik_activity_source_type`
--

CREATE TABLE IF NOT EXISTS `picvik_activity_source_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `picvik_activity_source_type`
--

INSERT INTO `picvik_activity_source_type` (`id`, `name`) VALUES
(1, 'picture_album'),
(2, 'picture'),
(3, 'new_video'),
(4, 'new_channel');

-- --------------------------------------------------------

--
-- Table structure for table `picvik_activity_type`
--

CREATE TABLE IF NOT EXISTS `picvik_activity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `picvik_activity_type`
--

INSERT INTO `picvik_activity_type` (`id`, `activity_name`) VALUES
(1, 'add_picture'),
(2, 'create_album'),
(3, 'new_video'),
(4, 'new_channel');

-- --------------------------------------------------------

--
-- Table structure for table `picvik_admin`
--

CREATE TABLE IF NOT EXISTS `picvik_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) NOT NULL,
  `password` varchar(120) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `picvik_admin`
--

INSERT INTO `picvik_admin` (`id`, `uname`, `password`) VALUES
(2, 'admin', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Table structure for table `picvik_comment`
--

CREATE TABLE IF NOT EXISTS `picvik_comment` (
  `commentid` int(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(512) DEFAULT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mediaid` int(20) NOT NULL,
  `mediatype` tinyint(11) NOT NULL,
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`commentid`),
  KEY `mediaid` (`mediaid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `picvik_comment`
--

INSERT INTO `picvik_comment` (`commentid`, `comment`, `datetime`, `mediaid`, `mediatype`, `uid`) VALUES
(1, 'demo comments', '2013-01-01 17:25:25', 3, 1, 28),
(2, 'demo comment2', '2013-01-01 17:27:27', 3, 1, 28),
(3, 'hello', '2013-01-01 19:09:18', 3, 1, 28),
(4, 'hello', '2013-01-01 19:16:55', 2, 1, 28),
(5, 'cool', '2013-01-01 19:17:11', 5, 1, 28),
(6, 'nice click', '2013-01-02 17:24:29', 10, 2, 28),
(7, 'wow', '2013-01-02 17:25:51', 10, 2, 28),
(8, 'comment1', '2013-01-02 17:28:21', 12, 2, 28),
(9, 'comment2', '2013-01-02 17:28:29', 12, 2, 28),
(10, 'comment3', '2013-01-02 17:28:36', 12, 2, 28),
(11, 'comment4', '2013-01-02 17:28:43', 12, 2, 28),
(12, 'comment5', '2013-01-02 17:29:00', 12, 2, 28),
(13, 'good one', '2013-01-02 17:34:10', 3, 2, 28),
(14, 'good one', '2013-01-02 17:34:51', 10, 2, 28),
(15, 'droid mania', '2013-01-02 17:38:59', 9, 2, 28),
(16, 'hello', '2013-01-06 18:48:19', 3, 4, 28),
(17, 'cool', '2013-01-06 19:34:08', 2, 4, 28),
(18, 'wow', '2013-01-06 19:35:19', 2, 4, 28),
(19, 'test', '2013-01-06 19:36:37', 2, 4, 28),
(20, 'test comment', '2013-01-06 19:36:51', 2, 4, 28),
(21, 'test comment\r\n', '2013-01-06 19:37:31', 2, 4, 28),
(22, 'super photos', '2013-01-07 03:57:48', 6, 1, 29),
(23, 'wow', '2013-01-07 04:02:57', 6, 1, 28),
(24, 'google', '2013-01-07 12:14:59', 6, 1, 28),
(25, 'demo', '2013-01-11 18:02:32', 1, 2, 28),
(26, 'good one', '2013-01-11 18:02:50', 1, 2, 28),
(27, 'nice', '2013-01-11 18:10:42', 1, 2, 28),
(28, 'cool', '2013-01-11 18:11:48', 1, 2, 28),
(29, 'dfsfsdf', '2013-01-11 18:19:34', 1, 2, 28),
(30, 'dasdasd', '2013-01-11 18:20:35', 1, 2, 28),
(31, 'sdhgjyttyjyt', '2013-01-11 18:21:27', 1, 2, 28),
(32, 'dsdfsdfsdfsd', '2013-01-11 18:21:40', 10, 2, 28),
(33, 'awesome\r\n', '2013-01-11 18:22:43', 1, 2, 28),
(34, 'nice', '2013-01-11 18:26:16', 8, 4, 28),
(35, 'demo comment', '2013-01-11 18:27:54', 3, 4, 28),
(36, 'sdafa', '2013-01-11 18:53:28', 4, 2, 28),
(37, 'ssss', '2013-01-11 19:20:26', 4, 4, 28);

-- --------------------------------------------------------

--
-- Table structure for table `picvik_media_tag`
--

CREATE TABLE IF NOT EXISTS `picvik_media_tag` (
  `mediaid` int(20) NOT NULL,
  `tagid` int(20) NOT NULL,
  PRIMARY KEY (`mediaid`,`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `picvik_my_activity`
--

CREATE TABLE IF NOT EXISTS `picvik_my_activity` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `activity_string` varchar(200) NOT NULL,
  `activity_type` int(11) NOT NULL,
  `activity_url` varchar(200) DEFAULT NULL,
  `activity_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `picvik_picture`
--

CREATE TABLE IF NOT EXISTS `picvik_picture` (
  `pictureid` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) DEFAULT 'Untitled',
  `description` varchar(512) DEFAULT NULL,
  `pictureurl` varchar(512) DEFAULT NULL,
  `privacy` tinyint(4) NOT NULL DEFAULT '1',
  `albumid` int(20) DEFAULT NULL,
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`pictureid`),
  KEY `albumid` (`albumid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `picvik_picture`
--

INSERT INTO `picvik_picture` (`pictureid`, `title`, `description`, `pictureurl`, `privacy`, `albumid`, `uid`) VALUES
(1, 'pic1', 'nothing', 'uploads/pictures/28-fb8e021d-dbf1-4aa4-bfae-7766d7875aa3-one.jpg', 1, 1, 28),
(4, 'pic1', 'nothing', 'uploads/pictures/28-194e11ff-4421-4004-88ea-ca0f62f6952c-noprofile.gif', 1, 3, 28),
(8, 'code', '', 'uploads/pictures/28-16d0e0b1-c38f-4701-8ccc-1e6d0214a350-big_772cd52c1950244f8428c5a700c4ccdfaf527a0f.jpg', 1, 5, 28),
(9, 'first', '', 'uploads/pictures/28-0404acc4-d004-415b-8017-3df5f85a62a3-IMAG0271.jpg', 2, 6, 28),
(10, 'second', '', 'uploads/pictures/28-7beb1dd3-5535-41e2-bf85-990c2f4a1f0d-IMG_2367.jpg', 1, 6, 28),
(12, 'fourth', '', 'uploads/pictures/28-df5e65b5-d8fb-4d3e-904f-8f9d5ec4bc62-IMG_2415.JPG', 1, 6, 28),
(13, '', '', 'uploads/pictures/28-46349190-c37e-4d42-9fff-9651bad1efde-IMG_2504.JPG', 2, 8, 28),
(15, '', '', 'uploads/pictures/28-5a64c181-0860-456e-8998-0296df940263-oDSC_1411.jpg', 2, 8, 28),
(16, 'private', '', 'uploads/pictures/28-05525077-7ef4-44c9-a7ca-4c71acd2a4d3-q3.png', 2, 9, 28),
(17, 'sample', '', 'uploads/pictures/28-8eb79032-e81b-4ce5-a1a4-72f1a6a1aa15-one.jpg', 1, 10, 28);

-- --------------------------------------------------------

--
-- Table structure for table `picvik_picture_album`
--

CREATE TABLE IF NOT EXISTS `picvik_picture_album` (
  `albumid` int(20) NOT NULL AUTO_INCREMENT,
  `albumname` varchar(120) DEFAULT 'Untitled',
  `description` varchar(512) DEFAULT NULL,
  `location` varchar(120) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `privacy` tinyint(4) NOT NULL DEFAULT '1',
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`albumid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `picvik_picture_album`
--

INSERT INTO `picvik_picture_album` (`albumid`, `albumname`, `description`, `location`, `date`, `privacy`, `uid`) VALUES
(1, 'demo1', 'demo desc', 'demo location', '2012-12-29', 1, 28),
(2, 'demo name2', 'demo desc', 'demo location2', '2012-12-29', 1, 28),
(3, 'demo name3', 'demodesc', 'demo location2', '2012-12-30', 1, 28),
(4, 'abcd', '', '', '2012-12-29', 1, 28),
(5, 'wall', 'wallpaper', 'demo location', '2012-12-29', 1, 28),
(6, 'google dev fest', 'went to google dev fest', 'bangalore', '2013-01-02', 1, 28),
(8, 'google dev fest in iiitb', 'awesome', 'bangalore', '2013-01-02', 2, 28),
(9, 'public', '', '', '2013-01-07', 1, 28),
(10, 'testing', '', '', '2013-01-07', 1, 28),
(11, 'sample album -1', '', '', '2012-12-29', 1, 28);

-- --------------------------------------------------------

--
-- Table structure for table `picvik_tag`
--

CREATE TABLE IF NOT EXISTS `picvik_tag` (
  `tagid` int(20) NOT NULL AUTO_INCREMENT,
  `tag` varchar(20) NOT NULL,
  PRIMARY KEY (`tagid`),
  KEY `tag` (`tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `picvik_user`
--

CREATE TABLE IF NOT EXISTS `picvik_user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) NOT NULL,
  `email` varchar(120) NOT NULL,
  `password` varchar(120) NOT NULL,
  `utype` int(11) NOT NULL DEFAULT '1',
  `regtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname` (`uname`,`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `picvik_user`
--

INSERT INTO `picvik_user` (`uid`, `uname`, `email`, `password`, `utype`, `regtime`, `status`) VALUES
(28, 'Ananda ', 'rahul.vit09@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', 1, '2012-12-31 19:14:51', 1),
(29, 'rahul', 'anandaprakashverma@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', 1, '2013-01-02 08:37:51', 2),
(30, 'username', 'demo@demo.com', '5f4dcc3b5aa765d61d8327deb882cf99', 1, '2013-01-11 19:49:17', 0);

-- --------------------------------------------------------

--
-- Table structure for table `picvik_userprofile`
--

CREATE TABLE IF NOT EXISTS `picvik_userprofile` (
  `pid` int(20) NOT NULL AUTO_INCREMENT,
  `img` varchar(512) DEFAULT NULL,
  `name` varchar(120) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address1` varchar(200) DEFAULT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `zip` varchar(15) DEFAULT NULL,
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `uid` (`uid`),
  KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `picvik_userprofile`
--

INSERT INTO `picvik_userprofile` (`pid`, `img`, `name`, `gender`, `dob`, `address1`, `address2`, `city`, `state`, `country`, `zip`, `uid`) VALUES
(4, 'uploads/profilepics/28-f9ad5989-239e-4988-8672-ecdaa9886ad8-one.jpg', 'apverma', 'male', '2013-01-09', 'prakash krishi sadan, das complex, civil lines', '', 'azamgarh', 'uttar pradesh', 'India', '276001', 28),
(5, 'ViewResources/images/noprofile.gif', 'Rahul', 'male', '2013-01-07', 'prakash krishi sadan, das complex, civil lines', '', 'azamgarh', 'uttar pradesh', 'India', '276001', 29),
(6, 'ViewResources/images/noprofile.gif', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 30);

-- --------------------------------------------------------

--
-- Table structure for table `picvik_user_activation`
--

CREATE TABLE IF NOT EXISTS `picvik_user_activation` (
  `activationKey` varchar(200) NOT NULL,
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`activationKey`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `picvik_user_activation`
--

INSERT INTO `picvik_user_activation` (`activationKey`, `uid`) VALUES
('04ccea81-ec33-473f-ba2a-9c1c63687293', 28),
('1b21d586-571c-4201-9565-a095c2a88dfa', 29),
('ae81ef9e-b9aa-47d5-8027-a38b3eb39eb2', 30);

-- --------------------------------------------------------

--
-- Table structure for table `picvik_video`
--

CREATE TABLE IF NOT EXISTS `picvik_video` (
  `videoid` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) DEFAULT 'Untitled',
  `Description` varchar(512) DEFAULT NULL,
  `videourl` varchar(512) DEFAULT NULL,
  `privacy` tinyint(4) NOT NULL DEFAULT '1',
  `channelid` int(20) NOT NULL,
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`videoid`),
  KEY `channelid` (`channelid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `picvik_video`
--

INSERT INTO `picvik_video` (`videoid`, `title`, `Description`, `videourl`, `privacy`, `channelid`, `uid`) VALUES
(2, 'demovideo1', '', 'uploads/videos/28-6beae2c2-2dbd-413e-9212-afa2d3c6fbb1-video.mp4', 1, 2, 28),
(3, 'demovid', '', 'uploads/videos/28-0da0f10f-b0f1-4724-b914-754d298afbf7-video.mp4', 1, 3, 28),
(4, 'demovid', '', 'uploads/videos/28-f8197d27-365f-434b-b587-7261eb1efb95-video2.mp4', 1, 3, 28),
(6, 'private piture', '', 'uploads/videos/28-286feaa9-615b-4cd7-9ad3-c43342907c4e-video.mp4', 2, 5, 28),
(7, 'sample 1', '', 'uploads/videos/28-df8a0b14-8a8c-4f4c-aaa9-a5b595384522-video2.mp4', 1, 6, 28),
(8, 'demo', '', 'uploads/videos/28-e8db013b-83ca-4f68-b56f-2b23dfed5b0e-video.mp4', 1, 7, 28),
(9, '', '', 'uploads/videos/28-9722c5e4-8693-49de-8f6b-91cffea460d8-video.mp4', 1, 8, 28);

-- --------------------------------------------------------

--
-- Table structure for table `picvik_video_channel`
--

CREATE TABLE IF NOT EXISTS `picvik_video_channel` (
  `channelid` int(20) NOT NULL AUTO_INCREMENT,
  `channelname` varchar(120) DEFAULT 'Untitled',
  `description` varchar(512) DEFAULT NULL,
  `location` varchar(120) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `privacy` tinyint(1) NOT NULL,
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`channelid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `picvik_video_channel`
--

INSERT INTO `picvik_video_channel` (`channelid`, `channelname`, `description`, `location`, `date`, `privacy`, `uid`) VALUES
(2, 'demochannel1', '', '', '2013-01-06', 1, 28),
(3, 'channel2', '', '', '2013-01-09', 1, 28),
(4, 'video demo channel', '', 'bangalore', '2013-01-17', 2, 28),
(5, 'public album', '', '', '2013-01-15', 1, 28),
(6, 'sample channel', 'null', '', '2012-12-29', 1, 28),
(7, 'demotesting', 'null', '', '2013-01-01', 1, 28),
(8, 'somename', 'null', '', '2013-01-08', 1, 28);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `picvik_activity`
--
ALTER TABLE `picvik_activity`
  ADD CONSTRAINT `picvik_activity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `picvik_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `picvik_activity_ibfk_2` FOREIGN KEY (`activity_type_id`) REFERENCES `picvik_activity_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `picvik_activity_ibfk_4` FOREIGN KEY (`activity_source_type_id`) REFERENCES `picvik_activity_source_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `picvik_comment`
--
ALTER TABLE `picvik_comment`
  ADD CONSTRAINT `picvik_comment_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `picvik_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `picvik_picture`
--
ALTER TABLE `picvik_picture`
  ADD CONSTRAINT `picvik_picture_ibfk_1` FOREIGN KEY (`albumid`) REFERENCES `picvik_picture_album` (`albumid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `picvik_picture_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `picvik_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `picvik_picture_album`
--
ALTER TABLE `picvik_picture_album`
  ADD CONSTRAINT `picvik_picture_album_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `picvik_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `picvik_userprofile`
--
ALTER TABLE `picvik_userprofile`
  ADD CONSTRAINT `picvik_userprofile_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `picvik_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `picvik_user_activation`
--
ALTER TABLE `picvik_user_activation`
  ADD CONSTRAINT `picvik_user_activation_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `picvik_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `picvik_video`
--
ALTER TABLE `picvik_video`
  ADD CONSTRAINT `picvik_video_ibfk_1` FOREIGN KEY (`channelid`) REFERENCES `picvik_video_channel` (`channelid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `picvik_video_channel`
--
ALTER TABLE `picvik_video_channel`
  ADD CONSTRAINT `picvik_video_channel_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `picvik_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
