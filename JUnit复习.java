2018年12月8日
  
1.Junit单元测试
2.反射
3.注解

Junit单元测试：
 测试的分类：
      黑盒测试：不需要编写代码，给输入值，看程序是否能够输出期望的值。
	  白盒测试：需要编写代码。关注程序具体的执行流程。
 
 Junit使用：白盒测试
   步骤：
   1.定义一个测试类（测试用列）
     建议：
	    测试类名：被测试的类名Test CalculatorTest
		包名：XX.XX.test  cn.itcasy.test
		
	2.定义测试的方法：可以独立运行
	  建议：
	    方法名：testXX（）
		返回值：void
		参数列表：空参数
		
	3.给方法加@Test
	4.导入junit依赖环境
	
	判断结果：
	 红色：失败
	 绿色：成功
	 一般我们会使用断言操作来处理结果
	   Assert.assertEqual(期望的结果，运算的结果)
	   
	 补充：
	 @Before
	  修饰的方法在测试方法执行之前会被自动执行
	  @After：
	  修饰的方法在测试方法执行之后会被自动的执行
	  
	
反射：框架设计的灵魂
	     框架：半成品软件。可以在框架的基础上进行软件开发，简化编码
		 反射：将类的各个组成部分封装为其他对象，这就是反射机制
		   
		   好处：
		   1.可以在程序运行过程中，操作这些对象
		   2.可以解耦，提高程序的可扩展性
		   
	    1.获取Class对象的方式
		   （a）、Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
		           * 多用于配置文件，将类名定义在配置文件中，读取文件，加载类
				   
			(b)、类名.class：通过类名的属性class获取
			      *多用于参数的传递
		  
		    (c)、对象.getClass()：getClass()方法在Object类中定义着
		          * 多用于对象的获取字节码的方式
				  
		结论：
		     同一个字节码文件（*.class）在一次程序运行过程中，只会被加载一次，不论通过哪一种方式获取的class对象都是同一个
			
      
     2.Class对象功能
         获取功能: 
            (1).获取成员变量们
                Field[] getFields():获取所有public修饰的成员变量
                Field  getField（String name） 获取指定名称的public修饰的成员变量
          
      		    Field [] getDeclaredFields() 获取所有的成员变量，不考虑修饰符
                Field[] getDeclaredField(String name) 

            (2).获取构造方法们
                Constructor<>[] getConstructors()
                Constructor<>[] getConstructor(参数类型parameterTypes)
				
			    Constructor<>[] getDeclaredConstructors()
			    Constructor<>[] getDeclaredConstructors(参数类型parameterTypes)
				
            (3)、获取成员方法们：
                  Method[] getMethods();	
                  Method getMethod(String name,参数类型) 
				  
			      Method [] getDeclaredMethods()
				  Method  getDeclaredMethod(String name,参数类型)
				  
			(4)获取全类名
			    String getName()
				
		 
		 **Field :成员变量
		    操作：
			  1.设置值：
			    void set(Object obj,Object value)
			  2.获取值
			    get(Object obj)
			  3.忽略访问权限修饰的安全检查
			    setAccessible(true):暴力反射
				
		
		**Constructor:构造方法
		    创建对象：
			  T newInstance(Object 。。。initargs)
			  如果使用空参数构造方法创建对象，操作可以简化：Class对象的newInstance方法
			  
			  
			  
		**Method：方法对象
		   执行方法：
		     Object invoke(Object obj,Object ...args)
		   获取方法名称：
		     String getName ：获取方法名
		

注解：
    1.概念：说明程序的，给计算机看的
    2.注解： 用于文字描述程序的，给程序员看的

    3.定义：注解（Annotation），也叫元数据。一种代码级别的说明，它是JDK1.5及以后版本引入的特性，与类、接口、枚举是在同一个层次，它可以声明在包、类、字段、方法、局部变量、方法参数等前面，用于对这些元素进行说明，注解。
      概念描述：
        JDK1.5之后的新特性
		说明程序
		使用注解：@注解名称
		
	   作用分类：
	   （1）编写文档：通过代码里标识的注解生成文档[生成文档doc文档]
	   （2）代码分析：通过代码里的标识的注解对代码进行代码（使用反射）
	    (3)编写检查：通过代码里标识的注解让编译器能够实现基本的编译检查（override）
			
		JDK中预定义的一些注解
		  @override：检测该注解的方法是否是继承父类的接口
		  @Deprecated：该注解标注的内容，表示已过时
		  @SuppressWarnings：压制警告
		    一般传递参数all  @SuppressWarnings("all")
			
	自定义注解
	   * 格式：
	   元注解
	   public @interface 注解名称｛
	   
	   ｝
	   本质：注解本质就是一个接口，该接口默认继承Annotation接口
	   	   * public interface MyAnno extends java.lang.annotation.Annotation {}

	   
	   *属性：接口中的抽象方法
	   要求：
	   1.属性的返回值类型有下列取值
	      基本数据类型
		  String
		  枚举
		  注解
		  以上类型的数组
		  
		2.定义了属性，在使用时需要给属性赋值
          a.如果定义属性时，使用default关键字给属性默认初始值，则在使用注解时，可以不进行属性的赋值
		  b.如果只有一个属性需要赋值，并且属性的名称是value,则value可以省略，直接定义值即可
		  c.数组赋值，值使用｛｝包裹，如果数组中只有一个值，则｛｝可以省略
		  
		 ***元注解：用于描述注解的注解
		     @Target：描述注解能够作用的位置
			    *ElementType取值：
				    TYPE:可以作用类上
					METHOD:可以作用于方法上
					FIELD：可以作用于成员变量上
					
				*@Retention：用于描述注解被保留的阶段
				  @Retention(RetentionPolicy.RUNTIME):当前被描述的注解，会被保留到class字节码文件中，并被JVM读取到
				  @Documented：被描述注解是否被抽取到api文档中
				  @Inherited：描述注解是否被子类继承
				  

				
