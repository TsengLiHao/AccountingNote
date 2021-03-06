USE [master]
GO
/****** Object:  Database [AccountingNote]    Script Date: 2022/1/4 下午 08:57:17 ******/
CREATE DATABASE [AccountingNote]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AccountingNote', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\AccountingNote.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AccountingNote_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\AccountingNote_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [AccountingNote] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AccountingNote].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AccountingNote] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AccountingNote] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AccountingNote] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AccountingNote] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AccountingNote] SET ARITHABORT OFF 
GO
ALTER DATABASE [AccountingNote] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AccountingNote] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AccountingNote] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AccountingNote] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AccountingNote] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AccountingNote] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AccountingNote] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AccountingNote] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AccountingNote] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AccountingNote] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AccountingNote] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AccountingNote] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AccountingNote] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AccountingNote] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AccountingNote] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AccountingNote] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AccountingNote] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AccountingNote] SET RECOVERY FULL 
GO
ALTER DATABASE [AccountingNote] SET  MULTI_USER 
GO
ALTER DATABASE [AccountingNote] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AccountingNote] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AccountingNote] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AccountingNote] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AccountingNote] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AccountingNote] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'AccountingNote', N'ON'
GO
ALTER DATABASE [AccountingNote] SET QUERY_STORE = OFF
GO
USE [AccountingNote]
GO
USE [AccountingNote]
GO
/****** Object:  Sequence [dbo].[hibernate_sequence]    Script Date: 2022/1/4 下午 08:57:17 ******/
CREATE SEQUENCE [dbo].[hibernate_sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[Accounting]    Script Date: 2022/1/4 下午 08:57:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounting](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [uniqueidentifier] NOT NULL,
	[Caption] [nvarchar](50) NULL,
	[Amount] [int] NOT NULL,
	[ActType] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Body] [nvarchar](50) NULL,
	[CategoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Accounting] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 2022/1/4 下午 08:57:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](50) NOT NULL,
	[UserID] [uniqueidentifier] NOT NULL,
	[CreateDate] [date] NOT NULL,
	[Body] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UsersInfo]    Script Date: 2022/1/4 下午 08:57:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsersInfo](
	[ID] [uniqueidentifier] NOT NULL,
	[Account] [varchar](50) NOT NULL,
	[PWD] [varchar](50) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[UserLevel] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[ReviseDate] [datetime] NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Accounting] ON 

INSERT [dbo].[Accounting] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body], [CategoryName]) VALUES (3, N'6bf6d163-efa3-4dad-ab5c-8ecf88e30c6e', N'AAA', 9, 1, CAST(N'2021-12-17T00:00:00.000' AS DateTime), N'AAA', N'我的分類1')
INSERT [dbo].[Accounting] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body], [CategoryName]) VALUES (15, N'6bf6d163-efa3-4dad-ab5c-8ecf88e30c6e', N'EEE', 38, 1, CAST(N'2021-12-27T21:14:34.437' AS DateTime), N'', N'我的分類2')
INSERT [dbo].[Accounting] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body], [CategoryName]) VALUES (16, N'6bf6d163-efa3-4dad-ab5c-8ecf88e30c6e', N'SSS', 27, 0, CAST(N'2021-12-27T21:15:42.497' AS DateTime), N'', N'我的分類3')
SET IDENTITY_INSERT [dbo].[Accounting] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [CategoryName], [UserID], [CreateDate], [Body]) VALUES (91, N'我的分類1', N'6bf6d163-efa3-4dad-ab5c-8ecf88e30c6e', CAST(N'2021-12-27' AS Date), N'')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [UserID], [CreateDate], [Body]) VALUES (92, N'我的分類2', N'6bf6d163-efa3-4dad-ab5c-8ecf88e30c6e', CAST(N'2021-12-27' AS Date), N'')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [UserID], [CreateDate], [Body]) VALUES (93, N'我的分類3', N'6bf6d163-efa3-4dad-ab5c-8ecf88e30c6e', CAST(N'2021-12-27' AS Date), N'')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
INSERT [dbo].[UsersInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate], [ReviseDate]) VALUES (N'f2368308-aa7a-4a60-a730-285c0cc09fca', N'admin', N'12345678', N'Admin', N'XXX@XXX.com', 0, CAST(N'2021-12-04T00:00:00.000' AS DateTime), CAST(N'2021-12-31T14:14:56.900' AS DateTime))
INSERT [dbo].[UsersInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate], [ReviseDate]) VALUES (N'6bf6d163-efa3-4dad-ab5c-8ecf88e30c6e', N'XXX', N'12345678', N'zzz', N'ZZZ@zzz.com', 1, CAST(N'2021-12-17T00:00:00.000' AS DateTime), CAST(N'2021-12-31T11:18:58.473' AS DateTime))
INSERT [dbo].[UsersInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate], [ReviseDate]) VALUES (N'22a798f6-5e3f-c148-bc10-ad7570440143', N'ddd', N'12345678', N'CCC', N'CCC@BBB.com', 1, CAST(N'2021-12-30T08:44:00.000' AS DateTime), CAST(N'2021-12-30T13:57:00.000' AS DateTime))
INSERT [dbo].[UsersInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate], [ReviseDate]) VALUES (N'cb24915d-2d92-4ffc-8a7e-d5214a31a96c', N'BBB', N'12345678', N'BBB', N'BBB@BBB.com', 1, CAST(N'2021-12-31T00:30:00.000' AS DateTime), CAST(N'2021-12-31T00:31:22.123' AS DateTime))
INSERT [dbo].[UsersInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate], [ReviseDate]) VALUES (N'5845445a-3e83-4d7a-bdef-b96922ee60ce', N'FFF', N'12345678', N'AAA', N'AAA@AAA.com', 1, CAST(N'2021-12-30T21:06:01.790' AS DateTime), CAST(N'2021-12-30T21:06:01.840' AS DateTime))
INSERT [dbo].[UsersInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate], [ReviseDate]) VALUES (N'15f41a51-cd48-42cb-ae8f-c34b13a0056b', N'zzz', N'12345678', N'xxxxx', N'zxz@zxz.com', 1, CAST(N'2021-12-13T00:00:00.000' AS DateTime), CAST(N'2021-12-30T20:33:00.000' AS DateTime))
INSERT [dbo].[UsersInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate], [ReviseDate]) VALUES (N'5d5494c2-8cab-4715-af90-ad3243383c9b', N'AAA', N'12345678', N'ZZZ', N'ZZZ@ZZZ', 1, CAST(N'2021-12-14T00:00:00.000' AS DateTime), CAST(N'2021-12-30T13:57:56.150' AS DateTime))
GO
ALTER TABLE [dbo].[Category] ADD  CONSTRAINT [DF_Category_CreateDate]  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[UsersInfo] ADD  CONSTRAINT [DF_UsersInfo_ID]  DEFAULT (newid()) FOR [ID]
GO
USE [master]
GO
ALTER DATABASE [AccountingNote] SET  READ_WRITE 
GO
