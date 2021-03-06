USE [master]
GO
/****** Object:  Database [cszx_db]    Script Date: 2016/5/29 11:15:11 ******/
CREATE DATABASE [cszx_db]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'cszx_db', FILENAME = N'D:\WorkSpace\JSP\cszx\database\cszx_db.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'cszx_db_log', FILENAME = N'D:\WorkSpace\JSP\cszx\database\cszx_db_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [cszx_db] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [cszx_db].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [cszx_db] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [cszx_db] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [cszx_db] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [cszx_db] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [cszx_db] SET ARITHABORT OFF 
GO
ALTER DATABASE [cszx_db] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [cszx_db] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [cszx_db] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [cszx_db] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [cszx_db] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [cszx_db] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [cszx_db] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [cszx_db] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [cszx_db] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [cszx_db] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [cszx_db] SET  DISABLE_BROKER 
GO
ALTER DATABASE [cszx_db] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [cszx_db] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [cszx_db] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [cszx_db] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [cszx_db] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [cszx_db] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [cszx_db] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [cszx_db] SET RECOVERY FULL 
GO
ALTER DATABASE [cszx_db] SET  MULTI_USER 
GO
ALTER DATABASE [cszx_db] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [cszx_db] SET DB_CHAINING OFF 
GO
ALTER DATABASE [cszx_db] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [cszx_db] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [cszx_db]
GO
/****** Object:  User [CodeSC]    Script Date: 2016/5/29 11:15:11 ******/
CREATE USER [CodeSC] FOR LOGIN [CodeSC] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [CodeSC]
GO
/****** Object:  Table [dbo].[acayear]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[acayear](
	[acaid] [int] IDENTITY(1,1) NOT NULL,
	[acaname] [varchar](20) NOT NULL,
	[acaexp] [text] NULL,
 CONSTRAINT [PK__acayear__A3F68FE6876F01B9] PRIMARY KEY CLUSTERED 
(
	[acaid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[admin]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[admin](
	[aid] [int] IDENTITY(1,1) NOT NULL,
	[uname] [varchar](10) NOT NULL,
	[upass] [varchar](50) NOT NULL,
 CONSTRAINT [PK__admin__DE508E2E92D5F78C] PRIMARY KEY CLUSTERED 
(
	[aid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[arrangement]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[arrangement](
	[acaid] [int] NOT NULL,
	[pid] [int] NOT NULL,
	[cid] [int] NOT NULL,
	[coid] [int] NOT NULL,
	[tid] [int] NOT NULL,
	[sid] [int] NOT NULL,
	[_week] [datetime] NULL,
	[aexp] [varchar](20) NULL,
	[arid] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_arrangement] PRIMARY KEY CLUSTERED 
(
	[arid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[class]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[class](
	[cid] [int] IDENTITY(1,1) NOT NULL,
	[cname] [varchar](20) NOT NULL,
	[cexp] [text] NULL,
	[pid] [int] NOT NULL,
 CONSTRAINT [PK_class] PRIMARY KEY CLUSTERED 
(
	[cid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[course]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[course](
	[coid] [int] IDENTITY(1,1) NOT NULL,
	[coname] [varchar](20) NULL,
	[coexp] [text] NULL,
 CONSTRAINT [PK__course__357D635D19C38C6C] PRIMARY KEY CLUSTERED 
(
	[coid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[cware]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[cware](
	[cwid] [int] IDENTITY(1,1) NOT NULL,
	[cwname] [varchar](20) NOT NULL,
	[addr] [text] NULL,
	[tcid] [int] NOT NULL,
 CONSTRAINT [PK_cware] PRIMARY KEY CLUSTERED 
(
	[cwid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[prof]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[prof](
	[pid] [int] IDENTITY(1,1) NOT NULL,
	[pname] [varchar](20) NOT NULL,
	[pexp] [text] NULL,
 CONSTRAINT [PK__prof__DD37D91AED1CB559] PRIMARY KEY CLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[section]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[section](
	[sid] [int] IDENTITY(1,1) NOT NULL,
	[sname] [varchar](10) NOT NULL,
	[start] [datetime] NOT NULL,
	[endtime] [datetime] NOT NULL,
 CONSTRAINT [PK__section__DDDFDD361781EB72] PRIMARY KEY CLUSTERED 
(
	[sid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tcotext]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[tcotext](
	[tcid] [int] IDENTITY(1,1) NOT NULL,
	[tcdate] [datetime] NULL,
	[tcexp] [ntext] NULL,
	[arid] [int] NOT NULL,
	[tctitle] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[tcid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[teacher]    Script Date: 2016/5/29 11:15:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[teacher](
	[tid] [int] IDENTITY(1,1) NOT NULL,
	[uname] [varchar](20) NOT NULL,
	[tname] [varchar](20) NULL,
	[texp] [text] NULL,
	[upass] [varchar](50) NOT NULL,
 CONSTRAINT [PK__teacher__DC105B0F2F439A85] PRIMARY KEY CLUSTERED 
(
	[tid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[acayear] ON 

INSERT [dbo].[acayear] ([acaid], [acaname], [acaexp]) VALUES (3037, N'2015', N'')
INSERT [dbo].[acayear] ([acaid], [acaname], [acaexp]) VALUES (3039, N'2014', N'')
INSERT [dbo].[acayear] ([acaid], [acaname], [acaexp]) VALUES (3040, N'2013', N'')
INSERT [dbo].[acayear] ([acaid], [acaname], [acaexp]) VALUES (5037, N'2000', N'')
SET IDENTITY_INSERT [dbo].[acayear] OFF
SET IDENTITY_INSERT [dbo].[admin] ON 

INSERT [dbo].[admin] ([aid], [uname], [upass]) VALUES (1, N'cs', N'202CB962AC59075B964B07152D234B70')
SET IDENTITY_INSERT [dbo].[admin] OFF
SET IDENTITY_INSERT [dbo].[arrangement] ON 

INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 22, 7, CAST(0x000063E300000000 AS DateTime), N'233', 1037)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 22, 7, CAST(0x000063E300000000 AS DateTime), N'233', 1038)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 22, 9, CAST(0x000063E300000000 AS DateTime), N'', 1039)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 22, 12, CAST(0x000063E300000000 AS DateTime), N'', 1040)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 22, 13, CAST(0x000063E300000000 AS DateTime), N'', 1041)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3039, 30, 5, 2, 22, 7, CAST(0x000063E300000000 AS DateTime), N'', 1042)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 1024, 7, CAST(0x000063E300000000 AS DateTime), N'', 1043)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 22, 9, CAST(0x000063E300000000 AS DateTime), N'', 1044)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 1024, 12, CAST(0x000063E300000000 AS DateTime), N'', 1045)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 1024, 12, CAST(0x000063E300000000 AS DateTime), N'', 1046)
INSERT [dbo].[arrangement] ([acaid], [pid], [cid], [coid], [tid], [sid], [_week], [aexp], [arid]) VALUES (3037, 30, 5, 2, 22, 7, CAST(0x000063E400000000 AS DateTime), N'', 2039)
SET IDENTITY_INSERT [dbo].[arrangement] OFF
SET IDENTITY_INSERT [dbo].[class] ON 

INSERT [dbo].[class] ([cid], [cname], [cexp], [pid]) VALUES (5, N'1', N'', 30)
INSERT [dbo].[class] ([cid], [cname], [cexp], [pid]) VALUES (7, N'1', N'', 32)
INSERT [dbo].[class] ([cid], [cname], [cexp], [pid]) VALUES (1006, N'2', N'333', 31)
SET IDENTITY_INSERT [dbo].[class] OFF
SET IDENTITY_INSERT [dbo].[course] ON 

INSERT [dbo].[course] ([coid], [coname], [coexp]) VALUES (2, N'java', N'helloWord')
INSERT [dbo].[course] ([coid], [coname], [coexp]) VALUES (5, N'python', N'222')
INSERT [dbo].[course] ([coid], [coname], [coexp]) VALUES (6, N'c', N'222')
INSERT [dbo].[course] ([coid], [coname], [coexp]) VALUES (7, N'c++', N'')
INSERT [dbo].[course] ([coid], [coname], [coexp]) VALUES (10, N'2', N'')
INSERT [dbo].[course] ([coid], [coname], [coexp]) VALUES (1006, N'组成原理', N'233')
INSERT [dbo].[course] ([coid], [coname], [coexp]) VALUES (2006, N'oc', N'')
SET IDENTITY_INSERT [dbo].[course] OFF
SET IDENTITY_INSERT [dbo].[cware] ON 

INSERT [dbo].[cware] ([cwid], [cwname], [addr], [tcid]) VALUES (6, N'练习5.doc', N'\UploadFiles\cs3\练习5.doc', 6)
INSERT [dbo].[cware] ([cwid], [cwname], [addr], [tcid]) VALUES (7, N'练习5.doc', N'\UploadFiles\cs\练习5.doc', 11)
INSERT [dbo].[cware] ([cwid], [cwname], [addr], [tcid]) VALUES (8, N'练习5.doc', N'\UploadFiles\cs\练习5.doc', 14)
SET IDENTITY_INSERT [dbo].[cware] OFF
SET IDENTITY_INSERT [dbo].[prof] ON 

INSERT [dbo].[prof] ([pid], [pname], [pexp]) VALUES (30, N'软件工程', NULL)
INSERT [dbo].[prof] ([pid], [pname], [pexp]) VALUES (31, N'图形图像', N'')
INSERT [dbo].[prof] ([pid], [pname], [pexp]) VALUES (32, N'外语', N'')
INSERT [dbo].[prof] ([pid], [pname], [pexp]) VALUES (33, N'艺术', N'')
INSERT [dbo].[prof] ([pid], [pname], [pexp]) VALUES (1031, N'                    ', N'                                                                 ')
SET IDENTITY_INSERT [dbo].[prof] OFF
SET IDENTITY_INSERT [dbo].[section] ON 

INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (7, N'第1节', CAST(0x000000000083D600 AS DateTime), CAST(0x00000000009450C0 AS DateTime))
INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (9, N'第2节', CAST(0x0000000000A4CB80 AS DateTime), CAST(0x000000000083D600 AS DateTime))
INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (10, N'第3节', CAST(0x000000000083D600 AS DateTime), CAST(0x000000000083D600 AS DateTime))
INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (12, N'第6节', CAST(0x0000000000B54640 AS DateTime), CAST(0x0000000000A4CB80 AS DateTime))
INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (13, N'第5节', CAST(0x000000000083D600 AS DateTime), CAST(0x0000000000A4CB80 AS DateTime))
INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (15, N'第4节', CAST(0x0000000000A4CB80 AS DateTime), CAST(0x0000000000F73140 AS DateTime))
INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (1012, N'第99节', CAST(0x000000000083D600 AS DateTime), CAST(0x000000000083D600 AS DateTime))
INSERT [dbo].[section] ([sid], [sname], [start], [endtime]) VALUES (1014, N'第8节', CAST(0x00000000015A11C0 AS DateTime), CAST(0x00000000015A11C0 AS DateTime))
SET IDENTITY_INSERT [dbo].[section] OFF
SET IDENTITY_INSERT [dbo].[tcotext] ON 

INSERT [dbo].[tcotext] ([tcid], [tcdate], [tcexp], [arid], [tctitle]) VALUES (3, CAST(0x0000A61400000000 AS DateTime), N'233', 1045, N'java 2')
INSERT [dbo].[tcotext] ([tcid], [tcdate], [tcexp], [arid], [tctitle]) VALUES (6, CAST(0x0000A61400000000 AS DateTime), N'233', 1043, N'java 1')
INSERT [dbo].[tcotext] ([tcid], [tcdate], [tcexp], [arid], [tctitle]) VALUES (7, CAST(0x0000A61400000000 AS DateTime), N'233', 1043, N'java 2')
INSERT [dbo].[tcotext] ([tcid], [tcdate], [tcexp], [arid], [tctitle]) VALUES (10, CAST(0x0000A61500000000 AS DateTime), N'233', 1043, N'python')
INSERT [dbo].[tcotext] ([tcid], [tcdate], [tcexp], [arid], [tctitle]) VALUES (11, CAST(0x0000A61400000000 AS DateTime), N'233', 1037, N'python')
INSERT [dbo].[tcotext] ([tcid], [tcdate], [tcexp], [arid], [tctitle]) VALUES (12, CAST(0x0000A61400000000 AS DateTime), N'233', 1037, N'java 3')
INSERT [dbo].[tcotext] ([tcid], [tcdate], [tcexp], [arid], [tctitle]) VALUES (14, CAST(0x0000A61500000000 AS DateTime), N'233', 2039, N'java 2')
SET IDENTITY_INSERT [dbo].[tcotext] OFF
SET IDENTITY_INSERT [dbo].[teacher] ON 

INSERT [dbo].[teacher] ([tid], [uname], [tname], [texp], [upass]) VALUES (22, N'cs', N'坂本', N'我最屌', N'202CB962AC59075B964B07152D234B70')
INSERT [dbo].[teacher] ([tid], [uname], [tname], [texp], [upass]) VALUES (1023, N'test', N'郑鸿', N'', N'202CB962AC59075B964B07152D234B70')
INSERT [dbo].[teacher] ([tid], [uname], [tname], [texp], [upass]) VALUES (1024, N'cs3', N'林晓农', N'', N'202CB962AC59075B964B07152D234B70')
SET IDENTITY_INSERT [dbo].[teacher] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [uq_key]    Script Date: 2016/5/29 11:15:11 ******/
ALTER TABLE [dbo].[acayear] ADD  CONSTRAINT [uq_key] UNIQUE NONCLUSTERED 
(
	[acaname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__admin__C7D2484ECB38E687]    Script Date: 2016/5/29 11:15:11 ******/
ALTER TABLE [dbo].[admin] ADD  CONSTRAINT [UQ__admin__C7D2484ECB38E687] UNIQUE NONCLUSTERED 
(
	[uname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [course_uq_key]    Script Date: 2016/5/29 11:15:11 ******/
ALTER TABLE [dbo].[course] ADD  CONSTRAINT [course_uq_key] UNIQUE NONCLUSTERED 
(
	[coname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [prof_uq_key]    Script Date: 2016/5/29 11:15:11 ******/
ALTER TABLE [dbo].[prof] ADD  CONSTRAINT [prof_uq_key] UNIQUE NONCLUSTERED 
(
	[pname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [section_uq_key]    Script Date: 2016/5/29 11:15:11 ******/
ALTER TABLE [dbo].[section] ADD  CONSTRAINT [section_uq_key] UNIQUE NONCLUSTERED 
(
	[sname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[arrangement]  WITH CHECK ADD  CONSTRAINT [FK__arrangeme__acaid__22AA2996] FOREIGN KEY([acaid])
REFERENCES [dbo].[acayear] ([acaid])
GO
ALTER TABLE [dbo].[arrangement] CHECK CONSTRAINT [FK__arrangeme__acaid__22AA2996]
GO
ALTER TABLE [dbo].[arrangement]  WITH CHECK ADD  CONSTRAINT [FK__arrangemen__coid__25869641] FOREIGN KEY([coid])
REFERENCES [dbo].[course] ([coid])
GO
ALTER TABLE [dbo].[arrangement] CHECK CONSTRAINT [FK__arrangemen__coid__25869641]
GO
ALTER TABLE [dbo].[arrangement]  WITH CHECK ADD  CONSTRAINT [FK__arrangement__pid__239E4DCF] FOREIGN KEY([pid])
REFERENCES [dbo].[prof] ([pid])
GO
ALTER TABLE [dbo].[arrangement] CHECK CONSTRAINT [FK__arrangement__pid__239E4DCF]
GO
ALTER TABLE [dbo].[arrangement]  WITH CHECK ADD  CONSTRAINT [FK__arrangement__sid__276EDEB3] FOREIGN KEY([sid])
REFERENCES [dbo].[section] ([sid])
GO
ALTER TABLE [dbo].[arrangement] CHECK CONSTRAINT [FK__arrangement__sid__276EDEB3]
GO
ALTER TABLE [dbo].[arrangement]  WITH CHECK ADD  CONSTRAINT [FK__arrangement__tid__267ABA7A] FOREIGN KEY([tid])
REFERENCES [dbo].[teacher] ([tid])
GO
ALTER TABLE [dbo].[arrangement] CHECK CONSTRAINT [FK__arrangement__tid__267ABA7A]
GO
ALTER TABLE [dbo].[arrangement]  WITH CHECK ADD  CONSTRAINT [FK_arrangement_arrangement] FOREIGN KEY([arid])
REFERENCES [dbo].[arrangement] ([arid])
GO
ALTER TABLE [dbo].[arrangement] CHECK CONSTRAINT [FK_arrangement_arrangement]
GO
ALTER TABLE [dbo].[arrangement]  WITH CHECK ADD  CONSTRAINT [FK_class_cid_arrangement_cid] FOREIGN KEY([cid])
REFERENCES [dbo].[class] ([cid])
GO
ALTER TABLE [dbo].[arrangement] CHECK CONSTRAINT [FK_class_cid_arrangement_cid]
GO
ALTER TABLE [dbo].[class]  WITH CHECK ADD  CONSTRAINT [FK_class_prof] FOREIGN KEY([pid])
REFERENCES [dbo].[prof] ([pid])
GO
ALTER TABLE [dbo].[class] CHECK CONSTRAINT [FK_class_prof]
GO
ALTER TABLE [dbo].[prof]  WITH CHECK ADD  CONSTRAINT [FK_prof_prof] FOREIGN KEY([pid])
REFERENCES [dbo].[prof] ([pid])
GO
ALTER TABLE [dbo].[prof] CHECK CONSTRAINT [FK_prof_prof]
GO
ALTER TABLE [dbo].[tcotext]  WITH CHECK ADD  CONSTRAINT [FK_tcotext_arrangement] FOREIGN KEY([arid])
REFERENCES [dbo].[arrangement] ([arid])
GO
ALTER TABLE [dbo].[tcotext] CHECK CONSTRAINT [FK_tcotext_arrangement]
GO
USE [master]
GO
ALTER DATABASE [cszx_db] SET  READ_WRITE 
GO
