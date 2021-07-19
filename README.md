# Final_examination_system
运行环境：NetBeans IDE 8.2

项目详情：此项目使用java代码开发，是关于期末考试系统的一个小项目。
文件夹CaseStudy是项目代码，Database是数据库

数据库里面添加考试的题目以及正确答案，用户在选择了答案后可以将学生的答案添加进数据库内并且和正确答案进行比较，以得到选择是否正确且最终计算出考试成绩。

目录CaseStudy\src\ScreenDemo下的Screen.java文件是用户注册界面，在没有账号的情况下可以进入此界面进行注册。

目录CaseStudy\src\ScreenDemo下的LoginScree.java文件是用户登录界面，在此处登录，如果没有账号可以点击注册按钮跳转至注册界面注册账号

目录CaseStudy\src\ScreenDemo下的QuizFrame.java文件是用户答题界面，可以在此处进行答题，必须答完二十道题目才可以退出，并点击保存按钮保存答案，点击提交按钮查看自己的分数。

目录CaseStudy\src\ScreenDemo下的FeedBack.java文件用一张表格列出用户的题数、正确数、错误数以及成绩

目前还存在的问题：1、数据库只能存在一个用户，在注册的时候会进行清空用户表的动作
                 2、数据库需要专门的设计，否则用户选择的答案与正确的答案对应不上
