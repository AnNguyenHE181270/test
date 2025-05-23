USE [master]
GO
/****** Object:  Database [ASSGN_PRJ_TOUR]    Script Date: 4/23/2024 2:35:57 AM ******/
CREATE DATABASE [ASSGN_PRJ_TOUR]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ASSGN_PRJ_TOUR', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\ASSGN_PRJ_TOUR.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ASSGN_PRJ_TOUR_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\ASSGN_PRJ_TOUR_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ASSGN_PRJ_TOUR].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET ARITHABORT OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET  MULTI_USER 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET QUERY_STORE = ON
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [ASSGN_PRJ_TOUR]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 4/23/2024 2:35:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[AccountID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](255) NULL,
	[Password] [nvarchar](255) NULL,
	[IsSeller] [bit] NULL,
	[IsAdmin] [bit] NULL,
	[IsActive] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 4/23/2024 2:35:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Log]    Script Date: 4/23/2024 2:35:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Log](
	[LogID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [int] NULL,
	[Action] [nvarchar](max) NULL,
	[Time] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[LogID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 4/23/2024 2:35:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[TotalPrice] [decimal](10, 2) NULL,
	[CreateDate] [date] NULL,
	[AccountID] [int] NULL,
	[Status] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 4/23/2024 2:35:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[Quantity] [int] NULL,
	[OrderID] [int] NULL,
	[TourID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tour]    Script Date: 4/23/2024 2:35:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tour](
	[TourID] [int] IDENTITY(1,1) NOT NULL,
	[TourName] [nvarchar](255) NULL,
	[TourPrice] [decimal](10, 2) NULL,
	[Quantity] [int] NULL,
	[TourImage] [nvarchar](max) NULL,
	[TourDescription] [nvarchar](max) NULL,
	[CategoryID] [int] NULL,
	[CreatedBy] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[TourID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([AccountID], [Username], [Password], [IsSeller], [IsAdmin], [IsActive]) VALUES (1, N'admin', N'123', 0, 1, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [IsSeller], [IsAdmin], [IsActive]) VALUES (2, N'minh', N'123', 1, 0, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [IsSeller], [IsAdmin], [IsActive]) VALUES (3, N'mai', N'123', 1, 0, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [IsSeller], [IsAdmin], [IsActive]) VALUES (4, N'linh', N'123', 0, 0, 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (1, N'Miền Bắc')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (2, N'Miền Trung')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (3, N'Miền Nam')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Log] ON 

INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (1, 2, N'Add new Tour', CAST(N'2024-04-23T02:17:19.440' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (2, 2, N'Add new Tour', CAST(N'2024-04-23T02:17:54.950' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (3, 2, N'Add new Tour', CAST(N'2024-04-23T02:18:17.010' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (4, 2, N'Add new Tour', CAST(N'2024-04-23T02:18:45.323' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (5, 2, N'Add new Tour', CAST(N'2024-04-23T02:19:10.097' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (6, 2, N'Add new Tour', CAST(N'2024-04-23T02:19:31.453' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (7, 2, N'Add new Tour', CAST(N'2024-04-23T02:19:59.473' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (8, 3, N'Add new Tour', CAST(N'2024-04-23T02:32:08.173' AS DateTime))
INSERT [dbo].[Log] ([LogID], [AccountID], [Action], [Time]) VALUES (9, 3, N'Add new Tour', CAST(N'2024-04-23T02:32:35.067' AS DateTime))
SET IDENTITY_INSERT [dbo].[Log] OFF
GO
SET IDENTITY_INSERT [dbo].[Tour] ON 

INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (1, N'Hà Nội', CAST(100.00 AS Decimal(10, 2)), 10, N'Images/1_HaNoi.jpg', N'abc', 1, 2)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (2, N'Lào Cai', CAST(200.00 AS Decimal(10, 2)), 20, N'Images/2_Sapa.jpg', N'abc', 1, 2)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (3, N'Ninh BÌnh', CAST(300.00 AS Decimal(10, 2)), 15, N'Images/3_NinhBinh.jpg', N'abc', 1, 2)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (4, N'Quảng Ninh', CAST(120.00 AS Decimal(10, 2)), 10, N'Images/4_QuangNinh.jpg', N'abc', 1, 2)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (5, N'Thanh Hóa', CAST(200.00 AS Decimal(10, 2)), 20, N'Images/5_ThanhHoa.jpg', N'abc', 2, 2)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (6, N'Nghệ An', CAST(300.00 AS Decimal(10, 2)), 25, N'Images/6_NgheAN.jpg', N'abc', 2, 2)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (7, N'Hồ Chí Minh', CAST(500.00 AS Decimal(10, 2)), 5, N'Images/7_HoChiMinh.jpg', N'abc', 3, 2)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (8, N'Cà Mau', CAST(300.00 AS Decimal(10, 2)), 15, N'Images/8_CaMau.jpg', N'abc', 3, 3)
INSERT [dbo].[Tour] ([TourID], [TourName], [TourPrice], [Quantity], [TourImage], [TourDescription], [CategoryID], [CreatedBy]) VALUES (9, N'Bến Tre', CAST(200.00 AS Decimal(10, 2)), 10, N'Images/9_BenTre.jpg', N'abc', 3, 3)
SET IDENTITY_INSERT [dbo].[Tour] OFF
GO
ALTER TABLE [dbo].[Log]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([TourID])
REFERENCES [dbo].[Tour] ([TourID])
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Account] ([AccountID])
GO
USE [master]
GO
ALTER DATABASE [ASSGN_PRJ_TOUR] SET  READ_WRITE 
GO
