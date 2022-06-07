# 项目的基本目录结构

- .idea/ -- 项目配置,插件配置,包括历史记录,版本控制信息
- .mvn/ -- 为项目指定某个特定 Maven 版本
- logs/ -- 日志输出文件
- src/ -- 主目录
	- main/ -- 代码主目录
		- java/你的 package/ -- 项目 java 代码目录
		- common/ -- 公共业务目录
			- controller/ -- 控制器目录
				- ExceptionController.java -- 错误统一处理控制器
				- ResponseController.java -- 响应统一处理控制器
			- entity/ -- 数据库模型目录
				- mysql/ -- mysql 的 loopback2mysql 的表模型目录
				- mysql4test/ -- mysql 的 test 的表模型目录
			- mapper/ -- 操作数据库的 mapper 的存放目录
				- mysql/ -- mysql 数据库操作的 mapperInterface 目录
					- xml/ -- mapper.xml 文件存放目录
				- mysql4test/ -- mysql4test 数据库操作的 mapperInterface 目录
					- xml/ -- mapper.xml 文件存放目录
			- service/ -- 服务目录,调用 mapper 增删改查或者是公共操作
				- mysql/ -- mysql 操作的 interface 目录
					- impl/ -- interface 的实现目录
				- mysql4test/ -- mysql4test 操作的 interface 目录
				- impl/ -- interface 的实现目录
			- utils/ -- 工具配置目录
				- annotation/ -- 自定义注解目录
					- MyApiResponse.java -- 响应注解
					- MyRestController.java -- 自定义 rest 控制器注解
					- MyTokenRequired.java -- jwt验证注解
				- config/ -- 配置目录
					- InterceptorConfig.java -- 拦截器配置
					- JTAConfig.java -- JTA数据库源配置
					- JTAMysqlConfig.java -- 这里是一个 mysql 的数据库配置
					- JTAMysql4testConfig.java -- 这里是另一个 mysql 的数据库配置
				- exception/ -- 异常目录
					- BusinessExceptionVo.java -- 自定义业务异常类
					- ExceptionEnumVo.java -- 异常枚举
					- IBaseExceptionVo.java -- 异常实现接口
					- JWTException.java -- JWT 异常类
				- interceptor/ -- 拦截器目录
					- JWTInterceptor.java -- JWT 拦截器
				- util/ -- 工具目录
					- JWTUtil.java -- JWT 工具类
					- RegexpUtil.java -- 正则工具类
					- ThrowableUtil.java -- 错误堆栈处理工具类
						- vo/ -- 常量/枚举/配置类的存放目录
				- 数据表名/ -- 对应表接口参数为复杂对象的类存放目录
				- ApiResponseVo.java -- 请求响应类
				- ProfileActiveVo.java -- profiles 的active
		- 你的项目名称/ -- 具体业务目录
			- controller/ -- 接口目录
			- service/ -- 具体业务目录
		- test/ -- 个人随便测试目录(请在真实环境删除该目录)
		- MainApplication.java -- 程序启动类
			- resources/ -- 资源目录
				- static/ -- 静态目录
				- templates/ -- 模板目录
				- application.yml -- 主要用来设定数据库配置,url 地址等;可以根据 profiles 来达到不同环境不同配置
				- application-dev.yml,application-qas.yml,application-prd.yml;application.yml 只配置 active
				- logback-spring.xml -- 日志输出配置
	- test/ -- 测试目录
		- java/你的 package/ -- 测试代码目录
- target/ -- 编译后的目标目录,里面是 class 文件
- pom.xml -- 主要用来设定项目依赖
- \*.iml -- 也是项目的一些配置文件

# question list

- 自定义注解之间再组合无效问题
- 对@Pattern再封装注解 默认值
- 注解+AOP使用

# to-do list

- 项目整理和优化
	- 自动创建 service 和 mapper
	- entity级联校验以及关系注解
	- 整合elasticsearch
	- 单元测试
