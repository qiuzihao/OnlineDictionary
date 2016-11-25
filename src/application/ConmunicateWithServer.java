package application;

public class ConmunicateWithServer
{
	//连接到服务器
	public static void connectServer()
	{
		
	}
	
	//获得当前在线（已经登录的）的用户信息
	public static void getUsersNow()
	{
		
	}
	
	//发送用户登录信息到服务器
	public static void sendLogInInfo(String info)
	{
		
	}
	
	//获得服务器对用户登录的响应
	public static void getLogInInfo()
	{
		
	}
	
	//发送用户登出信息到服务器
	public static void sendLogOutInfo(String info)
	{
		
	}
		
	//获得服务器对用户登出的响应
	public static void getLogOutInfo()
	{
		
	}
	
	//发送用户注册信息到服务器
	public static void sendSignInInfo(String info)
	{
		
	}
	
	//获得服务器对用户注册的响应
	public static void getSignInInfo()
	{
		
	} 
	
	//获得当前查询单词的点赞信息
	public static int[] getNumOfPraises(String keyword)
	{
		int[] praises=new int[3];
		praises[0]=0;  // for baidu
		praises[1]=0;  // for youdao
		praises[2]=0;  // for jinshan
		return praises; 
	}
	
	
}