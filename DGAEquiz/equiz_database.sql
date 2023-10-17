-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3307
-- Thời gian đã tạo: Th10 16, 2023 lúc 05:42 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `jdbcdemo`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`) VALUES
(1, 'Duong', '1234'),
(2, 'Tuan Anh', '1334'),
(3, 'Giang', '1239');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `campaign`
--

CREATE TABLE `campaign` (
  `id` bigint(11) NOT NULL,
  `title` text DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `campaign`
--

INSERT INTO `campaign` (`id`, `title`, `description`) VALUES
(1, 'Phần 1: Sưu tầm tranh', 'Bài học này chứa rất nhiều thông tin về tranh vẽ'),
(2, 'Phần 2: Thế giới động vật', 'Bài học này sẽ cho các bé tìm hiểu về thế giới động vật'),
(3, 'Phần 3: Chiến tranh trung cổ', 'Trong khoá này các bé sẽ học về các sự kiện trung cổ'),
(4, 'Phần 4: Cuộc chiến đông âu', 'Hãy tự tìm hiểu qua bài học này nhé!');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fill_question`
--

CREATE TABLE `fill_question` (
  `id` bigint(20) NOT NULL,
  `question` text NOT NULL,
  `context` text DEFAULT NULL,
  `option1` text DEFAULT NULL,
  `option2` text DEFAULT NULL,
  `option3` text DEFAULT NULL,
  `option4` text DEFAULT NULL,
  `correct_answer` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `fill_question`
--

INSERT INTO `fill_question` (`id`, `question`, `context`, `option1`, `option2`, `option3`, `option4`, `correct_answer`) VALUES
(1, 'Chọn đáp án đúng:', 'The first time that an (1) \r\n of air rage was recorded was in the 1940’s, but the passenger was never actually charged for an offence because there were no clear rules in place to specify where to prosecute', 'predicted', 'incident', 'passenger', 'hoped', 2),
(2, 'Chọn đáp án đúng:', 'It was later (2) \r\n that it would be the country where the plane is registered. ', 'passengers', 'established', 'occurring', 'hoped', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image_question`
--

CREATE TABLE `image_question` (
  `id` bigint(11) NOT NULL,
  `question` text NOT NULL,
  `image_source` text DEFAULT NULL,
  `option1` text DEFAULT NULL,
  `option2` text DEFAULT NULL,
  `option3` text DEFAULT NULL,
  `option4` text DEFAULT NULL,
  `correct_answer` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `image_question`
--

INSERT INTO `image_question` (`id`, `question`, `image_source`, `option1`, `option2`, `option3`, `option4`, `correct_answer`) VALUES
(1, 'Hãy chọn đáp án đúng', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Dodo_1.JPG/640px-Dodo_1.JPG', 'Con chim dodo', 'Con vẹt', 'Con khủng long', 'Con chó', 1),
(2, 'Đây là đội tuyển nào', 'https://liquipedia.net/commons/images/thumb/7/7c/GAM_Worlds_2022.jpg/562px-GAM_Worlds_2022.jpg', 'Seed 1 coincard', 'Ông trùm VCS', 'TeamWhale', 'GAM Esport', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `listening_question`
--

CREATE TABLE `listening_question` (
  `id` bigint(20) NOT NULL,
  `question` text NOT NULL,
  `audio_source` text DEFAULT NULL,
  `option1` text DEFAULT NULL,
  `option2` text DEFAULT NULL,
  `correct_answer` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `listening_question`
--

INSERT INTO `listening_question` (`id`, `question`, `audio_source`, `option1`, `option2`, `correct_answer`) VALUES
(1, 'Chọn đáp án đúng:', 'https://api.dictionaryapi.dev/media/pronunciations/en/book-uk.mp3', 'Book', 'Bob', 1),
(2, 'Chọn đáp án đúng:', 'https://api.dictionaryapi.dev/media/pronunciations/en/field-au.mp3', 'Feel', 'Field', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `translate_question`
--

CREATE TABLE `translate_question` (
  `id` bigint(20) NOT NULL,
  `question` text NOT NULL,
  `context` text DEFAULT NULL,
  `option1` text DEFAULT NULL,
  `option2` text DEFAULT NULL,
  `option3` text DEFAULT NULL,
  `option4` text DEFAULT NULL,
  `correct_answer` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `translate_question`
--

INSERT INTO `translate_question` (`id`, `question`, `context`, `option1`, `option2`, `option3`, `option4`, `correct_answer`) VALUES
(1, 'Chọn đáp án đúng:', 'Independent', 'Độc lập', 'Phụ thuộc', 'Ức chế', 'Kiểm soát', 1),
(2, 'Chọn đáp án đúng:', 'Zoo', 'Ngựa vằn', 'Sở thú', 'Cá heo', 'Mắt', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `campaign`
--
ALTER TABLE `campaign`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `fill_question`
--
ALTER TABLE `fill_question`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `image_question`
--
ALTER TABLE `image_question`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `listening_question`
--
ALTER TABLE `listening_question`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `translate_question`
--
ALTER TABLE `translate_question`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `campaign`
--
ALTER TABLE `campaign`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `fill_question`
--
ALTER TABLE `fill_question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `image_question`
--
ALTER TABLE `image_question`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `listening_question`
--
ALTER TABLE `listening_question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `translate_question`
--
ALTER TABLE `translate_question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
